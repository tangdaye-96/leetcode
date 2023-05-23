package psn.tangdaye.solutions;

import psn.tangdaye.model.ListNode;
import psn.tangdaye.model.TreeNode;
import psn.tangdaye.tool.Tools;

import java.util.*;

/**
 * @author shayan
 * @date : 2023/5/12 13:40
 * https://leetcode.cn/problem-list/xb9lfcwi/
 */
public class Interview {
    /**
     * 面试题 01.01. 判定字符是否唯一
     * <p>
     * 实现一个算法，确定一个字符串 s 的所有字符是否全都不同
     * <p>
     * https://leetcode.cn/problems/is-unique-lcci/?favorite=xb9lfcwi
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
     * https://leetcode.cn/problems/check-permutation-lcci/?favorite=xb9lfcwi
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
     * https://leetcode.cn/problems/string-to-url-lcci/?favorite=xb9lfcwi
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
     * https://leetcode.cn/problems/palindrome-permutation-lcci/?favorite=xb9lfcwi
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
     * https://leetcode.cn/problems/one-away-lcci/?favorite=xb9lfcwi
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
     * https://leetcode.cn/problems/compress-string-lcci/?favorite=xb9lfcwi
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
     * https://leetcode.cn/problems/rotate-matrix-lcci/?favorite=xb9lfcwi
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
     * https://leetcode.cn/problems/zero-matrix-lcci/?favorite=xb9lfcwi
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
     * https://leetcode.cn/problems/string-rotation-lcci/?favorite=xb9lfcwi
     */
    public boolean isFlippedString(String s1, String s2) {
        return s1.length() == s2.length() && (s2 + s2).contains(s1);
    }

    /**
     * 面试题 02.01. 移除重复节点
     * <p>
     * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
     * <p>
     * https://leetcode.cn/problems/remove-duplicate-node-lcci/?favorite=xb9lfcwi
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
     * https://leetcode.cn/problems/kth-node-from-end-of-list-lcci/?favorite=xb9lfcwi
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
     * https://leetcode.cn/problems/delete-middle-node-lcci/?favorite=xb9lfcwi
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
     * https://leetcode.cn/problems/partition-list-lcci/?favorite=xb9lfcwi
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
     * https://leetcode.cn/problems/sum-lists-lcci/?favorite=xb9lfcwi
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
     * https://leetcode.cn/problems/palindrome-linked-list-lcci/?favorite=xb9lfcwi
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
     * https://leetcode.cn/problems/intersection-of-two-linked-lists-lcci/?favorite=xb9lfcwi
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
     * https://leetcode.cn/problems/linked-list-cycle-lcci/?favorite=xb9lfcwi
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
     * https://leetcode.cn/problems/three-in-one-lcci/?favorite=xb9lfcwi
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
     * https://leetcode.cn/problems/min-stack-lcci/?favorite=xb9lfcwi
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
     * https://leetcode.cn/problems/stack-of-plates-lcci/?favorite=xb9lfcwi
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
     * https://leetcode.cn/problems/implement-queue-using-stacks-lcci/?envType=featured-list&envId=xb9lfcwi
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
     * https://leetcode.cn/problems/sort-of-stacks-lcci/?envType=featured-list&envId=xb9lfcwi
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
     * https://leetcode.cn/problems/animal-shelter-lcci/?envType=featured-list&envId=xb9lfcwi
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
     * https://leetcode.cn/problems/route-between-nodes-lcci/?envType=featured-list&envId=xb9lfcwi
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
     * https://leetcode.cn/problems/minimum-height-tree-lcci/?envType=featured-list&envId=xb9lfcwi
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
     * https://leetcode.cn/problems/list-of-depth-lcci/?envType=featured-list&envId=xb9lfcwi
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
}
