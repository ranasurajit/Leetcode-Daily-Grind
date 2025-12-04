class Solution {
    /**
     * Approach I : Using Stack + String Simulation Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    public int countCollisions(String directions) {
        int n = directions.length();
        Stack<Character> st = new Stack<Character>(); // SC: O(N)
        int collisions = 0;
        for (char ch : directions.toCharArray()) { // TC: O(N)
            if (ch == 'R') {
                st.push('R');
            } else if (ch == 'S') {
                while (!st.isEmpty() && st.peek() == 'R') {
                    collisions += 1;
                    st.pop();
                }
                st.push('S');
            } else {
                if (!st.isEmpty() && st.peek() == 'R') {
                    collisions += 2;
                    st.pop();
                    ch = 'S';
                    while (!st.isEmpty() && st.peek() == 'R') {
                        collisions += 1;
                        st.pop();
                    }
                    st.push('S');
                } else if (!st.isEmpty() && st.peek() == 'S') {
                    collisions += 1;
                    st.push('S');
                } else {
                    st.push('L');
                }
            }
        }
        return collisions;
    }

    /**
     * Approach : Using Stack + String Simulation Approach
     *
     * TC: O(N)
     * SC: O(1)
     */
    // public int countCollisions(String directions) {
    //     int n = directions.length();
    //     int i = 0;
    //     int j = n - 1;
    //     while (i < n && directions.charAt(i) == 'L') {
    //         i++;
    //     }
    //     while (j >= 0 && directions.charAt(j) == 'R') {
    //         j--;
    //     }
    //     int collisions = 0;
    //     for (int k = i; k <= j; k++) { // TC: O(N)
    //         if (directions.charAt(k) == 'L' || directions.charAt(k) == 'R') {
    //             collisions++;
    //         }
    //     }
    //     return collisions;
    // }
}