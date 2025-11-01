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
     * Approach : Using LinkedList + Dummy Pointer Approach
     *
     * TC: O(N) + O(M) ~ O(N + M)
     * SC: O(N)
     *
     * where N = size(nums), M = size(head)
     */
    public ListNode modifiedList(int[] nums, ListNode head) {
        Set<Integer> hs = new HashSet<Integer>(); // SC: O(N)
        for (int num : nums) { // TC: O(N)
            hs.add(num);
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode current = dummy;
        while (current.next != null) { // TC: O(M)
            if (hs.contains(current.next.val)) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return dummy.next;
    }
}
