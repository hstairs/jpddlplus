/*
 * Copyright (C) 2017 Enrico Scala.
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
package com.hstairs.ppmajal.conditions;

import com.hstairs.ppmajal.domain.PredicateSet;
import com.hstairs.ppmajal.domain.SchemaParameters;
import com.hstairs.ppmajal.domain.Type;
import com.hstairs.ppmajal.domain.Variable;
import com.hstairs.ppmajal.expressions.*;
import com.hstairs.ppmajal.parser.PddlParser;
import com.hstairs.ppmajal.problem.PDDLObjects;
import com.hstairs.ppmajal.transition.ConditionalEffects;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import org.antlr.runtime.tree.Tree;

/**
 * @author Enrico Scala
 */
public class FactoryConditions {

    private final PredicateSet predicates;
    private final LinkedHashSet<Type> types;
    public PDDLObjects constants;

    public FactoryConditions (PredicateSet predicates, LinkedHashSet<Type> types, PDDLObjects constants) {
        this.predicates = predicates;
        this.types = types;
        this.constants = constants;

    }

    @Override
    public String toString ( ) {
        return "FactoryConditions{" + "predicates=" + predicates + ", types=" + types + ", constants=" + constants + '}';
    }

    private Variable buildVariable (Tree t, SchemaParameters parTable) {
        Variable a = null;
        //a.setName(t.getChild(0).getText());
        //controllare che la variabile nei predicati sia effettivamente un parametro dell'azione oppure una costante!
        if (t.getType() == PddlParser.NAME) {

            System.out.println("Error in parsing variable terms");
            System.exit(-1);

        } else {

            a = Variable.variable(t.getText(),null);
            Variable v1 = parTable.getVar(a);
            if (v1 == null) {
                throw new RuntimeException("BuildPredicate: Variable " + a + " not involved in the action model");
//                System.exit(-1);
            }
            a = v1;
        }
        return a;
    }

    public Condition createCondition (Tree tree, SchemaParameters parTable) {
        Condition ret = null;
        if (tree == null) {
            return null;
        }

        switch (tree.getType()) {
            case PddlParser.PRED_HEAD:
                //extract the predicate
                return buildPredicate(tree, parTable);
            case PddlParser.AND_GD:
                Collection and = new HashSet();
                for (int i = 0; i < tree.getChildCount(); i++) {
                    Condition ret_val = createCondition(tree.getChild(i), parTable);
                    if (ret_val != null) {
                        and.add(ret_val);
                    }
                }
                return new AndCond(and);
            //Crea un and e per ogni figlio di questo nodo invoca creaformula
            //gestendo il valore di ritorno come un attributo di and
            case PddlParser.OR_GD:
                Collection or = new HashSet();
                for (int i = 0; i < tree.getChildCount(); i++) {
                    Condition ret_val = createCondition(tree.getChild(i), parTable);
                    if (ret_val != null) {
                        or.add(ret_val);
                    }
                }
                return new OrCond(or);

            case PddlParser.ONEOF:
                Collection oneOf = new HashSet();
                for (int i = 0; i < tree.getChildCount(); i++) {
                    Condition ret_val = createCondition(tree.getChild(i), parTable);
                    if (ret_val != null) {
                        oneOf.add(ret_val);
                    }
                }
                return new OneOf(oneOf);
            case PddlParser.NOT_GD:
                Condition cond = null; // TODO: Can it be null or should we throw an error?  
                for (int i = 0; i < tree.getChildCount(); i++) {
                    Condition ret_val = createCondition(tree.getChild(i), parTable);
                    if (ret_val != null) {
                        cond = ret_val;
                    }
                }
                NotCond not = NotCond.createNotCond(cond);
                return not;
            //Crea un not e per ogni figlio di questo nodo invoca creaformula
            //gestendo il valore di ritorno come un attributo di not
            case PddlParser.COMPARISON_GD:
                return Comparison.comparison(tree.getChild(0).getText(), createExpression(tree.getChild(1), parTable), createExpression(tree.getChild(2), parTable),false);
            //Create an equality structure for comparing Objects
            case PddlParser.EQUALITY_CON:
//                PDDLObjectsEquality equality = new Predicate();
//
//                if (tree.getChild(1).type() == PddlParser.NAME) {
//                    System.out.println("Constant objects in equality comparison are not supported");
//                    System.exit(-1);
//                } else {
//                    equality.setLeftV(buildVariable(tree.getChild(1), parTable));
//                }
//                if (tree.getChild(1).type() == PddlParser.NAME) {
//                    System.out.println("Constant objects in equality comparison are not supported");
//                    System.exit(-1);
//                } else {
//                    equality.setRightV(buildVariable(tree.getChild(2), parTable));
//                }
                return buildPredicate(tree, parTable);
            case PddlParser.FORALL_GD:
                ForAll forall = createForAll(tree, parTable, false);
                return forall;
            case PddlParser.EXISTS_GD:
                
                Collection exist = new HashSet();
                SchemaParameters p = new SchemaParameters();
                for (int i = 0; i < tree.getChildCount(); i++) {
                    Tree child = tree.getChild(i);
                    switch (child.getType()) {
                        case PddlParser.VARIABLE:
                            if (child.getChild(0) == null) {
                                break;
                            }
                            Type t = Type.type(child.getChild(0).getText());

                            p.add(Variable.variable(child.getText(), t));

                            break;
                        default:
                            //at this point I should have collected all the parameters for grounding
                            //the variable into constants
                            SchemaParameters aug_par_table = new SchemaParameters();
                            if (parTable != null) {
                                aug_par_table.addAll(parTable);
                            }
                            aug_par_table.addAll(p);
                            Condition ret_val = createCondition(child, aug_par_table);
                            if (ret_val != null) {
                                exist.add(ret_val);
                            }
                            break;

                    }

                }
                return new Existential(p,exist);
            case PddlParser.IMPLY_GD:
                System.out.println("Implication not supported yet:" + tree.getText());
                return null;
            //Create an equality structure for comparing Objects
            default:
                break;
        }
        return null;
    }

