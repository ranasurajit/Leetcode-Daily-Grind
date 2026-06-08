class Solution {
    /**
     * Approach III : Using Optimal (Two Pointers) Approach
     *
     * TC : O(n) + O(n) ~ O(n)
     * SC : O(1)
     */
    public int[] pivotArray(int[] nums, int pivot) {
        int n = nums.length;
        int[] result = new int[n];
        int pivotCount = 0;
        int index = 0;
        for (int i = 0; i < n; i++) { // TC : O(n)
            if (nums[i] < pivot) {
                result[index++] = nums[i];
            } else if (nums[i] == pivot) {
                pivotCount++;
            }
        }
        while (pivotCount > 0) {
            result[index++] = pivot;
            pivotCount--;
        }
        index = n - 1;
        for (int i = n - 1; i >= 0; i--) { // TC : O(n)
            if (nums[i] > pivot) {
                result[index--] = nums[i];
            }
        }
        return result;
    }

    /**
     * Approach II : Using Better (Two Pointers) Approach
     *
     * TC : O(n) + O(n) ~ O(n)
     * SC : O(1)
     */
    public int[] pivotArrayBetter(int[] nums, int pivot) {
        int n = nums.length;
        int smallCount = 0;
        int pivotCount = 0;
        int largeCount = 0;
        for (int num : nums) { // TC : O(n)
            if (num < pivot) {
                smallCount++;
            } else if (num == pivot) {
                pivotCount++;
            } else {
                largeCount++;
            }
        }
        int smallStart = 0;
        int pivotStart = smallStart + smallCount;
        int largeStart = pivotStart + pivotCount;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) { // TC : O(n)
            if (nums[i] < pivot) {
                result[smallStart++] = nums[i];
            } else if (nums[i] == pivot) {
                result[pivotStart++] = nums[i];
            } else {
                result[largeStart++] = nums[i];
            }
        }
        return result;
    }

    /**
     * Approach I : Using Brute-Force (Array Simulation) Approach
     *
     * TC : O(n) + O(n) ~ O(n)
     * SC : O(n)
     * as n1 + n2 + n3 = n
     */
    public int[] pivotArrayBruteForce(int[] nums, int pivot) {
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
