package psn.tangdaye.solutions;

import psn.tangdaye.tool.Tools;

import java.util.*;

/**
 * 剑指 Offer（专项突击版）
 * <p>
 * <a href="https://leetcode.cn/problem-list/e8X3pBZi/">https://leetcode.
 * cn/problem-list/e8X3pBZi/</a>
 */
public class Offer2 {
    /**
     * 剑指 Offer II 001. 整数除法
     * <p>
     * 给定两个整数 a 和 b ，求它们的除法的商 a/b ，要求不得使用乘号 '*'、除号 '/' 以及求余符号 '%' 。
     * <p>
     * <a href="https://leetcode.cn/problems/xoh6Oh/?favorite=e8X3pBZi">https://leetcode.cn/problems/xoh6Oh/?favorite=e8X3pBZi</a>
     */
    public int divide(int a, int b) {
        if (a == 0) return 0;
        if (b == -1) return a == Integer.MIN_VALUE ? Integer.MAX_VALUE : -a;
        if (b == 1) return a;
        if (a == Integer.MIN_VALUE) {
            if (b == Integer.MIN_VALUE) return 1;
            else {
                boolean neg = true;
                if (b < 0) {
                    b = -b;
                    neg = false;
                }
                int r = 0;
                while (a <= 0) {
                    a += b;
                    r++;
                }
                r--;
                return neg ? -r : r;
            }
        }
        boolean neg = false;
        if (a < 0 && b < 0) {
            a = -a;
            b = -b;
        } else if (a < 0) {
            a = -a;
            neg = true;
        } else if (b < 0) {
            b = -b;
            neg = true;
        }
        int r = 0;
        while (a >= 0) {
            a -= b;
            r++;
        }
        r--;
        return neg ? -r : r;
    }

    /**
     * 剑指 Offer II 002. 二进制加法
     * <p>
     * 给定两个 01 字符串 a 和 b ，请计算它们的和，并以二进制字符串的形式输出。
     * <p>
     * <a href="https://leetcode.cn/problems/JFETK5/?favorite=e8X3pBZi">https://leetcode.cn/problems/JFETK5/?favorite=e8X3pBZi</a>
     */
    public String addBinary(String a, String b) {
        if (a.length() < b.length()) {
            String c = a;
            a = b;
            b = c;
        }
        int l1 = a.length() - 1;
        int l2 = b.length() - 1;
        StringBuilder sb = new StringBuilder();
        boolean add = false;
        int i = l2;
        while (i >= 0) {
            boolean at = a.charAt(l1 - l2 + i) == '1';
            boolean bt = b.charAt(i) == '1';
            if (add) {
                if (at && bt) sb.append('1');
                else if (at || bt) sb.append('0');
                else {
                    sb.append('1');
                    add = false;
                }
            } else {
                if (at && bt) {
                    sb.append('0');
                    add = true;
                } else if (at || bt) sb.append('1');
                else sb.append('0');

            }
            i--;
        }
        if (!add) {
            return a.substring(0, l1 - l2) + sb.reverse();
        }
        i = l1 - l2 - 1;
        while (i >= 0) {
            boolean at = a.charAt(i) == '1';
            if (!at) {
                return a.substring(0, i) + '1' + sb.reverse();
            } else {
                sb.append('0');
                i--;
            }
        }
        sb.append('1');
        return sb.reverse().toString();
    }

    /**
     * 剑指 Offer II 003. 前 n 个数字二进制中 1 的个数
     * <p>
     * 给定一个非负整数 n ，请计算 0 到 n 之间的每个数字的二进制表示中 1 的个数，并输出一个数组。
     * <p>
     * <a href="https://leetcode.cn/problems/w3tCBm/?favorite=e8X3pBZi">https://leetcode.cn/problems/w3tCBm/?favorite=e8X3pBZi</a>
     */
    public int[] countBits(int n) {
        int[] result = new int[n + 1];
        result[0] = 0;
        for (int i = 1; i < n + 1; i++) {
            if (i % 2 == 0) result[i] = result[i / 2];
            else result[i] = result[i / 2] + 1;
        }
        return result;
    }

