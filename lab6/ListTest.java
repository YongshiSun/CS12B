//-----------------------------------------------------------------------------
// ListTest.java
// Test the List ADT
//-----------------------------------------------------------------------------

class ListTest{

     public static void main(String[] args){

     //testing add, remove, and  size
     List<String> A = new List<String>();
     A.add(1,"happy");
     A.add(2,"dog");
     A.add(3,"ghost");
     System.out.println(A);
     A.remove(1);
     System.out.println(A);
     System.out.println(A.size()); 

     //testing isEmpty() and get()
     List<Double> B = new List<Double>(); 
     System.out.println(B.isEmpty());
     B.add(1, 7.9);
     B.add(2, 8.9);
     System.out.println(B.isEmpty());
     System.out.println(B); 
     System.out.println(B.get(1));

     // testing remove all
     B.removeAll();
     System.out.println(B.isEmpty());
      
    }

}