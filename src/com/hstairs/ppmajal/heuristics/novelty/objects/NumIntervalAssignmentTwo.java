package com.hstairs.ppmajal.heuristics.novelty.objects;

import java.util.Objects;

// slower than just pair of NumericIntervalAssignment

public class NumIntervalAssignmentTwo {

  private final int var1;
  private final int val1;
  private final int var2;
  private final int val2;

  public NumIntervalAssignmentTwo(NumericIntervalAssignment a1, NumericIntervalAssignment a2) {
    this.var1 = a1.var();
    this.val1 = a1.val();
    this.var2 = a2.var();
    this.val2 = a2.val();
  }


  @Override
  public int hashCode() {
    return Objects.hash(var1, val1, var2, val2);
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final NumIntervalAssignmentTwo other = (NumIntervalAssignmentTwo) obj;
    return this.var1 == other.var1 && this.val1 == other.val1 && this.var2 == other.var2
        && this.val2 == other.val2;
  }

}
