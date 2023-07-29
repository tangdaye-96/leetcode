package psn.tangdaye.solutions;

/**
 * leetcode 精选 TOP 面试题
 * <a href="https://leetcode.cn/problem-list/2ckc81c/?page=1">https://leetcode.cn/problem-list/2ckc81c/?page=1</a>
 */
public class Top {

    /**
     * 7. 整数反转
     * <p>
     * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
     * <p>
     * <a href="https://leetcode.cn/problems/reverse-integer/?envType=featured-list&envId=2ckc81c">https://leetcode.cn/problems/reverse-integer/?envType=featured-list&envId=2ckc81c</a>
     */
    public int reverse(int x) {
        if (x == 0) return x;
        if (x == Integer.MIN_VALUE) return 0;
        boolean neg = x < 0;
        x = neg ? -x : x;
        int i = 0;
        char[] digits = new char[10];
        while (x != 0) {
            int r = x % 10;
            digits[i] = (char) ('0' + r);
            x = (x - r) / 10;
            i++;
        }
        int result = 0;
        for (int j = 0; j < i; j++) {
            int t1 = result * 10;
            if (t1 / 10 != result) return 0;
            int t2 = t1 + (digits[j] - '0');
            if (t2 - (digits[j] - '0') != t1) return 0;
            result = t2;
        }
        return neg ? -result : result;
    }

    /**
     * 8. 字符串转换整数 (atoi)
     * <p>
     * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
     * <p>
     * <a href="https://leetcode.cn/problems/string-to-integer-atoi/?envType=featured-list&envId=2ckc81c">https://leetcode.cn/problems/string-to-integer-atoi/?envType=featured-list&envId=2ckc81c</a>
     */
    public int myAtoi(String s) {
        boolean pre = true;
        int result = 0;
        boolean neg = false;
        for (char c : s.toCharArray()) {
            if (c == ' ' && pre) continue;
            if ((c == '-' || c == '+') && pre) {
                pre = false;
                neg = c == '-';
                continue;
            }
            if (c >= '0' && c <= '9') {
                pre = false;
                int t1 = result * 10;
                if (t1 / 10 != result) return neg ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                int t2 = t1 + (c - '0');
                if (t2 < 0) return neg ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                result = t2;
                continue;
            }
            break;
        }
        return neg ? -result : result;
    }

    /**
     * 13. 罗马数字转整数
     * <p>
     * 给定一个罗马数字，将其转换成整数。
     * <p>
     * <a href="https://leetcode.cn/problems/roman-to-integer/?envType=featured-list&envId=2ckc81c">https://leetcode.cn/problems/roman-to-integer/?envType=featured-list&envId=2ckc81c</a>
     */
    public int romanToInt(String s) {
        int result = 0;
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c == 'I') {
                if (i + 1 < s.length()) {
                    if (s.charAt(i + 1) == 'V') {
                        result += 4;
                        i += 2;
                        continue;
                    }
                    if (s.charAt(i + 1) == 'X') {
                        result += 9;
                        i += 2;
                        continue;
                    }
                }
                result += 1;
            } else if (c == 'X') {
                if (i + 1 < s.length()) {
                    if (s.charAt(i + 1) == 'L') {
                        result += 40;
                        i += 2;
                        continue;
                    }
                    if (s.charAt(i + 1) == 'C') {
                        result += 90;
                        i += 2;
                        continue;
                    }
                }
                result += 10;
            } else if (c == 'C') {
                if (i + 1 < s.length()) {
                    if (s.charAt(i + 1) == 'D') {
                        result += 400;
                        i += 2;
                        continue;
                    }
                    if (s.charAt(i + 1) == 'M') {
                        result += 900;
                        i += 2;
                        continue;
                    }
                }
                result += 100;
            } else if (c == 'V') result += 5;
            else if (c == 'L') result += 50;
            else if (c == 'D') result += 500;
            else if (c == 'M') result += 1000;
            i++;
        }
        return result;
    }


}
