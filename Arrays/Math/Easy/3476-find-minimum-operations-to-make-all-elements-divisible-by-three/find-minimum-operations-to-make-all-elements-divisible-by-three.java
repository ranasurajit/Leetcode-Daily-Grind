class Solution {
    /**
     * Approach : Using Array + Math Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public int minimumOperations(int[] nums) {
        int operations = 0;
        for (int num : nums) { // TC: O(N)
            int rem = num % 3;
            operations += Math.min(rem, 3 - rem);
        }
        return operations;
    }
}
