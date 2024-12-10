package com.assignment.producerconsumer;

public class Consumer implements Runnable {
    private final MessageQueue queue;
    private final Tracker tracker;
    private volatile boolean running = true;

    public Consumer(MessageQueue queue, Tracker tracker) {
        this.queue = queue;
        this.tracker = tracker;
    }

    @Override
    public void run() {
        try {
            while (running || !queue.isEmpty()) 
            {   
                String message = queue.getMessage();
                if (message == null) {
                    break;
                }

                if (processMessage(message)) {
                    tracker.incrementSuccessCount();
                    System.out.println("Processed successfully: " + message);
                } else {
                    tracker.incrementErrorCount();
                    System.out.println("Processing failed for: " + message);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Consumer interrupted: " + e.getMessage());
        }
    }

    private boolean processMessage(String message) {
        return Math.random() > 0.2; 
    }

    public void stop() {
        running = false;  
    }
}