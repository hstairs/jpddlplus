package com.hstairs.ppmajal.extraUtils;

public class PlannerExitException extends RuntimeException {
    private final int exitCode;

    public PlannerExitException(int exitCode, String message) {
        super(message);
        this.exitCode = exitCode;
    }

    public PlannerExitException(int exitCode, String message, Throwable cause) {
        super(message, cause);
        this.exitCode = exitCode;
    }

    public int exitCode() {
        return exitCode;
    }
}
