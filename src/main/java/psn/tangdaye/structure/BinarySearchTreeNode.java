package psn.tangdaye.structure;

import psn.tangdaye.tool.Tools;

/**
 * @date       : 2023/3/5 16:49
 * @author     : shayan
 */
@SuppressWarnings("unchecked")
public class BinarySearchTreeNode<T extends Comparable> extends BinaryTreeNode<T> {

    public BinarySearchTreeNode(T value) {
        super(value);
    }

    /**
     * add 之后返回根节点
     */
    public static <T extends Comparable<T>> BinarySearchTreeNode<T> add(BinarySearchTreeNode<T> root, T value) {
        if (root.value.compareTo(value) <= 0) {
            if (root.right == null) {
                root.setRight(new BinarySearchTreeNode<>(value));
            } else {
                add((BinarySearchTreeNode<T>) root.right, value);
            }
        } else {
            if (root.left == null) {
                root.setLeft(new BinarySearchTreeNode<>(value));
            } else {
                add((BinarySearchTreeNode<T>) root.left, value);
            }
        }
        return root;
    }

    public static <T extends Comparable<T>> BinarySearchTreeNode<T> randomTree(T... values) {
        Tools.shuffle(values);
        return simpleTree(values);
    }

    public static <T extends Comparable<T>> BinarySearchTreeNode<T> simpleTree(T... values) {
        BinarySearchTreeNode<T> x = null;
        for (T value : values) {
            if (x == null) {
                x = new BinarySearchTreeNode<>(value);
            } else {
                add(x, value);
            }
        }
        return x;
    }

}
