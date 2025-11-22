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
            int option1 = (3 * ((num / 3) + 1)) - num;
            int option2 = num % 3;
            operations += Math.min(option1, option2);
        }
        return operations;
    }
}
