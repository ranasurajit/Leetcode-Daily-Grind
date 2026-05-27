class Solution {
    /**
     * Approach : Using Hashing Approach
     *
     * TC : O(n) + O(26) ~ O(n)
     * SC : O(26) ~ O(1)
     */
    public int numberOfSpecialChars(String word) {
        int n = word.length();
        /**
         * we will store the last index of any character
         * in the lower and upper arrays if that is 
         * lowercase and uppercase respectively
         */
        int[] lower = new int[26]; // SC : O(26)
        int[] upper = new int[26]; // SC : O(26)
        Arrays.fill(lower, -1);
        Arrays.fill(upper, -1);
        for (int i = 0; i < n; i++) { // TC : O(n)
            char ch = word.charAt(i);
            int offset = -1;
            if (Character.isLowerCase(ch)) {
                lower[ch - 'a'] = i;
            } else {
                if (upper[ch - 'A'] == -1) {
                    // capture 1st index of upper-case character
                    upper[ch - 'A'] = i;
                }
            }
        }
        /**
         * now for every letters we will count the
         * number of characters whose latest index
         * stored for lower is less than one stored
         * for upper
         */
        int count = 0;
        for (int i = 0; i < 26; i++) { // TC : O(26)
            if (lower[i] == -1 || upper[i] == -1) {
                continue;
            }
            if (lower[i] < upper[i]) {
                count++;
            }
        }
        return count;
    }
}