    /**
     * 剑指 Offer II 004. 只出现一次的数字
     * <p>
     * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
     * <p>
     * <a href="https://leetcode.cn/problems/WGki4K/?favorite=e8X3pBZi">https://leetcode.cn/problems/WGki4K/?favorite=e8X3pBZi</a>
     */
    public int singleNumber(int[] nums) {
        int[] t = new int[32];
        for (int i = 0; i < 32; i++) {
            for (int n : nums) {
                t[i] += ((1 << i) & n) == 0 ? 0 : 1;
            }
        }
        int result = 0;
        for (int i = 0; i < 32; i++) {
            if (t[i] % 3 == 1) result ^= 1 << i;
        }
        return result;
    }

    /**
     * 剑指 Offer II 005. 单词长度的最大乘积
     * <p>
     * 给定一个字符串数组words，请计算当两个字符串 words[i] 和 words[j] 不包含相同字符时，它们长度的乘积的最大值。假设字符串中只包含英语的小写字母。如果没有不包含相同字符的一对字符串，返回 0。
     * <p>
     * <a href="https://leetcode.cn/problems/aseY1I/">https://leetcode.cn/problems/aseY1I/</a>
     */
    public int maxProduct(String[] words) {
        int[] x = new int[words.length];
        for (int i = 0; i < words.length; i++) x[i] = stringChar2int(words[i]);
        int max = 0;
        for (int i = 0; i < x.length; i++) {
            for (int j = i + 1; j < x.length; j++) {
                if ((x[i] & x[j]) == 0) max = Math.max(max, words[i].length() * words[j].length());
            }
        }
        return max;
    }

    private int stringChar2int(String x) {
        int result = 0;
        for (int i = 0; i < x.length(); i++) {
            result |= (1 << (x.charAt(i) - 'a'));
        }
        return result;
    }

