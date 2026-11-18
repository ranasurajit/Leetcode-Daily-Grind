class Solution {
    /**
     * Approach : Using String Simulation Approach
     *
     * TC : O(n / 2) ~ O(n)
     * SC : O(1)
     */
    public int minChanges(String s) {
        int n = s.length();
        int changes = 0;
        for (int i = 1; i < n; i += 2) { // TC : O(n / 2)
            if (s.charAt(i) == s.charAt(i - 1)) {
                continue;
            }
            changes++;
        }
        return changes;
    }
}
