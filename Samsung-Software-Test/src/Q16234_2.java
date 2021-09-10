import java.io.*;
import java.util.*;

public class Q16234_2 {
    static int N, L, R;
    static int[][] map = new int[51][51];
    static int[][] visited = new int[51][51];
    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static Union[] unions = new Union[2501];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0;
        while (true) {
            int cnt = 1;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j] == 0) {
                        bfs(i, j, cnt++);
                    }
                }
            }

            if (cnt == N * N + 1) {
                System.out.print(day);
                return;
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int num = visited[i][j];
                    if (unions[num].cnt != 1) {
                        map[i][j] = unions[num].avg;
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    visited[i][j] = 0;
                }
            }
            day++;
        }
    }

    static void bfs(int r, int c, int cnt) {
        unions[cnt] = new Union(0, 0, 0);
        Queue<int[]> q = new LinkedList<>();
        visited[r][c] = cnt;
        q.add(new int[]{r, c});
        while (!q.isEmpty()) {
            int[] p = q.poll();
            unions[cnt].cnt++;
            unions[cnt].sum += map[p[0]][p[1]];

            for (int i = 0; i < 4; i++) {
                int nr = p[0] + delta[i][0];
                int nc = p[1] + delta[i][1];
                if (nr >= 0 && nc >= 0 && nr < N && nc < N) {
                    if (visited[nr][nc] == 0) {
                        int diff = Math.abs(map[p[0]][p[1]] - map[nr][nc]);
                        if (diff >= L && diff <= R) {
                            visited[nr][nc] = cnt;
                            q.add(new int[]{nr, nc});
                        }
                    }
                }
            }
        }
        unions[cnt].avg = unions[cnt].sum / unions[cnt].cnt;
    }

    static class Union {
        int cnt, sum, avg;
        public Union(int cnt, int sum, int avg) {
            this.cnt = cnt;
            this.sum = sum;
            this.avg = avg;
        }
    }
}
