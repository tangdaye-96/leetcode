package psn.tangdaye.structure;

/**
 * @date       : 2023/3/5 19:45
 * @author     : shayan
 * 红黑树的实现
 * 1. 叶子节点为黑节点
 * 2. 根节点为黑节点
 * 3. 红节点的父节点为黑节点（红节点的子节点为黑节点）
 * 4. 任意节点到叶子节点黑节点数目相同
 */
@SuppressWarnings("unchecked")
public class RBTreeNode<T extends Comparable<T>> extends BinarySearchTreeNode<T> {
    public RBColor color = RBColor.BLACK; // 默认是黑节点

    private final static RBTreeNode LEAF = new RBTreeNode(null);

    public RBTreeNode(T value) {
        super(value);
        if (value != null) {
            setLeft(leaf());
            setRight(leaf());
        }
    }

    public RBTreeNode(T value, RBColor color) {
        super(value);
        this.color = color;
        if (value != null) {
            setLeft(leaf());
            setRight(leaf());
        }
    }

    public boolean isLeaf() {
        return this == LEAF;
    }

    private static <T extends Comparable<T>> RBTreeNode<T> leaf() {
        return LEAF;
    }

    @Override
    public String toString() {
        if (color == RBColor.RED) {
            return super.toString() + "(r)";
        }
        return super.toString() + "(b)";
    }

    public enum RBColor {
        RED,
        BLACK,
        ;
    }

}
