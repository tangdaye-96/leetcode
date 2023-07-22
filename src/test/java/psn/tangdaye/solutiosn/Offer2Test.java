package psn.tangdaye.solutiosn;

import org.junit.Assert;
import org.junit.Test;
import psn.tangdaye.solutions.Offer2;

import java.util.Arrays;

public class Offer2Test {

    Offer2 offer2 = new Offer2();

    @Test
    public void testOffer2() {
        Assert.assertEquals(-(Integer.MIN_VALUE / 2), offer2.divide(-2147483648, -2));
        Assert.assertEquals(Integer.MIN_VALUE, offer2.divide(-2147483648, 1));
    }

    @Test
    public void testAddBinary() {
        Assert.assertEquals("10000", offer2.addBinary("1111", "1"));
        Assert.assertEquals("101", offer2.addBinary("11", "10"));
    }

    @Test
    public void testCountBits() {
        Assert.assertEquals("[0, 1, 1, 2, 1, 2]", Arrays.toString(offer2.countBits(5)));
    }

    @Test
    public void testSingleNumber() {
        int[] array = {0, 1, 0, 1, 0, 1, 100};
        Assert.assertEquals(100, offer2.singleNumber(array));
    }

    @Test
    public void testMaxProduct() {
        String[] strs = {"a", "ab", "abc", "d", "cd", "bcd", "abcd"};
        Assert.assertEquals(4, offer2.maxProduct(strs));
    }

    @Test
    public void testTwoSum() {
        Assert.assertEquals("[1, 2]", Arrays.toString(offer2.twoSum(new int[]{1, 3, 4, 5}, 7)));
    }

    @Test
    public void testThreeSum() {
        Assert.assertEquals(1, offer2.threeSum(new int[]{0, 0, 0, 0}).size());
    }

    @Test
    public void testNumSubArrayProductLessThanK() {
        Assert.assertEquals(11, offer2.numSubarrayProductLessThanK(new int[]{10, 5, 2, 6, 4}, 100));
        Assert.assertEquals(18, offer2.numSubarrayProductLessThanK(new int[]{10, 9, 10, 4, 3, 8, 3, 3, 6, 2, 10, 10, 9, 3}, 19));
    }

    @Test
    public void testSubarraySum() {
        Assert.assertEquals(2, offer2.subarraySum(new int[]{1, 2, 3}, 3));
        Assert.assertEquals(2, offer2.subarraySum(new int[]{1, 1, 1}, 2));
        Assert.assertEquals(0, offer2.subarraySum(new int[]{1}, 0));
        Assert.assertEquals(1, offer2.subarraySum(new int[]{-1, -1, 1}, 0));
        Assert.assertEquals(3, offer2.subarraySum(new int[]{1, -1, 0}, 0));
    }

    @Test
    public void testFindMaxLength() {
        int[] nums = {1, 0, 0, 0, 1, 0, 0, 0, 1};
        Assert.assertEquals(2, offer2.findMaxLength(nums));
    }

    @Test
    public void testMinSubArrayLen() {
        Assert.assertEquals(2, offer2.minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
    }

    @Test
    public void testPivotIndex() {
        Assert.assertEquals(3, offer2.pivotIndex(new int[]{1, 7, 3, 6, 5, 6}));
    }

    @Test
    public void testNumMatrix() {
        int[][] data = {{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}};
        Offer2.NumMatrix matrix = new Offer2.NumMatrix(data);
        Assert.assertEquals(8, matrix.sumRegion(2, 1, 4, 3));
    }

    @Test
    public void testCheckInclusion() {
        Assert.assertTrue(offer2.checkInclusion("caabb", "caaabcb"));
        Assert.assertFalse(offer2.checkInclusion("abc", "dcda"));
        Assert.assertFalse(offer2.checkInclusion("hello", "ooolleoooleh"));
    }

    @Test
    public void testMinWindow() {
        Assert.assertEquals("BANC", offer2.minWindow("ADOBECODEBANC", "ABC"));
    }


}
