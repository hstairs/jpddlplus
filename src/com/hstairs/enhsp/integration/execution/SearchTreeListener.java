package com.hstairs.enhsp.integration.execution;

import com.hstairs.ppmajal.extraUtils.ExternalLoggerLogType;
import com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode;

public interface SearchTreeListener {
    void onSearchStart();

    void onLogEvent(SimpleSearchNode node, ExternalLoggerLogType logType, boolean isGoal);

    void onSearchEnd();

    void markSolutionNode(SimpleSearchNode node);
}
