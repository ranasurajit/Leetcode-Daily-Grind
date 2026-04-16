class Solution {
    /**
     * Approach II : Using Optimal (Hashing + Binary Search) Approach
     *
     * TC: O(n) + O(q x log(n)) ~ O(n + q x log(n))
     * SC: O(n)
     *
     * Accepted (614 / 614 testcases passed)
     */
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        int m = queries.length;
        Map<Integer, ArrayList<Integer>> map = new HashMap<>(); // SC: O(n)
        for (int i = 0; i < n; i++) { // TC: O(n)
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < m; i++) { // TC: O(q)
            int q = queries[i];
            ArrayList<Integer> list = map.get(nums[q]);
            if (list.size() == 1) {
                // no other nums[q] is found at any other index
                result.add(-1);
                continue;
            }
            int size = list.size();
            int low = 0;
            int high = size - 1;
            int pos = -1;
            while (low <= high) { // TC: O(log(n))
                int mid = low + (high - low) / 2;
                int target = list.get(mid);
                if (target == q) {
                    pos = mid;
                    break;
                } else if (target > q) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            int leftIdx = list.get((pos - 1 + size) % size);
            int rightIdx = list.get((pos + 1) % size);
            int dist1 = Math.abs(leftIdx - q);
            dist1 = Math.min(dist1, n - dist1);
            int dist2 = Math.abs(rightIdx - q);
            dist2 = Math.min(dist2, n - dist2);
            result.add(Math.min(dist1, dist2));
        }
        return result;
    }

    /**
     * Approach I : Using Brute-Force (Array Simulation) Approach
     *
     * TC: O(q x n)
     * SC: O(1)
     *
     * Time Limit Exceeded (608 / 614 testcases passed)
     */
    public List<Integer> solveQueriesBruteForce(int[] nums, int[] queries) {
        int n = nums.length;
        int m = queries.length;
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < m; i++) { // TC: O(q)
            int q = queries[i];
            result.add(-1);
            for (int j = 1; j < n; j++) { // TC: O(n)
                int leftIdx = (q - j + n) % n;
                int rightIdx = (q + j) % n;
                if (leftIdx >= 0 && nums[leftIdx] == nums[q]) {
                    result.set(i, j);
                    break;
                }
                if (rightIdx < n && nums[rightIdx] == nums[q]) {
                    result.set(i, j);
                    break;
                }
            }
        }
        return result;
    }
}
