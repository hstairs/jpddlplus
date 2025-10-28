package com.hstairs.ppmajal.pddl.heuristics.advanced;

import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;
import com.hstairs.ppmajal.PDDLProblem.PDDLState;
import com.hstairs.ppmajal.conditions.*;
import com.hstairs.ppmajal.conditions.PDDLObject;
import com.hstairs.ppmajal.domain.ActionParameter;
import com.hstairs.ppmajal.domain.PDDLDomain;
import com.hstairs.ppmajal.domain.Variable;
import com.hstairs.ppmajal.expressions.NumFluent;
import com.hstairs.ppmajal.expressions.PDDLNumber;
import com.hstairs.ppmajal.problem.State;
import com.hstairs.ppmajal.search.SearchHeuristic;
import com.hstairs.ppmajal.transition.TransitionGround;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.*;

// DJL imports (add DJL Core + DJL PyTorch engine jars to jar_dependencies)
//import ai.djl.Model;
import ai.djl.inference.Predictor;
//import ai.djl.modality.Classifications;
import ai.djl.ndarray.NDArray;
import ai.djl.ndarray.NDList;
import ai.djl.ndarray.NDManager;
import ai.djl.translate.Batchifier;
import ai.djl.translate.Translator;
import ai.djl.translate.TranslatorContext;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.ndarray.types.DataType;
import ai.djl.Device;
import ai.djl.engine.Engine;
import com.hstairs.ppmajal.transition.TransitionSchema;

/**
 * TorchScript-backed heuristic mirroring planner/heuristic/gnnVal_ts.py (GnnValTS),
 * using DJL (Deep Java Library) PyTorch engine to load and run the TorchScript model.
 *
 * Required runtime settings (suggested):
 * -Dgnn.ts.model=path/to/model.pt
 * -Dgnn.ts.encoding=path/to/encoding.json
 *
 * Required dependencies in jar_dependencies:
 * - djl-api, djl-pytorch-engine, djl-pytorch-native (or platform classifier), and their transitive deps
 */

/**
 * @author valerio
 */
public final class GnnValTSHeuristic implements SearchHeuristic {

    private final PDDLProblem problem;
    private final PDDLProblem frozenProblem;
    // DJL model and predictor
    private final ZooModel<TorchInputs, Float> model;
    private final Predictor<TorchInputs, Float> predictor;

    //encoding parameters
    private final Map<String, Integer> encoding;
    private final Map<Integer, String> reverseEncoding;
    private final Map<String, Integer> objEncoding;
    private final Map<Condition, Set<Condition>> preconditionsMap;
    private final Map<Condition, Condition> groundedFrozenToProblem;
    private final Map <String, Set<Condition>> goalsMap;
    private final Map <Integer,ArrayList<Integer>> boolGoalsMap;
    private final Map <Integer,BoolPredicate> boolPredicatesMap;
    private final Map <Integer,ArrayList<Integer>> boolConstantsMap;
    private final int numGoals;
    private final int numPreconditions;
    private final int numPredicates;
    private final boolean alternateEncoding;

    //private final int numPredicates;


    public GnnValTSHeuristic(PDDLProblem problem, PDDLProblem frozenProblem) {
        this(problem,
            System.getProperty("gnn.ts.model"),
            System.getProperty("gnn.ts.encoding"),
                frozenProblem);
    }

