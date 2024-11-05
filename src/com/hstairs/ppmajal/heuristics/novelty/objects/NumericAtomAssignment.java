package jpddlplus.heuristics.novelty.objects;

public record NumericAtomAssignment(int var, double val) {

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    NumericAtomAssignment other = (NumericAtomAssignment) obj;
    return (this.var == other.var) && (this.val == other.val);
  }

}
