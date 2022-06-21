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

        Integer[] array2 = {4, 2, 3, 4, 5, 6, 7, 8, 9};
        TreeNode root2 = TreeNode.fromArray(array2);
        Integer[] array3 = {4, 8, 9};
        TreeNode root3 = TreeNode.fromArray(array3);
        Assert.assertTrue(offer.isSubStructure(root2, root3));
    }

}
