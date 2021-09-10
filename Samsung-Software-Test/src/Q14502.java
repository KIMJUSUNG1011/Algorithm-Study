import java.io.*;
import java.util.*;

public class Q14502 {
    static int N, M;
    static int[][] board = new int[8][8];
    static boolean[][] visited = new boolean[8][8];
    static ArrayList<int[]> candidates = new ArrayList<>();
    static int nZero = -3;
    static int nInfection = 0;
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
                if (board[i][j] == 0) {
                    candidates.add(new int[]{i, j});
                    nZero++;
                }
            }
        }
        go(0, 0);
        System.out.print(max);
    }

    static void go(int index, int cnt) {

        // 종료 조건
        if (cnt == 3) {
            max = Math.max(max, getSafeSize());
            return;
        }

        if (index == candidates.size()) {
            return;
        }

        int r = candidates.get(index)[0];
        int c = candidates.get(index)[1];

        // r, c 에 벽을 세우는 경우
        board[r][c] = 1;
        go(index + 1, cnt + 1);
        board[r][c] = 0;

        // r, c 에 벽을 세우지 않는 경우
        go(index + 1, cnt);
    }

    static int getSafeSize() {
        nInfection = 0;
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 2) {
                    dfs(i, j);
                }
            }
        }
        return nZero - nInfection;
    }

    static void dfs(int r, int c) {
        visited[r][c] = true;
        for (int i = 0; i < 4; i++) {
            int nr = r + delta[i][0];
            int nc = c + delta[i][1];
            if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
                continue;
            }
            if (!visited[nr][nc] && board[nr][nc] != 1 && board[nr][nc] != 2) {
                dfs(nr, nc);
                nInfection++;
            }
        }
    }
}
