package psn.tangdaye.solutions;

import org.junit.Assert;
import org.junit.Test;

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


}
