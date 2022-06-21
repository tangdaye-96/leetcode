package psn.tangdaye.model;

import java.util.LinkedList;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    public static TreeNode fromArray(Integer[] array) {
        LinkedList<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(array[0]);
        q.push(root);
        int i = 1;
        while (!q.isEmpty() && i < array.length) {
            TreeNode current = q.pop();
            if (array[i] != null) {
                current.left = new TreeNode(array[i]);
                q.add(current.left);
            }
            i++;
            if (i < array.length && array[i] != null) {
                current.right = new TreeNode(array[i]);
                q.add(current.right);
            }
            i++;
        }
        return root;
    }

    public LinkedList<Integer> layer() {
        LinkedList<TreeNode> q = new LinkedList<>();
        q.push(this);
        LinkedList<Integer> result = new LinkedList<>();
        while (!q.isEmpty()) {
            TreeNode current = q.pop();
            if (current != null) {
                result.add(current.val);
                q.add(current.left);
                q.add(current.right);
            } else {
                result.add(null);
            }
        }
        while (true) {
            Integer t = result.pollLast();
            if (t != null) {
                result.add(t);
                break;
            }
        }
        return result;
    }

    public LinkedList<Integer> pre() {
        LinkedList<Integer> result = new LinkedList<>();
        pre(result, this);
        return result;
    }

    private static void pre(LinkedList<Integer> result, TreeNode current) {
        if (current == null) return;
        result.add(current.val);
        pre(result, current.left);
        pre(result, current.right);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TreeNode)) return false;
        TreeNode theOther = (TreeNode) obj;
        return toString().equals(theOther.toString());
    }

    @Override
    public String toString() {
        String t = pre().toString();
        return t.substring(1, t.length() - 1);
    }
}
