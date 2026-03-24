package com.hstairs.enhsp.integration.execution;

import com.hstairs.ppmajal.conditions.Condition;
import com.hstairs.ppmajal.extraUtils.ExternalLoggerLogType;
import com.hstairs.ppmajal.extraUtils.IExternalLogger;
import com.hstairs.ppmajal.search.searchnodes.SimpleSearchNode;

public final class PlanningExecutionController implements IExternalLogger {
    private final Object lock = new Object();
    private volatile boolean paused = false;
    private volatile boolean stopped = false;
    private volatile SearchTreeListener searchTreeListener;
    private volatile Condition goalCondition;

    public void setSearchTreeListener(SearchTreeListener searchTreeListener) {
        this.searchTreeListener = searchTreeListener;
    }

    public void setGoalCondition(Condition goalCondition) {
        this.goalCondition = goalCondition;
    }

    public void markSolutionNode(SimpleSearchNode node) {
        if (searchTreeListener != null && node != null) {
            searchTreeListener.markSolutionNode(node);
        }
    }

    @Override
    public void beforeExecution() {
        if (searchTreeListener != null) {
            searchTreeListener.onSearchStart();
        }
        waitIfPausedAndCheckStop();
    }

    @Override
    public void log(SimpleSearchNode node, ExternalLoggerLogType logType) {
        if (searchTreeListener != null) {
            boolean isGoal = false;
            if (goalCondition != null && node != null && node.s != null) {
                try {
                    isGoal = node.s.satisfy(goalCondition);
                } catch (Exception ignored) {
                }
            }
            searchTreeListener.onLogEvent(node, logType, isGoal);
        }
        waitIfPausedAndCheckStop();
    }

    @Override
    public void afterExecution() {
        if (searchTreeListener != null) {
            searchTreeListener.onSearchEnd();
        }
        waitIfPausedAndCheckStop();
    }

    public void pause() {
        paused = true;
    }

    public void resume() {
        synchronized (lock) {
            paused = false;
            lock.notifyAll();
        }
    }

    public void stop() {
        synchronized (lock) {
            stopped = true;
            paused = false;
            lock.notifyAll();
        }
    }

    public boolean isStopped() {
        return stopped;
    }

    public void checkStopped() {
        if (stopped || Thread.currentThread().isInterrupted()) {
            throw new PlanningStoppedException();
        }
    }

    private void waitIfPausedAndCheckStop() {
        synchronized (lock) {
            while (paused && !stopped) {
                try {
                    lock.wait(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new PlanningStoppedException();
                }
            }
        }
        if (stopped || Thread.currentThread().isInterrupted()) {
            throw new PlanningStoppedException();
        }
    }
}
