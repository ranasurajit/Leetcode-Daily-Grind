class Solution {
    private static final int[][] directions = {
        { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 }
    };

    private int m;
    private int n;

    /**
     * Approach : Using Backtracking Approach
     *
     * TC : O(m x n x 3 ^ l))
     * SC : O(l)
     * - O(l) - recursion stack
     *
     * where l = size(word)
     */
    public boolean exist(char[][] board, String word) {
        this.m = board.length;
        this.n = board[0].length;
        for (int i = 0; i < m; i++) {     // TC : O(m)
            for (int j = 0; j < n; j++) { // TC : O(n)
                if (board[i][j] == word.charAt(0)) {
                    // we have the 1st match so we can explore if further
                    if (backtrack(i, j, board, 0, word)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Using Backtracking Approach
     *
     * TC : O(3 ^ l)
     * - at most we explore 3 directions from any cell
     *   (excluded the direction it came from)
     * SC : O(l)
     */
    private boolean backtrack(int i, int j, char[][] board, int idx, String word) {
        // Base Case
        if (idx == word.length()) {
            return true;
        }
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] == '$' ||
            board[i][j] != word.charAt(idx)) {
            return false;
        }
        // Recursion Calls
        char temp = board[i][j];
        board[i][j] = '$';
        for (int[] dir : directions) { // TC : O(4)
            int effI = i + dir[0];
            int effJ = j + dir[1];
            if (backtrack(effI, effJ, board, idx + 1, word)) {
                return true;
            }
        }
        board[i][j] = temp;
        return false;
    }
}
