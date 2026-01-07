package psn.tangdaye.classic.mahjong;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : shayan
 * @date : 2026-01-05 16:39
 * @description : 性能优化版本，记忆化+位运算
 * <p>
 * 0-2表示1数量，3-5表示2数量，24-26表示9数量
 */
public class MahjongPro {
    private final Map<Integer, Boolean> memWin = new HashMap<>();
    private final Map<Integer, Boolean> memMatch = new HashMap<>();

    public MahjongPro() {
        memMatch.put(0, true);
    }

    // 获取sluts[i];
    private static final int[] MASK = {0, 7, 7 << 3, 7 << 6, 7 << 9, 7 << 12, 7 << 15, 7 << 18, 7 << 21, 7 << 24};

    public boolean canWin(int[] cards) {
        int[] sluts = new int[10];
        for (int card : cards) {
            sluts[card] += 1;
        }
        int rep = sluts2int(sluts);
        return doCanWin(rep);
    }

    private boolean doCanWin(int rep) {
        if (memWin.containsKey(rep)) return memWin.get(rep);
        for (int i = 1; i <= 9; i++) {
            int newRep = minusINum(rep, i, 2);
            if (newRep >= 0) {
                if (match(newRep)) {
                    memWin.put(rep, true);
                    return true;
                }
            }
        }
        memWin.put(rep, false);
        return false;
    }

    private boolean match(int rep) {
        if (memMatch.containsKey(rep)) return memMatch.get(rep);
        for (int i = 1; i <= 9; i++) {
            int value = getI(rep, i);
            if (value >= 1) {
                if (value >= 3) {
                    int kzRep = minusINum(rep, i, 3);
                    if (kzRep >= 0) {
                        if (match(kzRep)) {
                            memMatch.put(rep, true);
                            return true;
                        }
                    }
                }

                if (i <= 7 && getI(rep, i + 1) >= 1 && getI(rep, i + 2) >= 1) {
                    int szRep = minusINum(rep, i, 1);
                    szRep = minusINum(szRep, i + 1, 1);
                    szRep = minusINum(szRep, i + 2, 1);
                    if (match(szRep)) {
                        memMatch.put(rep, true);
                        return true;
                    }
                }
                memMatch.put(rep, false);
                return false;
            }
        }
        memMatch.put(rep, false);
        return false;
    }

    private static int minusINum(int rep, int i, int num) {
        int value = getI(rep, i);
        if (value < num) return -1;
        return (rep & ~MASK[i]) | ((value - num) << ((i - 1) * 3));
    }

    private static int getI(int rep, int index) {
        return (rep & MASK[index]) >> ((index - 1) * 3);
    }

    private static int sluts2int(int[] sluts) {
        int result = 0;
        for (int i = 1; i <= 9; i++) {
            result = result | (sluts[i] << ((i - 1) * 3));
        }
        return result;
    }

    private static int[] int2sluts(int rep) {
        int[] result = new int[10];
        for (int i = 1; i <= 9; i++) {
            result[i] = getI(rep, i);
        }
        return result;
    }

}
