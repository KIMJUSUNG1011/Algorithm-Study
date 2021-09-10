import java.io.*;
import java.util.*;

public class Q17822 {
    static int N, M, T;
    static int[][] board = new int[50][50];
    static int[][] visited = new int[50][50];
    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static boolean flag = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            for (int j = x; j <= N; j += x) {
                rotate(j - 1, d, k);
            }
            remove();
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] != -1) {
                    answer += board[i][j];
                }
            }
        }
        System.out.print(answer);
    }

    static void remove() {
        int cnt = 0, sum = 0;
        flag = true;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = 0;
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] == 0 && board[i][j] != -1) {
                    sum += dfs(i, j, board[i][j], 0);
                    cnt++;
                }
            }
        }

        // 삭제된 수가 없음
        if (flag) {
            Double avg = (double)sum / cnt;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (board[i][j] != -1) {
                        board[i][j] += avg.compareTo((double)board[i][j]);
                    }
                }
            }
        }
    }

    static int dfs(int r, int c, int val, int depth) {
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            int nr = r + delta[i][0];
            int nc = c + delta[i][1];
            if (nr >= 0 && nr < N) {
                if (nc == -1) {
                    nc = M - 1;
                }
                if (nc == M) {
                    nc = 0;
                }
                if (visited[nr][nc] == 0 && board[nr][nc] == val) {
                    visited[nr][nc] = 1;
                    board[nr][nc] = -1;
                    sum += dfs(nr, nc, val, ++depth);
                    flag = false;
                }
            }
        }

        if (depth == 0 && sum != 0) {
            board[r][c] = -1;
        }

        return val + sum;
    }

    static void rotate(int num, int d, int k) {
        int[] tmp = new int[M];
        d = d == 0 ? k : M - k;
        for (int i = 0; i < M; i++) {
            tmp[(i + d) % M] = board[num][i];
        }
        for (int i = 0; i < M; i++) {
            board[num][i] = tmp[i];
        }
    }
}
