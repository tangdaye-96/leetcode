package psn.tangdaye.solutions;

import psn.tangdaye.model.ListNode;
import psn.tangdaye.model.Node;
import psn.tangdaye.model.TreeNode;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

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

    /**
     * 剑指 Offer 03. 数组中重复的数字
     */
    public int findRepeatNumber(int[] nums) {
        boolean[] t = new boolean[nums.length];
        int k = 0;
        while (!t[nums[k]]) {
            t[nums[k]] = true;
            k++;
        }
        return nums[k];
    }

    /**
     * 剑指 Offer 53 - I. 在排序数组中查找数字 I
     */
    public int search(int[] nums, int target) {
        int index = Arrays.binarySearch(nums, target);
        if (index < 0) return 0;
        int i = index, j = index;
        while (i >= 0 && nums[i] == target) i--;
        while (j < nums.length && nums[j] == target) j++;
        return j - i - 1;
    }

    /**
     * 剑指 Offer 53 - II. 0～n-1中缺失的数字
     */
    public int missingNumber(int[] nums) {
        int n = nums.length + 1;
        int low = 0, high = n - 2;
        while (low <= high) {
            int mid = high + low >> 1;
            int midVal = nums[mid];
            if (midVal == mid) low = mid + 1;
            else high = mid - 1;
        }
        return low;
    }

    /**
     * 剑指 Offer 04. 二维数组中的查找
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int m = matrix.length; // row
        if (m == 0) return false;
        int n = matrix[0].length; // column
        if (n == 0) return false;

        int lowRow = 0, highRow = m - 1;
        while (lowRow <= highRow) {
            int midRow = highRow + lowRow >> 1;
            int midRowVal = matrix[midRow][0];
            if (midRowVal < target) lowRow = midRow + 1;
            else if (midRowVal > target) highRow = midRow - 1;
            else return true;
        }
        int row = lowRow;

        int lowColumn = 0, highColumn = n - 1;
        while (lowColumn <= highColumn) {
            int midColumn = lowColumn + highColumn >> 1;
            int midColumnVal = matrix[0][midColumn];
            if (midColumnVal < target) lowColumn = midColumn + 1;
            else if (midColumnVal > target) highColumn = midColumn - 1;
            else return true;
        }
        int column = lowColumn;

        for (int i = 0; i < row; i++) {
            int index = Arrays.binarySearch(matrix[i], 0, column, target);
            if (index > 0) return true;
        }
        return false;
    }

    /**
     * 剑指 Offer 11. 旋转数组的最小数字
     */
    public int minArray(int[] numbers) {
        int low = 0, high = numbers.length - 1;
        while (low < high) {
            if (numbers[low] < numbers[high]) break;
            int mid = low + high >> 1;
            if (numbers[mid] > numbers[low]) low = mid + 1;
            else if (numbers[mid] < numbers[high]) high = mid;
            else if (numbers[low] == numbers[high]) {
                low++;
                high--;
            } else if (numbers[mid] == numbers[high]) {
                high = mid;
            } else if (numbers[mid] == numbers[low]) {
                low = mid + 1;
            }
        }
        return numbers[low];
    }

    /**
     * 剑指 Offer 50. 第一个只出现一次的字符
     */
    public char firstUniqChar(String s) {
        short[] x = new short[26];
        for (int i = 0; i < s.length(); i++) {
            x[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (x[s.charAt(i) - 'a'] == 1) return s.charAt(i);
        }
        return ' ';
    }

    /**
     * 剑指 Offer 32 - I. 从上到下打印二叉树
     */
    public int[] levelOrder(TreeNode root) {
        if (root == null) return new int[0];
        TreeNode[] q = new TreeNode[1000];
        ArrayList<Integer> a = new ArrayList<>();
        int i = 0, j = 1;
        q[i] = root;
        while (i != j) {
            TreeNode current = q[i];
            i++;
            a.add(current.val);
            if (current.left != null) {
                q[j] = current.left;
                j++;
            }
            if (current.right != null) {
                q[j] = current.right;
                j++;
            }
        }
        int[] array = new int[a.size()];
        for (int m = 0; m < a.size(); m++) {
            array[m] = a.get(m);
        }
        return array;
    }

    /**
     * 剑指 Offer 32 - II. 从上到下打印二叉树 II
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<List<TreeNode>> treeNodes = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        treeNodes.add(Collections.singletonList(root));
        int i = 0, layer = 1;
        while (i != layer) {
            List<TreeNode> currentLayer = treeNodes.get(i);
            i++;
            result.add(currentLayer.stream().map(treeNode -> treeNode.val).collect(Collectors.toList()));
            List<TreeNode> nextLayer = new ArrayList<>();
            boolean next = false;
            for (TreeNode treeNode : currentLayer) {
                if (treeNode.left != null) {
                    nextLayer.add(treeNode.left);
                    next = true;
                }
                if (treeNode.right != null) {
                    nextLayer.add(treeNode.right);
                    next = true;
                }
            }
            if (next) {
                layer++;
                treeNodes.add(nextLayer);
            }
        }
        return result;
    }

    /**
     * 剑指 Offer 32 - III. 从上到下打印二叉树 III
     */
    public List<List<Integer>> levelOrder3(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<List<TreeNode>> treeNodes = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        treeNodes.add(Collections.singletonList(root));
        int i = 0, layer = 1;
        while (i != layer) {
            List<TreeNode> currentLayer = treeNodes.get(i);
            if (i % 2 == 0) {
                result.add(currentLayer.stream().map(treeNode -> treeNode.val).collect(Collectors.toList()));
            } else {
                List<Integer> integers = new ArrayList<>();
                for (int s = currentLayer.size() - 1; s >= 0; s--) {
                    integers.add(currentLayer.get(s).val);
                }
                result.add(integers);
            }
            i++;
            List<TreeNode> nextLayer = new ArrayList<>();
            boolean next = false;
            for (TreeNode treeNode : currentLayer) {
                if (treeNode.left != null) {
                    nextLayer.add(treeNode.left);
                    next = true;
                }
                if (treeNode.right != null) {
                    nextLayer.add(treeNode.right);
                    next = true;
                }
            }
            if (next) {
                layer++;
                treeNodes.add(nextLayer);
            }
        }
        return result;
    }

    /**
     * 剑指 Offer 26. 树的子结构
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        return A != null && A.contains(B);
    }

    /**
     * 剑指 Offer 27. 二叉树的镜像
     */
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return null;
        return root.mirror();
    }

    /**
     * 剑指 Offer 28. 对称的二叉树
     */
    public boolean isSymmetric(TreeNode root) {
        return root == null || root.isSymmetric();
    }

    /**
     * 剑指 Offer 10- I. 斐波那契数列
     */
    public int fib(int n) {
        if (n < 2) return n;
        BigInteger a = new BigInteger("0");
        BigInteger b = new BigInteger("1");
        BigInteger c = new BigInteger("1");
        for (int i = 2; i <= n; i++) {
            c = a.add(b);
            a = b;
            b = c;
        }
        return c.mod(new BigInteger("1000000007")).intValue();
    }
}
