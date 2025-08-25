package com.hstairs.ppmajal.extraUtils;

import com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode;

public interface IExternalLogger {
    public void log(SimpleSearchNode node, ExternalLoggerLogType logType);

    // Method called before the search
    default void beforeExecution() {}

    // Method called when the search is stopped
    default void afterExecution() {}
}