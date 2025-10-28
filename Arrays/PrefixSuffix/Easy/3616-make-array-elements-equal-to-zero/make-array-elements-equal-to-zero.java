class Solution {
    /**
     * Approach II : Using Optimal (Prefix-Sum) Approach
     *
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(1)
     */
    public int countValidSelections(int[] nums) {
        int n = nums.length;
        int count = 0;
        int leftSum = 0;
        int rightSum = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            rightSum += nums[i];
        }
        for (int i = 0; i < n; i++) { // TC: O(N)
            // choosing a starting point
            if (nums[i] == 0) {
                if (leftSum <= rightSum && rightSum - leftSum <= 1) {
                    count++;
                }
                if (leftSum >= rightSum && leftSum - rightSum <= 1) {
                    count++;
                }
            }
            leftSum += nums[i];
            rightSum -= nums[i];
        }
        return count;
    }

    /**
     * Approach I : Using Brute-Force (Array Simulation) Approach
     *
     * TC: O(N x N) + O(N x N) ~ O(N x N)
     * SC: O(N) - reused
     */
    public int countValidSelectionsBruteForce(int[] nums) {
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            // choosing a starting point
            if (nums[i] != 0) {
                continue;
            }
            // start towards left from start index 'i'
            count += makeIfMovesValid(nums, i, n, -1) ? 1 : 0; // TC: O(N), SC: O(N)
            // start towards right from start index 'i'
            count += makeIfMovesValid(nums, i, n, 1) ? 1 : 0; // TC: O(N), SC: O(N)
        }
        return count;
    }

    /**
     * Using Array Simulation Approach
     *
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(N)
     */
    private boolean makeIfMovesValid(int[] nums, int index, int n, int dir) {
        int[] copyNums = Arrays.copyOf(nums, n); // SC: O(N)
        while (index >= 0 && index < n) { // TC: O(N)
            // valid index to continue the operations
            if (copyNums[index] == 0) {
                index += dir;
            } else {
                // decrement current index of nums value by 1
                copyNums[index]--;
                dir = dir == 1 ? -1 : 1;
                index += dir;
            }
        }
        // check if Zero Array Elements is attained
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (copyNums[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
