class Solution {
    /**
     * Approach : Using Array Simulation Approach
     *
     * TC : O(n) + O(n) ~ O(n)
     * SC : O(n)
     * as n1 + n2 + n3 = n
     */
    public int[] pivotArray(int[] nums, int pivot) {
        int n = nums.length;
        ArrayList<Integer> smaller = new ArrayList<>(); // SC : O(n1)
        ArrayList<Integer> mid = new ArrayList<>();     // SC : O(n2)
        ArrayList<Integer> larger = new ArrayList<>();  // SC : O(n3)
        for (int num : nums) { // TC : O(n)
            if (num < pivot) {
                smaller.add(num);
            } else if (num == pivot) {
                mid.add(num);
            } else {
                larger.add(num);
            }
        }
        int[] result = new int[n];
        int index = 0;
        int i = 0;
        int size = smaller.size();
        while (index < size) { // TC : O(n1)
            result[index++] = smaller.get(i++);
        }
        i = 0;
        size += mid.size();
        while (index < size) { // TC : O(n2)
            result[index++] = mid.get(i++);
        }
        i = 0;
        size += larger.size();
        while (index < size) { // TC : O(n3)
            result[index++] = larger.get(i++);
        }
        return result;
    }
}
