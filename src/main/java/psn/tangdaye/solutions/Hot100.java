package psn.tangdaye.solutions;

import psn.tangdaye.model.NFA;
import psn.tangdaye.model.ListNode;

import java.util.*;

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

    public boolean isMatch(String s, String p) {
        NFA d = new NFA(p);
        return d.match(s);
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


}