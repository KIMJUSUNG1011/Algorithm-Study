import java.io.*;
import java.util.*;

public class Q14499 {
    static int N, M, K, r, c;
    static int[][] board = new int[20][20];
    static int[] dice = new int[6];
    static int[][] delta = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < K; i++) {
            int d = Integer.parseInt(st.nextToken()) - 1;
            int nr = r + delta[d][0];
            int nc = c + delta[d][1];
            if (nr >= 0 && nc >= 0 && nr < N && nc < M) {
                roll(d);
                sb.append(dice[0]).append("\n");
                int val = board[nr][nc];
                if (val == 0) {
                    board[nr][nc] = dice[5];
                } else {
                    dice[5] = board[nr][nc];
                    board[nr][nc] = 0;
                }
                r = nr;
                c = nc;
            }
        }
        System.out.print(sb);
    }

    static void roll(int d) {
        int t = dice[0];
        if (d == 0) {
            dice[0] = dice[3];
            dice[3] = dice[5];
            dice[5] = dice[2];
            dice[2] = t;
        } else if (d == 1) {
            dice[0] = dice[2];
            dice[2] = dice[5];
            dice[5] = dice[3];
            dice[3] = t;
        } else if (d == 2) {
            dice[0] = dice[1];
            dice[1] = dice[5];
            dice[5] = dice[4];
            dice[4] = t;
        } else {
            dice[0] = dice[4];
            dice[4] = dice[5];
            dice[5] = dice[1];
            dice[1] = t;
        }
    }
}
