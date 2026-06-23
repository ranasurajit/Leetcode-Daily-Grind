class Solution {
    private int n;
    private int l;
    private int r;
    private int m;
    private static final int MOD = (int) 1e9 + 7;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;

        if (n == 1) return m;
        if (n == 2) return (int)((long)m * (m - 1) % MOD);

        long[] prevUp = new long[m];
        long[] prevDown = new long[m];

        // Base case for i = 2
        for (int v = 0; v < m; v++) {
            prevUp[v] = v;             // count of smaller elements
            prevDown[v] = m - v - 1;   // count of larger elements
        }

        for (int i = 3; i <= n; i++) {

            long[] prefixUp = new long[m];
            long[] prefixDown = new long[m];

            prefixUp[0] = prevUp[0];
            prefixDown[0] = prevDown[0];

            for (int v = 1; v < m; v++) {
                prefixUp[v] = (prefixUp[v - 1] + prevUp[v]) % MOD;
                prefixDown[v] = (prefixDown[v - 1] + prevDown[v]) % MOD;
            }

            long[] currUp = new long[m];
            long[] currDown = new long[m];

            for (int v = 0; v < m; v++) {

                // UP → must come from DOWN and smaller values
                if (v > 0) {
                    currUp[v] = prefixDown[v - 1];
                }

                // DOWN → must come from UP and larger values
                if (v < m - 1) {
                    currDown[v] = (prefixUp[m - 1] - prefixUp[v] + MOD) % MOD;
                }
            }

            prevUp = currUp;
            prevDown = currDown;
        }

        long ans = 0;

        for (int v = 0; v < m; v++) {
            ans = (ans + prevUp[v] + prevDown[v]) % MOD;
        }

        return (int) ans;
    }

    /**
     * Approach II : Using Memoization (Top-Down) Approach
     *
     * TC : O(n x m²)
     * SC : O(n) + O(n x m)
     * where m = r - l + 1
     *
     * Time Limit Exceeded (484 / 921 testcases passed)
     */
    public int zigZagArraysMemoization(int n, int l, int r) {
        this.n = n;
        this.l = l;
        this.r = r;
        this.m = r - l + 1;
        long[][][] memo = new long[n][m][3];
        for (long[][] mem : memo) {
            for (long[] me : mem) {
                Arrays.fill(me, -1L);
            }
        }
        long result = 0L;
        for (int num = l; num <= r; num++) {
            result = (result + solveMemoization(1, num, 0, memo)) % MOD;
        }
        /**
         * dir -> initial is 0, +1 for 'up' and -1 for 'down'
         */
        return (int) result;
    }

    /**
     * Using Memoization Approach
     *
     * TC : O(n x m x m x 3) ~ O(n x m²)
     * SC : O(n)
     * where m = r - l + 1
     */
    private int solveMemoization(int idx, int prev, int dir, long[][][] memo) {
        // Base Case
        if (idx == n) {
            return 1;
        }
        int prevIndex = prev - l;
        // Memoization Check
        if (memo[idx][prevIndex][dir + 1] != -1L) {
            return (int) ((memo[idx][prevIndex][dir + 1]) % MOD);
        }
        // Recursion Calls
        long count = 0L;
        for (int num = l; num <= r; num++) { // TC : O(m)
            if (num == prev) {
                // No two adjacent elements are equal
                continue;
            }
            if (dir == 0) {
                // we need to decide the direction
                if (num > prev) {
                    // go up
                    count += solveMemoization(idx + 1, num, 1, memo);
                } else {
                    // go down
                    count += solveMemoization(idx + 1, num, -1, memo);
                }
            } else if (dir == 1) {
                // we need to select num less than prev
                if (num < prev) {
                    count += solveMemoization(idx + 1, num, -1, memo);
                }
            } else {
                // we need to select num more than prev
                if (num > prev) {
                    count += solveMemoization(idx + 1, num, 1, memo);
                }
            }
            count %= MOD;
        }
        memo[idx][prevIndex][dir + 1] = count;
        return (int) count;
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC : O(mⁿ)
     * SC : O(n)
     * where m = r - l + 1
     *
     * Time Limit Exceeded (149 / 921 testcases passed)
     */
    public int zigZagArraysRecursion(int n, int l, int r) {
        this.n = n;
        this.l = l;
        this.r = r;
        /**
         * dir -> initial is 0, +1 for 'up' and -1 for 'down'
         */
        return solveRecursion(0, -1, 0);
    }

    /**
     * Using Recursion Approach
     *
     * TC : O(mⁿ)
     * SC : O(n)
     * where m = r - l + 1
     */
    private int solveRecursion(int idx, int prev, int dir) {
        // Base Case
        if (idx == n) {
            return 1;
        }
        // Recursion Calls
        long count = 0L;
        for (int num = l; num <= r; num++) { // TC : O(m)
            if (prev == -1) {
                // first time pick
                // we can choose to pick 'num'
                count += solveRecursion(idx + 1, num, 0);
            } else {
                if (num == prev) {
                    // No two adjacent elements are equal
                    continue;
                }
                if (dir == 0) {
                    // we need to decide the direction
                    if (num > prev) {
                        // go up
                        count += solveRecursion(idx + 1, num, 1);
                    } else {
                        // go down
                        count += solveRecursion(idx + 1, num, -1);
                    }
                } else if (dir == 1) {
                    // we need to select num less than prev
                    if (num < prev) {
                        count += solveRecursion(idx + 1, num, -1);
                    }
                } else {
                    // we need to select num more than prev
                    if (num > prev) {
                        count += solveRecursion(idx + 1, num, 1);
                    }
                }
            }
            count %= MOD;
        }
        return (int) count;
    }
}
