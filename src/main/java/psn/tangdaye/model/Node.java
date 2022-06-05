package psn.tangdaye.model;

public class Node {
    public int val;
    public Node next;
    public Node random;
    int index;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }

    public static Node fromValue(Integer[][] value) {
        Node[] nodes = new Node[value.length];
        for (int i = 0; i < value.length; i++) {
            nodes[i] = new Node(value[i][0]);
        }
        for (int i = 0; i < value.length; i++) {
            if (i != value.length - 1) {
                nodes[i].next = nodes[i + 1];
            }
            if (value[i][1] != null) {
                nodes[i].random = nodes[value[i][1]];
            }
        }
        if (nodes.length == 0) return null;
        return nodes[0];

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Node current = this;
        int k = 0;
        while (current != null) {
            current.index = k;
            current = current.next;
            k++;
        }

        current = this;
        while (current != null) {
            sb.append(", ").append("[").append(current.val).append(",").append(null == current.random ? null : current.random.index).append("]");
            current = current.next;
        }
        sb.append("]");
        return "[" + sb.substring(2);
    }

}
