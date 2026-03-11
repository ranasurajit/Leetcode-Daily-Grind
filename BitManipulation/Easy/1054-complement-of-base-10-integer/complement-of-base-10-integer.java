class Solution {
    /**
     * Approach I : Using Math + Bit-Manipulation Approach
     *
     * TC: O(log(N) Base 2)
     * SC: O(1)
     */
    public int bitwiseComplement(int n) {
        int result = 0;
        if (n == 0) {
            return 1; // flipped
        }
        int pow = 0;
        while (n > 0) { // TC: O(log(N) Base 2)
            int rem = n % 2;
            if (rem == 0) {
                result += (1 << pow);
            }
            n = n / 2;
            pow++;
        }
        return result;
    }
}
