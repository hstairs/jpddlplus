package com.hstairs.enhsp2;

import com.hstairs.ppmajal.search.searchnodes.IdaStarSearchNode;
import com.hstairs.ppmajal.search.searchnodes.SearchNode;
import com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode;
import com.hstairs.ppmajal.extraUtils.ExternalLoggerLogType;
import com.hstairs.ppmajal.extraUtils.IExternalLogger;

public class SimpleExternalLogger implements IExternalLogger {
    @Override
    public void log(SimpleSearchNode node, ExternalLoggerLogType logType) {
        if(node instanceof SearchNode) {
            System.out.println(node);
            return;
        }
        
        if(node instanceof IdaStarSearchNode) {
            System.out.println("IDAStar Search node");
            return;
        }
        
        System.out.println("node");
    }
}