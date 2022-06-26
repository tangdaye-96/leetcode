package psn.tangdaye.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DoubleLinkedNode {
    public int val;
    public DoubleLinkedNode left;
    public DoubleLinkedNode right;

    public DoubleLinkedNode(Integer integer) {
        val = integer;
    }

    public static DoubleLinkedNode fromArray(Integer[] array) {
        LinkedList<DoubleLinkedNode> q = new LinkedList<>();
        DoubleLinkedNode root = new DoubleLinkedNode(array[0]);
        q.push(root);
        int i = 1;
        while (!q.isEmpty() && i < array.length) {
            DoubleLinkedNode current = q.pop();
            if (array[i] != null) {
                current.left = new DoubleLinkedNode(array[i]);
                q.add(current.left);
            }
            i++;
            if (i < array.length && array[i] != null) {
                current.right = new DoubleLinkedNode(array[i]);
                q.add(current.right);
            }
            i++;
        }
        return root;
    }

    @Override
    public String toString() {
        List<Integer> x = new ArrayList<>();
        x.add(val);
        DoubleLinkedNode current = this;
        while (current.right != null && current.right != this) {
            x.add(current.right.val);
            current = current.right;
        }
        return x.toString();
    }
}
