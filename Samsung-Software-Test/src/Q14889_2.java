import java.io.*;
import java.util.*;

public class Q14889_2 {

    static int N, cnt, answer = Integer.MAX_VALUE;
    static int[][] S = new int[20][20];
    static int[] order = new int[10];
    static int[] score = new int[1000000];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        go(0, 0);

        for (int i = 0; i < cnt / 2; i++) {
            answer = Math.min(answer, Math.abs(score[i] - score[cnt - i - 1]));
        }

        System.out.print(answer);
    }

    static void go(int index, int pos) {

        if (pos == N / 2) {

            int sum = 0;
            for (int i = 0; i < N / 2; i++) {
                for (int j = 0; j < N / 2; j++) {
                    sum += S[order[i]][order[j]];
                }
            }

            score[cnt++] = sum;
            return;
        }

        if (index == N) {
            return;
        }

        order[pos] = index;
        go(index + 1, pos + 1);
        order[pos] = 0;
        go(index + 1, pos);
    }
}
