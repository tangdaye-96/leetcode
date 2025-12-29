package psn.tangdaye.solutions;

import java.util.Random;

/**
 * @author : shayan
 * @date : 2023/2/21 13:48
 */
public class Sort {
    static Random random = new Random();

    // 快速排序
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

    // 归并排序
    public static void mergeSort(int[] array, boolean revert) {
        doMergeSort(array, 0, array.length - 1, revert);
    }

    private static void doMergeSort(int[] array, int l, int r, boolean revert) {
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

    // 冒泡排序
    public static void bubbleSort(int[] array, boolean revert) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 1; j < array.length - i; j++) {
                // 如果当前值比前一个值大，需要交换下，把大的放在前面，这样做完之后，最小的会被放在最后
                if (array[j] > array[j - 1] && revert) swap(array, j - 1, j);
                if (array[j] < array[j - 1] && !revert) swap(array, j - 1, j);
            }
        }
    }

    private static void swap(int[] array, int i, int j) {
        if (i == j) return;
        array[i] = array[i] ^ array[j];
        array[j] = array[i] ^ array[j];
        array[i] = array[i] ^ array[j];
    }

    // 堆排序
    public static void heapSort(int[] array, boolean revert) {
        // 构建堆
        buildHeap(array, !revert);
        // 交换堆顶和最后一个值
        for (int i = array.length - 1; i > 0; i--) {
            swap(array, 0, i);
            down(array, i, 0, !revert);
        }
    }

    // 原地构建大顶堆
    private static void buildHeap(int[] array, boolean isBig) {
        // 最后一个非叶子节点，即最后一个节点的父节点
        int lastNotLeafIndex = array.length / 2 - 1;
        for (int i = lastNotLeafIndex; i >= 0; i--) {
            down(array, array.length, i, isBig);
        }
    }

    // 从给定位置向下调整，当前节点需要和两个子节点中最大的交换，再把交换的节点当作当前节点，直到当前节点没有子节点
    private static void down(int[] array, int len, int currentIndex, boolean isBig) {
        while (currentIndex * 2 + 1 < len) {
            int result = adjust(array, len, currentIndex, isBig);
            if (result == -1) {
                currentIndex = currentIndex * 2 + 1;  // 移动到左子节点
            } else if (result == 1) {
                currentIndex = currentIndex * 2 + 2;  // 移动到右子节点
            } else {
                return;
            }
        }
    }

    // 当前节点和两个子节点中最大的交换，并返回0：不调整/-1：左/1：右
    private static int adjust(int[] array, int len, int currentIndex, boolean isBig) {
        int current = array[currentIndex];
        int leftIndex = currentIndex * 2 + 1;
        if (leftIndex >= len) return 0;
        int left = array[leftIndex];
        int rightIndex = currentIndex * 2 + 2;
        if (rightIndex >= len) {
            if ((current >= left) ^ isBig) {
                swap(array, currentIndex, leftIndex);
                return -1;
            }
        } else {
            int right = array[rightIndex];
            if (isBig) {
                if (left >= current && left >= right) {
                    swap(array, currentIndex, leftIndex);
                    return -1;
                } else if (right >= current) {
                    swap(array, currentIndex, rightIndex);
                    return 1;
                }
            } else {
                if (left <= current && left <= right) {
                    swap(array, currentIndex, leftIndex);
                    return -1;
                } else if (right <= current) {
                    swap(array, currentIndex, rightIndex);
                    return 1;
                }
            }
        }
        return 0;
    }
}
