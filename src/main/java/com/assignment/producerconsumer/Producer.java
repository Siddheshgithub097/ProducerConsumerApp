package com.assignment.producerconsumer;

public class Producer implements Runnable {
    private final MessageQueue messageQueue;
    private final int totalMessages;

    public Producer(MessageQueue messageQueue, int totalMessages) {
        this.messageQueue = messageQueue;
        this.totalMessages = totalMessages;
    }

    @Override
    public void run() 
    {
        try 
        {
            for (int i = 0; i < totalMessages; i++) 
            {
                String message = "Message " + (i + 1);
                messageQueue.addMessage(message);
                System.out.println("Produced: " + message);
                Thread.sleep(500); 
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Producer interrupted: " + e.getMessage());
        }
    }
}
 