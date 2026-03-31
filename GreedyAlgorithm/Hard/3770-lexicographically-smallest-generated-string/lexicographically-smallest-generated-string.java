class Solution {
    /**
     * Approach : Using Greedy Approach
     *
     * TC: O(N x M) + O(N + M - 1) + O(26 x N x M) ~ O(N x M)
     * SC: O(N + M - 1) + O(N + M - 1) ~ O(N + M)
     */
    public String generateString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        char[] word = new char[n + m - 1]; // SC: O(N + M - 1)
        // we can initialize word with default characters
        Arrays.fill(word, '$');
        // we also need to store the fixed positions
        boolean[] fixedPos = new boolean[n + m - 1]; // SC: O(N + M - 1)
        /**
         * we can start processing the str1 with 
         * indices having str1.charAt(i) = 'T'
         */
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (str1.charAt(i) == 'T') {
                int start = i;
                for (int j = 0; j < m; j++) { // TC: O(M)
                    if (word[start] == '$') {
                        word[start] = str2.charAt(j);
                        fixedPos[start] = true;
                    } else {
                        if (str2.charAt(j) != word[start]) {
                            // not possible to create word
                            return "";
                        }
                        fixedPos[start] = true;
                    }
                    start++;
                }
            }
        }
        /**
         * Now we can fill all the characters having
         * default character '$' greedily with 'a'
         */
        for (int i = 0; i < word.length; i++) { // TC: O(N + M - 1)
            if (word[i] == '$') {
                word[i] = 'a';
            }
        }
        /**
         * we can start processing the str1 with 
         * indices having str1.charAt(i) = 'F' to
         * validate both filled and unfilled
         * positions in word
         */
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (str1.charAt(i) == 'F') {
                // check if sub-string of str1 [i, i + m - 1] matches with str2
                boolean matched = true;
                for (int j = 0; j < m; j++) { // TC: O(M)
                    if (word[i + j] != str2.charAt(j)) {
                        matched = false;
                        break;
                    }
                }
                if (!matched) {
                    // this is expected
                    continue;
                }
                boolean modified = false;
                // scanning from right to left
                for (int j = m - 1; j >= 0; j--) { // TC: O(M)
                    int pos = i + j;
                    if (!fixedPos[pos]) {
                        // we can modify if position is not fixed for 'T' scan
                        for (int ch = 0; ch < 26; ch++) { // TC: O(26)
                            char c = (char) (ch + 'a');
                            if (c != str2.charAt(j)) {
                                word[pos] = c;
                                modified = true;
                                break;
                            }
                        }
                    }
                    if (modified) {
                        break;
                    }
                }
                if (!modified) {
                    // did not find a character to modify at all
                    return "";
                }
            }
        }
        return String.valueOf(word);
    }
}
