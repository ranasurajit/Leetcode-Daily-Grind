class Solution {

    public int minSwaps(int[] nums) {
        int n = nums.length;

        // Copy nums so that we can create the required sorted order.
        Integer[] sorted = new Integer[n];

        for (int i = 0; i < n; i++) {
            sorted[i] = nums[i];
        }

        // Sort by:
        // 1. Digit sum
        // 2. Value itself if digit sums are equal
        Arrays.sort(sorted, (a, b) -> {
            int sumA = digitSum(a);
            int sumB = digitSum(b);

            if (sumA != sumB) {
                return Integer.compare(sumA, sumB);
            }

            return Integer.compare(a, b);
        });

        // Store where every number needs to go.
        Map<Integer, Integer> targetIndex = new HashMap<>();

        for (int i = 0; i < n; i++) {
            targetIndex.put(sorted[i], i);
        }

        boolean[] visited = new boolean[n];
        int swaps = 0;

        // Find cycles in the permutation.
        for (int i = 0; i < n; i++) {

            // Already processed or already at correct position.
            if (visited[i] || targetIndex.get(nums[i]) == i) {
                continue;
            }

            int cycleSize = 0;
            int j = i;

            while (!visited[j]) {
                visited[j] = true;

                // nums[j] needs to move to its target index.
                j = targetIndex.get(nums[j]);

                cycleSize++;
            }

            // Cycle of size k requires k - 1 swaps.
            swaps += cycleSize - 1;
        }

        return swaps;
    }

    private int digitSum(int num) {
        int sum = 0;

        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }

        return sum;
    }
}
