package psn.tangdaye.solutions;

import psn.tangdaye.model.ListNode;
import psn.tangdaye.model.TreeNode;
import psn.tangdaye.tool.Tools;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author shayan
 * @date : 2023/5/12 13:40
 * @url : <a href="https://leetcode.cn/problem-list/xb9lfcwi/">https://leetcode.cn/problem-list/xb9lfcwi/</a>
 */
public class Interview {
    /**
     * 面试题 01.01. 判定字符是否唯一
     * <p>
     * 实现一个算法，确定一个字符串 s 的所有字符是否全都不同
     * <p>
     * <a href="https://leetcode.cn/problems/is-unique-lcci/?favorite=xb9lfcwi">https://leetcode.cn/problems/is-unique-lcci/?favorite=xb9lfcwi</a>
     */
    public boolean isUnique(String str) {
        int v = 0;
        for (int i = 0; i < str.length(); i++) {
            int u = v ^ 1 << (str.charAt(i) - 'a');
            if (u < v) return false;
            v = u;
        }
        return true;
    }

    /**
     * 面试题 01.02. 判定是否互为字符重排
     * <p>
     * 给定两个由小写字母组成的字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串
     * <p>
     * <a href="https://leetcode.cn/problems/check-permutation-lcci/?favorite=xb9lfcwi">https://leetcode.cn/problems/check-permutation-lcci/?favorite=xb9lfcwi</a>
     */
    public boolean checkPermutation(String s1, String s2) {
        int[] t = new int[26];
        int i = 0;
        while (i < s1.length() && i < s2.length()) {
            t[s1.charAt(i) - 'a'] += 1;
            t[s2.charAt(i) - 'a'] -= 1;
            i++;
        }
        i--;
        while (i < s1.length() - 1) {
            t[s1.charAt(i) - 'a'] += 1;
            i++;
        }
        while (i < s2.length() - 1) {
            t[s2.charAt(i) - 'a'] -= 1;
            i++;
        }
        for (int j = 0; j < 26; j++) {
            if (t[j] != 0) return false;
        }
        return true;
    }

