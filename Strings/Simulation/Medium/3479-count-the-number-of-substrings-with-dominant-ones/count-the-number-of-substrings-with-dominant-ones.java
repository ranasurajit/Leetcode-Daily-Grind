class Solution {
    /**
     * Approach III : Using Optimal(Prefix Sums + Index Skipping) Approach
     *
     * TC: O(N x Sqrt(N)) + O(N) ~ O(N x Sqrt(N)) - Worst Case - O(N²)
     * SC: O(N)
     *
     * Accepted (881 / 881 testcases passed)
     */
    public int numberOfSubstrings(String s) {
        int n = s.length();
        int countDominants = 0;
        int[] prefixOnes = new int[n]; // SC: O(N)
        prefixOnes[0] = s.charAt(0) == '1' ? 1 : 0;
        for (int i = 1; i < n; i++) { // TC: O(N)
            prefixOnes[i] = prefixOnes[i - 1] + (s.charAt(i) == '1' ? 1 : 0);
        }
        // count1s [i...j] = prefixOnes[j] - prefixOnes[i - 1]
        for (int i = 0; i < n; i++) { // TC: O(N)
            for (int j = i; j < n; j++) { // TC: O(Sqrt(N))
                int count1s = prefixOnes[j] - (i > 0 ? prefixOnes[i - 1] : 0);
                int count0s = j - i + 1 - count1s;
                if (count1s < count0s * count0s) {
                    int invalidIndexCount = count0s * count0s - count1s;
                    j = j + invalidIndexCount;
                    j--;
                } else if (count1s == count0s * count0s) {
                    countDominants++;
                } else {
                    countDominants++;
                    /**
                     * count1s > count0s * count0s
                     * [i...j] is a valid sub-string and now we need to move j 
                     * until we find an invalid substring
                     */
                    int k = (int) Math.sqrt(count1s) - count0s;
                    int nextJ = j + k;
                    if (nextJ >= n) {
                        // out of bounds
                        countDominants += (n - j - 1);
                        break; // early break as we cannot move j further
                    } else {
                        countDominants += k;
                    }
                    j = nextJ;
                }
            }
        }
        return countDominants;
    }

    /**
     * Approach II : Using Better Brute-Force(Prefix Sums) Approach
     *
     * TC: O(N²) + O(N) ~ O(N²)
     * SC: O(N)
     *
     * Time Limit Exceeded (872 / 881 testcases passed)
     */
    public int numberOfSubstringsBetterBruteForce(String s) {
        int n = s.length();
        int countDominants = 0;
        int[] prefixZeros = new int[n]; // SC: O(N)
        prefixZeros[0] = s.charAt(0) == '0' ? 1 : 0;
        for (int i = 1; i < n; i++) { // TC: O(N)
            prefixZeros[i] = prefixZeros[i - 1] + (s.charAt(i) == '0' ? 1 : 0);
        }
        for (int l = 0; l < n; l++) { // TC: O(N)
            for (int r = l; r < n; r++) { // TC: O(N)
                int count0s = prefixZeros[r] - (l > 0 ? prefixZeros[l - 1] : 0);
                int count1s = r - l + 1 - count0s;
                if (count1s >= count0s * count0s) {
                    countDominants++;
                }
            }
        }
        return countDominants;
    }

    /**
     * Approach I : Using Brute-Force(String Simulation) Approach
     *
     * TC: O(N²)
     * SC: O(1)
     *
     * Time Limit Exceeded (871 / 881 testcases passed)
     */
    public int numberOfSubstringsBruteForce(String s) {
        int n = s.length();
        int countDominants = 0;
        for (int i = 0; i < n; i++) { // TC: O(N)
            int count0s = 0;
            int count1s = 0;
            for (int j = i; j < n; j++) { // TC: O(N)
                if (s.charAt(j) == '0') {
                    count0s++;
                } else {
                    count1s++;
                }
                if (count1s >= count0s * count0s) {
                    countDominants++;
                }
            }
        }
        return countDominants;
    }
}
