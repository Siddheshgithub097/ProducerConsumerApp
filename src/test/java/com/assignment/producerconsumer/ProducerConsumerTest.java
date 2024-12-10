package com.assignment.producerconsumer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProducerConsumerTest {

    @Test
    void testSuccessfulProcessing() throws InterruptedException {
       
        MessageQueue queue = new MessageQueue();
        Tracker tracker = new Tracker();
        
     
        Producer producer = new Producer(queue, 5);
        Consumer consumer = new Consumer(queue, tracker);

        
        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);
        producerThread.start();
        consumerThread.start();

       
        producerThread.join();

        
        Thread.sleep(1000);
        consumerThread.interrupt();

        assertTrue(queue.isEmpty(), "Queue should be empty after processing all messages");

       
        assertEquals(5, tracker.getSuccessCount(), "Number of successful processes should be 5");

        
        assertEquals(0, tracker.getErrorCount(), "Error count should be 0");
    }

    
    
    @Test
    void testFailureProcessing() throws InterruptedException {
        MessageQueue queue = new MessageQueue();
        Tracker tracker = new Tracker();

        Producer producer = new Producer(queue, 5);
        
        Consumer consumer = new Consumer(queue, tracker) {
            protected boolean processMessage(String message) {
                return false;
            }
        };

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();

        assertTrue(tracker.getSuccessCount() == 0, "Success count should be 0");
        assertEquals(5, tracker.getErrorCount(), "Error count should be 5");
        assertEquals(5, tracker.getSuccessCount() + tracker.getErrorCount(),
                     "Total processed messages should equal success + error counts");
    }

}