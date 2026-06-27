class Solution {
    /**
     * Approach : Using Hashing Approach
     *
     * TC : O(n) + O(k) ~ O(n)
     * SC : O(k) ~ O(n)
     */
    public int maximumLength(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        Map<Long, Integer> freq = new HashMap<>(); // SC : O(k)
        long max = 0L;
        for (int num : nums) { // TC : O(n)
            freq.put((long) num, freq.getOrDefault((long) num, 0) + 1);
            max = Math.max(max, (long) num);
        }
        int length = 0;
        // handle for 1
        if (freq.containsKey(1L)) {
            int count = freq.get(1L);
            length = Math.max(length, (count % 2 == 0) ? count - 1 : count);
        }
        for (long start : freq.keySet()) { // TC : O(k)
            if (start == 1L) {
                continue;
            }
            long current = start;
            int size = 0;
            while (freq.getOrDefault(current, 0) >= 2) {
                size += 2;
                if (current > max) {
                    break;
                }
                current = current * current;
            }
            if (freq.getOrDefault(current, 0) >= 1) {
                // we need 1 count of highest digit to be in mid
                size++;
            } else {
                // if not found we can use existing 1 highest and decrese its size
                size--;
            }
            length = Math.max(length, size);
        }
        return length;
    }
}
