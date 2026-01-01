class Solution {
    /**
     * Approach : Using Brute-Force Approach
     *
     * TC: O(N) + O(N) ~ O(N)
     * SC: O(N)
     */
    public int[] plusOne(int[] digits) {
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
