class Solution {
    /**
     * Approach III : Using Tabulation (Bottom-Up) Approach
     *
     * TC : O(n) + O(n x log(n)) + O(d x n) + O(n) ~ O(n x (d + log(n)))
     * SC : O(n)
     * - O(n) - dp memory
     *
     * Accepted (127 / 127 testcases passed)
     */
    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        int[] dp = new int[n]; // SC : O(n)
        /**
         * In Bottom-Up we need to process smaller
         * values first
         */
        int[][] stepArr = new int[n][2];
        for (int i = 0; i < n; i++) { // TC : O(n)
            stepArr[i][0] = arr[i];
            stepArr[i][1] = i;
        }
        Arrays.sort(stepArr, (a, b) -> a[0] - b[0]); // TC : O(n x log(n))
        for (int i = 0; i < n; i++) { // TC : O(n)
            int idx = stepArr[i][1];
            // we will try to jump from every index
            int leftVisits = 1;
            int rightVisits = 1;
            // jump towards right
            for (int step = 1; step <= d; step++) { // TC : O(d)
                int nextIdx = idx + step;
                if (nextIdx >= n) {
                    // invalid index range
                    break;
                }
                if (arr[idx] <= arr[nextIdx]) {
                    // invalid index as per condition
                    break;
                }
                rightVisits = Math.max(rightVisits, 1 + dp[nextIdx]);
            }
            // jump towards left
            for (int step = 1; step <= d; step++) { // TC : O(d)
                int nextIdx = idx - step;
                if (nextIdx < 0) {
                    // invalid index range
                    break;
                }
                if (arr[idx] <= arr[nextIdx]) {
                    // invalid index as per condition
                    break;
                }
                leftVisits = Math.max(leftVisits, 1 + dp[nextIdx]);
            }
            int currentVisits = Math.max(leftVisits, rightVisits);
            dp[idx] = Math.max(dp[idx], currentVisits);
        }
        int maxVisits = 0;
        for (int i = 0; i < n; i++) { // TC : O(n)
            maxVisits = Math.max(maxVisits, dp[i]);
        }
        return maxVisits;
    }

    /**
     * Approach II : Using Memoization (Top-Down) Approach
     *
     * TC : O(d x n)
     * SC : O(n) + O(n)
     * - O(n) - memoization memory
     * - O(n) - recursion stack
     *
     * Accepted (127 / 127 testcases passed)
     */
    public int maxJumpsMemoization(int[] arr, int d) {
        int n = arr.length;
        int maxVisits = Integer.MIN_VALUE;
        int[] memo = new int[n]; // SC : O(n)
        Arrays.fill(memo, -1);
        for (int i = 0; i < n; i++) { // TC : O(n)
            // we will try to jump from every index
            int currentMaxVisits =
                solveMemoization(i, n, arr, d, memo); // TC : O(d)
            maxVisits = Math.max(maxVisits, currentMaxVisits);
        }
        return maxVisits;
    }

    /**
     * Using Memoization Approach
     *
     * TC : O(d)
     * SC : O(n)
     */
    private int solveMemoization(int idx, int n, int[] arr, int d, int[] memo) {
        int leftVisits = 1;
        int rightVisits = 1;
        // Memoization Check
        if (memo[idx] != -1) {
            return memo[idx];
        }
        // Recursion Calls
        for (int step = 1; step <= d; step++) { // TC : O(d)
            // jump towards right
            int nextIdx = idx + step;
            if (nextIdx >= n) {
                // invalid index range
                break;
            }
            if (arr[idx] <= arr[nextIdx]) {
                // invalid index as per condition
                break;
            }
            rightVisits = Math.max(rightVisits, 
                    1 + solveMemoization(nextIdx, n, arr, d, memo));
        }
        for (int step = 1; step <= d; step++) { // TC : O(d)
            // jump towards left
            int nextIdx = idx - step;
            if (nextIdx < 0) {
                // invalid index range
                break;
            }
            if (arr[idx] <= arr[nextIdx]) {
                // invalid index as per condition
                break;
            }
            leftVisits = Math.max(leftVisits, 
                    1 + solveMemoization(nextIdx, n, arr, d, memo));
        }
        return memo[idx] = Math.max(leftVisits, rightVisits);
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC : O(d ^ n)
     * SC : O(n)
     * - O(n) - recursion stack
     *
     * Time Limit Exceeded (113 / 127 testcases passed)
     */
    public int maxJumpsRecursion(int[] arr, int d) {
        int n = arr.length;
        int maxVisits = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) { // TC : O(n)
            // we will try to jump from every index
            int currentMaxVisits = solveRecursion(i, n, arr, d);
            maxVisits = Math.max(maxVisits, currentMaxVisits);
        }
        return maxVisits;
    }

    /**
     * Using Recursion Approach
     *
     * TC : O(d)
     * SC : O(n)
     */
    private int solveRecursion(int idx, int n, int[] arr, int d) {
        int leftVisits = 1;
        int rightVisits = 1;
        for (int step = 1; step <= d; step++) { // TC : O(d)
            // jump towards right
            int nextIdx = idx + step;
            if (nextIdx >= n) {
                // invalid index range
                break;
            }
            if (arr[idx] <= arr[nextIdx]) {
                // invalid index as per condition
                break;
            }
            rightVisits = Math.max(rightVisits, 
                    1 + solveRecursion(nextIdx, n, arr, d));
        }
        for (int step = 1; step <= d; step++) { // TC : O(d)
            // jump towards left
            int nextIdx = idx - step;
            if (nextIdx < 0) {
                // invalid index range
                break;
            }
            if (arr[idx] <= arr[nextIdx]) {
                // invalid index as per condition
                break;
            }
            leftVisits = Math.max(leftVisits, 
                    1 + solveRecursion(nextIdx, n, arr, d));
        }
        return Math.max(leftVisits, rightVisits);
    }
}
