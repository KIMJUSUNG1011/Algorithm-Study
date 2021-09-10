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
                dfs(i, j, 0, 0);
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
                visited[r][c] = true;
                dfs(nr, nc, sum + board[r][c], index + 1);
                visited[r][c] = false;
            }
        }
    }

    static void except(int r, int c) {
        int sum = board[r][c];
        int cnt = 4;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            int nr = r + delta[i][0];
            int nc = c + delta[i][1];
            if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
                cnt--;
                continue;
            }
            min = Math.min(min, board[nr][nc]);
            sum += board[nr][nc];
        }

        // 날개 중 가장 작은 것을 제외
        if (cnt == 4) {
            sum -= min;
        }

        max = Math.max(max, sum);
    }
}
