/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import conditions.Allocator;
import conditions.AndCond;
import conditions.Assign;
import conditions.Comparator;
import conditions.Conditions;
import conditions.NotCond;
import conditions.OrCond;
import conditions.Predicate;
import conditions.Term;

import expressions.BinaryOp;
import expressions.Expression;
import expressions.Function;
import expressions.Number;

import expressions.MinusUnary;
import expressions.MultiOp;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

import parser.PddlLexer;
import parser.PddlParser;

import problem.PddlProblem;

/**
 *
 * @author enrico
 */
public class PddlDomain extends Object {

    String name;
    private Set ActionsSchema;
    private PredicateSet predicates;
    private HashSet types;
    private List functions;
    private List DurativeActions;
    private List Requirements;

    private PddlDomain(Set ActionsSchema, PredicateSet Predicates, HashSet types, List Functions, List DurativeActions, List Requirements) {
        this.ActionsSchema = ActionsSchema;
        this.predicates = Predicates;
        this.types = types;
        this.functions = Functions;
        this.DurativeActions = DurativeActions;
        this.Requirements = Requirements;
    }

    /**
     * 
     */
    public PddlDomain() {

        types = new HashSet();
        ActionsSchema = new HashSet();
        functions = new ArrayList();
        Requirements = new ArrayList();
    }

    /**
     * 
     * @param p - The PddlProblem to validate the consistency for. BETA
     * @return true whether the problem is consistent wrt to the domain. Otherwise false
     */
    public boolean validate(PddlProblem p) {

        for (Object o : p.getProblemObjects()) {
            Term t = (Term) o;
            if (!types.contains(t.getType())) {
                System.out.println("Oggetto non valido:" + t);
                System.exit(-1);
            }
        }

        for (Object o : p.getInit()) {
            if (o instanceof Assign) {
            } else {
                Predicate t = (Predicate) o;
                if (!predicates.validateInst(t)) {
                    System.out.println("Predicato: " + t + " non valido");
                    System.exit(-1);

                }
            }
        }
        for (Object o : p.getGoals().son) {

            if (o instanceof Predicate) {

                Predicate t = (Predicate) o;
                if (!predicates.validateInst(t)) {
                    System.out.println("Predicato: " + t + " non valido");
                    System.exit(-1);

                }
            }
        }

        return true;
    }

    /**
     * 
     * @param file - the path of the pddl file representing the domain. As return the object will be fullfilled with the information in the pddl domain file
     * @throws IOException
     * @throws RecognitionException
     */
    public void parseDomain(String file) throws IOException, RecognitionException {
        ANTLRInputStream in;
        in = new ANTLRInputStream(new FileInputStream(file));
        PddlLexer lexer = new PddlLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        PddlParser parser = new PddlParser(tokens);
        PddlParser.pddlDoc_return root = parser.pddlDoc();

        if (parser.invalidGrammar()) {
            System.out.println("Errore di qualche genere");
        }


        CommonTree t = (CommonTree) root.getTree();
        int i;
        for (i = 0; i < t.getChildCount(); i++) {
            Tree c = (Tree) t.getChild(i);
            int type = c.getType();
            switch (type) {
                case PddlParser.DOMAIN_NAME:
                    name = c.getChild(0).getText();
                    break;
                case PddlParser.TYPES:
                    addTypes(c);
                    break;
                case PddlParser.PREDICATES:
                    //addPredicates(c);
                    this.predicates = (PredicateSet) addPredicates(c);
                    break;
                case PddlParser.ACTION:
                    addActions(c);
                    break;
                case PddlParser.REQUIREMENTS:
                    addRequirements(c);
                    break;
                case PddlParser.FUNCTIONS:
                    addFunctions(c);
                    break;
            }
        }

    }

    /**
     * A pretty representation of the domain
     */
    public void prettyPrint() {
        System.out.println("requisiti: " + this.Requirements);
        System.out.println("azioni:" + this.ActionsSchema);

    }

    /**
     * @return the ActionsSchema- a Set which contains all the action schema of the domain
     */
    public Set getActionsSchema() {
        return ActionsSchema;
    }

    /**
     * @param ActionsSchema the ActionsSchema to set
     */
    private void setActionsSchema(Set ActionsSchema) {
        this.ActionsSchema = ActionsSchema;
    }

    /**
     * @return the types declared in the domain
     */
    public HashSet getTypes() {
        return types;
    }

    /**
     * @param types the types to set
     */
    private void setTypes(HashSet Types) {
        this.types = Types;
    }

    /**
     * @return the functions declared in the domain
     */
    public List getFunctions() {
        return functions;
    }

    /**
     * @param functions the functions to set
     */
    private void setFunctions(List Functions) {
        this.functions = Functions;
    }

