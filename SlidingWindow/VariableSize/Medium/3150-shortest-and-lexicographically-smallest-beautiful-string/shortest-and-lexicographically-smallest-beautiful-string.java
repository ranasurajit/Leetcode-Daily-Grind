class Solution {
    /**
     * Approach : Using Sliding Window (Variable Size) Approach
     *
     * TC : O(n²)
     * SC : O(n)
     */
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int i = 0; // pointer at the start of sliding window
        int j = 0; // pointer at the end of sliding window
        int count1s = 0;
        int minLength = Integer.MAX_VALUE;
        String result = "";
        while (j < n) { // TC : O(n)
            int current = (s.charAt(j) - '0');
            if (current == 1) {
                count1s++;
            }
            while (i < n && count1s >= k) {
                if (count1s == k) {
                    int currentLength = j - i + 1;
                    String currentString = 
                        s.substring(i, j + 1); // TC : O(n), SC : O(n)
                    if (currentLength < minLength) {
                        minLength = currentLength;
                        result = currentString;
                    } else if (currentLength == minLength) {
                        if (result.compareTo(currentString) > 0) {
                            result = currentString;
                        }
                    }
                }
                // remove computation from index 'i'
                int prev = (s.charAt(i) - '0');
                if (prev == 1) {
                    count1s--;
                }
                i++;
            }
            j++;
        }
        return result;
    }
}
