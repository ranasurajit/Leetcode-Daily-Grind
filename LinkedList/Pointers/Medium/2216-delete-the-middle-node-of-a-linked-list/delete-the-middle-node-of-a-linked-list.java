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
     * Approach : Using Tortoise Hare (Fast and Slow Pointers) Algorithm Approach
     *
     * TC : O(n / 2) ~ O(n)
     * SC : O(1)
     */
    public ListNode deleteMiddle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) { // TC : O(n / 2)
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        /**
         * here 'slow' pointer is the mid of LinkedList and
         * 'prev' pointer points to next of mid of LinkedList
         */
        prev.next = slow.next;
        return head;
    }
}
