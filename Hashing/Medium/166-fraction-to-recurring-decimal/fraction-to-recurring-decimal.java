class Solution {
    /**
     * Approach : Using Hashing + String Approach
     *
     * TC: O(D + L)
     * SC: O(1)
     *
     * where D = denominator and L = len(result)
     */
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        if ((long) numerator * (long) denominator < 0L) {
            sb.append('-');
        }
        long absNumerator = Math.abs((long) numerator);
        long absDenominator = Math.abs((long) denominator);
        long intVal = absNumerator / absDenominator;
        sb.append(intVal);
        long remainder = absNumerator % absDenominator;
        if (remainder == 0L) {
            return sb.toString();
        }
        sb.append('.');
        Map<Long, Integer> indexMap = new HashMap<Long, Integer>();
        while (remainder > 0) { // TC: O(D)
            if (indexMap.containsKey(remainder)) {
                sb.insert(indexMap.get(remainder), "("); // TC: O(L)
                sb.append(')');
                break;
            }
            indexMap.put(remainder, sb.length());
            remainder = remainder * 10;
            long decValue = remainder / absDenominator;
            sb.append(decValue);
            remainder = remainder % absDenominator;
        }
        return sb.toString();
    }
}
