package psn.tangdaye.classic.mahjong;

/**
 * @author : shayan
 * @date : 2025-12-30 21:02
 * @description :
 */
public class MahjongSimple {
    /**
     * 最简单，仅有万子牌
     *
     * @param cards :cards表示点数，从1~9，每张牌不超过4张，一共14张
     * @return 能否胡牌
     */
    public static boolean canWin(int[] cards) {
        int[] sluts = new int[10];
        for (int card : cards) {
            sluts[card] += 1;
        }
        // 从1到9，依次去除两张相同的将牌，判断剩下的12张牌能否凑成全刻子/顺子
        for (int i = 1; i <= 9; i++) {
            if (sluts[i] >= 2) {
                sluts[i] -= 2;
                if (match(sluts)) return true;
                sluts[i] += 2;
            }
        }
        return false;
    }

    /**
     * @param sluts index 1~9表示有多少对应牌数量，不超过4，不小于0
     * @return 是否是全刻子/顺子
     */
    private static boolean match(int[] sluts) {
        if (empty(sluts)) return true;
        for (int i = 1; i <= 9; i++) {
            if (sluts[i] >= 1) {
                if (sluts[i] >= 3) {
                    sluts[i] -= 3;
                    if (match(sluts)) return true;
                    sluts[i] += 3;
                }
                if (i <= 7 && sluts[i] >= 1 && sluts[i + 1] >= 1 && sluts[i + 2] >= 1) {
                    sluts[i] -= 1;
                    sluts[i + 1] -= 1;
                    sluts[i + 2] -= 1;
                    if (match(sluts)) return true;
                    sluts[i] += 1;
                    sluts[i + 1] += 1;
                    sluts[i + 2] += 1;
                }
                return false;
            }
        }
        return false;
    }

    private static boolean empty(int[] sluts) {
        for (int i = 1; i <= 9; i++) {
            if (sluts[i] > 0) return false;
        }
        return true;
    }

    /**
     * @return 解的数量
     */
    private static int numOfSolutions(int n, int m) {
        int[] preRow = new int[m + 1];
        int[] currentRow = new int[m + 1];
        preRow[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= m; j++) {
                currentRow[j] = safeGet(preRow, j) + safeGet(preRow, j - 1) + safeGet(preRow, j - 2) + safeGet(preRow, j - 3) + safeGet(preRow, j - 4);
            }
            System.arraycopy(currentRow, 0, preRow, 0, 1 + m);
        }
        return currentRow[m];
    }

    private static int safeGet(int[] array, int index) {
        if (index >= array.length || index < 0) return 0;
        return array[index];
    }
}
