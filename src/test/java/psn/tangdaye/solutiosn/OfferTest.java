package psn.tangdaye.solutiosn;

import org.junit.Assert;
import org.junit.Test;
import psn.tangdaye.model.ListNode;
import psn.tangdaye.model.Node;
import psn.tangdaye.solutions.Offer;

import java.lang.reflect.Array;
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
        Assert.assertEquals("llo worldhe", offer.reverseLeftWords(t,2));
    }

}
