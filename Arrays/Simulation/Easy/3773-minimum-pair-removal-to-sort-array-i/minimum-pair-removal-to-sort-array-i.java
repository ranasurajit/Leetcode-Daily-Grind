class Solution {
    /**
     * Approach I : Using Simulation Approach
     *
     * TC: O(N²)
     * SC: O(1)
     */
    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        int operations = 0;
        while (n > 1) { // TC: O(N)
            Pair pair = isNonDecreasing(nums, n); // TC: O(N)
            boolean isValid = pair.isValid;
            int index = pair.index;
            int minSum = pair.minSum;
            if (pair.isValid) {
                return operations;
            }
            nums[index] = minSum;
            // shift elements left
            shiftLeft(nums, index, n); // TC: O(N)
            operations++;
            n--;
        }
        return operations;
    }

    /**
     * Using Simulation Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    private void shiftLeft(int[] nums, int index, int n) {
        for (int i = index + 1; i < n - 1; i++) { // TC: O(N)
            nums[i] = nums[i + 1];
        }
    }

    /**
     * Using Simulation Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    private Pair isNonDecreasing(int[] nums, int n) {
        int minSum = Integer.MAX_VALUE;
        boolean isValid = true;
        int index = -1;
        for (int i = 0; i < n - 1; i++) { // TC: O(N)
            int currentSum = nums[i] + nums[i + 1];
            if (currentSum < minSum) {
                // choose the leftmost one
                minSum = currentSum;
                index = i;
            }
            if (nums[i] > nums[i + 1]) {
                isValid = false;
            }
        }
        return new Pair(isValid, index, minSum);
    }

    private class Pair {
        boolean isValid;
        int index;
        int minSum;

        public Pair (boolean isValid, int index, int minSum) {
            this.isValid = isValid;
            this.index = index;
            this.minSum = minSum;
        }
    }
}
