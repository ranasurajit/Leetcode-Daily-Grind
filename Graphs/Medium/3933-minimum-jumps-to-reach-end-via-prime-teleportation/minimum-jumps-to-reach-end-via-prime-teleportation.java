class Solution {
    /**
     * Approach : Using BFS + Sieve of Eratosthenes Approach
     *
     * TC : O(n) + O(L x log(log(L))) + O(n) + O(n x log(n)) ~ O(n x log(n))
     * SC : O(L) + O(n) + O(n) + O(n) ~ O(n + L)
     *
     * where L = max(nums)
     */
    public int minJumps(int[] nums) {
        int n = nums.length;
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) { // TC : O(n)
            maxValue = Math.max(maxValue, nums[i]);
        }
        boolean[] primes =
            sievePrime(maxValue); // TC : O(L * log(log(L))), SC : O(L)
        /**
         * we need to treat each index as node with a value
         * having edge weight of 1
         */
        Map<Integer, ArrayList<Integer>> map = new HashMap<>(); // SC : O(n)
        for (int i = 0; i < n; i++) { // TC : O(n)
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        /**
         * Now we can start a BFS (as edge weights = 1 here) to 
         * find the minimum number of steps
         */
        boolean[] visited = new boolean[n]; // SC : O(n)
        Queue<Integer> queue = new LinkedList<>(); // SC : O(n)
        queue.offer(0);
        visited[0] = true;
        int steps = 0;
        while (!queue.isEmpty()) { // TC : O(n)
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int node = queue.poll();
                if (node == n - 1) {
                    return steps;
                }
                /**
                 * from node 'i', we can go to (i - 1), (i + 1) or 
                 * to multiple if nums[i] is prime
                 */
                if (node > 0 && !visited[node - 1]) {
                    queue.offer(node - 1);
                    visited[node - 1] = true;
                }
                if (node < n - 1 && !visited[node + 1]) {
                    queue.offer(node + 1);
                    visited[node + 1] = true;
                }
                int value = nums[node];
                if (primes[value]) {
                    for (int mult = value; 
                        mult <= maxValue; mult += value) { // TC : O(log(n))
                        if (map.containsKey(mult)) {
                            for (Integer idx : map.get(mult)) {
                                // then we can move from node to idx
                                if (idx < n && !visited[idx]) {
                                    queue.offer(idx);
                                    visited[idx] = true;
                                }
                            }
                        }
                        // need to clear else again same node would be sent to Queue
                        map.remove(mult);
                    }
                }
            }
            steps++;
        }
        return steps;
    }

    /**
     * Using Sieve of Eratosthenes Approach
     *
     * TC : O(L x log(log(L)))
     * SC : O(L)
     */
    private boolean[] sievePrime(int max) {
        boolean[] primes = new boolean[max + 1]; // SC : O(max)
        Arrays.fill(primes, true);
        primes[0] = false;
        primes[1] = false;
        for (int i = 2; i * i <= max; i++) {
            if (primes[i]) {
                for (int j = i * i; j <= max; j += i) {
                    primes[j] = false;
                }
            }
        }
        return primes;
    }
}
