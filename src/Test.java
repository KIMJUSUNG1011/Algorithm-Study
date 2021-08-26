import java.util.*;

public class Test {
    static int[] arr = {0, 0, 2, 3};
    static boolean[] visited = new boolean[4];

    public static void main(String[] args) {
        go(0);
    }

    static void go(int index) {

        if (index == arr.length) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
           if (!visited[i]) {
               visited[i] = true;
               go(index + 1);
               visited[i] = false;
           }
        }
    }
}