    /**
     * 面试题 01.03. URL化
     * <p>
     * URL化。编写一种方法，将字符串中的空格全部替换为%20。假定该字符串尾部有足够的空间存放新增字符，并且知道字符串的“真实”长度。（注：用Java实现的话，请使用字符数组实现，以便直接在数组上操作。）
     * <p>
     * <a href="https://leetcode.cn/problems/string-to-url-lcci/?favorite=xb9lfcwi">https://leetcode.cn/problems/string-to-url-lcci/?favorite=xb9lfcwi</a>
     */
    public String replaceSpaces(String s, int length) {
        char[] newValue = new char[s.length()];
        int j = 0;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c != ' ') newValue[j] = c;
            else {
                newValue[j] = '%';
                newValue[j + 1] = '2';
                newValue[j + 2] = '0';
                j += 2;
            }
            j += 1;
        }
        return new String(newValue, 0, j);
    }

    /**
     * 面试题 01.04. 回文排列
     * <p>
     * 给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。
     * <p>
     * <a href="https://leetcode.cn/problems/palindrome-permutation-lcci/?favorite=xb9lfcwi">https://leetcode.cn/problems/palindrome-permutation-lcci/?favorite=xb9lfcwi</a>
     */
    public boolean canPermutePalindrome(String s) {
        Map<Character, Integer> map = Tools.str2dic(s, 0, s.length() - 1);
        boolean flag = false;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() % 2 == 1 && flag) return false;
            else if (entry.getValue() % 2 == 1) flag = true;
        }
        return true;
    }

    /**
     * 面试题 01.05. 一次编辑
     * <p>
     * 字符串有三种编辑操作:插入一个英文字符、删除一个英文字符或者替换一个英文字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
     * <p>
     * <a href="https://leetcode.cn/problems/one-away-lcci/?favorite=xb9lfcwi">https://leetcode.cn/problems/one-away-lcci/?favorite=xb9lfcwi</a>
     */
    public boolean oneEditAway(String first, String second) {
        String longOne = first.length() > second.length() ? first : second;
        String shortOne = first.length() > second.length() ? second : first;
        if (longOne.length() - shortOne.length() > 1) return false;
        if (longOne.length() - shortOne.length() == 1) {
            int i = 0, j = 0;
            while (i < longOne.length() && j < shortOne.length()) {
                if (longOne.charAt(i) == shortOne.charAt(j)) j++;
                i++;
            }
            return j == shortOne.length();
        } else {
            int i = 0, count = 0;
            while (i < longOne.length()) {
                if (longOne.charAt(i) != shortOne.charAt(i)) count++;
                if (count > 1) return false;
                i++;
            }
            return true;
        }

    }

    private int editDistance(String first, String second) {
        int[][] dp = new int[1 + first.length()][1 + second.length()];
        for (int i = 0; i <= first.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= second.length(); j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= first.length(); i++) {
            for (int j = 1; j <= second.length(); j++) {
                char c1 = first.charAt(i - 1);
                char c2 = second.charAt(j - 1);
                if (c1 == c2) dp[i][j] = dp[i - 1][j - 1];
                else {
                    int x = dp[i - 1][j - 1];
                    int y = dp[i - 1][j];
                    int z = dp[i][j - 1];
                    dp[i][j] = 1 + (x < y ? Math.min(x, z) : Math.min(y, z));
                }
            }
        }
        return dp[first.length()][second.length()];
    }

    /**
     * 面试题 01.06. 字符串压缩
     * <p>
     * 字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短，则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。
     * <p>
     * <a href="https://leetcode.cn/problems/compress-string-lcci/?favorite=xb9lfcwi">https://leetcode.cn/problems/compress-string-lcci/?favorite=xb9lfcwi</a>
     */
    public String compressString(String s) {
        if (s.length() == 0) return s;
        char current = s.charAt(0);
        int count = 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == current) count++;
            else {
                sb.append(current).append(count);
                current = c;
                count = 1;
            }
        }
        sb.append(current).append(count);
        String result = sb.toString();
        return result.length() < s.length() ? result : s;
    }

    /**
     * 面试题 01.07. 旋转矩阵
     * <p>
     * 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
     * <p>
     * <a href="https://leetcode.cn/problems/rotate-matrix-lcci/?favorite=xb9lfcwi">https://leetcode.cn/problems/rotate-matrix-lcci/?favorite=xb9lfcwi</a>
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            // 仅处理matrix[i][i]到matrix[i][n-1-i],一共(n-1-2*i)个单元格
            for (int j = i; j < n - 1 - i; j++) {
                // (i,j) -> (j, n-1-i) -> (n-1-i, n-1-j) -> (n-1-j,i) -> (i,j);
                int t = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = t;
            }
        }
    }

    /**
     * 面试题 01.08. 零矩阵
     * <p>
     * 编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
     * <p>
     * <a href="https://leetcode.cn/problems/zero-matrix-lcci/?favorite=xb9lfcwi">https://leetcode.cn/problems/zero-matrix-lcci/?favorite=xb9lfcwi</a>
     */
    public void setZeroes(int[][] matrix) {
        Set<Integer> columns = new HashSet<>();
        Set<Integer> rows = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    columns.add(j);
                }
            }
        }
        for (int i : rows) {
            for (int j = 0; j < matrix[0].length; j++) matrix[i][j] = 0;
        }
        for (int j : columns) {
            for (int i = 0; i < matrix.length; i++) matrix[i][j] = 0;
        }
    }

    /**
     * 面试题 01.09. 字符串轮转
     * <p>
     * 字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成（比如，waterbottle是erbottlewat旋转后的字符串）。
     * <p>
     * <a href="https://leetcode.cn/problems/string-rotation-lcci/?favorite=xb9lfcwi">https://leetcode.cn/problems/string-rotation-lcci/?favorite=xb9lfcwi</a>
     */
    public boolean isFlippedString(String s1, String s2) {
        return s1.length() == s2.length() && (s2 + s2).contains(s1);
    }

    /**
     * 面试题 02.01. 移除重复节点
     * <p>
     * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
     * <p>
     * <a href="https://leetcode.cn/problems/remove-duplicate-node-lcci/?favorite=xb9lfcwi">https://leetcode.cn/problems/remove-duplicate-node-lcci/?favorite=xb9lfcwi</a>
     */
    public ListNode removeDuplicateNodes(ListNode head) {
        ListNode current1 = head;
        while (current1 != null) {
            ListNode pre2 = current1;
            ListNode current2 = current1.next;
            while (current2 != null) {
                if (current2.val == current1.val) pre2.next = current2.next;
                else pre2 = pre2.next;
                current2 = current2.next;
            }
            current1 = current1.next;
        }
        return head;
    }

    /**
     * 面试题 02.02. 返回倒数第 k 个节点
     * <p>
     * 实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。
     * <p>
     * <a href="https://leetcode.cn/problems/kth-node-from-end-of-list-lcci/?favorite=xb9lfcwi">https://leetcode.cn/problems/kth-node-from-end-of-list-lcci/?favorite=xb9lfcwi</a>
     */
    public int kthToLast(ListNode head, int k) {
        ListNode current = head;
        for (int i = 0; i < k; i++) {
            current = current.next;
        }
        ListNode result = head;
        while (current != null) {
            result = result.next;
            current = current.next;
        }
        return result.val;
    }

    /**
     * 面试题 02.03. 删除中间节点
     * <p>
     * 若链表中的某个节点，既不是链表头节点，也不是链表尾节点，则称其为该链表的「中间节点」。
     * <p>
     * 假定已知链表的某一个中间节点，请实现一种算法，将该节点从链表中删除。
     * <p>
     * <a href="https://leetcode.cn/problems/delete-middle-node-lcci/?favorite=xb9lfcwi">https://leetcode.cn/problems/delete-middle-node-lcci/?favorite=xb9lfcwi</a>
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     * 面试题 02.04. 分割链表
     * <p>
     * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
     * <p>
     * <a href="https://leetcode.cn/problems/partition-list-lcci/?favorite=xb9lfcwi">https://leetcode.cn/problems/partition-list-lcci/?favorite=xb9lfcwi</a>
     */
    public ListNode partition(ListNode head, int x) {
        // (<x) pos (>=x) current (未比较)
        ListNode t = new ListNode(Integer.MIN_VALUE);
        t.next = head;
        ListNode current = head;
        ListNode pos = t;
        boolean flag = false;
        while (current != null) {
            if (current.val >= x) {
                current = current.next;
            } else {
                // 当前元素复制到pos下一个
                ListNode temp = pos.next;
                pos.next = new ListNode(current.val);
                pos.next.next = temp;
                pos = pos.next;
                // 删除当前元素
                if (current.next != null) {
                    current.val = current.next.val;
                    current.next = current.next.next;
                } else {
                    flag = true;
                    break;
                }
            }
        }
        if (flag) {
            current = t.next;
            while (current.next != null) {
                if (current.next.next == null) {
                    current.next = null;
                    break;
                }
                current = current.next;
            }
        }
        return t.next;
    }

    /**
     * 面试题 02.05. 链表求和
     * <p>
     * 给定两个用链表表示的整数，每个节点包含一个数位。这些数位是反向存放的，也就是个位排在链表首部。
     * <p>
     * <a href="https://leetcode.cn/problems/sum-lists-lcci/?favorite=xb9lfcwi">https://leetcode.cn/problems/sum-lists-lcci/?favorite=xb9lfcwi</a>
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode current1 = l1;
        ListNode current2 = l2;
        ListNode head = new ListNode(-1);
        ListNode pre = head;
        boolean flag = false;
        while (current1 != null || current2 != null || flag) {
            ListNode current;
            int sum = flag ? 1 : 0;
            if (current1 != null) sum += current1.val;
            if (current2 != null) sum += current2.val;
            if (sum >= 10) {
                current = new ListNode(sum - 10);
                flag = true;
            } else {
                current = new ListNode(sum);
                flag = false;
            }
            pre.next = current;
            pre = pre.next;
            if (current1 != null) current1 = current1.next;
            if (current2 != null) current2 = current2.next;
        }
        return head.next;
    }

    /**
     * 面试题 02.06. 回文链表
     * <p>
     * 编写一个函数，检查输入的链表是否是回文的。
     * <p>
     * <a href="https://leetcode.cn/problems/palindrome-linked-list-lcci/?favorite=xb9lfcwi">https://leetcode.cn/problems/palindrome-linked-list-lcci/?favorite=xb9lfcwi</a>
     */
    public boolean isPalindrome(ListNode head) {
        int n = 0;
        ListNode tail = head;
        while (tail != null) {
            tail = tail.next;
            n++;
        }
        ListNode pre = null;
        ListNode current = head;
        for (int i = 0; i < n / 2; i++) {
            ListNode nextOne = current.next;
            current.next = pre;
            pre = current;
            current = nextOne;
        }
        if (n % 2 == 1) current = current.next;
        while (current != null && pre != null) {
            if (current.val != pre.val) return false;
            current = current.next;
            pre = pre.next;
        }
        return true;
    }

    /**
     * 面试题 02.07. 链表相交
     * <p>
     * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
     * <p>
     * <a href="https://leetcode.cn/problems/intersection-of-two-linked-lists-lcci/?favorite=xb9lfcwi">https://leetcode.cn/problems/intersection-of-two-linked-lists-lcci/?favorite=xb9lfcwi</a>
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int m = 0;
        int n = 0;
        ListNode current1 = headA;
        ListNode current2 = headB;
        while (current1 != null) {
            current1 = current1.next;
            m++;
        }
        while (current2 != null) {
            current2 = current2.next;
            n++;
        }
        current1 = headA;
        current2 = headB;
        while (m > n) {
            current1 = current1.next;
            m--;
        }
        while (n > m) {
            current2 = current2.next;
            n--;
        }
        while (current1 != current2) {
            current1 = current1.next;
            current2 = current2.next;
        }
        return current1;
    }

    /**
     * 面试题 02.08. 环路检测
     * <p>
     * 给定一个链表，如果它是有环链表，实现一个算法返回环路的开头节点。若环不存在，请返回 null。
     * <p>
     * <a href="https://leetcode.cn/problems/linked-list-cycle-lcci/?favorite=xb9lfcwi">https://leetcode.cn/problems/linked-list-cycle-lcci/?favorite=xb9lfcwi</a>
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        int r = 0;
        int i = 0;
        boolean first = true;
        while (fast != null && slow != null) {
            if (fast.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                if (first) {
                    r = i;
                    first = false;
                } else {
                    r = i - r;
                    break;
                }
            }
            i++;
        }
        if (fast == null || slow == null) return null;
        fast = head;
        slow = head;
        for (int k = 0; k < r; k++) fast = fast.next;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 面试题 03.01. 三合一
     * <p>
     * 三合一。描述如何只用一个数组来实现三个栈。
     * <p>
     * <a href="https://leetcode.cn/problems/three-in-one-lcci/?favorite=xb9lfcwi">https://leetcode.cn/problems/three-in-one-lcci/?favorite=xb9lfcwi</a>
     */
    public static class TripleInOne {
        int[] value;
        int size;
        int[] point;

        public TripleInOne(int stackSize) {
            value = new int[stackSize * 3];
            size = stackSize;
            point = new int[]{0, size, 2 * size};
        }

        public void push(int stackNum, int value) {
            int index = point[stackNum];
            if (index < size * (stackNum + 1)) {
                this.value[index] = value;
                index++;
                point[stackNum] = index;
            }
        }

        public int pop(int stackNum) {
            int index = point[stackNum];
            index--;
            if (index >= size * stackNum) {
                point[stackNum] = index;
                return value[index];
            } else return -1;
        }

        public int peek(int stackNum) {
            int index = point[stackNum];
            index--;
            if (index >= size * stackNum) return value[index];
            else return -1;
        }

        public boolean isEmpty(int stackNum) {
            int index = point[stackNum];
            return index == size * stackNum;
        }
    }

    /**
     * 面试题 03.02. 栈的最小值
     * <p>
     * 请设计一个栈，除了常规栈支持的pop与push函数以外，还支持min函数，该函数返回栈元素中的最小值。执行push、pop和min操作的时间复杂度必须为O(1)。
     * <p>
     * <a href="https://leetcode.cn/problems/min-stack-lcci/?favorite=xb9lfcwi">https://leetcode.cn/problems/min-stack-lcci/?favorite=xb9lfcwi</a>
     */
    public static class MinStack {
        private Stack<Integer> value;
        private Stack<Integer> minSince;

        public MinStack() {
            value = new Stack<>();
            minSince = new Stack<>();
        }

        public void push(int x) {
            value.push(x);
            if (minSince.isEmpty() || minSince.peek() > x) minSince.push(x);
            else minSince.push(minSince.peek());
        }

        public void pop() {
            value.pop();
            minSince.pop();
        }

        public int top() {
            return value.peek();
        }

        public int getMin() {
            return minSince.peek();
        }
    }

    /**
     * 面试题 03.03. 堆盘子
     * <p>
     * 堆盘子。设想有一堆盘子，堆太高可能会倒下来。因此，在现实生活中，盘子堆到一定高度时，我们就会另外堆一堆盘子。请实现数据结构SetOfStacks，模拟这种行为。SetOfStacks应该由多个栈组成，并且在前一个栈填满时新建一个栈。此外，SetOfStacks.push()和SetOfStacks.pop()应该与普通栈的操作方法相同（也就是说，pop()返回的值，应该跟只有一个栈时的情况一样）。 进阶：实现一个popAt(int index)方法，根据指定的子栈，执行pop操作。
     * <p>
     * <a href="https://leetcode.cn/problems/stack-of-plates-lcci/?favorite=xb9lfcwi">https://leetcode.cn/problems/stack-of-plates-lcci/?favorite=xb9lfcwi</a>
     */
    public static class StackOfPlates {
        private Stack<Stack<Integer>> values;
        private int size;

        public StackOfPlates(int cap) {
            values = new Stack<>();
            size = cap;
        }

        public void push(int val) {
            if (size == 0) return;
            if (values.isEmpty()) values.push(new Stack<>());
            Stack<Integer> t = values.peek();
            if (t.size() == size) values.push(new Stack<Integer>() {{
                push(val);
            }});
            else t.push(val);
        }

        public int pop() {
            if (size == 0) return -1;
            if (values.isEmpty()) return -1;
            Stack<Integer> t = values.pop();
            int x = t.pop();
            if (!t.isEmpty()) values.push(t);
            return x;
        }

        public int popAt(int index) {
            if (size == 0) return -1;
            if (values.size() <= index) return -1;
            Stack<Integer> t = values.get(index);
            int x = t.pop();
            if (t.isEmpty()) values.remove(index);
            return x;
        }
    }

    /**
     * 面试题 03.04. 化栈为队
     * <p>
     * 实现一个MyQueue类，该类用两个栈来实现一个队列。
     * <p>
     * <a href="https://leetcode.cn/problems/implement-queue-using-stacks-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/implement-queue-using-stacks-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public static class MyQueue {
        private Stack<Integer> in;
        private Stack<Integer> out;

        public MyQueue() {
            in = new Stack<>();
            out = new Stack<>();
        }

        public void push(int x) {
            in.push(x);
        }

        public int pop() {
            if (out.isEmpty()) while (!in.isEmpty()) out.push(in.pop());
            return out.pop();
        }

        public int peek() {
            if (out.isEmpty()) while (!in.isEmpty()) out.push(in.pop());
            return out.peek();
        }

        public boolean empty() {
            return in.isEmpty() && out.isEmpty();
        }
    }

    /**
     * 面试题 03.05. 栈排序
     * <p>
     * 栈排序。 编写程序，对栈进行排序使最小元素位于栈顶。最多只能使用一个其他的临时栈存放数据，但不得将元素复制到别的数据结构（如数组）中。该栈支持如下操作：push、pop、peek 和 isEmpty。当栈为空时，peek返回 -1。
     * <p>
     * <a href="https://leetcode.cn/problems/sort-of-stacks-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/sort-of-stacks-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public static class SortedStack {

        private Stack<Integer> stack;


        public SortedStack() {
            stack = new Stack<>();
        }

        public void push(int val) {
            Stack<Integer> t = new Stack<>();
            while (!stack.isEmpty() && stack.peek() < val) t.push(stack.pop());
            stack.push(val);
            while (!t.isEmpty()) stack.push(t.pop());
        }

        public void pop() {
            if (stack.isEmpty()) return;
            stack.pop();
        }

        public int peek() {
            if (stack.isEmpty()) return -1;
            return stack.peek();
        }

        public boolean isEmpty() {
            return stack.isEmpty();
        }
    }

    /**
     * 面试题 03.06. 动物收容所
     * <p>
     * 动物收容所。有家动物收容所只收容狗与猫，且严格遵守“先进先出”的原则。在收养该收容所的动物时，收养人只能收养所有动物中“最老”（由其进入收容所的时间长短而定）的动物，或者可以挑选猫或狗（同时必须收养此类动物中“最老”的）。换言之，收养人不能自由挑选想收养的对象。请创建适用于这个系统的数据结构，实现各种操作方法，比如enqueue、dequeueAny、dequeueDog和dequeueCat。允许使用Java内置的LinkedList数据结构。
     * <p>
     * <a href="https://leetcode.cn/problems/animal-shelter-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/animal-shelter-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public static class AnimalShelf {

        private LinkedList<Integer> queueCat;

        private LinkedList<Integer> queueDog;


        public AnimalShelf() {
            queueCat = new LinkedList<>();
            queueDog = new LinkedList<>();
        }

        public void enqueue(int[] animal) {
            if (animal[1] == 0) queueCat.add(animal[0]);
            if (animal[1] == 1) queueDog.add(animal[0]);
        }

        public int[] dequeueAny() {
            if (queueCat.isEmpty() && queueDog.isEmpty()) {
                return new int[]{-1, -1};
            }
            if (queueCat.isEmpty()) return new int[]{queueDog.pop(), 1};
            if (queueDog.isEmpty()) return new int[]{queueCat.pop(), 0};
            return queueCat.peek() > queueDog.peek() ? new int[]{queueDog.pop(), 1} : new int[]{queueCat.pop(), 0};
        }

        public int[] dequeueDog() {
            if (queueDog.isEmpty()) return new int[]{-1, -1};
            return new int[]{queueDog.pop(), 1};
        }

        public int[] dequeueCat() {
            if (queueCat.isEmpty()) return new int[]{-1, -1};
            return new int[]{queueCat.pop(), 0};
        }
    }

    /**
     * 面试题 04.01. 节点间通路
     * <p>
     * 节点间通路。给定有向图，设计一个算法，找出两个节点之间是否存在一条路径。
     * <p>
     * <a href="https://leetcode.cn/problems/route-between-nodes-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/route-between-nodes-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        Set<Integer> nodes = new HashSet<>();
        nodes.add(start);
        while (true) {
            boolean flag = true;
            for (int[] e : graph) {
                if (nodes.contains(e[0]) && !nodes.contains(e[1])) {
                    flag = false;
                    nodes.add(e[1]);
                }
            }
            if (flag) break;
        }
        return nodes.contains(target);
    }

    /**
     * 面试题 04.02. 最小高度树
     * <p>
     * 给定一个有序整数数组，元素各不相同且按升序排列，编写一个算法，创建一棵高度最小的二叉搜索树。
     * <p>
     * <a href="https://leetcode.cn/problems/minimum-height-tree-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/minimum-height-tree-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return buildBST(nums, 0, nums.length);
    }

    private TreeNode buildBST(int[] nums, int l, int r) {
        if (l >= r) return null;
        int mid = (l + r) / 2;
        int t = nums[mid];
        TreeNode root = new TreeNode(t);
        root.left = buildBST(nums, l, mid);
        root.right = buildBST(nums, mid + 1, r);
        return root;
    }

    /**
     * 面试题 04.03. 特定深度节点链表
     * <p>
     * 给定一棵二叉树，设计一个算法，创建含有某一深度上所有节点的链表（比如，若一棵树的深度为 D，则会创建出 D 个链表）。返回一个包含所有深度的链表的数组。
     * <p>
     * <a href="https://leetcode.cn/problems/list-of-depth-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/list-of-depth-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public ListNode[] listOfDepth(TreeNode tree) {
        LinkedList<List<TreeNode>> result = new LinkedList<>();
        result.add(Arrays.asList(tree));
        boolean flag = true;
        while (flag) {
            List<TreeNode> nodes = result.getLast();
            List<TreeNode> newLayer = new ArrayList<>();
            for (TreeNode node : nodes) {
                if (node.left != null) newLayer.add(node.left);
                if (node.right != null) newLayer.add(node.right);
            }
            if (newLayer.isEmpty()) flag = false;
            else result.add(newLayer);
        }
        ListNode[] t = new ListNode[result.size()];
        int i = 0;
        for (List<TreeNode> nodes : result) {
            ListNode pre = new ListNode(-1);
            ListNode current = new ListNode(-1);
            pre.next = current;
            for (TreeNode node : nodes) {
                current.next = new ListNode(node.val);
                current = current.next;
            }
            t[i] = pre.next.next;
            i++;
        }
        return t;
    }

    /**
     * 面试题 04.04. 检查平衡性
     * <p>
     * 实现一个函数，检查二叉树是否平衡。在这个问题中，平衡树的定义如下：任意一个节点，其两棵子树的高度差不超过 1。
     * <p>
     * <a href="https://leetcode.cn/problems/check-balance-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/check-balance-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public boolean isBalanced(TreeNode root) {
        Map<TreeNode, Integer> h = new HashMap<>();
        h.put(null, 0);
        calH(root, h);
        return handleIsBalanced(root, h);
    }

    private boolean handleIsBalanced(TreeNode root, Map<TreeNode, Integer> h) {
        if (root == null) return true;
        int delta = h.get(root.left) - h.get(root.right);
        return (delta <= 1 && delta >= -1) && handleIsBalanced(root.left, h) && handleIsBalanced(root.right, h);
    }

    private int calH(TreeNode root, Map<TreeNode, Integer> h) {
        if (root == null) return 0;
        int height = 1 + Math.max(calH(root.left, h), calH(root.right, h));
        h.put(root, height);
        return height;
    }

    /**
     * 面试题 04.05. 合法二叉搜索树
     * <p>
     * 实现一个函数，检查一棵二叉树是否为二叉搜索树。
     * <p>
     * <a href="https://leetcode.cn/problems/legal-binary-search-tree-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/legal-binary-search-tree-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public boolean isValidBST(TreeNode node) {
        // 中序递增
        Stack<TreeNode> stack = new Stack<>();
        TreeNode head = node;
        long now = Long.MIN_VALUE;
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                if (head.val < now) return false;
                now = head.val;
                head = head.right;
            }
        }
        return true;
    }

    /**
     * 面试题 04.06. 后继者
     * <p>
     * 设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
     * <p>
     * <a href="https://leetcode.cn/problems/successor-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/successor-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode head = root;
        boolean flag = false;
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                if (flag) return head;
                if (head == p) flag = true;
                head = head.right;
            }
        }
        return null;
    }

    /**
     * 面试题 04.08. 首个共同祖先
     * <p>
     * 设计并实现一个算法，找出二叉树中某两个节点的第一个共同祖先。不得将其他的节点存储在另外的数据结构中。注意：这不一定是二叉搜索树。
     * <p>
     * <a href="https://leetcode.cn/problems/first-common-ancestor-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/first-common-ancestor-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Stack<TreeNode> stack = new Stack<>();
        Map<TreeNode, String> map = new HashMap<>();
        stack.push(root);
        map.put(root, "");
        String pt = null, qt = null;
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            if (current == p) pt = map.get(current);
            if (current == q) qt = map.get(current);
            if (pt != null && qt != null) break;
            if (current.right != null) {
                stack.push(current.right);
                map.put(current.right, map.get(current) + "1");
            }
            if (current.left != null) {
                stack.push(current.left);
                map.put(current.left, map.get(current) + "0");
            }
        }
        TreeNode result = root;
        for (int i = 0; i < pt.length() && i < qt.length(); i++) {
            if (pt.charAt(i) != qt.charAt(i)) return result;
            if (pt.charAt(i) == '1') result = result.right;
            else result = result.left;
        }
        return result;
    }

    /**
     * 面试题 04.09. 二叉搜索树序列
     * <p>
     * 从左向右遍历一个数组，通过不断将其中的元素插入树中可以逐步地生成一棵二叉搜索树。
     * 给定一个由不同节点组成的二叉搜索树 root，输出所有可能生成此树的数组。
     * <p>
     * <a href="https://leetcode.cn/problems/bst-sequences-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/bst-sequences-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public List<List<Integer>> BSTSequences(TreeNode root) {
        if (root == null) return Collections.singletonList(new ArrayList<>());
        List<List<Integer>> l = BSTSequences(root.left);
        List<List<Integer>> r = BSTSequences(root.right);
        return mergeSequences(root.val, l, r);
    }

    private List<List<Integer>> mergeSequences(int val, List<List<Integer>> l, List<List<Integer>> r) {
        List<List<Integer>> res = new ArrayList<>();

        int m = l.get(0).size();
        int n = r.get(0).size();
        if (m == 0) {
            for (List<Integer> rt : r) {
                res.add(new ArrayList<Integer>() {{
                    add(val);
                    addAll(rt);
                }});
            }
            return res;
        }
        if (n == 0) {
            for (List<Integer> lt : l) {
                res.add(new ArrayList<Integer>() {{
                    add(val);
                    addAll(lt);
                }});
            }
            return res;
        }
        // val必须在前，l和r保持各自顺序任意合并
        List<LinkedList<IntegerHolder>> combine = combine(m + n, m);
        for (int i = 0; i < l.size() * r.size() * combine.size(); i++) {
            res.add(new ArrayList<Integer>() {{
                add(val);
            }});
        }
        int t = 0;
        for (List<Integer> lt : l) {
            for (List<Integer> rt : r) {
                for (LinkedList<IntegerHolder> choice : combine) {
                    List<Integer> thisOne = new ArrayList<>(m + n);
                    for (int i = 0; i < m + n; i++) {
                        thisOne.add(null);
                    }
                    int j = 0;
                    for (IntegerHolder i : choice) {
                        thisOne.set(i.getV(), lt.get(j));
                        j++;
                    }
                    j = 0;
                    for (int i = 0; i < thisOne.size(); i++) {
                        if (thisOne.get(i) == null) {
                            thisOne.set(i, rt.get(j));
                            j++;
                        }
                    }
                    res.get(t).addAll(thisOne);
                    t++;
                }
            }
        }
        return res;
    }

    /**
     * 返回0~m-1所有可能n个下标集合 (m>=n)
     * <p>
     * 例如: C(4,3) ==> [[0,1,2],[0,2,3],[0,1,3],[1,2,3]]
     * <p>
     * C(m,n) = C(m-1,n-1)+C(m-2,n-1)+...+C(n-1,n-1)
     */
    private static List<LinkedList<IntegerHolder>> combine(int m, int n) {
        if (n == 0) {
            return Collections.singletonList(new LinkedList<>());
        }
        if (m == n) {
            LinkedList<IntegerHolder> t = new LinkedList<>();
            for (int i = 0; i < m; i++) t.add(new IntegerHolder(i));
            return Collections.singletonList(t);
        }
        List<LinkedList<IntegerHolder>> res = new ArrayList<>();
        for (int i = m - 1; i >= n - 1; i--) {
            List<LinkedList<IntegerHolder>> x = combine(i, n - 1);
            for (LinkedList<IntegerHolder> t : x) {
                for (IntegerHolder y : t) y.inc(m - i);
                t.addFirst(new IntegerHolder(m - 1 - i));
            }
            res.addAll(x);
        }
        return res;
    }

    public static class IntegerHolder {
        private int v;

        public IntegerHolder(int v) {
            this.v = v;
        }

        public int getV() {
            return v;
        }

        public void inc(int delta) {
            v += delta;
        }

        @Override
        public String toString() {
            return String.valueOf(v);
        }
    }

    /**
     * 面试题 04.10. 检查子树
     * <p>
     * 检查子树。你有两棵非常大的二叉树：T1，有几万个节点；T2，有几万个节点。设计一个算法，判断 T2 是否为 T1 的子树。
     * <p>
     * <a href="https://leetcode.cn/problems/check-subtree-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/check-subtree-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public boolean checkSubTree(TreeNode t1, TreeNode t2) {
        if (t1 == null) return t2 == null;
        return checkEqualsTree(t1, t2) || checkSubTree(t1.left, t2) || checkSubTree(t1.right, t2);
    }

    private boolean checkEqualsTree(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        return t1 != null && t2 != null && t1.val == t2.val && checkEqualsTree(t1.left, t2.left) && checkEqualsTree(t1.right, t2.right);
    }

    /**
     * 面试题 04.12. 求和路径
     * <p>
     * 给定一棵二叉树，其中每个节点都含有一个整数数值(该值或正或负)。设计一个算法，打印节点数值总和等于某个给定值的所有路径的数量。注意，路径不一定非得从二叉树的根节点或叶节点开始或结束，但是其方向必须向下(只能从父节点指向子节点方向)。
     * <p>
     * <a href="https://leetcode.cn/problems/paths-with-sum-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/paths-with-sum-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        return handleSum(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    private int handleSum(TreeNode root, int target) {
        if (root == null) return 0;
        return (root.val == target ? 1 : 0) + handleSum(root.left, target - root.val) + handleSum(root.right, target - root.val);
    }

    /**
     * 面试题 05.01. 插入
     * <p>
     * 给定两个整型数字 N 与 M，以及表示比特位置的 i 与 j（i <= j，且从 0 位开始计算）。
     * <p>
     * 编写一种方法，使 M 对应的二进制数字插入 N 对应的二进制数字的第 i ~ j 位区域，不足之处用 0 补齐
     * <p>
     * <a href="https://leetcode.cn/problems/insert-into-bits-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/insert-into-bits-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     * <p>
     */
    public int insertBits(int n, int m, int i, int j) {
        int a = 1 << (j - i + 1);
        int b = a - 1;
        int c = b << i;
        int d = ~c;
        int e = n & d;
        int f = m << i;
        return e | f;
    }

    /**
     * 面试题 05.02. 二进制数转字符串
     * <p>
     * 二进制数转字符串。给定一个介于0和1之间的实数（如0.72），类型为double，打印它的二进制表达式。如果该数字无法精确地用32位以内的二进制表示，则打印“ERROR”。
     * <p>
     * <a href="https://leetcode.cn/problems/binary-number-to-string-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/binary-number-to-string-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public String printBin(double num) {
        StringBuilder sb = new StringBuilder("0.");
        for (int i = 0; i < 30; i++) {
            num = num * 2;
            if (num > 1) {
                sb.append("1");
                num -= 1;
            } else if (num < 1) {
                sb.append("0");
            } else {
                sb.append("1");
                return sb.toString();
            }
        }
        return "ERROR";
    }

    /**
     * 面试题 05.03. 翻转数位
     * <p>
     * 给定一个32位整数 num，你可以将一个数位从0变为1。请编写一个程序，找出你能够获得的最长的一串1的长度。
     * <p>
     * <a href="https://leetcode.cn/problems/reverse-bits-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/reverse-bits-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public int reverseBits(int num) {
        int max = Integer.MIN_VALUE;
        int flag = -1;
        int i = 0, j = 0;
        while (i < 32) {
            if ((num & (1 << i)) == 0) {
                if (flag >= 0) {
                    max = Math.max(max, i - j);
                    j = flag + 1;
                }
                flag = i;
            }
            i++;
        }
        return Math.max(max, i - j);
    }

    /**
     * 面试题 05.04. 下一个数
     * <p>
     * 下一个数。给定一个正整数，找出与其二进制表达式中1的个数相同且大小最接近的那两个数（一个略大，一个略小）。
     * <p>
     * <a href="https://leetcode.cn/problems/closed-number-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/closed-number-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public int[] findClosedNumbers(int num) {
        // 找到最右边左边是0的1，将其左移1位，再把它右边连续的1移动到最低位置
        if (num == 0) return new int[]{-1, -1};
        if (num == Integer.MAX_VALUE) return new int[]{-1, -1};
        int i = 0, k = 0;
        while (i < 30) {
            int j = i + 1;
            if ((num & (1 << i)) != 0) {
                if ((num & (1 << j)) == 0) break;
                k += 1;
            }
            i++;
        }
        int bigger = num | (1 << (i + 1));
        bigger &= (-(1 << (i + 1)));
        bigger += ((1 << k) - 1);
        // 找到最右边右边是0的1，将其右移1位，并且把它右边所有连续的1移到最高位
        int smaller = -1;
        i = 1;
        while (i < 31) {
            int j = i - 1;
            if (((num & (1 << i)) != 0) && ((num & (1 << j)) == 0)) break;
            i++;
        }
        if (i != 31) {
            smaller = num;
            smaller |= (1 << (i - 1));
            smaller ^= (1 << i);
            k = 0;
            while (k < 31 && ((num & (1 << k)) != 0)) k++;
            if (k > 0) {
                int t = (1 << k) - 1;
                smaller -= t;
                smaller += t << (i - 1 - k);
            }
        }

        return new int[]{bigger, smaller};
    }

    /**
     * 面试题 05.06. 整数转换
     * <p>
     * 整数转换。编写一个函数，确定需要改变几个位才能将整数A转成整数B。
     * <p>
     * <a href="https://leetcode.cn/problems/convert-integer-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/convert-integer-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public int convertInteger(int a, int b) {
        return Integer.bitCount(a ^ b);
    }

    /**
     * 面试题 05.07. 配对交换
     * <p>
     * 配对交换。编写程序，交换某个整数的奇数位和偶数位，尽量使用较少的指令（也就是说，位0与位1交换，位2与位3交换，以此类推）。
     * <p>
     * <a href="https://leetcode.cn/problems/exchange-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/exchange-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public int exchangeBits(int num) {
        // 偶数位左移+奇数位右移
        int a = 0b01010101010101010101010101010101 & num;
        int b = 0b10101010101010101010101010101010 & num;
        return (a << 1) + (b >> 1);
    }

    /**
     * 面试题 05.08. 绘制直线
     * <p>
     * 已知一个由像素点组成的单色屏幕，每行均有 w 个像素点，所有像素点初始为 0，左上角位置为 (0,0)。
     * <p>
     * 现将每行的像素点按照「每 32 个像素点」为一组存放在一个 int 中，再依次存入长度为 length 的一维数组中。
     * <p>
     * 我们将在屏幕上绘制一条从点 (x1,y) 到点 (x2,y) 的直线（即像素点修改为 1），请返回绘制过后的数组。
     * <p>
     * <a href="https://leetcode.cn/problems/draw-line-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/draw-line-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public int[] drawLine(int length, int w, int x1, int x2, int y) {
        int t = w / 32;
        int height = length / t;
        int[] result = new int[t * height];
        for (int i = 0; i < y * t; i++) result[i] = 0;
        for (int i = (y + 1) * t; i < result.length; i++) result[i] = 0;
        boolean[] x = new boolean[w];
        for (int i = 0; i < w; i++) x[i] = i >= x1 && i <= x2;
        int j = y * t;
        int e = 0;
        for (int i = 0; i < w; i++) {
            int s = 31 - i % 32;
            if (x[i]) e += (1 << s);
            if (s == 0) {
                result[j + i / 32] = e;
                e = 0;
            }
        }
        return result;
    }

    /**
     * 面试题 08.01. 三步问题
     * <p>
     * 三步问题。有个小孩正在上楼梯，楼梯有n阶台阶，小孩一次可以上1阶、2阶或3阶。实现一种方法，计算小孩有多少种上楼梯的方式。结果可能很大，你需要对结果模1000000007。
     * <p>
     * <a href="https://leetcode.cn/problems/three-steps-problem-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/three-steps-problem-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public int waysToStep(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (n == 3) return 4;
        long a = 1, b = 2, c = 4, d = 0;
        for (int i = 4; i <= n; i++) {
            d = (a + b + c) % 1000000007L;
            a = b;
            b = c;
            c = d;
        }
        return (int) d;
    }

    /**
     * 面试题 08.02. 迷路的机器人
     * <p>
     * 设想有个机器人坐在一个网格的左上角，网格 r 行 c 列。机器人只能向下或向右移动，但不能走到一些被禁止的网格（有障碍物）。设计一种算法，寻找机器人从左上角移动到右下角的路径。
     * <p>
     * <a href="https://leetcode.cn/problems/robot-in-a-grid-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/robot-in-a-grid-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
        int r = obstacleGrid.length, c = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[r - 1][c - 1] == 1) return Collections.emptyList();
        // -1表示左边来的，1表示上边来的，0表示无法到达
        byte[][] dp = new byte[r][c];
        dp[0][0] = -1;
        for (int j = 1; j < c; j++) {
            if (obstacleGrid[0][j] == 1) dp[0][j] = 0;
            else dp[0][j] = dp[0][j - 1];
        }
        for (int i = 1; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (obstacleGrid[i][j] == 1) dp[i][j] = 0;
                else {
                    if (j == 0) {
                        if (dp[i - 1][j] != 0) dp[i][j] = 1;
                        else dp[i][j] = 0;
                    } else {
                        if (dp[i][j - 1] != 0) dp[i][j] = -1;
                        else if (dp[i - 1][j] != 0) dp[i][j] = 1;
                        else dp[i][j] = 0;
                    }
                }
            }
        }
        if (dp[r - 1][c - 1] == 0) return Collections.emptyList();
        else {
            LinkedList<List<Integer>> result = new LinkedList<>();
            int i = r - 1, j = c - 1;
            while (i != 0 || j != 0) {
                result.addFirst(Arrays.asList(i, j));
                byte direction = dp[i][j];
                if (direction == 1) i -= 1;
                if (direction == -1) j -= 1;
            }
            result.addFirst(Arrays.asList(0, 0));
            return result;
        }
    }

    /**
     * 面试题 08.03. 魔术索引
     * <p>
     * 魔术索引。 在数组A[0...n-1]中，有所谓的魔术索引，满足条件A[i] = i。给定一个有序整数数组，编写一种方法找出魔术索引，若有的话，在数组A中找出一个魔术索引，如果没有，则返回-1。若有多个魔术索引，返回索引值最小的一个。
     * <p>
     * <a href="https://leetcode.cn/problems/magic-index-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/magic-index-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public int findMagicIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) if (nums[i] == i) return i;
        return -1;
    }

    /**
     * 面试题 08.04. 幂集
     * <p>
     * 幂集。编写一种方法，返回某集合的所有子集。集合中不包含重复的元素。
     * <p>
     * <a href="https://leetcode.cn/problems/power-set-lcci/">https://leetcode.cn/problems/power-set-lcci/</a>
     */
    public List<List<Integer>> subsets(int[] nums) {
        int t = nums.length;
        List<List<Integer>> list = new LinkedList<>();
        for (int i = 0; i < (1 << t); i++) {
            List<Integer> x = new ArrayList<>();
            for (int j = 0; j < t; j++) {
                if ((i & (1 << j)) != 0) {
                    x.add(nums[j]);
                }
            }
            list.add(x);
        }
        return list;
    }

    /**
     * 面试题 08.05. 递归乘法
     * <p>
     * 递归乘法。 写一个递归函数，不使用 * 运算符， 实现两个正整数的相乘。可以使用加号、减号、位移，但要吝啬一些。
     * <p>
     * <a href="https://leetcode.cn/problems/recursive-mulitply-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/recursive-mulitply-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public int multiply(int a, int b) {
        int result = 0;
        for (int i = 0; i < 32 && ((1 << i) <= b); i++) {
            if ((b & (1 << i)) != 0) {
                result += (a << i);
            }
        }
        return result;
    }

    /**
     * 面试题 08.06. 汉诺塔问题
     * <p>
     * 在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，盘子可以滑入任意一根柱子。一开始，所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只能放在更大的盘子上面)。移动圆盘时受到以下限制:
     * <p>
     * (1) 每次只能移动一个盘子;
     * <p>
     * (2) 盘子只能从柱子顶端滑出移到下一根柱子;
     * <p>
     * (3) 盘子只能叠在比它大的盘子上。
     * <p>
     * <a href="https://leetcode.cn/problems/hanota-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/hanota-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public void hanota(List<Integer> a, List<Integer> b, List<Integer> c) {
        move(new List[]{a, b, c}, a.size(), 0, 2);
    }

    private void move(List<Integer>[] set, int k, int from, int to) {
        if (k == 0) return;
        int other;
        if (from == 0 && to == 1) other = 2;
        else if (from == 0 && to == 2) other = 1;
        else if (from == 1 && to == 2) other = 0;
        else if (from == 1 && to == 0) other = 2;
        else if (from == 2 && to == 0) other = 1;
        else other = 0;
        move(set, k - 1, from, other);
        set[to].add(set[from].remove(set[from].size() - 1));
        move(set, k - 1, other, to);
    }

    /**
     * 面试题 08.07. 无重复字符串的排列组合
     * <p>
     * 面试题 08.08. 有重复字符串的排列组合
     * <p>
     * <a href="https://leetcode.cn/problems/permutation-i-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/permutation-i-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     * <p>
     * <a href="https://leetcode.cn/problems/permutation-ii-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/permutation-ii-lcci/?envType=featured-list&envId=xb9lfcw</a>
     * <p>
     * 字符串的排列组合，编写一种方法，计算某字符串的所有排列组合
     */
    public String[] permutation(String s) {
        char[] value = s.toCharArray();
        int[] t = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            t[i] = value[i] - 'a';
        }
        Arrays.sort(t);
        List<String> result = new ArrayList<>();
        do {
            result.add(genString(t));
        } while (nextPermutation(t));
        String[] k = new String[result.size()];
        result.toArray(k);
        return k;
    }

    private String genString(int[] t) {
        StringBuilder sb = new StringBuilder();
        for (int i : t) {
            sb.append((char) (i + 'a'));
        }
        return sb.toString();
    }

    private boolean nextPermutation(int[] nums) {
        if (nums.length == 1) return true;
        //1. 从后向前，找到破坏升序的第一个数字（等于不是破坏）
        int index = nums.length - 2;
        for (; index >= 0; index--) {
            int next = index + 1;
            if (nums[index] < nums[next]) break;
        }
        if (index < 0) {
            // 如果没有破坏反向升序的，说明原序列是完全降序，revert
            return false;
        } else {
            // 如果有破坏升序的 index， 找到后面最小的比它大的，交换，然后revert
            // x x x 5 9 8 6 5 4 3 2 1 ==> x x x 6 9 8 5 5 4 3 2 1 ==> x x x 6 1 2 3 4 5 5 8 9
            int i = index + 1;
            while (i < nums.length && nums[i] > nums[index]) i++;
            i = i - 1;
            swap(nums, index, i);
            revert(nums, index + 1, nums.length - 1);
            return true;
        }
    }

    private void revert(int[] array, int i, int j) {
        int left = i, right = j;
        while (left < right && array[left] != array[right]) {
            swap(array, left, right);
            left++;
            right--;
        }
    }

    private void swap(int[] array, int i, int j) {
        if (i == j) return;
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * 面试题 08.09. 括号
     * <p>
     * 括号。设计一种算法，打印n对括号的所有合法的（例如，开闭一一对应）组合。
     * <p>
     * <a href="https://leetcode.cn/problems/bracket-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/bracket-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public List<String> generateParenthesis(int n) {
        Set<String> current = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (i == 0) current.add("()");
            else {
                Set<String> next = new HashSet<>();
                for (String last : current) {
                    last = "(" + last;
                    for (int j = 0; j < last.length() && last.charAt(j) == '('; j++) {
                        next.add(last.substring(0, j + 1) + ")" + last.substring(j + 1));
                    }
                }
                current = next;
            }
        }
        return new ArrayList<>(current);
    }

    /**
     * 面试题 08.10. 颜色填充
     * <p>
     * 编写函数，实现许多图片编辑软件都支持的「颜色填充」功能。
     * <p>
     * 待填充的图像用二维数组 image 表示，元素为初始颜色值。初始坐标点的行坐标为 sr 列坐标为 sc。需要填充的新颜色为 newColor 。
     * <p>
     * 「周围区域」是指颜色相同且在上、下、左、右四个方向上存在相连情况的若干元素。
     * <p>
     * 请用新颜色填充初始坐标点的周围区域，并返回填充后的图像。
     * <p>
     * <a href="https://leetcode.cn/problems/color-fill-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/color-fill-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        boolean[][] done = new boolean[image.length][image[0].length];
        doFill(image, sr, sc, newColor, image[sr][sc], done);
        return image;
    }

    private void doFill(int[][] image, int sr, int sc, int newColor, int originColor, boolean[][] done) {
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length || image[sr][sc] != originColor || done[sr][sc])
            return;
        image[sr][sc] = newColor;
        done[sr][sc] = true;
        doFill(image, sr + 1, sc, newColor, originColor, done);
        doFill(image, sr - 1, sc, newColor, originColor, done);
        doFill(image, sr, sc + 1, newColor, originColor, done);
        doFill(image, sr, sc - 1, newColor, originColor, done);
    }

    /**
     * 面试题 08.11. 硬币
     * <p>
     * 硬币。给定数量不限的硬币，币值为25分、10分、5分和1分，编写代码计算n分有几种表示法。(结果可能会很大，你需要将结果模上1000000007)
     * <p>
     * <a href="https://leetcode.cn/problems/coin-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/coin-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public int waysToChange(int n) {
        int[] dp1 = new int[1 + n];
        for (int i = 0; i <= n; i++) dp1[i] = 1;
        int[] dp5 = new int[1 + n];
        for (int i = 0; i <= n; i++) {
            int a = i >= 5 ? dp5[i - 5] : 0;
            dp5[i] = (dp1[i] + a) % 1000000007;
        }
        int[] dp10 = new int[1 + n];
        for (int i = 0; i <= n; i++) {
            int a = i >= 10 ? dp10[i - 10] : 0;
            dp10[i] = (dp5[i] + a) % 1000000007;
        }
        int[] dp25 = new int[1 + n];
        for (int i = 0; i <= n; i++) {
            int a = i >= 25 ? dp25[i - 25] : 0;
            dp25[i] = (dp10[i] + a) % 1000000007;
        }
        return dp25[n];
    }

    /**
     * 面试题 08.12. 八皇后
     * <p>
     * 设计一种算法，打印 N 皇后在 N × N 棋盘上的各种摆法，其中每个皇后都不同行、不同列，也不在对角线上。这里的“对角线”指的是所有的对角线，不只是平分整个棋盘的那两条对角线。
     * <p>
     * <a href="https://leetcode.cn/problems/eight-queens-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/eight-queens-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public List<List<String>> solveNQueens(int n) {
        int[] column = new int[1 + n];// column[i]∈[0,1]表示第i列被占用 1...n
        int[] queen = new int[1 + n]; // queen[i]∈(1,2,3...5)表示第i行第几列结果
        // rup[i+j-1]=1表示(i,j)所在的反斜线被占用 反斜线上i+j是固定值, 取值范围是2到2*n
        int[] rup = new int[2 * n]; // 最终范围是1到2n-1
        // lup[i-j+n]=1表示(i,j)所在的正斜线被占用 正斜线上i-j是固定值, 取值范围是1-n到n-1
        int[] lup = new int[2 * n]; // 最终范围是1到2n-1
        List<List<String>> result = new ArrayList<>();
        dfs(1, n, result, column, rup, lup, queen);
        return result;
    }

    private void dfs(int i, int n, List<List<String>> result, int[] column, int[] rup, int[] lup, int[] queen) {
        if (i > n) result.add(getQueenResult(queen, n));
        else {
            for (int j = 1; j <= n; j++) {
                if ((column[j] == 0) && (rup[i + j - 1] == 0) && lup[i - j + n] == 0) {
                    queen[i] = j;
                    column[j] = rup[i + j - 1] = lup[i - j + n] = 1;
                    dfs(i + 1, n, result, column, rup, lup, queen);
                    column[j] = rup[i + j - 1] = lup[i - j + n] = 0;
                }
            }
        }
    }

    private List<String> getQueenResult(int[] queen, int n) {
        List<String> t = new ArrayList<>();
        for (int y = 1; y <= n; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 1; x <= n; x++) {
                if (queen[y] == x) sb.append("Q");
                else sb.append(".");
            }
            t.add(sb.toString());
        }
        return t;
    }

    /**
     * 面试题 08.13. 堆箱子
     * <p>
     * 堆箱子。给你一堆n个箱子，箱子宽wi、深di、高hi。箱子不能翻转，将箱子堆起来时，下面箱子的宽度、高度和深度必须大于上面的箱子。实现一种方法，搭出最高的一堆箱子。箱堆的高度为每个箱子高度的总和。
     * <p>
     * 第i个箱子用[wi, di, hi]表示
     * <p>
     * <a href="https://leetcode.cn/problems/pile-box-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/pile-box-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public int pileBox(int[][] box) {
        /*
          构建图 v1->v2表示 v1.w>v2.w && v1.d>v2.d && v1.h>v2.h
          最终求最长路径
         */
        List<Box> boxes = Arrays.stream(box).map(Box::new).collect(Collectors.toList());
        for (int i = 0; i < boxes.size(); i++) {
            for (int j = i + 1; j < boxes.size(); j++) {
                boxes.get(i).compareAndAdd(boxes.get(j));
            }
        }
        int result = 0;
        for (Box b : boxes) {
            result = Math.max(b.getMax(), result);
        }

        return result;
    }

    private static class Box implements Comparable<Box> {
        int w, d, h;
        private Integer maxH;
        Set<Box> children = new HashSet<>();

        Box(int[] box) {
            w = box[0];
            d = box[1];
            h = box[2];
        }

        void compareAndAdd(Box another) {
            int t = this.compareTo(another);
            if (t > 0) this.children.add(another);
            if (t < 0) another.children.add(this);
        }

        int getMax() {
            if (maxH != null) return maxH;
            int max = 0;
            for (Box b : children) {
                max = Math.max(max, b.getMax());
            }
            maxH = max + h;
            return maxH;
        }

        @Override
        public int compareTo(Interview.Box another) {
            if (w > another.w && d > another.d && h > another.h) return 1;
            if (w < another.w && d < another.d && h < another.h) return -1;
            return 0;
        }
    }

    /**
     * 面试题08.14. 布尔运算
     * <p>
     * 给定一个布尔表达式和一个期望的不二结果result，布尔表达式由0、1、&、|和^符号组成。实现一个函数，算出有几种可使该表达式得出result值的括号方法
     * <p>
     * <a href="https://leetcode.cn/problems/boolean-evaluation-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/boolean-evaluation-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public int countEval(String s, int result) {
        char[] value = s.toCharArray();
        Integer[][][] dp = new Integer[2][s.length()][s.length()];
        return doCount(value, 0, value.length - 1, result, dp);
    }

    private int doCount(char[] value, int left, int right, int result, Integer[][][] dp) {
        if (dp[result][left][right] != null) return dp[result][left][right];
        if (left == right) {
            int x = value[left] - '0' == result ? 1 : 0;
            dp[result][left][right] = x;
            return x;
        }
        int sum = 0;
        for (int i = left + 1; i < right; i += 2) {
            char op = value[i];
            if (op == '|') {
                if (result == 0) {
                    sum += doCount(value, left, i - 1, 0, dp) * doCount(value, i + 1, right, 0, dp);

                } else {
                    int l0 = doCount(value, left, i - 1, 0, dp);
                    int l1 = doCount(value, left, i - 1, 1, dp);
                    int r0 = doCount(value, i + 1, right, 0, dp);
                    int r1 = doCount(value, i + 1, right, 1, dp);
                    sum += l0 * r1 + l1 * r0 + l1 * r1;
                }
            } else if (op == '&') {
                if (result == 0) {
                    int l0 = doCount(value, left, i - 1, 0, dp);
                    int l1 = doCount(value, left, i - 1, 1, dp);
                    int r0 = doCount(value, i + 1, right, 0, dp);
                    int r1 = doCount(value, i + 1, right, 1, dp);
                    sum += l0 * r1 + l1 * r0 + l0 * r0;
                } else {
                    sum += doCount(value, left, i - 1, 1, dp) * doCount(value, i + 1, right, 1, dp);
                }
            } else {
                int l0 = doCount(value, left, i - 1, 0, dp);
                int l1 = doCount(value, left, i - 1, 1, dp);
                int r0 = doCount(value, i + 1, right, 0, dp);
                int r1 = doCount(value, i + 1, right, 1, dp);
                if (result == 0) {
                    sum += l0 * r0 + l1 * r1;
                } else {
                    sum += l0 * r1 + l1 * r0;
                }
            }
        }
        dp[result][left][right] = sum;
        return sum;
    }

    /**
     * 面试题 10.01. 合并排序的数组
     * <p>
     * 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
     * <p>
     * <a href="https://leetcode.cn/problems/sorted-merge-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/sorted-merge-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public void merge(int[] a, int m, int[] b, int n) {
        int[] x = new int[m + n];
        int ai = 0, bi = 0;
        for (int i = 0; i < m + n; i++) {
            if (ai >= m) {
                System.arraycopy(b, bi, x, i, n - bi);
                break;
            }
            if (bi >= n) {
                System.arraycopy(a, ai, x, i, m - ai);
                break;
            }
            if (a[ai] < b[bi]) {
                x[i] = a[ai];
                ai++;
            } else {
                x[i] = b[bi];
                bi++;
            }
        }
        System.arraycopy(x, 0, a, 0, m + n);
    }

    /**
     * 面试题 10.02. 变位词组
     * <p>
     * 编写一种方法，对字符串数组进行排序，将所有变位词组合在一起。变位词是指字母相同，但排列不同的字符串。
     * <p>
     * <a href="https://leetcode.cn/problems/group-anagrams-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/group-anagrams-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] array = s.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            if (map.containsKey(key)) map.get(key).add(s);
            else map.put(key, new ArrayList<String>() {{
                add(s);
            }});
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 面试题 10.03. 搜索旋转数组
     * <p>
     * 搜索旋转数组。给定一个排序后的数组，包含n个整数，但这个数组已被旋转过很多次了，次数不详。请编写代码找出数组中的某个元素，假设数组元素原先是按升序排列的。若有多个相同元素，返回索引值最小的一个。
     * <p>
     * <a href="https://leetcode.cn/problems/search-rotate-array-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/search-rotate-array-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public int search(int[] arr, int target) {
        // --------------x-------
        if (target == arr[0]) return 0;
        else {
            int small = minIndex(arr); // 最小值下标
            if (target > arr[0]) {
                int t = Arrays.binarySearch(arr, 0, small, target);
                if (t < 0) return -1;
                // 前半部分
                return searchFirst(arr, 0, small - 1, target);
            } else {
                // 后半部分
                int t = Arrays.binarySearch(arr, small, arr.length, target);
                if (t < 0) return -1;
                return searchFirst(arr, small, arr.length - 1, target);
            }
        }
    }

    private int minIndex(int[] numbers) {
        int low = 0, high = numbers.length - 1;
        while (low < high) {
            if (numbers[low] < numbers[high]) return high + 1;
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
        return low;
    }

    private int searchFirst(int[] nums, int i, int j, int target) {
        if (i + 1 == j) return nums[i] == target ? i : j;
        if (nums[i] == target) return i;
        int mid = (i + j) / 2;
        if (nums[mid] < target) return searchFirst(nums, mid + 1, j, target);
        else return searchFirst(nums, i, mid, target);
    }

    /**
     * 面试题 10.05. 稀疏数组搜索
     * <p>
     * 稀疏数组搜索。有个排好序的字符串数组，其中散布着一些空字符串，编写一种方法，找出给定字符串的位置。
     * <p>
     * <a href="https://leetcode.cn/problems/sparse-array-search-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/sparse-array-search-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public int findString(String[] words, String s) {
        int left = 0, right = words.length - 1;
        while (left <= right) {
            while (words[left].length() == 0) left++;
            while (words[right].length() == 0) right--;
            if (left == right) return words[left].equals(s) ? left : -1;
            if (left > right) return -1;
            int mid = (left + right) / 2;
            if (words[mid].length() == 0) {
                int midL = mid, midR = mid;
                while (words[midL].length() == 0) midL--;
                while (words[midR].length() == 0) midR++;
                int c1 = s.compareTo(words[midL]);
                int c2 = s.compareTo(words[midR]);
                if (c1 < 0) {
                    right = midL - 1;
                } else if (c1 == 0) {
                    return midL;
                } else if (c2 < 0) {
                    left = midL + 1;
                    right = midR - 1;
                } else if (c2 == 0) {
                    return midR;
                } else {
                    left = midR + 1;
                }
            }
        }
        return -1;
    }


    /**
     * 面试题 10.09. 排序矩阵查找
     * <p>
     * 给定M×N矩阵，每一行、每一列都按升序排列，请编写代码找出某元素。
     * <p>
     * <a href="https://leetcode.cn/problems/sorted-matrix-search-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/sorted-matrix-search-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length, n = matrix[0].length;
        // 先确定在哪几列
        int rightColumn = Arrays.binarySearch(matrix[0], target);
        if (rightColumn >= 0) return true;
        rightColumn = -(rightColumn + 1); // target的j值小于rightColumn
        int leftColumn = Arrays.binarySearch(matrix[m - 1], target);
        if (leftColumn >= 0) return true;
        leftColumn = -(leftColumn + 1); // target的j值大于等于leftColumn

        // 每一列搜索，并且搜索的上限递减
        int up = m;
        for (int j = leftColumn; j < rightColumn; j++) {
            int t = binarySearchColumn(matrix, j, 0, up, target);
            if (t > 0) return true;
            up = -(t + 1);
        }
        return false;
    }

    // 某个列中搜索
    private int binarySearchColumn(int[][] matrix, int column, int row1, int row2, int target) {
        int low = row1;
        int high = row2 - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = matrix[mid][column];

            if (midVal < target) low = mid + 1;
            else if (midVal > target) high = mid - 1;
            else return mid;
        }
        return -(low + 1);
    }

    /**
     * 面试题 10.10. 数字流的秩
     * <p>
     * 假设你正在读取一串整数。每隔一段时间，你希望能找出数字 x 的秩(小于或等于 x 的值的个数)。请实现数据结构和算法来支持这些操作，也就是说：
     * <p>
     * - 实现 track(int x)方法，每读入一个数字都会调用该方法；
     * <p>
     * - 实现 getRankOfNumber(int x) 方法，返回小于或等于 x 的值的个数。
     * <p>
     * <a href="https://leetcode.cn/problems/rank-from-stream-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/rank-from-stream-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public static class StreamRank {

        private final TreeMap<Integer, Integer> count;

        public StreamRank() {
            count = new TreeMap<>();
        }

        public void track(int x) {
            count.put(x, count.getOrDefault(x, 0) + 1);
        }

        public int getRankOfNumber(int x) {
            Map<Integer, Integer> head = count.headMap(x, true);
            return head.values().stream().reduce(Integer::sum).orElse(0);
        }
    }

    /**
     * 面试题 10.11. 峰与谷
     * <p>
     * 在一个整数数组中，“峰”是大于或等于相邻整数的元素，相应地，“谷”是小于或等于相邻整数的元素。例如，在数组{5, 8, 4, 2, 3, 4, 6}中，{8, 6}是峰， {5, 2}是谷。现在给定一个整数数组，将该数组按峰与谷的交替顺序排序。
     * <p>
     * <a href="https://leetcode.cn/problems/peaks-and-valleys-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/peaks-and-valleys-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int[] n = new int[nums.length];
        System.arraycopy(nums, 0, n, 0, nums.length);
        int last = nums.length - 1;
        int first = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                nums[i] = n[first];
                first++;
            } else {
                nums[i] = n[last];
                last--;
            }
        }

    }

    /**
     * 面试题 16.01. 交换数字
     * <p>
     * 编写一个函数，不用临时变量，直接交换numbers = [a, b]中a与b的值。
     * <p>
     * <a href="https://leetcode.cn/problems/swap-numbers-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/swap-numbers-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public int[] swapNumbers(int[] numbers) {
        numbers[0] = numbers[0] ^ numbers[1];
        numbers[1] = numbers[0] ^ numbers[1];
        numbers[0] = numbers[0] ^ numbers[1];
        return numbers;
    }

    /**
     * 面试题 16.02. 单词频率
     * <p>
     * 设计一个方法，找出任意指定单词在一本书中的出现频率。
     * <p>
     * <a href="https://leetcode.cn/problems/words-frequency-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/words-frequency-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public static class WordsFrequency {
        private final Map<String, Integer> map = new HashMap<>();

        public WordsFrequency(String[] book) {
            for (String word : book) {
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
        }

        public int get(String word) {
            return map.getOrDefault(word, 0);
        }
    }

    /**
     * 面试题 16.03. 交点
     * <p>
     * 给定两条线段（表示为起点start = {X1, Y1}和终点end = {X2, Y2}），如果它们有交点，请计算其交点，没有交点则返回空值。
     * <p>
     * <a href="https://leetcode.cn/problems/intersection-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/intersection-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public double[] intersection(int[] start1, int[] end1, int[] start2, int[] end2) {
        int x11 = start1[0], y11 = start1[1];
        int x12 = end1[0], y12 = end1[1];
        int x21 = start2[0], y21 = start2[1];
        int x22 = end2[0], y22 = end2[1];
        int deltaX1 = x12 - x11, deltaY1 = y12 - y11;
        int deltaX2 = x22 - x21, deltaY2 = y22 - y21;
        int temp = deltaY1 * deltaX2 * x11 + y21 * deltaX1 * deltaX2 - y11 * deltaX1 * deltaX2 - x21 * deltaY2 * deltaX1;
        int k = deltaY1 * deltaX2 - deltaY2 * deltaX1;
        if (k == 0) {
            if (temp == 0) {
                // 如果重叠的话需要返回x最小值（x也相同时返回y最小值）
                int x1 = Math.min(x11, x12), x2 = Math.min(x21, x22);
                if (x1 == x2) {
                    int y1 = Math.min(y11, y12), y2 = Math.min(y21, y22);
                    if (within(y1, y21, y22)) {
                        return new double[]{x1, y1}; // y1在y21和y22中间
                    }
                    if (within(y2, y11, y12)) {
                        return new double[]{x2, y2}; // y2在y11和y12中间
                    }
                } else if (x1 < x2) {
                    if (within(x2, x11, x12)) {
                        // x2在x11和x12中间
                        if (x21 < x22) return new double[]{x21, y21};
                        else return new double[]{x22, y22};
                    }
                } else {
                    if (within(x1, x21, x22)) {
                        // x1在x21和x22中间
                        if (x11 < x12) return new double[]{x11, y11};
                        else return new double[]{x21, y21};
                    }
                }
            }
            return new double[0];
        }
        double x = ((double) temp) / k;
        boolean withinX1 = within(x, x11, x12);
        boolean withinX2 = within(x, x21, x22);
        if (withinX1 && withinX2) {
            double y;
            if (deltaX1 == 0) {
                y = (double) y21 + ((double) deltaY2) / deltaX2 * (x - x21);
            } else {
                y = (double) y11 + ((double) deltaY1) / deltaX1 * (x - x11);
            }
            boolean withinY1 = within(y, y11, y12);
            boolean withinY2 = within(y, y21, y22);
            if (withinY1 && withinY2) return new double[]{x, y};
        }
        return new double[0];
    }

    private boolean within(double s, int s1, int s2) {
        return (s >= s1 && s <= s2) || (s <= s1 && s >= s2);
    }

    /**
     * 面试题 16.04. 井字游戏
     * <p>
     * 设计一个算法，判断玩家是否赢了井字游戏。输入是一个 N x N 的数组棋盘，由字符" "，"X"和"O"组成，其中字符" "代表一个空位。
     * <p>
     * 以下是井字游戏的规则：
     * <p>
     * 当有N个相同（且非空）的字符填充任何行、列或对角线时，游戏结束，对应该字符的玩家获胜。
     * 当所有位置非空时，也算为游戏结束。
     * 如果游戏结束，玩家不允许再放置字符。
     * 如果游戏存在获胜者，就返回该游戏的获胜者使用的字符（"X"或"O"）；如果游戏以平局结束，则返回 "Draw"；如果仍会有行动（游戏未结束），则返回 "Pending"。
     * <p>
     * <a href="https://leetcode.cn/problems/tic-tac-toe-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/tic-tac-toe-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public String tictactoe(String[] board) {
        int n = board.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append("O");
        for (int i = 0; i < n; i++) sb.append("X");
        String oWin = sb.substring(0, n);
        String xWin = sb.substring(n, n * 2);
        for (String s : board) {
            if (s.equals(oWin)) return "O";
            if (s.equals(xWin)) return "X";
        }

        boolean o = true, x = true;
        for (int i = 0; i < n; i++) {
            char t = board[i].charAt(i);
            if (t != 'X') x = false;
            if (t != 'O') o = false;
            if (!x && !o) break;
        }
        if (x) return "X";
        if (o) return "O";

        o = true;
        x = true;
        for (int i = 0; i < n; i++) {
            char t = board[i].charAt(n - 1 - i);
            if (t != 'X') x = false;
            if (t != 'O') o = false;
            if (!x && !o) break;
        }
        if (x) return "X";
        if (o) return "O";
        boolean canContinue = false;
        for (int i = 0; i < n; i++) {
            o = true;
            x = true;
            for (String s : board) {
                char t = s.charAt(i);
                if (t != 'X') x = false;
                if (t != 'O') o = false;
                if (t == ' ') canContinue = true;
                if (!x && !o) break;
            }
            if (x) return "X";
            if (o) return "O";
        }
        if (canContinue) return "Pending";
        return "Draw";
    }

    /**
     * 面试题 16.05. 阶乘尾数
     * <p>
     * 设计一个算法，算出 n 阶乘有多少个尾随零。
     * <p>
     * <a href="https://leetcode.cn/problems/factorial-zeros-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/factorial-zeros-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public int trailingZeroes(int n) {
        long t = 5;
        int result = 0;
        while (t <= n) {
            result += n / t;
            t *= 5;
        }
        return result;
    }

    /**
     * 面试题 16.06. 最小差
     * <p>
     * 给定两个整数数组a和b，计算具有最小差绝对值的一对数值（每个数组中取一个值），并返回该对数值的差
     * <p>
     * <a href="https://leetcode.cn/problems/smallest-difference-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/smallest-difference-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public int smallestDifference(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        int i = 0, j = 0;
        int result = Integer.MAX_VALUE;
        while (i < a.length && j < b.length) {
            int ai = a[i];
            int bj = b[j];
            if (ai == bj) return 0;
            if (ai < bj) {
                int c = bj - ai;
                if (c > 0) result = Math.min(result, c);
                i++;
            }
            if (ai > bj) {
                int c = ai - bj;
                if (c > 0) result = Math.min(result, c);
                j++;
            }
        }
        return result;
    }


    /**
     * 面试题 16.07. 最大数值
     * <p>
     * 编写一个方法，找出两个数字a和b中最大的那一个。不得使用if-else或其他比较运算符。
     * <p>
     * <a href="https://leetcode.cn/problems/maximum-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/maximum-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public int maximum(int a, int b) {
        return Math.max(a, b);
    }

    /**
     * 给定一个整数，打印该整数的英文描述。
     * <p>
     * 面试题 16.08. 整数的英语表示
     * <p>
     * <a href="https://leetcode.cn/problems/english-int-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/english-int-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public String numberToWords(int num) {
        String[] ones = {
                "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"
        };
        String[] teens = {
                "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
        };
        String[] tys = {"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        // 按照十亿/百万/千/百/十/个切分
        if (num == 0) return "Zero";
        if (num <= 10) return ones[num - 1];
        if (num < 20) return teens[num - 11];
        if (num < 100) {
            int p = num / 10;
            int q = num % 10;
            if (q == 0) return tys[p - 2];
            return tys[p - 2] + " " + ones[q - 1];
        }
        if (num < 1000) {
            int p = num / 100;
            int q = num % 100;
            if (q == 0) return ones[p - 1] + " Hundred";
            return ones[p - 1] + " Hundred " + numberToWords(q);
        }
        if (num < 1000000) {
            int p = num / 1000;
            int q = num % 1000;
            if (q == 0) return numberToWords(p) + " Thousand";
            return numberToWords(p) + " Thousand " + numberToWords(q);
        }
        if (num < 1000000000) {
            int p = num / 1000000;
            int q = num % 1000000;
            if (q == 0) return numberToWords(p) + " Million";
            return numberToWords(p) + " Million " + numberToWords(q);
        }
        int p = num / 1000000000;
        int q = num % 1000000000;
        if (q == 0) return numberToWords(p) + " Billion";
        return numberToWords(p) + " Billion " + numberToWords(q);
    }

    /**
     * 面试题 16.09. 运算
     * <p>
     * 请实现整数数字的乘法、减法和除法运算，运算结果均为整数数字，程序中只允许使用加法运算符和逻辑运算符，允许程序中出现正负常数，不允许使用位运算。
     * <p>
     * <a href="https://leetcode.cn/problems/operations-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/operations-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public static class Operations {
        public Operations() {
        }

        public int minus(int a, int b) {
            if (b == Integer.MIN_VALUE) {
                return a + Integer.MAX_VALUE + 1;
            }
            return a + (-b);
        }

        public int multiply(int a, int b) {
            if (b == 0 || a == 0) return 0;
            if (a == Integer.MIN_VALUE || b == Integer.MIN_VALUE) return Integer.MIN_VALUE;
            boolean neg = (a > 0 && b < 0) || (a < 0 && b > 0);
            a = a > 0 ? a : -a;
            b = b > 0 ? b : -b;
            int result = 0;
            for (int i = 0; i < b; i++) result += a;
            return neg ? -result : result;
        }

        public int divide(int a, int b) {
            if (a == 0) return 0;
            if (a == Integer.MIN_VALUE) return Integer.MIN_VALUE;
            boolean neg = (a > 0 && b < 0) || (a < 0 && b > 0);
            a = a > 0 ? a : -a;
            b = b < 0 ? b : -b;
            int p = -1;
            while (a >= 0) {
                a += b;
                p += 1;
            }
            return neg ? -p : p;
        }

    }

    /**
     * 面试题 16.10. 生存人数
     * <p>
     * 给定 N 个人的出生年份和死亡年份，第 i 个人的出生年份为 birth[i]，死亡年份为 death[i]，实现一个方法以计算生存人数最多的年份。
     * 如果有多个年份生存人数相同且均为最大值，输出其中最小的年份。
     * <p>
     * <a href="https://leetcode.cn/problems/living-people-lcci/?envType=featured-list&envId=xb9lfcwi">https://leetcode.cn/problems/living-people-lcci/?envType=featured-list&envId=xb9lfcwi</a>
     */
    public int maxAliveYear(int[] birth, int[] death) {
        int max = 0;
        int result = 1899;
        for (int year = 1900; year <= 2000; year++) {
            int current = 0;
            for (int i = 0; i < birth.length; i++) {
                if (birth[i] <= year && death[i] >= year) current++;
            }
            if (current > max) {
                max = current;
                result = year;
            }
        }
        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/diving-board-lcci/?envType=featured-list&envId=xb9lfcwi">面试题 16.11. 跳水板</a>
     * <p>
     * 你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，长度较长的木板长度为longer。你必须正好使用k块木板。编写一个方法，生成跳水板所有可能的长度。
     */
    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) return new int[0];
        if (shorter == longer) return new int[]{k * shorter};
        int[] result = new int[k + 1];
        for (int i = 0; i <= k; i++) {
            result[i] = i * longer + (k - i) * shorter;
        }
        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/bisect-squares-lcci/?envType=featured-list&envId=xb9lfcwi">面试题 16.13. 平分正方形</a>
     * <p>
     * 给定两个正方形及一个二维平面。请找出将这两个正方形分割成两半的一条直线。假设正方形顶边和底边与 x 轴平行。
     */
    public double[] cutSquares(int[] square1, int[] square2) {
        double x1 = square1[0], y1 = square1[1], l1 = square1[2];
        double x2 = square2[0], y2 = square2[1], l2 = square2[2];
        double c1x = x1 + l1 / 2, c1y = y1 + l1 / 2;
        double c2x = x2 + l2 / 2, c2y = y2 + l2 / 2;
        if (c1x == c2x) {
            double yMax = Math.max(y1 + l1, y2 + l2);
            double yMin = Math.min(y1, y2);
            return new double[]{c1x, yMin, c2x, yMax};
        }
        if (c1y == c2y) {
            double xMax = Math.max(x1 + l1, x2 + l2);
            double xMin = Math.min(x1, x2);
            return new double[]{xMin, c1y, xMax, c2y};
        }
        // 斜线，有八个交点，依次判断
        double yk = (c2y - c1y) / (c2x - c1x);
        double xk = (c2x - c1x) / (c2y - c1y);
        double[][] intersections = {
                {x1, yk * (x1 - c1x) + c1y},
                {x1 + l1, yk * (x1 + l1 - c1x) + c1y},
                {x2, yk * (x2 - c1x) + c1y},
                {x2 + l2, yk * (x2 + l2 - c1x) + c1y},
                {xk * (y1 - c1y) + c1x, y1},
                {xk * (y1 + l1 - c1y) + c1x, y1 + l1},
                {xk * (y2 - c1y) + c1x, y2},
                {xk * (y2 + l2 - c1y) + c1x, y2 + l2},
        };
        Arrays.sort(intersections, Comparator.comparingDouble(array -> array[0]));
        int index1 = 0, index2 = 7;
        for (; index1 <= 7; index1++) {
            double[] intersection = intersections[index1];
            double ix = intersection[0], iy = intersection[1];
            if (ix == x1 && iy >= y1 && iy <= y1 + l1) break;
            if (ix == x2 && iy >= y2 && iy <= y2 + l2) break;
            if (iy == y1 && ix >= x1 && ix <= x1 + l1) break;
            if (iy == y2 && ix >= x2 && ix <= x2 + l2) break;
            if (iy == y1 + l1 && ix >= x1 && ix <= x1 + l1) break;
            if (iy == y2 + l2 && ix >= x2 && ix <= x2 + l2) break;
        }
        for (; index2 >= 0; index2--) {
            double[] intersection = intersections[index2];
            double ix = intersection[0], iy = intersection[1];
            if (ix == x1 + l1 && iy >= y1 && iy <= y1 + l1) break;
            if (ix == x2 + l2 && iy >= y2 && iy <= y2 + l2) break;
            if (iy == y1 && ix >= x1 && ix <= x1 + l1) break;
            if (iy == y2 && ix >= x2 && ix <= x2 + l2) break;
            if (iy == y1 + l1 && ix >= x1 && ix <= x1 + l1) break;
            if (iy == y2 + l2 && ix >= x2 && ix <= x2 + l2) break;
        }
        return new double[]{
                intersections[index1][0], intersections[index1][1], intersections[index2][0], intersections[index2][1]
        };
    }

    /**
     * <a href="https://leetcode.cn/problems/best-line-lcci/?envType=featured-list&envId=xb9lfcwi">面试题 16.14. 最佳直线</a>
     * <p>
     * 给定一个二维平面及平面上的 N 个点列表Points，其中第i个点的坐标为Points[i]=[Xi,Yi]。请找出一条直线，其通过的点的数目最多。
     */
    public int[] bestLine(int[][] points) {
        int n = points.length;
        Map<String, int[]> map = new HashMap<>();// key:"A|B|C", value:[i,j,count]
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int[] p1 = points[i];
                int[] p2 = points[j];
                if (p1[0] == p2[0] && p1[1] == p2[1]) continue;
                String key = genCoefficients(p1, p2);
                if (map.containsKey(key)) {
                    map.get(key)[2]++;
                } else {
                    map.put(key, new int[]{i, j, 1});
                }
            }
        }
        return map.values().stream().max((o1, o2) -> {
            if (o1[2] > o2[2]) return 1;
            if (o1[2] < o2[2]) return -1;
            if (o1[0] > o2[0]) return -1;
            if (o1[0] < o2[0]) return 1;
            return Integer.compare(o2[1], o1[1]);
        }).map(ints -> new int[]{ints[0], ints[1]}).orElse(null);
    }

    /**
     * @param p1 点1
     * @param p2 点2
     * @return 过(p1, p2)的系数 a,b,c互质
     * <p>
     * 如果a!=0那么a>0
     * <p>
     * 如果a==0那么b>0
     */
    private String genCoefficients(int[] p1, int[] p2) {
        long x1 = p1[0], x2 = p2[0], y1 = p1[1], y2 = p2[1];
        long a = y2 - y1;
        long b = x1 - x2;
        long c = y1 * (x2 - x1) - x1 * (y2 - y1);
        if (a == 0 && c == 0) return "0|1|0";
        if (b == 0 && c == 0) return "1|0|0";
        long con = 1;
        // a,b不同时为0
        if (a == 0) con = gcd(b, c);
        else if (b == 0) con = gcd(a, c);
        else if (c == 0) con = gcd(a, b);
        else con = gcd(a, b, c);
        if (a < 0) con = -con;
        if (a == 0 && b < 0) con = -con;
        return String.format("%s|%s|%s", a / con, b / con, c / con);
    }

    private long gcd(long a, long b, long c) {
        long con = gcd(a, b);
        if (con == 1) return 1;
        con = gcd(con, c);
        return con;
    }

    private long gcd(long a, long b) {
        assert a != 0 && b != 0;
        // 辗转相除求最大公约数
        a = Math.abs(a);
        b = Math.abs(b);
        if (a < b) {
            long c = a;
            a = b;
            b = c;
        }
        long r = a % b;
        while (r != 0) {
            if (b == 1) return 1;
            a = b;
            b = r;
            r = a % b;
        }
        return b;
    }

    /**
     * <a href="https://leetcode.cn/problems/master-mind-lcci/?envType=featured-list&envId=xb9lfcwi">面试题 16.15. 珠玑妙算</a>
     * <p>
     * 珠玑妙算游戏（the game of master mind）的玩法如下。
     * <p>
     * 计算机有4个槽，每个槽放一个球，颜色可能是红色（R）、黄色（Y）、绿色（G）或蓝色（B）。例如，计算机可能有RGGB 4种（槽1为红色，槽2、3为绿色，槽4为蓝色）。作为用户，你试图猜出颜色组合。打个比方，你可能会猜YRGB。要是猜对某个槽的颜色，则算一次“猜中”；要是只猜对颜色但槽位猜错了，则算一次“伪猜中”。注意，“猜中”不能算入“伪猜中”。
     * <p>
     * 给定一种颜色组合solution和一个猜测guess，编写一个方法，返回猜中和伪猜中的次数answer，其中answer[0]为猜中的次数，answer[1]为伪猜中的次数。
     */
    public int[] masterMind(String solution, String guess) {
        Map<Character, Integer> map = new HashMap<>();
        int c = 0;
        for (int i = 0; i < solution.length(); i++) {
            if (solution.charAt(i) == guess.charAt(i)) c++;
            map.put(solution.charAt(i), map.getOrDefault(solution.charAt(i), 0) + 1);
        }
        int g = -c;
        for (int i = 0; i < guess.length(); i++) {
            char a = guess.charAt(i);
            if (map.getOrDefault(a, 0) > 0) {
                g++;
                map.put(a, map.getOrDefault(a, 0) - 1);
            }
        }
        return new int[]{c, g};
    }

    /**
     * <a href="https://leetcode.cn/problems/sub-sort-lcci/?envType=featured-list&envId=xb9lfcwi">面试题 16.16. 部分排序</a>
     * <p>
     * 给定一个整数数组，编写一个函数，找出索引m和n，只要将索引区间[m,n]的元素排好序，整个数组就是有序的。注意：n-m尽量最小，也就是说，找出符合条件的最短序列。函数返回值为[m,n]，若不存在这样的m和n（例如整个数组是有序的），请返回[-1,-1]。
     */
    public int[] subSort(int[] array) {
        // 排序，第一个不相等到最后一个不相等的之间就是结果
        int[][] t = new int[array.length][2];
        for (int i = 0; i < array.length; i++) {
            t[i][0] = array[i];
            t[i][1] = i;
        }
        Arrays.sort(t, (o1, o2) -> {
            if (o1[0] > o2[0]) return 1;
            if (o1[0] < o2[0]) return -1;
            return Integer.compare(o1[1], o2[1]);
        });
        int min = -1, max = -1;
        for (int i = 0; i < t.length; i++) {
            if (t[i][1] != i) {
                min = i;
                break;
            }
        }
        if (min != -1) {
            for (int i = t.length - 1; i >= 0; i--) {
                if (t[i][1] != i) {
                    max = i;
                    break;
                }
            }
        }
        return new int[]{min, max};


    }

    /**
     * <a href="https://leetcode.cn/problems/contiguous-sequence-lcci/?envType=featured-list&envId=xb9lfcwi">面试题 16.17. 连续数列</a>
     * <p>
     * 给定一个整数数组，找出总和最大的连续数列，并返回总和。
     */
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] > 0) dp[i] = nums[i] + dp[i - 1];
            else dp[i] = nums[i];
            max = Math.max(dp[i], max);
        }
        return max;
    }

    /**
     * <a href="https://leetcode.cn/problems/pattern-matching-lcci/?envType=featured-list&envId=xb9lfcwi">面试题 16.18. 模式匹配</a>
     * <p>
     * 你有两个字符串，即pattern和value。 pattern字符串由字母"a"和"b"组成，用于描述字符串中的模式。例如，字符串"catcatgocatgo"匹配模式"aabab"（其中"cat"是"a"，"go"是"b"），该字符串也匹配像"a"、"ab"和"b"这样的模式。但需注意"a"和"b"不能同时表示相同的字符串。编写一个方法判断value字符串是否匹配pattern字符串。
     */
    public boolean patternMatching(String pattern, String value) {
        return match(pattern, 0, value, 0, null, null);
    }

    public boolean match(String pattern, int i, String value, int j, String a, String b) {
        if (a != null && a.equals(b)) return false;
        if (i == pattern.length()) return j == value.length();
        char c = pattern.charAt(i);
        if (c == 'a') {
            if (a != null)
                return value.substring(j).startsWith(a) && match(pattern, i + 1, value, j + a.length(), a, b);
            else {
                for (int x = j; x <= value.length(); x++) {
                    if (match(pattern, i + 1, value, x, value.substring(j, x), b)) return true;
                }
            }
        }
        if (c == 'b') {
            if (b != null)
                return value.substring(j).startsWith(b) && match(pattern, i + 1, value, j + b.length(), a, b);
            else {
                for (int x = j; x <= value.length(); x++) {
                    if (match(pattern, i + 1, value, x, a, value.substring(j, x))) return true;
                }
            }
        }
        return false;
    }

    /**
     * <a href="https://leetcode.cn/problems/pond-sizes-lcci/?envType=featured-list&envId=xb9lfcwi">面试题 16.19. 水域大小</a>
     * 你有一个用于表示一片土地的整数矩阵land，该矩阵中每个点的值代表对应地点的海拔高度。若值为0则表示水域。由垂直、水平或对角连接的水域为池塘。池塘的大小是指相连接的水域的个数。编写一个方法来计算矩阵中所有池塘的大小，返回值需要从小到大排序。
     */
    public int[] pondSizes(int[][] land) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[0].length; j++) {
                int k = expand(land, i, j);
                if (k > 0) result.add(k);
            }
        }
        int[] t = new int[result.size()];
        for (int i = 0; i < result.size(); i++) t[i] = result.get(i);
        Arrays.sort(t);
        return t;
    }

    private int expand(int[][] land, int i, int j) {
        if (i < 0 || j < 0 || i >= land.length || j >= land[0].length) return 0;
        if (land[i][j] != 0) return 0;
        land[i][j] = Integer.MAX_VALUE;
        return 1 + expand(land, i - 1, j - 1) + expand(land, i - 1, j) + expand(land, i - 1, j + 1) + expand(land, i, j - 1) + expand(land, i, j + 1) + expand(land, i + 1, j - 1) + expand(land, i + 1, j) + expand(land, i + 1, j + 1);
    }

    /**
     * <a href="https://leetcode.cn/problems/t9-lcci/?envType=featured-list&envId=xb9lfcwi">面试题 16.20. T9键盘</a>
     * <p>
     * 在老式手机上，用户通过数字键盘输入，手机将提供与这些数字相匹配的单词列表。每个数字映射到0至4个字母。给定一个数字序列，实现一个算法来返回匹配单词的列表。
     */
    public List<String> getValidT9Words(String num, String[] words) {
        char[] table = new char[26];
        for (int i = 0; i < 26; i++) {
            if (i < 3) table[i] = '2';
            else if (i < 6) table[i] = '3';
            else if (i < 9) table[i] = '4';
            else if (i < 12) table[i] = '5';
            else if (i < 15) table[i] = '6';
            else if (i < 19) table[i] = '7';
            else if (i < 22) table[i] = '8';
            else table[i] = '9';
        }
        Map<String, List<String>> dic = new HashMap<>();
        for (String s : words) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                sb.append(table[s.charAt(i) - 'a']);
            }
            String key = sb.toString();
            if (dic.containsKey(key)) dic.get(key).add(s);
            else dic.put(key, new ArrayList<String>() {{
                add(s);
            }});
        }
        return dic.getOrDefault(num, Collections.emptyList());
    }

    /**
     * <a href="https://leetcode.cn/problems/sum-swap-lcci/?envType=featured-list&envId=xb9lfcwi">面试题 16.21. 交换和</a>
     * <p>
     * 给定两个整数数组，请交换一对数值（每个数组中取一个数值），使得两个数组所有元素的和相等。
     */
    public int[] findSwapValues(int[] array1, int[] array2) {
        int sum1 = Arrays.stream(array1).sum(), sum2 = Arrays.stream(array2).sum();
        if ((sum1 - sum2) % 2 != 0) return new int[0];
        int t = (sum1 - sum2) / 2;
        // ai = bj + t
        Set<Integer> x = Arrays.stream(array2).map(i -> i + t).boxed().collect(Collectors.toSet());
        for (int i : array1) {
            if (x.contains(i)) return new int[]{i, i - t};
        }
        return new int[0];
    }

    /**
     * <a href="https://leetcode.cn/problems/langtons-ant-lcci/?envType=featured-list&envId=xb9lfcwi">面试题 16.22. 兰顿蚂蚁</a>
     * <p>
     * 一只蚂蚁坐在由白色和黑色方格构成的无限网格上。开始时，网格全白，蚂蚁面向右侧。每行走一步，蚂蚁执行以下操作。
     * <p>
     * (1) 如果在白色方格上，则翻转方格的颜色，向右(顺时针)转 90 度，并向前移动一个单位。
     * <p>
     * (2) 如果在黑色方格上，则翻转方格的颜色，向左(逆时针方向)转 90 度，并向前移动一个单位。
     * <p>
     * 编写程序来模拟蚂蚁执行的前 K 个动作，并返回最终的网格。
     */
    public List<String> printKMoves(int k) {
        Map<String, Character> t = new HashMap<>();
        String current = "0|0";
        t.put(current, '_');
        int no = 0;
        char[] directions = {'R', 'D', 'L', 'U'};
        int[][] ops = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int minI = 0, minJ = 0;
        int maxI = 0, maxJ = 0;
        for (int m = 0; m < k; m++) {
            char c = t.get(current);
            String[] temp = current.split("\\|");
            int i = Integer.parseInt(temp[0]), j = Integer.parseInt(temp[1]);
            if (c == '_') {
                t.put(current, 'X');
                no = (no + 1) % 4;
            } else {
                t.put(current, '_');
                no = (no + 3) % 4;
            }
            int nextI = i + ops[no][0], nextJ = j + ops[no][1];
            minI = Math.min(nextI, minI);
            minJ = Math.min(nextJ, minJ);
            maxI = Math.max(nextI, maxI);
            maxJ = Math.max(nextJ, maxJ);
            current = String.format("%d|%d", nextI, nextJ);
            if (!t.containsKey(current)) t.put(current, '_');
        }
        char[][] result = new char[maxI - minI + 1][maxJ - minJ + 1];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                String loc = String.format("%d|%d", minI + i, minJ + j);
                result[i][j] = t.getOrDefault(loc, '_');
            }
        }
        String[] temp = current.split("\\|");
        int i = Integer.parseInt(temp[0]), j = Integer.parseInt(temp[1]);
        result[i - minI][j - minJ] = directions[no];
        return Arrays.stream(result).map(String::new).collect(Collectors.toList());
    }

    /**
     * <a href="https://leetcode.cn/problems/pairs-with-sum-lcci/?envType=featured-list&envId=xb9lfcwi">面试题 16.24. 数对和</a>
     * <p>
     * 设计一个算法，找出数组中两数之和为指定值的所有整数对。一个数只能属于一个数对。
     */
    public List<List<Integer>> pairSums(int[] nums, int target) {
        Map<Integer, Integer> numCount = new HashMap<>();
        for (int i : nums) {
            numCount.put(i, numCount.getOrDefault(i, 0) + 1);
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i : nums) {
            int iCount = numCount.getOrDefault(i, 0);
            int c = target - i;
            if (i != c) {
                int cCount = numCount.getOrDefault(c, 0);
                if (iCount > 0 && cCount > 0) {
                    numCount.put(i, iCount - 1);
                    numCount.put(c, cCount - 1);
                    result.add(Arrays.asList(i, c));
                }
            } else {
                if (iCount >= 2) {
                    numCount.put(i, iCount - 2);
                    result.add(Arrays.asList(i, c));
                }
            }
        }
        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/lru-cache-lcci/?envType=featured-list&envId=xb9lfcwi">面试题 16.25. LRU 缓存</a>
     * <p>
     * 设计和构建一个“最近最少使用”缓存，该缓存会删除最近最少使用的项目。缓存应该从键映射到值(允许你插入和检索特定键对应的值)，并在初始化时指定最大容量。当缓存被填满时，它应该删除最近最少使用的项目。
     * <p>
     * 它应该支持以下操作： 获取数据 get 和 写入数据 put 。
     * <p>
     * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
     * <p>
     * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
     */
    public static class LRUCache {

        private final int maxSize;
        private final Map<Integer, DoubleLinkNode> data;
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

        private static class DoubleLinkNode {
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

    /**
     * <a href="https://leetcode.cn/problems/calculator-lcci/?envType=featured-list&envId=xb9lfcwi">面试题 16.26. 计算器</a>
     * <p>
     * 给定一个包含正整数、加(+)、减(-)、乘(*)、除(/)的算数表达式(括号除外)，计算其结果。
     * <p>
     * 表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
     */
    public int calculate(String s) {
        Stack<Integer> ns = new Stack<>();
        Stack<Character> os = new Stack<>();
        if (s.charAt(0) == '-') s = "0" + s;
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c == ' ') {
                i++;
                continue;
            }
            if (c <= '9' && c >= '0') {
                int k = c - '0';
                i++;
                while (i < s.length() && s.charAt(i) <= '9' && s.charAt(i) >= '0') {
                    k = k * 10 + s.charAt(i) - '0';
                    i++;
                }
                ns.push(k);
                continue;
            }
            if (c == '+' || c == '-') {
                while (!os.isEmpty()) {
                    char op = os.pop();
                    int a = ns.pop(), b = ns.pop();
                    if (op == '*') ns.push(b * a);
                    else if (op == '+') ns.push(b + a);
                    else if (op == '/') ns.push(b / a);
                    else ns.push(b - a);
                }
            }
            os.push(c);
            i++;
        }
        while (!os.isEmpty()) {
            char op = os.pop();
            int a = ns.pop(), b = ns.pop();
            if (op == '*') ns.push(b * a);
            else if (op == '+') ns.push(b + a);
            else if (op == '/') ns.push(b / a);
            else ns.push(b - a);
        }
        return ns.pop();
    }
}
