package psn.tangdaye.tool;

public class Tools {
    public static void printJavaArray(String pythonArray) {
        System.out.println(pythonArray.replace('[', '{').replace(']', '}'));
    }

    public static void main(String[] args) {
        printJavaArray("[[1,2,3,4],[5,6,7,8],[9,10,11,12]]");
    }
}
