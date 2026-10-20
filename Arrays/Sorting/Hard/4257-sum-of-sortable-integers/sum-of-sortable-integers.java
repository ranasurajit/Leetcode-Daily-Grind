class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        long ans = 0;

        for (int d = 1; d * d <= n; d++) {
            if (n % d != 0) {
                continue;
            }

            // d is a divisor
            if (isSortable(nums, d)) {
                ans += d;
            }

            // n / d is the paired divisor
            int other = n / d;

            if (other != d && isSortable(nums, other)) {
                ans += other;
            }
        }

        return (int) ans;
    }

    private boolean isSortable(int[] nums, int k) {
        int n = nums.length;

        int prevMax = Integer.MIN_VALUE;

        for (int start = 0; start < n; start += k) {

            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            int descents = 0;

            // Find min/max and ordinary descents inside this block
            for (int i = start; i < start + k; i++) {
                min = Math.min(min, nums[i]);
                max = Math.max(max, nums[i]);

                if (i + 1 < start + k && nums[i] > nums[i + 1]) {
                    descents++;

                    if (descents > 1) {
                        return false;
                    }
                }
            }

            /*
             * Since the block is circular under rotation,
             * also check the edge:
             *
             * last -> first
             */
            if (k > 1 && nums[start + k - 1] > nums[start]) {
                descents++;
            }

            // More than one circular descent means no rotation can sort it.
            if (descents > 1) {
                return false;
            }

            /*
             * Once this block is rotated into sorted order,
             * it becomes:
             *
             * min ... max
             *
             * For the whole array to be sorted, the previous
             * block's maximum must not exceed this block's minimum.
             */
            if (prevMax > min) {
                return false;
            }

            prevMax = max;
        }

        return true;
    }
}
