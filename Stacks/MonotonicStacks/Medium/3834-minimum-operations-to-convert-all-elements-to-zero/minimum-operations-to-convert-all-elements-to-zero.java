class Solution {
    /**
     * Approach II : Using Monotonic Increasing Stack Approach
     *
     * TC: O(N)
     * SC: O(K)
     *
     * where K = size(uniques(nums))
     *
     * Accepted (968 / 968 testcases passed)
     */
    public int minOperations(int[] nums) {
        int n = nums.length;
        Stack<Integer> st = new Stack<Integer>(); // SC: O(K)
        int i = 0;
        int operations = 0;
        while (i < n) { // TC: O(N)
            while (!st.isEmpty() && nums[i] < st.peek()) {
                st.pop();
            }
            if (st.isEmpty() || nums[i] > st.peek()) {
                if (nums[i] > 0) {
                    st.push(nums[i]);
                    operations++;
                }
            }
            i++;
        }
        return operations;
    }

    /**
     * Approach I : Using Hashing Approach
     *
     * TC: O(N) + O(K x N) ~ O(K x N)
     * SC: O(K)
     *
     * where K = size(uniques(nums))
     *
     * Time Limit Exceeded (946 / 968 testcases passed)
     */
    public int minOperationsHashingApproach(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<Integer>(); // SC: O(K)
        for (int num : nums) { // TC: O(N)
            set.add(num);
        }
        int operations = 0;
        for (Integer key : set) { // TC: O(K)
            boolean flow = false;
            if (key == 0) {
                // need not execute any operation
                continue;
            }
            for (int i = 0; i < n; i++) { // TC: O(N)
                if (nums[i] < key) {
                    // continuity broke here
                    flow = false;
                } else {
                    if (nums[i] == key) {
                        if (!flow) {
                            flow = true;
                            operations++;
                        }
                    }
                }
            }
        }
        return operations;
    }
}
