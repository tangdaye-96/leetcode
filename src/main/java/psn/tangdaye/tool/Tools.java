package psn.tangdaye.tool;

public class Tools {
    public static void printJavaArray(String pythonArray) {
        System.out.println(pythonArray.replace('[', '{').replace(']', '}'));
    }
}
