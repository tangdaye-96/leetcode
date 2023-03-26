package psn.tangdaye.solutions;

import psn.tangdaye.model.ListNode;
import psn.tangdaye.model.TreeNode;
import psn.tangdaye.structure.Heap;

import java.util.*;

public class Hot100 {

    /**
     * 1. 两数之和
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     * https://leetcode.cn/problems/two-sum/?favorite=2cktkvj
     */
    public int[] twoSum(int[] nums, int target) {
        int[][] h = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            h[i][0] = nums[i];
            h[i][1] = i;
        }
        Arrays.sort(h, Comparator.comparingInt(ints -> ints[0]));
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int k = -1;
            int l = i + 1, r = nums.length - 1;
            while (l <= r) {
                int m = (l + r) / 2;
                if (h[m][0] == target - h[i][0]) {
                    k = m;
                    break;
                } else if (h[m][0] < target - h[i][0]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            if (k > 0) {
                result[0] = h[i][1];
                result[1] = h[k][1];
                return result;
            }
        }
        return null;
    }

    /**
     * 2. 两数相加
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     * <p>
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * https://leetcode.cn/problems/add-two-numbers/?favorite=2cktkvj
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int x = 0;
        ListNode l1c = l1;
        ListNode l2c = l2;
        ListNode s = new ListNode(-1);
        ListNode pre = s;
        while (l1c != null && l2c != null) {
            int t = l1c.val + l2c.val + x;
            if (t >= 10) {
                pre.next = new ListNode(t - 10);
                x = 1;
            } else {
                pre.next = new ListNode(t);
                x = 0;
            }
            l1c = l1c.next;
            l2c = l2c.next;
            pre = pre.next;
        }
        while (l1c != null) {
            int t = l1c.val + x;
            if (t >= 10) {
                pre.next = new ListNode(t - 10);
                x = 1;
            } else {
                pre.next = new ListNode(t);
                x = 0;
            }
            l1c = l1c.next;
            pre = pre.next;
        }
        while (l2c != null) {
            int t = l2c.val + x;
            if (t >= 10) {
                pre.next = new ListNode(t - 10);
                x = 1;
            } else {
                pre.next = new ListNode(t);
                x = 0;
            }
            l2c = l2c.next;
            pre = pre.next;
        }
        if (x != 0) {
            pre.next = new ListNode(1);
        }
        return s.next;
    }

    /**
     * 3. 无重复字符的最长子串
     * https://leetcode.cn/problems/longest-substring-without-repeating-characters/
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;
        int i = 0, j = 1;
        Set<Character> cs = new HashSet<>();
        cs.add(s.charAt(i));
        int max = 1;
        while (j < s.length()) {
            char c = s.charAt(j);
            if (!cs.contains(c)) {
                j += 1;
                cs.add(c);
            } else {
                char x = s.charAt(i);
                cs.remove(x);
                i += 1;
            }
            max = Math.max(j - i, max);
        }
        return max;
    }

    /**
     * 4. 寻找两个正序数组的中位数
     * 二分法
     * https://leetcode.cn/problems/median-of-two-sorted-arrays/?favorite=2cktkvj
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int all = nums1.length + nums2.length;
        if (all % 2 == 0) {
            return (minK(nums1, nums2, all / 2) + minK(nums1, nums2, all / 2 + 1)) / 2;
        } else {
            return minK(nums1, nums2, (all + 1) / 2);
        }
    }

    private double minK(int[] nums1, int[] nums2, int k) {
        if (nums1.length == 0) return nums2[k - 1];
        if (nums2.length == 0) return nums1[k - 1];
        if (k == 1) return Math.min(nums1[0], nums2[0]);
        return minK(nums1, 0, nums2, 0, k);
    }

    private double minK(int[] nums1, int i, int[] nums2, int j, int k) {
        if (k == 1) {
            if (i >= nums1.length) return nums2[j];
            if (j >= nums2.length) return nums1[i];
            return nums1[i] <= nums2[j] ? nums1[i] : nums2[j];
        }
        if (i >= nums1.length) return nums2[j + k - 1];
        if (j >= nums2.length) return nums1[i + k - 1];

        int index1 = i - 1 + k / 2;
        int index2 = j - 1 + k / 2;
        if (index1 >= nums1.length) {
            index1 = nums1.length - 1;
            index2 = i + j + k - 2 - index1;
        }
        if (index2 >= nums2.length) {
            index2 = nums2.length - 1;
            index1 = i + j + k - 2 - index2;
        }
        int x1 = nums1[index1];
        int x2 = nums2[index2];
        if (x1 == x2) {
            if (index1 + index2 - i - j + 2 == k) return x1;
            return minK(nums1, index1 + 1, nums2, index2 + 1, 1);
        }
        if (x1 < x2) {
            // 第k小的数一定比x1大，所以nums1指针向后移动
            return minK(nums1, index1 + 1, nums2, j, k - (index1 - (i - 1)));
        } else {
            return minK(nums1, i, nums2, index2 + 1, k - (index2 - (j - 1)));
        }
    }

    /**
     * 5. 最长回文子串
     * Manacher算法 动态规划+中心扩展
     * https://leetcode.cn/problems/longest-palindromic-substring/?favorite=2cktkvj
     */
    public String longestPalindrome(String s) {
        char[] x = new char[s.length() * 2 + 1];
        for (int i = 0; i < x.length; i++) {
            if (i % 2 == 1) {
                x[i] = s.charAt(i / 2);
            } else {
                x[i] = '$';
            }
        }
        int[] dp = new int[x.length];
        int maxCenter = -1, maxLen = -1;

        int center = -1;
        int right = -1;
        for (int i = 0; i < x.length; i++) {
            if (right > i) {
                int anotherI = 2 * center - i;
                int minLen = Math.min(dp[anotherI], right - i);
                dp[i] = expand(x, i - minLen, i + minLen);
            } else {
                dp[i] = expand(x, i, i);
            }
            if (i + dp[i] > right) {
                center = i;
                right = i + dp[i];
            }
            if (maxLen < dp[i]) {
                maxCenter = i;
                maxLen = dp[i];
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = maxCenter - maxLen; i <= maxCenter + maxLen; i++) {
            if (x[i] != '$') sb.append(x[i]);
        }
        return sb.toString();
    }

    private int expand(char[] x, int left, int right) {
        while (left >= 0 && right < x.length && x[left] == x[right]) {
            left--;
            right++;
        }
        return (right - left) / 2 - 1;
    }

    /**
     * 10. 正则表达式匹配
     * 动态规划
     * https://leetcode.cn/problems/regular-expression-matching/?favorite=2cktkvj
     */
    public boolean isMatch(String s, String p) {
        s = "#" + s;
        p = "#" + p;
        // dp[i][j]  = isMatch(s[:i],p[:j])
        /*
         *     #    a     *     b
         * # true  false true  false
         * a false true  true  false
         * a false false true  false
         * a false false true  false
         * a false false true  false
         * b false false false true
         */
        boolean[][] dp = new boolean[s.length()][p.length()];
        dp[0][0] = true;
        // 初始化第一行
        for (int j = 1; j < p.length(); j++) {
            char cp = p.charAt(j);
            if (cp == '*') {
                dp[0][j] = dp[0][j - 2];
            } else {
                dp[0][j] = false;
            }
        }
        // 初始化第一列
        for (int i = 1; i < s.length(); i++) {
            dp[i][0] = false;
        }
        // 动态规划
        for (int i = 1; i < s.length(); i++) {
            for (int j = 1; j < p.length(); j++) {
                char cs = s.charAt(i);
                char cp = p.charAt(j);
                if (cp != '*') {
                    if (cs == cp || cp == '.') {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = false;
                    }
                } else {
                    char cpm = p.charAt(j - 1);
                    if (cs == cpm || cpm == '.') {
                        /*
                         * s = ($$$$)x
                         * p = ($$$$x)*
                         */
                        dp[i][j] = dp[i - 1][j] || dp[i - 1][j - 2] || dp[i][j - 2];
                    } else {
                        /*
                         * s = ($$$$)x
                         * p = ($$$$y)*
                         */
                        dp[i][j] = dp[i][j - 2];
                    }
                }
            }
        }
        return dp[s.length() - 1][p.length() - 1];


    }

    /**
     * 11. 盛最多水的容器
     * https://leetcode.cn/problems/container-with-most-water/?favorite=2cktkvj
     */
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max = 0;
        while (left < right) {
            if (height[left] <= height[right]) {
                int current = height[left] * (right - left);
                max = current > max ? current : max;
                left++;
            } else {
                int current = height[right] * (right - left);
                max = current > max ? current : max;
                right--;
            }
        }
        return max;
    }

