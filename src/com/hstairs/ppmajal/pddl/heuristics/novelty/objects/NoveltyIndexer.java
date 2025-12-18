package com.hstairs.ppmajal.pddl.heuristics.novelty.objects;

public class NoveltyIndexer {

    private final int x;
    private final int k;
    private final double[] heuristicValues;
    private final long totCombinations;

    public NoveltyIndexer(final int x, final int k) {
        this.x = x;
        this.k = k;
        this.totCombinations = binomial(x, k);
        this.heuristicValues = new double[(int) totCombinations];
        for (int i = 0; i < totCombinations; i++) {
            this.heuristicValues[i] = Float.POSITIVE_INFINITY;
        }
    }

    public void set(int[] combination, double value) {
        int index = (int) getIndex(combination);
        heuristicValues[index] = value;
    }

    public double get(int[] combination) {
        int idx = (int) getIndex(combination);
        return heuristicValues[idx];
    }

    public long size(){
        return totCombinations;
    }

    private long getIndex(int[] combination){
        long index = 0;
        for(int m=0; m<k; m++){
            index += binomial(combination[m], m+1);
        }
        return index;
    }
    private long binomial (int x, int k) {
        if (k<0|| k>x) return 0;
        long res = 1;
        for (int i=1; i<=k; i++) {
            res = res*(x-i+1)/i;
        }
        return res;
    }
}
