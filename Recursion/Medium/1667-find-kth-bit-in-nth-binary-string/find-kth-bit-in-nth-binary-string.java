class Solution {
    /**
     * Approach : Using Recursion Approach
     *
     * TC: O(N²)
     * SC: O(N)
     */
    public char findKthBit(int n, int k) {
        String result = solveRecursion(n); // TC: O(N²), SC: O(N)
        System.out.println(result);
        return result.charAt(k - 1);
    }

    /**
     * Using Recursion Approach
     *
     * TC: O(N²)
     * SC: O(N) + O(N) ~ O(N)
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
