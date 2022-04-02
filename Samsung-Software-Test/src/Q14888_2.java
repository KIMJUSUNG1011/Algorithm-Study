import java.io.*;
import java.util.*;

public class Q14888_2 {

    static int N, max, min;
    static int[] A = new int[100];
    static int[] op = new int[4];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 4; i++) {
            op[i] = Integer.parseInt(st.nextToken());
        }

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        go(1, A[0]);
        System.out.println(max);
        System.out.println(min);
    }

    static void go(int index, int res) {

        if (index >= N) {
            max = Math.max(max, res);
            min = Math.min(min, res);
            return;
        }

        if (op[0] > 0) {
            op[0]--;
            go(index + 1, res + A[index]);
            op[0]++;
        }

        if (op[1] > 0) {
            op[1]--;
            go(index + 1, res - A[index]);
            op[1]++;
        }

        if (op[2] > 0) {
            op[2]--;
            go(index + 1, res * A[index]);
            op[2]++;
        }

        if (op[3] > 0) {
            op[3]--;
            go(index + 1, res / A[index]);
            op[3]++;
        }
    }
}
