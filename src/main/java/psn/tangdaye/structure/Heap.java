package psn.tangdaye.structure;

import java.util.ArrayList;

/**
 * @date       : 2023/2/27 19:24
 * @author     : shayan
 */
public class Heap<T extends Comparable<T>> {
    private ArrayList<T> data;

    private boolean isMin;

    public Heap(Iterable<T> data, boolean isMin) {
        this.isMin = isMin;
        this.data = new ArrayList<>();
        for (T e : data) {
            if (e != null) {
                add(e);
            }
        }
    }

    public Heap(T[] data, boolean isMin) {
        this.isMin = isMin;
        this.data = new ArrayList<>();
        for (T e : data) {
            if (e != null) {
                add(e);
            }
        }
    }

    public void add(T element) {
        data.add(element);
        up(data.size() - 1);
    }

    public T top() {
        return data.get(0);
    }

    public T pop() {
        T last = data.remove(data.size() - 1);
        if (data.size() == 0) return last;
        T top = data.set(0, last);
        down(0);
        return top;
    }

    public int size() {
        return data.size();
    }

    public void setTop(T e) {
        data.set(0, e);
        down(0);
    }

    @Override
    public String toString() {
        return data.toString();
    }

    private void up(int index) {
        if (index <= 0) return;

        T current = data.get(index);
        int parentIndex = (index - 1) / 2;
        T parent = data.get(parentIndex);
        if ((isMin && parent.compareTo(current) > 0) || (!isMin && parent.compareTo(current) < 0)) {
            T origin = data.set(parentIndex, current);
            data.set(index, origin);
            up(parentIndex);
        }
    }

    private void down(int index) {
        if (index >= data.size() / 2) return;

        T current = data.get(index);
        int leftIndex = 2 * index + 1; // <= 2*(size/2-1)+1<= 2*size/2 -1 <= size-1
        int rightIndex = 2 * index + 2;// <=size
        int childIndex = -1;
        T child = null;
        T left = data.get(leftIndex);
        T right = null;
        if (rightIndex < data.size()) {
            right = data.get(rightIndex);
        }
        if (right == null) {
            child = left;
            childIndex = leftIndex;
        } else {
            if ((isMin && left.compareTo(right) < 0) || (!isMin && left.compareTo(right) > 0)) {
                child = left;
                childIndex = leftIndex;
            } else {
                child = right;
                childIndex = rightIndex;
            }

        }
        if ((isMin && current.compareTo(child) > 0) || (!isMin && current.compareTo(child) < 0)) {
            T origin = data.set(childIndex, current);
            data.set(index, origin);
            down(childIndex);
        }
    }
}
