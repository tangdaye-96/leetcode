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
