package psn.tangdaye.solutions;


import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class SortTest {
    @Test
    public void testSort() {
        int[] array = {3, 1, 4, 1, 7, 5, 3};
        Sort.quickSort(array, true);
        Assert.assertEquals("[7, 5, 4, 3, 3, 1, 1]", Arrays.toString(array));

        Sort.mergeSort(array, false);
        Assert.assertEquals("[1, 1, 3, 3, 4, 5, 7]", Arrays.toString(array));

        Sort.heapSort(array, true);
        Assert.assertEquals("[7, 5, 4, 3, 3, 1, 1]", Arrays.toString(array));

        Sort.bubbleSort(array, false);
        Assert.assertEquals("[1, 1, 3, 3, 4, 5, 7]", Arrays.toString(array));
    }

}
