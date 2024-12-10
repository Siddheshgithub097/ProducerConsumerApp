package com.assignment.producerconsumer;

public class Tracker {
    private int successCount = 0;
    private int errorCount = 0;

    public synchronized void incrementSuccessCount() {
        successCount++;
    }

    public synchronized void incrementErrorCount() {
        errorCount++;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public int getErrorCount() {
        return errorCount;
    }
}