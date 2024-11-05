package jpddlplus.search;

import jpddlplus.problem.State;

public class SimpleSearchNode {

  final public State s;
  final public Object transition;
  final public SimpleSearchNode father;
  public float gValue;


  public SimpleSearchNode(State s, Object transition, SimpleSearchNode father, float g) {
    this.s = s;
    if (!(transition instanceof Integer)) {
      this.transition = transition;
    } else {
      this.transition = null;
    }
    this.father = father;
    this.gValue = g;
  }
}