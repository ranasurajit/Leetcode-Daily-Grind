class Solution {
    private static final int MOD = (int) 1e9 + 7;

    /**
     * Approach II : Using Hashing Approach
     *
     * TC: O(N) + O(N) + O(N) ~ O(N)
     * SC: O(N) + O(N) + O(N) + O(N) ~ O(N)
     *
     * Accepted (1121 / 1121 testcases passed)
     */
    public int specialTriplets(int[] nums) {
        int n = nums.length;
        long[] countLeft = new long[n];  // SC: O(N)
        long[] countRight = new long[n]; // SC: O(N)
        Map<Long, Long> freqLeftMap = new HashMap<Long, Long>();  // SC: O(N)
        Map<Long,  Long> freqRightMap = new HashMap<Long, Long>(); // SC: O(N)
        for (int j = 0; j < n; j++) { // TC: O(N)
            countLeft[j] = (freqLeftMap.getOrDefault((long) nums[j] * 2, 0L)) % MOD;
            freqLeftMap.put((long) nums[j], freqLeftMap.getOrDefault((long) nums[j], 0L) + 1L);
        }
        for (int j = n - 1; j >= 0; j--) { // TC: O(N)
            countRight[j] = (freqRightMap.getOrDefault((long) nums[j] * 2, 0L)) % MOD;
            freqRightMap.put((long) nums[j], freqRightMap.getOrDefault((long) nums[j], 0L) + 1L);
        }
        long count = 0L;
        for (int j = 0; j < n; j++) { // TC: O(N)
            count = (count + (countLeft[j] * countRight[j]) % MOD) % MOD;
        }
        return (int) count;
    }

    /**
     * Approach I : Using Brute-Force Approach
     *
     * TC: O(N x N x N)
     * SC: O(1)
     *
     * Time Limit Exceeded (930 / 1121 testcases passed)
     */
    public int specialTripletsBruteForce(int[] nums) {
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if ((nums[i] == nums[j] * 2) && (nums[k] == nums[j] * 2)) {
                        count = (count + 1) % MOD;
                    }
                }
            }
        }
        return count;
    }
}
