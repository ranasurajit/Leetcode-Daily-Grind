/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    /**
     * Approach II : Using Two Pointers + Linked List (Without Extra Memory) Approach
     *
     * TC : O(n)
     * SC : O(1)
     */
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        if (head == null) {
            return new int[] { -1, -1 };
        }
        ListNode prev = head;
        ListNode current = head.next;
        int lastIndex = -1;
        int minDist = Integer.MAX_VALUE;
        int firstIndex = -1;
        int maxIndex = -1;
        int countCriticalNodes = 0;
        int index = 0;
        while (current != null && current.next != null) { // TC : O(n)
            ListNode next = current.next;
            boolean isCritical = 
                (current.val < prev.val && current.val < next.val) || 
                (current.val > prev.val && current.val > next.val);
            if (isCritical) {
                // found a critical point
                countCriticalNodes++;
                if (lastIndex != -1) {
                    minDist = Math.min(minDist, index - lastIndex);
                } else {
                    firstIndex = index;
                }
                lastIndex = index;
                maxIndex = Math.max(maxIndex, index);
            }
            index++;
            prev = current;
            current = current.next;
        }
        if (countCriticalNodes < 2) {
            return new int[] { -1, -1 };
        }
        return new int[] { minDist, maxIndex - firstIndex };
    }

    /**
     * Approach I : Using Two Pointers + Linked List (With Extra Memory) Approach
     *
     * TC : O(n) + O(k) ~ O(n)
     * SC : O(k) ~ O(n)
     */
    public int[] nodesBetweenCriticalPointsWithExtraSpace(ListNode head) {
        if (head == null) {
            return new int[] { -1, -1 };
        }
        ListNode prev = head;
        ListNode current = head.next;
        int index = 1;
        List<Integer> criticalIndices = new ArrayList<>();
        while (current != null && current.next != null) { // TC : O(n)
            ListNode next = current.next;
            if ((current.val < prev.val && current.val < next.val) || 
                (current.val > prev.val && current.val > next.val)) {
                // found a critical point
                criticalIndices.add(index);
            }
            index++;
            prev = current;
            current = current.next;
        }
        int size = criticalIndices.size();
        if (size < 2) {
            return new int[] { -1, -1 };
        }
        int maxDist = criticalIndices.get(size - 1) - criticalIndices.get(0);
        int minDist = maxDist;
        for (int i = 1; i < size; i++) { // TC : O(n)
            minDist = Math.min(minDist, 
                criticalIndices.get(i) - criticalIndices.get(i - 1)
            );
        }
        return new int[] { minDist, maxDist };
    }
}
