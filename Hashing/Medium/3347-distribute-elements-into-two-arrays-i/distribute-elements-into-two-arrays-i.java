class Solution {
    /**
     * Approach : Using Simulation Approach
     *
     * TC : O(n) + O(n1) + O(n2) ~ O(n)
     * SC : O(n1) + O(n2) ~ O(n)
     */
    public int[] resultArray(int[] nums) {
        int n = nums.length;
        List<Integer> arr1 = new ArrayList<>(); // SC : O(n1)
        List<Integer> arr2 = new ArrayList<>(); // SC : O(n2)
        arr1.add(nums[0]);
        arr2.add(nums[1]);
        int index = 2;
        int p = 0; // current pointer pointing at last index of 'arr1'
        int q = 0; // current pointer pointing at last index of 'arr2'
        while (index < n) { // TC : O(n)
            if (arr1.get(p) > arr2.get(q)) {
                arr1.add(nums[index]);
                p++;
            } else {
                arr2.add(nums[index]);
                q++;
            }
            index++;
        }
        p = 0;
        q = 0;
        index = 0;
        int[] result = new int[n]; // SC : O(n)
        while (p < arr1.size()) {  // TC : O(n1)
            result[index++] = arr1.get(p++);
        }
        while (q < arr2.size()) {  // TC : O(n2)
            result[index++] = arr2.get(q++);
        }
        return result;
    }
}
