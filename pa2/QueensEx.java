class Queens {
    public static void main(String[] args) throws NumberFormatException {
        int[][] B = { { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 } };
        placeQueen(B, 2, 5);
        testBoard(B);
    }

    static void testBoard(int[][] B) {
        System.out.println();
        for (int h = 0; h < B.length - 1; h++) {
            for (int g = 0; g < B.length - 1; g++) {
                System.out.print(B[h][g] + ",  ");
            }
            System.out.println();
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
            for (int k = i + 1; k < B.length && l > 0; k++) {
                B[k][l] -= 1;
                l--;
            }
        } else { // (i > j)
            int k = i + 1;
            for (int l = j - 1; l > 0 && k < B.length; l--) {
                B[k][l] -= 1;
                k++;
            }
        }

        // decrement for queens right diagonal
        if (j > i) {
            int l = j + 1;
            for (int k = i + 1; k < B.length && l > B.length; k++) {
                B[k][l] -= 1;
                l++;
            }
        } else { // (i>j)
            int k = i + 1;
            for (int l = j + 1; l < B.length && k < B.length; l++) {
                B[k][l] -= 1;
                k++;
            }
        }
    }
}