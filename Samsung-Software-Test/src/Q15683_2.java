import java.io.*;
import java.util.*;

public class Q15683_2 {

    static int N, M, nZero, answer = Integer.MAX_VALUE;
    static List<int[]> cctvs = new ArrayList<>();
    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] >= 1 && map[i][j] <= 5) {
                    cctvs.add(new int[]{i, j});
                }
                if (map[i][j] == 0) {
                    nZero++;
                }
            }
        }

        dfs(0, map, 0);

        System.out.print(answer);
    }

    static void dfs(int index, int[][] map, int nGo) {

        if (index == cctvs.size()) {
            answer = Math.min(answer, nZero - nGo);
            return;
        }

        int[][] tmp;
        int r = cctvs.get(index)[0];
        int c = cctvs.get(index)[1];

        if (map[r][c] == 1) {
            for (int i = 0; i < 4; i++) {
                tmp = getCopy(map);
                dfs(index + 1, tmp, nGo + go(r, c, i, tmp));
            }
        }

        if (map[r][c] == 2) {
            for (int i = 0; i < 2; i++) {
                tmp = getCopy(map);
                dfs(index + 1, tmp, nGo + go(r, c, i, tmp) + go(r, c, i + 2, tmp));
            }
        }

        if (map[r][c] == 3) {
            for (int i = 0; i < 4; i++) {
                tmp = getCopy(map);
                dfs(index + 1, tmp, nGo + go(r, c, i, tmp) + go(r, c, (i + 1) % 4, tmp));
            }
        }

        if (map[r][c] == 4) {
            for (int i = 0; i < 4; i++) {
                tmp = getCopy(map);
                dfs(index + 1, tmp, nGo + go(r, c, i, tmp) + go(r, c, (i + 1) % 4, tmp) + go(r, c, (i + 3) % 4, tmp));
            }
        }

        if (map[r][c] == 5) {
            tmp = getCopy(map);
            int cnt = 0;
            for (int i = 0; i < 4; i++) {
                cnt += go(r, c, i, tmp);
            }
            dfs(index + 1, tmp, nGo + cnt);
        }
    }

    // r, c 에서 dir 방향으로 감시영역 처리
    static int go(int r, int c, int dir, int[][] map) {

        int nGo = 0;

        while (true) {
            int nr = r + delta[dir][0];
            int nc = c + delta[dir][1];
            if ((nr < 0 || nc < 0 || nr >= N || nc >= M) || map[nr][nc] == 6) {
                break;
            }

            // 감시영역 생성
            if (map[nr][nc] == 0) {
                map[nr][nc] = -1;
                nGo++;
            }

            r = nr;
            c = nc;
        }

        return nGo;
    }

    static int[][] getCopy(int[][] src) {
        int[][] dst = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dst[i][j] = src[i][j];
            }
        }
        return dst;
    }
}