    public BoolPredicate buildPredicate (Tree t, SchemaParameters parTable) {
        //controllare che la variabile nei predicati sia effettivamente un parametro dell'azione oppure una costante!
        String name = t.getChild(0).getText();
        ArrayList variables = new ArrayList();
        for (int i = 1; i < t.getChildCount(); i++) {
            if (t.getChild(i).getType() == PddlParser.NAME) {
                PDDLObject o = PDDLObject.object(t.getChild(i).getText(),null);
                variables.add(o);
            } else {
                Variable v = Variable.variable(t.getChild(i).getText(),null);

                Variable v1 = parTable.getVar(v);
                if (v1 != null) {
                    variables.add(v1);
                } else {
                    throw new RuntimeException("BuildPredicate: Variable " + v + " not found in " + parTable);
//                    System.exit(-1);
                }
            }
        }
        final BoolPredicate a = BoolPredicate.getPredicate(name, variables);
        if (predicates != null) {
            if (!predicates.validate(a) & !"=".equals(name)) {
                throw  new RuntimeException("Predicate " + a + " is not valid");

            }
        }
        return a;
    }

    public Expression createExpression (Tree t, SchemaParameters parTable) {


        switch (t.getType()) {
            case PddlParser.BINARY_OP: {
                BinaryOp ret = new BinaryOp();
                ret.setOperator(t.getChild(0).getText());
                ret.setLhs(createExpression(t.getChild(1), parTable));
                ret.setRhs(createExpression(t.getChild(2), parTable));
                return ret;
            }
            case PddlParser.SIN: {
                TrigonometricFunction ret = new TrigonometricFunction();
//                System.out.println(t.getChild(1));
                ret.setOperator("sin");
                ret.setArg(createExpression(t.getChild(0), parTable));
                return ret;
            }
            case PddlParser.ABS: {
                ComplexFunction ret = new ComplexFunction();
//                System.out.println(t.getChild(1));
                ret.setOperator("abs");
                ret.setArg(createExpression(t.getChild(0), parTable));
                return ret;
            }
            case PddlParser.COS: {
                TrigonometricFunction ret = new TrigonometricFunction();
//                System.out.println(t.getChild(1));
                ret.setOperator("cos");
                ret.setArg(createExpression(t.getChild(0), parTable));
                return ret;
            }
            case PddlParser.TAN: {
                TrigonometricFunction ret = new TrigonometricFunction();
//                System.out.println(t.getChild(1));
                ret.setOperator("tan");
                ret.setArg(createExpression(t.getChild(0), parTable));
                return ret;
            }
            case PddlParser.ATAN: {
                TrigonometricFunction ret = new TrigonometricFunction();
//                System.out.println(t.getChild(1));
                ret.setOperator("atan");
                ret.setArg(createExpression(t.getChild(0), parTable));
                return ret;
            }
            case PddlParser.NUMBER: {
                PDDLNumber ret = new PDDLNumber(Float.parseFloat(t.getText()));
                return ret;
            }
            case PddlParser.FUNC_HEAD: {
                String name = t.getChild(0).getText();
                ArrayList variables = new ArrayList();
                for (int i = 1; i < t.getChildCount(); i++) {
//                System.out.println("Constant Type:" + PddlParser.CONSTANTS);
//                System.out.println("Name Type:" + PddlParser.NAME);
//                System.out.println("Current Type:" + t.getChild(i).type());
                    if (t.getChild(i).getType() == PddlParser.NAME) {
                        PDDLObject o = PDDLObject.object(t.getChild(i).getText(),null);
                        variables.add(o);
                    } else {
                        Variable v = Variable.variable(t.getChild(i).getText(),null);
                        //System.out.println(parTable);
                        Variable v1 = parTable.getVar(v);

                        if (v1 != null) {
                            variables.add(v1);
                        } else {
//                        System.out.println("t.type: " + t.getChild(i).getText());
                            System.out.println("NumFluent: Variable " + v + " not involved in the action model");
                            System.exit(-1);
                        }
                    }
                }
                NumFluent ret = NumFluent.getNumFluent(name, variables);
                return ret;
            }
            case PddlParser.UNARY_MINUS:
                return new MinusUnary(createExpression(t.getChild(0), parTable));
            case PddlParser.MULTI_OP: {
                MultiOp ret = new MultiOp(t.getChild(0).getText());
                for (int i = 1; i < t.getChildCount(); i++) {
                    //System.out.println("Figlio di + o * " + createExpression(t.getChild(i)));
                    ret.addExpression(createExpression(t.getChild(i), parTable));
                }
                return ret;
            }
            default:
                break;
        }

        return null;

    }

