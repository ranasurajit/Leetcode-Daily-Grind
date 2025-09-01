class Solution {
    /**
     * Approach : Using Greedy + Heap (Max PriorityQueue) Approach
     *
     * TC: O(N x log(N)) + O(K x log(N)) + O(N) ~ O((N + K) x log(N))
     * SC: O(N)
     *
     * \U0001f4a1 Hint:
     * Always assign an extra student to the class that gives the maximum increase (gain) 
     * in pass ratio, not just the one with the lowest current ratio.
     *
     * \U0001f9e0 Intuition:
     *
     * - Each extra student increases the pass ratio differently across classes.
     * - The marginal gain is:
     *       Δ = (p + 1) / (t + 1) - (p / t)
     *
     * - Greedily choose the class with the highest Δ using a Max-Heap.
     * - Repeat until all extra students are placed, then compute the final average ratio.
     */
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        int n = classes.length;
        // we will be pushing the gain acheieved by adding 1 student to the class in the Max-Heap
        PriorityQueue<double[]> pq = 
            new PriorityQueue<double[]>((p, q) -> Double.compare(q[0], p[0])); // SC: O(N)
        for (int i = 0; i < n; i++) { // TC: O(N)
            pq.offer(new double[] { gain(classes[i]), (double) i }); // TC: O(log(N))
        }
        while (extraStudents > 0) { // TC: O(K)
            double[] gainElement = pq.poll();
            double gain = gainElement[0];
            int idx = (int) gainElement[1];
            classes[idx][0]++;
            classes[idx][1]++;
            double newGain = gain(classes[idx]); // TC: O(1)
            pq.offer(new double[] { newGain, (double) idx }); // TC: O(log(N))
            extraStudents--;
        }
        double sumRatios = 0d;
        for (int[] classItem : classes) { // TC: O(N)
            sumRatios += ((double) classItem[0]) / (classItem[1]);
        }
        return sumRatios / n;
    }

    /**
     * Using Simulation Approach
     *
     * TC: O(1)
     * SC: O(1)
     */
    private double gain(int[] classItem) {
        double oldRatio = ((double) classItem[0]) / classItem[1];
        double newRatio = ((double) classItem[0] + 1) / (classItem[1] + 1);
        return newRatio - oldRatio;
    }
}
