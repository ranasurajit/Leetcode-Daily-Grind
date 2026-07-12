class Solution {
    /**
     * Approach : Using Sorting Approach
     *
     * TC : O(n) + O(n x log(n)) + O(n) + O(n) ~ O(n x log(n))
     * SC : O(n)
     */
    public int[] arrayRankTransform(int[] arr) {
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
        int last = nums[0][0];
        nums[0][0] = 1;
        for (int i = 1; i < n; i++) { // TC : O(n)
            int temp = nums[i][0];
            nums[i][0] =
                (nums[i][0] == last) ? nums[i - 1][0] : 1 + nums[i - 1][0];
            last = temp;
        }
        for (int i = 0; i < n; i++) { // TC : O(n)
            int rank = nums[i][0];
            int index = nums[i][1];
            arr[index] = rank;
        }
        return arr;
    }
}
