class Solution {
    /**
     * Approach II : Using Array Simulation Approach
     *
     * TC : O(n)
     * SC : O(1) 
     */
    public int maxDistance(int[] colors) {
        int n = colors.length;
        int dist1 = 0;
        int dist2 = 0;
        for (int i = 1; i < n; i++) { // TC : O(n)
            if (colors[i] != colors[0]) {
                dist1 = Math.max(dist1, i);
            }
            if (colors[i] != colors[n - 1]) {
                dist2 = Math.max(dist2, n - 1 - i);
            }
        }
        return Math.max(dist1, dist2);
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
