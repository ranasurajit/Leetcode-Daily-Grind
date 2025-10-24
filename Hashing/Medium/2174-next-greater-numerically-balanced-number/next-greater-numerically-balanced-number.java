class Solution {
    /**
     * Approach : Using Hashing Approach
     *
     * TC: O(N x N)
     * SC: O(1)
     */
    public int nextBeautifulNumber(int n) {
        for (int i = n + 1; i < Integer.MAX_VALUE; i++) { // TC: O(N)
            if (isBalanced(i)) { // TC: O(N)
                return i;
            }
        }
        return -1;
    }

    /**
     * Using Hashing Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    private boolean isBalanced(int num) {
        String numericStr = String.valueOf(num);
        int n = numericStr.length();
        Map<Integer, Integer> freq = new HashMap<Integer, Integer>(); // SC: O(1)
        for (int i = 0; i < n; i++) { // TC: O(N)
            int x = numericStr.charAt(i) - '0';
            freq.put(x, freq.getOrDefault(x, 0) + 1);
        }
        for (Integer key : freq.keySet()) { // TC: O(9)
            if (freq.get(key) != key) {
                return false;
            }
        }
        return true;
    }
}
