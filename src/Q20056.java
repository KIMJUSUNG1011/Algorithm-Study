import java.io.*;
import java.util.*;

public class Q20056 {
    static int N, M, K;
    static Queue<FireBall> q = new LinkedList<>();
    static Board[][] board = new Board[50][50];
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
            int m = fb.m;
            int s = fb.s;
            int d = fb.d;

            // (r, c) 에서 d 방향으로 distance 만큼 이동
            int distance = s % N;
            while (distance-- > 0) {
                int nr = r + delta[d][0];
                int nc = c + delta[d][1];

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
                board[r][c] = new Board();
            }

            board[r][c].sumOfM += m;
            board[r][c].sumOfS += s;
            board[r][c].cnt++;
            board[r][c].d = d;

            if (d % 2 == 0) {
                board[r][c].isEven = true;
            } else {
                board[r][c].isOdd = true;
            }
        }
    }

    static void divide() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == null) {
                    continue;
                }
                int sumOfM = board[i][j].sumOfM;
                int sumOfS = board[i][j].sumOfS;
                int cnt = board[i][j].cnt;
                int d = board[i][j].d;
                boolean isEven = board[i][j].isEven;
                boolean isOdd = board[i][j].isOdd;
                board[i][j] = null;

                if (cnt > 1) {
                    // 질량이 0 인 파이어볼은 소멸됨
                    if (sumOfM / 5 == 0) {
                        continue;
                    }

                    int type = (isEven && isOdd) ? 1 : 0;
                    for (int k = 0; k < 4; k++) {
                        q.add(new FireBall(i, j, sumOfM / 5, sumOfS / cnt, k * 2 + type));
                    }
                } else {
                    q.add(new FireBall(i, j, sumOfM, sumOfS, d));
                }
            }
        }
    }

    static class FireBall {
        int r, c, m, s, d;
        public FireBall(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    static class Board {
        int sumOfM, sumOfS, cnt, d;
        boolean isEven, isOdd;
        public Board() {
            this.sumOfM = 0;
            this.sumOfS = 0;
            this.cnt = 0;
            this.isEven = false;
            this.isOdd = false;
            this.d = -1;
        }
    }
}
