class Solution {
    /**
     * Approach : Using Sliding Window (Fixed Size) Approach
     *
     * TC: O(N x K)
     * SC: O(K x Min(N - K + 1, 2 ^ K)) ~ O(K x Min(N, 2 ^ K)) 
     */
    public boolean hasAllCodes(String s, int k) {
        int n = s.length();
        if (n - k + 1 < (1 << k)) {
            /**
             * if number of sliding windows (n - k + 1) is not
             * enough for number of binary codes, then do early
             * pruning
             */
            return false;
        }
        Set<String> set = new HashSet<String>(); // SC: O(N - K + 1)
        int i = 0; // start pointer of sliding window
        int j = 0; // end pointer of sliding window
        StringBuilder sb = new StringBuilder();
        while (j < n) { // TC: O(N)
            sb.append(s.charAt(j));
            if (j - i + 1 == k) {
                // fixed window size of k is met
                set.add(sb.toString());
                // remove computation from index 'i'
                sb.deleteCharAt(0);
                // slide the window
                i++;
            }
            j++;
        }
        return set.size() == (1 << k);
    }
}
