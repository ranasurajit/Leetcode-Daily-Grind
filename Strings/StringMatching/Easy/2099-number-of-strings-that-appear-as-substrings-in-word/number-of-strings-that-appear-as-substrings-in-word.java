class Solution {
    /**
     * Approach I : Using Brute-Force (Two Pointers) Approach
     *
     * TC : O(m x n x l)
     * SC : O(1)
     * where m = length(word), l = max(length(patterns)), n = length(patterns)
     */
    public int numOfStrings(String[] patterns, String word) {
        int count = 0;
        for (String s : patterns) { // TC : O(n)
            if (isSubstring(word, s)) { // TC : O(l x m)
                count++;
            }
        }
        return count;
    }

    /**
     * Using Two Pointers Approach
     *
     * TC : O(m x l)
     * SC : O(1)
     */
    private boolean isSubstring(String word, String s) {
        int m = word.length();
        int n = s.length();
        int start = 0;
        while (start <= m - n) { // TC : O(m x l)
            int p = start; // pointer at the start of String 'word'
            int q = 0; // pointer at the start of String 's'
            while (q < n && word.charAt(p) == s.charAt(q)) {
                p++;
                q++;
                
            }
            if (q == n) {
                return true;
            }
            start++;
        }
        return false;
    }
}
