class Solution {
    /**
     * Approach II : Using Optimal (Bit-Manipulation) Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] result = new int[n];
        for (int i = 0; i < n; i++) { // TC: O(N)
            result[i] = computeFromNum(nums.get(i)); // TC: O(1)
        }
        return result;
    }

    /**
     * Using Bit-Manipulation Approach
     *
     * TC: O(32) ~ O(1)
     * SC: O(1)
     */
    private int computeFromNum(int num) {
        int index = 0;
        for (int i = 0; i < 32; i++) { // TC: O(32)
            if (i == 0 && ((num >> i) & 1) == 0) {
                return -1;
            }
            if (((num >> i) & 1) == 1) {
                continue;
            }
            index = i;
            break;
        }
        // we need to flip (index - 1)th bit to 0
        return num - (1 << (index - 1));
    }

    /**
     * Approach I : Using Brute-Force (Array Simulation) Approach
     *
     * TC: O(N x Max(nums))
     * SC: O(1)
     */
    public int[] minBitwiseArrayBruteForce(List<Integer> nums) {
        int n = nums.size();
        int[] result = new int[n];
        for (int i = 0; i < n; i++) { // TC: O(N)
            /**
             * To satisfy the condition of ans[i] OR ans[i] + 1 = nums.get(i),
             * ans[i] cannot exceed nums.get(i)
             */
            boolean found = false;
            for (int j = 0; j < nums.get(i); j++) { // TC: O(Max(nums))
                if ((j | (j + 1)) == nums.get(i)) {
                    result[i] = j;
                    found = true;
                    break;
                }
            }
            if (!found) {
                result[i] = -1;
            }
        }
        return result;
    }
}
