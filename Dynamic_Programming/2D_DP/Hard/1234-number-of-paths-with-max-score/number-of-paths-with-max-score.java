class Solution {
    private static final int[][] directions = {
        { 1, 0 }, { 0, 1 }, { 1, 1 }
    };

    private static final int MOD = (int) 1e9 + 7;

    /**
     * Approach : Using DP on Grids Tabulation (Bottom-Up) Approach
     *
     * TC : O(m x n) + O(m x n) ~ O(m x n)
     * SC : O(m x n) + O(m x n) ~ O(m x n)
     */
    public int[] pathsWithMaxScore(List<String> board) {
        int m = board.size();
        int n = board.get(0).length();
        int[][] scores = new int[m][n]; // SC : O(m x n)
        int[][] ways = new int[m][n];   // SC : O(m x n)
        /**
         * scores[i][j] denotes the maximum score from
         * cell (m - 1, n - 1) till cell (i, j)
         *
         * ways[i][j] denotes the count of maximum score from
         * cell (m - 1, n - 1) till cell (i, j)
         */
        for (int[] s : scores) { // TC : O(m)
            // adding default value to detect if cell (0, 0) is reachable or not
            Arrays.fill(s, -1);  // TC : O(n)
        }
        scores[m - 1][n - 1] = 0;
        ways[m - 1][n - 1] = 1;
        for (int i = m - 1; i >= 0; i--) {     // TC : O(m)
            for (int j = n - 1; j >= 0; j--) { // TC : O(n)
                char ch = board.get(i).charAt(j);
                if (ch == 'X') {
                    continue;
                }
                if (i == m - 1 && j == n - 1) {
                    continue;
                }
                int currentScore = ch == 'E' ? 0 : (ch - '0');
                int bestScore = -1;
                int totalWays = 0;
                for (int[] dir : directions) {
                    int i_ = i + dir[0];
                    int j_ = j + dir[1];
                    if (i_ < 0 || i_ >= m || j_ < 0 || j_ >= n) {
                        // invalid boundaries
                        continue;
                    }
                    if (board.get(i_).charAt(j_) == 'X') {
                        // cell with obstacle so should be avoided
                        continue;
                    }
                    if (scores[i_][j_] == -1) {
                        // invalid cell which is not reachable
                        continue;
                    }
                    int candidate = currentScore + scores[i_][j_];
                    if (candidate > bestScore) {
                        bestScore = candidate;
                        totalWays = ways[i_][j_];
                    } else if (candidate == bestScore) {
                        totalWays = (totalWays + ways[i_][j_]) % MOD;
                    }
                }
                if (bestScore != -1) {
                    scores[i][j] = bestScore;
                    ways[i][j] = totalWays;
                }
            }
        }
        if (scores[0][0] == -1) {
            return new int[] { 0, 0 };
        }
        return new int[] { scores[0][0], ways[0][0] };
    }
}
