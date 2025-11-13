class Solution {
    /**
     * Approach : Using Greedy + Pre-processing Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public int maxOperations(String s) {
        int n = s.length();
        /**
         * we need to move from left to write as we need to
         * maximize the operations and if we move from right
         * to left then it will minimize the operations
         *
         * also we need to count ones encountered till any
         * index and capture operations += num(1s) found at 
         * an index i where s.charAt(i) = '0'
         */
        int operations = 0;
        int leftOnes = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            leftOnes += s.charAt(i) == '1' ? 1 : 0;
            boolean isEntered = false;
            while (i < n && s.charAt(i) == '0') {
                isEntered = true;
                i++;
            }
            if (isEntered) {
                i--;
            }
            if (i > 0 && s.charAt(i) == '0') {
                operations += leftOnes;
            }
        }
        return operations;
    }
}
