class Solution {
    /**
     * Approach: Sliding Window + Monotonic Deque
     *
     * TC: O(n)
     * SC: O(n)
     */
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;

        Deque<Integer> deque = new ArrayDeque<>();

        long operations = 0;
        long count = 0;

        int right = n - 1;

        // Traverse from right to left
        for (int left = n - 1; left >= 0; left--) {

            // Merge smaller values into nums[left]
            while (!deque.isEmpty()
                    && nums[deque.peekLast()] < nums[left]) {

                int index = deque.pollLast();

                int nextIndex = deque.isEmpty()
                        ? right + 1
                        : deque.peekLast();

                operations += (long) (nextIndex - index)
                        * (nums[left] - nums[index]);
            }

            deque.offerLast(left);

            // Shrink window if operations exceed k
            while (operations > k) {

                operations -= nums[deque.peekFirst()]
                        - nums[right];

                if (deque.peekFirst() == right) {
                    deque.pollFirst();
                }

                right--;
            }

            // All subarrays [left...r], r <= right, are valid
            count += right - left + 1;
        }

        return count;
    }
}
