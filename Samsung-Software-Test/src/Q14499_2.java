import java.io.*;
import java.util.*;

public class Q14499_2 {

    static int N, M, R, C, K;
    static int[] dice = new int[6];
    static int[][] map = new int[20][20];
    static int[][] delta = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < K; i++) {
            int dir = Integer.parseInt(st.nextToken()) - 1;
            int nr = R + delta[dir][0];
            int nc = C + delta[dir][1];
            if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
                continue;
            }
            R = nr;
            C = nc;
            roll(dir);
            sb.append(dice[0]).append("\n");
        }
        System.out.print(sb.toString());
    }

    static void roll(int dir) {

        int tmp = dice[0];

        if (dir == 0) {
            dice[0] = dice[3];
            dice[3] = dice[5];
            dice[5] = dice[2];
            dice[2] = tmp;
        }
        if (dir == 1) {
            dice[0] = dice[2];
            dice[2] = dice[5];
            dice[5] = dice[3];
            dice[3] = tmp;
        }
        if (dir == 2) {
            dice[0] = dice[1];
            dice[1] = dice[5];
            dice[5] = dice[4];
            dice[4] = tmp;
        }
        if (dir == 3) {
            dice[0] = dice[4];
            dice[4] = dice[5];
            dice[5] = dice[1];
            dice[1] = tmp;
        }

        if (map[R][C] == 0) {
            map[R][C] = dice[5];
        } else {
            dice[5] = map[R][C];
            map[R][C] = 0;
        }
    }
}
