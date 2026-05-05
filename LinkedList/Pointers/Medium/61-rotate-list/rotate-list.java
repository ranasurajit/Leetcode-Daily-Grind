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
     * Approach III : Using Circular LinkedList Pointer Approach
     *
     * TC : O(n) + O(n - k) ~ O(n)
     * SC : O(1)
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        /**
         * we need to count the length of LinkedList
         */
        int length = 0;
        ListNode current = head;
        ListNode tail = null;
        while (current != null) { // TC : O(n)
            tail = current;
            current = current.next;
            length++;
        }
        /**
         * we need to remove un-necessary rotations 
         * as if k % length = 0, that means LinkedList
         * after rotations is identical to head
         */
        k = k % length;
        if (k == 0) {
            return head;
        }
        /**
         * Converting LinkedList to Circular LinkedList
         */
        tail.next = head;
        /**
         * Now we need to traverse to find the (n - k)th Node
         */
        current = head;
        int count = 1;
        while (count < length - k) { // TC : O(n - k)
            current = current.next;
            count++;
        }
        ListNode newHead = current.next;
        // Breaking the Circular LinkedList at current (as Tail)
        current.next = null;
        return newHead;
    }

    /**
     * Approach II : Using Single Pointers Approach
     *
     * TC : O(n) + O(n) ~ O(n)
     * SC : O(1)
     */
    public ListNode rotateRightSinglePointer(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        /**
         * we need to count the length of LinkedList
         */
        int length = 0;
        ListNode current = head;
        while (current != null) { // TC : O(n)
            current = current.next;
            length++;
        }
        /**
         * we need to remove un-necessary rotations 
         * as if k % length = 0, that means LinkedList
         * after rotations is identical to head
         */
        k = k % length;
        if (k == 0) {
            return head;
        }
        current = head;
        int count = 1;
        while (count < length - k) { // TC : O(n - k)
            current = current.next;
            count++;
        }
        ListNode newHead = current.next;
        current.next = null; // removing tail of current
        ListNode temp = newHead;
        while (count < length - 1) { // TC : O(k)
            temp = temp.next;
            count++;
        }
        temp.next = head;
        return newHead;
    }

    /**
     * Approach I : Using Two Pointers Approach
     *
     * TC : O(n) + O(n) + O(k % n) + O(n) + O(k % n) ~ O(n)
     * SC : O(1)
     */
    public ListNode rotateRightTwoPointers(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        /**
         * we need to count the length of LinkedList
         */
        int length = 0;
        ListNode current = head;
        while (current != null) { // TC : O(n)
            current = current.next;
            length++;
        }
        /**
         * we need to remove un-necessary rotations 
         * as if k % length = 0, that means LinkedList
         * after rotations is identical to head
         */
        k = k % length;
        if (k == 0) {
            return head;
        }
        /**
         * first we will reverse the entire LinkedList
         */
        ListNode revHead = reverseLL(head); // TC : O(n)
        /**
         * now we need to traverse in revHead till count becomes k
         */
        int count = 1;
        current = revHead;
        while (count < k && current != null) { // TC : O(k)
            current = current.next;
            count++;
        }
        ListNode secondLL = current.next; // partition LinkedList
        current.next = null; // removing tail of current
        /**
         * now we will reverse both parts of the LinkedLists
         */
        ListNode revFirst = reverseLL(revHead);   // TC : O(k)
        ListNode revSecond = reverseLL(secondLL); // TC : O(n - k)
        /**
         * we need to join First LinkedList's tail with 
         * second LinkedList's head and return First 
         * LinkedList's head
         */
        count = 1;
        current = revFirst;
        while (count < k && current != null) { // TC : O(k)
            current = current.next;
            count++;
        }
        current.next = revSecond; // joined here
        return revFirst;
    }

    /**
     * Using Two Pointers Approach
     *
     * TC : O(n)
     * SC : O(1)
     */
    private ListNode reverseLL(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = null;
        ListNode current = head;
        while (current != null) { // TC : O(n)
            ListNode temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }
        return prev;
    }
}
