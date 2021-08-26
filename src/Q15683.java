import java.io.*;
import java.util.*;

public class Q15683 {
    static int N, M;
    static int[][] cctv = new int[8][2];
    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int nCCTV = 0;
    static int min = Integer.MAX_VALUE;

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
                if (map[i][j] != 0 && map[i][j] != 6) {
                    cctv[nCCTV][0] = i;
                    cctv[nCCTV][1] = j;
                    nCCTV++;
                }
            }
        }

        go(-1, -1, map);
        System.out.print(min);
    }

    static void go(int index, int dir, int[][] map) {

        // 종료 조건
        if (index == nCCTV) {
            int nBlind = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 0) {
                        nBlind++;
                    }
                }
            }
            min = Math.min(min, nBlind);
            return;
        }

        if (index != -1) {
            int[] p = cctv[index];
            shoot(p[0], p[1], dir, map);
        }

        for (int i = 0; i < 4; i++) {
            int[][] tmp = new int[N][M];
            copy(tmp, map);
            go(index + 1, i, tmp);
        }
    }

    static void shoot(int r, int c, int dir, int[][] map) {
        int[] dirs = {dir, -1, -1, -1};
        int num = map[r][c];
        if (num == 2) {
            dirs[1] = (dir + 2) % 4;
        } else if (num == 3) {
            dirs[1] = (dir + 1) % 4;
        } else if (num == 4) {
            dirs[1] = (dir + 1) % 4;
            dirs[2] = (dir + 3) % 4;
        } else if (num == 5){
            dirs[1] = (dir + 1) % 4;
            dirs[2] = (dir + 2) % 4;
            dirs[3] = (dir + 3) % 4;
        }

        // 감시받는 영역 표시
        for (int i = 0; i < 4; i++) {
            int tr = r, tc = c;
            int d = dirs[i];
            if (d == -1) {
                break;
            }
            while (true) {
                int nr = tr + delta[d][0];
                int nc = tc + delta[d][1];
                if ((nr < 0 || nc < 0 || nr >= N || nc >= M) || map[nr][nc] == 6) {
                    break;
                }
                if (map[nr][nc] == 0) {
                    map[nr][nc] = -1;
                }
                tr = nr;
                tc = nc;
            }
        }
    }

    static void copy(int[][] t, int[][] o) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                t[i][j] = o[i][j];
            }
        }
    }
}
