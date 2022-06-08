package psn.tangdaye.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
}
