import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q17144 {
    static int R, C, T;
    static int[][] map = new int[51][51];
    static int[] up = new int[2];
    static int[] down = new int[2];
    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    if (up[0] == 0) {
                        up[0] = i;
                        up[1] = j;
                    } else {
                        down[0] = i;
                        down[1] = j;
                    }
                }
            }
        }

        while (T-- > 0) {
            expansion();
            rotate(up[0] - 1, up[1], 0, 'u');
            rotate(down[0] + 1, down[1], 2, 'd');
        }

        long answer = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                answer += map[i][j];
            }
        }
        System.out.print(answer + 2);
    }

    static void expansion() {
        int[][] tmp_map = new int[R + 1][C + 1];
        tmp_map[up[0]][up[1]] = -1;
        tmp_map[down[0]][down[1]] = -1;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == -1 || map[i][j] == 0) {
                    continue;
                }
                int nDir = 0;
                for (int k = 0; k < 4; k++) {
                    int nr = i + delta[k][0];
                    int nc = j + delta[k][1];
                    if ((nr < 0 || nc < 0 || nr >= R || nc >= C) || map[nr][nc] == -1) {
                        continue;
                    }
                    tmp_map[nr][nc] += map[i][j] / 5;
                    nDir++;
                }
                tmp_map[i][j] += map[i][j] - ((map[i][j] / 5) * nDir);
            }
        }
        map = tmp_map;
    }

    // 순환 방향의 반대로 돌면서 진행
    static void rotate(int r, int c, int dir, char type) {
        int rotateCond = type == 'u' ? down[0] : up[0];
        int rotateDir = type == 'u' ? 1 : 3;
        while (true) {
            int nr = r + delta[dir][0];
            int nc = c + delta[dir][1];
            int tr = r + delta[(dir + 2) % 4][0];
            int tc = c + delta[(dir + 2) % 4][1];
            if (map[tr][tc] != -1) {
                map[tr][tc] = map[r][c];
            }

            // 경계에 부딪히면 방향 전환
            if ((nr < 0 || nc < 0 || nr >= R || nc >= C) || (nr == rotateCond)) {
                dir = (dir + rotateDir) % 4;
                r = r + delta[dir][0];
                c = c + delta[dir][1];
                continue;
            }

            // 공기 청정기로 돌아오면 끝
            if (map[nr][nc] == -1) {
                map[r][c] = 0;
                break;
            }

            // 좌표값 갱신
            r = nr;
            c = nc;
        }
    }
}
