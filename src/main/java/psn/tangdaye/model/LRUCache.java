package psn.tangdaye.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : shayan
 * 请你设计并实现一个满足 LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value)如果关键字key 已经存在，则变更其数据值value ；如果不存在，则向缓存中插入该组key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 * <p>
 * https://leetcode.cn/problems/lru-cache/?favorite=2cktkvj
 * @date : 2023/3/28 13:43
 */
public class LRUCache {

    private int maxSize;
    private Map<Integer, DoubleLinkNode> data;
    private DoubleLinkNode head; // head.pre = null 这是最先使用的
    private DoubleLinkNode tail; // tail.next = null 这是最近使用的

    public LRUCache(int capacity) {
        maxSize = capacity;
        data = new HashMap<>(capacity);
        head = null;
        tail = null;
    }

    public int get(int key) {
        if (!data.containsKey(key)) return -1;
        DoubleLinkNode node = data.get(key);
        move2Tail(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (data.containsKey(key)) {
            DoubleLinkNode node = data.get(key);
            node.val = value;
            move2Tail(node);
        } else {
            DoubleLinkNode node = new DoubleLinkNode(key, value);
            if (data.size() == 0) {
                head = node;
                tail = node;
            } else {
                if (data.size() >= maxSize) {
                    data.remove(head.key);
                    head = head.next;
                    if (head != null) {
                        head.pre = null;
                    }
                }
                move2Tail(node);
            }
            data.put(key, node);
        }
    }

    private class DoubleLinkNode {
        int key;
        int val;
        DoubleLinkNode pre;
        DoubleLinkNode next;

        DoubleLinkNode(int key, int val) {
            this.key = key;
            this.val = val;
        }

        @Override
        public String toString() {
            List<Integer> x = new ArrayList<>();
            x.add(val);
            DoubleLinkNode current = this;
            while (current.next != null && current.next != this) {
                x.add(current.next.val);
                current = current.next;
            }
            return x.toString();
        }

    }

    private void move2Tail(DoubleLinkNode node) {
        if (node == tail) return;
        if (head == node) {
            head = head.next;
            if (head != null) {
                head.pre = null;
            }
        }
        if (head == null) {
            head = node;
        }
        if (node.pre != null) {
            node.pre.next = node.next;
        }
        if (node.next != null) {
            node.next.pre = node.pre;
        }
        if (tail != null) {
            tail.next = node;
        }
        node.pre = tail;
        node.next = null;
        tail = node;
    }
}
