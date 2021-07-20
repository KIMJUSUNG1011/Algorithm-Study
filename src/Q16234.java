import java.io.*;
import java.util.*;

public class Q16234 {
    static int n, L, R;
    static int[][] map = new int[51][51];
    static int[][] union = new int[51][51];
    static int[] avg = new int[2501];
    static int[][] delta = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 2000; i++) {
            avg = new int[n * n + 1];
            union = new int[n][n];
            int union_num = 1;

            // 한 번의 인구이동
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (union[j][k] == 0) {
                        bfs(j, k, union_num++);
                    }
                }
            }

            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (union[j][k] != 0) {
                        map[j][k] = avg[union[j][k]];
                    }
                }
            }

            // 연합의 수 == 나라의 수 이므로 더 이상 연합할 수 없음
            if (union_num == n * n + 1) {
                System.out.print(i);
                break;
            }
        }
    }

    static void bfs(int r, int c, int union_num) {
        int sum = 0, cnt = 0;
        Queue<int[]> q = new LinkedList<>();
        union[r][c] = union_num;
        q.add(new int[]{r, c});
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int x = p[0], y = p[1];
            sum += map[x][y];
            cnt++;
            for (int i = 0; i < 4; i++) {
                int nx = x + delta[i][0];
                int ny = y + delta[i][1];
                if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                    continue;
                }
                int diff = Math.abs(map[x][y] - map[nx][ny]);
                if (diff >= L && diff <= R) {
                    if (union[nx][ny] == 0) {
                        union[nx][ny] = union_num;
                        q.add(new int[]{nx, ny});
                    }
                }
            }
        }

        avg[union_num] = sum / cnt;
    }
}
