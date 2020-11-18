// -----------------------------------------------------------------------------
// Yongshi Sun ID:1619410
// Linked List implementation of the Dictionary ADT
// -----------------------------------------------------------------------------
public class Dictionary implements DictionaryInterface {

    private class Node {
        String key;
        String value;
        Node next;

        Node(String x, String y) {
            key = x;
            value = y;
            next = null;
        }
    }

    // Fields for the Dictionary class
    private Node head; // reference to first Node in List
    private int numItems; // number of items in this Dictionary

    // Dictionary()
    // constructor for the Dictionary class
    public Dictionary() {
        head = null;
        numItems = 0;
    }
    // private helper functions----------------------------------------------

    // findKey
    // returns a reference to the Node at position key in this Dictionary
    private Node findKey(String key) {
        Node N = head;
        for (; N != null; N = N.next) {
            if (N.key.equals(key)) {
                return N;
            }
        }
        return null;
    }

    // ADT operations-------------------------------------------------------

    // isEmpty
    // pre: none
    // post: returns true if the Dictionary contains no pairs, false otherwise
    public boolean isEmpty() {
        return (numItems == 0);
    }

    // size
    // pre: none
    // post: returns the number of (key,value) pairs in the Dictionary
    public int size() {
        return numItems;
    }

    // lookup
    // pre:lookup(key)==null
    // post:If the Dictionary contains a pair whose key field matches the argument
    // key, lookup returns the associated value field. If no such pair exists in the
    // Dictionary, a null reference is returned.
    public String lookup(String key) {
        Node N;
        N = findKey(key);
        if (N == null) {
            return null;
        } else {
            return N.value;
        }
    }

    // insert
    // pre: the Dictionary does not currently contain the argument key. (AKA
    // ookup(key)!=null)
    // post: the pair (key, value) is added to the Dictionary. If such a pair does
    // exist, a DuplicateKeyException will be thrown.
    public void insert(String key, String value) throws DuplicateKeyException {
        if (lookup(key) != null) {
            throw new DuplicateKeyException("cannot insert dulipcate keys.");
        }

        Node K = new Node(key, value);

        // if list is empty then make a node in it
        if (isEmpty()) {
            head = K;
        } else {
            Node G = head;
            while (G.next != null) {
                G = G.next;
            }
            G.next = K;
        }

        numItems++;

    }

    // delete
    // pre: the Dictionary currently contains the argument key.
    // post: pair is removed from the Dictionary. If no such pair exists, then a
    // KeyNotFoundException is thrown.
    public void delete(String key) throws KeyNotFoundException {

        if (lookup(key) == null || findKey(key) == null) {
            throw new KeyNotFoundException("cannot delete non-existent key");
        } else {

            Node N = findKey(key);
            Node P = head;
            String one = "1";

            // if linked list is empty
            if (P == null) {
                return;
            }

            // if key is the head
            if (P.next == null) {
                head = P.next;
                numItems--;
                return;
            }

            // if key is the first node
            if (P.key.equals(key)) {
                head = head.next;
                numItems--;
                return;
            }

            while (P.next != N) {
                if (P.next == null) {
                    return;
                }
                P = P.next;
            }
            P.next = N.next;
            N = null;

        }
        numItems--;
    }

    // makeEmpty
    // pre: none
    // post:Resets the Dictionary to the empty state.
    public void makeEmpty() {
        head = null;
        numItems = 0;
    }

    // toString
    // pre: none
    // post: Returns a String representation of the current state of the Dictionary.
    public String toString() {
        StringBuffer sb = new StringBuffer();
        Node N = head;

        for (; N != null; N = N.next) {
            sb.append(N.key).append(" ").append(N.value);
            sb.append(System.lineSeparator());
        }
        return new String(sb);
    }

}