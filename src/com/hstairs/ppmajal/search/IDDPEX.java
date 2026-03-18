package com.hstairs.ppmajal.search;

import com.hstairs.ppmajal.PDDLProblem.PDDLState;
import com.hstairs.ppmajal.extraUtils.ExternalLoggerLogType;
import com.hstairs.ppmajal.problem.State;
import com.hstairs.ppmajal.search.searchnodes.SearchNode;
import com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode;
import com.hstairs.ppmajal.search.searchnodes.DPEXSearchNode;
import it.unimi.dsi.fastutil.PriorityQueue;
import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import it.unimi.dsi.fastutil.objects.Object2FloatOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectHeapPriorityQueue;
import org.jgrapht.alg.util.Pair;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.Objects;
import java.util.Queue;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;

import com.hstairs.ppmajal.transition.TransitionSchema;

import com.hstairs.ppmajal.PDDLProblem.PDDLState;
import com.hstairs.ppmajal.PDDLProblem.PDDLProblem;    

public class IDDPEX extends SearchEngine {
    protected final boolean optimality;
    protected long previous;
    protected float hAtInit;
    final protected float hw;
    final protected TieBreaker tieBreaker;
    final protected boolean saveSearchSpace;
    final protected float gBound;

    final protected boolean bucketPriorityQueue;

    final protected Map<String, double[]> inputBounds;
    private final Map<String, Set<String>> actionsWithInputs = new java.util.HashMap<>();

    final private int K; // number of actions to sample
    final private String rectification_function; 
    private final String sampling_function;
    private final Map<DPEXSearchNode, Integer> partialExpansions;
    private final boolean ignoreStoringInputs;

    //private final Map<PDDLState, java.util.Set<Object>> expandedActionsByState = new java.util.HashMap<>(); Esto va mal. necesito almacenarlo en el state


    public  IDDPEX(float hw, boolean optimality, boolean helpfulActionsPruning,
                         TieBreaker tieBreaker, boolean saveSearchSpace, float gBound){
        this(hw,optimality,helpfulActionsPruning,tieBreaker,saveSearchSpace,gBound,false,null,1,"","",true);
    }
    public IDDPEX(float hw, boolean optimality, boolean helpfulActionsPruning,
                  TieBreaker tieBreaker, boolean saveSearchSpace, float gBound, boolean bucketPriorityQueue,
                   Map<String, double[]> inputBounds, int K, String rectification_function, String sampling_function, boolean ignoreStoringInputs) {
        super(helpfulActionsPruning);
        this.optimality = optimality;
        this.hw = hw;
        this.tieBreaker = tieBreaker;
        this.saveSearchSpace = saveSearchSpace;
        this.gBound = gBound;
        this.bucketPriorityQueue = bucketPriorityQueue;
        this.inputBounds = inputBounds;
        this.K = K;
        this.rectification_function = rectification_function;       
        this.sampling_function = sampling_function;
        this.partialExpansions = new java.util.HashMap<>();
        this.ignoreStoringInputs = ignoreStoringInputs;
        System.out.println(this.optimality ? "Optimal Search" : "Suboptimal Search");
    }
    public SearchStats getStats(){
        return new SearchStats(nodesExpanded,nodesEvaluated,deadEndsDetected,duplicatedDetected,totalTime,heuristicTime);
    }
    protected float getPreviousCost(Object2FloatMap<State> gMap, State successorState) {
        return gMap.getOrDefault(((PDDLState)successorState).getRepresentative(inputBounds), G_DEFAULT); // Angel
    }
    enum retCode{inserted, deadend, duplicated};
    
    protected retCode queueSuccessor(Object frontier, State successorState,
                                     DPEXSearchNode current_node, Object actionsBefore,
                                     float prev_cost, float gSuccessor, Object2FloatMap<State> g, SearchHeuristic h,
                                     float hw, Map<String, Double> sampledInputs) {
        if (Objects.equals(prev_cost, this.G_DEFAULT) || gSuccessor < prev_cost) {
            final long start = System.currentTimeMillis();
            final float hValue = h.computeEstimate(successorState);
            heuristicTime += System.currentTimeMillis() - start;
            if (hValue != Float.MAX_VALUE) {// && (d + succ_g) < this.depthLimit) {
                final DPEXSearchNode node = !optimality ?
                        new DPEXSearchNode(successorState, actionsBefore,
                                current_node, gSuccessor, hValue * hw, hValue, saveSearchSpace,this.extenalLogger != null, sampledInputs)
                        : new DPEXSearchNode(successorState, actionsBefore,
                        current_node, gSuccessor, hValue * hw + gSuccessor, hValue, saveSearchSpace,this.extenalLogger != null, sampledInputs);
                if (this.helpfulActions) {
                    node.helpfulActions = h.getTransitions(helpfulActions);
                }
                if (saveSearchSpace) {
                    current_node.add_descendant(node);
                }
                addInFrontier(frontier, node);
                g.put(((PDDLState)successorState).getRepresentative(inputBounds), gSuccessor); // Angel
                return retCode.inserted;
            } else {
                return retCode.deadend; 
            }
        }
        return retCode.duplicated; //reduce rectification if duplicated?
    }

