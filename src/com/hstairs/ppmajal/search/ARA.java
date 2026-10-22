package com.hstairs.ppmajal.search;

import com.hstairs.ppmajal.extraUtils.ExternalLoggerLogType;
import com.hstairs.ppmajal.problem.State;
import com.hstairs.ppmajal.search.searchnodes.SearchNode;
import com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode;
import it.unimi.dsi.fastutil.PriorityQueue;
import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import it.unimi.dsi.fastutil.objects.Object2FloatOpenHashMap;
import org.jgrapht.alg.util.Pair;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.Objects;

public class ARA extends SearchEngine{
    protected ARA(boolean helpfulActionsPruning) {
        super(helpfulActionsPruning);
    }

    @Override
    public SearchStats getStats() {
        return null;
    }

    @Override
    public SimpleSearchNode search(SearchProblem problem, SearchHeuristic h, PrintStream out) {
        throw  new UnsupportedOperationException("Not supported yet.");
    }
}
