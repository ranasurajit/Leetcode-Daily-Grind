class Solution {
    private int n;
    private int m;
    private List<Integer> robot;
    private List<Integer> factories;
    /**
     * Approach IV : Using Space Optimization (Optimized-DP) Approach
     *
     * TC: O(n x m) + O(n x log(n)) + O(m x log(m)) ~ O(n x m)
     * SC: O(m) + O(m) ~ O(m)
     * - O(m) - next and current array memory
     *
     * Accepted (40 / 40 testcases passed)
     */
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        this.n = robot.size();
        this.robot = robot;
        Collections.sort(robot);                     // TC: O(n x log(n))
        Arrays.sort(factory, (a, b) -> a[0] - b[0]); // TC: O(m x log(m))
        this.factories = new ArrayList<>();
        for (int[] f : factory) {
            int pos = f[0];
            int freq = f[1];
            for (int j = 0; j < freq; j++) {
                factories.add(pos);
            }
        }
        this.m = factories.size();
        // i = robot position, j = factory position
        long[] next = new long[m + 1]; // SC: O(m)
        // Initialization
        Arrays.fill(next, 0L);
        // Iterative Calls
        for (int i = n - 1; i >= 0; i--) { // TC: O(n)
            long[] current = new long[m + 1]; // SC: O(m)
            current[m] = (long) 1e12;
            for (int j = m - 1; j >= 0; j--) { // TC: O(m)
                long skip = current[j + 1];
                long pick = Math.abs((long) robot.get(i) -
                    (long) factories.get(j)) + next[j + 1];
                current[j] = Math.min(skip, pick);
            }
            next = current.clone();
        }
        return next[0];
    }

    /**
     * Approach III : Using Tabulation (Bottom-Up) Approach
     *
     * TC: O(n x m) + O(n x log(n)) + O(m x log(m)) + O(n) + O(m) ~ O(n x m)
     * SC: O(n x m)
     * - O(n x m) - dp array memory
     *
     * Accepted (40 / 40 testcases passed)
     */
    public long minimumTotalDistanceTabulation(List<Integer> robot,
        int[][] factory) {
        this.n = robot.size();
        this.robot = robot;
        Collections.sort(robot);                     // TC: O(n x log(n))
        Arrays.sort(factory, (a, b) -> a[0] - b[0]); // TC: O(m x log(m))
        this.factories = new ArrayList<>();
        for (int[] f : factory) {
            int pos = f[0];
            int freq = f[1];
            for (int j = 0; j < freq; j++) {
                factories.add(pos);
            }
        }
        this.m = factories.size();
        // i = robot position, j = factory position
        long[][] dp = new long[n + 1][m + 1]; // SC: O(n x m)
        // Initialization
        for (int j = 0; j < m; j++) { // TC: O(m)
            dp[n][j] = 0L;
        }
        for (int i = 0; i < n; i++) { // TC: O(n)
            dp[i][m] = (long) 1e12;
        }
        // Iterative Calls
        for (int i = n - 1; i >= 0; i--) { // TC: O(n)
            for (int j = m - 1; j >= 0; j--) { // TC: O(m)
                long skip = dp[i][j + 1];
                long pick = Math.abs((long) robot.get(i) -
                    (long) factories.get(j)) + dp[i + 1][j + 1];
                dp[i][j] = Math.min(skip, pick);
            }
        }
        return dp[0][0];
    }

    /**
     * Approach II : Using Memoization (Top-Down) Approach
     *
     * TC: O(n x m) + O(n x log(n)) + O(m x log(m)) ~ O(n x m)
     * SC: O(n x m) + O(n + m)
     * - O(n x m) - memoization memory
     * - O(n + m) - recursion stack
     *
     * Accepted (40 / 40 testcases passed)
     */
    public long minimumTotalDistanceMemoization(List<Integer> robot,
        int[][] factory) {
        this.n = robot.size();
        this.robot = robot;
        Collections.sort(robot);                     // TC: O(n x log(n))
        Arrays.sort(factory, (a, b) -> a[0] - b[0]); // TC: O(m x log(m))
        this.factories = new ArrayList<>();
        for (int[] f : factory) {
            int pos = f[0];
            int freq = f[1];
            for (int j = 0; j < freq; j++) {
                factories.add(pos);
            }
        }
        this.m = factories.size();
        // i = robot position, j = factory position
        long[][] memo = new long[n][m]; // SC: O(n x m)
        for (long[] mem : memo) {
            Arrays.fill(mem, -1L);
        }
        return solveMemoization(0, 0, memo); // TC: O(n x m), SC: O(n + m)
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(n x m)
     * SC: O(n + m)
     */
    private long solveMemoization(int i, int j, long[][] memo) {
        // Base Case
        if (i == n) {
            // no more robots to be fixed
            return 0L;
        }
        if (j == m) {
            // no more factories left so return invalid value
            return (long) 1e12;
        }
        // Memoization Check
        if (memo[i][j] != -1L) {
            return memo[i][j];
        }
        // Recursion Calls
        // pick or skip
        // skip
        long skip = solveMemoization(i, j + 1, memo);
        // pick
        long pick = Math.abs((long) robot.get(i) - (long) factories.get(j)) +
            solveMemoization(i + 1, j + 1, memo);
        return memo[i][j] = Math.min(skip, pick);
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ (n + m)) + O(n x log(n)) + O(m x log(m)) ~ O(2 ^ (n + m))
     * SC: O(n + m)
     * - O(n + m) - recursion stack
     *
     * Time Limit Exceeded (5 / 40 testcases passed)
     */
    public long minimumTotalDistanceRecursion(List<Integer> robot,
        int[][] factory) {
        this.n = robot.size();
        this.robot = robot;
        Collections.sort(robot);                     // TC: O(n x log(n))
        Arrays.sort(factory, (a, b) -> a[0] - b[0]); // TC: O(m x log(m))
        this.factories = new ArrayList<>();
        for (int[] f : factory) {
            int pos = f[0];
            int freq = f[1];
            for (int j = 0; j < freq; j++) {
                factories.add(pos);
            }
        }
        this.m = factories.size();
        // i = robot position, j = factory position
        return solveRecursion(0, 0);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ (n + m))
     * SC: O(n + m)
     */
    private long solveRecursion(int i, int j) {
        // Base Case
        if (i == n) {
            // no more robots to be fixed
            return 0L;
        }
        if (j == m) {
            // no more factories left so return invalid value
            return (long) 1e12;
        }
        // Recursion Calls
        // pick or skip
        // skip
        long skip = solveRecursion(i, j + 1);
        // pick
        long pick = Math.abs((long) robot.get(i) - (long) factories.get(j)) +
            solveRecursion(i + 1, j + 1);
        return Math.min(skip, pick);
    }
}
