// --------------------------------------
// Yongshi Sun ID:yosun date:4.12.11
// Subset.java
// uses recursion to print out all 𝑘-element subsets of the 𝑛-element set {1,
// 2, 3, … , 𝑛}, where both 𝑛 and 𝑘 are given on the command line.
// --------------------------------------

class SubsetTest {
    public static void main(String[] args) {

        int[] B = new int[] { 0, 0, 0, 0, 0 };
        printSubsets(B, 2, 1);

    }

    // functions
    static String setToString(int[] B) {
        int numOfOnes = 0;

        // count how many ones in bit array
        for (int i = 1; i < B.length; i++) {
            if (B[i] == 1) {
                numOfOnes++;
            }
        }

        String[] S = new String[numOfOnes];
        String result;
        int index = 0;
        int Sindex = 0;
        boolean isempty = true;

        // checking to see if the set is empty
        for (int i = 1; i < B.length; i++) {
            if (B[i] == 1) {
                isempty = false;
                break;
            }
            isempty = true;
        }

        if (isempty == true) {
            // System.out.println("{ }");
            return result = "{ }";
        }

        // converting bit array to string array
        for (int i = 1; i < B.length; i++) {
            if (B[i] == 0) {
                index++;
            } else {
                index++;
                S[Sindex] = Integer.toString(index);
                Sindex++;
            }
        }

        // making string array into a string
        result = "{";
        for (

                int i = 0; i < S.length; i++) {
            if (i == S.length - 1) {
                result = result + S[i];
            } else {
                result = result + S[i] + ", ";
            }
        }
        return result = result + "}";

    }

    static void printSubsets(int[] B, int k, int i) {

        if (k > 0) {

            if (i > (B.length - 1)) {
                return;
            }

            B[i] = 1;
            printSubsets(B, k - 1, i + 1);

            B[i] = 0;
            printSubsets(B, k, i + 1);

        }

        if (k == 0) {
            System.out.println(setToString(B));
            return;
        }

    }

}