    /**
     * 剑指 Offer II 006. 排序数组中两个数字之和
     * <p>
     * 给定一个已按照 升序排列  的整数数组 numbers ，请你从数组中找出两个数满足相加之和等于目标数 target 。
     * <p>
     * <a href="https://leetcode.cn/problems/kLl5u1/?favorite=e8X3pBZi">https://leetcode.cn/problems/kLl5u1/?favorite=e8X3pBZi</a>
     */
    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            if (2 * numbers[i] == target) return new int[]{i, i + 1};
            int c = Arrays.binarySearch(numbers, target - numbers[i]);
            if (c > 0) return new int[]{i, c};
        }
        return null;
    }


    /**
     * 剑指 Offer II 007. 数组中和为 0 的三个数
     * <p>
     * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
     * <p>
     * <a href="https://leetcode.cn/problems/1fGaJU/?envType=featured-list&envId=e8X3pBZi">https://leetcode.cn/problems/1fGaJU/?envType=featured-list&envId=e8X3pBZi</a>
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, List<int[]>> all = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int n = -(nums[i] + nums[j]);
                if (n >= 0) {
                    if (!all.containsKey(n)) all.put(n, new ArrayList<>());
                    List<int[]> theOne = all.get(n);
                    if (theOne.size() == 0 || nums[theOne.get(theOne.size() - 1)[1]] != nums[j])
                        theOne.add(new int[]{i, j});
                }
            }
        }
        List<List<Integer>> result = new LinkedList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int n = nums[i];
            if (n < 0) break;
            if (i != nums.length - 1 && n == nums[i + 1]) continue;
            if (all.containsKey(n)) {
                for (int[] temp : all.get(n)) {
                    if (i > temp[1]) result.add(Arrays.asList(nums[temp[0]], nums[temp[1]], n));
                }
            }
        }
        return result;
    }

    /**
     * 剑指 Offer II 008. 和大于等于 target 的最短子数组
     * <p>
     * 给定一个含有n个正整数的数组和一个正整数 target 。
     * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组[numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
     * <p>
     * <a href="https://leetcode.cn/problems/2VG8Kg/?envType=featured-list&envId=e8X3pBZi">https://leetcode.cn/problems/2VG8Kg/?envType=featured-list&envId=e8X3pBZi</a>
     */
    public int minSubArrayLen(int target, int[] nums) {
        int i = 0, j = 0;
        int sum = 0, min = Integer.MAX_VALUE;
        while (j < nums.length) {
            sum += nums[j];
            if (sum < target) j++;
            else {
                while (sum >= target) {
                    sum -= nums[i];
                    i++;
                }
                i--;
                sum += nums[i];
                min = Math.min(min, j - i + 1);
                if (min == 1) return 1;
                j++;
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    /**
     * 剑指 Offer II 009. 乘积小于 K 的子数组
     * <p>
     * 给定一个正整数数组 nums和整数 k ，请找出该数组内乘积小于 k 的连续的子数组的个数。
     * <p>
     * <a href="https://leetcode.cn/problems/ZVAVXX/?envType=featured-list&envId=e8X3pBZi">https://leetcode.cn/problems/ZVAVXX/?envType=featured-list&envId=e8X3pBZi</a>
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0;
        int i = 0, j = 0;
        long product = 1;
        int count = 0;
        while (j < nums.length) {
            product *= nums[j];
            if (product < k) {
                j++;
            } else {
                while (product >= k) {
                    count += (j - i);
                    product /= nums[i];
                    i++;
                }
                j++;
            }
        }
        int m = (j - i);
        return count + m * (m + 1) / 2;
    }

    /**
     * 剑指 Offer II 010. 和为 k 的子数组
     * <p>
     * 给定一个整数数组和一个整数 k ，请找到该数组中和为 k 的连续子数组的个数。
     * <p>
     * <a href="https://leetcode.cn/problems/QTMn0o/?envType=featured-list&envId=e8X3pBZi">https://leetcode.cn/problems/QTMn0o/?envType=featured-list&envId=e8X3pBZi</a>
     */
    public int subarraySum(int[] nums, int k) {
        int[] sum = new int[1 + nums.length];
        int count = 0;
        sum[0] = 0;
        for (int i = 0; i < nums.length; i++)
            sum[1 + i] = sum[i] + nums[i];
        for (int i = 0; i < sum.length; i++) {
            for (int j = i + 1; j < sum.length; j++) {
                if (sum[j] - sum[i] == k) count++;
            }
        }
        return count;
    }

    /**
     * 剑指 Offer II 011. 0 和 1 个数相同的子数组
     * <p>
     * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
     * <p>
     * <a href="https://leetcode.cn/problems/A1NYOS/?envType=featured-list&envId=e8X3pBZi">https://leetcode.cn/problems/A1NYOS/?envType=featured-list&envId=e8X3pBZi</a>
     */
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        int[] t = new int[1 + n];
        t[0] = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                t[i + 1] = t[i] + 1;
            } else {
                t[i + 1] = t[i] - 1;
            }
        }
        Map<Integer, LinkedList<Integer>> mp = new HashMap<>();
        for (int i = 0; i <= n; i++) {
            int sub = t[i];
            if (mp.containsKey(sub)) mp.get(sub).add(i);
            else {
                LinkedList<Integer> temp = new LinkedList<>();
                temp.add(i);
                mp.put(sub, temp);
            }
        }
        int maxLen = 0;
        for (LinkedList<Integer> list : mp.values()) {
            maxLen = Math.max(maxLen, list.getLast() - list.getFirst());
        }
        return maxLen;
    }

    /**
     * 剑指 Offer II 012. 左右两边子数组的和相等
     * <p>
     * 给你一个整数数组 nums ，请计算数组的 中心下标 。
     * <p>
     * <a href="https://leetcode.cn/problems/tvdfij/?envType=featured-list&envId=e8X3pBZi">https://leetcode.cn/problems/tvdfij/?envType=featured-list&envId=e8X3pBZi</a>
     */
    public int pivotIndex(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        left[0] = 0;
        int[] right = new int[n];
        right[n - 1] = 0;
        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] + nums[i - 1];
        }
        for (int i = n - 2; i >= 0; i--) {
            right[i] = right[i + 1] + nums[i + 1];
        }
        for (int i = 0; i < n; i++) {
            if (left[i] == right[i]) return i;
        }
        return -1;
    }


    public static class NumMatrix {
        int[][] rowSum;

        public NumMatrix(int[][] matrix) {
            rowSum = new int[matrix.length][];
            for (int i = 0; i < matrix.length; i++) {
                int[] row = matrix[i];
                int[] sum = new int[1 + row.length];
                sum[0] = 0;
                for (int j = 0; j < row.length; j++) {
                    sum[1 + j] = sum[j] + row[j];
                }
                rowSum[i] = sum;
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int sum = 0;
            for (int i = row1; i <= row2; i++) {
                sum += rowSum[i][1 + col2] - rowSum[i][col1];
            }
            return sum;
        }
    }


    /**
     * 剑指 Offer II 014. 字符串中的变位词
     * <p>
     * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的某个变位词。
     * <p>
     * <a href="https://leetcode.cn/problems/MPnaiL/?envType=featured-list&envId=e8X3pBZi">https://leetcode.cn/problems/MPnaiL/?envType=featured-list&envId=e8X3pBZi</a>
     */
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> source = Tools.str2dic(s1, 0, s1.length() - 1);
        Map<Character, Integer> dic = new HashMap<>(source);

        int i = 0, j = 0;
        while (j < s2.length()) {
            char jc = s2.charAt(j);
            char ic = s2.charAt(i);
            if (dic.containsKey(jc)) {
                int x = dic.get(jc);
                if (x == 0) {
                    while (ic != jc) {
                        dic.put(ic, dic.get(ic) + 1);
                        i++;
                        ic = s2.charAt(i);
                    }
                    i++;
                } else {
                    dic.put(jc, x - 1);
                    if (x == 1) {
                        boolean done = true;
                        for (Map.Entry<Character, Integer> entry : dic.entrySet()) {
                            if (entry.getValue() > 0) {
                                done = false;
                                break;
                            }
                        }
                        if (done) return true;
                    }
                }
            } else {
                dic = new HashMap<>(source);
                i = j + 1;
            }
            j++;
        }
        return false;
    }

    /**
     * 剑指 Offer II 017. 含有所有字符的最短字符串
     * <p>
     * 给定两个字符串 s 和t 。返回 s 中包含t的所有字符的最短子字符串。如果 s 中不存在符合条件的子字符串，则返回空字符串 "" 。
     * <p>
     * <a href="https://leetcode.cn/problems/M1oyTv/?favorite=e8X3pBZi">https://leetcode.cn/problems/M1oyTv/?favorite=e8X3pBZi</a>
     */
    public String minWindow(String s, String t) {
        Map<Character, Integer> dic = Tools.str2dic(t, 0, t.length() - 1);
        List<Integer> indexList = new ArrayList<>();
        int k = 0;
        int i = -1, j = 0;
        String minStr = "";
        while (j < s.length()) {
            char jc = s.charAt(j);
            if (dic.containsKey(jc)) {
                if (i < 0) i = j;
                indexList.add(j); // 匹配到就塞进去
                int x = dic.get(jc);
                dic.put(jc, x - 1); // 先-1;

                if (minStr.length() == 0) {
                    boolean done = true;
                    for (Map.Entry<Character, Integer> entry : dic.entrySet()) {
                        if (entry.getValue() > 0) {
                            done = false;
                            break;
                        }
                    }
                    if (done) minStr = s.substring(i, j + 1);
                }

                // 这里处理i
                if (dic.get(jc) < 0) {
                    while (true) {
                        i = indexList.get(k);
                        char ic = s.charAt(i);
                        if (dic.get(ic) < 0) {
                            int y = dic.get(ic);
                            dic.put(ic, y + 1);
                            k++;
                        } else {
                            break;
                        }
                    }
                }
            }
            minStr = j + 1 - i <= minStr.length() ? s.substring(i, j + 1) : minStr;
            j++;
        }
        return minStr;
    }


}
