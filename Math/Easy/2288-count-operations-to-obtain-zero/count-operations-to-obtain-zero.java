class Solution {
    /**
     * Approach II : Using Optimized Mathematical (Using Division) Approach
     *
     * TC: O(log(Max(num1, nums2)))
     * SC: O(1)
     */
    public int countOperations(int num1, int num2) {
        int operations = 0;
        while (num1 > 0 && num2 > 0) { // TC: O(Max(num1, nums2))
            if (num1 > num2) {
                operations += (num1 / num2);
                num1 %= num2;
            } else {
                operations += (num2 / num1);
                num2 %= num1;
            }
        }
        return operations;
    }

    /**
     * Approach I : Using Brute-Force (Math Simulation) Approach
     *
     * TC: O(Max(num1, nums2))
     * SC: O(1)
     */
    public int countOperationsBruteForce(int num1, int num2) {
        int operations = 0;
        while (num1 > 0 && num2 > 0) { // TC: O(Max(num1, nums2))
            if (num1 > num2) {
                num1 -= num2;
            } else {
                num2 -= num1;
            }
            operations++;
        }
        return operations;
    }
}
