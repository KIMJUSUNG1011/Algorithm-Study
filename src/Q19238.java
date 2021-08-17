import java.io.*;
import java.util.*;

public class Q19238 {
    static int N, M, F;
    static int[][] board = new int[20][20];
    static Pair[][] dst = new Pair[20][20];
    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static Pair taxi;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine(), " ");
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        taxi = new Pair(r - 1, c - 1);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int sr = Integer.parseInt(st.nextToken());
            int sc = Integer.parseInt(st.nextToken());
            board[sr - 1][sc - 1] = 2;

            int dr = Integer.parseInt(st.nextToken());
            int dc = Integer.parseInt(st.nextToken());
            dst[sr - 1][sc - 1] = new Pair(dr - 1, dc - 1);
        }

        int cnt = M;
        while (cnt > 0) {
            if (!findPassenger()) { break;}
            if (!findDst()) { break; }
            cnt--;
        }

        if (cnt == 0) {
            System.out.print(F);
        } else {
            System.out.print(-1);
        }
    }

    static boolean findPassenger() {
        int standard = -1;
        List<Pair> candidates = new ArrayList<>();
        Queue<Pair> q = new LinkedList<>();
        int[][] visited = new int[N][N];
        q.add(new Pair(taxi.r, taxi.c));
        visited[taxi.r][taxi.c] = 1;

        while (!q.isEmpty()) {
            Pair p = q.poll();
            int r = p.r;
            int c = p.c;
            if (board[r][c] == 2) {
                if (standard == -1) {
                    standard = visited[r][c];
                }
                if (visited[r][c] == standard) {
                    candidates.add(new Pair(r, c));
                }
            }
            for (int i = 0; i < 4; i++) {
                int nr = r + delta[i][0];
                int nc = c + delta[i][1];
                if ((nr < 0 || nc < 0 || nr >= N || nc >= N) || board[nr][nc] == 1) {
                    continue;
                }
                if (visited[nr][nc] == 0) {
                    q.add(new Pair(nr, nc));
                    visited[nr][nc] = visited[r][c] + 1;
                }
            }
        }

        // 벽에 막혀 손님에게 가지 못한 경우
        if (standard == -1) {
            return false;
        }

        F -= standard - 1;
        if (F < 0) {
            return false;
        } else {
            Collections.sort(candidates);
            Pair select = candidates.get(0);
            taxi.r = select.r;
            taxi.c = select.c;
            return true;
        }
    }

    static boolean findDst() {
        Pair d = dst[taxi.r][taxi.c];
        board[taxi.r][taxi.c] = 0;
        Queue<Pair> q = new LinkedList<>();
        int[][] visited = new int[N][N];
        q.add(new Pair(taxi.r, taxi.c));
        visited[taxi.r][taxi.c] = 1;
        boolean isDst = false;

        while (!q.isEmpty()) {
            Pair p = q.poll();
            int r = p.r;
            int c = p.c;

            // 목적지 도착
            if (r == d.r && c == d.c) {
                isDst = true;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + delta[i][0];
                int nc = c + delta[i][1];
                if ((nr < 0 || nc < 0 || nr >= N || nc >= N) || board[nr][nc] == 1) {
                    continue;
                }
                if (visited[nr][nc] == 0) {
                    q.add(new Pair(nr, nc));
                    visited[nr][nc] = visited[r][c] + 1;
                }
            }
        }

        // 벽에 막혀 목적지에 도달하지 못한 경우
        if (!isDst) {
            return false;
        }

        F -= visited[d.r][d.c] - 1;
        if (F < 0) {
            return false;
        } else {
            F += (visited[d.r][d.c] - 1) * 2;
            taxi.r = d.r;
            taxi.c = d.c;
            return true;
        }
    }

    static class Pair implements Comparable<Pair> {
        int r, c;
        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
        @Override
        public int compareTo(Pair p) {
            if (this.r != p.r) {
                return this.r - p.r;
            } else {
                return this.c - p.c;
            }
        }
    }
}
