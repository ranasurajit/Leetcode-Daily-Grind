class Solution {
    /**
     * Approach II : Using Sorting + Hashing Approach
     *
     * TC : O(n x log(n)) + O(n) + O(n) ~ O(n x log(n))
     * SC : O(n) + O(n) ~ O(n)
     */
    public int[] arrayRankTransform(int[] arr) {
        int n = arr.length;
        if (n == 0) {
            return arr;
        }
        int[] sorted = arr.clone(); // SC : O(n)
        Arrays.sort(sorted); // TC : O(n x log(n))
        int rank = 1;
        // Creating HashMap of num and rank
        Map<Integer, Integer> numsRankMap = new HashMap<>(); // SC : O(n)
        for (int x : sorted) { // TC : O(n)
            if (!numsRankMap.containsKey(x)) {
                numsRankMap.put(x, rank++);
            }
        }
        for (int i = 0; i < n; i++) { // TC : O(n)
            arr[i] = numsRankMap.get(arr[i]);
        }
        return arr;
    }

    /**
     * Approach I : Using Brute-Force (Sorting) Approach
     *
     * TC : O(n) + O(n x log(n)) + O(n) + O(n) ~ O(n x log(n))
     * SC : O(n)
     */
    public int[] arrayRankTransformBruteForce(int[] arr) {
        int n = arr.length;
        if (n == 0) {
            return arr;
        }
        int[][] nums = new int[n][2]; // SC : O(n)
        for (int i = 0; i < n; i++) { // TC : O(n)
            nums[i][0] = arr[i];
            nums[i][1] = i;
        }
        Arrays.sort(nums,
            (a, b) -> Integer.compare(a[0],b[0])); // TC : O(n x log(n))
        int rank = 1;
        int prev = nums[0][0];
        nums[0][0] = rank;
        for (int i = 1; i < n; i++) { // TC : O(n)
            if (nums[i][0] != prev) {
                rank++;
                prev = nums[i][0];
            }
            nums[i][0] = rank;
        }
        for (int i = 0; i < n; i++) { // TC : O(n)
            arr[nums[i][1]] = nums[i][0];
        }
        return arr;
    }
}
