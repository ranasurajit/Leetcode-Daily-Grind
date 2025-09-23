class Solution {
    /**
     * Approach : Using Two Pointers Approach
     *
     * TC: O(Max(N, M)) + O(Max(N, M)) ~ O(Max(N, M))
     * SC: O(N + M)
     */
    public int compareVersion(String version1, String version2) {
        int n = version1.length();
        int m = version2.length();
        int p = 0; // start pointer at String 'version1'
        int q = 0; // start pointer at String 'version2'
        int versionNum1 = 0;
        int versionNum2 = 0;
        List<Integer> ver1List = new ArrayList<Integer>(); // SC: O(N)
        List<Integer> ver2List = new ArrayList<Integer>(); // SC: O(M)
        while (p < n || q < m) { // TC: O(Max(N, M))
            if (p < n) {
                char chVer1 = version1.charAt(p);
                if (chVer1 != '.') {
                    versionNum1 = versionNum1 * 10 + (chVer1 - '0');
                } else {
                    ver1List.add(versionNum1);
                    versionNum1 = 0;
                }
            }
            if (q < m) {
                char chVer2 = version2.charAt(q);
                if (chVer2 != '.') {
                    versionNum2 = versionNum2 * 10 + (chVer2 - '0');
                } else {
                    ver2List.add(versionNum2);
                    versionNum2 = 0;
                }
            }
            p++;
            q++;
        }
        // adding the last computed value to its respective list
        ver1List.add(versionNum1);
        ver2List.add(versionNum2);
        p = 0; // start pointer at List 'ver1List'
        q = 0; // start pointer at List 'ver2List'
        n = ver1List.size();
        m = ver2List.size();
        System.out.println(ver1List + " - " + ver2List);
        while (p < n || q < m) { // TC: O(Max(N, M))
            if (p >= n) {
                ver1List.add(0);
            }
            if (q >= m) {
                ver2List.add(0);
            }
            if (Long.compare(ver1List.get(p), ver2List.get(q)) == 0) {
                p++;
                q++;
            } else {
                return Long.compare(ver1List.get(p), ver2List.get(q));
            }
        }
        return 0;
    }
}
