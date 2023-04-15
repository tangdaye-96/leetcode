package psn.tangdaye.solutiosn;


import org.junit.Assert;
import org.junit.Test;
import psn.tangdaye.solutions.AlgorithmMIT;

import java.util.Arrays;

public class AlgorithmMITTest {
    @Test
    public void testSort() {
        int[] array = {3, 1, 4, 1, 7, 5, 3};
        AlgorithmMIT.quickSort(array, true);
        Assert.assertEquals("[7, 5, 4, 3, 3, 1, 1]", Arrays.toString(array));

        AlgorithmMIT.mergeSort(array, false);
        Assert.assertEquals("[1, 1, 3, 3, 4, 5, 7]", Arrays.toString(array));
    }

}
