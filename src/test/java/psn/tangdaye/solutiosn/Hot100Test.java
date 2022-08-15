package psn.tangdaye.solutiosn;

import org.junit.Assert;
import org.junit.Test;
import psn.tangdaye.model.ListNode;
import psn.tangdaye.solutions.Hot100;

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
}
