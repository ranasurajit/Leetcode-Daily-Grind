class Solution {
    /**
     * Approach : Using Greedy Approach
     *
     * TC : O(n)
     * SC : O(1)
     */
    public boolean sumGame(String num) {
        int n = num.length();
        int leftKnownSum = 0;
        int rightKnownSum = 0;
        int countLeftQuest = 0;
        int countRightQuest = 0;
        for (int i = 0; i < (n / 2); i++) { // TC : O(n / 2)
            char ch = num.charAt(i);
            if (ch == '?') {
                countLeftQuest++;
            } else {
                leftKnownSum += (ch - '0');
            }
        }
        for (int i = (n / 2); i < n; i++) { // TC : O(n / 2)
            char ch = num.charAt(i);
            if (ch == '?') {
                countRightQuest++;
            } else {
                rightKnownSum += (ch - '0');
            }
        }
        int totalQuest = countLeftQuest + countRightQuest;
        if (totalQuest == 0) {
            // if '?' don't exist then we can directly return result
            return leftKnownSum != rightKnownSum;
        }
        if ((totalQuest & 1) != 0) {
            // count of '?' is odd so it gives advantage to Alice to win
            return true;
        }
        /**
         * count of '?' is even
         * so for every pair of question marks if Alice picks x, 
         * then Bob will try to minimize the result to (9 - x)
         * so for every pair of '?' will account for increment
         * of sum '9' to the portion
         */
        int leftSum = 2 * leftKnownSum + 9 * countLeftQuest;
        int rightSum = 2 * rightKnownSum + 9 * countRightQuest;
        return leftSum != rightSum;
    }
}
