class Solution {
    /**
     * Approach II : Using Optimal (String Simulation) Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public int minOperations(String s) {
        int n = s.length();
        int operations = 0; // 1st bit starts with 0
        /**
         * we need to check for both conditions when expected
         * String starts with 0 or 1, and count mismatches for
         * both the conditions and return the minimum operations
         *
         * Note: we can check only 1 parity and other parity is 
         * inverse of it as 1st condition's mismatch is good for
         * 2nd condition's match
         */
        for (int i = 0; i < n; i++) { // TC: O(N)
            int current = s.charAt(i) - '0';
            int expected = i % 2 == 0 ? 0 : 1;
            if (current != expected) {
                operations++;
            }
        }
        return Math.min(operations, n - operations);
    }

    /**
     * Approach I : Using Brute-Force (String Simulation) Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public int minOperationsBruteForce(String s) {
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
