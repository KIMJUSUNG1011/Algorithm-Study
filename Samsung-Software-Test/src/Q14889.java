import java.io.*;
import java.util.*;

public class Q14889 {
    static int N;
    static int[][] board = new int[20][20];
    static boolean[] check = new boolean[20];
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        go(0, 0);
        System.out.print(min);
    }

    static void go(int index, int cnt) {

        if (cnt == N / 2) {
            min = Math.min(min, diff());
            return;
        }

        if (index == N) {
            return;
        }

        // index 를 start 팀으로 선택하는 경우
        check[index] = true;
        go(index + 1, cnt + 1);
        check[index] = false;

        // index 를 link 팀으로 선택하는 경우
        go(index + 1, cnt);
    }

    static int diff() {
        int[] t1 = new int[N / 2];
        int[] t2 = new int[N / 2];
        int idx1 = 0, idx2 = 0;
        for (int i = 0; i < N; i++) {
            if (check[i]) {
                t1[idx1++] = i;
            } else {
                t2[idx2++] = i;
            }
        }

        int score1 = 0, score2 = 0;
        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < N / 2; j++) {
                if (i != j) {
                    score1 += board[t1[i]][t1[j]];
                    score2 += board[t2[i]][t2[j]];
                }
            }
        }

        return Math.abs(score1 - score2);
    }
}
