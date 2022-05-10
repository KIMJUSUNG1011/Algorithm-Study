import java.io.*;
import java.util.*;

public class Q16236_2 {

    static int N, time = 0, sharkSize = 2;
    static int[][] map = new int[20][20];
    static int[] shark = new int[3];
    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static Queue<int[]> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    shark[0] = i;
                    shark[1] = j;
                    map[i][j] = 0;
                }
            }
        }

        while (true) {
            if (bfs() == -1) {
                break;
            }
        }

        System.out.print(time);
    }

    static int bfs() {

        int[][] check = new int[20][20];
        int dist = -1, flag = 0;
        int targetR = N, targetC = N;

        check[shark[0]][shark[1]] = 1;
        q.add(new int[]{shark[0], shark[1]});

        while (!q.isEmpty()) {

            dist++;

            int size = q.size();

            for (int i = 0; i < size; i++) {

                int[] p = q.poll();

                // 먹을 수 있는 경우
                if (map[p[0]][p[1]] > 0 && map[p[0]][p[1]] < sharkSize) {
                    if (p[0] < targetR || (p[0] == targetR && p[1] < targetC)) {
                        targetR = p[0];
                        targetC = p[1];
                        flag = 1;
                    }
                }

                for (int j = 0; j < 4; j++) {
                    int nr = p[0] + delta[j][0];
                    int nc = p[1] + delta[j][1];
                    if ((nr < 0 || nc < 0 || nr >= N || nc >= N) || map[nr][nc] > sharkSize) {
                        continue;
                    }
                    if (check[nr][nc] == 0) {
                        check[nr][nc] = 1;
                        q.add(new int[]{nr, nc});
                    }
                }
            }

            if (flag == 1) {
                while (!q.isEmpty()) { q.poll(); }
                break;
            }
        }

        if (flag == 1) {

            shark[0] = targetR;
            shark[1] = targetC;
            shark[2]++;

            if (shark[2] == sharkSize) {
                shark[2] = 0;
                sharkSize++;
            }

            map[targetR][targetC] = 0;
            time += dist;
            return 0;
        }

        return -1;
    }
}