    protected void addInFrontier(Object frontier, DPEXSearchNode newNode) {
        if (frontier instanceof Queue) {
            ((Queue) frontier).add(newNode);
        } else if (frontier instanceof ObjectHeapPriorityQueue) {
            ((ObjectHeapPriorityQueue) frontier).enqueue(newNode);
        } else if (frontier instanceof BucketPriorityQueue){
            ((BucketPriorityQueue) frontier).enqueue(newNode);
        }

        this.tryLog(newNode, ExternalLoggerLogType.Generating);
    }


    

    @Override
    public SimpleSearchNode search(SearchProblem problem, SearchHeuristic h, PrintStream out) {
        int M = 10000000; // max iterations
        int R_MAX = 1; // max rectifications
        while (true) {
            System.out.println("Starting DPEX with M=" + M + " and R_MAX=" + R_MAX);
            SimpleSearchNode result = search_(problem, h, out, M, R_MAX);
            if (result != null) {
                return result;
            }

            if (R_MAX > 10){
                M *= 2;
                R_MAX *= 2;
            } else {
                R_MAX++;
            }
            
            if (R_MAX > 100){
                System.out.println("ID-DPEX: exceeded maximum R_MAX limit.");
                return null;
            }
        }
    }

    public SimpleSearchNode search_(SearchProblem problem, SearchHeuristic h, PrintStream out, int M, int R_MAX) 
    {

        // Get actions with inputs 

        ((PDDLProblem) problem).getLinkedDomain().getActionsSchema().forEach( a -> {
            for (String input : inputBounds.keySet()) {
                if (a.toString().contains("("+input+")")){
                    actionsWithInputs.computeIfAbsent(a.getName(), k -> new java.util.HashSet<>()).add(input);
                }
            }
        });

        //System.out.println("Actions with inputs: " + actionsWithInputs);


        java.util.Random rand = new java.util.Random(); // with seed 1 the problem is unsolvable

        zeroCounters();
        final State initState = problem.getInit();

        if (!problem.satisfyGlobalConstraints(initState)) {
            out.println("Initial State is not valid");
            return null;
        }
                long timeAtStart = System.currentTimeMillis();
        hAtInit = h.computeEstimate(initState);
        final PriorityQueue<DPEXSearchNode> frontier = getPriorityQueue((int)hAtInit*10);

        out.println("h(I):"+hAtInit);

        heuristicTime += System.currentTimeMillis() - timeAtStart;
        if (hAtInit == Float.MAX_VALUE) {
            deadEndsDetected++;
            return null;
        }else{
            nodesEvaluated++;
        }
        DPEXSearchNode init = new DPEXSearchNode(initState.clone(),
                0,hw*hAtInit,hAtInit, saveSearchSpace, this.extenalLogger != null);
        if (this.helpfulActions) {
            init.helpfulActions = h.getTransitions(helpfulActions);
        }

        super.initHandle(init); //This is to inspect the search space if needed

        frontier.enqueue(init);
        this.tryLog(init, ExternalLoggerLogType.Generating);

        Object2FloatMap<State> gValue = new Object2FloatOpenHashMap<>();
        gValue.put(initState, 0f);//The initial state is at 0 distance, of course.
        float bestf = 0;
        previous = 0;

        int numIts = 0;

        while (!frontier.isEmpty() && (numIts < M || M == -1)) {
            numIts++;
            //System.out.println("------------------------------------------------");
            //printFrontier(frontier);

            final DPEXSearchNode currentNode = frontier.dequeue();

            //System.out.println("#####################");
            //System.out.println("Chosen state:");
            //System.out.println(currentNode);
            //System.out.println(currentNode.f);
            //System.out.println("#####################");

            this.tryLog(currentNode, ExternalLoggerLogType.Expanding);

            //System.out.println(currentNode.gValue);
            //System.out.println(getPreviousCost(gValue, currentNode.s));
            //System.out.println(gValue);
            
            int number_of_expansions = partialExpansions.getOrDefault(currentNode, 0);


            if (currentNode.gValue == getPreviousCost(gValue, currentNode.s)){
                nodesExpanded++;

                partialExpansions.put(currentNode, number_of_expansions + 1);

                long fromTheBeginning = (System.currentTimeMillis() - timeAtStart);
                final Boolean res = problem.goalSatisfied(currentNode.s);
                if (res == null) {//this means it is a dead-end
                    deadEndsDetected++;
                    continue;
                }else if (res) {
                    totalTime = (System.currentTimeMillis() - timeAtStart);
                    return currentNode;
                }
                bestf = printInfoDuringSearch(timeAtStart,out,bestf,fromTheBeginning,
                        nodesExpanded,nodesEvaluated,frontier,currentNode);
                final Object[] actionsToSearch = getActionsToSearch(currentNode, problem, h);


                // Sample input values 
                
                Map<String, Double> sampledInputs = new java.util.HashMap<>();
                State stateWithSampledInputs = currentNode.s;  // Will be replaced if we sample
                List<Pair<State, Object>> successors = new ArrayList<>();
                // Map to store which sampledInputs were used for each successor
                Map<Pair<State, Object>, Map<String, Double>> successorInputs = new java.util.HashMap<>();

                int max_tries =  100;
                int tries = 0;
                do { 
                    
                    if (inputBounds != null && currentNode.s instanceof com.hstairs.ppmajal.PDDLProblem.PDDLState) {
                        // IMPORTANT: Clone the state to avoid modifying the original
                        com.hstairs.ppmajal.PDDLProblem.PDDLState pddlState = (com.hstairs.ppmajal.PDDLProblem.PDDLState) currentNode.s.clone();
                        sampledInputs = new java.util.HashMap<>();  // Create new map for this iteration
                        for (Map.Entry<String, double[]> entry : inputBounds.entrySet()) {

                            String fluentName = entry.getKey();

                            double value = 0.0;
                            if (sampling_function.equals("uniform")) {
                                double lower = entry.getValue()[0];
                                double upper = entry.getValue()[1];
                                double n = entry.getValue()[2]; // decimals
                                
                                double factor = Math.pow(10, n);

                                value = lower + (upper - lower) * rand.nextDouble();
                                if (!(Double.isNaN(n) || n < 0.0)) {
                                    value = Math.floor(value * factor) / factor;
                                }
                            } else if (sampling_function.equals("lhs")) {
                                // Latin Hypercube Sampling
                                // TO DO
                            } else if (sampling_function.equals("halton")) {
                                // Halton sequence
                                // TO DO
                            } 
                            com.hstairs.ppmajal.expressions.NumFluent numFluent = com.hstairs.ppmajal.expressions.NumFluent.numericFluent(fluentName);
                            pddlState.setNumFluent(numFluent, value);

                            sampledInputs.put(fluentName, value);
                            
                        }
                        stateWithSampledInputs = pddlState;  // Use the cloned state with sampled values
                    }
                    
                    
                    // Convert the actions into a list
                    Iterator<Pair<State, Object>> it = problem.getSuccessors(stateWithSampledInputs, actionsToSearch);
                    while (it.hasNext()) {
                        Pair<State, Object> succ = it.next();
                        Object act = succ.getSecond();
                        String actStr = act.toString();

                        if (!ignoreStoringInputs) {
                            // Extract preamble of the action
                            String actionPreamble = actStr;
                            if (actStr.startsWith("(") && actStr.endsWith(")")) {
                                String tmp = actStr.substring(1, actStr.length() - 1);
                                int idx = tmp.indexOf(' ');
                                if (idx > 0) {
                                    actionPreamble = tmp.substring(0, idx);
                                } else {
                                    actionPreamble = tmp;
                                }
                            }

                            String appliedKey;
                            if (actionsWithInputs.containsKey(actionPreamble)) {
                                // If the action uses inputs, then append the inputs to the action key
                                Set<String> usedInputs = actionsWithInputs.get(actionPreamble);
                                StringBuilder keyBuilder = new StringBuilder(actStr);
                                for (String input : usedInputs) {
                                    if (sampledInputs.containsKey(input)) {
                                        keyBuilder.append(",").append(input).append("=").append(sampledInputs.get(input));
                                    }
                                }
                                appliedKey = keyBuilder.toString();
                            } else {
                                // If the action does not use inputs, key = complete action
                                appliedKey = actStr;
                            }

                            if (!((DPEXSearchNode) currentNode).isActionAlreadyApplied(appliedKey)) {
                                successors.add(succ);
                                // Store the sampled inputs for this specific successor
                                successorInputs.put(succ, new java.util.HashMap<>(sampledInputs));
                            }else{
                                //System.out.println("Skipping already applied action: " + appliedKey);
                            }
                        } else {
                            successors.add(succ);
                            // Store the sampled inputs for this specific successor
                            successorInputs.put(succ, new java.util.HashMap<>(sampledInputs));
                        }
                    } 
                    
                    tries++;
                } while (successors.isEmpty() && tries < max_tries);


                if (tries == max_tries){
                    if (!ignoreStoringInputs){
                        deadEndsDetected++;
                    } else {
                        if (rectification_function.equals("linear")) {
                            currentNode.f += 0.1;
                        } else if (rectification_function.equals("quadratic")) {
                            currentNode.f += 2 * number_of_expansions + 1;
                        } else if (rectification_function.equals("logarithmic")) {
                            currentNode.f += (float)Math.log(1+1/(number_of_expansions+1));
                        }
                        addInFrontier(frontier, currentNode);
                    }
                    continue;
                }
                //System.out.println(successors);
                
                // Sample k actions
                Collections.shuffle(successors);  // mezcla aleatoriamente
                List<Pair<State, Object>> sampled = successors.subList(0, Math.min(K, successors.size()));
                
                // Iterate over the sample
                for (Pair<State, Object> next : sampled) {
                    final State successorState = next.getFirst();
                    final Object act = next.getSecond();
                    String actStr = act.toString();

                    // Get the sampled inputs that were used for THIS specific successor
                    Map<String, Double> successorSampledInputs = successorInputs.get(next);

                    if (!ignoreStoringInputs) {
                        
                        // Extract preamble
                        String actionPreamble = actStr;
                        if (actStr.startsWith("(") && actStr.endsWith(")")) {
                            String tmp = actStr.substring(1, actStr.length() - 1);
                            int idx = tmp.indexOf(' ');
                            if (idx > 0) {
                                actionPreamble = tmp.substring(0, idx);
                            } else {
                                actionPreamble = tmp;
                            }
                        }

                        String appliedKey;
                        if (actionsWithInputs.containsKey(actionPreamble)) {
                            Set<String> usedInputs = actionsWithInputs.get(actionPreamble);
                            StringBuilder keyBuilder = new StringBuilder(actStr);
                            for (String input : usedInputs) {
                                if (sampledInputs.containsKey(input)) {
                                    keyBuilder.append(",").append(input).append("=").append(sampledInputs.get(input));
                                }
                            }
                            appliedKey = keyBuilder.toString();
                        } else {
                            appliedKey = actStr;
                        }
                        if (currentNode instanceof DPEXSearchNode) {
                            DPEXSearchNode dpexNode = (DPEXSearchNode) currentNode;
                            if (dpexNode.isActionAlreadyApplied(appliedKey)) {
                                System.out.println("Skipping already applied action at queuing: " + appliedKey);
                                continue; // Skip this action. this should not happen due to the previous filtering
                            } else {
                                dpexNode.markActionAsApplied(appliedKey);
                                //System.out.println("Applied action: " + appliedKey);
                            }
                        }
                    }
                    
                    
                    /*if (inputBounds != null) {
                        com.hstairs.ppmajal.PDDLProblem.PDDLState pddlState = (com.hstairs.ppmajal.PDDLProblem.PDDLState) successorState;
                        for (Map.Entry<String, double[]> entry : inputBounds.entrySet()) {

                            String fluentName = entry.getKey();

                            double value = successorSampledInputs.getOrDefault(fluentName, 0.0);
                            com.hstairs.ppmajal.expressions.NumFluent numFluent = com.hstairs.ppmajal.expressions.NumFluent.numericFluent(fluentName);
                            pddlState.setNumFluent(numFluent, value);

                            //sampledInputs.put(fluentName, value);
                            
                        }
                    }*/

                    //if (act.toString().contains("withdraw")){
                    //System.out.println("---------------");
                    //System.out.println(currentNode.s + " g=" + currentNode.gValue + " -- " + act);}
                    //final float successorG = problem.gValue(currentNode.s, act, successorState, currentNode.gValue);
                    final float successorG = problem.gValue(stateWithSampledInputs, act, successorState, currentNode.gValue);

                    //if (act.toString().contains("withdraw")){
                    //System.out.println(successorState + " g=" + successorG );
                    //}
                    
                    
                    if (successorG < gBound) {
                        if (Objects.equals(successorG, this.G_DEFAULT)) {
                            deadEndsDetected++;
                            continue;
                        }
                        switch (this.queueSuccessor(frontier, successorState, currentNode, act,
                                getPreviousCost(gValue, successorState), successorG, gValue, h, hw, successorSampledInputs)) {
                            case inserted -> nodesEvaluated++;
                            case deadend -> deadEndsDetected++;
                            case duplicated -> duplicatedDetected++;
                        }
                    }
                }

                // Reinsert the parent node. 
                // We increase its f-value according to the rectification function recursively
                if (currentNode.rectification <= R_MAX){
                    if (rectification_function.equals("linear")) {
                        currentNode.f += 0.1;
                        currentNode.rectification += 0.1;
                    } else if (rectification_function.equals("quadratic")) {
                        currentNode.f += 2 * number_of_expansions + 1;
                        currentNode.rectification += 2 * number_of_expansions + 1;
                    } else if (rectification_function.equals("logarithmic")) {
                        currentNode.f += (float)Math.log(1+1/(number_of_expansions+1));
                        currentNode.rectification += (float)Math.log(1+1/(number_of_expansions+1));
                    }
                }


                // Reinsert currentNode into the frontier
                if (tries != max_tries)
                    addInFrontier(frontier, currentNode);



            }

            this.tryLog(currentNode, ExternalLoggerLogType.Closing);
        }
        return null;
    }

