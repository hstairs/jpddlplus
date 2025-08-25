package com.hstairs.ppmajal.extraUtils;

import com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode;

public interface IExternalLogger {
    public void log(SimpleSearchNode node, ExternalLoggerLogType logType);
}