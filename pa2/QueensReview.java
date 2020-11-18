//-------------------------
//Yongshi Sun ID:1619410
// Queens.java
// will find all the solutions to the n-Queens problem for 1<=n<=15
//----------------------
class QueensReview {
    public static void main(String[] args) throws NumberFormatException {
        if (args.length >= 1 && args.length < 3) {

            // checking user's last input to see if it is a number
            try {
                int n = Integer.parseInt(args[args.length - 1]);

            } catch (NumberFormatException e) {
                System.out.println("Usage: Queens [-v] number");
                System.out.println("Option: -v verbose output, print all solutions");
                System.exit(0);
            }

            // assigning user last input to n
            int n = Integer.parseInt(args[args.length - 1]);

            // creating 2D array
            int[][] B = new int[n + 1][n + 1];

            // filling array with all zeros
            for (int i = 0; i < B.length; i++) {
                for (int j = 0; j < B.length; j++) {
                    B[i][j] = 0;
                }
            }
            placeQueen(B, 1, 2);
            testBoard(B);
        }

    }

    static void placeQueen(int[][] B, int i, int j) {
        B[i][j] = 1;
        B[i][0] = j;

        // decrement for all under the queen(vertically)
        for (int k = i; k < B.length - 1; k++) {
            B[k + 1][j] -= 1; // (B[k][j] = B[k][j] - 1)
        }

        // decrement for queens left diagonal
        if (j > i) {
            int l = j - 1;
            for (int k = i + 1; k < B.length; k++) {
                B[k][l] -= 1;
                l--;
            }
        } else { // (i > j)
            int k = i + 1;
            for (int l = j - 1; l > 0; l--) {
                B[k][l] -= 1;
                k++;
            }
        }

        // decrement for queens right diagonal
        if (j < i) {
            int l = j + 1;
            for (int k = i + 1; k < B.length; k++) {
                B[k][l] -= 1;
                l++;
            }
        } else { // (i<j)
            int k = i + 1;
            for (int l = j + 1; l < B.length; l++) {
                B[k][l] -= 1;
                k++;
            }
        }
    }

    static void removeQueen(int[][] B, int i, int j) {
        B[i][j] = 0;
        B[i][0] = 0;

        // increment queen's vertical
        for (int k = i; k < B.length - 1; k++) {
            B[k + 1][j] += 1; // (B[k][j] = B[k][j] + 1)
        }

        // increment queen's left diagonal
        if (j > i) {
            int l = j - 1;
            for (int k = i + 1; k < B.length; k++) {
                B[k][l] += 1;
                l--;
            }
        } else { // (i > j)
            int k = i + 1;
            for (int l = j - 1; l > 0; l--) {
                B[k][l] += 1;
                k++;
            }
        }

        // increment queen's right diagonal
        if (j < i) {
            int l = j + 1;
            for (int k = i + 1; k < B.length; k++) {
                B[k][l] += 1;
                l++;
            }
        } else { // (i<j)
            int k = i + 1;
            for (int l = j + 1; l < B.length; l++) {
                B[k][l] += 1;
                k++;
            }
        }

    }

    static void printBoard(int[][] B) {
        System.out.print("(");
        for (int h = 1; h < B.length; h++) {
            if (h == B.length - 1) {
                System.out.print(B[h][0]);
                break;
            }
            System.out.print(B[h][0] + ", ");

        }
        System.out.println(")");
    }

    static int findSolutions(int[][] B, int i, String mode) {
        String o = "-v";
        int sum = 0;

        if (i > B.length - 1) { // a solution have been found
            if (o.compareTo(mode) == 0) {
                printBoard(B);
            } else if (mode == "") {
                System.out.println(B.length - 1 + "-Queens has " + sum + " solutions");

            } else {
                System.out.println("Usage: Queens [-v] number");
                System.out.println("Option: -v verbose output, print all solutions");
            }
            return 1;

        } else { // looking for solution
            for (int j = 1; j < B.length; j++) {
                if (B[i][j] == 0) {
                    placeQueen(B, i, j);
                    // testBoard(B);
                    // System.out.println("place");
                    sum = sum + findSolutions(B, i + 1, mode);
                    removeQueen(B, i, j);
                }
            }

        }
        return sum;
    }

    static void testBoard(int[][] B) {
        System.out.println();
        for (int h = 0; h < B.length; h++) {
            for (int g = 0; g < B.length; g++) {
                System.out.print(B[h][g] + ",  ");
            }
            System.out.println();
        }
    }
}