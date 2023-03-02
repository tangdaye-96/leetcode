package psn.tangdaye.solutiosn;

import org.junit.Assert;
import org.junit.Test;
import psn.tangdaye.model.ListNode;
import psn.tangdaye.solutions.Hot100;

import java.util.Arrays;

public class Hot100Test {
    Hot100 hot = new Hot100();

    @Test
    public void testTwoSum() {
        int[] t = {3, 2, 3};
        int[] result = hot.twoSum(t, 6);
        Assert.assertEquals(0, result[0]);
        Assert.assertEquals(2, result[1]);
    }

    @Test
    public void testAddTwoNumbers() {
        int[] array1 = {9, 9, 9, 9, 9, 9, 9};
        int[] array2 = {9, 9, 9, 9};
        ListNode l1 = ListNode.fromArray(array1);
        ListNode l2 = ListNode.fromArray(array2);
        ListNode k = hot.addTwoNumbers(l1, l2);
        Assert.assertEquals("[8, 9, 9, 9, 0, 0, 0, 1]", k.toString());
    }

    @Test
    public void testLengthOfLongestSubstring() {
        String s = "pwwkew";
        Assert.assertEquals(3, hot.lengthOfLongestSubstring(s));
    }

    @Test
    public void testFindMedianSortedArrays() {
//        int[] nums1 = {1, 2, 3, 4, 5, 6};
//        int[] nums2 = {2, 4, 6, 8, 10, 12};
//        Assert.assertEquals(hot.findMedianSortedArrays(nums1, nums2), 4.5, 0.0);

        int[] nums3 = {2, 3, 5, 6};
        int[] nums4 = {1, 4};
        System.out.println(hot.findMedianSortedArrays(nums3, nums4));
    }

    @Test
    public void testLongestPalindrome() {
        String s = "aabaab";
        Assert.assertEquals(hot.longestPalindrome(s), "aabaa");
    }

    @Test
    public void testIsMatch() {
        String[] s = {"aa", "asdfasdfasdfasdfasdf", "aaca"};
        String[] p = {"a*", ".*asdf.*sdf.*df.*f.*", "ab*a*c*a"};
        for (int i = 0; i < s.length; i++) {
            Assert.assertTrue(hot.isMatch(s[i], p[i]));

        }
        String[] s2 = {"a", "sdf", "ff"};
        String[] p2 = {"aaa*", "d*s*f", "f"};
        for (int i = 0; i < s2.length; i++) {
            Assert.assertTrue(!hot.isMatch(s2[i], p2[i]));
        }
    }

    @Test
    public void testMaxArea() {
        int[] height = {1, 8, 6, 1000, 1000, 6, 3, 8, 7};
        Assert.assertEquals(hot.maxArea(height), 1000);
    }

    @Test
    public void testThreeSum() {
        int[] x = {3, 0, -2, -1, 1, 2};
        Assert.assertEquals(3, hot.threeSum(x).size());
    }

    @Test
    public void testLetterCombinations() {
        String digits = "237";
        Assert.assertEquals(36, hot.letterCombinations(digits).size());
    }


    @Test
    public void testRemoveNthFromEnd() {
        int[] array = {1, 2};
        ListNode head = ListNode.fromArray(array);
        Assert.assertEquals(String.valueOf(hot.removeNthFromEnd(head, 2)), "[2]");
    }

    @Test
    public void testIsValid() {
        String s = "([{{{}}}])";
        Assert.assertTrue(hot.isValid(s));
    }

    @Test
    public void testMergeTwoLists() {
        int[] array1 = {1, 3, 5, 7, 9};
        int[] array2 = {2, 4, 6, 8, 10, 12, 14};
        ListNode head1 = ListNode.fromArray(array1);
        ListNode head2 = ListNode.fromArray(array2);
        System.out.println(hot.mergeTwoLists(head1, head2));
    }

    @Test
    public void testMergeKLists() {
        int[] array1 = {1, 4, 7, 10, 20};
        int[] array2 = {2, 5, 8, 11, 14, 17, 20};
        int[] array3 = {3, 6, 9, 12, 15, 18, 21, 24};
        ListNode head1 = ListNode.fromArray(array1);
        ListNode head2 = ListNode.fromArray(array2);
        ListNode head3 = ListNode.fromArray(array3);
        ListNode[] heads = {head1, head2, head3};
        Assert.assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 15, 17, 18, 20, 20, 21, 24]", hot.mergeKLists(heads).toString());
    }

    @Test
    public void testNextPermutation() {
        int[] array = {3, 5, 3, 3, 3, 2, 1};
        hot.nextPermutation(array);
        Assert.assertEquals("[5, 3, 1, 2, 3, 3, 3]", Arrays.toString(array));

        int[] array2 = {3, 5, 3, 3, 3, 2, 1, 7};
        hot.nextPermutation(array2);
        Assert.assertEquals("[3, 5, 3, 3, 3, 2, 7, 1]", Arrays.toString(array2));

        int[] array3 = {5, 1, 1};
        hot.nextPermutation(array3);
        Assert.assertEquals("[1, 1, 5]", Arrays.toString(array3));
    }

    @Test
    public void testLongestValidParentheses() {
        String s = "()()((()()()()()";
        Assert.assertEquals(10, hot.longestValidParentheses(s));
    }

}
