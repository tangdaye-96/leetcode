package psn.tangdaye.solutions;

import psn.tangdaye.model.DoubleLinkedNode;
import psn.tangdaye.model.ListNode;
import psn.tangdaye.model.Node;
import psn.tangdaye.model.TreeNode;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class Offer {

    /**
     * 剑指 Offer 09. 用两个栈实现队列
     * 看了答案
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

    /**
     * 剑指 Offer 10- II. 青蛙跳台阶问题
     */
    public int numWays(int n) {
        if (n == 0) return 1;
        if (n == 1) return 1;
        BigInteger a = new BigInteger("1");
        BigInteger b = new BigInteger("1");
        BigInteger c = new BigInteger("2");
        for (int i = 2; i <= n; i++) {
            c = a.add(b);
            a = b;
            b = c;
        }
        return c.mod(new BigInteger("1000000007")).intValue();
    }

    /**
     * 剑指 Offer 63. 股票的最大利润
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int[] dp = new int[prices.length];
        dp[0] = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            int price = prices[i];
            int thisMax = price - min;
            dp[i] = Math.max(thisMax, dp[i - 1]);
            min = Math.min(min, price);
        }
        return dp[prices.length - 1];
    }

    /**
     * 剑指 Offer 42. 连续子数组的最大和
     */
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length]; // 包含自身的最大连续子数组和
        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = dp[i - 1] < 0 ? nums[i] : dp[i - 1] + nums[i];
            max = Math.max(max, dp[i]);
        }
        return max;
    }


    /**
     * 剑指 Offer 47. 礼物的最大价值
     */
    public int maxValue(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 剑指 Offer 46. 把数字翻译成字符串
     */
    public int translateNum(int num) {
        if (num <= 9) return 1;
        String str = String.valueOf(num);
        int[] dp = new int[str.length()];
        dp[0] = 1;
        dp[1] = str.charAt(0) == '1' || (str.charAt(0) == '2' && str.charAt(1) <= '5') ? 2 : 1;
        for (int i = 2; i < str.length(); i++) {
            dp[i] = dp[i - 1];
            if (str.charAt(i - 1) == '1' || (str.charAt(i - 1) == '2' && str.charAt(i) <= '5')) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
        }
        return dp[str.length() - 1];
    }

    /**
     * 剑指 Offer 48. 最长不含重复字符的子字符串
     * 看了答案
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;
        Set<Character> characters = new HashSet<>();
        characters.add(s.charAt(0));
        int i = 0, j = 1;
        int max = Integer.MIN_VALUE;
        while (i < s.length() && j < s.length()) {
            while (j < s.length()) {
                char c = s.charAt(j);
                if (characters.contains(c)) {
                    break;
                }
                characters.add(c);
                j++;
            }
            max = Math.max(max, j - i);
            characters.remove(s.charAt(i));
            i++;
        }
        return max;
    }

    /**
     * 剑指 Offer 18. 删除链表的节点
     */
    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) return null;
        if (head.val == val) return head.next;
        ListNode current = head;
        while (current != null) {
            if (current.next == null) return head;
            if (current.next.val == val) {
                current.next = current.next.next;
            }
            current = current.next;
        }
        return head;
    }

    /**
     * 剑指 Offer 22. 链表中倒数第k个节点
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode kthNode = head;
        for (int i = 0; i < k; i++) {
            kthNode = kthNode.next;
        }
        ListNode current = head;
        while (kthNode != null) {
            current = current.next;
            kthNode = kthNode.next;
        }
        return current;
    }

    /**
     * 剑指 Offer 25. 合并两个排序的链表
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode n1 = l1;
        ListNode n2 = l2;
        ListNode head = new ListNode(Integer.MIN_VALUE);
        ListNode current = head;
        while (n1 != null && n2 != null) {
            if (n1.val <= n2.val) {
                current.next = new ListNode(n1.val);
                n1 = n1.next;
            } else {
                current.next = new ListNode(n2.val);
                n2 = n2.next;
            }
            current = current.next;
        }
        while (n1 != null) {
            current.next = new ListNode(n1.val);
            n1 = n1.next;
            current = current.next;
        }
        while (n2 != null) {
            current.next = new ListNode(n2.val);
            n2 = n2.next;
            current = current.next;
        }
        return head.next;
    }

    /**
     * 剑指 Offer 52. 两个链表的第一个公共节点
     * 看了答案
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode a = headA;
        ListNode b = headB;
        while (true) {
            if (a == b) return a;
            if (a != null && b != null) {
                a = a.next;
                b = b.next;
            } else if (a == null) {
                a = headB;
            } else {
                b = headA;
            }
        }
    }

    /**
     * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
     */
    public int[] exchange(int[] nums) {
        int i = 0, n = nums.length - 1;
        while (i < n) {
            int t = nums[i];
            if (t % 2 == 0) {
                nums[i] = nums[n];
                nums[n] = t;
                n--;
            } else {
                i++;
            }
        }
        return nums;
    }

    /**
     * 剑指 Offer 57. 和为s的两个数字
     */
    public int[] twoSum(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int sum = nums[i] + nums[j];
            if (sum == target) {
                int[] result = new int[2];
                result[0] = nums[i];
                result[1] = nums[j];
                return result;
            } else if (sum < target) {
                i++;
            } else {
                j--;
            }
        }
        return null;
    }

    /**
     * 剑指 Offer 58 - I. 翻转单词顺序
     */
    public String reverseWords(String s) {
        s = s.trim();
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }

    /**
     * 剑指 Offer 12. 矩阵中的路径
     * 看了答案
     */
    public boolean exist(char[][] board, String word) {
        boolean[][] used = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (check(board, i, j, 0, word, used)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean check(char[][] board, int i, int j, int k, String word, boolean[][] used) {
        if (board[i][j] != word.charAt(k)) return false;
        if (k == word.length() - 1) return true;
        used[i][j] = true;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean result = false;
        for (int[] dir : directions) {
            int s = i + dir[0], t = j + dir[1];
            if (s >= 0 && s < board.length && t >= 0 && t < board[0].length) {
                if (!used[s][t]) {
                    boolean flag = check(board, s, t, k + 1, word, used);
                    if (flag) {
                        result = true;
                        break;
                    }
                }
            }
        }
        used[i][j] = false;
        return result;
    }

    /**
     * 剑指 Offer 13. 机器人的运动范围
     */
    public int movingCount(int m, int n, int k) {
        List<int[]> candidates = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i / 100 + (i % 100) / 10 + i % 10 + j / 100 + (j % 100) / 10 + j % 10 <= k) {
                    candidates.add(new int[]{i, j});
                }
            }
        }
        Set<String> done = new HashSet<>();
        done.add("0,0");
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int h = 1; h < candidates.size(); h++) {
            int[] candidate = candidates.get(h);
            int i = candidate[0];
            int j = candidate[1];
            for (int[] dir : directions) {
                int s = i + dir[0], t = j + dir[1];
                if (s >= 0 && s < m && t >= 0 && t < n && done.contains(s + "," + t)) {
                    done.add(i + "," + j);
                }
            }
        }
        return done.size();
    }


    /**
     * 剑指 Offer 34. 二叉树中和为某一值的路径
     */
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<LinkedList<Integer>> r = subPathSum(root, target);
        return new ArrayList<>(r);
    }

    public List<LinkedList<Integer>> subPathSum(TreeNode root, int target) {
        if (root == null) {
            return Collections.emptyList();
        }
        if (root.left == null && root.right == null) {
            if (root.val == target) {
                return List.of(new LinkedList<>() {{
                    add(root.val);
                }});
            } else return Collections.emptyList();
        }
        List<LinkedList<Integer>> result = new ArrayList<>();
        List<LinkedList<Integer>> l = subPathSum(root.left, target - root.val);
        List<LinkedList<Integer>> r = subPathSum(root.right, target - root.val);
        for (LinkedList<Integer> a : l) {
            a.push(root.val);
            result.add(a);
        }
        for (LinkedList<Integer> a : r) {
            a.push(root.val);
            result.add(a);
        }
        return result;
    }

    /**
     * 剑指 Offer 36. 二叉搜索树与双向链表
     */
    public DoubleLinkedNode treeToDoublyList(DoubleLinkedNode root) {
        if (root == null) return null;
        DoubleLinkedNode l = treeToDoublyList(root.left);
        DoubleLinkedNode r = treeToDoublyList(root.right);
        DoubleLinkedNode lEnd;
        DoubleLinkedNode rEnd;
        if (l == null && r == null) {
            root.left = root;
            root.right = root;
            return root;
        }
        if (l == null) {
            rEnd = r.left;
            root.right = r;
            r.left = root;
            rEnd.right = root;
            root.left = rEnd;
            return root;
        }
        if (r == null) {
            lEnd = l.left;
            lEnd.right = root;
            root.left = lEnd;
            root.right = l;
            l.left = root;
            return l;
        }
        lEnd = l.left;
        rEnd = r.left;
        // 左节点的终点的right是root
        lEnd.right = root;
        root.left = lEnd;
        // 右节点的起点的left是root
        r.left = root;
        root.right = r;
        // 右节点的终点的right是左节点
        rEnd.right = l;
        l.left = rEnd;

        return l;
    }

    /**
     * 剑指 Offer 54. 二叉搜索树的第k大节点
     */
    public int kthLargest(TreeNode root, int k) {
        ArrayList<Integer> list = root.middle();
        return list.get(list.size() - k);
    }

    /**
     * 剑指 Offer 45. 把数组排成最小的数
     */
    public String minNumber(int[] nums) {
        String[] array = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            array[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(array, (left, right) -> {
            int i = 0;
            while (i < left.length() && i < right.length()) {
                if (left.charAt(i) < right.charAt(i)) return -1;
                if (left.charAt(i) > right.charAt(i)) return 1;
                i++;
            }
            return (left + right).compareTo(right + left);
        });
        return String.join("", array);
    }

    /**
     * 剑指 Offer 61. 扑克牌中的顺子
     */
    public boolean isStraight(int[] nums) {
        Arrays.sort(nums);
        int i = 0;
        while (nums[i] == 0) i++;
        for (int k = i; k < nums.length - 1; k++) {
            int t = nums[k];
            int n = nums[k + 1];
            if (t == n) return false;
            if (i - (n - t - 1) >= 0) {
                i -= (n - t - 1);
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 面试题40. 最小的k个数
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        int[] result = new int[k];
        System.arraycopy(arr, 0, result, 0, k);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k + 1, Collections.reverseOrder());
        for (int i : result) maxHeap.add(i);
        for (int i = k; i < arr.length; i++) {
            if (!maxHeap.isEmpty() && arr[i] < maxHeap.peek()) {
                maxHeap.add(arr[i]);
                maxHeap.poll();
            }
        }
        for (int i = 0; i < arr.length && !maxHeap.isEmpty(); i++) {
            result[i] = maxHeap.poll();
        }
        return result;
    }

    /**
     * 剑指 Offer 41. 数据流中的中位数
     * 优化：大堆 + 小堆
     */
    public static class MedianFinder {
        private final LinkedList<Integer> list;

        public MedianFinder() {
            list = new LinkedList<>();
        }

        public void addNum(int num) {
            int index = Collections.binarySearch(list, num);
            if (index < 0) index = -index - 1;
            list.add(index, num);
        }

        public double findMedian() {
            if (list.size() % 2 == 0) {
                int index = list.size() / 2 - 1;
                return ((double) list.get(index) + list.get(index + 1)) / 2;
            }
            int index = list.size() / 2;
            return list.get(index);
        }
    }

    /**
     * 104. 二叉树的最大深度
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 剑指 Offer 55 - II. 平衡二叉树
     */
    public boolean isBalanced(TreeNode root) {
        return depth(root) != -1;
    }

    public int depth(TreeNode current) {
        if (current == null) return 0;
        int left = depth(current.left);
        if (left == -1) return -1;
        int right = depth(current.right);
        if (right == -1) return -1;
        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
    }

    /**
     * 剑指 Offer 64. 求1+2+…+n
     * 看了一下短路思路
     */
    public int sumNums(int n) {
        return (square(n) + n) >> 1;
    }

    private int square(int a) {
        int o = 0;
        o += square(a, 0);
        o += square(a, 1);
        o += square(a, 2);
        o += square(a, 3);
        o += square(a, 4);
        o += square(a, 5);
        o += square(a, 6);
        o += square(a, 7);
        o += square(a, 8);
        o += square(a, 9);
        o += square(a, 10);
        o += square(a, 11);
        o += square(a, 12);
        o += square(a, 13);
        o += square(a, 14);
        o += square(a, 15);
        o += square(a, 16);
        o += square(a, 17);
        o += square(a, 18);
        o += square(a, 19);
        o += square(a, 20);
        o += square(a, 21);
        o += square(a, 22);
        o += square(a, 23);
        o += square(a, 24);
        o += square(a, 25);
        o += square(a, 26);
        o += square(a, 27);
        o += square(a, 28);
        o += square(a, 29);
        o += square(a, 30);
        o += square(a, 31);
        return o;
    }

    private int square(int a, int i) {
        int o = 0;
        boolean k = (a & (1 << i)) > 0 && (o += a << i) >= 0;
        return o;
    }

    /**
     * 剑指 Offer 68 - II. 二叉树的最近公共祖先
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        parentMap.put(root, null);
        genParentMap(root, parentMap);

        List<TreeNode> pAncestors = new ArrayList<>();
        List<TreeNode> qAncestors = new ArrayList<>();
        TreeNode pAncestor = p;
        while (pAncestor != null) {
            pAncestors.add(pAncestor);
            pAncestor = parentMap.get(pAncestor);
        }

        TreeNode qAncestor = q;
        while (qAncestor != null) {
            qAncestors.add(qAncestor);
            qAncestor = parentMap.get(qAncestor);
        }

        for (TreeNode i : pAncestors) {
            if (qAncestors.contains(i)) return i;
        }
        return null;
    }

    private void genParentMap(TreeNode current, Map<TreeNode, TreeNode> parentMap) {
        if (current == null) return;
        if (current.left != null) {
            parentMap.put(current.left, current);
        }
        if (current.right != null) {
            parentMap.put(current.right, current);
        }
        genParentMap(current.left, parentMap);
        genParentMap(current.right, parentMap);
    }

    /**
     * 剑指 Offer 07. 重建二叉树
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preorder.length - 1, inorder, inorderMap, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int i, int j, int[] inorder, Map<Integer, Integer> inorderMap, int s, int t) {
        if (i > j || s > t || i < 0 || j >= preorder.length || s < 0 || t >= inorder.length) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[i]);
        int inRootIndex = inorderMap.get(root.val);
        int preRightIndex = inRootIndex - s + i + 1;
        root.left = buildTree(preorder, i + 1, preRightIndex - 1, inorder, inorderMap, s, inRootIndex - 1);
        root.right = buildTree(preorder, preRightIndex, j, inorder, inorderMap, inRootIndex + 1, t);
        return root;
    }

    /**
     * 剑指 Offer 16. 数值的整数次方
     */
    public double myPow(double x, int n) {
        if (n == 0) return 1;
        if (Integer.MIN_VALUE < n && n < 0) return 1.0 / myPow(x, -n);
        if (Integer.MIN_VALUE == n) {
            if (x == 1 || x == -1) return 1;
            return 0.0;
        }
        if (n % 2 == 0) {
            double y = myPow(x, n / 2);
            return y * y;
        } else {
            double y = myPow(x, (n - 1) / 2);
            return y * y * x;
        }
    }

    /**
     * 剑指 Offer 33. 二叉搜索树的后序遍历序列
     */
    public boolean verifyPostorder(int[] postorder) {
        return verifyPostorder(postorder, 0, postorder.length - 1, 0, 0);
    }

    public boolean verifyPostorder(int[] postorder, int low, int high, int flag, int parent) {
        if (low >= high) return true;
        int root = postorder[high];
        int leftIndex = low - 1;
        boolean set = false;
        for (int i = high; i >= low; i--) {
            if (flag == 1 && postorder[i] <= parent) return false;
            if (flag == -1 && postorder[i] >= parent) return false;
            if (postorder[i] < root && !set) {
                leftIndex = i;
                set = true;
            }
        }

        return verifyPostorder(postorder, low, leftIndex, -1, root) && verifyPostorder(postorder, leftIndex + 1, high - 1, 1, root);
    }

    /**
     * 剑指 Offer 15. 二进制中1的个数
     */
    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) count += 1;
        }
        return count;
    }

    /**
     * 剑指 Offer 65. 不用加减乘除做加法
     */
    public int add(int a, int b) {
        int[] x = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
        int sum = 0;
        boolean r = false;
        for (int i : x) {
            int t = 1 << i;
            int ta = a & t;
            int tb = b & t;
            if (ta == t && tb == t) {
                if (r) {
                    sum |= t;
                } else r = true;
            } else if (ta == t || tb == t) {
                if (!r) {
                    sum |= t;
                }
            } else {
                if (r) {
                    sum |= t;
                }
                r = false;
            }
        }
        return sum;
    }

    /**
     * 剑指 Offer 56 - I. 数组中数字出现的次数
     * 想到了xy，没想到分组
     */
    public int[] singleNumbers(int[] nums) {
        int xy = 0;
        for (int i : nums) {
            xy ^= i;
        }
        int x = xy;
        int t = 0;
        while (0 == ((xy >> t) & 1)) t++;
        for (int i : nums) {
            if (1 == ((i >> t) & 1)) x ^= i;
        }
        int[] result = new int[2];
        result[0] = x;
        result[1] = x ^ xy;
        return result;
    }

    /**
     * 剑指 Offer 56 - II. 数组中数字出现的次数 II
     */
    public int singleNumber(int[] nums) {
        int x = 0;
        for (int k = 0; k < 32; k++) {
            boolean f1 = false;
            boolean f2 = false;
            for (int i : nums) {
                if (((i >> k) & 1) == 1) {
                    if (!f1 && !f2) {
                        f2 = true;
                    } else if (!f1) {
                        f1 = true;
                        f2 = false;
                    } else {
                        f1 = false;
                    }
                }
            }
            if (f2) {
                x |= (1 << k);
            }
        }
        return x;
    }

    /**
     * 剑指 Offer 39. 数组中出现次数超过一半的数字
     * 这个精巧的思路参考了答案
     */
    public int majorityElement(int[] nums) {
        int x = 0;
        int votes = 0;
        for (int i : nums) {
            if (votes == 0) x = i;
            votes += x == i ? 1 : -1;
        }
        return x;
    }

    /**
     * 剑指 Offer 66. 构建乘积数组
     * 参考了答案
     */
    public int[] constructArr(int[] a) {
        int n = a.length;
        if (n == 0) return a;
        int[] l = new int[n];
        l[0] = 1;
        int[] r = new int[n];
        r[n - 1] = 1;
        for (int i = 1; i < n; i++) {
            l[i] = l[i - 1] * a[i - 1];
            r[n - i - 1] = r[n - i] * a[n - i];
        }
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = l[i] * r[i];
        }
        return b;
    }

    /**
     * 剑指 Offer 14- I. 剪绳子
     */
    public int cuttingRope(int n) {
        int max = Integer.MIN_VALUE;
        for (int i = 2; i <= n; i++) {
            max = Math.max(maxCutting(n, i), max);
        }
        return max;
    }

    private int maxCutting(int n, int m) {
        int q = n / m;
        int r = n % m;
        return (int) (Math.pow(q, m - r) * Math.pow(q + 1, r));
    }

    /**
     * 剑指 Offer 14- II. 剪绳子 II
     */
    public int cuttingRope2(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;
        long t = 1;
        int p = 0;
        int q = 0;
        if (n % 3 == 1) {
            p = (n - 4) / 3;
            q = 2;
        } else if (n % 3 == 2) {
            p = (n - 2) / 3;
            q = 1;
        } else {
            p = n / 3;
        }
        for (int i = 1; i <= p; i++) {
            t = (t * 3) % 1000000007;
        }
        for (int i = 1; i <= q; i++) {
            t = (t * 2) % 1000000007;
        }
        return (int) t;
    }

    /**
     * 剑指 Offer 57 - II. 和为s的连续正数序列
     */
    public int[][] findContinuousSequence(int target) {
        List<int[]> result = new ArrayList<>();
        for (int m = 2; m * (m + 1) <= 2 * target; m++) {
            if ((2 * target - (m + 1) * m) % (2 * m) == 0) {
                int n = (2 * target - (m * m - 1)) / (2 * m);
                int[] t = new int[m];
                for (int i = 1; i <= m; i++) {
                    t[i - 1] = n + i;
                }
                result.add(t);
            }
        }
        Collections.reverse(result);
        int[][] result2 = new int[result.size()][];
        result.toArray(result2);
        return result2;
    }

    /**
     * 剑指 Offer 62. 圆圈中最后剩下的数字
     * 参考了答案
     */
    public int lastRemaining(int n, int m) {
        return lastRemainingR(n, m);
    }

    private int lastRemainingR(int n, int m) {
        if (n == 1) {
            return 0;
        }
        int x = lastRemainingR(n - 1, m);
        return (m + x) % n;
    }

    /**
     * 剑指 Offer 29. 顺时针打印矩阵
     */
    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return new int[0];
        int i = 0, j = 0;
        int t = 0;
        int m = matrix.length, n = matrix[0].length;
        int[] result = new int[m * n];
        int a = 0, b = n, c = 1, d = m;
        int direction = 0;
        while (t < m * n) {
            result[t] = matrix[i][j];
            t += 1;
            if (direction == 0 && j == b - 1) {
                b = b - 1;
                direction = 1;
            }
            if (direction == 1 && i == d - 1) {
                d = d - 1;
                direction = 2;
            }
            if (direction == 2 && j == a) {
                a = a + 1;
                direction = 3;
            }
            if (direction == 3 && i == c) {
                c = c + 1;
                direction = 0;
            }
            if (direction == 0) j = j + 1; // 右
            else if (direction == 1) i = i + 1; // 下
            else if (direction == 2) j = j - 1; // 左
            else i = i - 1; // 上
        }
        return result;
    }

    /**
     * 剑指 Offer 31. 栈的压入、弹出序列
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i = 0, j = 0;
        while (j < popped.length && i <= pushed.length) {
            if (!stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j += 1;
            } else {
                if (i == pushed.length) return false;
                stack.push(pushed[i]);
                i++;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 剑指 Offer 20. 表示数值的字符串
     */
    public boolean isNumber(String s) {
        if (s.length() == 0) return false;
        int start = 0, end = s.length();
        while (start < s.length() && s.charAt(start) == ' ') {
            start += 1;
        }
        while (end >= 1 && s.charAt(end - 1) == ' ') {
            end -= 1;
        }
        int eIndex = -1;
        for (int i = start; i < end; i++) {
            if (s.charAt(i) == 'e' || s.charAt(i) == 'E') {
                eIndex = i;
                break;
            }
        }
        if (eIndex == -1) {
            return isDecimalOrInteger(s, start, end);
        } else {
            return isDecimalOrInteger(s, start, eIndex) && isInteger(s, eIndex + 1, end);
        }
    }

    private boolean isDecimalOrInteger(String s, int start, int end) {
        if (end <= start) return false;
        char f = s.charAt(start);
        int t = (f == '+' || f == '-') ? 1 : 0;
        int dotIndex = s.indexOf('.', start + t);
        if (dotIndex == -1) return isUnSignNumber(s, start + t, end);
        else if (dotIndex == start + t) return isUnSignNumber(s, start + t + 1, end);
        else if (dotIndex > start + t && dotIndex < end - 1)
            return isUnSignNumber(s, start + t, dotIndex) && isUnSignNumber(s, dotIndex + 1, end);
        else if (dotIndex == end - 1) return isUnSignNumber(s, start + t, end - 1);
        else return false;

    }

    private boolean isInteger(String s, int start, int end) {
        if (end <= start) return false;
        char f = s.charAt(start);
        int t = (f == '+' || f == '-') ? 1 : 0;
        return isUnSignNumber(s, start + t, end);
    }

    private boolean isUnSignNumber(String s, int start, int end) {
        if (end <= start) return false;
        for (int i = start; i < end; i++) {
            char c = s.charAt(i);
            if (c < '0' || c > '9') return false;
        }
        return true;
    }

    /**
     * 剑指 Offer 67. 把字符串转换成整数
     */
    public int strToInt(String str) {
        if (str.length() == 0) return 0;
        int start = 0;
        while (start < str.length() && str.charAt(start) == ' ') {
            start += 1;
        }
        if (start == str.length()) return 0;
        char f = str.charAt(start);
        if (f != '+' && f != '-' && (f < '0' || f > '9')) return 0;
        int t = (f == '+' || f == '-') ? 1 : 0;

        int end = start + t;
        while (end < str.length() && str.charAt(end) >= '0' && str.charAt(end) <= '9') {
            end++;
        }
        int d = 1;
        int result = 0;
        for (int i = end - 1; i >= start + t; i--) {
            if ((d % 10 != 0 && d != 1)) {
                for (int k = start + t; k <= i; k++) {
                    if (str.charAt(k) != '0') return f != '-' ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
                return f != '-' ? result : -result;
            }

            if (1000000000 == d && str.charAt(i) > '2') return f != '-' ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            if (1000000000 == d && str.charAt(i) == '2' && result > Integer.MAX_VALUE - 2000000000)
                return f != '-' ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            result += (str.charAt(i) - '0') * d;
            d *= 10;
        }
        return f != '-' ? result : -result;
    }

    /**
     * 剑指 Offer 59 - I. 滑动窗口的最大值
     * 参考了答案 单调队列
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) return nums;
        if (k == 1) return nums;
        int[] q = new int[nums.length + 1];
        int[] result = new int[nums.length + 1 - k];
        int head = 0;
        int tail = 0;
        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            while (tail >= head && nums[q[tail]] < current) tail--;
            tail++;
            q[tail] = i;
            while (q[head] <= i - k) head++;

            if (i >= k - 1) {
                result[i + 1 - k] = nums[q[head]];
            }
        }
        return result;
    }

    /**
     * 剑指 Offer 59 - II. 队列的最大值
     */
    public static class MaxQueue {
        private LinkedList<Integer> data;
        private LinkedList<Integer> maxSince;

        public MaxQueue() {
            data = new LinkedList<>();
            maxSince = new LinkedList<>();
        }

        public int max_value() {
            if (maxSince.isEmpty()) return -1;
            return maxSince.peekFirst();
        }

        public void push_back(int value) {
            while (!maxSince.isEmpty() && maxSince.peekLast() < value) {
                maxSince.pollLast();
            }
            maxSince.add(value);
            data.add(value);


        }

        public int pop_front() {
            if (data.isEmpty() || maxSince.isEmpty()) {
                return -1;
            }
            int ans = data.poll();
            if (maxSince.peekFirst() != null && ans == maxSince.peekFirst()) {
                maxSince.pollFirst();
            }
            return ans;
        }
    }

    /**
     * 剑指 Offer 17. 打印从1到最大的n位数
     */
    public int[] printNumbers(int n) {
        int[] result = new int[(int) Math.pow(10, n) - 1];
        for (int i = 0; i < result.length; i++) result[i] = i + 1;
        return result;
    }

    /**
     * 剑指 Offer 37. 序列化二叉树
     */
    public static class Codec {
        public String serialize(TreeNode root) {
            if (root == null) return "[]";
            LinkedList<TreeNode> q = new LinkedList<>();
            q.push(root);
            LinkedList<Integer> result = new LinkedList<>();
            while (!q.isEmpty()) {
                TreeNode current = q.pop();
                if (current != null) {
                    result.add(current.val);
                    q.add(current.left);
                    q.add(current.right);
                } else {
                    result.add(null);
                }
            }

            while (true) {
                Integer t = result.pollLast();
                if (t != null) {
                    result.add(t);
                    break;
                }
            }

            return result.toString();
        }

        public TreeNode deserialize(String data) {
            if ("[]".equals(data)) return null;

            String[] temp = data.substring(1, data.length() - 1).split(",");
            Integer[] array = new Integer[temp.length];
            for (int i = 0; i < temp.length; i++) {
                String s = temp[i].trim();
                if ("null".equals(s)) array[i] = null;
                else array[i] = Integer.valueOf(s);
            }
            if (array.length == 0 || null == array[0]) return null;

            LinkedList<TreeNode> q = new LinkedList<>();
            TreeNode root = new TreeNode(array[0]);
            q.push(root);
            int i = 1;
            while (!q.isEmpty() && i < array.length) {
                TreeNode current = q.pop();
                if (array[i] != null) {
                    current.left = new TreeNode(array[i]);
                    q.add(current.left);
                }
                i++;
                if (i < array.length && array[i] != null) {
                    current.right = new TreeNode(array[i]);
                    q.add(current.right);
                }
                i++;
            }
            return root;
        }
    }

    /**
     * 剑指 Offer 60. n个骰子的点数
     */
    public double[] dicesProbability(int n) {
        long[][] dp = new long[n + 1][6 * n + 1];
        for (int i = 1; i <= 6; i++) {
            dp[1][i] = 1L;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = i; j <= 6 * i; j++) {
                for (int k = 1; k <= 6; k++) {
                    dp[i][j] += j - k >= 0 ? dp[i - 1][j - k] : 0L;
                }
            }
        }
        double[] result = new double[5 * n + 1];
        double t = Math.pow(6, n);
        for (int i = n; i <= 6 * n; i++) {
            double sum = (double) dp[n][i];
            result[i - n] = sum / t;
        }
        return result;
    }

    /**
     * 剑指 Offer 43. 1～n 整数中 1 出现的次数
     */
    public int countDigitOne(int n) {
        int[] data = {1, 20, 300, 4000, 50000, 600000, 7000000, 80000000, 900000000};
        int[] m = {10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};
        int t = n;
        int count = 0;
        for (int j = 8; j >= 0; j--) {
            int q = t / m[j];
            int r = t % m[j];
            if (1 == q) {
                count += data[j];
                count += (r + 1);
            } else if (q >= 2) {
                count += q * data[j];
                count += m[j];
            }
            t = r;
        }
        return t >= 1 ? count + 1 : count;
    }

    /**
     * 剑指 Offer 38. 字符串的排列
     */
    public String[] permutation(String s) {
        if (s.length() == 0) return new String[0];
        HashSet<String> t = new HashSet<>();
        t.add(s.substring(0, 1));
        for (int i = 1; i < s.length(); i++) {
            HashSet<String> newT = new HashSet<>();
            for (String k : t) {
                for (int j = 0; j <= k.length(); j++) {
                    String left = k.substring(0, j);
                    String right = k.substring(j);
                    newT.add(left + s.charAt(i) + right);
                }
            }
            t = newT;
        }
        String[] result = new String[t.size()];
        return t.toArray(result);
    }

    /**
     * 剑指 Offer 49. 丑数
     */
    public int nthUglyNumber(int n) {
        int i = 0, j = 0, k = 0;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int s = 1; s < n; s++) {
            dp[s] = Math.min(Math.min(dp[i] * 2, dp[j] * 3), dp[k] * 5);
            if (dp[s] == dp[i] * 2) i += 1;
            if (dp[s] == dp[j] * 3) j += 1;
            if (dp[s] == dp[k] * 5) k += 1;
        }
        return dp[n - 1];
    }

    /**
     * 剑指 Offer 44. 数字序列中某一位的数字
     * 1*10 + 2*90 +3*900
     */
    public int findNthDigit(int n) {
        if (n <= 9) return n;
        int i = 1;
        while (n >= 0) {
            int t = i == 1 ? 10 : (int) (i * 9 * Math.pow(10, i - 1));
            if (n > t) {
                n -= t;
                i++;
            } else break;
        }
        int q = n / i;
        int r = n % i;
        if (q == 0) {
            return r == 0 ? 1 : 0;
        }
        int x = (int) (Math.pow(10, i - 1)) + q;
        return (x + "").charAt(r) - '0';
    }

    /**
     * 剑指 Offer 19. 正则表达式匹配
     */
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (isMatchSingle(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (isMatchSingle(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    private boolean isMatchSingle(String s, String p, int si, int pi) {
        if (si == 0) {
            return false;
        }
        if (p.charAt(pi - 1) == '.') {
            return true;
        }
        return s.charAt(si - 1) == p.charAt(pi - 1);
    }

    /**
     * 剑指 Offer 51. 数组中的逆序对
     */
    public int reversePairs(int[] nums) {
        int[] temp = new int[nums.length];
        return mergeSort(nums, 0, nums.length, temp);
    }

    private int mergeSort(int[] nums, int l, int r, int[] temp) {
        if (l + 1 >= r) return 0;
        int m = (l + r) / 2;
        int t = mergeSort(nums, l, m, temp);
        int s = mergeSort(nums, m, r, temp);
        int x = merge(nums, l, m, r, temp);
        return t + s + x;
    }

    private int merge(int[] result, int l, int m, int r, int[] temp) {
        System.arraycopy(result, 0, temp, 0, result.length);
        int i = l, j = m, k = l;
        int t = 0;
        while (i < m && j < r) {
            if (temp[i] <= temp[j]) {
                result[k] = temp[i];
                i++;
                k++;
            } else {
                result[k] = temp[j];
                j++;
                k++;
                t += (m - i);
            }
        }

        if (i != m) {
            System.arraycopy(temp, i, result, k, m - i);
        }
        if (j != r) {
            System.arraycopy(temp, j, result, k, r - j);
        }
        return t;
    }
}
