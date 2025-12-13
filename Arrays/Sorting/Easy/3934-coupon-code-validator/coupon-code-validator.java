class Solution {
    private static final String[] validBusinesses = { 
        "electronics", "grocery", "pharmacy", "restaurant"
    };

    /**
     * Approach : Using Hashing + Sorting + Array Simulation Approach
     *
     * TC: O(N x L) + O(K x log(K)) + O(K) ~ O(N x L)
     * SC: O(K)
     * where L = average length of code, K = length of valid coupons
     */
    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {
        int n = code.length;
        Map<String, Integer> businessMap = new HashMap<String, Integer>(); // SC: O(4)
        for (int i = 0; i < validBusinesses.length; i++) { // TC: O(4)
            businessMap.put(validBusinesses[i], i);
        }
        List<Pair> allowedCoupons = new ArrayList<Pair>(); // SC: O(K)
        for (int i = 0; i < n; i++) { // TC: O(N)
            if (!isActive[i]) {
                continue;
            }
            if (isValidCode(code[i]) &&  // TC: O(L)
                isValidBusinessLine(businessLine[i])) { // TC: O(1)
                allowedCoupons.add(new Pair(code[i], businessMap.get(businessLine[i])));
            }
        }
        Collections.sort(allowedCoupons, (Pair a, Pair b) -> {
            if (a.order != b.order) {
                // businessLines do not match
                return Integer.compare(a.order, b.order);
            }
            // businessLines match so sort by code in lexicographical (ascending) order
            return a.code.compareTo(b.code);
        }); // TC: O(K x log(K))
        List<String> result = new ArrayList<String>();
        for (Pair coupon : allowedCoupons) { // TC: O(K)
            result.add(coupon.code);
        }
        return result;
    }

    /**
     * Using Simulation Approach
     *
     * TC: O(L)
     * SC: O(1)
     */
    private boolean isValidCode(String code) {
        int len = code.length();
        if (len == 0) {
            return false;
        }
        for (int i = 0; i < len; i++) { // TC: O(L)
            char ch = code.charAt(i);
            if (!Character.isLetterOrDigit(ch)) {
                if (ch != '_') {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Using Enumeration Approach
     *
     * TC: O(1)
     * SC: O(1)
     */
    private boolean isValidBusinessLine(String businessLine) {
        return businessLine.equals("electronics") || 
            businessLine.equals("grocery") ||
            businessLine.equals("pharmacy") ||
            businessLine.equals("restaurant");
    }

    private class Pair {
        String code;
        int order;

        public Pair (String code, int order) {
            this.code = code;
            this.order = order;
        }
    }
}
