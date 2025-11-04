class Solution {
    /**
     * Approach : Using Sliding Window (Fixed Size) + Hashing + Max-Heap Approach
     *
     * TC: O(N x K x log(K))
     * SC: O(N + K)
     */
    public int[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        int i = 0; // start pointer of sliding window
        int j = 0; // end pointer of sliding window
        Map<Integer, Integer> freq = new HashMap<Integer, Integer>(); // SC: O(N)
        int index = 0;
        int[] result = new int[n - k + 1];
        while (j < n) { // TC: O(N)
            freq.put(nums[j], freq.getOrDefault(nums[j], 0) + 1);
            if (j - i + 1 == k) {
                // sliding window formed here
                result[index++] = getSumOfXTopElements(freq, x); // TC: O(K x log(K)), SC: O(K)
                // slide to next window
                // remove computation from index 'i'
                freq.put(nums[i], freq.getOrDefault(nums[i], 0) - 1);
                // clean-up Map when nums[i] has 0 frequency
                if (freq.get(nums[i]) == 0) {
                    freq.remove(nums[i]);
                }
                // slide the window
                i++;
            }
            j++;
        }
        return result;
    }

    /**
     * Using Max-Heap Approach
     *
     * TC: O(K x log(K)) + O(Max(X, K)) ~ O(K x log(K))
     * SC: O(K)
     */
    private int getSumOfXTopElements(Map<Integer, Integer> freq, int x) {
        // we will store { num, freq } in the Max-Heap
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((p, q) -> {
            if (p[1] == q[1]) {
                // frequencies of two numbers are same then return number with higher value
                return q[0] - p[0];
            }
            // return number with higher frequency
            return q[1] - p[1];
        }); // SC: O(K)
        for (Integer key : freq.keySet()) { // TC: O(K)
            pq.offer(new int[] { key, freq.get(key) }); // TC: O(log(K))
        }
        int sum = 0;
        while (!pq.isEmpty() && x-- > 0) { // TC: O(Max(X, K))
            int[] current = pq.poll();
            sum += current[0] * current[1];
        }
        return sum;
    }
}
