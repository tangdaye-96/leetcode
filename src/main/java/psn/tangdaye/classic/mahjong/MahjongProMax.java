package psn.tangdaye.classic.mahjong;

import java.util.*;

/**
 * @author : shayan
 * @date : 2026-01-05 20:58
 * @description :
 */
public class MahjongProMax {
    private final Map<Integer, Set<Hu>> memWin = new HashMap<>();
    private final Map<Integer, Set<Ma>> memMatch = new HashMap<>();

    public MahjongProMax() {
        memMatch.put(0, Collections.singleton(new Ma()));
    }

    // 获取sluts[i];
    private static final int[] MASK = {0, 7, 7 << 3, 7 << 6, 7 << 9, 7 << 12, 7 << 15, 7 << 18, 7 << 21, 7 << 24};

    /**
     * 计算出所有胡牌番型
     *
     * @param cards cards表示点数，从1~9，每张牌不超过4张，一共14张
     * @return 胡牌的番型，null表示不胡牌
     */
    public Set<Hu> win(int[] cards) {
        int[] sluts = new int[10];
        for (int card : cards) {
            sluts[card] += 1;
        }
        int rep = sluts2int(sluts);
        return doCanWin(rep);
    }

    private Set<Hu> doCanWin(int rep) {
        if (memWin.containsKey(rep)) return memWin.get(rep);
        Set<Hu> all = new HashSet<>();
        for (int i = 1; i <= 9; i++) {
            int newRep = minusINum(rep, i, 2);
            if (newRep >= 0) {
                Set<Ma> ma = match(newRep);
                if (ma != null) {
                    Set<Hu> hu = addJ(ma, i);
                    all.addAll(hu);
                }
            }
        }
        if (all.isEmpty()) {
            memWin.put(rep, null);
            return null;
        } else {
            memWin.put(rep, all);
            return all;
        }
    }

    private Set<Ma> match(int rep) {
        if (memMatch.containsKey(rep)) return memMatch.get(rep);
        Set<Ma> all = new HashSet<>();
        for (int i = 1; i <= 9; i++) {
            int value = getI(rep, i);
            if (value >= 1) {
                if (value >= 3) {
                    int kzRep = minusINum(rep, i, 3);
                    if (kzRep >= 0) {
                        Set<Ma> ma = match(kzRep);
                        if (ma != null) {
                            Set<Ma> maNew = addKz(ma, i);
                            all.addAll(maNew);
                        }
                    }
                }
                if (i <= 7 && getI(rep, i + 1) >= 1 && getI(rep, i + 2) >= 1) {
                    int szRep = minusINum(rep, i, 1);
                    szRep = minusINum(szRep, i + 1, 1);
                    szRep = minusINum(szRep, i + 2, 1);
                    Set<Ma> ma = match(szRep);
                    if (ma != null) {
                        Set<Ma> maNew = addSz(ma, i);
                        all.addAll(maNew);
                    }
                }
                if (all.isEmpty()) {
                    memMatch.put(rep, null);
                    return null;
                }
            }
        }
        if (all.isEmpty()) {
            memMatch.put(rep, null);
            return null;
        } else {
            memMatch.put(rep, all);
            return all;
        }
    }

    private Set<Hu> addJ(Set<Ma> ma, int j) {
        Set<Hu> hu = new HashSet<>();
        for (Ma m : ma) {
            Hu h = new Hu(m, j);
            hu.add(h);
        }
        return hu;
    }

    private Set<Ma> addKz(Set<Ma> ma, int kzPos) {
        Set<Ma> maNew = new HashSet<>();
        for (Ma m : ma) {
            Ma nm = new Ma(m);
            nm.kz[kzPos] += 1;
            maNew.add(nm);
        }
        return maNew;
    }

    private Set<Ma> addSz(Set<Ma> ma, int szPos) {
        Set<Ma> maNew = new HashSet<>();
        for (Ma m : ma) {
            Ma nm = new Ma(m);
            nm.sz[szPos] += 1;
            maNew.add(nm);
        }
        return maNew;
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

    public static class Hu {
        private final int j;

        private final int[] kz;

        private final int[] sz;

        public Hu(Ma ma, int j) {
            this.kz = new int[10];
            System.arraycopy(ma.kz, 0, this.kz, 0, 10);
            this.sz = new int[10];
            System.arraycopy(ma.sz, 0, this.sz, 0, 10);
            this.j = j;
        }

        @Override
        public String toString() {
            return j2str(j) + "，" + kz2str(kz) + "，" + sz2str(sz);
        }

        private String j2str(int j) {
            return "将牌：" + j + j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Hu hu = (Hu) o;
            return j == hu.j && Objects.deepEquals(kz, hu.kz) && Objects.deepEquals(sz, hu.sz);
        }

        @Override
        public int hashCode() {
            return Objects.hash(j, Arrays.hashCode(kz), Arrays.hashCode(sz));
        }
    }

    public static class Ma {
        private final int[] kz;

        private final int[] sz;

        public Ma() {
            this.kz = new int[10];
            this.sz = new int[10];
        }


        public Ma(Ma another) {
            this.kz = new int[10];
            System.arraycopy(another.kz, 0, this.kz, 0, 10);

            this.sz = new int[10];
            System.arraycopy(another.sz, 0, this.sz, 0, 10);
        }

        @Override
        public String toString() {
            return kz2str(kz) + "，" + sz2str(sz);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Ma ma = (Ma) o;
            return Objects.deepEquals(kz, ma.kz) && Objects.deepEquals(sz, ma.sz);
        }

        @Override
        public int hashCode() {
            return Objects.hash(Arrays.hashCode(kz), Arrays.hashCode(sz));
        }
    }

    private static int[] int2sluts(int rep) {
        int[] result = new int[10];
        for (int i = 1; i <= 9; i++) {
            result[i] = getI(rep, i);
        }
        return result;
    }

    private static String kz2str(int[] kz) {
        StringBuilder sb = new StringBuilder();
        sb.append("刻子：");
        boolean hasKz = false;
        for (int i = 1; i <= 9; i++) {
            int t = kz[i];
            for (int j = 0; j < t; j++) {
                sb.append(i).append(i).append(i).append("/");
                hasKz = true;
            }
        }
        if (hasKz) {
            sb.deleteCharAt(sb.length() - 1);
        } else {
            sb.append("无");
        }
        return sb.toString();
    }

    private static String sz2str(int[] sz) {
        StringBuilder sb = new StringBuilder();
        sb.append("顺子：");
        boolean hasSz = false;
        for (int i = 1; i <= 9; i++) {
            int t = sz[i];
            for (int j = 0; j < t; j++) {
                sb.append(i).append(i + 1).append(i + 2).append("/");
                hasSz = true;
            }
        }
        if (hasSz) {
            sb.deleteCharAt(sb.length() - 1);
        } else {
            sb.append("无");
        }
        return sb.toString();
    }

}
