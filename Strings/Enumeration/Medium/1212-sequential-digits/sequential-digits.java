class Solution {
    /**
     * Approach : Using Enumeration / Candidate Generation Approach
     *
     * TC : O(1)
     * SC : O(1)
     */
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();
        int minLen = String.valueOf(low).length();
        int maxLen = String.valueOf(high).length();
        solve(low, high, minLen, maxLen, result);
        return result;
    }

    /**
     * Using Enumeration Approach
     *
     * TC : O(1)
     * SC : O(1)
     */
    private void solve(int low, int high, int minLen,
        int maxLen, List<Integer> result) {
        for (int len = minLen; len <= maxLen; len++) { // TC : O(8)
            for (int d = 1; d <= 10 - len; d++) {      // TC : O(k)
                int numFormed = 0;
                int start = d;
                for (int j = 0; j < len; j++) {        // TC : O(9 - k)
                    numFormed = numFormed * 10 + start;
                    start++;
                }
                if (numFormed >= low && numFormed <= high) {
                    result.add(numFormed);
                }
            }
        }
    }
}
