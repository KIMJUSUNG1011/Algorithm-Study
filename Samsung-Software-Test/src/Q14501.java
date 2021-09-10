import java.io.*;
import java.util.*;

public class Q14501 {
    static int N;
    static int[] T = new int[16];
    static int[] P = new int[16];
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        go(1, 0);
        System.out.print(max);
    }

    static void go(int index, int sum) {

        // 종료 조건
        if (index > N) {
            max = Math.max(max, sum);
            return;
        }

        int t = T[index];

        // index 일의 상담을 하는 경우
        if (index + t <= N + 1) {
            go(index + t, sum + P[index]);
        }
        // index 일의 상담을 안하는 경우
        go(index + 1, sum);
    }
}
