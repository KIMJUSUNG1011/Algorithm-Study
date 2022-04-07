import java.io.*;
import java.util.*;

public class Q15683_2 {

    static int N, M, answer = Integer.MAX_VALUE;
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
            }
        }

        go(0, map);

        System.out.print(answer);
    }

    static void go(int index, int[][] map) {

        if (index == cctvs.size()) {
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 0) {
                        cnt++;
                    }
                }
            }

            answer = Math.min(answer, cnt);
            return;
        }

        int r = cctvs.get(index)[0];
        int c = cctvs.get(index)[1];
        int num = map[r][c];
        int[][] tmp;

        if (num == 1) {
            for (int i = 0; i < 4; i++) {
                tmp = getCopy(map);
                process(r, c, i, tmp);
                go(index + 1, tmp);
            }
        }

        if (num == 2) {
            for (int i = 0; i < 2; i++) {
                tmp = getCopy(map);
                process(r, c, i, tmp);
                process(r, c, i + 2, tmp);
                go(index + 1, tmp);
            }
        }

        if (num == 3) {
            for (int i = 0; i < 4; i++) {
                tmp = getCopy(map);
                process(r, c, i, tmp);
                process(r, c, (i + 1) % 4, tmp);
                go(index + 1, tmp);
            }
        }

        if (num == 4) {
            for (int i = 0; i < 4; i++) {
                tmp = getCopy(map);
                process(r, c, i, tmp);
                process(r, c, (i + 1) % 4, tmp);
                process(r, c, (i + 3) % 4, tmp);
                go(index + 1, tmp);
            }
        }

        if (num == 5) {
            tmp = getCopy(map);
            for (int i = 0; i < 4; i++) { process(r, c, i, tmp); }
            go(index + 1, tmp);
            for (int i = 0; i < 4; i++) { process(r, c, i, tmp); }
        }
    }

    // r, c 에서 dir 방향으로 감시영역 처리
    static void process(int r, int c, int dir, int[][] map) {

        while (true) {
            int nr = r + delta[dir][0];
            int nc = c + delta[dir][1];
            if ((nr < 0 || nc < 0 || nr >= N || nc >= M) || map[nr][nc] == 6) {
                break;
            }

            // 감시영역 생성
            if (map[nr][nc] == 0) {
                map[nr][nc] = -1;
            }

            r = nr;
            c = nc;
        }
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
