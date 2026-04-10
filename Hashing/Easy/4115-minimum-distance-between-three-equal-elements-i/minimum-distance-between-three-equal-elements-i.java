class Solution {
    /**
     * Approach III : Using Optimal (Hashing) Approach
     *
     * TC: O(n)
     * SC: O(n) + O(n) ~ O(n)
     */
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        int minDist = Integer.MAX_VALUE;
        int[] last = new int[n + 1];   // SC: O(n)
        int[] secondLast = new int[n + 1]; // SC: O(n)
        Arrays.fill(last, -1);
        Arrays.fill(secondLast, -1);
        for (int i = 0; i < n; i++) {  // TC: O(n)
            int val = nums[i];
            if (secondLast[val] != -1) {
                /**
                 * the index is filled now we can compare
                 * (j - i) + (k - j) + (k - i) is
                 * dependent on (k - i) as (j - i) + (k - j) = (k - i)
                 */
                minDist = Math.min(minDist, 2 * (i - secondLast[val]));
            }
            secondLast[val] = last[val];
            last[val] = i;
        }
        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }

    /**
     * Approach II : Using Better (Hashing) Approach
     *
     * TC: O(n) + O(k x n) ~ O(n²)
     * SC: O(n)
     */
    public int minimumDistanceBetter(int[] nums) {
        int n = nums.length;
        int minDist = Integer.MAX_VALUE;
        Map<Integer, ArrayList<Integer>> map = new HashMap<>(); // SC: O(n)
        for (int i = 0; i < n; i++) {         // TC: O(n)
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        for (Integer key : map.keySet()) { // TC: O(k)
            ArrayList<Integer> indices = map.get(key);
            int m = indices.size();
            if (m >= 3) {
                int k = m - 1;
                while (k >= 2) { // TC: O(n)
                    int j = k - 1;
                    int i = k - 2;
                    int current = (indices.get(j) - indices.get(i)) + 
                        (indices.get(k) - indices.get(j)) +
                        (indices.get(k) - indices.get(i));
                    minDist = Math.min(minDist, current);
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
     */
    public int minimumDistanceBruteForce(int[] nums) {
        int n = nums.length;
        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < n - 2; i++) {         // TC: O(n)
            for (int j = i + 1; j < n - 1; j++) { // TC: O(n)
                for (int k = j + 1; k < n; k++) { // TC: O(n)
                    if (nums[i] == nums[j] && nums[j] == nums[k]) {
                        int current = (j - i) + (k - j) + (k - i);
                        minDist = Math.min(minDist, current);
                    }
                }
            }
        }
        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }
}
