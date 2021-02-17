/*
 * Copyright (C) 2010-2017 Enrico Scala. Contact: enricos83@gmail.com.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package com.hstairs.ppmajal.domain;

import com.google.common.collect.Sets;
import com.hstairs.ppmajal.conditions.*;
import com.hstairs.ppmajal.expressions.NumFluent;
import com.hstairs.ppmajal.extraUtils.Utils;
import com.hstairs.ppmajal.parser.PddlLexer;
import com.hstairs.ppmajal.parser.PddlParser;
import com.hstairs.ppmajal.problem.PDDLObjects;
import com.hstairs.ppmajal.problem.PddlProblem;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author enrico
 */
public final class PddlDomain extends Object {

    HashMap<String,Boolean> canBeDynamic;

    public final Collection<EventSchema> eventsSchema;
    public final PDDLObjects constants;
    private final Set<ProcessSchema> ProcessesSchema;
    private final Collection<NumFluent> derived_variables;
    public Set<Type> types;
    public Collection functions;
    public Collection<String> requirements;
    protected Collection<ActionSchema> ActionsSchema;
    private String name;
    private PredicateSet predicates;
    private String pddlReferenceFile;
    private LinkedHashSet<SchemaGlobalConstraint> SchemaGlobalConstraints;
    private FactoryConditions fc;

    public PddlDomain (String domainFile) {
        super();
        SchemaGlobalConstraints = new LinkedHashSet();

        types = new LinkedHashSet<>();

        //Add default dummy type object
        Type t = Type.createType("object","null");
        types.add(t);

        ActionsSchema = new TreeSet<>(new ActionComparator());
        functions = new ArrayList();
        derived_variables = new ArrayList();
        requirements = new ArrayList<>();
        constants = new PDDLObjects();
        ProcessesSchema = new LinkedHashSet();
        eventsSchema = new LinkedHashSet();
        this.parseDomain(domainFile);
    }


    public PddlDomain (Collection<EventSchema> eventsSchema, PDDLObjects constants, Set<ProcessSchema> processesSchema, Collection<NumFluent> derived_variables) {
        this.eventsSchema = eventsSchema;
        this.constants = constants;
        ProcessesSchema = processesSchema;
        this.derived_variables = derived_variables;
    }


