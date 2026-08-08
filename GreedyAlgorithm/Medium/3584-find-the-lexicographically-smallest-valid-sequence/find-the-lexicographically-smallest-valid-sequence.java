class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        /**
         * we can greedily try to fill the matching index
         * of word1 that matches with the indices of word2
         */
        int[] suffix = new int[m];
        Arrays.fill(suffix, -1);
        int p = n - 1; // pointer at the end of String 'word1'
        int q = m - 1; // pointer at the end of String 'word2'
        while (p >= 0 && q >= 0) {
            if (word1.charAt(p) == word2.charAt(q)) {
                suffix[q] = p;
                q--;
            }
            p--;
        }
        int[] prefix = new int[m];
        Arrays.fill(prefix, -1);
        p = 0; // pointer at the start of String 'word1'
        q = 0; // pointer at the start of String 'word2'
        while (p < n && q < m) {
            if (word1.charAt(p) == word2.charAt(q)) {
                prefix[q] = p;
                q++;
            }
            p++;
        }
        p = 0; // pointer at the start of array 'prefix'
        q = 0; // pointer at the start of array 'prefix'
        boolean usedModification = false;
        int[] result = new int[m];
        while (p < n && q < m) {
            if (word1.charAt(p) == word2.charAt(q)) {
                // matches
                result[q] = p;
                p++;
                q++;
            } else if (!usedModification && (q == m - 1 ||
                (suffix[q + 1] != -1 && p < suffix[q + 1]))) {
                // un-matched
                result[q] = p;
                usedModification = true;
                p++;
                q++;
            } else {
                p++;
            }
        }
        if (q != m) {
            // pointer 'p' is exhaused
            return new int[0];
        }
        return result;
    }
}
