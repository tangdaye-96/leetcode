package psn.tangdaye.model;

import java.util.ArrayList;
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

    public ArrayList<Integer> middle() {
        ArrayList<Integer> result = new ArrayList<>();
        middle(result, this);
        return result;
    }

    private void middle(ArrayList<Integer> result, TreeNode current) {
        if (current == null) return;
        middle(result, current.left);
        result.add(current.val);
        middle(result, current.right);
    }

    public TreeNode mirror() {
        TreeNode treeNode = new TreeNode(val);
        if (left != null) {
            treeNode.right = left.mirror();
        }
        if (right != null) {
            treeNode.left = right.mirror();
        }
        return treeNode;
    }

    public boolean isSymmetric() {
        return preDFSReverse(left, right);
    }

    private void pre(LinkedList<Integer> result, TreeNode current) {
        if (current == null) return;
        result.add(current.val);
        pre(result, current.left);
        pre(result, current.right);
    }

    private boolean preDFS(TreeNode a, TreeNode b) {
        if (b == null) return true;
        if (a == null) return false;
        return a.val == b.val && preDFS(a.left, b.left) && preDFS(a.right, b.right);
    }

    private boolean preDFSReverse(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null) return false;
        if (b == null) return false;
        return a.val == b.val && preDFSReverse(a.left, b.right) && preDFSReverse(a.right, b.left);
    }

    @Override
    public String toString() {
        String t = pre().toString();
        return t.substring(1, t.length() - 1);
    }

    public boolean contains(TreeNode b) {
        if (b == null) return false;
        return preDFS(this, b) || (left != null && left.contains(b)) || (right != null && right.contains(b));
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TreeNode)) return false;
        TreeNode t = (TreeNode) obj;
        return val == t.val
                && ((left != null && left.equals(t.left) || (left == null && t.left == null)))
                && ((right != null && right.equals(t.right) || (right == null && t.right == null)));
    }
}
