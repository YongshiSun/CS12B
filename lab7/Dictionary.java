//-----------------------------------------------------------------------------
// Yongshi Sun ID:1619410
// Dictionary.java
// Binary Search Tree implementation of the Dictionary ADT
//-----------------------------------------------------------------------------
public class Dictionary implements DictionaryInterface {

    private class Node {
        String key;
        String value;
        Node left;
        Node right;

        Node(String k, String v) {
            key = k;
            value = v;
            left = null;
            right = null;
        }
    }

    // Fields for the Dictionary class
    private Node root; // reference to first Node in List
    private int numPairs; // number of items in this Dictionary

    // Dictionary()
    // constructor for the Dictionary class
    public Dictionary() {
        root = null;
        numPairs = 0;
    }

    // private functions ------------------------------------------------
    
    // findKey()
    // returns the Node containing key k in the subtree rooted at R, or returns
    // NULL if no such Node exists
    private Node findKey(Node R, String k) {
        if(R==null||Integer.parseInt(k)==Integer.parseInt(R.key)){
            return R;
        }if(Integer.parseInt(k)<Integer.parseInt(R.key)){
            return findKey(R.left,k);
        }else{
            return findKey(R.right,k);
        }
    }
    
    // findParent()
    // returns the parent of N in the subtree rooted at R, or returns NULL
    // if N is equal to R. (pre: R != NULL)
    private Node findParent(Node N, Node R) {
        Node P = null;
        if (N != R) {
            P = R;
            while (P.left != N && P.right != N) {
                if (Integer.parseInt(N.key) < Integer.parseInt(P.key)) {
                    P = P.left;
                }else{
                    P=P.right;
                }    
            }
        }
    return P;
    }

    // findLeftmost()
    // returns the leftmost Node in the subtree rooted at R, or NULL if R is NULL.
    private Node findLeftmost(Node R){
        Node L = R;
        if(L!=null){
            for( ; L.left!=null;L=L.left);
        }
        return L;
    }

    // printInOrder()
    // prints the (key, value) pairs belonging to the subtree rooted at R in order
    // of increasing keys to file pointed to by out.
    private String printInOrder(Node R){
        String print = "";
        if(R!= null){
            print += printInOrder(R.left);
            System.out.println(R.key +" "+ R.value);
            print += printInOrder(R.right);
        }
        return print;
    }
    
    // deleteAll()
    // deletes all Nodes in the subtree rooted at N.
    private void deleteAll(Node N){
        if( N!=null ){
            deleteAll(N.left);
            deleteAll(N.right);
        }
    }

    // public functions -----------------------------------------------------------

    // isEmpty()
    // pre: none
    // returns true if this Dictionary is empty, false otherwise
    @Override
    public boolean isEmpty(){
        return(numPairs == 0);
    }

    // size()
    // pre: none
    // returns the number of entries in this Dictionary
    @Override
    public int size(){
        return (numPairs);
    }

    // lookup()
    // pre: none
    // returns value associated key, or null reference if no such key exists
    @Override
    public String lookup(String key){
        Node N;
        N=findKey(root,key);
        return (N==null ? null : N.value);
    }

    // insert()
    // inserts new (key,value) pair into this Dictionary
    // pre: lookup(key)==null
    @Override
    public void insert(String key, String value) throws DuplicateKeyException{
        Node N, A, B;
        if( findKey(root, key)!=null){
            throw new DuplicateKeyException("cant insert duplicate keys");
        }
        N = new Node (key, value);
        B = null;
        A = root;
        while(A!=null){
            B = A;
            if(Integer.parseInt(key) < Integer.parseInt(A.key)){
                A = A.left;
            }else{
                A = A.right;
            }
        }
        if(B==null){
            root = N;
        }else if(Integer.parseInt(key) < Integer.parseInt(B.key)){
            B.left = N;
        }else{
            B.right = N;
        }
        numPairs++;
    }

    // delete()
    // deletes pair with the given key
    // pre: lookup(key)!=null
    @Override
    public void delete(String key) throws KeyNotFoundException{
        Node N, P, S;
         N = findKey(root, key);
        if (N==null){

                throw new KeyNotFoundException("can't delete non-existent key");
        }
        if(N.left == null && N.right == null){
            if(N==root){
                root = null;
            }else{
                P =findParent(N,root);
                if(P.right == N){
                    P.right =null;
                }else{
                    P.left = null;
                }
            }
        }else if(N.right ==null){
            if(N==root){
                root = N.left;
            }else{
                P = findParent(N,root);
                if(P.right==N){
                    P.right = N.left;
                }else{
                    P.left = N.left;
                }
            }
        }else if( N.left==null){
            if(N==root){
                root = N.right;
            }else{
                P = findParent(N, root);
                if(P.right ==N){
                    P.right = N.right;
                }else{
                    P.left = N.right;
                }
            }
        }else{
            S = findLeftmost(N.right);
            N.key = S.key;
            N.value = S.value;
            P = findParent(S,N);
            if(P.right ==S){
                P.right = S.right;
            }else{
                P.left = S.right;
            }
        }
        numPairs--;
        
    }

    // makeEmpty()
    // pre: none
    @Override
    public void makeEmpty(){
        deleteAll(root);
        root = null;
        numPairs = 0;
    }

    // toString()
    // returns a String representation of this Dictionary
    // overrides Object's toString() method
    // pre: none
    @Override
    public String toString(){
        return printInOrder(root);
    }
    
}

    

    

        
        
            
            
        
        
        
    
    
    