import java.io.*;
import java.util.*;

public class Q14500_2 {

    static int N, M, answer;
    static int[][] map = new int[500][500];
    static int[][] check = new int[500][500];
    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                go(0, 0, i, j);
                wing(i, j);
            }
        }

        System.out.print(answer);
    }

    static void go(int index, int sum, int r, int c) {

        if (r < 0 || c < 0 || r >= N || c >= M) {
            return;
        }

        if (index == 4) {
            answer = Math.max(answer, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (check[r][c] == 0) {
                check[r][c] = 1;
                go(index + 1, sum + map[r][c], r + delta[i][0], c + delta[i][1]);
                check[r][c] = 0;
            }
        }
    }

    static void wing(int r, int c) {

        int sum = map[r][c];
        int nWing = 0;

        for (int i = 0; i < 4; i++) {
            int nr = r + delta[i][0];
            int nc = c + delta[i][1];
            if (nr >= 0 && nc >= 0 && nr < N && nc < M) {
                nWing++;
                sum += map[nr][nc];
            }
        }

        if (nWing == 3) {
            answer = Math.max(answer, sum);
            return;
        }

        if (nWing == 4) {
            for (int i = 0; i < 4; i++) {
                answer = Math.max(answer, sum - map[r + delta[i][0]][c + delta[i][1]]);
            }
            return;
        }
    }
}