    public ForAll createForAll (Tree infoAction, SchemaParameters parTable, boolean effect) {
        Collection forall = new HashSet();
        SchemaParameters s = new SchemaParameters();
        for (int i = 0; i < infoAction.getChildCount(); i++) {
            Tree child = infoAction.getChild(i);
            switch (child.getType()) {
                case PddlParser.VARIABLE:
                    if (child.getChild(0) == null) {
                        break;
                    }
                    Type t = Type.type(child.getChild(0).getText());
                    s.add(Variable.variable(child.getText(), t));
                    break;
                default:
                    //at this point I should have collected all the parameters for grounding
                    //the variable into constants
                    SchemaParameters aug_par_table = new SchemaParameters();
                    if (parTable != null) {
                        aug_par_table.addAll(parTable);
                    }
                    aug_par_table.addAll(s);
                    Condition ret_val;
                    if (effect) {
                        ret_val = (Condition) this.createPostCondition(aug_par_table, child);
                    } else {
                        ret_val = createCondition(child, aug_par_table);
                    }
                    //System.out.println("ret_val for forall condition is:"+ret_val);
                    if (ret_val != null) {
                        forall.add(ret_val);
                    } else {
                        System.out.println("Something fishy here.." + child);
                        System.exit(-1);
                    }
                    break;

            }

        }
        return new ForAll(s,forall);

    }