    public GnnValTSHeuristic(PDDLProblem problem, String modelPath, String encodingPath, PDDLProblem frozenProblem) {
        if (frozenProblem == null) {
            this.frozenProblem = problem;
        }
        else{
            this.frozenProblem = frozenProblem;
        }
        this.problem = problem;
        if (modelPath == null || encodingPath == null) {
            throw new IllegalArgumentException("Missing system properties: -Dgnn.ts.model and -Dgnn.ts.encoding must be set");
        }
        if (!new File(modelPath).exists()) {
            throw new IllegalArgumentException("TorchScript model not found: " + modelPath);
        }
        if (!new File(encodingPath).exists()) {
            throw new IllegalArgumentException("Encoding JSON not found: " + encodingPath);
        }
        //generate object encoding
        Map<String, Integer> objEncoding = new HashMap<>();
        int objIdx = 0;
        for (var obj : problem.getProblemObjects()) {
            objEncoding.put(obj.getName(), objIdx++);
        }
        this.objEncoding = objEncoding;



//map all lifted action preconditions to their grounded version
        PDDLDomain domain = this.frozenProblem.getLinkedDomain();
        Collection<TransitionSchema> liftedActions = domain.getActionsSchema();
        Map<String, TransitionSchema> nameToLiftedAction = new HashMap<>();
        Map<Condition, Set<Condition>> liftedToGroundedPreconditions = new HashMap<>();

        for (TransitionSchema schema : liftedActions) {
            String actionName = schema.getName();
            nameToLiftedAction.put(actionName, schema);
            Condition preconditions = schema.getPreconditions();

            if (preconditions instanceof AndCond) {
                AndCond andCond = (AndCond) preconditions;
                for (Object son : andCond.sons) {
                    if (son instanceof Comparison) {
                        Condition liftedPrecondition = (Condition) son;
                        liftedToGroundedPreconditions.put(liftedPrecondition, new HashSet<>());
                    }
                }
            } else if (preconditions != null && preconditions instanceof Comparison) {
                liftedToGroundedPreconditions.put(preconditions, new HashSet<>());
            }
        }

        for (TransitionGround action : this.frozenProblem.actions) {
            String actionBaseName = action.getName().split("\\s+")[0];
            TransitionSchema liftedSchema = nameToLiftedAction.get(actionBaseName);

            if (liftedSchema != null) {
                Condition groundedPreconditions = action.getPreconditions();
                Condition liftedPreconditions = liftedSchema.getPreconditions();

                if (groundedPreconditions instanceof AndCond &&
                        liftedPreconditions instanceof AndCond) {

                    AndCond groundedAnd = (AndCond) groundedPreconditions;
                    AndCond liftedAnd = (AndCond) liftedPreconditions;
  // Assumiamo che l'ordine delle precondizioni sia mantenuto durante la groundizzazione
 // for (int i = 0; i < groundedAnd.sons.length && i < liftedAnd.sons.length; i++) {
   // if (groundedAnd.sons[i] instanceof Comparison && liftedAnd.sons[i] instanceof Comparison) {
        //TODO: substitute lifted variables with the grounded ones to ensure a correct ordering
     //   Condition liftedCond = (Condition) liftedAnd.sons[i];
      //  Condition groundedCond = (Condition) groundedAnd.sons[i];
        //liftedCond.ground(liftedCond.getInvolvedVariables(),(PDDLObject)groundedCond.getInvolvedVariables());
     //   Collection<?> prova = groundedCond.getInvolvedFluents();
     //   Collection<?> prova2 = liftedCond.getInvolvedFluents();
                    //liftedToGroundedPreconditions.get(liftedCond).add(groundedCond);

                    // Pair lifted vs grounded preconditions by grounding lifted comparisons with the action substitution
                    // Build substitution map Variable -> PDDLObject from lifted schema params to grounded action params
                    java.util.Map<com.hstairs.ppmajal.domain.Variable, PDDLObject> substitution = new java.util.HashMap<>();
                    if (liftedSchema.getParameters() != null && action.getParameters() != null) {
                        for (int pi = 0; pi < liftedSchema.getParameters().size() && pi < action.getParameters().size(); pi++) {
                            com.hstairs.ppmajal.domain.Variable v = (com.hstairs.ppmajal.domain.Variable) liftedSchema.getParameters().get(pi);
                            PDDLObject obj = action.getParameters().get(pi);
                            substitution.put(v, obj);
                        }
                    }

                    for (Object liftedObj : liftedAnd.sons) {
                        if (!(liftedObj instanceof Comparison)) {
                            continue;
                        }
                        Condition liftedCond = (Condition) liftedObj;
                        Condition groundedLifted = liftedCond.ground(substitution, this.frozenProblem.getObjects());
                        String liftedGroundNorm = normalizeConditionString(groundedLifted.toString());

                        for (Object groundedObj : groundedAnd.sons) {
                            if (!(groundedObj instanceof Comparison)) {
                                continue;
                            }
                            Condition groundedCond = (Condition) groundedObj;
                            String groundedNorm = normalizeConditionString(groundedCond.toString());
                            if (liftedGroundNorm.equals(groundedNorm)) {
                                liftedToGroundedPreconditions.get(liftedCond).add(groundedCond);
                            }
                        }
                    }
                } else if (groundedPreconditions != null && liftedPreconditions != null && liftedPreconditions instanceof Comparison && groundedPreconditions instanceof Comparison) {
                    // Single precondition: directly associate lifted with grounded
                    liftedToGroundedPreconditions.get(liftedPreconditions).add(groundedPreconditions);
                }
            }
        }
        this.preconditionsMap = liftedToGroundedPreconditions;

        // Build a mapping between frozen grounded preconditions and original problem grounded preconditions
        Map<String, TransitionGround> signatureToProblemAction = new HashMap<>();
        for (TransitionGround act : this.problem.actions) {
            String base = act.getName().split("\\s+")[0];
            StringBuilder sig = new StringBuilder(base).append("|");
            if (act.getParameters() != null) {
                for (ActionParameter p : act.getParameters()) {
                    if (p instanceof PDDLObject) {
                        sig.append(((PDDLObject) p).getName()).append(",");
                    } else {
                        sig.append(p.toString()).append(",");
                    }
                }
            }
            signatureToProblemAction.put(sig.toString(), act);
        }

        Map<Condition, Condition> frozenToProblem = new HashMap<>();
        for (TransitionGround frozenAction : this.frozenProblem.actions) {
            String base = frozenAction.getName().split("\\s+")[0];
            StringBuilder sig = new StringBuilder(base).append("|");
            if (frozenAction.getParameters() != null) {
                for (ActionParameter p : frozenAction.getParameters()) {
                    if (p instanceof PDDLObject) {
                        sig.append(((PDDLObject) p).getName()).append(",");
                    } else {
                        sig.append(p.toString()).append(",");
                    }
                }
            }
            TransitionGround problemAction = signatureToProblemAction.get(sig.toString());
            if (problemAction == null) {
                continue;
            }

            Condition frPre = frozenAction.getPreconditions();
            Condition prPre = problemAction.getPreconditions();
            int disallainment = 0;
            if (frPre instanceof AndCond && prPre instanceof AndCond) {
                AndCond frAnd = (AndCond) frPre;
                AndCond prAnd = (AndCond) prPre;
                for(int i = 0; i < prAnd.sons.length; i++) {
                    if(prAnd.sons[i] instanceof Comparison) {
                        if(frAnd.sons[i + disallainment] instanceof Comparison) {
                            frozenToProblem.put((Condition) frAnd.sons[i + disallainment], (Condition) prAnd.sons[i]);

                        }else{
                            disallainment += 1;
                            while(! (frAnd.sons[i + disallainment] instanceof Comparison)) {
                                disallainment += 1;
                            }
                            frozenToProblem.put((Condition) frAnd.sons[i + disallainment], (Condition) prAnd.sons[i]);
                        }
                    }
                }
            } else if (frPre instanceof Comparison && prPre instanceof Comparison) {
                frozenToProblem.put(frPre, prPre);
            }
        }

        //we also add frozen numeric goals mapped with their simplified version
        AndCond goalsToMap = (AndCond) problem.getGoals();
        AndCond liftedGoals = (AndCond) problem.getLiftedGoals();

        for(int i = 0; i < goalsToMap.sons.length && i < liftedGoals.sons.length; i++){
            if ( goalsToMap.sons[i] instanceof Comparison && liftedGoals.sons[i] instanceof Comparison) {
                frozenToProblem.put((Condition) liftedGoals.sons[i], (Condition) goalsToMap.sons[i]);
            }
        }

        this.groundedFrozenToProblem = frozenToProblem;

        // Load encoding JSON (string->int)
        this.encoding = readFlatStringIntJson(encodingPath);
        Map <Integer,String> reverseEncoding = new HashMap<>();
        for (Map.Entry<String, Integer> e : encoding.entrySet()) {
            reverseEncoding.put(e.getValue(), e.getKey());
        }
        this.reverseEncoding = reverseEncoding;
        //divide the encodings based on what they represent in the problem
        String[] keys = reverseEncoding.values().toArray(new String[0]);
        int numPreconditions = 0;
        int numGoals = 0;
        for(int i = 0; i < keys.length; i++){
            if (keys[i].contains("goal_")) {
                numGoals++;
            } else if (keys[i].contains("false_")) {
                numPreconditions++;
            }
        }
        this.numPreconditions = numPreconditions;
        this.numGoals = numGoals;
        this.numPredicates = domain.getPredicates().size();

        //check if the preconditions are saved as (true,false,true,false) or (true,true,false,false)
        if(keys[this.numPredicates + this.numGoals + 1].contains("false_")){
            this.alternateEncoding = true;
        }else{
            this.alternateEncoding = false;
        }

        Map<String,Set<Condition>> goalsMap = new HashMap<>();
        for(int i = (this.numGoals + this.numPreconditions * 2 + this.numPredicates); i < this.encoding.size(); i++){
            goalsMap.put(reverseEncoding.get(i), new HashSet<>());
        }
        boolean found = false;
        Map <Integer, ArrayList<Integer>> boolGoalsMap = new HashMap<>();
        AndCond groundGoals = (AndCond) this.frozenProblem.getGoals();
        //get numeric goals map and bool goals map
        int rememberMe = 0;
        Condition rememberCond = null;
        for (int i = 0; i < groundGoals.sons.length; i++) {
            //TODO: check if this is useful, probably not since it was checked as always false
            //if (found) {
            //    goalsMap.get(reverseEncoding.get(rememberMe)).add(rememberCond);
            //}
            found = false;
            if (groundGoals.sons[i] instanceof BoolPredicate) {
                int key = this.encoding.get(((BoolPredicate) groundGoals.sons[i]).getName()) + this.numPredicates;
                if (!boolGoalsMap.containsKey(key)) {
                    boolGoalsMap.put(key, new ArrayList<>());
                }
                Collection<?> involvedObjects = ((BoolPredicate) groundGoals.sons[i]).getInvolvedVariables();
                for(Object toAdd : involvedObjects){
                    boolGoalsMap.get(key).add(this.objEncoding.get(((PDDLObject)toAdd).getName()));
                }

            } else {
                //TODO: i fluenti numerici potrebbero dover essere ordinati probabilmente, tenere controllato (done during the heuristic call)
                Set<NumFluent> numFluentsList = ((Condition) groundGoals.sons[i]).getInvolvedFluents();
                Condition cond = (Condition) groundGoals.sons[i];
                for (int j = this.numGoals + this.numPreconditions * 2 + this.numPredicates; j < this.encoding.size(); j++) {
                    if (found) {
                        goalsMap.get(reverseEncoding.get(j)).add(cond);
                        found = false;
                        break;
                    }
                    String check = reverseEncoding.get(j);
                    for (NumFluent numFluent : numFluentsList) {
                        if ((check.contains(" " + numFluent.getName() + "(")) || (check.contains(numFluent.getName() + "(") && check.startsWith(numFluent.getName())) ){
                            check = check.replaceFirst(numFluent.getName(), "found");
                            rememberMe = j;
                            rememberCond = cond;
                            found = true;
                        } else {
                            found = false;
                            break;
                        }
                    }
                }

            }
            if (found) {
                goalsMap.get(reverseEncoding.get(rememberMe)).add(rememberCond);
                found = false;
            }
        }
        this.boolGoalsMap = boolGoalsMap;
        this.goalsMap = goalsMap;

        //map containing all the predicates indicized by their id, this is here to avoid extracting it multiples times
        Map<Integer, BoolPredicate> boolPredicatesMap = new HashMap<>();
        for(Object bool : problem.getAllFluents()){
            if (bool instanceof  BoolPredicate){
                boolPredicatesMap.put(((BoolPredicate) bool).getId(), (BoolPredicate) bool);
            }
        }
        this.boolPredicatesMap = boolPredicatesMap;

        //map containing constant boolean values that are required by the gnn
        Map<Integer, ArrayList<Integer>> boolConstantsMap = new HashMap<>();
        for (BoolPredicate bool : problem.getInitBoolFluentsValues().keySet()) {
            if (! (bool.getName().equals("=")) && ! boolPredicatesMap.containsKey(bool.getId())) {
                int key = this.encoding.get(bool.getName());
                if (!boolConstantsMap.containsKey(key)) {
                    boolConstantsMap.put(key, new ArrayList<>());
                }
                Collection<?> involvedObjects = bool.getInvolvedVariables();
                for(Object toAdd : involvedObjects){
                    boolConstantsMap.get(key).add(this.objEncoding.get(((PDDLObject)toAdd).getName()));
                }

            }
        }
        this.boolConstantsMap = boolConstantsMap;


        Device device;
        if (Engine.getInstance().getGpuCount() > 0) {
            device = Device.gpu();  // usa la prima GPU disponibile
            System.out.println("Using GPU for inference.");
        } else {
            device = Device.cpu();
            System.out.println("No GPU found, falling back to CPU.");
        }

        //set the seed to ensure reproducibility
        Engine.getInstance().setRandomSeed(17);

        // Build DJL Criteria and load model with custom Translator
        try {
            Criteria<TorchInputs, Float> criteria = Criteria.builder()
                    .setTypes(TorchInputs.class, Float.class)
                    .optModelPath(Paths.get(modelPath))
                    .optEngine("PyTorch")
                    .optTranslator(new TorchScriptTranslator())
                    .optDevice(device)
                    .build();

            this.model = criteria.loadModel();
            this.predictor = this.model.newPredictor();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load TorchScript model via DJL: " + modelPath, e);
        }
        finally{
            System.clearProperty("gnn.ts.model");
            System.clearProperty("gnn.ts.encoding");

        }
    }





