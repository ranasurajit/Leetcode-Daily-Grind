class Solution {
    /**
     * Approach III : Using Two Pointers + Math Approach
     *
     * TC: O(N x N)
     * SC: O(1)
     */
    public int countTriples(int n) {
        int count = 0;
        for (int c = 1; c <= n; c++) { // TC: O(N)
            int target = c * c;
            int a = 1;
            int b = c - 1;
            while (a < b) { // TC: O(N)
                int sqrSum = a * a + b * b;
                if (sqrSum == target) {
                    count += 2; // (a, b) and (b, a) are accounted
                    a++;
                    b--;
                } else if (sqrSum < target) {
                    a++;
                } else {
                    b--;
                }
            }
        }
        return count;
    }

    /**
     * Approach II : Using Enumeration + Math Approach
     *
     * TC: O(N x N)
     * SC: O(1)
     */
    public int countTriplesBetter(int n) {
        int count = 0;
        for (int a = 1; a <= n; a++) {     // TC: O(N)
            for (int b = a + 1; b <= n; b++) { // TC: O(N)
                int squares = a * a + b * b;
                int c = (int) Math.sqrt(squares);
                if (c > n) {
                    // out of range
                    break;
                }
                if (c * c == squares) {
                    // as pairs can be swappable
                    count += 2;
                }
            }
        }
        return count;
    }

    /**
     * Approach I : Using Brute-Force (Enumeration + Hashing) Approach
     *
     * TC: O(N x N)
     * SC: O(N x N)
     */
    public int countTriplesBruteForce(int n) {
        int count = 0;
        Map<Integer, ArrayList<int[]>> map = new HashMap<Integer, ArrayList<int[]>>(); // SC: O(N x N)
        for (int i = 1; i <= n; i++) {     // TC: O(N)
            for (int j = 1; j <= n; j++) { // TC: O(N)
                map.computeIfAbsent((i * i + j * j), k -> new ArrayList<int[]>()).add(new int[] { i, j });
            }
        }
        for (int i = 1; i <= n; i++) { // TC: O(N)
            if (map.containsKey(i * i)) {
                count += map.get(i * i).size();
            }
        }
        return count;
    }
}
