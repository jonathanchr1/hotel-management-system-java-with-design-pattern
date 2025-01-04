package model;

public class Queue {
    String queueItems[] = new String[7];
    String[] temp = new String[7];
    int front = 0, end = 0;
    
    private static Queue queueInstance;

    private Queue() {
        for (int x = 0; x < queueItems.length; x++) {
            queueItems[x] = "e";
        }
    }
    
    public static Queue getQueueInstance() {
    	if (queueInstance == null) {
    		queueInstance = new Queue();
    	}
    	return queueInstance;
    }

    public void addToQueue(String name) {
        try {
            queueItems[end] = name;
            end++;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Queue Overload!!!!");
            takeFromQueue();
            queueItems[6] = name;
        }
    }

    public void takeFromQueue() {
        try {
            if (end > front && !queueItems[front].equals("e")) {
                System.out.println("Customer Name: " + queueItems[front]);
                System.arraycopy(queueItems, 1, temp, 0, queueItems.length - 1);
                System.arraycopy(temp, 0, queueItems, 0, temp.length);
            } else {
                System.out.println("Queue is Empty");
                end = 0;
            }
        } catch(NullPointerException e){
            System.out.println("Empty Queue");
        }
    }

    public void displayQueue() {
        takeFromQueue();
        takeFromQueue();
        takeFromQueue();
        System.out.println("");
    }
}