    @Override
    public float computeEstimate(State s0) {
        if (!(s0 instanceof PDDLState)) {
            return Float.MAX_VALUE;
        }
        final PDDLState s = (PDDLState) s0;

        final TorchInputs inputs = encodeStateForModel(s);
        try {
            Float out = predictor.predict(inputs);
            if (out == null || out.isNaN()) {
                return Float.MAX_VALUE;
            }
            return out;
        } catch (Exception e) {
            // If inference fails, return an uninformed large value
            return Float.MAX_VALUE;
        }
    }

    @Override
    public Object[] getTransitions(boolean onlyHelpful) {
        return problem.actions.toArray();
    }

    @Override
    public Collection<TransitionGround> getAllTransitions() {
        return Collections.emptyList();
    }

    // --- Encoding helpers ---

    private TorchInputs encodeStateForModel(PDDLState s) {
        // Mirrors Python: __encode_gnn_state__ + get_encoded_states_with_values + collate
        Map<Integer, ArrayList<Integer>> tempInput = new HashMap<>();
        Map<Integer, ArrayList<Double>> tempInits = new HashMap<>();
        long[] sizes = new long[1];
        sizes[0] = this.objEncoding.size();

        //initialize input and inits maps, this can be changed and put in the constructor to avoid doing it multiples times
        for(int i = 0; i < this.encoding.size(); i++){
            tempInput.put(i, new ArrayList<>());
            tempInits.put(i, new ArrayList<>());
        }
        //main body that handles inputs for boolean goals, boolean inits are always empty, since there are no numeric values involved
        for(int i : this.boolGoalsMap.keySet()){
            tempInput.put(i, this.boolGoalsMap.get(i));
        }

        //main body that handles inputs for boolean constants
        for(int i : this.boolConstantsMap.keySet()){
            tempInput.put(i, this.boolConstantsMap.get(i));
        }

        //main body that handles inputs for boolean predicates
        if (s.getBoolFluentsSize() > 0){
            BitSet boolValues = s.getBoolFluents();
            for(int i : boolValues.stream().toArray()){
                BoolPredicate predToAdd = this.boolPredicatesMap.get(i);
                int key = this.encoding.get(predToAdd.getName());
                Collection<?> involvedObjects = predToAdd.getInvolvedVariables();
                for(Object toAdd : involvedObjects){
                    tempInput.get(key).add(this.objEncoding.get(((PDDLObject)toAdd).getName()));
                }
            }
        }


        Iterator<Condition> it = this.preconditionsMap.keySet().iterator();
        int numPrec = 0;
        boolean satisfied = false;
        int toPut;
        String condName;
        //main body that handles inputs for action preconditions
        while (it.hasNext()) {
            Condition key = it.next();
            Set<Condition> prec = this.preconditionsMap.get(key);
            //we need to preserve the correct ordering of the objects
            Set<Integer> objs;

            if(this.alternateEncoding) {
                condName = reverseEncoding.get(this.numGoals + this.numPredicates + numPrec * 2);
            }
            else{
                condName = reverseEncoding.get(this.numGoals + this.numPredicates + numPrec);
                }

            // Regex: cattura una sequenza di caratteri \w che precede una (
            Pattern pattern = Pattern.compile("(\\w+)\\s*\\(");
            Matcher matcher = pattern.matcher(condName);
            List<String> resultList = new ArrayList<>();
            while (matcher.find()) {
                resultList.add(matcher.group(1));
            }

            // Convertiamo in array di stringhe
            String[] result = resultList.toArray(new String[0]);
            for (Condition cond : prec) {
                Condition evalCond = this.groundedFrozenToProblem.getOrDefault(cond, cond);
                satisfied = evalCond.isSatisfied(s);
                if (satisfied) {
                    if (this.alternateEncoding) {
                        toPut = this.numGoals + this.numPredicates + numPrec * 2;
                    } else {
                        toPut = this.numGoals + this.numPredicates + numPrec;
                    }

                } else {
                    if(this.alternateEncoding) {
                        toPut = this.numGoals + this.numPredicates + numPrec * 2 + 1;
                    }
                    else{
                        toPut = this.numGoals + this.numPredicates + this.numPreconditions + numPrec;
                    }

                }
                Set<NumFluent> numFluentsList = cond.getInvolvedFluents();
                NumFluent[] toOrder = new NumFluent[numFluentsList.size()];
                for (NumFluent numFluent : numFluentsList) {
                    for (int i = 0; i < result.length; i++) {
                        if (numFluent.getName().equals(result[i])) {
                            toOrder[i] = numFluent;
                            break;
                        }
                    }
                }
                objs = new LinkedHashSet<>();

                for (NumFluent numFluent : toOrder) {
                    if (numFluent != null) {
                        double value = s.fluentValue(numFluent);

                        if (Double.isNaN(value)) {
                            Map<NumFluent, PDDLNumber> numValue = s.getP().getInitNumFluentsValues();
                            value = numValue.get(numFluent).eval(s);
                        }
                        tempInits.get(toPut).add(value);
                        for (ActionParameter newObj : numFluent.getTerms()) {
                            objs.add(this.objEncoding.get(((PDDLObject) newObj).getName()));
                        }
                    }
                }
                for (Integer obj : objs) {
                    tempInput.get(toPut).add(obj);
                }
            }

            numPrec += 1;
        }

        //main body that handles numeric goal conditions
        int numGoals = 0;
        Iterator<String> it2 = this.goalsMap.keySet().iterator();
        while (it2.hasNext()) {
            String key = it2.next();
            Set<Condition> goals = this.goalsMap.get(key);
            Set<Integer> objs;

            for(Condition cond : goals){
                Condition evalCond = this.groundedFrozenToProblem.getOrDefault(cond, cond);
                satisfied = evalCond.isSatisfied(s);
                Set<NumFluent> numFluentsList = cond.getInvolvedFluents();
                objs = new LinkedHashSet<>();
                String repr = cond.toString();
                // Ordina i fluenti secondo la posizione nella stringa
                List<NumFluent> orderedFluents = new ArrayList<>(numFluentsList);
                orderedFluents.sort(Comparator.comparingInt(fluent ->
                        repr.indexOf(fluent.toString())));

                // Se è presente un ">", inverte l’ordine
                if (repr.contains(">")) {
                    Collections.reverse(orderedFluents);
                }
                for (NumFluent numFluent : orderedFluents) {
                    double value = s.fluentValue(numFluent);
                    if (Double.isNaN(value)) {
                        Map<NumFluent,PDDLNumber> numValue = s.getP().getInitNumFluentsValues();
                        value = numValue.get(numFluent).eval(s);
                    }
                    tempInits.get(this.numPredicates*2 + numGoals).add(value);
                    if(satisfied){
                        tempInits.get(encoding.get(key)).add(value);
                    }
                    for(ActionParameter newObj : numFluent.getTerms()){
                        objs.add(this.objEncoding.get(((PDDLObject)newObj).getName()));
                    }
                }
                tempInput.get(this.numPredicates*2 + numGoals).addAll(objs);
                if(satisfied) {
                    tempInput.get(encoding.get(key)).addAll(objs);
                }
            }
            numGoals +=1;
        }



        Map<Integer, long[]> input = new HashMap<>();
        // Init/goal encoding left empty here; extend if your model expects it
        Map<Integer, double[]> inits = new HashMap<>();

        // Conversione tempInput → input
        for (Map.Entry<Integer, ArrayList<Integer>> entry : tempInput.entrySet()) {
            ArrayList<Integer> list = entry.getValue();
            long[] arr = new long[list.size()];
            for (int i = 0; i < list.size(); i++) {
                arr[i] = list.get(i).longValue();
            }
            input.put(entry.getKey(), arr);
        }

// Conversione tempInits → inits
        for (Map.Entry<Integer, ArrayList<Double>> entry : tempInits.entrySet()) {
            ArrayList<Double> list = entry.getValue();
            double[] arr = new double[list.size()];
            for (int i = 0; i < list.size(); i++) {
                arr[i] = list.get(i);  // ⚠️ attenzione: perde la parte decimale
            }
            inits.put(entry.getKey(), arr);
        }
        return new TorchInputs(input, sizes, inits);
    }