    public PostCondition createPostCondition (SchemaParameters parameters, Tree tree) {
        if (tree == null) {
            return new AndCond(Collections.EMPTY_SET);
        }
        switch (tree.getType()) {
            case PddlParser.PRED_HEAD:
                //estrapola tutti i predicati e ritornali come set di predicati
                return buildPredicate(tree, parameters);

            case PddlParser.AND_EFFECT:
                Collection and = new HashSet();
                for (int i = 0; i < tree.getChildCount(); i++) {
                    Object ret_val = createPostCondition(parameters, tree.getChild(i));
                    if (ret_val != null) {
                        and.add(ret_val);
                    }
                }
                return new AndCond(and);
            case PddlParser.NOT_EFFECT:
                Condition ret_val = (Condition) createPostCondition(parameters, tree.getChild(0));
                NotCond not = NotCond.createNotCond(ret_val);
                return not;
            case PddlParser.ASSIGN_EFFECT:
                NumEffect a = new NumEffect(tree.getChild(0).getText());
//                System.out.println("DEBUG: Working out this effect:"+a);
                a.setFluentAffected((NumFluent) createExpression(tree.getChild(1), parameters));
                a.setRight(createExpression(tree.getChild(2), parameters));
                return a;
            case PddlParser.FORALL_EFFECT:
                ForAll forall = this.createForAll(tree, parameters, true);
                return forall;
            case PddlParser.WHEN_EFFECT:
                Condition lhs = createCondition(tree.getChild(0), parameters);
                PostCondition rhs = this.createPostCondition(parameters, tree.getChild(1));
                return new ConditionalEffect(lhs, rhs);
            default:
                throw new RuntimeException("Error in : "+tree+" whose type is"+tree.getType());
        }
    }

    public Condition createGoals (Tree infoAction) {
        if (infoAction == null) {
            return null;
        }
        if (infoAction.getType() == PddlParser.PRED_HEAD) {
            //estrapola tutti i predicati e ritornali come set di predicati
//            AndCond and = new AndCond();
//            and.addConditions();
            return buildPredicate(infoAction, null);
        } else if (infoAction.getType() == PddlParser.AND_GD) {
            Collection and = new HashSet();
            for (int i = 0; i < infoAction.getChildCount(); i++) {
                Condition ret_val = createGoals(infoAction.getChild(i));
                if (ret_val != null) {
                    and.add(ret_val);
                }
            }
            return new AndCond(and);

        } else if (infoAction.getType() == PddlParser.OR_GD) {
            Collection or = new HashSet();
            for (int i = 0; i < infoAction.getChildCount(); i++) {
                Condition ret_val = createGoals(infoAction.getChild(i));
                if (ret_val != null) {
                    or.add(ret_val);
                }
            }
            return new OrCond(or);
            //Crea un or e per ogni figlio di questo nodo invoca creaformula
            //gestendo il valore di ritorno come un attributo di or
        } else if (infoAction.getType() == PddlParser.NOT_GD) {
            Condition cond = null; // TODO: Can the condition be null or should we throw an error if that happens?
            for (int i = 0; i < infoAction.getChildCount(); i++) {
                Condition ret_val = createGoals(infoAction.getChild(i));
                if (ret_val != null) {
                    cond = ret_val;
                }
            }
            NotCond not = NotCond.createNotCond(cond);
            return not;
            //Crea un not e per ogni figlio di questo nodo invoca creaformula
            //gestendo il valore di ritorno come un attributo di not
        } else if (infoAction.getType() == PddlParser.COMPARISON_GD) {
            //System.out.println("Comparison:" + infoAction.getText());
            Collection ret = new HashSet();
            ret.add(Comparison.comparison(infoAction.getChild(0).getText(), createExpression(infoAction.getChild(1)), createExpression(infoAction.getChild(2)),false));
            return new AndCond(ret);
            //Crea un not e per ogni figlio di questo nodo invoca creaformula
            //gestendo il valore di ritorno come un attributo di not
        } else if (infoAction.getType() == PddlParser.ONEOF) {
            Collection oneOf = new HashSet();
            for (int i = 0; i < infoAction.getChildCount(); i++) {
                Condition ret_val = addOneOf(infoAction.getChild(i));
                if (ret_val != null) {
                    oneOf.add(ret_val);
                }
            }
            return new OneOf(oneOf);
        }

        return null;
    }

