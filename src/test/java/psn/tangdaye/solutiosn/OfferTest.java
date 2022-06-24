package psn.tangdaye.solutiosn;

import org.junit.Assert;
import org.junit.Test;
import psn.tangdaye.model.ListNode;
import psn.tangdaye.model.Node;
import psn.tangdaye.model.TreeNode;
import psn.tangdaye.solutions.Offer;

import java.util.Arrays;

public class OfferTest {
    Offer offer = new Offer();

    @Test
    public void testCQueue() {
        Offer.CQueue c = new Offer.CQueue();
        StringBuilder sb = new StringBuilder("[");
        String[] optList = {"CQueue", "appendTail", "deleteHead", "appendTail", "appendTail", "deleteHead", "deleteHead", "appendTail", "appendTail", "appendTail", "deleteHead", "deleteHead", "deleteHead", "appendTail", "appendTail", "deleteHead", "deleteHead", "deleteHead", "appendTail", "appendTail", "deleteHead"};
        int[][] params = {{}, {11}, {}, {1}, {17}, {}, {}, {19}, {20}, {13}, {}, {}, {}, {12}, {3}, {}, {}, {}, {10}, {19}, {}};
        for (int i = 0; i < optList.length; i++) {
            switch (optList[i]) {
                case "CQueue":
                    c = new Offer.CQueue();
                    sb.append("null,");
                    break;
                case "appendTail":
                    c.appendTail(params[i][0]);
                    sb.append("null,");
                    break;
                case "deleteHead":
                    int t = c.deleteHead();
                    sb.append(t).append(",");
                    break;
            }
        }
        Assert.assertEquals("[null,null,11,null,null,1,17,null,null,null,19,20,13,null,null,12,3,-1,null,null,10]", sb.substring(0, sb.length() - 1) + "]");
    }

    @Test
    public void testMinStack() {
        Offer.MinStack minStack = new Offer.MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        Assert.assertEquals(-3, minStack.min());
        minStack.pop();
        Assert.assertEquals(0, minStack.top());
        Assert.assertEquals(-2, minStack.min());
    }

    @Test
    public void testReversePrint() {
        ListNode head = new ListNode(1);
        head.next(2).next(3).next(4);
        System.out.println(head);
        System.out.println(Arrays.toString(offer.reversePrint(head)));
    }

    @Test
    public void testReverseList() {
        ListNode head = new ListNode(1);
        head.next(2).next(3).next(4);
        Assert.assertEquals("[4, 3, 2, 1]", offer.reverseList(head).toString());
    }

    @Test
    public void testCopyRandomList() {
        Integer[][] value = {{7, null}, {13, 0}, {11, 4}, {10, 2}, {1, 0}};
        Node head = Node.fromValue(value);
        Node newHead = offer.copyRandomList(head);
        Assert.assertNotNull(head);
        Assert.assertEquals(head.toString(), newHead.toString());
    }

    @Test
    public void testReplaceSpace() {
        String t = "hello   world";
        Assert.assertEquals("hello%20%20%20world", offer.replaceSpace(t));
    }

    @Test
    public void testReverseLeftWords() {
        String t = "hello world";
        Assert.assertEquals("llo worldhe", offer.reverseLeftWords(t, 2));
    }

    @Test
    public void testFindRepeatNumber() {
        int[] nums = {2, 3, 1, 0, 2, 5, 3};
        Assert.assertEquals(2, offer.findRepeatNumber(nums));
    }

    @Test
    public void testSearch() {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;
        Assert.assertEquals(2, offer.search(nums, target));
    }

    @Test
    public void testMissingNumber() {
        int[] nums = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        Assert.assertEquals(9, offer.missingNumber(nums));
    }

    @Test
    public void testFindNumberIn2DArray() {
        int[][] matrix = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        Assert.assertTrue(offer.findNumberIn2DArray(matrix, 8));

        int[][] matrix2 = {{1}};
        Assert.assertTrue(offer.findNumberIn2DArray(matrix2, 1));
    }

    @Test
    public void testMinArray() {
        int[] nums = {4, 5, 1, 2, 3};
        Assert.assertEquals(1, offer.minArray(nums));

        int[] nums2 = {3, 3, 1, 3};
        Assert.assertEquals(1, offer.minArray(nums2));

        int[] nums3 = {3, 1, 3};
        Assert.assertEquals(1, offer.minArray(nums3));
    }

