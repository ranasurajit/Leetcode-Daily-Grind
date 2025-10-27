class Solution {
    /**
     * Approach : Using Matrix + Prefix Sum Approach
     *
     * TC: O(M x N)
     * SC: O(1)
     */
    public int numberOfBeams(String[] bank) {
        int n = bank[0].length();
        int prevDeviceCount = 0;
        int beams = 0;
        for (String device : bank) { // TC: O(M)
            int currentDeviceCount = 0;
            for (int i = 0; i < n; i++) { // TC: O(N)
                currentDeviceCount += device.charAt(i) == '1' ? 1 : 0;
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
