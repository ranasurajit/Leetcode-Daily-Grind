class Solution {
    private int m;
    private int n;
    private static final int[][] directions = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

    /**
     * Approach : Using Backtracking Approach
     *
     * TC: O(M x N x 3 ^ L)
     * from every cell we go to 3 directions 
     * (1 direction is negated as we came from that)
     * 
     * SC: O(L), where L = size(word)
     */
    public boolean exist(char[][] board, String word) {
        this.m = board.length;
        this.n = board[0].length;
        for (int i = 0; i < m; i++) {     // TC: O(M)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (board[i][j] == word.charAt(0) && 
                    backtrack(i, j, 0, word, board)) { // TC: O(3 ^ L), SC: O(L)
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Using Backtracking Approach
     *
     * TC: O(M x N x 3 ^ L)
     * from every cell we go to 3 directions 
     * (1 direction is negated as we came from that)
     * 
     * SC: O(L), where L = size(word)
     */
    private boolean backtrack(int i, int j, int idx, String word, char[][] board) {
        // Base Case
        if (idx == word.length()) {
            // we reached the end of word so all characters matches
            return true;
        }
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] == '$' || 
            board[i][j] != word.charAt(idx)) {
            // invalid cases (out of bound, already visited cell or no match)
            return false;
        }
        // Recursion Calls
        // here board[i][j] matches word.charAt(idx)
        char temp = board[i][j]; // storing the value to backtrack later
        board[i][j] = '$'; // marking cell as visited
        for (int[] dir : directions) { // TC: O(4)
            int effX = i + dir[0];
            int effY = j + dir[1];
            if (backtrack(effX, effY, idx + 1, word, board)) {
                return true;
            }
        }
        board[i][j] = temp;
        return false;
    }
}
