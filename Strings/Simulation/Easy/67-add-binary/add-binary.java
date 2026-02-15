class Solution {
    /**
     * Approach : Using String Simulation (Concise) Approach
     *
     * TC: O(N + M)
     * SC: O(N + M)
     */
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder(); // SC: O(N + M)
        int p = a.length() - 1;
        int q = b.length() - 1;
        int carry = 0;
        while (p >= 0 || q >= 0 || carry > 0) { // TC: O(N + M)
            int numA = 0;
            int numB = 0;
            if (p >= 0) {
                numA = a.charAt(p) - '0';
            }
            if (q >= 0) {
                numB = b.charAt(q) - '0';
            }
            int result = carry + numA + numB;
            carry = computeAdd(result, sb, carry);
            p--;
            q--;
        }
        return sb.reverse().toString();
    }

    /**
     * Approach I : Using String Simulation Approach
     *
     * TC: O(N + M)
     * SC: O(N + M)
     */
    public String addBinaryOptimal(String a, String b) {
        StringBuilder sb = new StringBuilder(); // SC: O(N + M)
        int p = a.length() - 1;
        int q = b.length() - 1;
        int carry = 0;
        while (p >= 0 && q >= 0) { // TC: O(N + M)
            int numA = a.charAt(p) - '0';
            int numB = b.charAt(q) - '0';
            int result = carry + numA + numB;
            carry = computeAdd(result, sb, carry);
            p--;
            q--;
        }
        while (p >= 0) {
            int numA = a.charAt(p) - '0';
            int result = carry + numA;
            carry = computeAdd(result, sb, carry);
            p--;
        }
        while (q >= 0) {
            int numB = b.charAt(q) - '0';
            int result = carry + numB;
            carry = computeAdd(result, sb, carry);
            q--;
        }
        if (carry > 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }

    /**
     * Using String Simulation Approach
     *
     * TC: O(1)
     * SC: O(1)
     */
    private int computeAdd(int add, StringBuilder sb, int carry) {
        if (add > 2) {
            sb.append('1');
            carry = 1;
        } else if (add > 1) {
            sb.append('0');
            carry = 1;
        } else if (add > 0) {
            sb.append('1');
            carry = 0;
        } else {
            sb.append('0');
            carry = 0;
        }
        return carry;
    }
}
