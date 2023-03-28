package psn.tangdaye.structure.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @date       : 2023/3/5 13:37
 * @author     : shayan
 */
public class CommonTreeNode<T> {

    public T value;

    protected List<CommonTreeNode<T>> children;

    protected CommonTreeNode<T> parent;

    public CommonTreeNode(T value) {
        this.value = value;
    }

    public CommonTreeNode(T value, CommonTreeNode<T>... children) {
        this.value = value;
        this.children = Arrays.asList(children);
        for (CommonTreeNode<T> child : children) {
            if (child != null) child.parent = this;
        }
    }

    public void setChild(int i, CommonTreeNode<T> node) {
        if (children == null) children = new ArrayList<>();
        while (children.size() <= i) {
            children.add(null);
        }
        children.set(i, node);
        if (node != null) node.parent = this;
    }

    public Iterable<CommonTreeNode<T>> preOrder() {
        return PreIterator::new;
    }

    public Iterable<CommonTreeNode<T>> layerOrder() {
        return LayerIterator::new;
    }

    public String layerString() {
        StringBuilder s = new StringBuilder();
        for (CommonTreeNode<T> node : layerOrder()) {
            s.append(String.valueOf(node)).append(", ");
        }
        return s.substring(0, s.length() - 2);
    }

    public String toString() {
        return String.valueOf(value);
    }

    private class PreIterator implements Iterator<CommonTreeNode<T>> {
        private LinkedList<CommonTreeNode<T>> q = new LinkedList<>();

        public PreIterator() {
            q.push(CommonTreeNode.this);
        }


        @Override
        public boolean hasNext() {
            return q.size() > 0;
        }

        @Override
        public CommonTreeNode<T> next() {
            if (!q.isEmpty()) {
                CommonTreeNode<T> t = q.pop();
                if (t != null) {
                    if (t.children != null) {
                        for (int i = t.children.size() - 1; i >= 0; i--) {
                            q.push(t.children.get(i));
                        }
                    }
                }
                return t;
            }
            return null;
        }
    }

    private class LayerIterator implements Iterator<CommonTreeNode<T>> {
        private LinkedList<CommonTreeNode<T>> q = new LinkedList<>();

        public LayerIterator() {
            q.push(CommonTreeNode.this);
        }

        @Override
        public boolean hasNext() {
            return q.size() > 0;
        }

        @Override
        public CommonTreeNode<T> next() {
            if (!q.isEmpty()) {
                CommonTreeNode<T> t = q.pop();
                if (t != null) {
                    if (t.children != null) {
                        q.addAll(t.children);
                    }
                }
                return t;
            }
            return null;
        }
    }

//    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        CommonTreeNode<Integer> a = new CommonTreeNode<>(1,
                new CommonTreeNode<>(2,
                        null, new CommonTreeNode<>(5)
                ),
                new CommonTreeNode<>(3,
                        new CommonTreeNode<>(6), new CommonTreeNode<>(7)
                )
        );
        for (CommonTreeNode<Integer> x : a.preOrder()) {
            System.out.println(String.valueOf(x));
        }
        System.out.println("=======");

        for (CommonTreeNode<Integer> x : a.layerOrder()) {
            System.out.println(String.valueOf(x));
        }
    }

}
