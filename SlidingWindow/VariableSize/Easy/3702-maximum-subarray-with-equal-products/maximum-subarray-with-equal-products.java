class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int[] primes = {2, 3, 5, 7};
        int[] freq = new int[11];
        int i = 0;
        int maxLength = 2;
        for (int j = 0; j < n; j++) {
            // Expand the window
            for (int prime : primes) {
                if (nums[j] % prime == 0) {
                    freq[prime]++;
                }
            }
            // Shrink the window if it is not pairwise coprime
            while (freq[2] > 1 || freq[3] > 1 ||
                freq[5] > 1 || freq[7] > 1) {
                for (int prime : primes) {
                    if (nums[i] % prime == 0) {
                        freq[prime]--;
                    }
                }
                i++;
            }
            // Update the maximum window length
            maxLength = Math.max(maxLength, j - i + 1);
        }

        return maxLength;
    }
}
