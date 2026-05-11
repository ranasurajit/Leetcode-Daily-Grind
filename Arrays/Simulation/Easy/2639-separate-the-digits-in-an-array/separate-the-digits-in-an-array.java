class Solution {
    /**
     * Approach II : Using Math + Simulation Approach
     *
     * TC : O(n) + O(m)
     * SC : O(m)
     */
    public int[] separateDigits(int[] nums) {
        List<Integer> digits = new ArrayList<>(); // SC : O(m)
        for (int num : nums) { // TC : O(n)
            int digitLen = 1 + (int) Math.log10(num);
            int[] temp = new int[digitLen];
            for (int i = digitLen - 1; i >= 0; i--) { // TC : O(5)
                temp[i] = num % 10;
                num /= 10;
            }
            for (int d : temp) { // TC : O(5)
                digits.add(d);
            }
        }
        int m = digits.size();
        int[] result = new int[m];
        for (int i = 0; i < m; i++) { // TC : O(m)
            result[i] = digits.get(i);
        }
        return result;
    }

    /**
     * Approach I : Using Math + Stack + Simulation Approach
     *
     * TC : O(n) + O(m)
     * SC : O(m)
     */
    public int[] separateDigitsUsingStack(int[] nums) {
        List<Integer> digits = new ArrayList<>(); // SC : O(m)
        for (int num : nums) { // TC : O(n)
            if (num / 10 == 0) {
                // current num is a single digit element
                digits.add(num);
            } else {
                // send it for further processing - 1 <= nums[i] <= 105
                Stack<Integer> st = new Stack<>(); // SC : O(5)
                while (num > 0) { // TC : O(5)
                    st.push(num % 10);
                    num = num / 10;
                }
                while (!st.isEmpty()) { // TC : O(5)
                    digits.add(st.pop());
                }
            }
        }
        int m = digits.size();
        int[] result = new int[m];
        for (int i = 0; i < m; i++) { // TC : O(m)
            result[i] = digits.get(i);
        }
        return result;
    }
}
