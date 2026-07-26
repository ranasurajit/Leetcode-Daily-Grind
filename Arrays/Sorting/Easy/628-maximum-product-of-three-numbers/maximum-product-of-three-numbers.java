class Solution {
    /**
     * Approach III : Using Array Simulation Approach
     *
     * TC : O(n)
     * SC : O(1)
     */
    public int maximumProduct(int[] nums) {
        int n = nums.length;
        int firstMax = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;
        int thirdMax = Integer.MIN_VALUE;
        int firstMin = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) { // TC : O(n)
            // compare and store the 3 max values
            if (nums[i] > firstMax) {
                thirdMax = secondMax;
                secondMax = firstMax;
                firstMax = nums[i];
            } else if (nums[i] > secondMax) {
                thirdMax = secondMax;
                secondMax = nums[i];
            } else if (nums[i] > thirdMax) {
                thirdMax = nums[i];
            }
            // compare and store the 3 max values
            if (nums[i] < firstMin) {
                secondMin = firstMin;
                firstMin = nums[i];
            } else if (nums[i] < secondMin) {
                secondMin = nums[i];
            }
        }
        return Math.max(
            firstMax * secondMax * thirdMax,
            Math.max(
                firstMin * secondMin * firstMax,
                firstMin * secondMax * firstMax
            )
        );
    }

    /**
     * Approach II : Using HashMap Approach
     *
     * TC : O(n) + O(2000) + O(2000) ~ O(n)
     * SC : O(2001) + O(2001) + O(6) ~ O(1)
     */
    public int maximumProductHashMap(int[] nums) {
        int[] numsFirstMap = new int[2001]; // SC : O(2001)
        int[] numsLastMap = new int[2001];  // SC : O(2001)
        for (int x : nums) { // TC : O(n)
            numsFirstMap[x + 1000]++; // adding offset of 1000
            numsLastMap[x + 1000]++;  // adding offset of 1000
        }
        int[] ranges = new int[6]; // SC : O(6)
        int k = 0;
        int start = 0;
        while (k < 3 && start < 2000) { // TC : O(2000)
            while (k < 3 && numsFirstMap[start] > 0) {
                ranges[k] = start - 1000; // removing offset
                numsFirstMap[start]--;
                k++;
            }
            start++;
        }
        k = 5;
        start = 2000;
        while (k > 2 && start >= 0) { // TC : O(2000)
            while (k > 2 && numsLastMap[start] > 0) {
                ranges[k] = start - 1000; // removing offset
                numsLastMap[start]--;
                k--;
            }
            start--;
        }
        return Math.max(
            ranges[0] * ranges[1] * ranges[5],
            Math.max(
                ranges[3] * ranges[4] * ranges[5],
                ranges[0] * ranges[1] * ranges[2]
            )
        );
    }

    /**
     * Approach I : Using Sorting Approach
     *
     * TC : O(n x log(n))
     * SC : O(1)
     */
    public int maximumProductSorting(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums); // TC : O(n x log(n))
        int firstMax = nums[n - 1];
        int secondMax = nums[n - 2];
        int thirdMax = nums[n - 3];
        int firstMin = nums[0];
        int secondMin = nums[1];
        int thirdMin = nums[2];
        return Math.max(
            firstMin * secondMin * firstMax,
            Math.max(
                secondMax * thirdMax * firstMax,
                firstMin * secondMin * thirdMin
            )
        );
    }
}