    /**
     * 15. 三数之和
     * https://leetcode.cn/problems/3sum/?favorite=2cktkvj
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new LinkedList<>();
        HashMap<Integer, Integer> indexHashMap = new HashMap<>();
        for (int num : nums) {
            int count = 0;
            if (indexHashMap.containsKey(num)) {
                count = indexHashMap.get(num);
            }
            indexHashMap.put(num, count + 1);
        }
        if (indexHashMap.size() == 0) return result;
        Integer preA = null;
        for (int i = 0; i < nums.length && nums[i] <= 0; i++) {
            int a = nums[i];
            if (preA == null || preA != a) preA = a;
            else continue;
            Integer preC = null;
            for (int j = nums.length - 1; j > i + 1 && nums[j] >= 0; j--) {
                int c = nums[j];
                if (preC == null || preC != c) preC = c;
                else continue;
                int b = -a - c;
                if (b < a) {
                } else if (b == a) {
                    if (indexHashMap.getOrDefault(b, 0) > 1) result.add(Arrays.asList(a, b, c));
                } else if (a < b && b < c) {
                    if (indexHashMap.getOrDefault(b, 0) > 0) result.add(Arrays.asList(a, b, c));
                } else if (b == c) {
                    if (indexHashMap.getOrDefault(b, 0) > 1) result.add(Arrays.asList(a, b, c));
                } else {
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 17. 电话号码的字母组合
     * https://leetcode.cn/problems/letter-combinations-of-a-phone-number/?favorite=2cktkvj
     */
    public List<String> letterCombinations(String digits) {
        Map<Character, String> map = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        List<String> result = new LinkedList<>();
        if (digits.length() == 0) return result;
        char x = digits.charAt(0);
        String t = map.get(x);
        for (int i = 0; i < t.length(); i++) {
            result.add("" + t.charAt(i));
        }
        for (int i = 1; i < digits.length(); i++) {
            result = descartesMultiple(result, map.get(digits.charAt(i)).toCharArray());
        }
        return result;
    }

