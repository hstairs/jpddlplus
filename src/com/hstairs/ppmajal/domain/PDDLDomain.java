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

import com.hstairs.ppmajal.conditions.*;
import com.hstairs.ppmajal.expressions.NumEffect;
import com.hstairs.ppmajal.expressions.NumFluent;
import com.hstairs.ppmajal.extraUtils.Utils;
import com.hstairs.ppmajal.parser.PddlLexer;
import com.hstairs.ppmajal.parser.PddlParser;
import com.hstairs.ppmajal.PDDLProblem.PDDLObjects;
import com.hstairs.ppmajal.transition.ConditionalEffects;
import com.hstairs.ppmajal.transition.Transition.Semantics;
import com.hstairs.ppmajal.transition.TransitionSchema;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

/**
 * @author enrico
 */
public final class PDDLDomain {

    /**
     * @return the requirements
     */
    public Collection<String> getRequirements() {
        if (requirements == null) {
            requirements = new ArrayList();
        }
        return requirements;
    }

    /**
     * @return the functions
     */
    public Collection getFunctions() {
        return functions;
    }

    /**
     * @return the eventsSchema
     */
    public Collection<TransitionSchema> getEventsSchema() {
        return eventsSchema;
    }

    HashMap<String, Boolean> canBeDynamic;
    private Collection<TransitionSchema> eventsSchema;
    public final PDDLObjects constants;
    private Collection<TransitionSchema> processesSchemata;
    private Collection<NumFluent> derived_variables;
    private Set<Type> types;
    private Collection functions;
    private Collection<String> requirements;
    protected Collection<TransitionSchema> ActionsSchema;
    private String name;
    private final PredicateSet predicates;
    private String pddlReferenceFile;
    private Set<SchemaGlobalConstraint> SchemaGlobalConstraints;
    private FactoryConditions fc;

    public PDDLDomain(String domainFile) {
        this();
        this.parseDomain(domainFile);
    }

    public PDDLDomain() {
        this(new LinkedHashSet(), new PDDLObjects(), new LinkedHashSet(), new ArrayList(), new PredicateSet());
    }

    private PDDLDomain(Collection<TransitionSchema> eventsSchema, PDDLObjects constants,
            Set<TransitionSchema> processesSchema, Collection<NumFluent> derived_variables, PredicateSet p) {
        SchemaGlobalConstraints = null;
        types = new LinkedHashSet();
        types.add(Type.type("object"));
        ActionsSchema = new TreeSet<>(new ActionComparator());
        functions = new ArrayList();
        requirements = null;
        this.eventsSchema = eventsSchema;
        this.constants = constants;
        processesSchemata = processesSchema;
        this.derived_variables = derived_variables;
        this.predicates = p;
    }