    /**
     * @return the DurativeActions
     */
    private List getDurativeActions() {
        return DurativeActions;
    }

    /**
     * @param DurativeActions the DurativeActions to set
     */
    private void setDurativeActions(List DurativeActions) {
        this.DurativeActions = DurativeActions;
    }

    /**
     * @return the Requirements declared in the domain
     */
    public List getRequirements() {
        return Requirements;
    }

    /**
     * @param Requirements the Requirements to set
     */
    private void setRequirements(List Requirements) {
        this.Requirements = Requirements;
    }

    /**
     * @return the predicates definitions
     */
    public PredicateSet getPredicates() {
        return predicates;
    }

    /**
     * @param predicates the predicates to set
     */
    private void setPredicates(PredicateSet Predicates) {
        this.predicates = Predicates;
    }

    //da migliorare perchè dovrebbe rappresentare una potenziale gerarchica di oggetti!!!
    private void addTypes(Tree c) {
        for (int i = 0; i < c.getChildCount(); i++) {
            Type tip= new Type(c.getChild(i).getText());
            
            Tree tipo = (Tree)c.getChild(i);
            if (tipo.getChildCount()>0){
                boolean subTypeOfExist = false;
                Type father = new Type( tipo.getChild(0).getText());
                if (father.isObject()){
                    if (!(types.contains(father)))
                        types.add(father);
                    tip.setSubTypeOf(father);
                    subTypeOfExist = true;
                }else{
                    Iterator it = types.iterator();
                    while(it.hasNext()){
                        Type ele = (Type)it.next();
                        if (ele.equals(father)){
                            tip.setSubTypeOf(ele);
                            subTypeOfExist = true;
                            break;
                        }
                    }
                }
                if (subTypeOfExist){
                    this.types.add(tip);        
                }else{
                    System.out.println("Error: " + tip + " has declared father: " + father + " which is not a type neither object; inferring object" );
                    tip.setSubTypeOf(new Type("object"));
                    this.types.add(tip);        
                }
            }else
                this.types.add(tip);        
//                
//            System.out.println("tipo di cazzo:" + tipo.toString());
//            System.out.println("numero di figli" + tipo.getChildCount());
//            
//            this.types.add(tip);
            
        }
    }

    private void addActions(Tree c) {

        ActionSchema a = new ActionSchema();
        Tree action = (Tree) c.getChild(0);
        a.setName(action.getText());

        this.ActionsSchema.add(a);

        for (int i = 1; i < c.getChildCount(); i++) {
            Tree infoAction = (Tree) c.getChild(i);
            int type = infoAction.getType();

            switch (type) {
                case (PddlParser.PRECONDITION):
                    Conditions con = createPreconditions(infoAction.getChild(0), a.getParameters());
                    a.setPreconditions(con);
                    break;
                case (PddlParser.VARIABLE):
                    Type t = new Type(infoAction.getChild(0).getText());

                    if (types.contains(t)) {
                        a.addParameter(new Variable(infoAction.getText(), t));
                    }
                    break;
                case (PddlParser.EFFECT):
                    Conditions add = createAddEffect(a.getParameters(), infoAction.getChild(0));
                    Conditions del = createDelEffect(a.getParameters(), infoAction.getChild(0));
                    Conditions numericEffect = createNumericEffect(a.getParameters(), infoAction.getChild(0));
                    //a.addParameter(new Variable(infoAction.getText(),new types(infoAction.getChild(0).getText())));
                    a.setAddList(add);
                    a.setDelList(del);
                    a.setNumeric(numericEffect);
                    break;
            }

        }
    }

    private Conditions createPreconditions(Tree infoAction, ActionParameters parTable) {
        Conditions ret = null;
        if (infoAction == null) {
            return null;
        }
        if (infoAction.getType() == PddlParser.PRED_HEAD) {
            //estrapola tutti i predicati e ritornali come set di predicati
            return buildPredicate(infoAction, parTable);
        } else if (infoAction.getType() == PddlParser.AND_GD) {
            AndCond and = new AndCond();
            for (int i = 0; i < infoAction.getChildCount(); i++) {
                Conditions ret_val = createPreconditions(infoAction.getChild(i), parTable);
                if (ret_val != null) {
                    and.addConditions(ret_val);
                }
            }
            return and;
            //Crea un and e per ogni figlio di questo nodo invoca creaformula
            //gestendo il valore di ritorno come un attributo di and
        } else if (infoAction.getType() == PddlParser.OR_GD) {
            OrCond or = new OrCond();
            for (int i = 0; i < infoAction.getChildCount(); i++) {
                Conditions ret_val = createPreconditions(infoAction.getChild(i), parTable);
                if (ret_val != null) {
                    or.addConditions(ret_val);
                }
            }
            return or;
        } else if (infoAction.getType() == PddlParser.NOT_GD) {
            NotCond not = new NotCond();
            for (int i = 0; i < infoAction.getChildCount(); i++) {
                Conditions ret_val = createPreconditions(infoAction.getChild(i), parTable);
                if (ret_val != null) {
                    not.addConditions(ret_val);
                }
            }
            return not;
            //Crea un not e per ogni figlio di questo nodo invoca creaformula
            //gestendo il valore di ritorno come un attributo di not
        } else if (infoAction.getType() == PddlParser.COMPARISON_GD) {
            System.out.println("Comparison:" + infoAction.getText());

            Comparator c = new Comparator(infoAction.getChild(0).getText());

            c.setFirst(createExpression(infoAction.getChild(1)));
            c.setTwo(createExpression(infoAction.getChild(2)));
            return c;
            //Crea un not e per ogni figlio di questo nodo invoca creaformula
            //gestendo il valore di ritorno come un attributo di not
        }
        return null;
    }

