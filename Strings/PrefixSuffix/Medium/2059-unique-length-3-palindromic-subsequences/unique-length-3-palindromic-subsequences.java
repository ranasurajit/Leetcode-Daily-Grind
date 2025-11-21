class Solution {
    /**
     * Approach II : Using Optimal (Prefix-Suffix + Hashing) Approach
     *
     * TC: O(26) + O(N) + O(26 x N) ~ O(N)
     * SC: O(26 x 2) ~ O(1)
     *
     * Accepted (70 / 70 testcases passed)
     */
    public int countPalindromicSubsequence(String s) {
        int n = s.length();
        int[][] indices = new int[26][2];
        for (int[] ind : indices) { // TC: O(26)
            Arrays.fill(ind, -1);
        }
        // we will be filling the indices with first and last occurence of any character
        for (int i = 0; i < n; i++) { // TC: O(N)
            int idx = s.charAt(i) - 'a';
            if (indices[idx][0] == -1) {
                indices[idx][0] = i;
            } else {
                indices[idx][1] = i;
            }
        }
        int count = 0;
        for (int i = 0; i < 26; i++) { // TC: O(26)
            int first = -1;
            int last = -1;
            if (indices[i][0] != -1 && indices[i][1] - indices[i][0] > 0) {
                Set<Character> hs = new HashSet<Character>();
                for (int j = indices[i][0] + 1; j < indices[i][1]; j++) { // TC: O(N)
                    hs.add(s.charAt(j));
                }
                count += hs.size();
            }
        }
        return count;
    }

    /**
     * Approach I : Using Brute-Force (Recursion) Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     *
     * Time Limit Exceeded (14 / 70 testcases passed)
     */
    public int countPalindromicSubsequenceBruteForce(String s) {
        int n = s.length();
        Set<String> hs = new HashSet<String>();
        solveRecursion(0, n, "", s, hs);
        return hs.size();
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2 ^ N)
     * SC: O(N)
     */
    private void solveRecursion(int idx, int n, String current, String s, Set<String> hs) {
        // Base Case
        if (idx == n) {
            if (current.length() == 3 && current.charAt(0) == current.charAt(2)) {
                hs.add(current);
            }
            return;
        }
        // Recursion Calls
        // pick or skip
        solveRecursion(idx + 1, n, current, s, hs); // skip
        solveRecursion(idx + 1, n, current + String.valueOf(s.charAt(idx)), s, hs); // pick
    }
}
