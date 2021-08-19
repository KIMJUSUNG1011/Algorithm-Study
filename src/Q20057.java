import java.io.*;
import java.util.*;

public class Q20057 {
    static int N;
    static int[][] map = new int[500][500];
    static boolean[][] visited = new boolean[500][500];
    static int[][] delta = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static int alpha = 0;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int dir = 0;
        int r = N / 2, c = N / 2;
        while (r != 0 || c != 0) {
            visited[r][c] = true;
            int tmpDir = (dir + 1) % 4;
            int tmpR = r + delta[tmpDir][0];
            int tmpC = c + delta[tmpDir][1];
            if (!visited[tmpR][tmpC]) {
                dir = tmpDir;
            }

            // 토네이도 시전
            tornado(r, c, dir);

            printMap();

            r += delta[dir][0];
            c += delta[dir][1];
        }

        System.out.print(answer);
    }

    static void tornado(int r, int c, int d) {
        int nr = r + delta[d][0];
        int nc = c + delta[d][1];
        int y = map[nr][nc];
        alpha = y;

        int[] p = {r, c};
        p = move(p[0], p[1], (d + 3) % 4,0.01, y);
        p = move(p[0], p[1], d, 0.07, y);
        move(p[0], p[1], (d + 3) % 4, 0.02, y);
        move(p[0], p[1], d, 0.1, y);

        p[0] = r;
        p[1] = c;
        p = move(p[0], p[1], (d + 1) % 4,0.01, y);
        p = move(p[0], p[1], d, 0.07, y);
        move(p[0], p[1], (d + 1) % 4, 0.02, y);
        move(p[0], p[1], d, 0.1, y);

        nr += delta[d][0];
        nc += delta[d][1];
        move(nr, nc, d, 0.05, y);

        if (nr >= 0 && nc >= 0 && nr < N && nc < N) {
            map[nr][nc] += alpha;
        } else {
            answer += alpha;
        }
    }

    static int[] move(int r, int c, int dir, double rate, int y) {
        int nr = r + delta[dir][0];
        int nc = c + delta[dir][1];
        alpha -= y * rate;

        if (nr >= 0 && nc >= 0 && nr < N && nc < N) {
            map[nc][nc] += y * rate;
        } else {
            answer += y * rate;
        }
        return new int[]{nr, nc};
    }

    static void printMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%3d", map[i][j]);
            } System.out.println();
        } System.out.println();
    }
}
