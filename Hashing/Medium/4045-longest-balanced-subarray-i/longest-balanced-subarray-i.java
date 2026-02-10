class Solution {
    /**
     * Approach II : Using Better (Two Pointers + Hashing) Approach
     *
     * TC: O(N²)
     * SC: O(N)
     */
    public int longestBalanced(int[] nums) {
        int n = nums.length;
        int maxLength = 0;
        Set<Integer> seen = new HashSet<Integer>(); // SC: O(N)
        for (int i = 0; i < n; i++) {     // TC: O(N)
            int balance = 0; // reset balance on every index 'i' shift
            seen.clear();
            for (int j = i; j < n; j++) { // TC: O(N)
                if (!seen.contains(nums[j])) {
                    // to determine if nums[j] was earlier seen so balance would not be changed
                    if ((nums[j] & 1) == 0) {
                        balance++;
                    } else {
                        balance--;
                    }
                }
                if (balance == 0) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
                seen.add(nums[j]);
            }
        }
        return maxLength;
    }

    /**
     * Approach I : Using Brute-Force (Two Pointers + Hashing) Approach
     *
     * TC: O(N²)
     * SC: O(N1 + N2) ~ O(N)
     */
    public int longestBalancedBruteForce(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> evenMap = new HashMap<Integer, Integer>(); // SC: O(N1)
        Map<Integer, Integer> oddMap = new HashMap<Integer, Integer>();  // SC: O(N2)
        int maxLength = 0;
        for (int i = 0; i < n; i++) {     // TC: O(N)
            evenMap.clear();
            oddMap.clear();
            for (int j = i; j < n; j++) { // TC: O(N)
                if ((nums[j] & 1) == 0) {
                    evenMap.put(nums[j], evenMap.getOrDefault(nums[j], 0) + 1);
                } else {
                    oddMap.put(nums[j], oddMap.getOrDefault(nums[j], 0) + 1);
                }
                if (evenMap.size() == oddMap.size()) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }
        return maxLength;
    }
}
