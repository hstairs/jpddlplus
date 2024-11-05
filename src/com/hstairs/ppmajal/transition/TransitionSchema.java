package jpddlplus.transition;

import jpddlplus.conditions.AndCond;
import jpddlplus.conditions.BoolPredicate;
import jpddlplus.conditions.Condition;
import jpddlplus.conditions.ConditionalEffectAsACondition;
import jpddlplus.conditions.ForAll;
import jpddlplus.conditions.PostCondition;
import jpddlplus.conditions.Terminal;
import jpddlplus.domain.SchemaParameters;
import jpddlplus.expressions.NumEffect;
import jpddlplus.expressions.NumFluent;
import jpddlplus.problem.Printer;
import java.util.Collection;
import java.util.HashSet;

public class TransitionSchema extends Transition {

  final protected SchemaParameters parameters;
  final protected Collection<ForAll> forallEffects;

  public TransitionSchema(String name, Semantics semantics, SchemaParameters parameters,
      Condition preconditions, ConditionalEffects conditionalPropositionalEffects,
      ConditionalEffects conditionalNumericEffects) {
    this(parameters, name, conditionalPropositionalEffects, conditionalNumericEffects,
        preconditions, semantics, null);
  }

  public TransitionSchema(SchemaParameters par, String transitionName,
      ConditionalEffects<Terminal> propEffect, ConditionalEffects<NumEffect> numEffect,
      Condition precondition, Semantics semantics, Collection<ForAll> forall) {
    super(transitionName, propEffect, numEffect, precondition, semantics);
    this.forallEffects = forall;
    this.parameters = par;
  }

  public SchemaParameters getParameters() {
    return parameters;
  }

  public Collection<ForAll> getForallEffects() {
    return forallEffects;
  }

  @Override
  public String toString() {
    final StringBuilder ret = new StringBuilder();
    ret.append("(:action ");
    ret.append(name).append(" ");
    ret.append("\n\t:parameters ");
    if (this.parameters == null) {
      ret.append("()");
    } else {
      ret.append(this.parameters);
    }
    ret.append("\n\t:precondition ");
    if (this.preconditions == null) {
      ret.append("()");
    } else {
      ret.append(this.preconditions);
    }
    ret.append("\n\t:effect ");
    ret.append("(and \n");
    Printer.pddlPrint(ret, this.conditionalNumericEffects);
    Printer.pddlPrint(ret, this.conditionalPropositionalEffects);
    ret.append("\n))");
    return ret.toString();
  }

  public Collection<BoolPredicate> getBoolPredicates(PostCondition effect) {
    final Collection<BoolPredicate> ret = new HashSet();
    if (effect instanceof AndCond) {
      final Collection<BoolPredicate> involvedPredicates = ((AndCond) effect).getInvolvedPredicates();
      ret.addAll(involvedPredicates);
    } else if (effect instanceof Terminal) {
      final Collection<BoolPredicate> terminalConditions = ((BoolPredicate) effect).getInvolvedPredicates();
      ret.addAll(terminalConditions);
    }
    return ret;
  }


  public Collection<NumFluent> getNumPredicates(PostCondition effect) {
    final Collection<NumFluent> ret = new HashSet();
    if (effect instanceof AndCond) {
      for (var eff : ((AndCond) effect).sons) {
        if (eff instanceof PostCondition) {
          ret.addAll(getNumPredicates((PostCondition) eff));
        }
      }
    } else if (effect instanceof NumEffect) {
      final Collection<NumFluent> terminalConditions = ((NumEffect) effect).getInvolvedFluents();
      ret.addAll(terminalConditions);
    }
    return ret;
  }

  @Override
  public Iterable<? extends BoolPredicate> getPropositionAffected() {
    Collection<BoolPredicate> ret = (Collection<BoolPredicate>) super.getPropositionAffected();
    for (var f : this.forallEffects) {
      for (var s : f.sons) {
        if (s instanceof ConditionalEffectAsACondition) {
          PostCondition effect = ((ConditionalEffectAsACondition) s).effect;
          ret.addAll(getBoolPredicates(effect));
        } else {
          ret.addAll(getBoolPredicates((PostCondition) s));
        }
      }
    }
    return ret;
  }

  @Override
  public Collection<NumFluent> getAllNumericAffected() {
    Collection<NumFluent> ret = super.getAllNumericAffected();
    for (var f : this.forallEffects) {
      for (var s : f.sons) {
        if (s instanceof ConditionalEffectAsACondition) {
          PostCondition effect = ((ConditionalEffectAsACondition) s).effect;
          ret.addAll(getNumPredicates(effect));
        } else {
          ret.addAll(getNumPredicates((PostCondition) s));
        }
      }
    }
    return ret;
  }
}