    private Conditions createAddEffect(ActionParameters parTable, Tree infoAction) {
        Conditions ret = null;
        if (infoAction == null) {
            return new Predicate();
        }
        if (infoAction.getType() == PddlParser.PRED_HEAD) {
            //estrapola tutti i predicati e ritornali come set di predicati
            return buildPredicate(infoAction, parTable);
        } else if (infoAction.getType() == PddlParser.AND_EFFECT) {
            AndCond and = new AndCond();
            for (int i = 0; i < infoAction.getChildCount(); i++) {
                Conditions ret_val = createAddEffect(parTable, infoAction.getChild(i));
                if (ret_val != null) {
                    and.addConditions(ret_val);
                }
            }
            return and;
            //Crea un and e per ogni figlio di questo nodo invoca creaformula
            //gestendo il valore di ritorno come un attributo di and
        }
        return null;
    }

    private Predicate buildPredicate(Tree t, ActionParameters parTable) {
        Predicate a = new Predicate();
        a.setPredicateName(t.getChild(0).getText());
        //controllare che la variabile nei predicati sia effettivamente un parametro dell'azione!
        for (int i = 1; i < t.getChildCount(); i++) {
            Variable v = new Variable(t.getChild(i).getText());
            Variable v1 = parTable.containsVariable(v);
            if (v1 != null) {
                a.addVariable(v1);
            } else {
                System.out.println("La variabile " + v + " non è presente nei parametri");
                System.exit(-1);
            }
        }
        if (!predicates.validate(a)) {
            System.out.println("Predicato " + a + " non valido");
            System.exit(-1);
        }
        return a;
    }

    private Conditions createDelEffect(ActionParameters parTable, Tree infoAction) {
        if (infoAction == null) {
            return null;
        }
        if (infoAction.getType() == PddlParser.PRED_HEAD) {
            //estrapola tutti i predicati e ritornali come set di predicati

            if (infoAction.getParent() != null) { //non dovrebbe mai succedere....?
                if (infoAction.getParent().getType() == PddlParser.NOT_EFFECT) {//solo se mio padre è un not altrimenti non aggiungo niente
                    return buildPredicate(infoAction, parTable);
                }
            } else {
                System.out.println(infoAction + " é un predicato senza padre; da controllare comunque potrebbe essere possibile");
            }
//            for (int i=1; i<infoAction.getChildCount();i++){
//                a.addVariable(add(new Variable(infoAction.getChild(i)));
//            }
        } else if (infoAction.getType() == PddlParser.AND_EFFECT) {
            AndCond and = new AndCond();
            for (int i = 0; i < infoAction.getChildCount(); i++) {
                Conditions ret_val = createDelEffect(parTable, infoAction.getChild(i));
                if (ret_val != null) {
                    and.addConditions(ret_val);
                }
            }
            if (!and.son.isEmpty()) {
                return and;
            } else {
                return null;
            }
            //Crea un or e per ogni figlio di questo nodo invoca creaformula
            //gestendo il valore di ritorno come un attributo di or
        } else if (infoAction.getType() == PddlParser.NOT_EFFECT) {
            NotCond not = new NotCond();
            for (int i = 0; i < infoAction.getChildCount(); i++) {
                not.addConditions(createDelEffect(parTable, infoAction.getChild(i)));
            }
            return not;
            //Crea un or e per ogni figlio di questo nodo invoca creaformula
            //gestendo il valore di ritorno come un attributo di or
        }
        return null;
    }

