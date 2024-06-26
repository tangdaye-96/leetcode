package psn.tangdaye.tool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Tools {
    public static void printJavaArray(String pythonArray) {
        System.out.println(pythonArray.replace('[', '{').replace(']', '}'));
    }

    public static <T> String beauty2DArray(List<List<T>> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (List<T> l : list) {
            sb.append("[");
            for (T t : l) {
                sb.append(t);
                sb.append(", ");
            }
            if (sb.length() > 2) {
                sb.delete(sb.length() - 2, sb.length());
            }
            sb.append("], ");
        }
        if (sb.length() > 2) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("]");
        return sb.toString();
    }

    public static String beauty2DArray(int[][] list) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int[] l : list) {
            sb.append("[");
            for (int t : l) {
                sb.append(String.valueOf(t));
                sb.append(",");
            }
            if (sb.length() > 1) {
                sb.delete(sb.length() - 1, sb.length());
            }
            sb.append("],");
        }
        if (sb.length() > 1) {
            sb.delete(sb.length() - 1, sb.length());
        }
        sb.append("]");
        return sb.toString();
    }

    public static <T> String beauty2DArray(T[][] list) {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for (T[] l : list) {
            sb.append(" [");
            for (T t : l) {
                sb.append(String.valueOf(t));
                sb.append(", ");
            }
            if (sb.length() > 2) {
                sb.delete(sb.length() - 2, sb.length());
            }
            sb.append("], \n");
        }
        if (sb.length() > 3) {
            sb.delete(sb.length() - 3, sb.length());
        }
        sb.append("\n]");
        return sb.toString();
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

    public static Map<Character, Integer> str2dic(String s, int l, int r) {
        Map<Character, Integer> target = new HashMap<>();
        for (int i = l; i <= r; i++) {
            int k = target.getOrDefault(s.charAt(i), 0);
            target.put(s.charAt(i), k + 1);
        }
        return target;
    }

    public static void swap(int[] array, int i, int j) {
        if (i == j) return;
        array[i] = array[i] ^ array[j];
        array[j] = array[i] ^ array[j];
        array[i] = array[i] ^ array[j];
    }

}
