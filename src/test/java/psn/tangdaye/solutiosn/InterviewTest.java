package psn.tangdaye.solutiosn;

import org.junit.Assert;
import org.junit.Test;
import psn.tangdaye.model.ListNode;
import psn.tangdaye.model.TreeNode;
import psn.tangdaye.solutions.Interview;
import psn.tangdaye.tool.Tools;

import java.util.Arrays;

/**
 * @author : shayan
 * @date : 2023/5/12 13:43
 */
public class InterviewTest {
    Interview interview = new Interview();

    @Test
    public void testIsUnique() {
        String x = "leetcode";
        Assert.assertFalse(interview.isUnique(x));
        String y = "abcdefghijklmnopqrstuvwxyz";
        Assert.assertTrue(interview.isUnique(y));
    }

    @Test
    public void testCheckPermutation() {
        String x = "a";
        String y = "ab";
        Assert.assertFalse(interview.checkPermutation(x, y));
    }

    @Test
    public void testReplaceSpaces() {
        String x = "ds sdfs afs sdfa dfssf asdf             ";
        Assert.assertEquals("ds%20sdfs%20afs%20sdfa%20dfssf%20asdf", interview.replaceSpaces(x, 27));
    }

    @Test
    public void testCanPermutePalindrome() {
        Assert.assertTrue(interview.canPermutePalindrome("tactcoa"));
    }

    @Test
    public void testEditDistance() {
        Assert.assertTrue(interview.oneEditAway("horse", "morse"));
        Assert.assertFalse(interview.oneEditAway("horse", "rose"));
    }

    @Test
    public void test() {
        Assert.assertEquals("a2b1c5a3", interview.compressString("aabcccccaaa"));
        Assert.assertEquals("abbccd", interview.compressString("abbccd"));
    }

    @Test
    public void testRotate() {
        int[][] matrix = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        interview.rotate(matrix);
        Assert.assertEquals("[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]", Tools.beauty2DArray(matrix));
    }