    protected Expression createExpression (Tree t) {

        int test = t.getType();
        switch (t.getType()) {
            case PddlParser.BINARY_OP: {
                BinaryOp ret = new BinaryOp();
                ret.setOperator(t.getChild(0).getText());
                ret.setLhs(createExpression(t.getChild(1)));
                ret.setRhs(createExpression(t.getChild(2)));
                ret.grounded = true;
                return ret;
            }
            case PddlParser.NUMBER: {
                //Float.
                PDDLNumber ret = new PDDLNumber(Float.valueOf(t.getText()));
                return ret;
            }
            case PddlParser.FUNC_HEAD: {
                String name = t.getChild(0).getText();
                ArrayList variables = new ArrayList();
                for (int i = 1; i < t.getChildCount(); i++) {
//                System.out.println("Constant Type:" + PddlParser.CONSTANTS);
//                System.out.println("Name Type:" + PddlParser.NAME);
//                System.out.println("Current Type:" + t.getChild(i).type());
                    if (t.getChild(i).getType() == PddlParser.NAME) {
                        PDDLObject o = PDDLObject.object(t.getChild(i).getText(),null);
                        variables.add(o);
                    } else {

                    }
                }
                return NumFluent.getNumFluent(name, variables);
            }
            case PddlParser.UNARY_MINUS:
                return new MinusUnary(createExpression(t.getChild(0)));
            case PddlParser.MULTI_OP: {
                MultiOp ret = new MultiOp(t.getChild(0).getText());
                for (int i = 1; i < t.getChildCount(); i++) {
                    //System.out.println("Figlio di + o * " + createExpression(t.getChild(i)));
                    ret.addExpression(createExpression(t.getChild(i)));
                }
                ret.grounded = true;
                return ret;
            }
            default:
                break;
        }

        return null;

    }

    private Condition addOneOf (Tree infoAction) {
        if (infoAction == null) {
            return null;
        }
        switch (infoAction.getType()) {
            case PddlParser.PRED_HEAD:
                //estrapola tutti i predicati e ritornali come set di predicati
//            AndCond and = new AndCond();
//            and.addConditions();
                return buildPredicate(infoAction, null);
            case PddlParser.AND_GD:
                Collection and = new HashSet();
                for (int i = 0; i < infoAction.getChildCount(); i++) {
                    Condition ret_val = addOneOf(infoAction.getChild(i));
                    if (ret_val != null) {
                        and.add(ret_val);
                    }
                }
                return new AndCond(and);
            case PddlParser.OR_GD:
                //            System.out.println("Or Condition");
                Collection or = new HashSet();
                for (int i = 0; i < infoAction.getChildCount(); i++) {
                    Condition ret_val = addOneOf(infoAction.getChild(i));
//                System.out.println(ret_val);
                    if (ret_val != null) {
                        or.add(ret_val);
                    }
                }
                return new OrCond(or);
            //Crea un or e per ogni figlio di questo nodo invoca creaformula
            //gestendo il valore di ritorno come un attributo di or
            case PddlParser.NOT_PRED_INIT:
                Condition cond = null; // TODO: Can the condition be null or should we throw an error?
                for (int i = 0; i < infoAction.getChildCount(); i++) {
                    Condition ret_val = addOneOf(infoAction.getChild(i));
                    if (ret_val != null) {
                        cond = ret_val;
                    }
                }
                NotCond not = NotCond.createNotCond(cond);
                return not;
            case PddlParser.PRED_INST:
                //estrapola tutti i predicati e ritornali come set di predicati
//            AndCond and = new AndCond();
//            and.addConditions();
                return this.buildPredicate(infoAction, null);
            case PddlParser.ONEOF:
                Collection one_of = new HashSet();
                for (int i = 0; i < infoAction.getChildCount(); i++) {
                    Condition ret_val = addOneOf(infoAction.getChild(i));
                    if (ret_val != null) {
                        one_of.add(ret_val);
                    }
                }
                return new OneOf(one_of);
            case PddlParser.NOT_GD:
                NotCond nc = null;
                for (int i = 0; i < infoAction.getChildCount(); i++) {
                    Condition ret_val = addOneOf(infoAction.getChild(i));
                    if (ret_val != null) {
                        nc = NotCond.createNotCond(ret_val);
                        return nc;
                    }
                }
//                break;
//                return one_of;
            default:
                throw new RuntimeException("Oneof Parsing: Some serious error:" + infoAction);
//                return null;
        }
//        return null;
    }

