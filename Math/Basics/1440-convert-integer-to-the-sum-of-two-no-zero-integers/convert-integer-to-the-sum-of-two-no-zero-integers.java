class Solution {
    /**
     * Approach : Using Two Pointers + Number Theory + Simulation Approach
     *
     * TC: O(N x log(N) Base 10)
     * SC: O(1)
     */
    public int[] getNoZeroIntegers(int n) {
        int start = 1;
        int end = n;
        while (start <= end) { // TC: O(N)
            if (start + end == n) {
                if (hasZeroDigit(start) || hasZeroDigit(end)) { // TC: O(log(N) Base 10)
                    start++;
                    end--;
                } else {
                    return new int[] { start, end };
                }
            } else if (start + end < n) {
                start++;
            } else {
                end--;
            }
        }
        return new int[] { -1, -1 };
    }

    /**
     * Using Number Theory + Simulation Approach
     *
     * TC: O(log(N) Base 10)
     * SC: O(1)
     */
    private boolean hasZeroDigit(int num) {
        while (num > 0) {
            int rem = num % 10;
            if (rem == 0) {
                return true;
            }
            num = num / 10;
        }
        return false;
    }
}
