class Solution {
    /**
     * Approach II : Using Array Simulation Approach
     *
     * TC : O(n)
     * SC : O(1) 
     */
    public int maxDistance(int[] colors) {
        int n = colors.length;
        int maxDist = 0;
        for (int i = 1; i < n; i++) { // TC : O(n)
            if (colors[i] != colors[0]) {
                maxDist = Math.max(maxDist, i);
            }
            if (colors[i] != colors[n - 1]) {
                maxDist = Math.max(maxDist, n - 1 - i);
            }
        }
        return maxDist;
    }

    /**
     * Approach I : Using Two Pointers Approach
     *
     * TC : O(n) + O(n) ~ O(n)
     * SC : O(1) 
     */
    public int maxDistanceTwoPointer(int[] colors) {
        int n = colors.length;
        int i = 0; // pointer at the start of array 'colors'
        int j = n - 1; // pointer at the end of array 'colors'
        int dist1 = 0;
        while (i < j) { // TC : O(n)
            if (colors[i] != colors[j]) {
                dist1 = j - i;
                break;
            }
            j--;
        }
        i = 0; // reset pointer at the start of array 'colors'
        j = n - 1; // reset pointer at the end of array 'colors'
        int dist2 = 0;
        while (i < j) { // TC : O(n)
            if (colors[i] != colors[j]) {
                dist2 = j - i;
                break;
            }
            i++;
        }
        return Math.max(dist1, dist2);
    }
}
