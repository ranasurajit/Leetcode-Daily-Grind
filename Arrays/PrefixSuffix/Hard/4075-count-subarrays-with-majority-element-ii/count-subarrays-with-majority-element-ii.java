class Solution {
    /**
     * Approach V : Using Optimal (Prefix-Sum + Hashing) Approach
     *
     * TC : O(n)
     * SC : O(n)
     *
     * Accepted (516 / 516 testcases passed)
     */
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        Map<Long, Integer> prefixMap = new HashMap<>(); // SC : O(n)
        prefixMap.put(0L, 1);
        long prefixSum = 0L;
        long validLeftIndices = 0L;
        long result = 0;
        for (int j = 0; j < n; j++) { // TC : O(n)
            if (nums[j] == target) {
                validLeftIndices += prefixMap.getOrDefault(prefixSum, 0);
                prefixSum += 1;
            } else {
                prefixSum -= 1;
                validLeftIndices -= prefixMap.getOrDefault(prefixSum, 0);
            }
            prefixMap.put(prefixSum, prefixMap.getOrDefault(prefixSum, 0) + 1);
            result += validLeftIndices;
        }
        return result;
    }

    /**
     * Approach IV : Using Better (Prefix-Sum) Approach
     *
     * TC : O(n) + O(n) + O(n²) ~ O(n²)
     * SC : O(1)
     *
     * Time Limit Exceeded (511 / 516 testcases passed)
     */
    public long countMajoritySubarraysBetterPrefixSum(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {  // TC : O(n)
            // we add +1 to count if nums[j] = target else add -1
            if (nums[i] == target) {
                nums[i] = 1;
            } else {
                nums[i] = -1;
            }
        }
        // pre-compute prefix-sum
        for (int i = 1; i < n; i++) {  // TC : O(n)
            nums[i] += nums[i - 1];
        }
        long result = 0;
        for (int l = 0; l < n; l++) {     // TC : O(n)
            for (int r = l; r < n; r++) { // TC : O(n)
                int sum = nums[r] - (l > 0 ? nums[l - 1] : 0);
                if (sum > 0) {
                    result++;
                }
            }
        }
        return result;
    }

    /**
     * Approach III : Using Optimal Brute-Force (Array Simulation) Approach
     *
     * TC : O(n²)
     * SC : O(1)
     *
     * Time Limit Exceeded (510 / 516 testcases passed)
     */
    public long countMajoritySubarraysOptimalBruteForce(int[] nums, int target) {
        int n = nums.length;
        int result = 0;
        for (int i = 0; i < n; i++) {          // TC : O(n)
            // we add +1 to count if nums[j] = target else add -1
            int count = 0;
            for (int j = i; j < n; j++) {      // TC : O(n)
                count += (nums[j] == target) ? 1 : -1;
                if (count > 0) {
                    result++;
                }
            }
        }
        return result;
    }

    /**
     * Approach II : Using Better Brute-Force (Array Simulation) Approach
     *
     * TC : O(n²)
     * SC : O(1)
     *
     * Time Limit Exceeded (510 / 516 testcases passed)
     */
    public long countMajoritySubarraysBetterBruteForce(int[] nums, int target) {
        int n = nums.length;
        int result = 0;
        for (int i = 0; i < n; i++) {          // TC : O(n)
            // running count of elements = target in sub-array nums[i...j]
            int count = 0;
            for (int j = i; j < n; j++) {      // TC : O(n)
                count += (nums[j] == target) ? 1 : 0;
                if (count > (j - i + 1) / 2) {
                    result++;
                }
            }
        }
        return result;
    }

    /**
     * Approach I : Using Brute-Force (Array Simulation) Approach
     *
     * TC : O(n³)
     * SC : O(1)
     *
     * Time Limit Exceeded (505 / 516 testcases passed)
     */
    public long countMajoritySubarraysBruteForce(int[] nums, int target) {
        int n = nums.length;
        int result = 0;
        for (int i = 0; i < n; i++) {          // TC : O(n)
            for (int j = i; j < n; j++) {      // TC : O(n)
                int count = 0;
                // count of elements = target in sub-array nums[i...j]
                for (int k = i; k <= j; k++) { // TC : O(n)
                    if (nums[k] == target) {
                        count++;
                    }
                }
                if (count > (j - i + 1) / 2) {
                    result++;
                }
            }
        }
        return result;
    }
}
