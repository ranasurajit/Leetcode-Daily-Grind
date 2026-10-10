class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] diff = new int[n];

        int maxDiff = 0;
        long totalDiff = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            maxDiff = Math.max(maxDiff, diff[i]);
            totalDiff += diff[i];
        }

        long k = (long) k1 + k2;

        // All differences can be reduced to zero.
        if (k >= totalDiff) {
            return 0L;
        }

        // Find the minimum possible maximum difference.
        int low = 0, high = maxDiff;

        while (low < high) {
            int mid = low + (high - low) / 2;
            long needed = 0;

            for (int d : diff) {
                if (d > mid) {
                    needed += d - mid;
                }
            }

            if (needed <= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        int level = low;
        long used = 0;
        long answer = 0;
        long countAtLevel = 0;

        for (int d : diff) {
            if (d > level) {
                used += d - level;
            }

            int finalDiff = Math.min(d, level);
            answer += (long) finalDiff * finalDiff;

            if (d >= level) {
                countAtLevel++;
            }
        }

        // Distribute leftover operations among differences
        // that are still equal to level.
        long remaining = k - used;
        answer -= remaining * (2L * level - 1);

        return answer;
    }
}
