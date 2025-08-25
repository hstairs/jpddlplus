package com.hstairs.ppmajal.extraUtils;

public enum ExternalLoggerLogType {
    Generating,
    Expanding,
    Closing;

    @Override
    public String toString() {
        return name().toLowerCase();
    } 
}
