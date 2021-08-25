import java.io.*;
import java.util.*;

public class Q14500 {
    static int N, M;
    static int[][] board = new int[500][500];
    static boolean[][] visited = new boolean[500][500];
    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, 0, 0);
                visited[i][j] = false;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 모서리 지점을 중심으로는 예외 형태를 만들 수 없음
                if ((i == 0 || i == N - 1) && (j == 0 || j == M - 1)) {
                    continue;
                }
                except(i ,j);
            }
        }

        System.out.print(max);
    }

    static void dfs(int r, int c, int sum, int index) {

        // 종료 조건
        if (index == 4) {
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nr = r + delta[i][0];
            int nc = c + delta[i][1];
            if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
                continue;
            }
            if (!visited[nr][nc]) {
                visited[nr][nc] = true;
                dfs(nr, nc, sum + board[r][c], index + 1);
                visited[nr][nc] = false;
            }
        }
    }

    static void except(int r, int c) {
        int sum = board[r][c];
        for (int i = 0; i < 4; i++) {
            int nr = r + delta[i][0];
            int nc = c + delta[i][1];
            if (nr >= 0 && nc >= 0 && nr < N && nc < M) {
                sum += board[nr][nc];
            }
        }

        if (r == 0 || r == N - 1 || c == 0 || c == M - 1) {
            max = Math.max(max, sum);
        } else {
            for (int i = 0; i < 4; i++) {
                int nr = r + delta[i][0];
                int nc = c + delta[i][1];
                max = Math.max(max, sum - board[nr][nc]);
            }
        }
    }
}
