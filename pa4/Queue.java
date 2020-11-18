//-----------------------------------------------------------------------------
// Yongshi Sun ID:1619410
// Queue.java
// linked list based implementation of Queue ADT with Exceptions & Interface
//-----------------------------------------------------------------------------

public class Queue implements QueueInterface {

    // private inner Node class
    private class Node {
        Object item;
        Node next;

        Node(Object x) {
            item = x;
            next = null;
        }
    }

    // Fields for the Queue class
    private Node head; // reference to first Node in Queue
    private Node tail; // reference to last Node in Queue
    private int numItems; // number of items in this Queue

    // Queue()
    // constructor for the Queue class
    public Queue() {
        head = null;
        tail = null;
        numItems = 0;
    }

    // ADT operations ----------------------------------------------------------

    // isEmpty()
    // pre: none
    // post: returns true if this Queue is empty, false otherwise
    @Override
    public boolean isEmpty() {
        return (numItems == 0);
    }

    // length()
    // pre: none
    // post: returns the length of this Queue.
    @Override
    public int length() {
        return numItems;
    }

    // enqueue()
    // adds newItem to back of this Queue
    // pre: none
    // post: !isEmpty()
    @Override
    public void enqueue(Object newItem) {
        Node N = new Node(newItem);
        if (isEmpty()) {
            N.next = head;
            head = N;
            tail = N;
        } else {
            tail.next = new Node(newItem);
            tail = tail.next;
            tail.next = null;
        }
        numItems++;
    }

    // dequeue()
    // deletes and returns item from front of this Queue
    // pre: !isEmpty()
    // post: this Queue will have one fewer element
    @Override
    public Object dequeue() throws QueueEmptyException {
        Object D = head.item;
        if (isEmpty()) {
            throw new QueueEmptyException("Queue Error: Can't dequeue an empty Queue");
        } else {
            if (head == tail) {
                dequeueAll();
            } else {
                Node P = head.next;
                Node K = head;
                head = P;
                K.next = null;

            }
        }
        numItems--;
        return D;
    }

    // peek()
    // pre: !isEmpty()
    // post: returns item at front of Queue
    @Override
    public Object peek() throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException("Queue Error: Can't dequeue an empty Queue");
        } else {
            return head.item;
        }
    }

    // dequeueAll()
    // sets this Queue to the empty state
    // pre: !isEmpty()
    // post: isEmpty()
    @Override
    public void dequeueAll() throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException("Queue Error: Can't dequeue an empty Queue");
        } else {
            head = null;
            numItems = 0;
        }
    }

    // toString()
    // overrides Object's toString() method
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        Node N = head;

        for (; N != null; N = N.next) {
            sb.append(N.item).append(" ");
        }
        return new String(sb);
    }

}