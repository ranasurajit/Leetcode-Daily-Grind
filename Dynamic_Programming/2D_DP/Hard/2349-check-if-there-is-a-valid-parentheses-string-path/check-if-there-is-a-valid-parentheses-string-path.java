//Approach-1 (Top Down / Recursion + Memoization)
//T.C : O(m*n*(m+n))
//S.C : O(m*n*(m+n))
class Solution {
    int m, n;
    int[][][] t;

    public boolean solve(int i, int j, int openCount, char[][] grid) {
        openCount += (grid[i][j] == '(') ? 1 : -1;

        if (openCount < 0)
            return false;

        if (t[i][j][openCount] != -1) {
            return t[i][j][openCount] == 1;
        }

        if (i == m - 1 && j == n - 1) {
            t[i][j][openCount] = (openCount == 0) ? 1 : 0;
            return openCount == 0;
        }

        // move down
        if (i + 1 < m) {
            if (solve(i + 1, j, openCount, grid)) {
                t[i][j][openCount] = 1;
                return true;
            }
        }

        // move right
        if (j + 1 < n) {
            if (solve(i, j + 1, openCount, grid)) {
                t[i][j][openCount] = 1;
                return true;
            }
        }

        t[i][j][openCount] = 0;
        return false;
    }

    public boolean hasValidPath(char[][] grid) {
        m = grid.length;
        n = grid[0].length;

        if ((m + n - 1) % 2 == 1)
            return false;

        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(')
            return false;

        t = new int[m][n][201];
        for (int[][] row : t)
            for (int[] col : row)
                Arrays.fill(col, -1);

        return solve(0, 0, 0, grid);
    }
}
