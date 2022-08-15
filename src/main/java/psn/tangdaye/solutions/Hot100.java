package psn.tangdaye.solutions;

import psn.tangdaye.model.ListNode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Hot100 {

    /**
     * 1. 两数之和
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
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int left1 = 0, right1 = nums1.length;
        int left2 = 0, right2 = nums2.length;


    }
}