import java.io.*;
import java.util.*;

public class Q14502_2 {

    static int N, M, nDirty, answer = Integer.MIN_VALUE;
    static int[][] map = new int[8][8];
    static int[][] check = new int[8][8];
    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static List<int[]> zeros = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    zeros.add(new int[]{i, j});
                }
            }
        }

        go(0, 0);
        System.out.print(answer);
    }

    static void go(int index, int cnt) {

        if (cnt == 3) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 2) {
                        check[i][j] = 1;
                        go2(i, j);
                    }
                }
            }
            answer = Math.max(answer, zeros.size() - 3 - nDirty);
            check = new int[N][M];
            nDirty = 0;
            return;
        }

        if (index == zeros.size()) {
            return;
        }

        int r = zeros.get(index)[0];
        int c = zeros.get(index)[1];

        map[r][c] = 1;
        go(index + 1, cnt + 1);
        map[r][c] = 0;
        go(index + 1, cnt);
    }

    static void go2(int r, int c) {

        for (int i = 0; i < 4; i++) {

            int nr = r + delta[i][0];
            int nc = c + delta[i][1];

            if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
                continue;
            }

            if (map[nr][nc] == 1) {
                continue;
            }

            if (map[nr][nc] == 0 && check[nr][nc] == 0) {
                check[nr][nc] = 1;
                nDirty++;
                go2(nr, nc);
            }
        }
    }
}
