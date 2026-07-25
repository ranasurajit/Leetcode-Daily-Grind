class Solution {
    /**
     * Approach : Using Math + Simulation Approach
     *
     * TC : O(log10(n))
     * SC : O(1)
     */
    public int maxProduct(int n) {
        int first = -1;
        int second = -1;
        while (n > 0) { // TC : O(log10(n))
            int digit = n % 10;
            if (first < digit) {
                second = first;
                first = digit;
            } else if (second < digit) {
                second = digit;
            }
            n = n / 10;
        }
        return first * second;
    }
}
