package psn.tangdaye.tool;

import java.util.Arrays;
import java.util.Random;

public class Tools {
    public static void printJavaArray(String pythonArray) {
        System.out.println(pythonArray.replace('[', '{').replace(']', '}'));
    }

    public static String beauty2DArray(boolean[][] array) {
        StringBuilder sb = new StringBuilder();
        for (boolean[] anArray : array) {
            String x = Arrays.toString(anArray);
            sb.append(x, 1, x.length() - 1);
            sb.append("\n");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public static <T> void shuffle(T[] array) {
        shuffle(array, 0, array.length);
    }

    public static <T> void shuffle(T[] array, int s, int t) {
        Random r = new Random();
        for (int i = s; i < t; i++) {
            int index = r.nextInt(t - s);
            T temp = array[s + index];
            array[s + index] = array[i];
            array[i] = temp;
        }
    }

    public static void main(String[] args) {
        boolean[][] array = {{true, true, true}, {true, true, true}, {true, true, true}};
        System.out.println(beauty2DArray(array));
//        printJavaArray("[[1,2,3,4],[5,6,7,8],[9,10,11,12]]");
    }
}
