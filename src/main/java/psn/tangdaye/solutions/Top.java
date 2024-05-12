package psn.tangdaye.solutions;

import psn.tangdaye.tool.Tools;

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

    /**
     * <a href="https://leetcode.cn/problems/longest-common-prefix/?envType=featured-list&envId=2ckc81c">14. 最长公共前缀</a>
     * <p>
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 1) return strs[0];
        String first = strs[0];
        int i = 0;
        while (i < first.length()) {
            char c = first.charAt(i);
            for (int j = 1; j < strs.length; j++) {
                String current = strs[j];
                if (i >= current.length()) return first.substring(0, i);
                else {
                    if (current.charAt(i) != c) return first.substring(0, i);
                }
            }
            i++;
        }
        return first;
    }

    /**
     * <a href="https://leetcode.cn/problems/remove-duplicates-from-sorted-array/?envType=featured-list&envId=2ckc81c">26. 删除有序数组中的重复项</a>
     * <p>
     * 给你一个 升序排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。然后返回 nums 中唯一元素的个数。
     */
    public int removeDuplicates(int[] nums) {
        int i = 1; // 输出位置
        int j = 1; // 输入位置
        while (j < nums.length) {
            while (j < nums.length && nums[j] == nums[i - 1]) j++;
            if (j == nums.length) return i;
            Tools.swap(nums, i, j);
            i++;
            j++;
        }
        return i;
    }

    /**
     * <a href="https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/?envType=featured-list&envId=2ckc81c">28. 找出字符串中第一个匹配项的下标</a>
     * <p>
     * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。如果 needle 不是 haystack 的一部分，则返回  -1 。
     */
    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }


    /**
     * <a href="https://leetcode.cn/problems/divide-two-integers/?envType=featured-list&envId=2ckc81c">29. 两数相除</a>
     * <p>
     * 给你两个整数，被除数 dividend 和除数 divisor。将两数相除，要求 不使用 乘法、除法和取余运算。
     */
    public int divide(int dividend, int divisor) {
        if (dividend == 0) return 0;
        if (divisor == Integer.MIN_VALUE) return dividend == Integer.MIN_VALUE ? 1 : 0;
        if (divisor == -1) return dividend == Integer.MIN_VALUE ? Integer.MAX_VALUE : -dividend;
        if (divisor == 1) return dividend;
        if (divisor == -2) return -(dividend >> 1);
        if (divisor == 2) return dividend >> 1;
        boolean neg = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0);
        int result = 0;
        if (dividend < 0) {
            divisor = divisor > 0 ? -divisor : divisor;
            while (dividend < divisor) {
                int i = 0;
                while ((dividend < (divisor << i)) && (divisor << i) < 0) i++;
                dividend -= divisor << (i - 1);
                result += 1 << (i - 1);
            }
        } else {
            divisor = divisor < 0 ? -divisor : divisor;
            while (dividend > divisor) {
                int i = 0;
                while ((dividend > (divisor << i)) && (divisor << i) > 0) i++;
                dividend -= divisor << (i - 1);
                result += 1 << (i - 1);
            }
        }
        if (dividend == divisor) result += 1;
        return neg ? -result : result;
    }

    /**
     * <a href="https://leetcode.cn/problems/valid-sudoku/?envType=featured-list&envId=2ckc81c">36. 有效的数独</a>
     * <p>
     * 请你判断一个 9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
     */
    public boolean isValidSudoku(char[][] board) {
        // 行/列
        for (int i = 0; i < 9; i++) {
            boolean[] check1 = new boolean[9];
            boolean[] check2 = new boolean[9];
            for (int j = 0; j < 9; j++) {
                char c1 = board[j][i];
                char c2 = board[i][j];
                if (c1 != '.') {
                    if (check1[c1 - '1']) return false;
                    check1[c1 - '1'] = true;
                }
                if (c2 != '.') {
                    if (check2[c2 - '1']) return false;
                    check2[c2 - '1'] = true;
                }
            }
        }
        // 九宫
        int m = 0;
        while (m < 9) {
            int iMin = (m / 3) * 3, iMax = iMin + 3;
            int jMin = (m % 3) * 3, jMax = jMin + 3;
            boolean[] check = new boolean[9];
            for (int i = iMin; i < iMax; i++) {
                for (int j = jMin; j < jMax; j++) {
                    char c = board[i][j];
                    if (c != '.') {
                        if (check[c - '1']) return false;
                        check[c - '1'] = true;
                    }
                }
            }
            m++;
        }
        return true;
    }

    /**
     * <a href="https://leetcode.cn/problems/count-and-say/?envType=featured-list&envId=2ckc81c">38. 外观数列</a>
     * <p>
     * 给定一个正整数 n ，输出外观数列的第 n 项。
     * <p>
     * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。
     */
    public String countAndSay(int n) {
        String t = "1";
        for (int i = 1; i < n; i++) {
            t = say(t);
        }
        return t;
    }

    private String say(String last) {
        if (last.length() == 1) return "1" + last;
        StringBuilder sb = new StringBuilder();
        int i = 0, j = 1;
        while (j < last.length()) {
            while (j < last.length() && last.charAt(j) == last.charAt(i)) j++;
            int count = j - i;
            sb.append(count).append(last.charAt(i));
            i = j;
            j++;
            if (j == last.length()) sb.append(1).append(last.charAt(i));
        }
        return sb.toString();
    }

    /**
     * <a href="https://leetcode.cn/problems/first-missing-positive/?envType=featured-list&envId=2ckc81c">41. 缺失的第一个正数</a>
     * <p>
     * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
     */
    public int firstMissingPositive(int[] nums) {
        long[] bitmap = new long[1 + nums.length / 64];
        for (int n : nums) {
            if (n <= 0 || n > nums.length) continue;
            int index = n / 64;
            int offset = n % 64 - 1;
            bitmap[index] |= (1L << offset);
        }
        int result = 1;
        int now = result;
        while (result <= nums.length) {
            int index = result / 64;
            if (now > 64) now -= 64;
            if ((bitmap[index] & (1L << (now - 1))) == 0) return result;
            result++;
            now++;
        }
        return result;
    }

}
