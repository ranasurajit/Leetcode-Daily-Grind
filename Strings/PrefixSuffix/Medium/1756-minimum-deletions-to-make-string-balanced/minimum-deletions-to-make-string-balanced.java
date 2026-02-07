class Solution {
    /**
     * Approach : Using Array Prefix-Suffix Approach
     *
     * TC: O(N) + O(N) + O(N) ~ O(N)
     * SC: O(N) + O(N) ~ O(N)
     */
    public int minimumDeletions(String s) {
        int n = s.length();
        if (n <= 1) {
            return 0;
        }
        /**
         * we need to make String balanced such that 
         * leftPart has all a's and rightPart has all b's
         * we will count number of bad 'b's on the left
         * and also count of bad 'a's from any index to 
         * its right
         */
        int[] leftBad = new int[n]; // SC: O(N)
        int[] rightBad = new int[n]; // SC: O(N)
        leftBad[0] = s.charAt(0) == 'b' ? 1 : 0;
        for (int i = 1; i < n; i++) { // TC: O(N)
            leftBad[i] = leftBad[i - 1] + (s.charAt(i) == 'b' ? 1 : 0);
        }
        rightBad[n - 1] = 0;
        for (int i = n - 2; i >= 0; i--) { // TC: O(N)
            rightBad[i] = rightBad[i + 1] + (s.charAt(i + 1) == 'a' ? 1 : 0);
        }
        int minDeletion = Math.min(leftBad[n - 1], rightBad[0]);
        for (int i = 0; i < n; i++) { // TC: O(N)
            minDeletion = Math.min(minDeletion, leftBad[i] + rightBad[i]);
        }
        return minDeletion;
    }
}
