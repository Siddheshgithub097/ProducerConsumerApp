package com.assignment.producerconsumer;

public class ProducerConsumerApplication 
{
	public static void main(String[] args) {
		
	
	MessageQueue queue = new MessageQueue();
    Tracker tracker = new Tracker();
    
    int totalMessages = 10;
    Thread producerThread = new Thread(new Producer(queue, totalMessages));
    Thread consumerThread = new Thread(new Consumer(queue, tracker));

       
            producerThread.start();
            consumerThread.start();

            try {
                producerThread.join(); 
                Thread.sleep(1000); 
                consumerThread.interrupt(); 

                System.out.println("Summary:");
                System.out.println("Total messages processed successfully: " + tracker.getSuccessCount());
                System.out.println("Total errors encountered: " + tracker.getErrorCount());
            } catch (InterruptedException e) {
                System.err.println("Main thread interrupted: " + e.getMessage());
            }
        }
}