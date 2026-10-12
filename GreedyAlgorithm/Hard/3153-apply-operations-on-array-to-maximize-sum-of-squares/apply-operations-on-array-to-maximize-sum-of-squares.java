class Solution {
    public int maxSum(List<Integer> nums, int k) {
        final long MOD = 1_000_000_007L;
        int[] bitCount = new int[32];

        // Count set bits at every position
        for (int num : nums) {
            for (int bit = 0; bit < 32; bit++) {
                if ((num & (1 << bit)) != 0) {
                    bitCount[bit]++;
                }
            }
        }

        long ans = 0;

        // Construct k maximum numbers greedily
        for (int i = 0; i < k; i++) {
            long num = 0;

            for (int bit = 0; bit < 32; bit++) {
                if (bitCount[bit] > 0) {
                    num |= (1L << bit);
                    bitCount[bit]--;
                }
            }

            ans = (ans + num * num) % MOD;
        }

        return (int) ans;
    }
}