    // Just for debugging
    private void printFrontier(PriorityQueue heap) {
            List<Object> temp = new ArrayList<>();
            while (!heap.isEmpty()) {
                Object node = heap.dequeue();
                temp.add(node);
            }
            for (Object node : temp){ System.out.println(node); System.out.println(((DPEXSearchNode)node).f);System.out.println();}
            for (Object node : temp) heap.enqueue(node);
    }

    protected PriorityQueue getPriorityQueue(int size) {
        if (bucketPriorityQueue){
            return new BucketPriorityQueue(size,0,1);
        }else{
            return new ObjectHeapPriorityQueue<>(tieBreaker);
        }
    }


    protected float printInfoDuringSearch(long timeAtStart, PrintStream out, float bestf, long fromTheBeginning,
                                        int nodesExpanded,
                                        int nodesEvaluated, Object frontier,
                                        DPEXSearchNode currentNode) {
        if (fromTheBeginning >= previous + 10000) {
            final float speed = nodesExpanded / (fromTheBeginning / 1000);
            out.println("-------------Time: " + fromTheBeginning / 1000
                    + "s ; Expanded Nodes: " + nodesExpanded +
                    " (Avg-Speed " + speed + " n/s); Evaluated States: " + nodesEvaluated + " ; Dead Ends: " + deadEndsDetected +
                    " ; Duplicated: " + duplicatedDetected);
            previous = fromTheBeginning;
        }
        final float hValueInCurrentNode = optimality ? currentNode.f-currentNode.gValue : currentNode.f;
        if (optimality && (bestf < currentNode.f)) {//this is the debugLevel for when the planner is run in optimality modality
            bestf = currentNode.gValue + hValueInCurrentNode;
            out.println("f(n) = " + currentNode.f + " (Expanded Nodes: " + nodesExpanded
                    + ", Evaluated States: " + nodesEvaluated + ", Time: " +
                    (float) ((System.currentTimeMillis() - timeAtStart)) / 1000.0 + ")"+
                    " Frontier Size: "+size(frontier));
        }else if (!optimality && hAtInit > (hValueInCurrentNode)) {
            out.println(" g(n)= " + currentNode.gValue + " h(n)=" + (hValueInCurrentNode));
            hAtInit = hValueInCurrentNode;
        }
        return bestf;
    }

    private int size(Object frontier) {
        if (frontier instanceof Queue) {
            return ((Queue) frontier).size();
        } else if (frontier instanceof ObjectHeapPriorityQueue) {
            return ((ObjectHeapPriorityQueue) frontier).size();
        } else if (frontier instanceof BucketPriorityQueue){
            return ((BucketPriorityQueue) frontier).size();
        }
        return -1;
    }
}