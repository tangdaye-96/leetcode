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
}
