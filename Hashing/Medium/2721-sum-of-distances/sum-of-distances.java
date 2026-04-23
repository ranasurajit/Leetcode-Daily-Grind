class Solution {
    /**
     * Approach II : Using Optimal (Hashing + Prefix-Sum) Approach
     *
     * TC : O(n) + O(n) ~ O(n)
     * SC : O(n)
     *
     * Accepted (1068 / 1068 testcases passed)
     */
    public long[] distance(int[] nums) {
        int n = nums.length;
        Map<Integer, ArrayList<Integer>> map = new HashMap<>(); // SC: O(n)
        for (int i = 0; i < n; i++) { // TC : O(n)
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        long[] arr = new long[n];
        for (ArrayList<Integer> list : map.values()) { // TC : O(k)
            int size = list.size();
            if (size == 1) {
                continue;
            }
            long[] prefixSum = new long[size];
            prefixSum[0] = list.get(0);
            for (int j = 1; j < size; j++) { // TC : O(n / k)
                prefixSum[j] = prefixSum[j - 1] + list.get(j);
            }
            for (int k = 0; k < size; k++) { // TC : O(n / k)
                int idx = list.get(k);
                /**
                 * left  = k * idx - sum(left indices)
                 * right = sum(right indices) - (countRight * idx)
                 */
                long leftSum = ((long) k * idx) - (k > 0 ? prefixSum[k - 1] : 0);
                long rightSum = (prefixSum[size - 1] - prefixSum[k]) -
                    (size - k - 1) * (long) idx;
                arr[idx] = leftSum + rightSum;
            }
        }
        return arr;
    }

    /**
     * Approach I : Using Brute-Force (Hashing + Simulation) Approach
     *
     * TC : O(n) + O(n²) ~ O(n²)
     * SC : O(n)
     *
     * Time Limit Exceeded (1065 / 1068 testcases passed)
     */
    public long[] distanceBruteForce(int[] nums) {
        int n = nums.length;
        Map<Integer, ArrayList<Integer>> map = new HashMap<>(); // SC: O(n)
        for (int i = 0; i < n; i++) { // TC : O(n)
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        long[] arr = new long[n]; 
        for (int i = 0; i < n; i++) { // TC : O(n)
            long sum = 0L;
            ArrayList<Integer> list = map.get(nums[i]);
            if (list.size() == 1) {
                continue;
            }
            for (Integer idx : list) { // TC : O(n)
                sum += Math.abs(idx - i);
            }
            arr[i] = sum;
        }
        return arr;
    }
}