    /**
     * @param file - the path of the pddl file representing the domain. As
     *             return the object will be fullfilled with the information in the pddl
     *             domain file
     * @throws IOException
     * @throws RecognitionException
     */
    public void parseDomain (String file) {
        try {
            this.setPddlFilRef(file);
            ANTLRInputStream in;
            in = new ANTLRInputStream(new FileInputStream(file));
            PddlLexer lexer = new PddlLexer(in);
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            PddlParser parser = new PddlParser(tokens);
            PddlParser.pddlDoc_return root = parser.pddlDoc();

            if (parser.invalidGrammar()) {
                System.out.println("Some Syntax Error");
                System.exit(-1);
            }

//        fc = new FactoryConditions(this.predicates, (LinkedHashSet<Type>) this.types,this.constants);
            CommonTree t = (CommonTree) root.getTree();
            int i;
//            System.out.println(this.constants);
            fc = new FactoryConditions(this.predicates, (LinkedHashSet<Type>) this.types, this.constants);
            for (i = 0; i < t.getChildCount(); i++) {
                Tree c = t.getChild(i);
                int type = c.getType();
//            System.out.println("Tipo: " + c.getText());
                switch (type) {
                    case PddlParser.DOMAIN_NAME:
                        setName(c.getChild(0).getText());
                        break;
                    case PddlParser.TYPES:
                        addTypes(c);
                        break;
                    case PddlParser.PREDICATES:
                        this.predicates = (PredicateSet) addPredicates(c);
                        fc = new FactoryConditions(this.predicates, (LinkedHashSet<Type>) this.types, this.constants);

                        break;
                    case PddlParser.ACTION:
                        //fc = new FactoryConditions(this.predicates, (LinkedHashSet<Type>) this.types, this.constants);
//                        System.out.println(fc.constants);
                        addActions(c);
                        break;
                    case PddlParser.EVENT:
                        addEvent(c);
                        break;
                    case PddlParser.REQUIREMENTS:
                        addRequirements(c);
                        break;
                    case PddlParser.FUNCTIONS:
                        this.functions.addAll(fc.addFunctions(c));
                        break;
                    case PddlParser.CONSTANTS:
                        addConstants(c);
                        fc.constants = this.constants;
                        break;
                    case PddlParser.FREE_FUNCTIONS:
                        this.derived_variables.addAll(fc.addFunctions(c));
                        break;
                    case PddlParser.GLOBAL_CONSTRAINT:
                        fc = new FactoryConditions(this.predicates, (LinkedHashSet<Type>) this.types, this.constants);
                        addGlobalConstraint(c);
                        break;
                    case PddlParser.PROCESS:
                        addProcess(c);
                        break;
//                case PddlParser.DOM_CONSTRAINTS:
//                    addGlobalConstraints(c);
//                    break;
                }
            }
            push_not_at_the_terminals();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PddlDomain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PddlDomain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RecognitionException ex) {
            Logger.getLogger(PddlDomain.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * A pretty representation of the domain
     */
    public void prettyPrint ( ) {
        System.out.println("Requirements: " + this.requirements);
        System.out.println("Actions Domain: " + this.ActionsSchema);
        if (this.ProcessesSchema != null) {
            System.out.println("Process Domain: " + this.ProcessesSchema);
        }
        //if (!this.eventsSchema.isEmpty())
        System.out.println("Events Domain: " + this.eventsSchema);
        System.out.println("Predicates: " + this.predicates);
        System.out.println("Functions: " + this.functions);
        System.out.println("Global Constraints: " + this.getSchemaGlobalConstraints());

    }

    /**
     * @return the ActionsSchema- a Set which contains all the action schema of
     * the domain
     */
    public Collection<ActionSchema> getActionsSchema ( ) {
        return ActionsSchema;
    }

    /**
     * @return the types declared in the domain
     */
    public Set<Type> getTypes ( ) {
        return types;
    }

    /**
     * @return the predicates definitions
     */
    public PredicateSet getPredicates ( ) {
        return predicates;
    }

    //da migliorare perchè dovrebbe rappresentare una potenziale gerarchica di oggetti!!!
    private void addTypes (Tree c) {
        for (int i = 0; i < c.getChildCount(); i++) {

            Tree tipo = c.getChild(i);
            String father = "object";
            if (tipo.getChildCount() > 0) {
                father = tipo.getChild(0).getText();               
            }
            Type tip = Type.createType(c.getChild(i).getText(),father);
            this.types.add(tip);
        }
    }

    private void addActions (Tree c) {
        this.addGenericActionSchemas(c, new ActionSchema());
    }

    private Object addPredicates (Tree t) {
        PredicateSet col = new PredicateSet();
        if (t == null) {
            return null;
        }
        if (t.getType() == PddlParser.PREDICATES) {//Sono uno dei predicati
            for (int i = 0; i < t.getChildCount(); i++) {
                Tree child = t.getChild(i);
                String name = child.getText();
                ArrayList variables = new ArrayList();
                Variable v;
                for (int j = 0; j < child.getChildCount(); j++) {
                    v = (Variable) addPredicates(child.getChild(j));
                    variables.add(v);
                }
                col.add(Predicate.createPredicate(name, variables));
            }
            return col;
        } else {
            String father = "object";
            if (t.getChildCount() > 0) {
                father = t.getChild(0).getText();
            }
            Variable v = new Variable(t.getText(),Type.createType(father));
            return v;
        }
    }

    private void addRequirements (Tree c) {
        if (c != null) {
            //System.out.println(c.getText());
            for (int i = 0; i < c.getChildCount(); i++) {
                final String req = c.getChild(i).getText();
                this.requirements.add(req);
            }
        }
    }


    //

    /**
     * Returns the action with specified name. Notice that this method is rather
     * inefficient if there are many actions, and that a table that maps names
     * to action schemas could be more effective.
     *
     * @param name the name of the action
     * @return an ActionSchema object (if any) with the name in input this
     * assumes that there is a 1:1 relation between action and name, i.e. we
     * cannot have different actions with the same name
     */
    public ActionSchema getActionByName (String name) {
        for (final ActionSchema el : ActionsSchema) {
            final String elname = el.getName();
            if (elname.equalsIgnoreCase(name)) {
                return el;
            }
        }

        return null;
    }

    /**
     * @return the pddlFilRef
     */
    public String getPddlFilRef ( ) {
        return pddlReferenceFile;
    }

    /**
     * @param pddlFilRef the pddlFilRef to set
     */
    public void setPddlFilRef (String pddlFilRef) {
        this.pddlReferenceFile = pddlFilRef;
    }

    private void addConstants (Tree c) {

        for (int i = 0; i < c.getChildCount(); i++) {
            if (c.getChild(i).getChildCount() > 0) {
                PDDLObject o = PDDLObject.createObject(c.getChild(i).getText(), Type.createType(c.getChild(i).getChild(0).getText()));
                this.getConstants().add(o);
            } else {
                PDDLObject o = PDDLObject.createObject(c.getChild(i).getText(), Type.createType("object"));
                this.getConstants().add(o);
            }
//            System.out.println("Aggiungo l'oggetto:" + c.getChild(i).getText());
//            System.out.println("che è di tipo:" + new Type(c.getChild(i).getChild(0).getText()));
        }

    }

    /**
     * @return the constants
     */
    public PDDLObjects getConstants ( ) {
        return constants;
    }

    public void saveDomain (String file) throws IOException {
        PddlDomain domain = this;
        Writer f;

        f = new BufferedWriter(new FileWriter(file));
        ParametersAsTerms constants = new ParametersAsTerms();
        String actions = "\n";

        f.write("(define (domain " + domain.getName() + ")\n");
        if (this.requirements != null) {
            f.write("(:requirements " + Utils.toPDDLSet(requirements) + ")\n");
        }
        if (domain.getTypes() != null) {
            f.write("(:types " + Utils.toPDDLTypesSet(domain.getTypes()) + ")\n");
        }
        if (this.constants != null && !this.constants.isEmpty()) {
            f.write(this.constants.pddlPrint("constants") + "\n");
        }
        if (!domain.getPredicates().isEmpty()) // f.write("(:constants "+constants.pddlPrint()+")\n");
        {
            f.write("(:predicates " + domain.getPredicates().pddlPrint(true) + "\n");
        }
        if (!functions.isEmpty()) {
            f.write("(:functions " + Utils.toPDDLSet(functions) + ")\n");
        }

        if (!domain.getActionsSchema().isEmpty()) {
            f.write(Utils.toPDDLSetWithBreak(domain.getActionsSchema()));
        }

        f.write(actions);
        f.write("\n)");
        f.close();
        f.close();
    }

    /**
     * @return the name
     */
    public String getName ( ) {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName (String name) {
        this.name = name;
    }

    public HashMap<String,Boolean> getDynamicPredicateMap ( ) {
       
        if (canBeDynamic == null) {
            canBeDynamic = new HashMap();
            ArrayList<PDDLGenericAction> all = new ArrayList();
            all.addAll(this.ActionsSchema);
            all.addAll(this.ProcessesSchema);
            all.addAll(this.eventsSchema);
            for (int i = 0; i < all.size(); i++) {
                Set<Condition> terminalConditions = all.get(i).getPreconditions().getTerminalConditions();
                for (int j = 0; j < all.size(); j++) {
                    PDDLGenericAction get = all.get(j);
                    for (Condition cond : terminalConditions) {
                        Terminal p = null;
                        if (cond instanceof NotCond) {
                            p = (Terminal) ((NotCond) cond).getSon();
                        } else if ((cond instanceof Predicate) || (cond instanceof Comparison)) {
                            p = (Terminal) cond;
                        }
                        if (p != null && p instanceof Predicate) {
                            for (Predicate pred : get.getPropositionAffected()) {
                                if (pred.getPredicateName().equals(((Predicate) p).getPredicateName())) {
                                    canBeDynamic.put(((Predicate) p).getPredicateName(), true);
                                }
                            }
                        }
//                        if (p != null && p instanceof Comparison) {
//                            for (NumFluent nf : get.getNumericFluentAffected()) {
//                                for (NumFluent nf2 : p.getInvolvedFluents()) {
//                                    if (nf.getName().equals(nf2.getName())) {
//                                        canBeDynamic.put(p, true);
//                                    }
//                                }
//                            }
//                        }
                    }
                }
            }
        }

        return canBeDynamic;
    }

    public Type getTypeByName(String text) {
        for (Type t : this.getTypes()) {
            if (t.getName().equals(text)) {
                return t;
            }
        }
        return null;
    }

    public HashMap<Object, Boolean> generateInvariant() {
        HashMap<Object, Boolean> ret = new HashMap();
        for (ActionSchema as : this.getActionsSchema()) {
            Condition prop_effects = as.getAddList();
            if (prop_effects instanceof AndCond) {
                AndCond ac = (AndCond) prop_effects;
                for (Object o : ac.sons) {
                    if (o instanceof Predicate) {
                        Predicate p = (Predicate) o;
                        Predicate pDef = this.getPredicates().findAssociated(p);
                        ret.put(pDef, Boolean.FALSE);
                    }
                }
            } else {
                System.out.println("Support only and cond as prop effects. In case of singleton, please put it under AND");
            }
            prop_effects = as.getDelList();
            if (prop_effects != null) {
                if (prop_effects instanceof AndCond) {
                    AndCond ac = (AndCond) prop_effects;
                    for (Object o : ac.sons) {
                        if (o instanceof NotCond) {
                            NotCond nc = (NotCond) o;
                            //System.out.println(nc);
//                            for (Object o1 : nc.son) {
                            Predicate p = (Predicate) nc.getSon();
                            Predicate pDef = this.getPredicates().findAssociated(p);
                            ret.put(pDef, Boolean.FALSE);
//                            }

                        }
                    }
                } else {
                    System.out.println("Support only AND as prop effects. In case of singleton, please put it under AND also if it is just one proposition");
                }
            }
            Set<NumFluent> anfa = (Set<NumFluent>) as.getAbstractNumericFluentAffected();
            for (NumFluent nf : anfa) {
                ret.put(nf, Boolean.FALSE);
            }
        }
        return ret;
    }


    /**
     * @return the free_functions
     */
    public Collection<NumFluent> get_derived_variables ( ) {
        return derived_variables;
    }


    private void addGlobalConstraints (Tree c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }




    private void addGlobalConstraint (Tree c) {
        SchemaGlobalConstraint con = new SchemaGlobalConstraint(c.getChild(0).getText());
        //System.out.println("Adding:"+a.getName());
        this.getSchemaGlobalConstraints().add(con);

        for (int i = 1; i < c.getChildCount(); i++) {
            Tree infoConstraint = c.getChild(i);
            int type = infoConstraint.getType();

            switch (type) {
                case (PddlParser.PRECONDITION):

                    Condition condition = fc.createCondition(infoConstraint.getChild(0), con.parameters);
                    if ((condition instanceof Comparison) || (condition instanceof Predicate)) {
                        AndCond and = new AndCond();
                        and.addConditions(condition);
                        con.condition = condition;
                    } else {
                        con.condition = condition;
                    }
                    break;
                case (PddlParser.VARIABLE):
                    if (infoConstraint.getChild(0) == null) {
                        break;
                    }
                    Type t = Type.createType(infoConstraint.getChild(0).getText());
                    con.parameters.add(new Variable(infoConstraint.getText(), t));
                    break;

            }

        }
    }

    /**
     * @return the SchemaGlobalConstraints
     */
    public LinkedHashSet<SchemaGlobalConstraint> getSchemaGlobalConstraints ( ) {
        return SchemaGlobalConstraints;
    }

    /**
     * @param SchemaGlobalConstraints the SchemaGlobalConstraints to set
     */
    public void setSchemaGlobalConstraints (LinkedHashSet SchemaGlobalConstraints) {
        this.SchemaGlobalConstraints = SchemaGlobalConstraints;
    }

    private void addProcess (Tree c) {
        ProcessSchema a = new ProcessSchema();
        Tree process = c.getChild(0);
        a.setName(process.getText());
//        System.out.println("DEBUG: Adding:"+a.getName());
        this.ProcessesSchema.add(a);

        for (int i = 1; i < c.getChildCount(); i++) {
            Tree infoAction = c.getChild(i);
            int type = infoAction.getType();
            switch (type) {
                case (PddlParser.PRECONDITION):
                    Condition con = fc.createCondition(infoAction.getChild(0), a.getParameters());
                    if ((con instanceof Comparison) || (con instanceof Predicate)) {
                        AndCond and = new AndCond();
                        and.addConditions(con);
                        a.setPreconditions(and);
                    } else if (con != null) {
                        a.setPreconditions((ComplexCondition) con);
                    }
                    break;
                case (PddlParser.VARIABLE):
                    if (infoAction.getChild(0) == null) {
                        break;
                    }
                    Type t = Type.createType(infoAction.getChild(0).getText());
                    
                        Variable variable = new Variable(infoAction.getText(), t);
//                        System.out.print(variable);
                        a.addParameter(variable);
                    break;
                case (PddlParser.EFFECT):
                    addEffects(a, infoAction);
//                    System.out.println(a);
                    break;

            }

        }
    }

    /**
     * @return the ProcessesSchema
     */
    public Set<ProcessSchema> getProcessesSchema ( ) {
        return ProcessesSchema;
    }


    //    private Object createConditionalEffect(SchemaParameters parameters, Tree t) {
//        
//        if (t.getType() == PddlParser.AND_EFFECT){
//            for (int j = 0; j < t.getChildCount(); j++) {
//                    if (t.getChild(j).getType() == PddlParser.WHEN_EFFECT){
//                        Tree child = t.getChild(j);
//                        System.out.println(t.getChild(j).toStringTree());
//                    }
//                }
//        }
//        return null;
//    }
    private void addEffects (PDDLGenericAction a, Tree infoAction) {
        //PostCondition res = createPostCondition(a.parameters, infoAction.getChild(0));
        PostCondition res = fc.createPostCondition(a.parameters, infoAction.getChild(0));
        a.create_effects_by_cases(res);
    }

    public void saveDomainWithInterpretationObjects (String file) throws IOException {
        PddlDomain domain = this;
        Writer f;

        f = new BufferedWriter(new FileWriter(file));
        ParametersAsTerms constants = new ParametersAsTerms();

        f.write("(define (domain " + domain.getName() + ")\n");
        if (requirements != null && !requirements.isEmpty()) {
            f.write(";(:requirements " + Utils.toPDDLSet(requirements) + ")\n");
        }
        if (domain.getTypes() != null && !domain.getTypes().isEmpty()) {
            f.write("(:types " + Utils.toPDDLTypesSet(domain.getTypes()) + ")\n");
        }
        if (this.constants != null && !this.constants.isEmpty()) {
            f.write(this.constants.pddlPrint("constants") + "\n");
        }
        if (!domain.getPredicates().isEmpty()) // f.write("(:constants "+constants.pddlPrint()+")\n");
        {
            f.write("(:predicates (true) " + domain.getPredicates().pddlPrintWithExtraObject(true) + " \n");
        }

        if (!domain.getActionsSchema().isEmpty()) {
            f.write(Utils.toPDDLWithExtraObject(domain.getActionsSchema()));
        }

        f.write("\n)");
        f.close();
        f.close();
    }

    private void push_not_at_the_terminals ( ) {
        for (ActionSchema a : this.ActionsSchema) {
            a.push_not_to_terminals();
        }

        for (ProcessSchema a : this.ProcessesSchema) {
            a.push_not_to_terminals();
        }
        for (EventSchema a : this.eventsSchema) {
            a.push_not_to_terminals();
        }

    }

    private void addEvent (Tree c) {
        this.addGenericActionSchemas(c, new EventSchema());
    }

    private void addGenericActionSchemas (Tree c, ActionSchema a) {

        Tree action = c.getChild(0);
        a.setName(action.getText());
        //System.out.println("Adding:"+a.getName());
        if (a instanceof EventSchema) {
            this.eventsSchema.add((EventSchema) a);

        } else if (a instanceof ActionSchema) {
            this.ActionsSchema.add(a);
        }
        for (int i = 1; i < c.getChildCount(); i++) {
            Tree infoAction = c.getChild(i);
            int type = infoAction.getType();
//            System.out.println(type);
//            System.out.println(infoAction);
            switch (type) {
                case (PddlParser.PRECONDITION):

                    Condition con = null;
                    if (a.parameters == null)
                        con = fc.createCondition(infoAction.getChild(0), null);
                    else
                        con = fc.createCondition(infoAction.getChild(0), a.parameters);
                    //con = fc.createCondition(infoAction.getChild(0), a.parameters);

                    if ((con instanceof NotCond) || (con instanceof Comparison) || (con instanceof Predicate) || (con instanceof ForAll)) {
                        AndCond and = new AndCond();
                        and.addConditions(con);
                        a.setPreconditions(and);
                    } else if (con != null) {
                        a.setPreconditions((ComplexCondition) con);
                    }
                    break;
                case (PddlParser.VARIABLE):
                    if (infoAction.getChild(0) == null) {
                        a.addParameter(new Variable(infoAction.getText(), Type.createType("object")));
                        break;
                    } else {
                        Type t = Type.createType(infoAction.getChild(0).getText());
                        a.addParameter(new Variable(infoAction.getText(), t));
                        break;
                    }
                case (PddlParser.EFFECT):
                    addEffects(a, infoAction);
//                    System.out.println(a);
                    break;
                default:
                    System.out.println("This hasn't been anticipated in the grammar properly");
                    System.out.println("Type: " + type);
                    System.out.println("InfoAction: " + infoAction);
                    break;
            }

        }
    }

    public boolean can_be_abstract_dominant_constraints ( ) {
        Set<Condition> set = new HashSet();
        for (ActionSchema as : this.ActionsSchema) {
            set.addAll(as.getPreconditions().getTerminalConditions());
        }

        for (int i = 0; i < set.toArray().length; i++) {
            for (int j = i + 1; j < set.toArray().length; j++) {
                Condition c1 = (Condition) set.toArray()[i];
                Condition c2 = (Condition) set.toArray()[j];
                if ((c1 instanceof Comparison) && (c2 instanceof Comparison)) {
                    Comparison comp_c1 = (Comparison) c1;
                    Comparison comp_c2 = (Comparison) c2;
                    if (comp_c1.getInvolvedFluents().equals(comp_c2.getInvolvedFluents())) {
                        //System.out.println(comp_c1+" "+comp_c2);
                        return true;
                    }
                }
            }

        }
        return false;
    }

    public void substituteEqualityConditions ( ) {
        for (ActionSchema actionSchema : this.getActionsSchema()) {
            actionSchema.setPreconditions((ComplexCondition) actionSchema.getPreconditions().transformEquality());
        }
        for (EventSchema event : this.eventsSchema) {
            event.setPreconditions((ComplexCondition) event.getPreconditions().transformEquality());
        }
        for (ProcessSchema process : this.ProcessesSchema) {
            process.setPreconditions((ComplexCondition) process.getPreconditions().transformEquality());
        }
//        for (SchemaGlobalConstraint gc :this.getSchemaGlobalConstraints()){
//            gc.condition = gc.condition.transform_equality();
//        }
    }

    public void setActionSchema (Collection<ActionSchema> actionsSchema) {
        this.ActionsSchema = actionsSchema;
    }

    public PddlDomain clone ( ) {

        PddlDomain res = new PddlDomain(new LinkedHashSet(this.eventsSchema), new PDDLObjects(this.constants), new LinkedHashSet<>(this.getProcessesSchema()), new LinkedHashSet<>(this.derived_variables));
        res.setName(this.name);
        res.requirements = new ArrayList<>(this.requirements);
        res.ActionsSchema = new LinkedHashSet<>();
        for (ActionSchema as : this.ActionsSchema) {
            res.ActionsSchema.add(as.clone());
        }
        res.types = new LinkedHashSet<>(this.types);
        res.predicates = new PredicateSet(this.predicates);
        res.functions = new LinkedHashSet(this.functions);
        return res;
    }


}
