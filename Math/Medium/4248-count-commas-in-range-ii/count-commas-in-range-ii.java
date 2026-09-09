class Solution {
    /**
     * Approach : Using Math Approach
     *
     * TC : O(1)
     * SC : O(1)
     */
    public long countCommas(long n) {
        if (n < 1000) {
            return 0L;
        }
        long count = 0L;
        if (n >= 1e3) {
            // 1 comma numbers
            long end = Math.min(n, 999_999L);
            count += (end - 1_000L + 1);
        }
        if (n >= 1e6) {
            // 2 comma numbers
            long end = Math.min(n, 999_999_999L);
            count += (end - 1_000_000L + 1) * 2;
        }
        if (n >= 1e9) {
            // 3 comma numbers
            long end = Math.min(n, 999_999_999_999L);
            count += (end - 1_000_000_000L + 1) * 3;
        }
        if (n >= 1e12) {
            // 4 comma numbers
            long end = Math.min(n, 999_999_999_999_999L);
            count += (end - 1_000_000_000_000L + 1) * 4;
        }
        if (n == 1e15) {
            // 5 comma numbers
            count += 5;
        }
        return count;
    }
}
