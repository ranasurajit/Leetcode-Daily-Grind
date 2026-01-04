class Solution {
    /**
     * Approach II : Using Better (Simulation) Approach
     *
     * TC: O(N x Sqrt(M)), where M = max(nums)
     * SC: O(1)
     *
     * Accepted (18 / 18 testcases passed)
     */
    public int sumFourDivisors(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            sum += sumOfAllDivisorsBetter(nums[i]); // TC: O(Sqrt(M))
        }
        return sum;
    }

    /**
     * Using Simulation Approach
     *
     * TC: O(Sqrt(M))
     * SC: O(1)
     */
    private int sumOfAllDivisorsBetter(int num) {
        int sum = 1 + num;
        int count = 0;
        int max = (int) Math.sqrt(num);
        for (int i = 2; i <= max; i++) { // TC: O(Sqrt(M))
            if (num % i == 0) {
                sum += i;
                count++;
                if (i != (num / i)) {
                    sum += (num / i);
                    count++;
                }
                if (count > 2) {
                    // early break as num will be having more than 4 divisors
                    break;
                }
            }
        }
        // we did not add 1 and number itself
        return count == 2 ? sum : 0;
    }

    /**
     * Approach I : Using Brute-Force (Simulation) Approach
     *
     * TC: O(N x M), where M = max(nums)
     * SC: O(1)
     *
     * Accepted (18 / 18 testcases passed)
     */
    public int sumFourDivisorsBruteForce(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            sum += sumOfAllDivisors(nums[i]); // TC: O(M)
        }
        return sum;
    }

    /**
     * Using Simulation Approach
     *
     * TC: O(M)
     * SC: O(1)
     */
    private int sumOfAllDivisors(int num) {
        int sum = 1 + num;
        int count = 0;
        for (int i = 2; i <= num / 2; i++) { // TC: O(M / 2)
            if (num % i == 0) {
                sum += i;
                count++;
                if (count > 2) {
                    // early break as num will be having more than 4 divisors
                    break;
                }
            }
        }
        // we did not add 1 and number itself
        return count == 2 ? sum : 0;
    }
}
