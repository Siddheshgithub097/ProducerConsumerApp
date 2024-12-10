package com.assignment.producerconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageQueue {
    private BlockingQueue<String> queue;

    public MessageQueue() {
        this.queue = new LinkedBlockingQueue<>();
    }

    public void addMessage(String message) throws InterruptedException {
        queue.put(message);
    }

    public String getMessage() throws InterruptedException {
        return queue.take();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}