    private List<String> descartesMultiple(List<String> aL, char[] bL) {
        List<String> result = new LinkedList<>();
        for (Object a : aL) {
            for (Object b : bL) {
                result.add(String.valueOf(a) + String.valueOf(b));
            }
        }
        return result;
    }

    /**
     * 19. 删除链表的倒数第 N 个结点
     * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode left = head;
        ListNode right = head;
        ListNode pre = new ListNode(-1);
        pre.next = head;

        for (int i = 0; i < n; i++) {
            right = right.next;
        }
        if (right == null) {
            // 删除头结点
            return head.next;
        }
        while (right != null) {
            right = right.next;
            left = left.next;
            pre = pre.next;
        }
        pre.next = left.next;
        left.next = null;
        return head;
    }

    /**
     * 20. 有效的括号
     * https://leetcode.cn/problems/valid-parentheses/?favorite=2cktkvj
     */
    public boolean isValid(String s) {
        LinkedList<Character> l = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') l.add(c);
            else {
                if (l.size() == 0) return false;
                char x = l.removeLast();
                if ((x == '(' && c == ')') || (x == '[' && c == ']') || (x == '{' && c == '}')) continue;
                return false;
            }
        }
        return l.size() == 0;
    }

    /**
     * 21. 合并两个有序链表
     * https://leetcode.cn/problems/merge-two-sorted-lists/?favorite=2cktkvj
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode current1 = list1;
        ListNode current2 = list2;
        ListNode pre = new ListNode(Integer.MIN_VALUE);
        ListNode current = pre;
        while (current1 != null && current2 != null) {
            if (current1.val < current2.val) {
                current.next = current1;
                current1 = current1.next;
            } else {
                current.next = current2;
                current2 = current2.next;
            }
            current = current.next;
        }
        if (current1 != null) {
            current.next = current1;
        } else {
            current.next = current2;
        }
        return pre.next;
    }

    /**
     * 22. 括号生成
     * https://leetcode.cn/problems/generate-parentheses/?favorite=2cktkvj
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        int numsLP = 0;
        String t = "";
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < 2 * n; i++) {
            for (String preS : result) {
                String l1 = null, l2 = null;
                if (numsLP < n) {
                    l1 = nextParentheses(preS, '(', stack);
                    l2 = nextParentheses(preS, ')', stack);
                } else {
                    l2 = nextParentheses(preS, ')', stack);
                }
                if (l1 != null) {
                }
            }

        }
        return null;
    }

    private String nextParentheses(String current, char parentheses, LinkedList<Character> stack) {
        if (parentheses == '(') {
            stack.add(parentheses);
            return current + parentheses;
        } else {
            if (stack.isEmpty()) return null;
            char c = stack.removeLast();
            if (c != '(') return null;
            return current + parentheses;
        }
    }

    /**
     * 23. 合并K个升序链表
     * https://leetcode.cn/problems/merge-k-sorted-lists/?favorite=2cktkvj
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 1) {
            return lists[0];
        }
        ListNode pre = new ListNode(Integer.MIN_VALUE);
        // 头应该用（最小）堆【优先队列】，不应该用列表，否则会重复比较
        Heap<ListNode> heap = new Heap<>(lists, true);
        ListNode current = pre;

        while (heap.size() != 0) {
            ListNode head = heap.top();
            current.next = head;
            current = current.next;
            head = head.next;
            if (head == null) {
                heap.pop();
            } else {
                heap.setTop(head);
            }
        }

        return pre.next;
    }

    /**
     * 31. 下一个排列
     * https://leetcode.cn/problems/next-permutation/?favorite=2cktkvj
     */
    public void nextPermutation(int[] nums) {
        if (nums.length == 1) return;
        if (nums.length == 2) {
            swap(nums, 0, 1);
            return;
        }
        //1. 从后向前，找到破坏升序的第一个数字（等于不是破坏）
        int index = nums.length - 2;
        for (; index >= 0; index--) {
            int next = index + 1;
            if (nums[index] < nums[next]) break;
        }
        if (index < 0) {
            // 如果没有破坏反向升序的，说明原序列是完全降序，revert
            revert(nums, 0, nums.length - 1);
        } else {
            // 如果有破坏升序的 index， 找到后面最小的比它大的，交换，然后revert
            // x x x 5 9 8 6 5 4 3 2 1 ==> x x x 6 9 8 5 5 4 3 2 1 ==> x x x 6 1 2 3 4 5 5 8 9
            int i = index + 1;
            while (i < nums.length && nums[i] > nums[index]) i++;
            i = i - 1;
            swap(nums, index, i);
            revert(nums, index + 2, nums.length - 1);
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
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * https://leetcode.cn/problems/longest-valid-parentheses/?favorite=2cktkvj
     * 32. 最长有效括号
     */
    public int longestValidParentheses(String s) {
        int left = 0, right = s.length();
        while (left < s.length() && s.charAt(left) == ')') left++;
        while (right > 0 && s.charAt(right - 1) == '(') right++;
        s = s.substring(left, right);
        if (s.length() == 0) return 0;
        int[] dp = new int[s.length()]; // dp[i] 是s[0:i]的最长串
        dp[0] = 0;
        LinkedList<LPStruct> stack = new LinkedList<>();// 左括号右边的成对的数量
        stack.add(new LPStruct(0));
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                dp[i] = dp[i - 1];
                stack.add(new LPStruct(i));
            } else {
                if (stack.size() == 0) {
                    dp[i] = dp[i - 1];
                } else {
                    stack.forEach(k -> k.value++);
                    LPStruct x = stack.removeLast();
                    if (stack.size() == 0) {
                        dp[i] = dp[x.index] + x.value;
                    } else {
                        LPStruct y = stack.getLast();
                        dp[i] = Math.max(dp[i - 1], y.value);
                    }
                }
            }
        }
        return dp[s.length() - 1] * 2;
    }

    private static class LPStruct {
        int index;
        int value;

        LPStruct(int index) {
            this.index = index;
            this.value = 0;
        }
    }

    /**
     * https://leetcode.cn/problems/search-in-rotated-sorted-array/?favorite=2cktkvj
     * 33. 搜索旋转排序数组
     */
    public int search(int[] nums, int target) {
        if (nums.length == 0) return -1;
        if (nums.length == 1) return target == nums[0] ? 0 : -1;

        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (left == mid) break;
            if (nums[left] < nums[mid]) {
                // 前半有序
                left = mid;
            } else {
                right = mid;
            }
        }
        // left和right最大差1
        if (nums[left] < nums[right]) left = right;
        // left是最大值
        if (target > nums[left]) return -1;
        if (target == nums[left]) return left;
        if (target > nums[0]) {
            int x = Arrays.binarySearch(nums, 0, left, target);
            return x >= 0 ? x : -1;
        }
        if (target == nums[0]) return 0;
        if (left == nums.length - 1) return -1;
        int x = Arrays.binarySearch(nums, left + 1, nums.length, target);
        return x >= 0 ? x : -1;

    }

    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     * https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/?favorite=2cktkvj
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) return new int[]{-1, -1};
        if (nums[0] > target || nums[nums.length - 1] < target) return new int[]{-1, -1};
        int left = 0, right = nums.length;
        int mid = (left + right) / 2;
        while (left < right) {
            mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            } else {
                // 两边扩展
                break;
            }
        }
        if (nums[mid] == target) {
            // nums[left] <= target nums[right-1] >= target
            int first = searchFirst(nums, left, mid, target);
            int last = searchLast(nums, mid, right - 1, target);
            return new int[]{first, last};
        }
        return new int[]{-1, -1};
    }

    // 保持nums[j] == target;
    private int searchFirst(int[] nums, int i, int j, int target) {
        if (i + 1 == j) return nums[i] == target ? i : j;
        if (nums[i] == target) return i;
        int mid = (i + j) / 2;
        if (nums[mid] < target) return searchFirst(nums, mid + 1, j, target);
        else return searchFirst(nums, i, mid, target);
    }

    // 保持nums[i] == target;
    private int searchLast(int[] nums, int i, int j, int target) {
        if (i + 1 == j) return nums[j] == target ? j : i;
        if (nums[j] == target) return j;
        int mid = (i + j) / 2;
        if (nums[mid] > target) return searchLast(nums, i, mid - 1, target);
        else return searchLast(nums, mid, j, target);
    }

    /**
     * 39. 组合总和
     * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
     * https://leetcode.cn/problems/combination-sum/?favorite=2cktkvj
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>>[] dp = new ArrayList[target + 1];
        dp[0] = new ArrayList<>();
        for (int sum = 1; sum <= target; sum++) {
            dp[sum] = new ArrayList<>();
            for (int c : candidates) {
                if (c > sum) break;
                else if (c == sum) dp[sum].add(Collections.singletonList(sum));
                else {
                    List<List<Integer>> once = dp[sum - c];
                    for (List<Integer> k : once) {
                        if (k.get(k.size() - 1) <= c) {
                            List<Integer> newK = new ArrayList<>(k);
                            newK.add(c);
                            dp[sum].add(newK);
                        }
                    }
                }
            }
        }
        return dp[target];
    }

    /**
     * 42. 接雨水
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     * https://leetcode.cn/problems/trapping-rain-water/?favorite=2cktkvj
     */
    public int trap(int[] height) {
        // 递减栈
        Stack<int[]> stack = new Stack<>();
        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            int h = height[i];
            if (stack.isEmpty() || h <= stack.peek()[0]) {
                stack.push(new int[]{h, i});
                continue;
            }
            while (!stack.isEmpty()) {
                int[] k = stack.peek();
                if (k[0] >= h) break;
                stack.pop();
                if (stack.isEmpty()) break;
                int[] top = stack.peek();
                sum += (Math.min(top[0], h) - k[0]) * (i - top[1] - 1);
            }
            stack.push(new int[]{h, i});
        }
        return sum;
    }

    /**
     * 46. 全排列
     * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
     * https://leetcode.cn/problems/permutations/?favorite=2cktkvj
     */
    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new LinkedList<>();
        for (int n : nums) {
            if (result.isEmpty()) {
                result.add(new LinkedList<Integer>() {{
                    add(n);
                }});
            } else {
                List<List<Integer>> temp = new LinkedList<>();
                for (List<Integer> array : result) {
                    for (int i = 1; i <= array.size(); i++) {
                        List<Integer> next = new LinkedList<>(array);
                        next.add(i, n);
                        temp.add(next);
                    }
                    array.add(0, n);
                }
                result.addAll(temp);
            }
        }
        return result;
    }

    /**
     * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
     * 48. 旋转图像
     * https://leetcode.cn/problems/rotate-image/?favorite=2cktkvj
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
     * 49. 字母异位词分组
     * https://leetcode.cn/problems/group-anagrams/?favorite=2cktkvj
     * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        // 找个hashcode，把【字母异位词】映射到同一个int上
        Map<String, List<String>> a = new HashMap<>();
        int[] alphabet = new int[26];
        for (String s : strs) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                alphabet[i] = 0;
            }
            for (int i = 0; i < s.length(); i++) {
                alphabet[s.charAt(i) - 'a'] += 1;
            }
            for (int i = 0; i < 26; i++) {
                if (alphabet[i] > 0) {
                    char c = (char) (i + 'a');
                    sb.append(alphabet[i]).append(c);
                }
            }
            String x = sb.toString();
            if (a.get(x) == null) {
                a.put(x, new ArrayList<String>() {{
                    add(s);
                }});
            } else {
                a.get(x).add(s);
            }
        }
        List<List<String>> result = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : a.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }

    /**
     * 53. 最大子数组和
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * https://leetcode.cn/problems/maximum-subarray/
     */
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        int maxSum = nums[0];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] >= 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
            maxSum = Math.max(maxSum, dp[i]);
        }
        return maxSum;
    }

    /**
     * 55. 跳跃游戏
     * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标。数组中的每个元素代表你在该位置可以跳跃的最大长度。判断你是否能够到达最后一个下标。
     * https://leetcode.cn/problems/jump-game/?favorite=2cktkvj
     */
    public boolean canJump(int[] nums) {
        int[] dp = new int[nums.length];
        int n = nums.length;
        // dp[i]表示，从i到n-1，最前一个能到达nums[n-1]的下标
        dp[n - 1] = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            if (i + nums[i] >= dp[i + 1]) {
                dp[i] = i;
            } else {
                dp[i] = dp[i + 1];
            }
        }
        return dp[0] == 0;
    }

    /**
     * 56. 合并区间
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
     * https://leetcode.cn/problems/merge-intervals/?favorite=2cktkvj
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<int[]> result = new ArrayList<>();
        for (int[] interval : intervals) {
            if (result.isEmpty()) result.add(interval);
            else {
                int[] x = result.get(result.size() - 1);
                if (interval[0] <= x[1]) {
                    x[1] = Math.max(interval[1], x[1]);
                } else {
                    result.add(interval);
                }
            }
        }
        int[][] x = new int[result.size()][2];
        for (int i = 0; i < result.size(); i++) {
            x[i] = result.get(i);
        }
        return x;
    }

    /**
     * 62. 不同路径
     * <p>
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
     * 问总共有多少条不同的路径？
     * <p>
     * https://leetcode.cn/problems/unique-paths/?favorite=2cktkvj
     */
    public int uniquePaths(int m, int n) {
        // 答案是C(m-1, m+n-2);
        int s = m - 1;
        int t = n - 1;
        int k = s > t ? t : s;
        long res = 1;
        for (int i = 0; i < k; i++) {
            res = res * (s + t - i);
            res = res / (i + 1);
        }
        return (int) res;
    }

    /**
     * 64. 最小路径和
     * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     * https://leetcode.cn/problems/minimum-path-sum/?favorite=2cktkvj
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) dp[i][j] = grid[i][j];
                else if (i == 0) dp[i][j] = dp[i][j - 1] + grid[i][j];
                else if (j == 0) dp[i][j] = dp[i - 1][j] + grid[i][j];
                else dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 70. 爬楼梯
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * https://leetcode.cn/problems/climbing-stairs/?favorite=2cktkvj
     */
    public int climbStairs(int n) {
        if (n == 1) return 1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 72. 编辑距离
     * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
     * 你可以对一个单词进行如下三种操作：
     * <p>
     * 插入一个字符
     * 删除一个字符
     * 替换一个字符
     * https://leetcode.cn/problems/edit-distance/?favorite=2cktkvj
     */
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        int m = word1.length();
        int n = word2.length();
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char c1 = word1.charAt(i - 1);
                char c2 = word2.charAt(j - 1);
                if (c1 == c2) dp[i][j] = dp[i - 1][j - 1];
                else dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
            }
        }
        return dp[m][n];
    }

    /**
     * 75. 颜色分类
     * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
     * https://leetcode.cn/problems/sort-colors/?favorite=2cktkvj
     */
    public void sortColors(int[] nums) {
        // 计数排序
        int r = 0;
        int w = 0;
        for (int i : nums) {
            if (i == 0) r += 1;
            if (i == 1) w += 1;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i < r) nums[i] = 0;
            else if (i < r + w) nums[i] = 1;
            else nums[i] = 2;
        }
    }

    /**
     * 76. 最小覆盖子串
     * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     * https://leetcode.cn/problems/minimum-window-substring/?favorite=2cktkvj
     */
    public String minWindow(String s, String t) {
        Map<Character, Integer> dic = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            int x = dic.getOrDefault(t.charAt(i), 0);
            dic.put(t.charAt(i), x + 1);
        }
        List<Integer> indexList = new ArrayList<>();
        int k = 0;
        int i = -1, j = 0;
        String minStr = "";
        while (j < s.length()) {
            char jc = s.charAt(j);
            if (dic.containsKey(jc)) {
                if (i < 0) i = j;
                indexList.add(j); // 匹配到就塞进去
                int x = dic.get(jc);
                dic.put(jc, x - 1); // 先-1;

                if (minStr.length() == 0) {
                    boolean done = true;
                    for (Map.Entry<Character, Integer> entry : dic.entrySet()) {
                        if (entry.getValue() > 0) {
                            done = false;
                            break;
                        }
                    }
                    if (done) minStr = s.substring(i, j + 1);
                }

                // 这里处理i
                if (dic.get(jc) < 0) {
                    while (true) {
                        i = indexList.get(k);
                        char ic = s.charAt(i);
                        if (dic.get(ic) < 0) {
                            int y = dic.get(ic);
                            dic.put(ic, y + 1);
                            k++;
                        } else {
                            break;
                        }
                    }
                }
            }
            minStr = j + 1 - i <= minStr.length() ? s.substring(i, j + 1) : minStr;
            j++;
        }
        return minStr;
    }

    /**
     * 78. 子集
     * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     * https://leetcode.cn/problems/subsets/?favorite=2cktkvj
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        for (int a : nums) {
            List<List<Integer>> needAdd = new ArrayList<>();
            for (List<Integer> set : result) {
                List<Integer> newSet = new ArrayList<>(set);
                newSet.add(a);
                needAdd.add(newSet);
            }
            result.addAll(needAdd);
        }
        return result;
    }

    /**
     * 79. 单词搜索
     * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
     * https://leetcode.cn/problems/word-search/?favorite=2cktkvj
     */
    public boolean exist(char[][] board, String word) {
        boolean[][] used = new boolean[board.length][board[0].length];
        int k = 0;
        for (int s = 0; s < board.length; s++) {
            for (int t = 0; t < board[0].length; t++) {
                boolean x = doExist(board, word, used, s, t, k);
                if (x) return true;
            }
        }
        return false;
    }

    private boolean doExist(char[][] board, String word, boolean[][] used, int s, int t, int k) {
        if (k == word.length()) return true;
        if (s >= board.length || t >= board[0].length || s < 0 || t < 0) return false;
        if (used[s][t]) return false;
        if (!(board[s][t] == word.charAt(k))) {
            return false;
        }
        used[s][t] = true;
        boolean x = doExist(board, word, used, s, t + 1, k + 1) || doExist(board, word, used, s + 1, t, k + 1) || doExist(board, word, used, s - 1, t, k + 1) || doExist(board, word, used, s, t - 1, k + 1);
        if (x) return true;
        else used[s][t] = false;
        return false;
    }

    /**
     * 84. 柱状图中最大的矩形
     * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
     * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
     * https://leetcode.cn/problems/largest-rectangle-in-histogram/?favorite=2cktkvj
     */
    public int largestRectangleArea(int[] heights) {
        // 右边最远比自己小[0]，左边最远比自己小[1]，记录一下，最后求最大值
        int[][] data = new int[heights.length][2];
        Stack<int[]> stack = new Stack<>();
        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < heights.length; i++) {
            int h = heights[i];
            while (!stack.isEmpty() && h < stack.peek()[0]) {
                int[] t = stack.pop();
                data[t[1]][0] = i;
            }
            stack.push(new int[]{h, i});
        }
        while (!stack.isEmpty()) {
            int[] t = stack.pop();
            data[t[1]][0] = heights.length;
        }
        for (int i = heights.length - 1; i >= 0; i--) {
            int h = heights[i];
            while (!stack.isEmpty() && h < stack.peek()[0]) {
                int[] t = stack.pop();
                data[t[1]][1] = i;
            }
            stack.push(new int[]{h, i});
        }
        while (!stack.isEmpty()) {
            int[] t = stack.pop();
            data[t[1]][1] = -1;
        }
        for (int i = 0; i < data.length; i++) {
            maxArea = Math.max(heights[i] * (data[i][0] - data[i][1] - 1), maxArea);
        }
        return maxArea;
    }


    /**
     * 85. 最大矩形
     * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
     * https://leetcode.cn/problems/maximal-rectangle/?favorite=2cktkvj
     */
    public int maximalRectangle(char[][] matrix) {
        int[][] up = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '0') {
                    up[i][j] = 0;
                } else {
                    if (i == 0 && j == 0) {
                        up[i][j] = 1;
                    } else if (i == 0) {
                        up[i][j] = 1;
                    } else {
                        up[i][j] = up[i - 1][j] + 1;
                    }
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for (int[] heights : up) {
            int maxI = largestRectangleArea(heights);
            max = Math.max(maxI, max);
        }

        return max;
    }

    /**
     * 94. 二叉树的中序遍历
     * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
     * https://leetcode.cn/problems/binary-tree-inorder-traversal/?favorite=2cktkvj
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> a = new ArrayList<>();
        doInorderTraversal(root, a);
        return a;
    }

    private void doInorderTraversal(TreeNode root, List<Integer> a) {
        if (root == null) return;
        doInorderTraversal(root.left, a);
        a.add(root.val);
        doInorderTraversal(root.right, a);
    }

    /**
     * 96. 不同的二叉搜索树
     * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
     * https://leetcode.cn/problems/unique-binary-search-trees/?favorite=2cktkvj
     */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i] = 0;
            for (int j = 0; j <= i - 1; j++) {
                dp[i] += dp[j] * dp[i - 1 - j];
            }
        }
        return dp[n];
    }

    /**
     * 98. 验证二叉搜索树
     * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
     * https://leetcode.cn/problems/validate-binary-search-tree/?favorite=2cktkvj
     */

    public boolean isValidBST(TreeNode root) {
        List<Integer> a = inorderTraversal(root);
        for (int i = 0; i < a.size() - 1; i++) {
            if (a.get(i) >= a.get(i + 1)) return false;
        }
        return true;
    }

    /**
     * 101. 对称二叉树
     * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
     * https://leetcode.cn/problems/symmetric-tree/?favorite=2cktkvj
     */
    public boolean isSymmetric(TreeNode root) {
        return doSymmetric(root.left, root.right);
    }

    public boolean doSymmetric(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null) return false;
        if (b == null) return false;
        return a.val == b.val && doSymmetric(a.left, b.right) && doSymmetric(a.right, b.left);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<TreeNode> currentLevel = new ArrayList<>() {{
            add(root);
        }};
        List<List<Integer>> result = new ArrayList<>();
        while (true) {
            List<Integer> currentResult = new ArrayList<>();
            List<TreeNode> nextLevel = new ArrayList<>();
            for (TreeNode current : currentLevel) {
                if (current != null) {
                    currentResult.add(current.val);
                    nextLevel.add(current.left);
                    nextLevel.add(current.right);
                }
            }
            if (!currentResult.isEmpty()) result.add(currentResult);
            if (nextLevel.isEmpty()) break;
            currentLevel = nextLevel;
        }
        return result;
    }

    /**
     * 104. 二叉树的最大深度
     * 给定一个二叉树，找出其最大深度。
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * https://leetcode.cn/problems/maximum-depth-of-binary-tree/?favorite=2cktkvj
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 105. 从前序与中序遍历序列构造二叉树
     * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
     * https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/?favorite=2cktkvj
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> preOrderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            preOrderMap.put(preorder[i], i);
        }
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return generateRoot(preorder, inorderMap, 0, 0, preorder.length);
    }

    private TreeNode generateRoot(int[] preorder, Map<Integer, Integer> inorderMap, int preStart, int inStart, int length) {
        if (length <= 0 || preStart < 0 || preStart + length > preorder.length) return null;
        TreeNode root = new TreeNode(preorder[preStart]);
        int inIndex = inorderMap.get(root.val); //root在中序遍历中的位置
        int leftInStart = inStart;
        int leftPreStart = preStart + 1;
        int leftLength = inIndex - inStart;

        int rightInStart = inIndex + 1;
        int rightPreStart = preStart + leftLength + 1;
        int rightLength = length - 1 - leftLength;

        root.left = generateRoot(preorder, inorderMap, leftPreStart, leftInStart, leftLength);
        root.right = generateRoot(preorder, inorderMap, rightPreStart, rightInStart, rightLength);

        return root;
    }

    /**
     * 114. 二叉树展开为链表
     * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
     * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
     * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
     * <p>
     * https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/?favorite=2cktkvj
     */
    public void flatten(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode current = new TreeNode(-1);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                current.left = null;
                current.right = node;
                current = current.right;
                stack.push(node.right);
                stack.push(node.left);
            }
        }
    }

    /**
     * 121. 买卖股票的最佳时机
     * 给定一个数组 prices ，它的第i个元素prices[i] 表示一支给定股票第 i 天的价格。
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/?favorite=2cktkvj
     */
    public int maxProfit(int[] prices) {
        int sale = prices[0];
        int profit = 0;
        int max = 0;
        for (int price : prices) {
            if (price >= sale) {
                profit += price - sale;
                sale = price;
            } else if (price < (sale - profit)) {
                profit = 0;
                sale = price;
            }
            max = Math.max(max, profit);
        }
        return max;
    }

    /**
     * 124. 二叉树中的最大路径和
     * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
     * 路径和 是路径中各节点值的总和。
     * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
     * <p>
     * https://leetcode.cn/problems/binary-tree-maximum-path-sum/?favorite=2cktkvj
     */
    public int maxPathSum(TreeNode root) {

    }

}