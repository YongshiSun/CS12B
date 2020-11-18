//-----------------------------------------------------------------------------
// Yongshi Sun ID:1619410
// QueueTest.java
// A test client for the Queue ADT
//-----------------------------------------------------------------------------

public class QueueTest{

    public static void main(String[] args){

        //creating 2 linked list
        Queue A = new Queue();
        Queue B = new Queue();        

        //testing isEmpty() and enqueue()
        A.enqueue(1);
        A.enqueue("ten");
        System.out.println("A.isEmpty() = " + A.isEmpty());
        System.out.println("B.isEmpty() = " + B.isEmpty());
        System.out.println("--------------------------------");

        //testing length() and dequeue()
        System.out.println("A.length() = " + A.length());
        A.dequeue();
        System.out.println(A);
        System.out.println("--------------------------------");

        // testing peek() and dequeueAll()
        B.enqueue(1);
        B.enqueue(7);
        B.enqueue(1);
        B.enqueue(4);
        System.out.println("B.isEmpty() = " + B.isEmpty());
        System.out.println("B.peek() = " + B.peek());
        B.dequeue();
        B.dequeue();
        A.enqueue(B.dequeue());
        System.out.println(A);
        System.out.println("B.peek() = " + B.peek());
        B.dequeueAll();
        System.out.println("B.isEmpty() = " + B.isEmpty());
        System.out.println("--------------------------------");

    }
}