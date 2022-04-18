import java.io.*;
import java.util.*;

public class Q23288_2 {

    static long answer = 0;
    static int N, M, K, groupNum = 1;
    static int[] dice = {0, 1, 2, 3, 4, 5, 6};
    static int[] group = new int[401];
    static int[][] map = new int[20][20];
    static int[][] check = new int[20][20];
    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (check[i][j] == 0) {
                    group[groupNum] = dfs(i, j);
                    groupNum++;
                }
            }
        }

        int dir = 1;
        int r = 0, c = 0;

        for (int i = 0; i < K; i++) {

            r += delta[dir][0];
            c += delta[dir][1];

            if (r < 0 || c < 0 || r >= N || c >= M) {
                dir = (dir + 2) % 4;
                r += delta[dir][0] * 2;
                c += delta[dir][1] * 2;
            }

            rotate(dir);
            answer += map[r][c] * group[check[r][c]];

            if (dice[6] > map[r][c]) {
                dir = (dir + 1) % 4;
            } else if (dice[6] < map[r][c]) {
                dir = (dir + 3) % 4;
            }
        }

        System.out.print(answer);
    }

    static void rotate(int dir) {

        int tmp = dice[1];

        if (dir == 0) {
            dice[1] = dice[5];
            dice[5] = dice[6];
            dice[6] = dice[2];
            dice[2] = tmp;
        }
        if (dir == 1) {
            dice[1] = dice[4];
            dice[4] = dice[6];
            dice[6] = dice[3];
            dice[3] = tmp;
        }
        if (dir == 2) {
            dice[1] = dice[2];
            dice[2] = dice[6];
            dice[6] = dice[5];
            dice[5] = tmp;
        }
        if (dir == 3) {
            dice[1] = dice[3];
            dice[3] = dice[6];
            dice[6] = dice[4];
            dice[4] = tmp;
        }
    }

    static int dfs(int r, int c) {

        int sum = 1;

        check[r][c] = groupNum;

        for (int i = 0; i < 4; i++) {

            int nr = r + delta[i][0];
            int nc = c + delta[i][1];

            if ((nr < 0 || nc < 0 || nr >= N || nc >= M) || map[r][c] != map[nr][nc]) {
                continue;
            }

            if (check[nr][nc] == 0) {
                sum += dfs(nr, nc);
            }
        }

        return sum;
    }
}
