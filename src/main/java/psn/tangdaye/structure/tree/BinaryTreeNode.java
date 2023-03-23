package psn.tangdaye.structure.tree;

/**
 * @date       : 2023/3/5 13:35
 * @author     : shayan
 */
@SuppressWarnings("unchecked")
public class BinaryTreeNode<T> extends CommonTreeNode<T> {

    public BinaryTreeNode<T> left;

    public BinaryTreeNode<T> right;

    public BinaryTreeNode(T value) {
        super(value);

    }

    public BinaryTreeNode(T value, T leftValue, T rightValue) {
        super(value);
        setLeft(new BinaryTreeNode<>(leftValue));
        setRight(new BinaryTreeNode<>(rightValue));
    }

    public BinaryTreeNode<T> setLeft(BinaryTreeNode<T> l) {
        setChild(0, l);
        left = l;
        return this;
    }

    public BinaryTreeNode<T> setRight(BinaryTreeNode<T> r) {
        setChild(1, r);
        this.right = r;
        return this;
    }

    public BinaryTreeNode<T> grandParent() {
        if (parent != null) return (BinaryTreeNode<T>) parent.parent;
        return null;
    }

    public BinaryTreeNode<T> uncle() {
        BinaryTreeNode<T> grandParent = grandParent();
        if (parent != null && grandParent != null) {
            if (parent == grandParent.left) {
                return grandParent.right;
            } else {
                return grandParent.left;
            }
        }
        return null;
    }

    public boolean isLeftChild() {
        if (parent == null) throw new RuntimeException("root node");
        BinaryTreeNode<T> binaryTreeParent = (BinaryTreeNode<T>) parent;
        return this == binaryTreeParent.left;
    }

    public boolean isRightChild() {
        if (parent == null) throw new RuntimeException("root node");
        BinaryTreeNode<T> binaryTreeParent = (BinaryTreeNode<T>) parent;
        return this == binaryTreeParent.right;
    }

}
