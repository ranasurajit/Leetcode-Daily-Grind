class Solution {

    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        int count = 0;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        // Store cell as a single integer: r * n + c
        int[] stack = new int[m * n];

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {

                // Water or already visited
                if (grid[r][c] == 0) {
                    continue;
                }

                long sum = 0;

                int top = 0;
                stack[top++] = r * n + c;

                // Mark visited
                int startValue = grid[r][c];
                grid[r][c] = 0;

                sum += startValue;

                while (top > 0) {
                    int cell = stack[--top];

                    int row = cell / n;
                    int col = cell % n;

                    for (int d = 0; d < 4; d++) {
                        int nr = row + dr[d];
                        int nc = col + dc[d];

                        if (nr >= 0 && nr < m &&
                            nc >= 0 && nc < n &&
                            grid[nr][nc] > 0) {

                            sum += grid[nr][nc];

                            // Mark visited immediately
                            grid[nr][nc] = 0;

                            stack[top++] = nr * n + nc;
                        }
                    }
                }

                if (sum % k == 0) {
                    count++;
                }
            }
        }

        return count;
    }
}
