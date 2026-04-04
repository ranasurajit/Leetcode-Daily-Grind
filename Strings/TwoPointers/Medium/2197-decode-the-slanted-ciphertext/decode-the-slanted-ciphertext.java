class Solution {
    /**
     * Approach I : Using Two Pointers Approach
     *
     * TC: O(m x n) + O(n x (m + n)) + O(m x n) ~ O(m x n)
     * SC: O(m x n)
     */
    public String decodeCiphertext(String encodedText, int rows) {
        int m = rows;
        int n = encodedText.length() / m;
        char[][] mat = new char[m][n];    // SC: O(m x n)
        for (int i = 0; i < m; i++) {     // TC: O(m)
            for (int j = 0; j < n; j++) { // TC: O(n)
                mat[i][j] = encodedText.charAt(i * n + j);
            }
        }
        System.out.println(Arrays.deepToString(mat));
        StringBuilder sb = new StringBuilder();
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
