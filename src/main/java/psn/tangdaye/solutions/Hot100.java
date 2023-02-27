package psn.tangdaye.solutions;

import psn.tangdaye.model.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Hot100 {

    /**
     * 1. 两数之和
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
     * https://leetcode.cn/problems/add-two-numbers/?favorite=2cktkvj
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
        
    }


}