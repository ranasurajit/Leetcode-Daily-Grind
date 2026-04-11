class Solution {
    /**
     * Approach II : Using Better (Hashing) Approach
     *
     * TC: O(n)
     * SC: O(1)
     *
     * Time Limit Exceeded (941 / 979 testcases passed)
     */
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        int minDist = Integer.MAX_VALUE;
        Map<Integer, ArrayList<Integer>> map = new HashMap<>(); // SC: O(n)
        for (int i = 0; i < n; i++) { // TC: O(n)
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        for (Integer key : map.keySet()) { // TC: O(k)
            ArrayList<Integer> list = map.get(key);
            int size = list.size();
            if (size > 2) {
                int k = size - 1;
                while (k >= 2) { // TC: O(n / k)
                    int i = k - 2;
                    /**
                     * (j - i) + (k - j) + (k - i) = 2 * (k - i)
                     * as (k - j) + (j - i) = k - i
                     */
                    minDist = Math.min(minDist, 2 * (list.get(k) - list.get(i)));
                    k--;
                }
            }
        }
        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }

    /**
     * Approach I : Using Brute-Force (Array Simulation) Approach
     *
     * TC: O(n³)
     * SC: O(1)
     *
     * Time Limit Exceeded (941 / 979 testcases passed)
     */
    public int minimumDistanceBruteForce(int[] nums) {
        int n = nums.length;
        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < n - 2; i++) { // TC: O(n)
            for (int j = i + 1; j < n - 1; j++) { // TC: O(n)
                for (int k = j + 1; k < n; k++) { // TC: O(n)
                    if (nums[i] == nums[j] && nums[j] == nums[k]) {
                        /**
                         * (j - i) + (k - j) + (k - i) = 2 * (k - i)
                         * as (k - j) + (j - i) = k - i
                         */
                        minDist = Math.min(minDist, 2 * (k - i));
                    }
                }
            }
        }
        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }
}
