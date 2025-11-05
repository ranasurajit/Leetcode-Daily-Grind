class Solution {
    long sum = 0; //maintains sum of top x elements from main set
    TreeSet<int[]> main; //contains top-x freq, elements
    TreeSet<int[]> sec;  //contains remaining freq, elements
    Map<Integer, Integer> freq; //element -> frequency

    public long[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        sum = 0;
        freq = new HashMap<>();

        // Comparator to sort by freq first, then by val (both ascending)
        Comparator<int[]> comp = (a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        };

        main = new TreeSet<>(comp);
        sec = new TreeSet<>(comp);

        List<Long> resultList = new ArrayList<>();

        int i = 0;
        int j = 0;
        while (j < n) {
            int num = nums[j];

            // If already present, remove old (freq, val)
            if (freq.getOrDefault(num, 0) > 0) {
                removeFromSet(new int[]{freq.get(num), num}, x);
            }

            // Increase frequency
            freq.put(num, freq.getOrDefault(num, 0) + 1);

            // Insert updated pair
            insertInSet(new int[]{freq.get(num), num}, x);

            // When window size becomes k
            if (j - i + 1 == k) {
                resultList.add(sum);

                // Remove outgoing element
                int outNum = nums[i];
                removeFromSet(new int[]{freq.get(outNum), outNum}, x);
                freq.put(outNum, freq.get(outNum) - 1);

                if (freq.get(outNum) == 0) {
                    freq.remove(outNum);
                } else {
                    insertInSet(new int[]{freq.get(outNum), outNum}, x);
                }

                i++;
            }

            j++;
        }

        // Convert List<Long> to long[]
        long[] result = new long[resultList.size()];
        for (int idx = 0; idx < resultList.size(); idx++) {
            result[idx] = resultList.get(idx);
        }
        return result;
    }

    void insertInSet(int[] p, int x) {
        if (main.size() < x || comparePairs(p, main.first()) > 0) {
            sum += 1L * p[0] * p[1];
            main.add(p);

            if (main.size() > x) {
                int[] smallest = main.first();
                sum -= 1L * smallest[0] * smallest[1];
                main.remove(smallest);
                sec.add(smallest);
            }
        } else {
            sec.add(p);
        }
    }

    void removeFromSet(int[] p, int x) {
        if (main.contains(p)) {
            sum -= 1L * p[0] * p[1];
            main.remove(p);

            if (!sec.isEmpty()) {
                int[] largest = sec.last();
                sec.remove(largest);
                main.add(largest);
                sum += 1L * largest[0] * largest[1];
            }
        } else {
            sec.remove(p);
        }
    }

    // Helper comparison to mimic pair comparison from C++
    int comparePairs(int[] a, int[] b) {
        if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
        return Integer.compare(a[1], b[1]);
    }

    /**
     * Approach II : Using Sliding Window (Fixed Size) + TreeMap + HashMap Approach
     *
     * TC: O(N x log(K)) + O(X x (N - K + 1)) ~ O(N x (log(K) + X))
     * SC: O(K) + O(K) ~ O(K)
     *
     * Time Limit Exceeded (778 / 784 testcases passed)
     */
    public long[] findXSumBetterApproach(int[] nums, int k, int x) {
        int n = nums.length;
        // Custom comparator: higher freq first; tie-breaker by larger num
        Comparator<Pair> comparator = ((a, b) -> {
            if (a.freq == b.freq) {
                return Integer.compare(b.num, a.num);
            }
            return Integer.compare(b.freq, a.freq);
        });
        Map<Integer, Integer> freqNum = new HashMap<Integer, Integer>(); // SC: O(K)
        TreeMap<Pair, Integer> freqOrder = new TreeMap<Pair, Integer>(comparator); // SC: O(K)
        int i = 0; // start pointer of sliding window
        int j = 0; // end pointer of sliding window
        int index = 0;
        long[] result = new long[n - k + 1];
        while (j < n) { // TC: O(N - K + 1)
            int oldFreq = freqNum.getOrDefault(nums[j], 0);
            if (oldFreq > 0) {
                freqOrder.remove(new Pair(nums[j], oldFreq)); // TC: O(log(K))
            }
            freqNum.put(nums[j], freqNum.getOrDefault(nums[j], 0) + 1);
            freqOrder.put(new Pair(nums[j], oldFreq + 1), 1); // TC: O(log(K))
            if (j - i + 1 == k) {
                // sliding window size is met
                result[index++] = getXSumFromKLongSubArrayFromTreeMap(freqOrder, x); // TC: O(X), SC: O(1)
                // remove computation from index 'i'
                freqOrder.remove(new Pair(nums[i], freqNum.get(nums[i]))); // TC: O(log(K))
                freqNum.put(nums[i], freqNum.get(nums[i]) - 1);
                int newFreq = freqNum.get(nums[i]);
                if (freqNum.get(nums[i]) == 0) {
                    // clean-up freq HashMap if nums[i] frequency = 0
                    freqNum.remove(nums[i]);
                } else {
                    freqOrder.put(new Pair(nums[i], newFreq), 1);
                }
                // slide to next window
                i++;
            }
            j++;
        }
        return result;
    }

    /**
     * Using Hashing + Max-Heap Approach
     *
     * TC: O(X)
     * SC: O(1)
     */
    private long getXSumFromKLongSubArrayFromTreeMap(TreeMap<Pair, Integer> freqOrder, int x) {
        long xSum = 0L;
        for (Pair key : freqOrder.keySet()) { // TC: O(X)
            xSum += (long) key.num * (long)key.freq;
            x--;
            if (x == 0) {
                break;
            }
        }
        return xSum;
    }

    static class Pair {
        int num;
        int freq;
        Pair(int n, int f) {
            this.num = n;
            this.freq = f;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;
            Pair p = (Pair) o;
            return num == p.num && freq == p.freq;
        }

        @Override
        public int hashCode() {
            return Objects.hash(num, freq);
        }

        @Override
        public String toString() {
            return "(" + num + "," + freq + ")";
        }
    }

    /**
     * Approach I : Using Sliding Window (Fixed Size) + Hashing + Max-Heap Approach
     *
     * TC: O(N x K x log(K))
     * SC: O(K) + O(K) ~ O(K)
     *
     * Time Limit Exceeded (776 / 784 testcases passed)
     */
    public long[] findXSumBruteForceApproach(int[] nums, int k, int x) {
        int n = nums.length;
        Map<Integer, Integer> freq = new HashMap<Integer, Integer>(); // SC: O(K)
        int i = 0; // start pointer of sliding window
        int j = 0; // end pointer of sliding window
        int index = 0;
        long[] result = new long[n - k + 1];
        while (j < n) { // TC: O(N - K + 1)
            freq.put(nums[j], freq.getOrDefault(nums[j], 0) + 1);
            if (j - i + 1 == k) {
                // sliding window size is met
                result[index++] = getXSumFromKLongSubArray(freq, x); // TC: O(K x log(K)), SC: O(K)
                // remove computation from index 'i'
                freq.put(nums[i], freq.get(nums[i]) - 1);
                if (freq.get(nums[i]) == 0) {
                    // clean-up freq HashMap if nums[i] frequency = 0
                    freq.remove(nums[i]);
                }
                // slide to next window
                i++;
            }
            j++;
        }
        return result;
    }

    /**
     * Using Hashing + Max-Heap Approach
     *
     * TC: O(K x log(K)) + O(Min(X, K)) ~ O(K x log(K))
     * SC: O(K)
     */
    private long getXSumFromKLongSubArray(Map<Integer, Integer> freq, int x) {
        // we will store { num, freq(num) } in Max-Heap
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((p, q) -> {
            if (p[1] == q[1]) {
                // if frequency of numbers are same then return larger number
                return q[0] - p[0];
            }
            // return number with larger frequency
            return q[1] - p[1];
        }); // SC: O(K)
        for (Integer key : freq.keySet()) { // TC: O(K)
            pq.offer(new int[] { key, freq.get(key) }); // TC: O(log(K))
        }
        long xSum = 0L;
        while (!pq.isEmpty() && x-- > 0) { // TC: O(Min(X, K))
            int[] current = pq.poll();
            xSum += (long) current[0] * (long) current[1];
        }
        return xSum;
    }
}
