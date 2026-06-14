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
     * Approach II : Using Two Pointers (Without Partitioning) Approach
     *
     * TC : O(3 x (n / 2)) ~ O(n)
     * SC : O(1) 
     */
    public int pairSum(ListNode head) {
        /**
         * we are going to find the mid-point of
         * the LinkedList to compute pair node sum
         */
        ListNode prevMidNode = getPrevMidLL(head); // TC : O(n / 2), SC : O(1)
        /**
         * we will reverse the LinkedList at the mid
         * of the LinkedList without partitioning it
         */
        ListNode midNode =
            reverseLL(prevMidNode.next); // TC : O(n / 2), SC : O(1)
        /**
         * now we can traverse from both LinkedLists to find
         * maximum twin sum of nodes from both pointers
         */
        ListNode current1 = head;
        ListNode current2 = midNode;
        int currentSum = 0;
        int maxSum = 0; // as 1 <= Node.val <= 10⁵
        while (current2 != null) { // TC : O(n / 2)
            currentSum = current1.val + current2.val;
            maxSum = Math.max(maxSum, currentSum);
            current1 = current1.next;
            current2 = current2.next;
        }
        return maxSum;
    }

    /**
     * Approach I : Using Two Pointers (With Partitioning) Approach
     *
     * TC : O(3 x (n / 2)) ~ O(n)
     * SC : O(1) 
     */
    public int pairSumPartitionApproach(ListNode head) {
        if (head == null) {
            return 0;
        }
        if (head.next == null) {
            return head.val;
        }
        /**
         * above edge cases is not required as per constraints
         * which says "The number of nodes in the list is an 
         * even integer in the range [2, 105]"
         */
         /**
         * we are going to find the mid-point of
         * the LinkedList to compute pair node sum
         */
        ListNode prevMidNode = getPrevMidLL(head); // TC : O(n / 2), SC : O(1)
        ListNode midNode = prevMidNode.next;
        /**
         * removing node after prevNode to divide the LinkedList
         * into two equal halves as length of LinkedList is even
         */
        prevMidNode.next = null;
        /**
         * we need to reverse the LinkedList from its midNode
         */
        ListNode midHead = reverseLL(midNode); // TC : O(n / 2), SC : O(1)
        /**
         * now we can traverse from both LinkedLists to find
         * maximum twin sum of nodes from both pointers
         */
        ListNode current1 = head;
        ListNode current2 = midHead;
        int currentSum = 0;
        int maxSum = 0; // as 1 <= Node.val <= 10⁵
        while (current1 != null) { // TC : O(n / 2)
            currentSum = current1.val + current2.val;
            maxSum = Math.max(maxSum, currentSum);
            current1 = current1.next;
            current2 = current2.next;
        }
        return maxSum;
    }

    /**
     * Using Tortoise and Hare Algorithm (Fast and Slow Pointers) Approach
     *
     * TC : O(n / 2)
     * SC : O(1) 
     */
    private ListNode getPrevMidLL(ListNode head) {
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) { // TC : O(n / 2)
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        return prev;
    }

    /**
     * Using Iterative Two Pointers Approach
     *
     * TC : O(n / 2)
     * SC : O(1) 
     */
    private ListNode reverseLL(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = null;
        ListNode current = head;
        while (current != null) { // TC : O(n / 2)
            ListNode temp = current.next;
            current.next = prev;
            // shift the pointers
            prev = current;
            current = temp;
        }
        return prev;
    }
}
