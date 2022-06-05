package psn.tangdaye.solutions;

import psn.tangdaye.model.ListNode;
import psn.tangdaye.model.Node;

import java.util.HashMap;
import java.util.Stack;

public class Offer {

    /**
     * 剑指 Offer 09. 用两个栈实现队列
     */
    public static class CQueue {
        private final Stack<Integer> input = new Stack<>();

        private final Stack<Integer> output = new Stack<>();

        public CQueue() {
        }

        public void appendTail(int value) {
            input.push(value);
        }

        public int deleteHead() {
            if (output.isEmpty()) {
                while (!input.isEmpty()) {
                    output.push(input.pop());
                }
            }
            if (output.isEmpty()) {
                return -1;
            }
            return output.pop();
        }
    }

    /**
     * 剑指 Offer 30. 包含min函数的栈
     */
    public static class MinStack {

        private final Stack<Integer> data = new Stack<>();
        private final Stack<Integer> minSince = new Stack<>();

        public MinStack() {
            minSince.push(Integer.MAX_VALUE);
        }

        public void push(int x) {
            data.push(x);
            int minimum = minSince.pop();
            minSince.push(minimum);
            minSince.push(Math.min(x, minimum));
        }

        public void pop() {
            data.pop();
            minSince.pop();

        }

        public int top() {
            int top = data.pop();
            data.push(top);
            return top;
        }

        public int min() {
            int minimum = minSince.pop();
            minSince.push(minimum);
            return minimum;
        }
    }

    /**
     * 剑指 Offer 06. 从尾到头打印链表
     */
    public int[] reversePrint(ListNode head) {
        int len = 0;
        ListNode current = head;
        while (current != null) {
            len++;
            current = current.next;
        }

        int[] result = new int[len];
        int k = len - 1;
        current = head;
        while (current != null) {
            result[k] = current.val;
            k--;
            current = current.next;
        }
        return result;
    }

    /**
     * 剑指 Offer 24. 反转链表
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode current = head;
        ListNode reverseCurrent = new ListNode(head.val);
        while (current != null) {
            ListNode next = current.next;
            if (next != null) {
                ListNode reverseNext = new ListNode(next.val);
                reverseNext.next = reverseCurrent;
                reverseCurrent = reverseNext;
            }
            current = current.next;
        }
        return reverseCurrent;
    }

    /**
     * 剑指 Offer 35. 复杂链表的复制
     */
    public Node copyRandomList(Node head) {
        Node current = head;
        int len = 0;
        while (current != null) {
            current = current.next;
            len++;
        }

        Node[] nodes = new Node[len];
        HashMap<Integer, Integer> nodesMap = new HashMap<>();
        current = head;
        for (int i = 0; i < len; i++) {
            nodes[i] = current;
            nodesMap.put(current.hashCode(), i);
            current = current.next;
        }

        Node[] newNodes = new Node[len];
        for (int i = 0; i < len; i++) {
            newNodes[i] = new Node(nodes[i].val);
        }

        for (int i = 0; i < len; i++) {
            if (i != len - 1) {
                newNodes[i].next = newNodes[i + 1];
            }

            if (nodes[i].random != null) {
                newNodes[i].random = newNodes[nodesMap.get(nodes[i].random.hashCode())];
            }
        }

        if (newNodes.length == 0) return null;
        return newNodes[0];
    }

    /**
     * 剑指 Offer 05. 替换空格
     */
    public String replaceSpace(String s) {
        if (s == null) return null;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 32) {
                sb.append("%20");
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    /**
     * 剑指 Offer 58 - II. 左旋转字符串
     */
    public String reverseLeftWords(String s, int n) {
        char[] target = new char[s.length()];
        char[] src = s.toCharArray();
        System.arraycopy(src, 0, target, src.length - n, n);
        System.arraycopy(src, n, target, 0, src.length - n);
        return new String(target);
    }

}
