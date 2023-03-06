package psn.tangdaye.structure;

/**
 * @date       : 2023/3/6 15:48
 * @author     : shayan
 */
public class RBTree<T extends Comparable<T>> {
    private RBTreeNode<T> root;

    public RBTreeNode<T> getRoot() {
        return root;
    }

    public void add(T value) {
        if (root == null) {
            root = new RBTreeNode<>(value);
            return;
        }
        // 先增加红色节点，再调整
        RBTreeNode<T> a = binaryAdd(root, value);
        addCase1(a);
    }

    /**
     * a(r) ==> a(b) ok
     */
    private void addCase1(RBTreeNode<T> a) {
        // case 1: a is root
        if (a.parent == null) {
            a.color = RBTreeNode.RBColor.BLACK;
            root = a;
            return;
        }
        addCase2(a);
    }

    /**
     *   b(b)
     *     a(r)  ok
     */
    private void addCase2(RBTreeNode<T> a) {
        // case 2: a parent is black
        RBTreeNode<T> b = (RBTreeNode<T>) a.parent;
        if (b.color == RBTreeNode.RBColor.BLACK) {
            return;
        }
        addCase3(a);
    }

    /**
     *       c(b)                  c(r)
     *    b(r)   d(r)           b(b)    d(b)
     * a(r)             ==>  a(r)
     */
    private void addCase3(RBTreeNode<T> a) {
        // parent and uncle are both red
        RBTreeNode<T> b = (RBTreeNode<T>) a.parent;
        RBTreeNode<T> c = (RBTreeNode<T>) a.grandParent();
        RBTreeNode<T> d = (RBTreeNode<T>) a.uncle();
        if (d.color == RBTreeNode.RBColor.RED) {
            b.color = RBTreeNode.RBColor.BLACK;
            d.color = RBTreeNode.RBColor.BLACK;
            c.color = RBTreeNode.RBColor.RED;
            addCase1(c);
            return;
        }
        addCase4(a);
    }

    /**
     *        c(b)                    c(b)                     c(b)                    c(b)
     *    b(r)    d(b)    ==>      a(r)   d(b)             d(b)    b(r)    ==>     d(b)    a(r)
     *       a(r)               b(r)                             a(r)                         b(r)
     */
    private void addCase4(RBTreeNode<T> a) {
        // uncle is black && self/parent/grandParent is z-like(not strait)
        RBTreeNode<T> b = (RBTreeNode<T>) a.parent;
        if (a.isRightChild() && b.isLeftChild()) {
            leftRotate(a);
            a = b;
        } else if (a.isLeftChild() && b.isRightChild()) {
            rightRotate(a);
            a = b;
        }
        addCase5(a);
    }

    /**
     *        c(b)                     b(b)                    c(b)                      b(b)
     *     b(r)   d(b)    ==>      a(r)   c(r)             d(b)    b(r)      ==>     c(r)    a(r)
     *  a(r)                                 d(b)                     a(r)        d(b)
     */
    private void addCase5(RBTreeNode<T> a) {
        // uncle is black && self/parent/grandParent is strait
        RBTreeNode<T> b = (RBTreeNode<T>) a.parent;
        RBTreeNode<T> c = (RBTreeNode<T>) a.grandParent();
        if (a.isLeftChild() && b.isLeftChild()) {
            rightRotate(b);
        } else {
            leftRotate(b);
        }
        b.color = RBTreeNode.RBColor.BLACK;
        c.color = RBTreeNode.RBColor.RED;
    }

    /**
     * 左旋
     */
    private void leftRotate(RBTreeNode<T> a) {
        if (a.parent == null) {
            root = a;
            return;
        }
        if (a.isLeftChild()) throw new RuntimeException("left child cannot left rotate");
        RBTreeNode<T> rbGrandparent = (RBTreeNode<T>) a.grandParent();
        RBTreeNode<T> rbParent = (RBTreeNode<T>) a.parent;
        RBTreeNode<T> rbLeft = (RBTreeNode<T>) a.left;

        a.setLeft(rbParent);
        rbParent.setRight(rbLeft);

        if (root == rbParent) {
            root = a;
        }

        if (rbGrandparent != null) {
            if (rbGrandparent.left == rbParent) rbGrandparent.setLeft(a);
            else rbGrandparent.setRight(a);
        } else {
            a.parent = null;
        }
    }

    /**
     * 右旋
     */
    private void rightRotate(RBTreeNode<T> a) {
        if (a.parent == null) {
            root = a;
            return;
        }

        if (a.isRightChild()) throw new RuntimeException("right child cannot right rotate");
        RBTreeNode<T> rbGrandparent = (RBTreeNode<T>) a.grandParent();
        RBTreeNode<T> rbParent = (RBTreeNode<T>) a.parent;
        RBTreeNode<T> rbRight = (RBTreeNode<T>) a.right;

        a.setRight(rbParent);
        rbParent.setLeft(rbRight);

        if (root == rbParent) {
            root = a;
        }

        if (rbGrandparent != null) {
            if (rbGrandparent.right == rbParent) rbGrandparent.setRight(a);
            else rbGrandparent.setLeft(a);
        } else {
            a.parent = null;
        }
    }

    public static <T extends Comparable<T>> RBTree<T> simpleTree(T... values) {
        RBTree<T> tree = new RBTree<>();
        for (T value : values) {
            tree.add(value);
        }
        return tree;
    }

    private RBTreeNode<T> binaryAdd(RBTreeNode<T> root, T value) {
        if (root.value.compareTo(value) <= 0) {
            RBTreeNode<T> trueRight = (RBTreeNode<T>) root.right;
            if (trueRight.isLeaf()) {
                RBTreeNode<T> newOne = new RBTreeNode<>(value, RBTreeNode.RBColor.RED);
                root.setRight(newOne);
                return newOne;
            } else {
                return binaryAdd(trueRight, value);
            }
        } else {
            RBTreeNode<T> trueLeft = (RBTreeNode<T>) root.left;
            if (trueLeft.isLeaf()) {
                RBTreeNode<T> newOne = new RBTreeNode<>(value, RBTreeNode.RBColor.RED);
                root.setLeft(newOne);
                return newOne;
            } else {
                return binaryAdd(trueLeft, value);
            }
        }
    }

}
