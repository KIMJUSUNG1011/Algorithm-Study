import java.io.*;
import java.util.*;

public class Q21610_2 {

    static int N, M, answer;
    static int[][] map = new int[50][50];
    static int[][] clouds = new int[50][50];
    static int[][] tmp = new int[50][50];
    static int[] d = new int[100];
    static int[] s = new int[100];
    static int[][] delta = {
            {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}
    };

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            d[i] = Integer.parseInt(st.nextToken()) - 1;
            s[i] = Integer.parseInt(st.nextToken());
        }

        clouds[N - 2][0] = clouds[N - 1][0] = clouds[N - 2][1] = clouds[N - 1][1] = 1;

        for (int i = 0; i < M; i++) {

            // 구름 이동
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (clouds[j][k] == 1) {
                        move(j, k, i);
                        clouds[j][k] = 0;
                    }
                }
            }

            // 물 복사
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (tmp[j][k] == 1) {
                        waterCopy(j, k);
                    }
                }
            }

            // 구름 생성
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (tmp[j][k] == 1) {
                        tmp[j][k] = 0;
                        continue;
                    }
                    if (map[j][k] >= 2) {
                        clouds[j][k] = 1;
                        map[j][k] -= 2;
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer += map[i][j];
            }
        }

        System.out.print(answer);
    }

    static void move(int r, int c, int m) {

        int nr, nc;

        for (int i = 0; i < s[m]; i++) {

            nr = r + delta[d[m]][0];
            nc = c + delta[d[m]][1];

            if (nr < 0) {
                nr = N - 1;
            }
            if (nr >= N) {
                nr = 0;
            }
            if (nc < 0) {
                nc = N - 1;
            }
            if (nc >= N) {
                nc = 0;
            }

            r = nr;
            c = nc;
        }

        tmp[r][c] = 1;
        map[r][c]++;
    }

    static void waterCopy(int r, int c) {

        for (int i = 1; i < 8; i += 2) {
            int nr = r + delta[i][0];
            int nc = c + delta[i][1];
            if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
                continue;
            }
            if (map[nr][nc] > 0) {
                map[r][c]++;
            }
        }
    }
}
