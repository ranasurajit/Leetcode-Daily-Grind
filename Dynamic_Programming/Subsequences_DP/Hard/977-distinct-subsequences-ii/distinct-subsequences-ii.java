class Solution {
    public int distinctSubseqII(String s) {
        final long MOD = 1_000_000_007L;

        // last[i] = dp value before the previous occurrence
        // of character ('a' + i)
        long[] last = new long[26];

        // Number of distinct subsequences including ""
        long dp = 1;

        for (char ch : s.toCharArray()) {
            int index = ch - 'a';

            long newDp = (2 * dp - last[index] + MOD) % MOD;

            // Save the old dp for this character
            last[index] = dp;

            dp = newDp;
        }

        // Remove the empty subsequence
        return (int) ((dp - 1 + MOD) % MOD);
    }
}
