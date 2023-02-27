package psn.tangdaye.model;

import java.util.ArrayList;

/**
 * @date       : 2023/2/27 19:24
 * @author     : shayan
 */
public class Heap<T extends Comparable<T>> {
    private ArrayList<T> data;

    private boolean isMin;

    public Heap(T[] data, boolean isMin) {
    }

    public void add(T element) {
        // 加在最后面
        // 最后的元素【向上调整】
        data.add(element);
        upLast();
    }

    public T top() {
        return data.get(0);
    }

    public T pop() {
        // 交换第一个元素和最后一个元素
        // 删除最后一个元素
        // 第一个的元素【向下调整】
        T last = data.remove(data.size() - 1);
        T top = data.set(0, last);
        downTop();
        return top;
    }

    private void upLast() {
    }

    private void downTop() {
    }

}
