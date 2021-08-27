import java.io.*;
import java.util.*;

public class Q17142 {
    static int N, M;
    static int[][] map = new int[50][50];
    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static ArrayList<int[]> virus = new ArrayList<>();
    static int nZero = 0;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    virus.add(new int[]{i, j});
                }
                if (map[i][j] == 0) {
                    nZero++;
                }
            }
        }

        if (nZero == 0) {
            System.out.print(0);
            return;
        }

        go(0, 0);

        if (min == Integer.MAX_VALUE) {
            System.out.print(-1);
        } else {
            System.out.print(min);
        }
    }

    static void go(int index, int cnt) {

        if (cnt == M) {
            getTime();
            return;
        }

        if (index == virus.size()) {
            return;
        }

        int r = virus.get(index)[0];
        int c = virus.get(index)[1];
        map[r][c] = -2;
        go(index + 1, cnt + 1);
        map[r][c] = 2;
        go(index + 1, cnt);
    }

    static void getTime() {
        int res = -1, nVirus = 0;
        Queue<int[]> q = new LinkedList<>();
        int[][] check = new int[50][50];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == -2) {
                    q.add(new int[]{i ,j});
                    check[i][j] = 1;
                }
            }
        }
        while (!q.isEmpty()) {
            int[] p = q.poll();
            for (int i = 0; i < 4; i++) {
                int nr = p[0] + delta[i][0];
                int nc = p[1] + delta[i][1];
                if ((nr < 0 || nc < 0 || nr >= N || nc >= N) || map[nr][nc] == 1) {
                    continue;
                }
                if (check[nr][nc] == 0) {
                    check[nr][nc] = check[p[0]][p[1]] + 1;
                    q.add(new int[]{nr, nc});
                    if (map[nr][nc] == 0) {
                        res = Math.max(res, check[nr][nc] - 1);
                        nVirus++;
                    }
                }
            }
        }

        // 모든 빈 칸에 바이러스를 퍼뜨림
        if (nZero == nVirus) {
            min = Math.min(min, res);
        }
    }
}
