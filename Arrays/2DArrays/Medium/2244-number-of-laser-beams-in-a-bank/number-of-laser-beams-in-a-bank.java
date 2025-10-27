class Solution {
    /**
     * Approach : Using Matrix + Prefix Sum Approach
     *
     * TC: O(N) + O(M x N) ~ O(M x N)
     * SC: O(1)
     */
    public int numberOfBeams(String[] bank) {
        int n = bank.length;
        int prevDeviceCount = 0;
        // for (int i = 0; i < bank[0].length(); i++) { // TC: O(N)
        //     prevDeviceCount += bank[0].charAt(i) == '1' ? 1 : 0;
        // }
        int beams = 0;
        for (int i = 0; i < n; i++) { // TC: O(M)
            int currentDeviceCount = 0;
            for (int j = 0; j < bank[i].length(); j++) { // TC: O(N)
                currentDeviceCount += bank[i].charAt(j) == '1' ? 1 : 0;
            }
            if (currentDeviceCount == 0) {
                currentDeviceCount = prevDeviceCount;
            } else {
                beams += currentDeviceCount * prevDeviceCount;
            }
            prevDeviceCount = currentDeviceCount;
        }
        return beams;
    }
}
