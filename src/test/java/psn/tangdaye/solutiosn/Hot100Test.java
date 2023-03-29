package psn.tangdaye.solutiosn;

import org.junit.Assert;
import org.junit.Test;
import psn.tangdaye.model.LRUCache;
import psn.tangdaye.model.ListNode;
import psn.tangdaye.model.TreeNode;
import psn.tangdaye.solutions.Hot100;
import psn.tangdaye.tool.Tools;

import java.util.Arrays;
import java.util.List;

import static psn.tangdaye.tool.Tools.beauty2DArray;

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
        int[] nums1 = {1, 2, 3, 4, 5, 6};
        int[] nums2 = {2, 4, 6, 8, 10, 12};
        Assert.assertEquals(hot.findMedianSortedArrays(nums1, nums2), 4.5, 0.0);
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
        Assert.assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 14]", hot.mergeTwoLists(head1, head2).toString());
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
        String u = "()()((()()()()())";
        Assert.assertEquals(12, hot.longestValidParentheses(u));
        String z = "()()((()()()()())()";
        Assert.assertEquals(14, hot.longestValidParentheses(z));
        String v = "(((())))";
        Assert.assertEquals(8, hot.longestValidParentheses(v));
    }

    @Test
    public void testSearch() {
        int[] array = {6, 7, 8, 9, 1, 2, 3, 4, 5};
        Assert.assertEquals(2, hot.search(array, 8));
        Assert.assertEquals(-1, hot.search(array, 0));
        int[] array2 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Assert.assertEquals(0, hot.search(array2, 1));
        Assert.assertEquals(-1, hot.search(array2, 0));
        int[] array3 = {3, 1};
        Assert.assertEquals(1, hot.search(array3, 1));
    }

    @Test
    public void testSearchRange() {
        int[] array1 = {1, 1, 1, 1, 1, 6, 7, 8, 9, 9, 9, 9, 9};
        Assert.assertEquals("[0, 4]", Arrays.toString(hot.searchRange(array1, 1)));
        Assert.assertEquals("[8, 12]", Arrays.toString(hot.searchRange(array1, 9)));

        int[] array2 = {5, 7, 7, 8, 8, 10};
        Assert.assertEquals("[3, 4]", Arrays.toString(hot.searchRange(array2, 8)));
    }

    @Test
    public void testCombinationSum() {
        int[] array = {2, 3, 6, 7};
        int target = 7;
        Assert.assertEquals("[[2, 2, 3], [7]]", beauty2DArray(hot.combinationSum(array, target)));
    }

    @Test
    public void testTrap() {
        int[] height1 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] height2 = {4, 2, 0, 3, 2, 5};
        Assert.assertEquals(hot.trap(height1), 6);
        Assert.assertEquals(hot.trap(height2), 9);
    }

    @Test
    public void testPermute() {
        int[] h = {1, 2, 3, 4, 5};
        Assert.assertEquals(120, hot.permute(h).size());
    }

    @Test
    public void testRotate() {
        int[][] matrix = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        hot.rotate(matrix);
        Assert.assertEquals("[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]", Tools.beauty2DArray(matrix));
    }

    @Test
    public void testGroupAnagrams() {
        String[] array = {"eat", "tea", "tan", "ate", "nat", "bat"};
        Assert.assertEquals(3, hot.groupAnagrams(array).size());
    }

    @Test
    public void testMaxSubArray() {
        int[] array = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        Assert.assertEquals(6, hot.maxSubArray(array));
    }

    @Test
    public void testCanJump() {
        int[] array1 = {2, 3, 1, 1, 4};
        Assert.assertTrue(hot.canJump(array1));

        int[] array2 = {3, 2, 5, 0, 7, 0, 0, 0, 0, 4};
        Assert.assertTrue(hot.canJump(array2));

        int[] array3 = {3, 2, 1, 0, 4};
        Assert.assertTrue(!hot.canJump(array3));
    }

    @Test
    public void testMerge() {
        int[][] array1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        Assert.assertEquals("[[1,6],[8,10],[15,18]]", Tools.beauty2DArray(hot.merge(array1)));

        int[][] array2 = {{1, 4}, {4, 5}};
        Assert.assertEquals("[[1,5]]", Tools.beauty2DArray(hot.merge(array2)));
    }

    @Test
    public void testUniquePaths() {
        Assert.assertEquals(1916797311, hot.uniquePaths(9, 51));
    }

    @Test
    public void testMinPathSum() {
        int[][] array = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        Assert.assertEquals(7, hot.minPathSum(array));
    }

    @Test
    public void testClimbStairs() {
        Assert.assertEquals(3, hot.climbStairs(3));
    }

    @Test
    public void testMinDistance() {
        Assert.assertEquals(3, hot.minDistance("horse", "ros"));
        Assert.assertEquals(5, hot.minDistance("intention", "execution"));
    }

    @Test
    public void sortColors() {
        int[] array = {2, 0, 2, 1, 1, 0};
        hot.sortColors(array);
        Assert.assertEquals("[0, 0, 1, 1, 2, 2]", Arrays.toString(array));
    }

    @Test
    public void testMinWindow() {
        String s = "ADOBECODEBANC", t = "ABC";
        Assert.assertEquals("BANC", hot.minWindow(s, t));

        String s1 = "abcdefxabcdyefghyzzjklmnyo", t1 = "xyz";
        Assert.assertEquals("xabcdyefghyz", hot.minWindow(s1, t1));

        String s2 = "xyzabcdefgx", t2 = "xyz";
        Assert.assertEquals("xyz", hot.minWindow(s2, t2));
    }

    @Test
    public void testSubSets() {
        int[] nums = {1, 2, 3};
        Assert.assertEquals(8, hot.subsets(nums).size());
    }

    @Test
    public void testExist() {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";
        String word2 = "ABCCEDASF";
        String word3 = "ABCCEDASFC";
        String word4 = "SEE";
        Assert.assertTrue(hot.exist(board, word));
        Assert.assertTrue(hot.exist(board, word2));
        Assert.assertFalse(hot.exist(board, word3));
        Assert.assertTrue(hot.exist(board, word4));

    }

    @Test
    public void testLargestRectangleArea() {
        int[] heights = {2, 1, 5, 6, 2, 3};
        Assert.assertEquals(10, hot.largestRectangleArea(heights));
    }

    @Test
    public void testMaximalRectangle() {
        char[][] matrix = {{'0', '0', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1', '1'}};
        Assert.assertEquals(6, hot.maximalRectangle(matrix));
    }

    @Test
    public void testInorderTraversal() {
        Integer[] array = {1, null, 2, 3};
        TreeNode root = TreeNode.fromArray(array);
        Assert.assertEquals("[1, 3, 2]", hot.inorderTraversal(root).toString());
    }

    @Test
    public void testNumTrees() {
        int n = 3;
        Assert.assertEquals(5, hot.numTrees(n));
    }

    @Test
    public void testIsValidBST() {
        Integer[] array = {2, 2, 2};
        TreeNode root = TreeNode.fromArray(array);
        Assert.assertFalse(hot.isValidBST(root));
    }

    @Test
    public void testIsSymmetric() {
        Integer[] array = {1, 2, 2, 3, 4, 4, 3};
        TreeNode root = TreeNode.fromArray(array);
        Assert.assertTrue(hot.isSymmetric(root));
    }

    @Test
    public void testLayerOrder() {
        Integer[] array = {1, 2, 2, 3, 4, 4, 3};
        TreeNode root = TreeNode.fromArray(array);
        Assert.assertEquals("[[1], [2, 2], [3, 4, 4, 3]]", Tools.beauty2DArray(hot.levelOrder(root)));
        Assert.assertEquals("[[1]]", Tools.beauty2DArray(hot.levelOrder(new TreeNode(1))));
        Assert.assertEquals("[]", Tools.beauty2DArray(hot.levelOrder(null)));
    }

    @Test
    public void testMaxDepth() {
        Integer[] array = {3, 9, 20, null, null, 15, 7};
        TreeNode root = TreeNode.fromArray(array);
        Assert.assertEquals(3, hot.maxDepth(root));
    }

    @Test
    public void testBuildTree() {
        int[] preOrder = {1, 2, 3, 4};
        int[] inOrder = {2, 3, 4, 1};
        Assert.assertEquals(hot.buildTree(preOrder, inOrder).left.right.right.val, 4);
    }

    @Test
    public void testFlatten() {
        Integer[] array = {1, 2, 5, 3, 4, null, 6};
        TreeNode root = TreeNode.fromArray(array);
        hot.flatten(root);
        Assert.assertEquals(6, root.right.right.right.right.right.val);
    }

    @Test
    public void testMaxProfit() {
        int[] prices = {7, 1, 5, 3, 6, 4};
        Assert.assertEquals(5, hot.maxProfit(prices));

        int[] prices1 = {7, 6, 5, 4, 3, 2};
        Assert.assertEquals(0, hot.maxProfit(prices1));

        int[] prices2 = {2, 1, 4};
        Assert.assertEquals(3, hot.maxProfit(prices2));
    }

    @Test
    public void testMaxPathSum() {
        Integer[] array = {-10, 9, 20, null, null, 15, 7};
        TreeNode treeNode = TreeNode.fromArray(array);
        Assert.assertEquals(42, hot.maxPathSum(treeNode));
    }

    @Test
    public void testLongestConsecutive() {
        int[] array = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        Assert.assertEquals(9, hot.longestConsecutive(array));

        int[] array1 = {9, 1, 4, 7, 3, -1, 0, 5, 8, -1, 6};
        Assert.assertEquals(7, hot.longestConsecutive(array1));
    }

    @Test
    public void testSingleNumber() {
        int[] array = {4, 1, 2, 1, 2};
        Assert.assertEquals(4, hot.singleNumber(array));
    }

    @Test
    public void testWordBreak() {
        String s = "catsandog";
        List<String> dic = Arrays.asList("cats", "dog", "sand", "and", "cat");
        Assert.assertFalse(hot.wordBreak(s, dic));

        String s1 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        List<String> dic1 = Arrays.asList("aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa", "ba");
        Assert.assertFalse(hot.wordBreak(s1, dic1));
    }

    @Test
    public void testHasCycle() {
        ListNode h = ListNode.fromArray(new int[]{1, 2, 3, 4});
        h.next.next.next.next = h;
        Assert.assertTrue(hot.hasCycle(h));
    }

    @Test
    public void testDetectCycle() {
        ListNode h = ListNode.fromArray(new int[]{1, 2, 3, 4});
        h.next.next.next.next = h;
        Assert.assertEquals(1, hot.detectCycle(h).val);

        ListNode h2 = ListNode.fromArray(new int[]{1, 2});
        Assert.assertNull(hot.detectCycle(h2));
    }

    @Test
    public void testLRUCache() {
        LRUCache l = new LRUCache(2);
        l.put(1, 1);
        l.put(2, 2);
        l.put(3, 3);
        l.put(4, 4);
        l.put(5, 5);
        l.put(6, 6);
        l.put(7, 7);
        Assert.assertEquals(-1, l.get(2));
        Assert.assertEquals(-1, l.get(3));
        Assert.assertEquals(7, l.get(7));
    }

    @Test
    public void testSortList() {
        ListNode head = ListNode.fromArray(new int[]{-1, 5, 3, 4, 0});
        hot.sortList(head);
        Assert.assertEquals("[-1, 0, 3, 4, 5]", String.valueOf(head));

        ListNode head2 = ListNode.fromArray(new int[]{-1, 5, 3, 4});
        hot.sortList(head2);
        Assert.assertEquals("[-1, 3, 4, 5]", String.valueOf(head2));
    }
}