    public Collection<NumFluent> addFunctions (Tree c) {
        LinkedHashSet<NumFluent> res = new LinkedHashSet();
        if (c != null) {
            for (int i = 0; i < c.getChildCount(); i++) {
                ArrayList variables = new ArrayList();
                Tree t = c.getChild(i);
                for (int j = 0; j < t.getChildCount(); j++) {
                    String father = "object";
                    if (t.getChild(j).getChildCount() > 0) {
                        father = t.getChild(j).getChild(0).getText();
                    }
                    Variable v = Variable.variable(t.getChild(j).getText(),Type.type(father));
                    variables.add(v);
                }
                NumFluent ret = NumFluent.getNumFluent(c.getChild(i).getText(), variables);
                res.add(ret);
            }
        }
        return res;
    }

    public static void addEffectFromConditonalEffects(ConditionalEffect cond, ConditionalEffects<Terminal> propEffect, ConditionalEffects<NumEffect> numEffect) {
        if (cond.effect instanceof AndCond) {
            final AndCond temp = (AndCond) cond.effect;
            for (Object son : temp.sons) {
                if (son instanceof NumEffect) {
                    numEffect.add(cond.activation_condition, (NumEffect) son);
                } else if (son instanceof BoolPredicate || son instanceof NotCond) {
                    propEffect.add(cond.activation_condition, (Terminal) son);
                } else {
                    throw new RuntimeException("Effects cannot be nested, but can only be considered as sets (" + son + ")");
                }
            }
        }
    }

 
    public Collection<ForAll>  createEffectsFromPostCondition(Tree child, PostCondition res, ConditionalEffects<Terminal> propEffect, ConditionalEffects<NumEffect> numEffect) {
        Collection<ForAll> forAllResult = new ArrayList();
        if (res instanceof AndCond) {
            AndCond pc = (AndCond) res;
            for (Object o : pc.sons) {
                if (o instanceof BoolPredicate) {
                    propEffect.add((Terminal) o);
                } else if (o instanceof NotCond) {
                    propEffect.add((Terminal) o);
                } else if (o instanceof NumEffect) {
                    numEffect.add((NumEffect) o);
                } else if (o instanceof ConditionalEffect) {
                    ConditionalEffect cond = (ConditionalEffect) o;
                    addEffectFromConditonalEffects(cond, propEffect, numEffect);
                } else if (o instanceof ForAll) {
                    forAllResult.add((ForAll) o);
                    
                }
            }
        } else if (res instanceof BoolPredicate) {
            propEffect.add((Terminal) res);
        } else if (res instanceof NotCond) {
            propEffect.add((Terminal) res);
        } else if (res instanceof NumEffect) {
            numEffect.add((NumEffect) res);
        } else if (res instanceof ConditionalEffect) {
            ConditionalEffect cond = (ConditionalEffect) res;
            addEffectFromConditonalEffects(cond, propEffect, numEffect);
        } else if (res instanceof ForAll) {
            forAllResult.add((ForAll) res);
        }
        return forAllResult;
    }

    public static void createEffectsFromPostCondition(PostCondition res, ConditionalEffects<Terminal> propEffect, ConditionalEffects<NumEffect> numEffect) {
        if (res instanceof AndCond) {
            AndCond pc = (AndCond) res;
            for (Object o : pc.sons) {
                if (o instanceof BoolPredicate) {
                    propEffect.add((Terminal) o);
                } else if (o instanceof NotCond) {
                    propEffect.add((Terminal) o);
                } else if (o instanceof NumEffect) {
                    numEffect.add((NumEffect) o);
                } else if (o instanceof ConditionalEffect) {
                    ConditionalEffect cond = (ConditionalEffect) o;
                    addEffectFromConditonalEffects(cond, propEffect, numEffect);
                } else if (o instanceof ForAll) {
                    throw new UnsupportedOperationException();
                }
            }
        } else if (res instanceof BoolPredicate) {
            propEffect.add((Terminal) res);
        } else if (res instanceof NotCond) {
            propEffect.add((Terminal) res);
        } else if (res instanceof NumEffect) {
            numEffect.add((NumEffect) res);
        } else if (res instanceof ConditionalEffect) {
            ConditionalEffect cond = (ConditionalEffect) res;
            addEffectFromConditonalEffects(cond, propEffect, numEffect);
        } else if (res instanceof ForAll) {
            throw new UnsupportedOperationException();
        }
    }
}
