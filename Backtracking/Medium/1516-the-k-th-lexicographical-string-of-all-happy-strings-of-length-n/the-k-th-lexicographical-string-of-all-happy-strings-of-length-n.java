class Solution {
    private List<String> happyList = new ArrayList<>();
    private int n;
    private int k;
    private int count = 0;
    private String kthHappy = "";
    private char[] letters = { 'a', 'b', 'c' };

    /**
     * Approach II : Using Optimal (Recursion + Backtracking) Approach
     *
     * TC: O(n x k)
     * SC: O(n)
     * - O(n) - recursion stack
     */
    public String getHappyString(int n, int k) {
        this.n = n;
        this.k = k;
        backtrackOptimal(0, -1, new StringBuilder()); // TC: O(n x k), SC: O(n)
        return kthHappy;
    }

    /**
     * Using Recursion + Backtracking Approach
     *
     * TC: O(x n 3 x k) ~ O(n x k)
     * SC: O(n)
     */
    private void backtrackOptimal(int idx, int prevIdx, StringBuilder sb) {
        // Base Case
        if (idx == n) {
            count++;
            if (count == k) {
                kthHappy = sb.toString();
            }
            return;
        }
        // Recursion Calls
        for (int i = 0; i < letters.length && kthHappy.isEmpty(); i++) { // TC: O(3)
            if (prevIdx == -1 || letters[prevIdx] != letters[i]) {
                // we can pick this character
                sb.append(letters[i]); // modify
                backtrackOptimal(idx + 1, i, sb); // explore
                sb.setLength(sb.length() - 1); // backtrack
            }
        }
    }

    /**
     * Approach I : Using Brute-Force (Recursion + Backtracking) Approach
     *
     * TC: O(3 x n x 2ⁿ⁻¹)
     * SC: O(3 x n x 2ⁿ⁻¹) + O(n)
     * - O(3 x n x 2ⁿ⁻¹) - for happyList memory
     * - O(n) - recursion stack
     */
    public String getHappyStringBruteForce(int n, int k) {
        this.n = n;
        backtrack(0, -1, new StringBuilder());
        return k <= happyList.size() ? happyList.get(k - 1) : "";
    }

    /**
     * Using Recursion + Backtracking Approach
     *
     * TC: O(3 x n x 2ⁿ⁻¹)
     * SC: O(3 x n x 2ⁿ⁻¹) + O(n)
     */
    private void backtrack(int idx, int prevIdx, StringBuilder sb) {
        // Base Case
        if (idx == n) {
            happyList.add(sb.toString());
            return;
        }
        // Recursion Calls
        for (int i = 0; i < letters.length; i++) { // TC: O(3)
            if (prevIdx == -1 || letters[prevIdx] != letters[i]) {
                // we can pick this character
                sb.append(letters[i]); // modify
                backtrack(idx + 1, i, sb); // explore
                sb.setLength(sb.length() - 1); // backtrack
            }
        }
    }
}
