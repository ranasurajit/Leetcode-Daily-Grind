class Solution {
    /**
     * Approach II : Using Enumeration + Math Approach
     *
     * TC: O(N x N)
     * SC: O(1)
     */
    public int countTriples(int n) {
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