    private Object addPredicates(Tree t) {
        PredicateSet col = new PredicateSet();
        if (t == null) {
            return null;
        } //Assumo che ogni variabile sia tipata: da aggiungere nella grammatica l'impossibilità di avere variabili non tipate
        //in realtà anche per pddl non tipati funziona. Il risultato ritornato è un null che è comunque un risultato accettabile come tipo.
        if (t.getChildCount() == 0) {
            Type unTipo = new Type(t.getText());
            if (types.contains(unTipo)) {
                return unTipo;
            } else {
                return null;
            }
        }
        if (t.getType() == PddlParser.PREDICATES) {//Sono uno dei predicati
            for (int i = 0; i < t.getChildCount(); i++) {
                Tree child = t.getChild(i);
                Predicate p = new Predicate();
                p.setPredicateName(child.getText());
                Variable v;
                for (int j = 0; j < child.getChildCount(); j++) {
                    v = (Variable) addPredicates(child.getChild(j));
                    p.addVariable(v);
                }
                col.add(p);
            }
            return col;
        } else {
            Variable v = new Variable(t.getText());

            v.setType((Type) addPredicates(t.getChild(0)));
            return v;
        }
    }

    private void exploreTree(Tree t) {
        if (t == null) {
            return;
        }
        if (t.getChildCount() == 0) {
            System.out.println("Foglia:" + t.getText() + "Tipo:" + t.getType());
            return;
        }
        System.out.println("Nodo intermedio: " + t.getText() + "Tipo:" + t.getType());
        for (int i = 0; i < t.getChildCount(); i++) {
            exploreTree(t.getChild(i));
        }
        return;
    }

    private Conditions createNumericEffect(ActionParameters parameters, Tree child) {

        if (child.getType() == PddlParser.AND_EFFECT) {
            AndCond and = new AndCond();
            for (int i = 0; i < child.getChildCount(); i++) {
                Conditions ret_val = createNumericEffect(parameters, child.getChild(i));
                if (ret_val != null) {
                    and.addConditions(ret_val);
                }
            }
            return and;
            //Crea un and e per ogni figlio di questo nodo invoca creaformula
            //gestendo il valore di ritorno come un attributo di and
        } else if (child.getType() == PddlParser.ASSIGN_EFFECT) {
            Allocator a = new Allocator(child.getChild(0).getText());
            a.setOne((Function)createExpression(child.getChild(1)));
            a.setTwo((Expression)createExpression(child.getChild(2)));
            return a;


        }
        return null;
    }

    private void addRequirements(Tree c) {
        if (c != null) {
            System.out.println(c.getText());
            for (int i = 0; i < c.getChildCount(); i++) {
                this.Requirements.add(c.getChild(i).getText());
            }
        }
    }

    private void addFunctions(Tree c) {
        if (c != null) {
            for (int i = 0; i < c.getChildCount(); i++) {
                
                System.out.println(c.getChild(i).getText());
                Function ret = new Function(c.getChild(i).getText());
                Tree t = c.getChild(i);
               for (int j = 0; j < t.getChildCount(); j++) {
                    ret.addVariable(new Variable(t.getChild(j).getText()));
                }
                this.functions.add(ret);
            }
        }
    }
    
    //
    /**
     * 
     * @param name - the name of the action
     * @return an ActionSchema object (if any) with the name in input
     * this assumes that there is a 1:1 relation between action and name, i.e. we cannot have different actions with the same name
     */
    public ActionSchema getActionByName(String name){
        ActionSchema ret = null;
        Iterator it = this.ActionsSchema.iterator();
        
        while(it.hasNext()){
            ActionSchema el = (ActionSchema)it.next();
            if (el.getName().equalsIgnoreCase(name))
                return el;
        }
        return ret;
    }
    private Expression createExpression(Tree t) {

        if (t.getType() == PddlParser.BINARY_OP) {
            BinaryOp ret = new BinaryOp();
            ret.setOperator(t.getChild(0).getText());
            ret.setOne(createExpression(t.getChild(1)));
            ret.setTwo(createExpression(t.getChild(2)));

            return ret;
        } else if (t.getType() == PddlParser.NUMBER) {
            Number ret = new Number(new Float(t.getText()));
            return ret;
        } else if (t.getType() == PddlParser.FUNC_HEAD) {
            Function ret = new Function(t.getChild(0).getText());
            for (int i = 1; i < t.getChildCount(); i++) {
                ret.addVariable(new Variable(t.getChild(i).getText()));
            }
            return ret;
        } else if (t.getType() == PddlParser.UNARY_MINUS) {
            return new MinusUnary(createExpression(t.getChild(0)));
        } else if (t.getType() == PddlParser.MULTI_OP) {
            MultiOp ret = new MultiOp(t.getChild(0).getText());
            for (int i=1; i<t.getChildCount();i++){
                //System.out.println("Figlio di + o * " + createExpression(t.getChild(i)));
                ret.addExpression(createExpression(t.getChild(i)));
            }
            return ret;
        }

        return null;

    }
}
