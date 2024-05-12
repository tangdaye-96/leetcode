package psn.tangdaye.solutiosn;

import org.junit.Assert;
import org.junit.Test;
import psn.tangdaye.solutions.Top;

public class TopTest {

    Top top = new Top();

    @Test
    public void testReverse() {
        Assert.assertEquals(32, top.reverse(23));
        Assert.assertEquals(-32, top.reverse(-23));
        Assert.assertEquals(0, top.reverse(1888888888));
    }

    @Test
    public void testMyAtoi() {
        Assert.assertEquals(Integer.MAX_VALUE, top.myAtoi("2147483647"));
        Assert.assertEquals(21474836, top.myAtoi("21474836-47"));
        Assert.assertEquals(Integer.MAX_VALUE, top.myAtoi("2147483648"));
    }

    @Test
    public void testRomanToInt() {
        Assert.assertEquals(1994, top.romanToInt("MCMXCIV"));
    }

    @Test
    public void testLongestCommonPrefix() {
        Assert.assertEquals("fl", top.longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        Assert.assertEquals("", top.longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
    }

    @Test
    public void testRemoveDuplicates() {
        int[] array = {0, 1, 2, 3, 3, 3, 3, 4, 5, 6, 7, 7, 7, 7, 7, 7};
        Assert.assertEquals(8, top.removeDuplicates(array));
        for (int i = 0; i <= 7; i++) Assert.assertEquals(i, array[i]);
    }

    @Test
    public void testStrStr() {
        Assert.assertEquals(2, top.strStr("hello world", "ll"));
        Assert.assertEquals(-1, top.strStr("hello word", "ow"));
    }

    @Test
    public void testDivide() {
        Assert.assertEquals(-1 << 30, top.divide(Integer.MIN_VALUE, 2));
        Assert.assertEquals(Integer.MAX_VALUE / 3, top.divide(Integer.MAX_VALUE, 3));
    }

    @Test
    public void testIsValidSudoku() {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        Assert.assertTrue(top.isValidSudoku(board));
        board[0][0] = '8';
        Assert.assertFalse(top.isValidSudoku(board));
    }

    @Test
    public void testCountAndSay() {
        Assert.assertEquals("111221", top.countAndSay(5));
    }

    @Test
    public void testFirstMissingPositive() {
        int[] t = {7, 6, 5, 4, 3, 2, 0};
        Assert.assertEquals(1, top.firstMissingPositive(t));
        t[6] = 1;
        Assert.assertEquals(8, top.firstMissingPositive(t));

    }


}
