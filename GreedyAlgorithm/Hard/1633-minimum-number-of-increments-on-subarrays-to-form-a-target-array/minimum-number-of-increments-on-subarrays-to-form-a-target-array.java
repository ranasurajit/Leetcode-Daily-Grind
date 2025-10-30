class Solution {
    /**
     * Approach : Using Greedy Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public int minNumberOperations(int[] target) {
        int n = target.length;
        int prev = 0;
        int operations = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (target[i] > prev) {
                operations += target[i] - prev;
            }
            prev = target[i];
        }
        return operations;
    }
}
