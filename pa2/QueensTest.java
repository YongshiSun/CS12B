class QueensTest {
    public static void main(String[] args) throws NumberFormatException {
        int[][] B = { { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 } };

        // int[][] B = { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }
        // };
        testBoard(B);
        // placeQueen(B, 1, 2);
        // testBoard(B);

    }

    static void placeQueen(int[][] B, int i, int j) {
        B[i][j] = 1;
        B[i][0] = j;

        // decrement for all under the queen(vertically)
        for (int k = i; k < B.length - 1; k++) {
            B[k + 1][j] -= 1; // (B[k][j] = B[k][j] - 1)
        }

        // decrement for queens left diagonal
        if (j < i) {
            int l = j - 1;
            for (int k = i + 1; k < B.length; k++) {
                if (l <= 0 || k >= B.length) {
                    System.out.println("no good");
                    return;
                } else {
                    B[k][l] -= 1;
                    l--;
                }

            }
        } else { // (i < j)
            int k = i + 1;
            for (int l = j - 1; l > 0; l--) {
                if (l <= 0 || k >= B.length) {
                    System.out.println("also no good");
                    return;
                } else {
                    B[k][l] -= 1;
                    k++;

                }
            }
        }

        // decrement for queens right diagonal
        if (j < i) {
            int l = j + 1;
            for (int k = i + 1; k < B.length; k++) {
                if (l >= B.length || k >= B.length) {
                    System.out.println("no no good");
                    return;
                } else {
                    B[k][l] -= 1;
                    l++;

                }
            }
        } else { // (i < j)
            int k = i + 1;
            for (int l = j + 1; l < B.length - 1; l++) {
                if (k >= B.length || l >= B.length) {
                    System.out.println("sad no good");
                    return;
                } else {
                    System.out.println("k= " + k);
                    System.out.println("l= " + l);
                    B[k][l] -= 1;
                    k++;

                }
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
        return -1;
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