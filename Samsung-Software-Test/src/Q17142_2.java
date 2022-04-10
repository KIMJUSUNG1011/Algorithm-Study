import java.io.*;
import java.util.*;

public class Q17142_2 {

    static int N, M, nZero = 0, answer = Integer.MAX_VALUE;
    static int[][] map = new int[50][50];
    static List<int[]> pairs = new ArrayList<>();
    static Queue<int[]> q = new LinkedList<>();
    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    nZero++;
                }
                if (map[i][j] == 2) {
                    pairs.add(new int[]{i, j});
                }
            }
        }

        dfs(0, 0);

        if (answer == Integer.MAX_VALUE) {
            System.out.print(-1);
        } else {
            System.out.print(answer);
        }
    }

    static void dfs(int index, int cnt) {

        if (cnt == M) {
            bfs();
            return;
        }

        if (index == pairs.size()) {
            return;
        }

        int r = pairs.get(index)[0];
        int c = pairs.get(index)[1];

        map[r][c] = -2;
        dfs(index + 1, cnt + 1);
        map[r][c] = 2;
        dfs(index + 1, cnt);
    }

    static void bfs() {

        int[][] check = new int[N][N];
        int nCopy = 0, time = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == -2) {
                    q.add(new int[]{i, j});
                    check[i][j] = 1;
                }
            }
        }

        while (!q.isEmpty()) {
            int[] p = q.poll();
            if (map[p[0]][p[1]] == 0) {
                time = Math.max(time, check[p[0]][p[1]] - 1);
                nCopy++;
            }
            for (int i = 0; i < 4; i++) {
                int nr = p[0] + delta[i][0];
                int nc = p[1] + delta[i][1];
                if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
                    continue;
                }
                if (check[nr][nc] == 0 && map[nr][nc] != 1) {
                    q.add(new int[]{nr, nc});
                    check[nr][nc] = check[p[0]][p[1]] + 1;
                }
            }
        }

        if (nCopy == nZero) {
            answer = Math.min(answer, time);
        }
    }
}
