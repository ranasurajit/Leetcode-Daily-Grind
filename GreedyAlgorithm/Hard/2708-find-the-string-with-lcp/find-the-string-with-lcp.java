class Solution {
    /**
     * Approach : Using Greedy Algorithm + DP (Bottom-Up) Approach
     *
     * TC: O(N) + O(N²) + O(N²) + O(N²) ~ O(N²)
     * SC: O(N) + O(N²) ~ O(N²)
     */
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char[] word = new char[n]; // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            // placing default
            word[i] = '$';
        }
        // try matching with lcp matrix
        for (int i = 0; i < n; i++) { // TC: O(N)
            // try matching with previous index
            for (int j = 0; j < i; j++) { // TC: O(N)
                if (lcp[j][i] != 0) {
                    word[i] = word[j];
                    break;
                }
            }
            if (word[i] == '$') {
                boolean[] isForbidden = new boolean[26]; // SC: O(26)
                for (int j = 0; j < i; j++) { // TC: O(N)
                    if (lcp[j][i] == 0) {
                        isForbidden[word[j] - 'a'] = true;
                    }
                }
                for (int ch = 0; ch < 26; ch++) {
                    if (!isForbidden[ch]) {
                        word[i] = (char) (ch + 'a');
                        break;
                    }
                }
                // if still word[i] = '$' then such String is not possible
                if (word[i] == '$') {
                    return "";
                }
            }
        }
        String result = String.valueOf(word);
        // now we need to create lcpMatrix of word and compare with lcp
        int[][] computedLCP = createLCP(word); // TC: O(N²), SC: O(N²)
        // now we can compare if computedLCP matches with lcp
        for (int i = 0; i < n; i++) { // TC: O(N)
            for (int j = 0; j < n; j++) { // TC: O(N)
                if (lcp[i][j] != computedLCP[i][j]) {
                    return "";
                }
            }
        }
        return result;
    }

    /**
     * Using DP (Bottom-Up) Approach
     *
     * TC: O(N) + O(N) + O(N²) ~ O(N²)
     * SC: O(N²)
     */
    private int[][] createLCP(char[] word) {
        int n = word.length;
        int[][] lcp = new int[n][n]; // SC: O(N²)
        for (int i = 0; i < n; i++) { // TC: O(N)
            lcp[i][n - 1] = word[i] == word[n - 1] ? 1 : 0;
        }
        for (int j = 0; j < n; j++) { // TC: O(N)
            lcp[n - 1][j] = word[j] == word[n - 1] ? 1 : 0;
        }
        for (int i = n - 2; i >= 0; i--) { // TC: O(N)
            for (int j = n - 2; j >= 0; j--) { // TC: O(N)
                lcp[i][j] = word[i] == word[j] ? 1 + lcp[i + 1][j + 1] : 0;
            }
        }
        return lcp;
    }
}
