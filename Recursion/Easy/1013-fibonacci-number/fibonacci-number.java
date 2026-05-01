class Solution {
    /**
     * Approach : Using Recursion Approach
     *
     * TC : O(2ⁿ)
     * SC : O(n)
     *
     * O(n) - recursion stack 
     */
    public int fib(int n) {
        // Base Case
        if (n <= 1) {
            return n;
        }
        // Recursion Calls
        return fib(n - 1) + fib(n - 2);
    }
}
