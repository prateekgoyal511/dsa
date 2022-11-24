package linkedlists;


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class ReorderListRecursion {
    public void reorderList(ListNode head) {
        reorderListUtil(head);
    }

    private ListNode reorderListUtil(ListNode head) {
        //Base conditions
        if(head == null) return null;
        if(head.next == null) return head;
        if(head.next.next == null)return head;

        //Size of linked list is greater than 2.
        ListNode secondLast = findSecondLastNode(head);
        ListNode last = secondLast.next;
        ListNode second = head.next;
        //Make next node of head = last.
        head.next = last;
        //Make secondlast as the last node now.
        secondLast.next = null;
        //In last.next, we should have reordered list from second to secondlast.
        last.next = reorderListUtil(second);
        return head;
    }

    private ListNode findSecondLastNode(ListNode head) {
        ListNode current = head;
        while(current.next.next != null) {
            current = current.next;
        }
        return current;
    }
}
