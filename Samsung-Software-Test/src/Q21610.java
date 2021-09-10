import java.io.*;
import java.util.*;

public class Q21610 {
    static int N, M;
    static int[][] board = new int[50][50];
    static boolean[][] clouds = new boolean[50][50];
    static boolean[][] visited = new boolean[50][50];
    static int[][] delta = {
            {0, -1}, {-1, -1}, {-1, 0}, {-1, 1},
            {0, 1}, {1, 1}, {1, 0}, {1, -1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = N - 2; i < N; i++) {
            for (int j = 0; j < 2; j++) {
                clouds[i][j] = true;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            visited = new boolean[N][N];
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (clouds[j][k]) {
                        move(j, k, d, s);
                        clouds[j][k] = false;
                    }
                }
            }

            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (visited[j][k]) {
                        copy(j, k);
                    }
                }
            }

            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (board[j][k] >= 2 && !visited[j][k]) {
                        board[j][k] -= 2;
                        clouds[j][k] = true;
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] > 0) {
                    answer += board[i][j];
                }
            }
        }
        System.out.print(answer);
    }

    static void move(int r, int c, int d, int s) {
        for (int i = 0; i < s; i++) {
            r += delta[d][0];
            c += delta[d][1];

            if (r == -1) {
                r = N - 1;
            }
            if (r == N) {
                r = 0;
            }
            if (c == -1) {
                c = N - 1;
            }
            if (c == N) {
                c = 0;
            }
        }
        visited[r][c] = true;
        board[r][c]++;
    }

    static void copy(int r, int c) {
        int cnt = 0;
        for (int k = 1; k < 8; k += 2) {
            int nr = r + delta[k][0];
            int nc = c + delta[k][1];
            if ((nr >= 0 && nc >= 0 && nr < N && nc < N) && board[nr][nc] > 0) {
                cnt++;
            }
        }
        if (cnt > 0) {
            board[r][c] += cnt;
        }
    }
}
