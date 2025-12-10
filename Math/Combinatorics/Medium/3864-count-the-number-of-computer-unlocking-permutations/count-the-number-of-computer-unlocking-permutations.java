class Solution {
    private static final int MOD = (int) 1e9 + 7;

    /**
     * Approach : Using Math + Combinatorics Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public int countPermutations(int[] complexity) {
        int n = complexity.length;
        long count = 1L;
        for (int i = 1; i < n; i++) { // TC: O(N)
            if (complexity[i] <= complexity[0]) {
                // not possible to unlock all computers
                return 0;
            }
            count = (count * i) % MOD;
        }
        return (int) count;
    }
}
