class Solution {
    /**
     * Approach : Using Arrays + Number Theory Approach
     *
     * TC: O(N x log(K)) + O(N) ~ O(N x log(K))
     * SC: O(log(K))
     *
     * where K = Min(nums)
     */
    public List<Integer> replaceNonCoprimes(int[] nums) {
        int n = nums.length;
        List<Integer> coprimeList = new ArrayList<Integer>(); // SC: O(N)
        for (int i = 0; i < n; i++) {
            while (!coprimeList.isEmpty()) {
                int size = coprimeList.size();
                int prev = coprimeList.get(size - 1);
                int gcdValue = gcd(prev, nums[i]); // TC: O(log(Min(a, b))), SC: O(log(Min(a, b)))
                if (gcdValue == 1) {
                    break;
                }
                coprimeList.remove(size - 1);
                int lcm = (prev / gcdValue) * nums[i];
                nums[i] = lcm;
            }
            coprimeList.add(nums[i]);
        }
        return coprimeList;
    }

    /**
     * Using Math Approach
     *
     * TC: O(log(Min(a, b)))
     * SC: O(log(Min(a, b)))
     */
    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
