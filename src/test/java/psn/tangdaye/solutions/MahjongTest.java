package psn.tangdaye.solutions;

import org.junit.Assert;
import org.junit.Test;
import psn.tangdaye.classic.mahjong.MahjongPro;
import psn.tangdaye.classic.mahjong.MahjongProMax;
import psn.tangdaye.classic.mahjong.MahjongSimple;

import java.util.Arrays;
import java.util.Set;

/**
 * @author : shayan
 * @date : 2026-01-05 16:22
 * @description :
 */

public class MahjongTest {
    @Test
    public void simpleTest() {
        int[] cards1 = {1, 2, 3, 1, 2, 3, 3, 4, 5, 4, 5, 5, 9, 9};
        boolean canWin1 = MahjongSimple.canWin(cards1);
        Assert.assertFalse(canWin1);

        int[] cards2 = {1, 2, 3, 1, 2, 3, 3, 4, 5, 4, 5, 6, 9, 9};
        boolean canWin2 = MahjongSimple.canWin(cards2);
        Assert.assertTrue(canWin2);
    }

    @Test
    public void proTest() {
        MahjongPro pro = new MahjongPro();

        int[] cards1 = {1, 2, 3, 1, 2, 3, 3, 4, 5, 4, 5, 5, 9, 9};
        boolean canWin1 = pro.canWin(cards1);
        Assert.assertFalse(canWin1);

        int[] cards2 = {1, 2, 3, 1, 2, 3, 3, 4, 5, 4, 5, 6, 9, 9};
        boolean canWin2 = pro.canWin(cards2);
        Assert.assertTrue(canWin2);

        int[] cards3 = {1, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 1};
        boolean canWin3 = pro.canWin(cards3);
        Assert.assertTrue(canWin3);

        int[] cards4 = {1, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 9};
        boolean canWin4 = pro.canWin(cards4);
        Assert.assertTrue(canWin4);
    }

    @Test
    public void proMaxTest() {
        MahjongProMax proMax = new MahjongProMax();

        int[] cards1 = {1, 1, 1, 3, 3, 3, 5, 5, 5, 7, 7, 7, 9, 9};
        Set<MahjongProMax.Hu> canWin1 = proMax.win(cards1);
        System.out.println("牌型：" + Arrays.toString(cards1));
        System.out.println("番型列表：" + canWin1);

        int[] cards2 = {1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4};
        Set<MahjongProMax.Hu> canWin2 = proMax.win(cards2);
        System.out.println("牌型：" + Arrays.toString(cards2));
        System.out.println("番型列表：" + canWin2);

        int[] cards3 = {1, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 1};
        Set<MahjongProMax.Hu> canWin3 = proMax.win(cards3);
        System.out.println("牌型：" + Arrays.toString(cards3));
        System.out.println("番型列表：" + canWin3);
    }

}
