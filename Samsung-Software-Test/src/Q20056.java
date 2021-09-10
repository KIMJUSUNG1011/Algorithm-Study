import java.io.*;
import java.util.*;

public class Q20056 {
    static int N, M, K;
    static Queue<FireBall> q = new LinkedList<>();
    static FireBall[][] board = new FireBall[50][50];
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
            q.add(new FireBall(r - 1, c - 1, m, s, d));
        }

        while (K-- > 0) {
            move();
            divide();
        }

        int answer = 0;
        while (!q.isEmpty()) {
            FireBall fb = q.poll();
            answer += fb.m;
        }
        System.out.print(answer);
    }

    static void move() {
        while (!q.isEmpty()) {
            FireBall fb = q.poll();
            int r = fb.r;
            int c = fb.c;

            // (r, c) 에서 d 방향으로 distance 만큼 이동
            int distance = fb.s % N;
            while (distance-- > 0) {
                int nr = r + delta[fb.d][0];
                int nc = c + delta[fb.d][1];

                if (nr == N) {
                    nr = 0;
                }
                if (nc == N) {
                    nc = 0;
                }
                if (nr == -1) {
                    nr = N - 1;
                }
                if (nc == -1) {
                    nc = N - 1;
                }

                r = nr;
                c = nc;
            }

            if (board[r][c] == null) {
                fb.r = r;
                fb.c = c;
                board[r][c] = fb;
            } else {
                board[r][c].m += fb.m;
                board[r][c].s += fb.s;

                // 파이어볼들의 방향이 짝수, 홀수가 섞여있는 경우
                if (board[r][c].d % 2 != fb.d % 2) {
                    board[r][c].d = -1;
                }
            }
            board[r][c].cnt++;
        }
    }

    static void divide() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == null) {
                    continue;
                }
                FireBall fb = board[i][j];
                board[i][j] = null;

                if (fb.cnt > 1) {
                    // 질량이 0 인 파이어볼은 소멸됨
                    if (fb.m / 5 == 0) {
                        continue;
                    }
                    for (int k = 0; k < 4; k++) {
                        q.add(new FireBall(i, j, fb.m / 5, fb.s / fb.cnt, k * 2 + (fb.d == -1 ? 1 : 0)));
                    }
                } else {
                    // (i, j) 에 파이어볼이 하나만 있는 경우
                    q.add(new FireBall(i, j, fb.m, fb.s, fb.d));
                }
            }
        }
    }

    static class FireBall {
        int r, c, m, s, d, cnt;
        public FireBall(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
            this.cnt = 0;
        }
    }
}
