class Solution {

    private static final long MOD1 = 1_000_000_007L;
    private static final long MOD2 = 1_000_000_009L;
    private static final long BASE = 131L;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        long[] prefix1 = new long[n + 1];
        long[] prefix2 = new long[n + 1];

        long[] power1 = new long[n + 1];
        long[] power2 = new long[n + 1];

        power1[0] = 1;
        power2[0] = 1;

        // Precompute prefix hashes and powers
        for (int i = 0; i < n; i++) {

            prefix1[i + 1] =
                (prefix1[i] * BASE + nums[i]) % MOD1;

            prefix2[i + 1] =
                (prefix2[i] * BASE + nums[i]) % MOD2;

            power1[i + 1] = (power1[i] * BASE) % MOD1;
            power2[i + 1] = (power2[i] * BASE) % MOD2;
        }

        int low = 1;
        int high = n;
        int ans = n;

        // Binary search on subarray length
        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (hasUniqueSubarray(n, mid,
                    prefix1, prefix2, power1, power2)) {

                ans = mid;
                high = mid - 1;

            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private boolean hasUniqueSubarray(
            int n, int len,
            long[] prefix1, long[] prefix2,
            long[] power1, long[] power2) {

        HashMap<Long, Integer> map = new HashMap<>();

        for (int i = 0; i + len <= n; i++) {

            int j = i + len;

            long hash1 = (prefix1[j]
                - (prefix1[i] * power1[len]) % MOD1
                + MOD1) % MOD1;

            long hash2 = (prefix2[j]
                - (prefix2[i] * power2[len]) % MOD2
                + MOD2) % MOD2;

            // Combine both hashes into a single key
            long key = (hash1 << 32) | hash2;

            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        // Check if any subarray occurs exactly once
        for (int count : map.values()) {
            if (count == 1) {
                return true;
            }
        }

        return false;
    }
}
