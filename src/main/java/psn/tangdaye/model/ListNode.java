package psn.tangdaye.model;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }


    public ListNode next(int nextVar) {
        this.next = new ListNode(nextVar);
        return this.next;
    }

    public static ListNode fromArray(int[] array) {
        if (array.length == 0) return null;
        ListNode head = new ListNode(array[0]);
        ListNode current = head;
        for (int i = 1; i < array.length; i++) {
            current.next = new ListNode(array[i]);
            current = current.next;
        }
        return head;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode current = this;
        while (current != null) {
            sb.append(", ").append(current.val);
            current = current.next;
        }
        sb.append("]");
        return "[" + sb.substring(2);
    }

}
