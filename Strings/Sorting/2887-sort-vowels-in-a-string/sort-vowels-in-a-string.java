class Solution {
    /**
     * Approach : Using Sorting + Two Pointers Approach
     *
     * TC: O(N x log(N)) + O(N) ~ O(N x log(N))
     * SC: O(N) + O(N) ~ O(N)
     */
    public String sortVowels(String s) {
        int n = s.length();
        char[] originalChars = s.toCharArray(); // SC: O(N)
        char[] sortedChars = s.toCharArray(); // SC: O(N)
        Arrays.sort(sortedChars); // TC: O(N x log(N))
        int p = 0; // pointer at the start of char[] 'originalChars'
        int q = 0; // pointer at the start of char[] 'sortedChars'
        while (p < n && q < n) { // TC: O(N)
            while (p < n && !isVowel(originalChars[p])) {
                p++;
            }
            while (q < n && !isVowel(sortedChars[q])) {
                q++;
            }
            if (p < n && q < n) {
                originalChars[p] = sortedChars[q];
                p++;
                q++;
            }
        }
        return String.valueOf(originalChars);
    }

    /**
     * Using Simulation Approach
     *
     * TC: O(1)
     * SC: O(1)
     */
    private boolean isVowel(char ch) {
        return ch == 'a' || ch == 'A' ||
            ch == 'e' || ch == 'E' ||
            ch == 'i' || ch == 'I' ||
            ch == 'o' || ch == 'O' ||
            ch == 'u' || ch == 'U';
    }
}
