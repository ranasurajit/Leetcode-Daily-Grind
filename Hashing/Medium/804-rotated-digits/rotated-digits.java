class Solution {
    /**
     * Approach I : Using Math + Hashing Approach
     *
     * TC : O(n x log₁₀(n))
     * SC : O(1)
     */
    public int rotatedDigits(int n) {
        int[][] pairs = {
            { 0, 0 }, { 1, 1 }, { 2, 5 }, { 6, 9 }, { 8, 8 }
        };
        Map<Integer, Integer> rotatedMap = new HashMap<>(); // SC : O(10) ~ O(1)
        /**
         * out of digits 0-9, the numbers 0, 1, 2, 5, 6, 8, 9 
         * forms a valid digit so we can store them in HashMap
         */ 
        for (int[] pair : pairs) { // TC : O(5) ~ O(1)
            rotatedMap.putIfAbsent(pair[0], pair[1]);
            rotatedMap.putIfAbsent(pair[1], pair[0]);
        }
        int count = 0;
        for (int i = 1; i <= n; i++) { // TC : O(n)
            if (isGoodInteger(rotatedMap, i)) { // TC : O(log₁₀(n))
                count++;
            }
        }
        return count;
    }

    /**
     * Using Math + Hashing Approach
     *
     * TC : O(log₁₀(n))
     * SC : O(1)
     */
    private boolean isGoodInteger(Map<Integer, Integer> rotatedMap, int num) {
        int countDigits = 0;
        int sameDigits = 0;
        while (num > 0) { // TC : O(log₁₀(n))
            int rem = num % 10;
            if (!rotatedMap.containsKey(rem)) {
                return false;
            }
            if (rem == rotatedMap.get(rem)) {
                sameDigits++;
            }
            num /= 10;
            countDigits++;
        }
        // at least 1 digit should be different after rotation
        return sameDigits < countDigits;
    }
}
