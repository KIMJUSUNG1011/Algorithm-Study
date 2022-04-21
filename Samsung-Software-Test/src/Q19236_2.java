import java.io.*;
import java.util.*;

public class Q19236_2 {

    static int answer = -1;
    static int[] shark = new int[3];
    static int[][] delta = {{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] fish = new int[17][4];
        int[][] map = new int[4][4];
        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 4; j++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                fish[a][0] = i;
                fish[a][1] = j;
                fish[a][2] = b - 1;
                map[i][j] = a;
            }
        }

        int num = map[0][0];
        shark[2] = fish[num][2];
        fish[num][3] = 1;
        map[0][0] = -1;
        dfs(0, 0, num, map, fish);

        System.out.print(answer);
    }

    static void dfs(int r, int c, int sum, int[][] map, int[][] fish) {

        int prev = shark[2];

        move(map, fish);

        for (int i = 1; i <= 3; i++) {

            int nr = r + delta[shark[2]][0] * i;
            int nc = c + delta[shark[2]][1] * i;

            if ((nr < 0 || nc < 0 || nr >= 4 || nc >= 4) || map[nr][nc] == 0) {
                continue;
            }

            int[][] tmpMap = copy(map);
            int[][] tmpFish = copy(fish);

            int num = map[nr][nc];
            shark[2] = fish[num][2];
            tmpFish[num][3] = 1;
            tmpMap[r][c] = 0;
            tmpMap[nr][nc] = -1;
            dfs(nr, nc, sum + num, tmpMap, tmpFish);
            shark[2] = prev;
        }

        answer = Math.max(answer, sum);
    }

    static void move(int[][] map, int[][] fish) {

        for (int i = 1; i <= 16; i++) {

            // 잡아먹힌 물고기
            if (fish[i][3] == 1) {
                continue;
            }

            for (int j = 0; j < 8; j++) {

                int nr = fish[i][0] + delta[(fish[i][2] + j) % 8][0];
                int nc = fish[i][1] + delta[(fish[i][2] + j) % 8][1];

                if ((nr < 0 || nc < 0 || nr >= 4 || nc >= 4) || map[nr][nc] == -1) {
                    continue;
                }

                fish[i][2] = (fish[i][2] + j) % 8;

                // 이동하는 칸이 빈 칸인 경우
                if (map[nr][nc] == 0) {
                    map[fish[i][0]][fish[i][1]] = 0;
                    fish[i][0] = nr;
                    fish[i][1] = nc;
                    map[nr][nc] = i;
                } else {
                    int[] dst = fish[map[nr][nc]];
                    int[] tmp = {fish[i][0], fish[i][1]};

                    // 맵 교체
                    map[fish[i][0]][fish[i][1]] = map[nr][nc];
                    map[dst[0]][dst[1]] = i;

                    // 물고기 교환
                    fish[i][0] = dst[0];
                    fish[i][1] = dst[1];
                    dst[0] = tmp[0];
                    dst[1] = tmp[1];
                }

                break;
            }
        }
    }

    static int[][] copy(int[][] arr) {
        int R = arr.length, C = arr[0].length;
        int[][] dst = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                dst[i][j] = arr[i][j];
            }
        }
        return dst;
    }

    static void print(int[][] map) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(map[i][j] + " ");
            } System.out.println();
        } System.out.println();
    }
}
