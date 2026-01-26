class Solution {
    /**
     * Approach : Using Sorting + Array Simulation Approach
     *
     * TC: O(N x log(N)) + O(N) + O(N) ~ O(N x log(N))
     * SC: O(1)
     */
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        int n = arr.length;
        /**
         * here we just need the pairs in ascending order
         * so maintaining the access order in array 'arr' 
         * is not needed, so we can sort the array 'arr' 
         */
        Arrays.sort(arr); // TC: O(N x log(N))
        int minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) { // TC: O(N)
            minDiff = Math.min(minDiff, arr[i] - arr[i - 1]);
        }
        List<List<Integer>> pairsList = new ArrayList<List<Integer>>();
        for (int i = 1; i < n; i++) { // TC: O(N)
            if (arr[i] - arr[i - 1] == minDiff) {
                pairsList.add(Arrays.asList(arr[i - 1], arr[i]));
            }
        }
        return pairsList;
    }
}
