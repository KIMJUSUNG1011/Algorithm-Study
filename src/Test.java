import java.util.*;

public class Test {
    static int[] arr = {0, 1, 2, 3};
    static int[] order = new int[2];

    public static void main(String[] args) {
        go(0, 0);
    }

    static void go(int index, int cnt) {

        if (cnt == 2) {
            for (int o : order) {
                System.out.print(o + " ");
            } System.out.println();
            return;
        }

        if (index == 4) {
            return;
        }

        order[cnt] = index;
        go(index + 1, cnt + 1);
        order[cnt] = 0;

        go(index + 1, cnt);
    }

    /*
    static void go(int index, int start) {

        if (index == 2) {
            for (int o : order) {
                System.out.print(o + " ");
            } System.out.println();
            return;
        }

        for (int i = start; i < 4; i++) {
            if (!visited[i]) {
                visited[i] = true;
                order[index] = arr[i];
                go(index + 1, i + 1);
                order[index] = 0;
                visited[i] = false;
            }
        }
    }
     */
}
