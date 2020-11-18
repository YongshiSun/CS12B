
// --------------------------------------
// Yongshi Sun ID:yosun date:4.12.11
// Subset.java
// uses recursion to print out all 𝑘-element subsets of the 𝑛-element set {1,
// 2, 3, … , 𝑛}, where both 𝑛 and 𝑘 are given on the command line.
// --------------------------------------

class Subset {
    public static void main(String[] args) {

        // checking if usage is correct
        if (args.length >= 2) {
            String zero = args[0];
            String one = args[1];
            int n, k;

            // checking to see if inputs are int
            try {
                n = Integer.parseInt(zero);
                k = Integer.parseInt(one);
            } catch (NumberFormatException e) {
                System.out.println("Usage: Java Subset <int> <int> ");
                System.exit(0);
            }

            n = Integer.parseInt(zero);
            k = Integer.parseInt(one);

            if (n < k) {
                System.out.println("Usage: Java Subset <int> <int> ");
                System.exit(0);
            } else {

                int[] B = new int[n + 1]; // making the bit array

                for (int d = 0; d < B.length; d++) {
                    B[d] = 0;
                }

                printSubsets(B, k, 1);

            }

        } else {
            System.out.println("Usage: Java Subset <int> <int> ");
            return;
        }
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
        boolean isempty = false;

        // checking to see if the set is empty
        for (int i = 1; i < B.length; i++) {
            if (B[i] == 1) {
                isempty = true;
                break;
            }
            isempty = false;
        }

        if (isempty == false) {
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

        // making string array into string
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