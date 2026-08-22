class Solution {
    /**
     * Approach : Using Math Approach
     *
     * TC : O(log₁₀(n))
     * SC : O(1)
     */
    public boolean checkDivisibility(int n) {
        int sum = 0;
        int product = 1;
        int temp = n;
        while (temp > 0) { // TC : O(log₁₀(n))
            int digit = temp % 10;
            sum += digit;
            product *= digit;
            temp /= 10;
        }
        sum += product;
        return n % sum == 0;
    }
}
