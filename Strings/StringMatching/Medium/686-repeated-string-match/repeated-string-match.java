class Solution {
    /**
     * Approach : Using KMP Algorithm (To Find LPS) Approach
     *
     * TC: O(N) + O(M x K)
     * SC: O(N) + O(M x K)
     * where K = count of repeatitions
     */
    public int repeatedStringMatch(String a, String b) {
        int n = b.length(); // pattern
        int[] lps = new int[n]; // SC: O(N)
        buildLPS(b, n, lps); // TC: O(N)
        int count = 1;
        StringBuilder sb = new StringBuilder(); // SC: O(M x K)
        int tries = 0;
        int maxTries = (a.length() + b.length() - 1) / a.length();
        while (tries < maxTries + 1) {
            sb.append(a);
            int m = sb.length();
            int i = 0; // pointer at the start of String b i.e. pattern
            int j = 0; // pointer at the start of String sb i.e. text
            while (j < m) { // TC: O(M)
                if (b.charAt(i) == sb.charAt(j)) {
                    i++;
                    j++;
                    if (i == n) {
                        // pattern found
                        return count;
                    }
                } else {
                    if (i > 0) {
                        i = lps[i - 1];
                    } else {
                        j++;
                    }
                }
            }
            count++;
            tries++;
        }
        return count >= maxTries ? -1 : count;
    }

    /**
     * Using KMP Algorithm (To Find LPS) Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    private void buildLPS(String b, int n, int[] lps) {
        int i = 1;
        int len = 0;
        while (i < n) { // TC: O(N)
            if (b.charAt(len) == b.charAt(i)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len > 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }
}
