class Solution {

    static class MedianWindow {
        TreeMap<Integer, Integer> low = new TreeMap<>();
        TreeMap<Integer, Integer> high = new TreeMap<>();

        int lowSize = 0, highSize = 0;
        long lowSum = 0, highSum = 0;

        private void addValue(TreeMap<Integer, Integer> map, int num) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        private void removeValue(TreeMap<Integer, Integer> map, int num) {
            int count = map.get(num);

            if (count == 1) {
                map.remove(num);
            } else {
                map.put(num, count - 1);
            }
        }

        private void addLow(int num) {
            addValue(low, num);
            lowSize++;
            lowSum += num;
        }

        private void addHigh(int num) {
            addValue(high, num);
            highSize++;
            highSum += num;
        }

        private void removeLow(int num) {
            removeValue(low, num);
            lowSize--;
            lowSum -= num;
        }

        private void removeHigh(int num) {
            removeValue(high, num);
            highSize--;
            highSum -= num;
        }

        private void balance() {
            while (lowSize > highSize + 1) {
                int num = low.lastKey();
                removeLow(num);
                addHigh(num);
            }

            while (lowSize < highSize) {
                int num = high.firstKey();
                removeHigh(num);
                addLow(num);
            }
        }

        public void add(int num) {
            if (lowSize == 0 || num <= low.lastKey()) {
                addLow(num);
            } else {
                addHigh(num);
            }

            balance();
        }

        public void remove(int num) {
            if (low.containsKey(num)) {
                removeLow(num);
            } else {
                removeHigh(num);
            }

            balance();
        }

        public long getCost() {
            long median = low.lastKey();

            return median * lowSize - lowSum
                 + highSum - median * highSize;
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;

        // Step 1: Calculate cost of each window of size x
        long[] cost = new long[n - x + 1];

        MedianWindow window = new MedianWindow();

        for (int i = 0; i < n; i++) {
            window.add(nums[i]);

            if (i >= x) {
                window.remove(nums[i - x]);
            }

            if (i >= x - 1) {
                cost[i - x + 1] = window.getCost();
            }
        }

        // Step 2: DP to select k non-overlapping windows
        long INF = Long.MAX_VALUE / 4;

        long[] prev = new long[n + 1];

        for (int j = 1; j <= k; j++) {
            long[] curr = new long[n + 1];
            Arrays.fill(curr, INF);

            for (int i = x; i <= n; i++) {

                // Skip current element
                curr[i] = curr[i - 1];

                // Take window ending at i - 1
                if (prev[i - x] != INF) {
                    curr[i] = Math.min(
                        curr[i],
                        prev[i - x] + cost[i - x]
                    );
                }
            }

            prev = curr;
        }

        return prev[n];
    }
}
