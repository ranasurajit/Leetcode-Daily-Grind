class Solution {
    /**
     * Approach : Using String Simulation + Math Approach
     *
     * TC : O(n) + O(n²) ~ O(n²)
     * SC : O(n)
     */
    public String getPermutation(int n, int k) {
        List<Integer> nums = new ArrayList<>(); // SC : O(n)
        int fact = 1;
        for (int i = 1; i < n; i++) { // TC : O(n)
            fact = fact * i;
            nums.add(i);
        }
        nums.add(n);
        k = k - 1; // for 0 based index
        // nums already contains the number with k = 0th permutation sequence
        StringBuilder sb = new StringBuilder();
        while (!nums.isEmpty()) { // TC : O(n)
            int idx = k / fact;
            sb.append(nums.get(idx));
            nums.remove(idx);    // TC : O(n)
            k = k % fact;
            if (nums.size() > 0) {
                fact = fact / nums.size();
            }
        }
        return sb.toString();
    }
}
