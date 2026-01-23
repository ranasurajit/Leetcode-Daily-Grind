class Solution {
    /**
     * Approach : Using Ordered Set (TreeSet) + Array Pre-Processing Approach
     *
     * TC: O(N) + O(N x log(N)) + O(N x log(N)) ~ O(N x log(N))
     * SC: O(N) + O(N) + O(N) + O(N) ~ O(N)
     */
    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        // we will store Pair { sum, index } in TreeSet / Ordered Set
        TreeSet<Pair> treeSet = new TreeSet<Pair>((a, b) -> {
            if (a.sum == b.sum) {
                // here sums are equal so return lowest index
                return Integer.compare(a.index, b.index);
            }
            // returns lowest sum
            return Long.compare(a.sum, b.sum);
        }); // SC: O(N)
        /**
         * we need prevIndex and nextIndex to find the correct index 
         * of neighbours (left and right) after the deletion post summation
         *
         * also we need to find the bad pairs to carry on the operations
         * till badPairs becomes zero
         */
        int badPairs = 0;
        int[] prevIndex = new int[n]; // SC: O(N)
        int[] nextIndex = new int[n]; // SC: O(N)
        long[] temp = new long[n];    // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            temp[i] = (long) nums[i];
            prevIndex[i] = i - 1;
            nextIndex[i] = i + 1;
        }
        for (int i = 0; i < n - 1; i++) { // TC: O(N)
            if (temp[i + 1] < temp[i]) {
                // if decreasing pair found
                badPairs++;
            }
            treeSet.add(new Pair (temp[i] + temp[i + 1], i)); // TC: O(log(N))
        }
        int operations = 0;
        while (badPairs > 0) { // TC: O(N) - Max BadPairs = N - 1
            Pair pair = treeSet.pollFirst(); // TC: O(log(N))
            int first = pair.index;
            int second = nextIndex[first];
            // clear rendundant pairs from TreeSet
            int firstLeft = prevIndex[first];
            int secondRight = nextIndex[second];
            /**
             * update count of badPairs based upon current pair and previous 
             * and next and then remove redundant pairs clear leftSums and 
             * rightSums from TreeSet
             */
            if (temp[first] > temp[second]) {
                // current Pair is bad so anyways we remove it
                badPairs--;
            }
            if (firstLeft >= 0) {
                if (temp[firstLeft] > temp[first] && 
                    temp[firstLeft] <= temp[first] + temp[second]) {
                    // previous pair was bad too but modified pair is good
                    badPairs--;
                } else if (temp[firstLeft] <= temp[first] &&
                    temp[firstLeft] > temp[first] + temp[second]) {
                    // previous pair was good but modified pair is bad
                    badPairs++;
                }
            }
            if (secondRight < n) {
                if (temp[secondRight] >= temp[second] && 
                    temp[secondRight] < temp[first] + temp[second]) {
                    // next pair was good but modified pair is bad
                    badPairs++;
                } else if (temp[secondRight] < temp[second] &&
                    temp[secondRight] >= temp[first] + temp[second]) {
                    // next pair was bad too but modified pair is good
                    badPairs--;
                }
            }
            // remove redundant Pairs and add updated Sum Pair to TreeSet
            if (firstLeft >= 0) {
                // remove redundant Pair
                treeSet.remove(new Pair(temp[firstLeft] + temp[first], 
                    firstLeft)); // TC: O(log(N))
                // add updated Sum Pair
                treeSet.add(new Pair(temp[firstLeft] + temp[first] + temp[second], 
                    firstLeft)); // TC: O(log(N))
            }
            if (secondRight < n) {
                // remove redundant Pair
                treeSet.remove(new Pair(temp[second] + temp[secondRight], 
                    second)); // TC: O(log(N))
                // add updated Sum Pair
                treeSet.add(new Pair(temp[secondRight] + temp[first] + temp[second],
                    first)); // TC: O(log(N))
                prevIndex[secondRight] = first; // updating the prevIndex
            }
            nextIndex[first] = secondRight; // updating the nextIndex
            temp[first] += temp[second];
            operations++;
        }
        return operations;
    }

    static class Pair {
        long sum;
        int index;

        public Pair (long sum, int index) {
            this.sum = sum;
            this.index = index;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Pair)) {
                return false;
            }
            Pair p = (Pair) o;
            return p.sum == sum && p.index == index;
        }

        @Override
        public int hashCode() {
            return Objects.hash(sum, index);
        }
    }
}
