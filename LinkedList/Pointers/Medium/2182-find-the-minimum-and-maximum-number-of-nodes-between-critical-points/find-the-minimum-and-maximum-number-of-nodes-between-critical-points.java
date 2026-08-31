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
     * Approach : Using Two Pointers on Linked List Approach
     *
     * TC : O(n) + O(n) ~ O(n)
     * SC : O(1)
     */
    public int[] nodesBetweenCriticalPoints(ListNode head) {
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