    @Test
    public void testFirstUniqChar() {
        String s = "abac";
        Assert.assertEquals('b', offer.firstUniqChar(s));
    }

    @Test
    public void testTreeNode() {
        Integer[] array = {1, 2, 3, 4, null, null, 5};
        TreeNode root = TreeNode.fromArray(array);
        int[] result = offer.levelOrder(root);
        Assert.assertEquals("[1, 2, 3, 4, 5]", Arrays.toString(result));
        Assert.assertEquals("[[1], [2, 3], [4, 5]]", offer.levelOrder2(root).toString());
        Assert.assertEquals("[[1], [3, 2], [4, 5]]", offer.levelOrder3(root).toString());
        Assert.assertEquals("1, 2, 4, 3, 5", root.toString());

        Integer[] array2 = {10, 12, 6, 8, 3, 11};
        TreeNode root2 = TreeNode.fromArray(array2);
        Integer[] array3 = {10, 12, 6, 8};
        TreeNode root3 = TreeNode.fromArray(array3);
        Assert.assertTrue(offer.isSubStructure(root2, root3));

        Integer[] array4 = {4, 2, 7, 1, 3, 6, 9};
        TreeNode root4 = TreeNode.fromArray(array4);
        Integer[] array5 = {4, 7, 2, 9, 6, 3, 1};
        TreeNode root5 = TreeNode.fromArray(array5);
        Assert.assertEquals(offer.mirrorTree(root4).toString(), root5.toString());

        Integer[] array6 = {1, 2, 2, 3, 4, 4, 3};
        TreeNode root6 = TreeNode.fromArray(array6);
        Assert.assertTrue(offer.isSymmetric(root6));
    }

    @Test
    public void testFib() {
        Assert.assertEquals(407059028, offer.fib(95));
    }

    @Test
    public void testNumWays() {
        Assert.assertEquals(21, offer.numWays(7));
    }

    @Test
    public void testMaxProfit() {
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        int[] prices2 = {7, 6, 4, 3, 1};
        Assert.assertEquals(5, offer.maxProfit(prices1));
        Assert.assertEquals(0, offer.maxProfit(prices2));
    }

    @Test
    public void testMaxSubArray() {
        int[] array1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] array2 = {5, 4, -1, 7, 8};
        Assert.assertEquals(6, offer.maxSubArray(array1));
        Assert.assertEquals(23, offer.maxSubArray(array2));
    }

    @Test
    public void testMaxValue() {
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        Assert.assertEquals(12, offer.maxValue(grid));
    }

    @Test
    public void testTranslateNum() {
        int num = 12258;
        Assert.assertEquals(5, offer.translateNum(num));
    }

    @Test
    public void testLengthOfLongestSubstring() {
        String s = "advertisement";
        Assert.assertEquals(8, offer.lengthOfLongestSubstring(s));
    }

    @Test
    public void testDeleteNode() {
        int[] array = {4, 5, 1, 2, 3};
        ListNode head = ListNode.fromArray(array);
        Assert.assertEquals("[4, 5, 1, 2]", offer.deleteNode(head, 3).toString());
    }

    @Test
    public void testGetKthFromEnd() {
        int[] array = {4, 5, 1, 2, 3};
        ListNode head = ListNode.fromArray(array);
        Assert.assertEquals("[3]", offer.getKthFromEnd(head, 1).toString());
    }

    @Test
    public void testMergeTwoLists() {
        int[] array1 = {1, 2, 4};
        int[] array2 = {1, 3, 4};
        ListNode l1 = ListNode.fromArray(array1);
        ListNode l2 = ListNode.fromArray(array2);
        Assert.assertEquals("[1, 1, 2, 3, 4, 4]", offer.mergeTwoLists(l1, l2).toString());
    }

    @Test
    public void testGetIntersectionNode() {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        ListNode l1 = ListNode.fromArray(array);
        ListNode l2 = new ListNode(99);
        l2.next = l1.next.next.next;
        Assert.assertEquals("[4, 5, 6, 7, 8, 9]", offer.getIntersectionNode(l1, l2).toString());
    }

    @Test
    public void testExchange() {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Assert.assertEquals("[1, 9, 3, 7, 5, 6, 8, 4, 2]", Arrays.toString(offer.exchange(array)));
    }

    @Test
    public void testTwoSum() {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Assert.assertEquals("[1, 9]", Arrays.toString(offer.twoSum(array, 10)));
    }

}
