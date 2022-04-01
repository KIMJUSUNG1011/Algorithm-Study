import java.io.*;
import java.util.*;

public class Q14501_2 {

    static int N, answer;
    static int[][] cal = new int[16][2];
    static int[] d = new int[17];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            cal[i][0] = Integer.parseInt(st.nextToken());
            cal[i][1] = Integer.parseInt(st.nextToken());
        }

        go(1);

        System.out.println(answer);
    }

    static void go(int index) {

        if (index > N) {
            answer = Math.max(answer, d[index]);
            return;
        }

        int T = cal[index][0];
        int P = cal[index][1];

        if (index + T <= N + 1 && d[index + T] < d[index] + P) {
            d[index + T] = d[index] + P;
            go(index + T);
        }

        if (d[index + 1] < d[index]) {
            d[index + 1] = d[index];
        }

        go(index + 1);
    }
}

/*
d[i] : i 일 전까지 얻은 최대 수익
 */
