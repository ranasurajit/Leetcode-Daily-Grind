class Solution {
    private static final int[][] directions = {
        { 1, 0 },
        { -1, 0 },
        { 0, 1 },
        { 0, -1 }
    };

    /**
     * Approach : Using BFS + Bit-Masking Approach
     * 
     * TC : O(m × n × E × 2 ^ L)
     * SC : O(m × n × E × 2 ^ L) + O(m x n) ~ O(m × n × E × 2 ^ L)
     */
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        int litters = 0;
        int sr = -1;
        int sc = -1;
        int[][] litterId = new int[m][n]; // SC : O(m x n)
        int litterCount = 0;
        for (int i = 0; i < m; i++) { // TC : O(m)
            Arrays.fill(litterId[i], -1);
            for (int j = 0; j < n; j++) { // TC : O(n)
                char ch = classroom[i].charAt(j);
                if (ch == 'S') {
                    sr = i;
                    sc = j;
                } else if (ch == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }
        int fullMask = (1 << litterCount) - 1;
        boolean[][][][] visited = 
            new boolean[m][n][energy + 1][1 << litterCount]; // SC : O(m x n x k)
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(sr, sc, energy, litters, 0));
        visited[sr][sc][energy][0] = true;
        while (!queue.isEmpty()) { // TC : O(m x n)
            Pair current = queue.poll();
            int cr = current.row;
            int cc = current.col;
            int ce = current.energyLeft;
            int cl = current.litterMask;
            int cm = current.moves;
            if (cl == fullMask) {
                // all litters collected
                return cm;
            }
            if (ce == 0) {
                // continue with other explorations
                continue;
            }
            for (int[] dir : directions) { // TC : O(4)
                int nr = cr + dir[0];
                int nc = cc + dir[1];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    // invalid bounds
                    continue;
                }
                if (classroom[nr].charAt(nc) == 'X') {
                    // obstacle
                    continue;
                }
                if (ce == 0) {
                    // no energy left to move further
                    continue;
                }
                int newEnergy = ce - 1;
                if (classroom[nr].charAt(nc) == 'R') {
                    // energy is reset to full capacity
                    newEnergy = energy;
                }
                int newMask = cl;
                if (classroom[nr].charAt(nc) == 'L') {
                    // collect the litter
                    int lId = litterId[nr][nc];
                    newMask = (newMask | (1 << lId));
                }
                if (visited[nr][nc][newEnergy][newMask]) {
                    continue;
                }
                visited[nr][nc][newEnergy][newMask] = true;
                queue.offer(new Pair(nr, nc, newEnergy, newMask, cm + 1));
            }
        }
        return -1;
    }
}

class Pair {
    int row;
    int col;
    int energyLeft;
    int litterMask;
    int moves;

    public Pair (int row, int col, int energyLeft, int litterMask, int moves) {
        this.row = row;
        this.col = col;
        this.energyLeft = energyLeft;
        this.litterMask = litterMask;
        this.moves = moves;
    }
}
