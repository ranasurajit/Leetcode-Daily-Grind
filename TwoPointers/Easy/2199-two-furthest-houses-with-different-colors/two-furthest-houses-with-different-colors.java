class Solution {
    /**
     * Approach : Using Two Pointers Approach
     *
     * TC : O(n)
     * SC : O(1) 
     */
    public int maxDistance(int[] colors) {
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