    @Test
    public void testSetZeroes() {
        int[][] matrix = {{0, 1, 9, 0}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        interview.setZeroes(matrix);
        Assert.assertEquals("[[0,0,0,0],[0,4,8,0],[0,3,6,0],[0,14,12,0]]", Tools.beauty2DArray(matrix));
    }

    @Test
    public void testIsFlippedString() {
        Assert.assertTrue(interview.isFlippedString("waterbottle", "erbottlewat"));
    }

    @Test
    public void testRemoveDuplicatedNodes() {
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3, 3, 2, 1, 1, 0});
        ListNode newHead = interview.removeDuplicateNodes(head);
        Assert.assertEquals("[1, 2, 3, 0]", newHead.toString());
    }

    @Test
    public void testKthToLast() {
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3, 3, 2, 1, 1, 0});
        Assert.assertEquals(1, interview.kthToLast(head, 3));
        Assert.assertEquals(0, interview.kthToLast(head, 1));
    }

    @Test
    public void testDeleteNode() {
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3, 4});
        interview.deleteNode(head.next);
        Assert.assertEquals("[1, 3, 4]", head.toString());
    }

    @Test
    public void testPartition() {
        ListNode head = ListNode.fromArray(new int[]{1, 4, 3, 2, 5, 2});
        ListNode newHead = interview.partition(head, 3);
        Assert.assertEquals("[1, 2, 2, 4, 3, 5]", newHead.toString());

        ListNode head2 = ListNode.fromArray(new int[]{4, 1});
        ListNode newHead2 = interview.partition(head2, 3);
        Assert.assertEquals("[1, 4]", newHead2.toString());
    }

    @Test
    public void testAddTwoNumbers() {
        ListNode node1 = ListNode.fromArray(new int[]{7, 1, 6});
        ListNode node2 = ListNode.fromArray(new int[]{5, 9, 3});
        Assert.assertEquals("[2, 1, 0, 1]", interview.addTwoNumbers(node2, node1).toString());
    }

    @Test
    public void testIsPalindrome() {
        ListNode node1 = ListNode.fromArray(new int[]{1, 2, 3, 4, 5, 4, 3, 2, 1});
        ListNode node2 = ListNode.fromArray(new int[]{1, 2, 3, 4, 4, 3, 2, 1});
        Assert.assertTrue(interview.isPalindrome(node1));
        Assert.assertTrue(interview.isPalindrome(node2));
    }

    @Test
    public void testGetIntersectionNode() {
        ListNode node1 = ListNode.fromArray(new int[]{1, 2, 3, 4, 5});
        ListNode node2 = new ListNode(1);
        node2.next = new ListNode(2);
        node2.next.next = node1.next;
        Assert.assertSame(node1.next, interview.getIntersectionNode(node1, node2));
    }

    @Test
    public void testDetectCycle() {
        ListNode node = ListNode.fromArray(new int[]{1, 2});
        Assert.assertNull(interview.detectCycle(node));
    }

    @Test
    public void testTripleInOne() {
        Interview.TripleInOne tripleInOne = new Interview.TripleInOne(2);
        Assert.assertEquals(-1, tripleInOne.pop(1));
        tripleInOne.push(1, 2);
        Assert.assertEquals(2, tripleInOne.pop(1));
        Assert.assertEquals(-1, tripleInOne.pop(1));
    }

    @Test
    public void testMinStack() {
        Interview.MinStack minStack = new Interview.MinStack();
        minStack.push(1);
        minStack.push(2);
        minStack.push(0);
        Assert.assertEquals(0, minStack.top());
        Assert.assertEquals(0, minStack.getMin());
        minStack.pop();
        Assert.assertEquals(1, minStack.getMin());
    }

    @Test
    public void testStackOfPlates() {
        Interview.StackOfPlates stack = new Interview.StackOfPlates(3);
        stack.push(0);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        Assert.assertEquals(2, stack.popAt(0));
        Assert.assertEquals(3, stack.pop());
        Assert.assertEquals(1, stack.pop());
        Assert.assertEquals(0, stack.pop());
        Assert.assertEquals(-1, stack.pop());
    }

    @Test
    public void testMyQueue() {
        Interview.MyQueue queue = new Interview.MyQueue();
        queue.push(0);
        queue.push(1);
        queue.push(2);
        queue.push(3);
        Assert.assertEquals(0, queue.pop());
        Assert.assertEquals(1, queue.pop());
        Assert.assertEquals(2, queue.pop());
        Assert.assertEquals(3, queue.pop());
    }

    @Test
    public void testSortedStack() {
        Interview.SortedStack stack = new Interview.SortedStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        Assert.assertEquals(1, stack.peek());
        stack.pop();
        Assert.assertEquals(2, stack.peek());
    }

    @Test
    public void testAnimalShelf() {
        Interview.AnimalShelf animalShelf = new Interview.AnimalShelf();
        animalShelf.enqueue(new int[]{1, 0});
        Assert.assertEquals(1, animalShelf.dequeueCat()[0]);
    }

    @Test
    public void testFindWhetherExistsPath() {
        int[][] graph = {{0, 1}, {1, 2}};
        Assert.assertTrue(interview.findWhetherExistsPath(3, graph, 0, 2));
    }

    @Test
    public void testSortedArrayToBST() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        TreeNode root = interview.sortedArrayToBST(nums);
        Assert.assertEquals(5, root.high());
    }

    @Test
    public void testListOfDepth() {
        TreeNode node = TreeNode.fromArray(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        Assert.assertEquals(3, interview.listOfDepth(node).length);
    }

    @Test
    public void testIsBalanced() {
        TreeNode node = TreeNode.fromArray(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        Assert.assertTrue(interview.isBalanced(node));
    }

    @Test
    public void testIsValidBST() {
        TreeNode node = TreeNode.fromArray(new Integer[]{4, 2, 6, 1, 3, 5, 7});
        Assert.assertTrue(interview.isValidBST(node));
    }

    @Test
    public void testInorderSuccessor() {
        TreeNode node = TreeNode.fromArray(new Integer[]{4, 2, 6, 1, 3, 5, 7});
        Assert.assertEquals(node.right.left, interview.inorderSuccessor(node, node));
    }

    @Test
    public void testLowestCommonAncestor() {
        TreeNode node = TreeNode.fromArray(new Integer[]{4, 2, 6, 1, 3, 5, 7});
        Assert.assertEquals(node, interview.lowestCommonAncestor(node, node.left.right, node.right.left));
    }

    @Test
    public void testBSTSequences() {
        TreeNode node = TreeNode.fromArray(new Integer[]{4, 2, 6, 1, 3, 5, 7});
        Assert.assertEquals(80, interview.BSTSequences(node).size());
    }

    @Test
    public void testCheckSubTree() {
        TreeNode node1 = TreeNode.fromArray(new Integer[]{4, 2, 6, 1, 3, 5, 7});
        TreeNode node2 = TreeNode.fromArray(new Integer[]{2, 1, 3});
        Assert.assertTrue(interview.checkSubTree(node1, node2));
    }

    @Test
    public void testPathSum() {
        Integer[] array = {10, 5, -3, 3, 2, null, 11, 3, -2, null, 1};
        TreeNode root = TreeNode.fromArray(array);
        Assert.assertEquals(3, interview.pathSum(root, 8));
    }

    @Test
    public void testInsertBits() {
        Assert.assertEquals(44, interview.insertBits(12, 2, 4, 7));
    }

    @Test
    public void testPrintBin() {
        Assert.assertEquals("0.111", interview.printBin(0.875));
    }

    @Test
    public void testReverseBits() {
        Assert.assertEquals(8, interview.reverseBits(1775));
    }

    @Test
    public void testFindClosedNumbers() {
        Assert.assertEquals("[19, 13]", Arrays.toString(interview.findClosedNumbers(14)));
        Assert.assertEquals("[69, 56]", Arrays.toString(interview.findClosedNumbers(67)));
        Assert.assertEquals("[72, 66]", Arrays.toString(interview.findClosedNumbers(68)));
    }

    @Test
    public void testConvertInteger() {
        Assert.assertEquals(2, interview.convertInteger(1, 2));
    }


}
