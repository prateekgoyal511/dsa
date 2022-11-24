package linkedlists;

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
class ReorderListWithoutRecursion {
    public void reorderList(ListNode head) {
        if(head == null) return;
        if(head.next == null) return;
        if(head.next.next == null)return;


        ListNode mid = findMidPointer(head);
        //Second list's head'
        ListNode list2Head = mid.next;
        //First list ends at mid.
        mid.next = null;
        //Reversed second list
        ListNode reverseList2Head = reverse(list2Head);
        mergeAlternately(head, reverseList2Head);
    }

    private ListNode findMidPointer(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode reverse(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode rest = reverse(head.next);
        //Head.next is now in the end. So, its next should point to last node, i.e. head
        head.next.next = head;
        head.next = null;
        return rest;
    }

    private void mergeAlternately(ListNode list1Head, ListNode list2Head) {
        //The node whose next node is going to come from list2
        ListNode node1 = list1Head;
        ListNode node2 = list2Head;
        ListNode nextToNode1;
        ListNode nextToNode2;
        while(node1 != null && node2 != null) {
            //Let's save next nodes of both lists since ultimately we have to move our pointers there'
            nextToNode1 = node1.next;
            nextToNode2 = node2.next;
            node1.next = node2;
            node2.next = nextToNode1;
            node1 = nextToNode1;
            node2 = nextToNode2;
        }
    }
}
