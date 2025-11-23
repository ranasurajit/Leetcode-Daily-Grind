class Solution {
    private static int INF = (int) 1e4;
    /**
     * Approach III : Using Greedy Clean Approach
     *
     * TC: O(N) + O(N1 x log(N1)) + O(N2 x log(N2)) ~ O(N x log(N))
     * SC: O(N1 + N2) ~ O(N)
     *
     * Accepted (43 / 43 testcases passed)
     */
    public int maxSumDivThree(int[] nums) {
        int n = nums.length;
        /**
         * When we do modulo 3 then remainders can be 0, 1 or 2
         * so now we can check the overall modulo because of 
         * which it is not allowing the overall sum divisible by 3 
         */
        int total = 0;
        List<Integer> ones = new ArrayList<Integer>(); // SC: O(N1)
        List<Integer> twos = new ArrayList<Integer>(); // SC: O(N1)
        for (int i = 0; i < n; i++) { // TC: O(N)
            total += nums[i];
            int rem = nums[i] % 3;
            if (rem == 1) {
                ones.add(nums[i]);
            } else if (rem == 2) {
                twos.add(nums[i]);
            }
        }
        if (total % 3 == 0) {
            return total;
        }
        int overallMod = total % 3;
        /**
         * There will be two cases when overallMod = 1 or 2
         * to maximize sum, we need to pick minimum number 
         * that contributes leftover 'overallMod'
         * 
         * if overallMod = 2, we need to find one number which contributes 
         * remainder 2 or 2 mimimum numbers that contribute a remainder 1
         *
         * if overallMod = 1, we need to find one number which contributes 
         * remainder 1 or 2 mimimum numbers that contribute a remainder 2
         */
        Collections.sort(ones); // TC: O(N1 x log(N1))
        Collections.sort(twos); // TC: O(N2 x log(N2))
        int remove = INF;
        int minNumOne = 0;
        int minNumTwo = 0;
        if (overallMod == 2) {
            minNumOne = twos.size() > 0 ? twos.get(0) : INF;
            minNumTwo = ones.size() > 1 ? ones.get(0) + ones.get(1) : INF;
        } else {
            minNumOne = ones.size() > 0 ? ones.get(0) : INF;
            minNumTwo = twos.size() > 1 ? twos.get(0) + twos.get(1) : INF;
        }
        remove = Math.min(minNumOne, minNumTwo);
        return total - remove;
    }

    /**
     * Approach II : Using Greedy Approach
     *
     * TC: O(N) + O(N1 x log(N1)) + O(N2 x log(N2)) ~ O(N x log(N))
     * SC: O(N)
     *
     * Accepted (43 / 43 testcases passed)
     */
    public int maxSumDivThreeGreedyApproach(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0] % 3 == 0 ? nums[0] : 0; 
        }
        /**
         * When we do modulo 3 then remainders can be 0, 1 or 2
         * so now we can check the overall modulo because of 
         * which it is not allowing the overall sum divisible by 3 
         */
        int overallMod = 0;
        int total = 0;
        Map<Integer, ArrayList<Integer>> map = 
            new HashMap<Integer, ArrayList<Integer>>(); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            int rem = nums[i] % 3;
            overallMod += rem;
            total += nums[i];
            map.computeIfAbsent(rem, k -> new ArrayList<Integer>()).add(nums[i]);
        }
        if (total % 3 == 0) {
            return total;
        }
        overallMod = overallMod % 3;
        /**
         * There will be two cases when overallMod = 1 or 2
         * to maximize sum, we need to pick minimum number 
         * that contributes leftover 'overallMod'
         * 
         * if overallMod = 2, we need to find one number which contributes 
         * remainder 2 or 2 mimimum numbers that contribute a remainder 1
         *
         * if overallMod = 1, we need to find one number which contributes 
         * remainder 1 or 2 mimimum numbers that contribute a remainder 2
         */
        int minNumOne = 0;
        int minNumPairOne = 0;
        int minNumPairTwo = 0;
        List<Integer> ones = map.getOrDefault(1, new ArrayList<Integer>());
        List<Integer> twos = map.getOrDefault(2, new ArrayList<Integer>());
        Collections.sort(ones); // TC: O(N1 x log(N1))
        Collections.sort(twos); // TC: O(N2 x log(N2))
        if (overallMod == 2) {
            minNumOne = twos.size() > 0 ? twos.get(0) : INF;
            minNumPairOne = ones.size() > 0 ? ones.get(0) : INF;
            minNumPairTwo = ones.size() > 1 ? ones.get(1) : INF;
        } else {
            minNumOne = ones.size() > 0 ? ones.get(0) : INF;
            minNumPairOne = twos.size() > 0 ? twos.get(0) : INF;
            minNumPairTwo = twos.size() > 1 ? twos.get(1) : INF;
        }
        return Math.max(total - minNumOne, total - minNumPairOne - minNumPairTwo);
    }

    /**
     * Approach I : Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     * - O(N) - recursion stack
     *
     * Time Limit Exceeded (19 / 43 testcases passed)
     */
    public int maxSumDivThreeRecursion(int[] nums) {
        int n = nums.length;
        return solveRecursion(0, 0, n, nums);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private int solveRecursion(int idx, int sum, int n, int[] nums) {
        // Base Case
        if (idx == n) {
            return sum % 3 == 0 ? sum : 0;
        }
        // Recursion Calls
        int skip = solveRecursion(idx + 1, sum, n, nums); // skip
        int pick = solveRecursion(idx + 1, sum + nums[idx], n, nums); // pick
        return Math.max(pick, skip);
    }
}
