class Solution {
    /**
     * Approach II: Using Optimal Recursion (Without String Formation) Approach
     *
     * TC: O(n)
     * SC: O(n)
     * - O(n) - recursion stack
     */
    public char findKthBit(int n, int k) {
        return solve(n, k);
    }

    /**
     * Using Optimal Recursion (Without String Formation) Approach
     *
     * TC: O(n)
     * SC: O(n)
     */
    private char solve(int n, int k) {
        // Base Case
        if (n == 1) {
            return '0';
        }
        // Recursion Calls
        int mid = (1 << (n - 1));
        // the formed String is always odd
        if (k == mid) {
            return '1';
        }
        if (k < mid) {
            return solve(n - 1, k);
        }
        if (k > mid) {
            // the target will be mirror index i.e. (2^n - 1) - k + 1 = 2^n - k
            int mirrorIdx = (1 << n) - k;
            char ch = solve(n - 1, mirrorIdx);
            // return inverse of char 'ch'
            return ch == '0' ? '1' : '0';
        }
        return '0';
    }

    /**
     * Approach I : Using Brute-Force (Recursion) Approach
     *
     * TC: O(2ⁿ)
     * SC: O(2ⁿ) + O(n)
     * - O(n) - recursion stack
     */
    public char findKthBitBruteForce(int n, int k) {
        String result = solveRecursion(n); // TC: O(N²), SC: O(N)
        System.out.println(result);
        return result.charAt(k - 1);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(2ⁿ)
     * SC: O(2ⁿ) + O(n)
     */
    private String solveRecursion(int n) {
        // Base Case
        if (n == 1) {
            return "0";
        }
        // Recursion Case
        String result = "";
        String previous = solveRecursion(n - 1);
        result = previous + "1" + invertAndReverse(previous); // TC: O(N), SC: O(N)
        return result;
    }

    /**
     * Using String Simulation + Two Pointers Approach
     *
     * TC: O(N) + O(N / 2) ~ O(N)
     * SC: O(N)
     */
    private String invertAndReverse(String s) {
        char[] chars = s.toCharArray(); // SC: O(N)
        int n = chars.length;
        // invert String
        for (int i = 0; i < n; i++) { // TC: O(N)
            chars[i] = chars[i] == '0' ? '1' : '0';
        }
        // reverse String
        int low = 0;
        int high = n - 1;
        while (low < high) { // TC: O(N / 2)
            // swap characters at index 'low' with 'high'
            char temp = chars[low];
            chars[low] = chars[high];
            chars[high] = temp;
            low++;
            high--;
        }
        return String.valueOf(chars);
    }
}
