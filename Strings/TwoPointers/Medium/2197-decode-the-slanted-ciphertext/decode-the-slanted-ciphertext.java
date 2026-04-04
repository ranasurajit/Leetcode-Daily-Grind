class Solution {
    /**
     * Approach II : Using Optimal (Two Pointers) Approach
     *
     * TC: O(n x (m + n)) + O(m x n) ~ O(m x n) ~ O(N)
     * SC: O(m x n) ~ O(N)
     * where N = len(encodedText) = m x n
     */
    public String decodeCiphertext(String encodedText, int rows) {
        if (rows == 1 || encodedText.isEmpty()) {
            return encodedText;
        }
        int n = encodedText.length() / rows;
        /**
         * we will not be generating matrix 'mat' at all
         * but we will be using the indices instead
         */
        StringBuilder sb = new StringBuilder(); // SC: O(m x n)
        int col = 0;
        while (col < n) { // TC: O(n)
            int p = 0;
            int q = col;
            while (p < rows && q < n) { // TC: O(m + n)
                // mat[p][q] = encodedText.charAt(p * n + q);
                sb.append(encodedText.charAt(p * n + q));
                p++;
                q++;
            }
            col++;
        }
        while (sb.charAt(sb.length() - 1) == ' ') { // TC: O(m x n)
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * Approach I : Using Brute-Force (Two Pointers) Approach
     *
     * TC: O(m x n) + O(n x (m + n)) + O(m x n) ~ O(m x n) ~ O(N)
     * SC: O(m x n) + O(m x n) ~ O(N)
     * where N = len(encodedText) = m x n
     */
    public String decodeCiphertextBruteForce(String encodedText, int rows) {
        if (rows == 1 || encodedText.isEmpty()) {
            return encodedText;
        }
        int m = rows;
        int n = encodedText.length() / m;
        char[][] mat = new char[m][n];    // SC: O(m x n)
        for (int i = 0; i < m; i++) {     // TC: O(m)
            for (int j = 0; j < n; j++) { // TC: O(n)
                mat[i][j] = encodedText.charAt(i * n + j);
            }
        }
        StringBuilder sb = new StringBuilder(); // SC: O(m x n)
        int col = 0;
        while (col < n) { // TC: O(n)
            int p = 0;
            int q = col;
            while (p < m && q < n) { // TC: O(m + n)
                sb.append(mat[p][q]);
                p++;
                q++;
            }
            col++;
        }
        int idx = sb.length() - 1;
        while (idx > 0 && sb.charAt(idx) == ' ') { // TC: O(m x n)
            sb.setLength(sb.length() - 1);
            idx--;
        }
        return sb.toString();
    }
}