    private static Map<String, Integer> readFlatStringIntJson(String path) {
        Map<String, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read encoding JSON: " + path, e);
        }
        String json = sb.toString().trim();
        if (json.startsWith("{") && json.endsWith("}")) {
            json = json.substring(1, json.length() - 1).trim();
        }
        if (json.isEmpty()) {
            return map;
        }
        String[] entries = json.split(",");
        for (String entry : entries) {
            String[] kv = entry.split(":");
            if (kv.length != 2) continue;
            String key = kv[0].trim();
            if (key.startsWith("\"") && key.endsWith("\"")) {
                key = key.substring(1, key.length() - 1);
            }
            String valueStr = kv[1].trim();
            valueStr = valueStr.replaceAll("[^0-9-]", "");
            if (valueStr.isEmpty()) continue;
            try {
                int val = Integer.parseInt(valueStr);
                map.put(key, val);
            } catch (NumberFormatException ignored) {
            }
        }
        return map;
    }


     //this was the first attempt to normalize conditions, currently unused
    private String normalizeConditionString(String stringRepresentation) {
        // Rimuoviamo le parentesi esterne
        String normalized = stringRepresentation.trim();
        // Step 1: temporary replace '-' used in identifiers (not minus) like Python does
        boolean tmpReplace = false;
        if (!normalized.contains("- ") && !normalized.contains(" -") && normalized.contains("-")) {
            normalized = normalized.replace("-", "_");
            tmpReplace = true;
        }

        // Remove parentheses
        normalized = normalized.replaceAll("[\\(\\)]", "");
        // Remove patterns like "?x - type"
        normalized = normalized.replaceAll("\\?\\w+\\s+-\\s+", "");

        // Detect comparison operator
        String operator = "";
        if (normalized.contains("<=")) operator = "<=";
        else if (normalized.contains(">=")) operator = ">=";
        else if (normalized.contains("==")) operator = "==";
        else if (normalized.contains("=")) operator = "=";
        else if (normalized.contains("<")) operator = "<";
        else if (normalized.contains(">")) operator = ">";

        // Split by operator
        String leftSide = normalized;
        String rightSide = "";
        if (!operator.isEmpty()) {
            String[] parts = normalized.split(Pattern.quote(operator), 2);
            if (parts.length == 2) {
                leftSide = parts[0].trim();
                rightSide = parts[1].trim();
            }
        }

        // Normalize operator direction to left form like Python:
        //   >  => flip and use <
        //   >= => flip and use <=
        //   == => treat as =
        if (">".equals(operator)) {
            // a > b  =>  b - a < 0
            String tmp = leftSide;
            leftSide = rightSide;
            rightSide = tmp;
            operator = "<";
        } else if (">=".equals(operator)) {
            // a >= b =>  b - a <= 0
            String tmp = leftSide;
            leftSide = rightSide;
            rightSide = tmp;
            operator = "<=";
        } else if ("==".equals(operator)) {
            operator = "=";
        } else if (operator.isEmpty()) {
            // No operator detected; return the cleaned string
            String outNoOp = normalized.replaceAll("(?<!\\s)([*/])(?!\\s)", " $1 ");
    outNoOp = outNoOp.replaceAll("\\s{2,}", " ").trim();
            if (tmpReplace) {
        outNoOp = outNoOp.replace("-", "- ").replace("_", "-");
    } else {
        outNoOp = outNoOp.replace("-", "- ");
    }
            return outNoOp;
}

// Build left-normalized expression: left - right op 0
String expr = leftSide + " - " + rightSide + " " + operator + " 0";

// Add spacing around * and /, collapse multiple spaces
expr = expr.replaceAll("(?<!\\s)([*/])(?!\\s)", " $1 ");
expr = expr.replaceAll("\\s{2,}", " ").trim();

// Restore hyphens in identifiers and ensure spacing around minus
        if (tmpReplace) {
expr = expr.replace("-", "- ").replace("_", "-");
        } else {
expr = expr.replace("-", "- ");
        }
                return expr;
    }

    private String buildNumFluentSignature(NumFluent f) {
        StringBuilder sb = new StringBuilder();
        sb.append(f.getName()).append("(");
        boolean first = true;
        for (ActionParameter term : f.getTerms()) {
            String name;
            if (term instanceof PDDLObject) {
                name = ((PDDLObject) term).getName();
            } else if (term instanceof Variable) {
                name = ((Variable) term).getName();
            } else {
                name = term.toString();
            }
            if (!first) sb.append(" ");
            sb.append(name);
            first = false;
        }
        sb.append(")");
        return sb.toString();
    }

    // Container for model inputs
    private static final class TorchInputs {
        final Map<Integer, long[]> input;  // predicateId -> indices
        final long[] sizes;                // one size per batch elt (batch=1)
        final Map<Integer, double[]> inits;  // predicateId -> init/goal vector indices

        TorchInputs(Map<Integer, long[]> input, long[] sizes, Map<Integer, double[]> inits) {
            this.input = input;
            this.sizes = sizes;
            this.inits = inits;
        }
    }

    // Translator turning TorchInputs into NDList for the TorchScript model, and reading back a scalar float
    private static final class TorchScriptTranslator implements Translator<TorchInputs, Float> {

        @Override
        public NDList processInput(TranslatorContext ctx, TorchInputs inputs) {
            NDManager manager = ctx.getNDManager();

            // ====== Pack input map (bool) as parallel key/value tensors ======
            int numKeys = inputs.input.size();
            long[] keys = new long[numKeys];
            List<long[]> segments = new ArrayList<>();
            int k = 0;
            for (Map.Entry<Integer, long[]> e : inputs.input.entrySet()) {
                keys[k++] = e.getKey();
                segments.add(e.getValue());
            }

            // Flatten values and build offsets
            long totalLen = segments.stream().mapToLong(arr -> arr.length).sum();
            long[] values = new long[(int) totalLen];
            long[] offsets = new long[numKeys + 1];
            int pos = 0;
            int idx = 0;
            for (long[] seg : segments) {
                System.arraycopy(seg, 0, values, pos, seg.length);
                pos += seg.length;
                offsets[idx + 1] = offsets[idx] + seg.length;
                idx++;
            }

            //NDArray keysArr = manager.create(keys, new Shape(keys.length)).toType(DataType.INT64, false);
            //NDArray valuesArr = manager.create(values, new Shape(values.length)).toType(DataType.INT64, false);
            //NDArray offsetsArr = manager.create(offsets, new Shape(offsets.length)).toType(DataType.INT64, false);
            //NDArray sizesArr = manager.create(inputs.sizes, new Shape(inputs.sizes.length)).toType(DataType.INT64, false);
            NDArray keysArr = manager.create(keys).toType(DataType.INT64, false);
            NDArray valuesArr = manager.create(values).toType(DataType.INT64, false);
            NDArray offsetsArr = manager.create(offsets).toType(DataType.INT64, false);
            NDArray sizesArr = manager.create(inputs.sizes).toType(DataType.INT64, false);

            // ====== Pack inits map (numeric) ======
            int numInitKeys = inputs.inits.size();
            long[] initKeys = new long[numInitKeys];
            List<double[]> initSegments = new ArrayList<>();
            int ik = 0;
            for (Map.Entry<Integer, double[]> e : inputs.inits.entrySet()) {
                initKeys[ik++] = e.getKey();
                initSegments.add(e.getValue());
            }

            long totalInitLen = initSegments.stream().mapToLong(arr -> arr.length).sum();
            double[] initValues = new double[(int) totalInitLen];
            long[] initOffsets = new long[numInitKeys + 1];
            pos = 0;
            idx = 0;
            for (double[] seg : initSegments) {
                System.arraycopy(seg, 0, initValues, pos, seg.length);
                pos += seg.length;
                initOffsets[idx + 1] = initOffsets[idx] + seg.length;
                idx++;
            }

            //NDArray initKeysArr = manager.create(initKeys, new Shape(initKeys.length)).toType(DataType.INT64, false);
            //NDArray initValuesArr = manager.create(initValues, new Shape(initValues.length)).toType(DataType.FLOAT32, false);
            //NDArray initOffsetsArr = manager.create(initOffsets, new Shape(initOffsets.length)).toType(DataType.INT64, false);
            NDArray initKeysArr = manager.create(initKeys).toType(DataType.INT64, false);
            NDArray initValuesArr = manager.create(initValues).toType(DataType.FLOAT32, false);
            NDArray initOffsetsArr = manager.create(initOffsets).toType(DataType.INT64, false);

            // ====== Return NDList in correct order ======
            return new NDList(
                    keysArr,        // bool_keys (INT64)
                    offsetsArr,     // bool_offsets (INT64)
                    valuesArr,      // bool_values (INT64)
                    sizesArr,       // batch_sizes (INT64)
                    initKeysArr,    // num_keys (INT64)
                    initOffsetsArr, // num_offsets (INT64)
                    initValuesArr   // num_values (FLOAT32)
            );
        }




        @Override
        public Float processOutput(TranslatorContext ctx, NDList list) {
            // Expect a single scalar output
            NDArray out = list.singletonOrThrow();
            return out.toFloatArray()[0];
        }

        @Override
        public Batchifier getBatchifier() {
            // Single-state evaluation at a time
            return Batchifier.STACK;
        }
    }
}