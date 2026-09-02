class Solution {
    public boolean uniformArray(int[] nums1) {
        int n = nums1.length;
        int[] nums2 = new int[n];
        int odds = 0;
        int evens = 0;
        for (int i = 0; i < n; i++) {
            if ((nums1[i] & 1) == 0) {
                evens++;
            } else {
                odds++;
            }
        }
        if (odds == 0 || evens == 0) {
            return true;
        }
        return true;
    }
}
