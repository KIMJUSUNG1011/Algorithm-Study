import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q14503 {
    static int n, m, dir;
    static int[][] map = new int[51][51];
    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] robot = new int[2];
        robot[0] = Integer.parseInt(st.nextToken());
        robot[1] = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(robot[0], robot[1], dir);
        System.out.print(answer);
    }

    static void solve(int x, int y, int dir) {
        // 청소한 영역은 2 로 표시
        if (map[x][y] == 0) {
            map[x][y] = 2;
            answer++;
        }

        for (int i = 0; i < 4; i++) {
            // 왼쪽 방향으로 이동
            dir = (dir + 3) % 4;
            int nx = x + delta[dir][0];
            int ny = y + delta[dir][1];

            if (map[nx][ny] == 0) {
                solve(nx, ny, dir);
                return;
            }
        }

        // for 문이 정상적으로 끝났다면 네 방향 모두 청소가 되어있거나 벽인 경우

        int bx = x + delta[(dir + 2) % 4][0];
        int by = y + delta[(dir + 2) % 4][1];
        if (map[bx][by] == 2) {
            solve(bx, by, dir);
        }
    }
}
