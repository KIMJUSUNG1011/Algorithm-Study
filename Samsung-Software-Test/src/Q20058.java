import java.io.*;
import java.util.*;

public class Q20058 {
    static int N, Q, len;
    static int[][] board = new int[64][64];
    static boolean[][] visited = new boolean[64][64];
    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int sum = 0, max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        len = (int)Math.pow(2, N);
        for (int i = 0; i < len; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < len; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < Q; i++) {
            int L = Integer.parseInt(st.nextToken());
            solve(L);
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                sum += board[i][j];
            }
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (!visited[i][j] && board[i][j] != 0) {
                    bfs(i, j);
                }
            }
        }
        System.out.println(sum);
        System.out.print(max);
    }

    static void solve(int L) {
        int partLen = (int)Math.pow(2, L);
        int[][] tmpBoard = new int[len][len];
        for (int i = 0; i <= len - 1; i += partLen) {
            for (int j = 0; j <= len - 1; j += partLen) {
                rotate(i, j, partLen, tmpBoard);
            }
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                board[i][j] = tmpBoard[i][j];
            }
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (board[i][j] != 0) {
                    remove(i, j, tmpBoard);
                }
            }
        }
    }

    static void rotate(int r, int c, int partLen, int[][] tmp) {
        int s1 = r + (c + partLen - 1);
        int s2 = r - c;
        for (int i = r; i < r + partLen; i++) {
            for (int j = c; j < c + partLen; j++) {
                tmp[s2 + j][s1 - i] = board[i][j];
            }
        }
    }

    static void remove(int r, int c, int[][] tmp) {
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            int nr = r + delta[i][0];
            int nc = c + delta[i][1];
            if ((nr >= 0 && nc >= 0 && nr < len && nc < len) && tmp[nr][nc] > 0) {
                cnt++;
            }
        }
        if (cnt < 3) {
            board[r][c]--;
        }
    }

    static void bfs(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        visited[r][c] = true;
        q.add(new int[]{r, c});
        int cnt = 0;
        while (!q.isEmpty()) {
            int[] p = q.poll();
            cnt++;
            for (int i = 0; i < 4; i++) {
                int nr = p[0] + delta[i][0];
                int nc = p[1] + delta[i][1];
                if ((nr >= 0 && nc >= 0 && nr < len && nc < len) && board[nr][nc] > 0) {
                    if (!visited[nr][nc]) {
                        visited[nr][nc] = true;
                        q.add(new int[]{nr, nc});
                    }
                }
            }
        }
        max = Math.max(max, cnt);
    }
}
