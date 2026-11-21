class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        // Build the original tree
        List<Integer>[] tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            tree[parent[i]].add(i);
        }

        // Find the modified parent for every node
        int[] newParent = parent.clone();

        // Most recent ancestor for each character
        int[] last = new int[26];
        Arrays.fill(last, -1);

        // Store the previous ancestor value for backtracking
        int[] previous = new int[n];

        // Iterative DFS using enter/exit events
        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{0, 0});

        while (!stack.isEmpty()) {
            int[] event = stack.pop();
            int node = event[0];
            int state = event[1];
            int c = s.charAt(node) - 'a';

            if (state == 0) {
                // Enter node

                previous[node] = last[c];

                // Find closest matching ancestor
                if (last[c] != -1) {
                    newParent[node] = last[c];
                }

                // Update most recent ancestor
                last[c] = node;

                // Exit event for backtracking
                stack.push(new int[]{node, 1});

                // Process children
                for (int child : tree[node]) {
                    stack.push(new int[]{child, 0});
                }

            } else {
                // Exit node: restore ancestor state
                last[c] = previous[node];
            }
        }

        // Build the modified tree
        List<Integer>[] modifiedTree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            modifiedTree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            modifiedTree[newParent[i]].add(i);
        }

        // Generate traversal order of modified tree
        int[] order = new int[n];
        int index = 0;

        Deque<Integer> nodes = new ArrayDeque<>();
        nodes.push(0);

        while (!nodes.isEmpty()) {
            int node = nodes.pop();
            order[index++] = node;

            for (int child : modifiedTree[node]) {
                nodes.push(child);
            }
        }

        // Compute subtree sizes in reverse traversal order
        int[] answer = new int[n];
        Arrays.fill(answer, 1);

        for (int i = n - 1; i >= 0; i--) {
            int node = order[i];

            if (newParent[node] != -1) {
                answer[newParent[node]] += answer[node];
            }
        }

        return answer;
    }
}
