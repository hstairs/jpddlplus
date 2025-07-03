package com.hstairs.enhsp2;

import com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode;
import com.hstairs.ppmajal.extraUtils.IExternalLogger;

public class SimpleExternalLogger implements IExternalLogger {
    @Override
    public void log(SimpleSearchNode node) {
        System.out.println("From external logger!");
    }
}