    /**
     * @param file - the path of the pddl file representing the domain. As
     * return the object will be fullfilled with the information in the pddl
     * domain file
     * @throws IOException
     * @throws RecognitionException
     */
    public void parseDomain(String file) {
        try {
            this.setPddlFilRef(file);
            final ANTLRInputStream in;
            in = new ANTLRInputStream(new FileInputStream(file));
            PddlLexer lexer = new PddlLexer(in);
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            final PddlParser parser = new PddlParser(tokens);
            final PddlParser.pddlDoc_return root = parser.pddlDoc();

            if (parser.invalidGrammar()) {
                System.out.println("Some Syntax Error");
                System.exit(-1);
            }

//        fc = new FactoryConditions(this.predicates, (LinkedHashSet<Type>) this.types,this.constants);
            CommonTree t = (CommonTree) root.getTree();
            int i;
//            System.out.println(this.constants);
            fc = new FactoryConditions(this.predicates, (LinkedHashSet<Type>) this.getTypes(), this.constants);
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
                        addPredicates(c);
                        fc = new FactoryConditions(this.predicates, (LinkedHashSet<Type>) this.getTypes(), this.constants);

                        break;
                    case PddlParser.ACTION:
                        //fc = new FactoryConditions(this.predicates, (LinkedHashSet<Type>) this.types, this.constants);
//                        System.out.println(fc.constants);
                        addTransition(c, Semantics.ACTION);
                        break;
                    case PddlParser.EVENT:
                        addTransition(c, Semantics.EVENT);
                        break;
                    case PddlParser.REQUIREMENTS:
                        addRequirements(c);
                        break;
                    case PddlParser.FUNCTIONS:
                        this.getFunctions().addAll(fc.addFunctions(c));
                        break;
                    case PddlParser.CONSTANTS:
                        addConstants(c);
                        fc.constants = this.constants;
                        break;
                    case PddlParser.FREE_FUNCTIONS:
                        this.derived_variables.addAll(fc.addFunctions(c));
                        break;
                    case PddlParser.GLOBAL_CONSTRAINT:
                        fc = new FactoryConditions(this.predicates, (LinkedHashSet<Type>) this.getTypes(), this.constants);
                        addGlobalConstraint(c);
                        break;
                    case PddlParser.PROCESS:
                        addTransition(c, Semantics.PROCESS);
                        break;
//                case PddlParser.DOM_CONSTRAINTS:
//                    addGlobalConstraints(c);
//                    break;
                }
            }
            pushNotAtTheTerminals();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PDDLDomain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PDDLDomain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RecognitionException ex) {
            Logger.getLogger(PDDLDomain.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * A pretty representation of the domain
     */
    public void prettyPrint() {
        System.out.println("Requirements: " + this.getRequirements());
        System.out.println("Types: " + this.types);
        System.out.println("Types Hierarchy: " + Type.getHierarchy());
        System.out.println("Predicates: " + this.predicates);
        System.out.println("Functions: " + this.getFunctions());

        System.out.println("Actions Domain: " + this.ActionsSchema);
        if (this.processesSchemata != null) {
            System.out.println("Process Domain: " + this.processesSchemata);
        }
        //if (!this.eventsSchema.isEmpty())
        System.out.println("Events Domain: " + this.getEventsSchema());
        System.out.println("Global Constraints: " + this.getSchemaGlobalConstraints());

    }

    /**
     * @return the ActionsSchema- a Set which contains all the action schema of
     * the domain
     */
    public Collection<TransitionSchema> getActionsSchema() {
        return ActionsSchema;
    }

    /**
     * @return the types declared in the domain
     */
    public Set<Type> getTypes() {
        if (types == null) {
            types = new LinkedHashSet();
        }
        return types;
    }

    /**
     * @return the predicates definitions
     */
    public PredicateSet getPredicates() {
        return predicates;
    }

    //da migliorare perchè dovrebbe rappresentare una potenziale gerarchica di oggetti!!!
    private void addTypes(Tree c) {
        for (int i = 0; i < c.getChildCount(); i++) {

            Tree tipo = c.getChild(i);
            String father = "object";
            if (tipo.getChildCount() > 0) {
                father = tipo.getChild(0).getText();
            }
            Type tip = Type.getType(c.getChild(i).getText(), father);
            this.getTypes().add(tip);
        }
    }

    private Object addPredicates(Tree t) {
        PredicateSet col = predicates;
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
                col.add(BoolPredicate.getPredicate(name, variables));
            }
            return col;
        } else {
            String father = "object";
            if (t.getChildCount() > 0) {
                father = t.getChild(0).getText();
            }
            Variable v = Variable.variable(t.getText(), Type.type(father));
            return v;
        }
    }

    private void addRequirements(Tree c) {
        if (c != null) {
            //System.out.println(c.getText());
            for (int i = 0; i < c.getChildCount(); i++) {
                final String req = c.getChild(i).getText();
                this.getRequirements().add(req);
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
    public TransitionSchema getActionByName(String name) {
        for (final TransitionSchema el : ActionsSchema) {
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
    public String getPddlFilRef() {
        return pddlReferenceFile;
    }

    /**
     * @param pddlFilRef the pddlFilRef to set
     */
    public void setPddlFilRef(String pddlFilRef) {
        this.pddlReferenceFile = pddlFilRef;
    }

    private void addConstants(Tree c) {

        for (int i = 0; i < c.getChildCount(); i++) {
            if (c.getChild(i).getChildCount() > 0) {
                PDDLObject o = PDDLObject.object(c.getChild(i).getText(), Type.type(c.getChild(i).getChild(0).getText()));
                this.getConstants().add(o);
            } else {
                PDDLObject o = PDDLObject.object(c.getChild(i).getText(), Type.type("object"));
                this.getConstants().add(o);
            }
//            System.out.println("Aggiungo l'oggetto:" + c.getChild(i).getText());
//            System.out.println("che è di tipo:" + new Type(c.getChild(i).getChild(0).getText()));
        }

    }

    /**
     * @return the constants
     */
    public PDDLObjects getConstants() {
        return constants;
    }

    public void saveDomain(String file) throws IOException {
        PDDLDomain domain = this;
        Writer f;

        f = new BufferedWriter(new FileWriter(file));
        ParametersAsTerms constants = new ParametersAsTerms();
        String actions = "\n";

        f.write("(define (domain " + domain.getName() + ")\n");
        if (this.getRequirements() != null && !domain.getRequirements().isEmpty()) {
            f.write("(:requirements " + Utils.toPDDLSet(getRequirements()) + ")\n");
        }
        if (domain.getTypes() != null && !domain.getTypes().isEmpty()) {
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
            f.write("(:functions " + Utils.toPDDLSet(getFunctions()) + ")\n");
        }

        if (!domain.getActionsSchema().isEmpty()) {
            f.write(Utils.toPDDLSetWithBreak(domain.getActionsSchema()));
        }

        f.write(actions);
        f.write("\n)");
        f.close();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Boolean> getDynamicPredicateMap() {

        if (canBeDynamic == null) {
            canBeDynamic = new HashMap();
            ArrayList<TransitionSchema> all = new ArrayList();
            all.addAll(this.ActionsSchema);
            all.addAll(this.processesSchemata);
            all.addAll(this.getEventsSchema());
            Set<Condition> terminalConditions = new HashSet();
            for (int i = 0; i < all.size(); i++) {
                terminalConditions.addAll(all.get(i).getPreconditions().getTerminalConditions());
            }
            for (int j = 0; j < all.size(); j++) {
                TransitionSchema transition = all.get(j);
                for (final Condition cond : terminalConditions) {
                    Terminal p = null;
                    if (cond instanceof NotCond) {
                        p = (Terminal) ((NotCond) cond).getSon();
                    } else if ((cond instanceof BoolPredicate) || (cond instanceof Comparison)) {
                        p = (Terminal) cond;
                    }
                    if (p != null && p instanceof BoolPredicate) {
                        for (BoolPredicate pred : transition.getPropositionAffected()) {
                            if (pred.getName().equals(((BoolPredicate) p).getName())) {
                                canBeDynamic.put(((BoolPredicate) p).getName(), true);
                            }
                        }
                        
                    }
                    if (p != null && p instanceof Comparison) {
                        for (NumFluent nf : transition.getAllNumericAffected()) {
                            for (NumFluent nf2 : p.getInvolvedFluents()) {
                                if (nf.getName().equals(nf2.getName())) {
                                    canBeDynamic.put(nf2.getName(), true);
                                }
                            }
                        }
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

    /**
     * @return the free_functions
     */
    public Collection<NumFluent> get_derived_variables() {
        return derived_variables;
    }

    private void addGlobalConstraints(Tree c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void addGlobalConstraint(Tree c) {
        SchemaGlobalConstraint con = new SchemaGlobalConstraint(c.getChild(0).getText());
        //System.out.println("Adding:"+a.getName());

        this.getSchemaGlobalConstraints().add(con);

        for (int i = 1; i < c.getChildCount(); i++) {
            Tree infoConstraint = c.getChild(i);
            int type = infoConstraint.getType();

            switch (type) {
                case (PddlParser.PRECONDITION):

                    Condition condition = fc.createCondition(infoConstraint.getChild(0), con.parameters);
                    if ((condition instanceof Comparison) || (condition instanceof BoolPredicate)) {
                        Collection and = new HashSet();
                        and.add(condition);
                        con.condition = new AndCond(and);
                    } else {
                        con.condition = condition;
                    }
                    break;
                case (PddlParser.VARIABLE):
                    if (infoConstraint.getChild(0) == null) {
                        break;
                    }
                    Type t = Type.type(infoConstraint.getChild(0).getText());
                    con.parameters.add(Variable.variable(infoConstraint.getText(), t));
                    break;

            }

        }
    }

    /**
     * @return the SchemaGlobalConstraints
     */
    public Set<SchemaGlobalConstraint> getSchemaGlobalConstraints() {
        if (SchemaGlobalConstraints == null) {
            SchemaGlobalConstraints = new LinkedHashSet();
        }
        return SchemaGlobalConstraints;
    }

    /**
     * @param SchemaGlobalConstraints the SchemaGlobalConstraints to set
     */
    public void setSchemaGlobalConstraints(LinkedHashSet SchemaGlobalConstraints) {
        this.SchemaGlobalConstraints = SchemaGlobalConstraints;
    }

    private void addTransition(Tree c, Semantics semantics) {
        Tree name = c.getChild(0);
//        System.out.println("DEBUG: Adding:"+a.getName());
        String transitionName = name.getText();
        Condition precondition = null;
        SchemaParameters par = new SchemaParameters();
        ConditionalEffects<Terminal> propEffect = new ConditionalEffects<>();
        ConditionalEffects<NumEffect> numEffect = new ConditionalEffects<>();
        Collection<ForAll> forall = new ArrayList();
        for (int i = 1; i < c.getChildCount(); i++) {
            Tree infoAction = c.getChild(i);
            int type = infoAction.getType();
            switch (type) {
                case (PddlParser.PRECONDITION):
                    Condition con = fc.createCondition(infoAction.getChild(0), par);
                    if ((con instanceof Comparison) || (con instanceof BoolPredicate)) {
                        Collection and = new HashSet();
                        and.add(con);
                        precondition = new AndCond(and);
                    } else if (con != null) {
                        precondition = con;
                    }
                    break;
                case (PddlParser.VARIABLE):
                    Type t = null;
                    if (infoAction.getChild(0) == null) {
                        t = Type.type("object");
                    } else {
                        t = Type.type(infoAction.getChild(0).getText());
                    }

                    Variable variable = Variable.variable(infoAction.getText(), t);
//                        System.out.print(variable);
                    par.add(variable);
                    break;
                case (PddlParser.EFFECT):

                    PostCondition res = fc.createPostCondition(par, infoAction.getChild(0));
                    forall = fc.createEffectsFromPostCondition(infoAction.getChild(0), res, propEffect, numEffect);

                    break;

            }

        }

        TransitionSchema transition = new TransitionSchema(par, transitionName, propEffect, numEffect, precondition, semantics, forall);
        switch (semantics) {
            case ACTION:
                this.ActionsSchema.add(transition);
                break;
            case PROCESS:
                this.processesSchemata.add(transition);
                break;
            case EVENT:
                this.getEventsSchema().add(transition);
                break;
        }

    }

    /**
     * @return the processesSchemata
     */
    public Collection<TransitionSchema> getProcessesSchema() {
        return processesSchemata;
    }

    //    private Object createConditionalEffect(SchemaParameters parameters, Tree t) {
//        
//        if (t.type() == PddlParser.AND_EFFECT){
//            for (int j = 0; j < t.getChildCount(); j++) {
//                    if (t.getChild(j).type() == PddlParser.WHEN_EFFECT){
//                        Tree child = t.getChild(j);
//                        System.out.println(t.getChild(j).toStringTree());
//                    }
//                }
//        }
//        return null;
//    }
    public void saveDomainWithInterpretationObjects(String file) throws IOException {
        PDDLDomain domain = this;
        Writer f;

        f = new BufferedWriter(new FileWriter(file));
        ParametersAsTerms constants = new ParametersAsTerms();

        f.write("(define (domain " + domain.getName() + ")\n");
        if (getRequirements() != null && !requirements.isEmpty()) {
            f.write(";(:requirements " + Utils.toPDDLSet(getRequirements()) + ")\n");
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

    private ArrayList<TransitionSchema> pushNotAtTheTerminals(Collection<TransitionSchema> input) {
        ArrayList<TransitionSchema> output = new ArrayList(input);
        ListIterator<TransitionSchema> iterator = output.listIterator();
        while (iterator.hasNext()) {
            final TransitionSchema next = iterator.next();
            final TransitionSchema transitionSchema = new TransitionSchema(next.getParameters(), next.getName(),
                    next.getConditionalPropositionalEffects().pushNotToTerminals(),
                    next.getConditionalNumericEffects().pushNotToTerminals(),
                    next.getPreconditions().pushNotToTerminals(),
                    next.getSemantics(),
                    next.getForallEffects());
            iterator.set(transitionSchema);
            
        }
        return output;

    }

    private void pushNotAtTheTerminals() {
        final ArrayList listViewOfTransitions = new ArrayList(this.getActionsSchema());
        listViewOfTransitions.addAll(this.getProcessesSchema());
        listViewOfTransitions.addAll(this.getEventsSchema());
        final ListIterator<TransitionSchema> iterator = listViewOfTransitions.listIterator();
        this.ActionsSchema = pushNotAtTheTerminals(this.ActionsSchema);
        this.processesSchemata = pushNotAtTheTerminals(this.processesSchemata);
        this.eventsSchema = pushNotAtTheTerminals(getEventsSchema());
    }

    public void substituteEqualityConditions() {
        ArrayList listViewOfTransitions = new ArrayList(this.getActionsSchema());
        listViewOfTransitions.addAll(this.getProcessesSchema());
        listViewOfTransitions.addAll(this.getEventsSchema());
        ListIterator<TransitionSchema> iterator = listViewOfTransitions.listIterator();

        while (iterator.hasNext()) {
            TransitionSchema next = iterator.next();

            iterator.set(new TransitionSchema(next.getParameters(), next.getName(),
                    next.getConditionalPropositionalEffects(),
                    next.getConditionalNumericEffects(),
                    next.getPreconditions().transformEquality(),
                    next.getSemantics(),
                    next.getForallEffects()));
        }
    }

    public PDDLDomain clone() {

        PDDLDomain res = new PDDLDomain(new LinkedHashSet(this.getEventsSchema()), new PDDLObjects(this.constants), new LinkedHashSet<>(this.getProcessesSchema()), new LinkedHashSet<>(this.derived_variables), predicates);
        res.setName(this.name);
        res.requirements = new ArrayList<>(this.getRequirements());
        res.ActionsSchema = new LinkedHashSet<>();
        for (TransitionSchema as : this.ActionsSchema) {
            throw new UnsupportedOperationException("Cloning of the domain not supported yet");
//            res.ActionsSchema.add(as.clone());
        }
        res.types = new LinkedHashSet<>(this.getTypes());
        res.functions = new LinkedHashSet(this.getFunctions());
        return res;
    }

    public void addPredicate(String a, List l) {
        predicates.add(BoolPredicate.getPredicate(a, l));
    }

    public void addType(Type createType) {
        types.add(createType);
    }

    public void addAction(TransitionSchema action) {
        ActionsSchema.add(action);
    }

    public void addFunction(String a, ArrayList arrayList) {
        functions.add(NumFluent.getNumFluent(a, arrayList));
    }

    public void addBoolPredicate(String a) {
        predicates.add(BoolPredicate.getPredicate(a, null));
    }

}
