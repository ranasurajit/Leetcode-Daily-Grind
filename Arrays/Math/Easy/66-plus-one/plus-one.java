class Solution {
    /**
     * Approach II : Using Inplace Substitution Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            digits[i]++;
            if (digits[i] == 10) {
                digits[i] = 0;
            } else {
                return digits;
            }
        }
        digits = new int[n + 1];
        digits[0] = 1;
        return digits;
    }

    /**
     * Approach I : Using Brute-Force Approach
     *
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(N)
     */
    public int[] plusOneBruteForce(int[] digits) {
        int n = digits.length;
        List<Integer> onePlusList = new ArrayList<Integer>(); // SC: O(N)
        int carry = 1; // adding 1 to digits
        for (int i = n - 1; i >= 0; i--) { // TC: O(N)
            int sum = carry + digits[i];
            onePlusList.add(sum % 10);
            carry = sum / 10; 
        }
        if (carry > 0) {
            onePlusList.add(carry);
        }
        int m = onePlusList.size();
        int[] result = new int[m];
        for (int i = m - 1; i >= 0; i--) { // TC: O(N)
            result[i] = onePlusList.get(m - i - 1);
        }
        return result;
    }
}
