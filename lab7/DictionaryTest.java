// -----------------------------------------------------------------------------
// Yongshi Sun ID:1619410
// A test client for the Dictionary ADT
// ----------------------------------------------------------------------------

public class DictionaryTest {

    public static void main(String[] args) {
        Dictionary A = new Dictionary();
        Dictionary B = new Dictionary();

        System.out.println("isEmpty: " + A.isEmpty());
        B.insert("1", "b");
        B.insert("2", "a");
        B.insert("3", "g");
        System.out.println("isEmpty: " + B.isEmpty());
        System.out.println(B);
        System.out.println("Size: " + B.size());
        String v = B.lookup("3");
        System.out.println("key=1 " + (v == null ? "not found" : ("value=" + v)));
        B.delete("1");
        System.out.println(B);
        System.out.println("Size: " + B.size());
        B.insert("4", "c");
        System.out.println(B);
        B.makeEmpty();
        System.out.println("isEmpty: " + B.isEmpty());

    }
}
 
 
 