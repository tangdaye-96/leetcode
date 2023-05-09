package psn.tangdaye.solutions;

import java.util.Random;

/**
 * @author : shayan
 * @date : 2023/2/21 13:48
 */
public class Sort {
    static Random random = new Random();

    // 排序
    public static void quickSort(int[] array, boolean revert) {
        doQuickSort(array, 0, array.length - 1, revert);
    }
    private static void doQuickSort(int[] array, int l, int r, boolean revert) {
        if (l >= r) return;
        int t = l + random.nextInt(r + 1 - l);

        swap(array, t, r);
        int pivot = array[r];
        int i = l - 1, j = l;
        for (; j < r; j++) {
            if (revert ^ array[j] <= pivot) {
                i = i + 1;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, r);
        doQuickSort(array, l, i, revert);
        doQuickSort(array, i + 2, r, revert);
    }

    public static void mergeSort(int[] array, boolean revert) {
        doMergeSort(array, 0, array.length - 1, revert);
    }
    public static void doMergeSort(int[] array, int l, int r, boolean revert) {
        if (l >= r) {
            return;
        }
        int m = (l + r) / 2;
        doMergeSort(array, l, m, revert);
        doMergeSort(array, m + 1, r, revert);
        merge(array, l, r, revert);
    }
    private static void merge(int[] array, int l, int r, boolean revert) {
        int m = (l + r) / 2;
        int i = l;
        int j = m + 1;
        int[] temp = new int[r - l + 1];
        int s = 0;
        while (l <= m && j <= r) {
            if (array[i] <= array[j] ^ revert) {
                temp[s] = array[i];
                i++;
            } else {
                temp[s] = array[j];
                j++;
            }
            s++;
        }
        if (i <= m) {
            System.arraycopy(array, i, temp, s, m - i + 1);
        } else if (j <= r) {
            System.arraycopy(array, j, temp, s, r - j + 1);
        }
        System.arraycopy(temp, 0, array, l, r - l + 1);
    }

    private static void swap(int[] array, int i, int j) {
        if (i == j) return;
        array[i] = array[i] ^ array[j];
        array[j] = array[i] ^ array[j];
        array[i] = array[i] ^ array[j];
    }


}
