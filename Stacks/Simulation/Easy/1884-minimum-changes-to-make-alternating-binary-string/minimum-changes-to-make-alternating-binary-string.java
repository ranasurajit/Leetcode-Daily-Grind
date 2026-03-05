class Solution {
    /**
     * Approach : Using String Simulation Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public int minOperations(String s) {
        int n = s.length();
        int operations1 = 0; // 1st bit starts with 0
        int operations2 = 0; // 1st bit starts with 1
        /**
         * we need to check for both conditions when expected
         * String starts with 0 or 1, and count mismatches for
         * both the conditions and return the minimum operations
         */
        for (int i = 0; i < n; i++) { // TC: O(N)
            int current = s.charAt(i) - '0';
            int expected1 = i % 2 == 0 ? 0 : 1;
            int expected2 = i % 2 == 0 ? 1 : 0;
            if (current != expected1) {
                operations1++;
            } 
            if (current != expected2) {
                operations2++;
            }
        }
        return Math.min(operations1, operations2);
    }
}
