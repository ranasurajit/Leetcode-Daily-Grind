class Solution {
    private int n;
    private int[] nums;
    private long INF = (long) -1e14 + 1;

    /**
     * Approach VI : Using Space Optimization (Optimized DP) Approach
     *
     * TC: O(N x 4) ~ O(N)
     * SC: O(4 + 4) ~ O(1)
     *
     * Accepted (858 / 858 testcases passed)
     */
    public long maxSumTrionic(int[] nums) {
        this.n = nums.length;
        this.nums = nums;
        /**
         * case 0: for subarray [l...p], trend = 0 (not started)
         * case 1: for subarray [l...p], trend = 1 (strictly increasing)
         * case 2: for subarray [p...q], trend = 2 (strictly decreasing)
         * case 3: for subarray [q...r], trend = 3 (strictly increasing)
         */
        long[] nextDP = new long[4]; // SC: O(4) ~ O(1)
        long[] currentDP = new long[4]; // SC: O(4) ~ O(1)
        nextDP[3] = 0L;
        currentDP[3] = 0L;
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            for (int trend = 3; trend >= 0; trend--) { // TC: O(4)
                long skip = INF;
                long pick = INF;
                if (trend == 3) {
                    // we can end it here
                    pick = nums[i];
                }
                if (i < n - 1) {
                    int current = nums[i];
                    int next = nums[i + 1];
                    if (trend == 0) {
                        skip = nextDP[0];
                    }
                    if (trend == 0 && current < next) {
                        // we can start strictly increasing
                        // pick
                        pick = Math.max(pick, current + nextDP[1]);
                    } else if (trend == 1) {
                        // strictly increasing
                        // pick only
                        if (current < next) {
                            // trend = 1 here so we will continue
                            pick = Math.max(pick, current + nextDP[1]);
                        } else if (current > next) {
                            // trend = 1 here but we need to start next decreasing trend - 2
                            pick = Math.max(pick, current + nextDP[2]);
                        }
                    } else if (trend == 2) {
                        // strictly decreasing
                        if (current > next) {
                            // trend = 2 here so we will continue
                            pick = Math.max(pick, current + nextDP[2]);
                        } else if (current < next) {
                            // trend = 2 here so we are bound to go to next increasing trend - 3
                            pick = Math.max(pick, current + nextDP[3]); 
                        }
                    } else if (trend == 3 && current < next) {
                        // trend = 2 here so we will continue
                        pick = Math.max(pick, current + nextDP[3]);
                    }
                }
                currentDP[trend] = Math.max(pick, skip);
            }
            nextDP = currentDP.clone();
        }
        return nextDP[0];
    }

    /**
     * Approach V : Using Tabulation (Bottom-Up) Approach
     *
     * TC: O(N x 4) ~ O(N)
     * SC: O(N x 4) ~ O(N)
     * - O(N) - dp array memory
     *
     * Accepted (858 / 858 testcases passed)
     */
    public long maxSumTrionicTabulation(int[] nums) {
        this.n = nums.length;
        this.nums = nums;
        /**
         * case 0: for subarray [l...p], trend = 0 (not started)
         * case 1: for subarray [l...p], trend = 1 (strictly increasing)
         * case 2: for subarray [p...q], trend = 2 (strictly decreasing)
         * case 3: for subarray [q...r], trend = 3 (strictly increasing)
         */
        long[][] dp = new long[n + 1][4]; // SC: O(4 x N) ~ O(N)
        dp[n][3] = 0L;
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            for (int trend = 3; trend >= 0; trend--) { // TC: O(4)
                long skip = INF;
                long pick = INF;
                if (trend == 3) {
                    // we can end it here
                    pick = nums[i];
                }
                if (i < n - 1) {
                    int current = nums[i];
                    int next = nums[i + 1];
                    if (trend == 0) {
                        skip = dp[i + 1][0];
                    }
                    if (trend == 0 && current < next) {
                        // we can start strictly increasing
                        // pick
                        pick = Math.max(pick, current + dp[i + 1][1]);
                    } else if (trend == 1) {
                        // strictly increasing
                        // pick only
                        if (current < next) {
                            // trend = 1 here so we will continue
                            pick = Math.max(pick, current + dp[i + 1][1]);
                        } else if (current > next) {
                            // trend = 1 here but we need to start next decreasing trend - 2
                            pick = Math.max(pick, current + dp[i + 1][2]);
                        }
                    } else if (trend == 2) {
                        // strictly decreasing
                        if (current > next) {
                            // trend = 2 here so we will continue
                            pick = Math.max(pick, current + dp[i + 1][2]);
                        } else if (current < next) {
                            // trend = 2 here so we are bound to go to next increasing trend - 3
                            pick = Math.max(pick, current + dp[i + 1][3]); 
                        }
                    } else if (trend == 3 && current < next) {
                        // trend = 2 here so we will continue
                        pick = Math.max(pick, current + dp[i + 1][3]);
                    }
                }
                dp[i][trend] = Math.max(pick, skip);
            }
        }
        return dp[0][0];
    }

    /**
     * Approach IV : Using Memoization (Top-Down) Approach
     *
     * TC: O(N)
     * SC: O(N) + O(N)
     * - O(N) - recursion stack
     * - O(N) - memoization memory
     *
     * Accepted (858 / 858 testcases passed)
     */
    public long maxSumTrionicMemoization(int[] nums) {
        this.n = nums.length;
        this.nums = nums;
        /**
         * case 0: for subarray [l...p], trend = 0 (not started)
         * case 1: for subarray [l...p], trend = 1 (strictly increasing)
         * case 2: for subarray [p...q], trend = 2 (strictly decreasing)
         * case 3: for subarray [q...r], trend = 3 (strictly increasing)
         */
        int trend = 0;
        long[][] memo = new long[n + 1][4]; // SC: O(4 x N) ~ O(N)
        for (long[] mem : memo) {
            Arrays.fill(mem, -1L);
        }
        return solveMemoization(0, n, trend, nums, memo); // TC: O(N), SC: O(N)
    }

    /**
     * Using Memoization Approach
     *
     * TC: O(4 x N) ~ O(N)
     * SC: O(N)
     */
    private long solveMemoization(int idx, int n, int trend, int[] nums, long[][] memo) {
        // Base Case
        if (idx == n) {
            if (trend == 3) {
                // reached with finding the trionic sub-array
                return 0L;
            } else {
                // wrong path
                return INF;
            }
        }
        long skip = INF;
        long pick = INF;
        if (trend == 3) {
            // we can end it here
            pick = nums[idx];
        }
        // Memoization Check
        if (memo[idx][trend] != -1L) {
            return memo[idx][trend];
        }
        // Recursion Calls
        if (idx < n - 1) {
            int current = nums[idx];
            int next = nums[idx + 1];
            if (trend == 0) {
                skip = solveMemoization(idx + 1, n, 0, nums, memo);
            }
            if (trend == 0 && current < next) {
                // we can start strictly increasing
                // pick
                pick = Math.max(pick, current + solveMemoization(idx + 1, n, 1, nums, memo));
            } else if (trend == 1) {
                // strictly increasing
                // pick only
                if (current < next) {
                    // trend = 1 here so we will continue
                    pick = Math.max(pick, current +
                        solveMemoization(idx + 1, n, 1, nums, memo));
                } else if (current > next) {
                    // trend = 1 here but we need to start next decreasing trend - 2
                    pick = Math.max(pick, current +
                        solveMemoization(idx + 1, n, 2, nums, memo));
                }
            } else if (trend == 2) {
                // strictly decreasing
                if (current > next) {
                    // trend = 2 here so we will continue
                    pick = Math.max(pick, current +
                        solveMemoization(idx + 1, n, 2, nums, memo));
                } else if (current < next) {
                    // trend = 2 here so we are bound to go to next increasing trend - 3
                    pick = Math.max(pick, current + 
                        solveMemoization(idx + 1, n, 3, nums, memo)); 
                }
            } else if (trend == 3 && current < next) {
                // trend = 2 here so we will continue
                pick = Math.max(pick, current +
                    solveMemoization(idx + 1, n, 3, nums, memo));
            }
        }
        return memo[idx][trend] = Math.max(skip, pick);
    }

    /**
     * Approach III : Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     * - O(N) - recursion stack
     *
     * Time Limit Exceeded (841 / 858 testcases passed)
     */
    public long maxSumTrionicRecursion(int[] nums) {
        this.n = nums.length;
        this.nums = nums;
        /**
         * case 0: for subarray [l...p], trend = 0 (not started)
         * case 1: for subarray [l...p], trend = 1 (strictly increasing)
         * case 2: for subarray [p...q], trend = 2 (strictly decreasing)
         * case 3: for subarray [q...r], trend = 3 (strictly increasing)
         */
        int trend = 0;
        return solveRecursion(0, n, trend, nums);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private long solveRecursion(int idx, int n, int trend, int[] nums) {
        // Base Case
        if (idx == n) {
            if (trend == 3) {
                // reached with finding the trionic sub-array
                return 0L;
            } else {
                // wrong path
                return INF;
            }
        }
        long skip = INF;
        long pick = INF;
        if (trend == 3) {
            // we can end it here
            pick = nums[idx];
        }
        // Recursion Calls
        if (idx < n - 1) {
            int current = nums[idx];
            int next = nums[idx + 1];
            if (trend == 0) {
                skip = solveRecursion(idx + 1, n, 0, nums);
            }
            if (trend == 0 && current < next) {
                // we can start strictly increasing
                // pick
                pick = Math.max(pick, current + solveRecursion(idx + 1, n, 1, nums));
            } else if (trend == 1) {
                // strictly increasing
                // pick only
                if (current < next) {
                    // trend = 1 here so we will continue
                    pick = Math.max(pick, current + solveRecursion(idx + 1, n, 1, nums));
                } else if (current > next) {
                    // trend = 1 here but we need to start next decreasing trend - 2
                    pick = Math.max(pick, current + solveRecursion(idx + 1, n, 2, nums));
                }
            } else if (trend == 2) {
                // strictly decreasing
                if (current > next) {
                    // trend = 2 here so we will continue
                    pick = Math.max(pick, current + solveRecursion(idx + 1, n, 2, nums));
                } else if (current < next) {
                    // trend = 2 here so we are bound to go to next increasing trend - 3
                    pick = Math.max(pick, current + solveRecursion(idx + 1, n, 3, nums)); 
                }
            } else if (trend == 3 && current < next) {
                // trend = 2 here so we will continue
                pick = Math.max(pick, current + solveRecursion(idx + 1, n, 3, nums));
            }
        }
        return Math.max(skip, pick);
    }

    /**
     * Approach II : Using Array Prefix-Suffix + Simulation Approach
     *
     * TC: O(N²) + O(N) ~ O(N²)
     * SC: O(N)
     *
     * Time Limit Exceeded (848 / 858 testcases passed)
     */
    public long maxSumTrionicBetter(int[] nums) {
        int n = nums.length;
        long[] prefixSum = new long[n]; // SC: O(N)
        prefixSum[0] = nums[0];
        for (int i = 1; i < n; i++) { // TC: O(N)
            prefixSum[i] = nums[i] + prefixSum[i - 1];
        }
        long maxSum = Long.MIN_VALUE;
        // note: It is guaranteed that at least one trionic subarray exists
        // strictly increasing from front to find p index count
        int l = 0;
        int start = 0;
        // there could be multiple trionic sub-arrays
        while (start < n) { // TC: O(N²)
            l = start;
            while (l < n - 1 && nums[l + 1] <= nums[l]) {
                // skipping invalid indices at the beginning
                l++;
            }
            // from here starts the strictly increasing sub-arrays [l...p]
            int p = l;
            while (p < n - 1 && nums[p + 1] > nums[p]) {
                p++;
            }
            // from here starts the strictly decreasing sub-arrays [p...q]
            int q = p;
            while (q < n - 1 && nums[q + 1] < nums[q]) {
                q++;
            }
            // from here starts the strictly increasing sub-arrays [q...r]
            int r = q;
            while (r < n - 1 && nums[r + 1] > nums[r]) {
                r++;
                long bestPrefixSum = prefixSum[r];
                bestPrefixSum = Math.max(bestPrefixSum, prefixSum[r]);
                long baseSum = l > 0 ? prefixSum[l - 1] : 0;
                maxSum = Math.max(maxSum, bestPrefixSum - baseSum); // TC: O(1)
            }
            start++;
        }
        return maxSum;
    }

    /**
     * Approach I : Using Array Simulation Approach
     *
     * TC: O(N² x K x L)
     * SC: O(1)
     * where L = maximum size of trionic sub-array, R = size of subarray[q...r]
     *
     * Time Limit Exceeded (759 / 858 testcases passed)
     */
    public long maxSumTrionicBruteForce(int[] nums) {
        int n = nums.length;
        long maxSum = Long.MIN_VALUE;
        // note: It is guaranteed that at least one trionic subarray exists
        // strictly increasing from front to find p index count
        int l = 0;
        int start = 0;
        // there could be multiple trionic sub-arrays
        while (start < n) { // TC: O(N²)
            l = start;
            while (l < n - 1 && nums[l + 1] <= nums[l]) {
                // skipping invalid indices at the beginning
                l++;
            }
            // from here starts the strictly increasing sub-arrays [l...p]
            int p = l;
            while (p < n - 1 && nums[p + 1] > nums[p]) {
                p++;
            }
            // from here starts the strictly decreasing sub-arrays [p...q]
            int q = p;
            while (q < n - 1 && nums[q + 1] < nums[q]) {
                q++;
            }
            // from here starts the strictly increasing sub-arrays [q...r]
            int r = q;
            while (r < n - 1 && nums[r + 1] > nums[r]) {
                r++;
            }
            for (int j = q + 1; j <= r; j++) { // TC: O(K)
                // we need to try for all indices from (q + 1) trail to r
                maxSum = Math.max(maxSum, maxSubArraySum(nums, l, j)); // TC: O(L)
            }
            start++;
        }
        return maxSum;
    }

    /**
     * Using Array Simulation Approach
     *
     * TC: O(L)
     * SC: O(1)
     */
    private long maxSubArraySum(int[] nums, int start, int end) {
        long sum = 0;
        System.out.println(start + ", " + end);
        for (int i = start; i <= end; i++) {
            sum += (long) nums[i];
        }
        return sum;
    }
}
