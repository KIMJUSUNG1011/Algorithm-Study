import java.io.*;
import java.util.*;

public class Q20056_2 {

    static int N, M, K, answer = 0;
    static Queue<Ball> q = new LinkedList<>();
    static Ball[][] balls = new Ball[50][50];
    static int[][] delta = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            q.add(new Ball(r - 1, c - 1, m, s, d));
        }

        while (K-- > 0) {
            move();
        }

        while (!q.isEmpty()) {
            Ball b = q.poll();
            answer += b.m;
        }

        System.out.print(answer);
    }

    static void move() {

        while (!q.isEmpty()) {

            Ball b = q.poll();

            b.r = (b.r + delta[b.d][0] * b.s) % N;
            b.c = (b.c + delta[b.d][1] * b.s) % N;

            if (b.r < 0) {
                b.r += N;
            }
            if (b.c < 0) {
                b.c += N;
            }

            if (balls[b.r][b.c] == null) {
                balls[b.r][b.c] = new Ball(b.r, b.c, 0, 0, 0);
            }

            balls[b.r][b.c].cnt++;
            balls[b.r][b.c].m += b.m;
            balls[b.r][b.c].s += b.s;
            balls[b.r][b.c].d += b.d;
            balls[b.r][b.c].dirs[b.d % 2] = 1;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (balls[i][j] == null) {
                    continue;
                }

                Ball b = balls[i][j];

                // 초기화
                balls[i][j] = null;

                if (b.cnt == 1) {
                    q.add(new Ball(i, j, b.m, b.s, b.d));
                    continue;
                }

                if (b.m / 5 == 0) {
                    continue;
                }

                int type = b.dirs[0] == 1 && b.dirs[1] == 1 ? 1 : 0;
                for (int k = 0; k < 4; k++) {
                    q.add(new Ball(i, j, b.m / 5, b.s / b.cnt, k * 2 + type));
                }
            }
        }
    }

    static class Ball {
        int r, c, m, s, d, cnt;
        int[] dirs;
        public Ball(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
            this.cnt = 0;
            dirs = new int[2];
        }
    }
}
