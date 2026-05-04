package com.library.library_management;

public class WaitlistQueue {

    private class WaitlistNode {
        Request data;
        WaitlistNode next;

        public WaitlistNode(Request data) {
            this.data = data;
            this.next = null;
        }
    }

    private WaitlistNode front;
    private WaitlistNode rear;

    public WaitlistQueue() {
        front = null;
        rear = null;
    }

    public boolean isEmpty() {
        return front == null;
    }

    //Enqueue    O(1)
    public void enqueue(Request r) {
        WaitlistNode newNode = new WaitlistNode(r);

        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        }
        else {
            rear.next = newNode;
            rear = newNode;
        }

        System.out.println("Request added to waitlist.");
    }

    //Dequeue     O(1)
    public Request dequeue() {
        if (isEmpty()) {
            System.out.println("Waitlist is empty.");
            return null;
        }

        Request removed = front.data;
        front = front.next;

        if (front == null) {
            rear = null;
        }

        return removed;
    }

    //Check who is first on the waitlist     O(1)
    public Request peek() {
        if (isEmpty()) {
            System.out.println("Waitlist is empty.");
            return null;
        }

        return front.data;
    }

    //Display all requests     O(n)
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("Waitlist is empty.");
            return;
        }

        WaitlistNode current = front;

        while (current != null) {
            current.data.displayRequest();
            current = current.next;
        }
    }
}
