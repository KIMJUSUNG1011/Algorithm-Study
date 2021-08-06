import java.io.*;
import java.util.*;

public class Q17837 {
    static int N, K;
    static int[][] map = new int[13][13];
    static Board[][] boards = new Board[13][13];
    static int[][] delta = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                boards[i][j] = new Board();
            }
        }

        Horse[] horses = new Horse[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            horses[i] = new Horse(i, r, c, dir - 1);
            boards[r][c].stack.push(horses[i]);
        }

        int cnt = 0;
        while (cnt++ <= 1000) {
            //  1번부터 K번 말까지 이동
            for (Horse horse : horses) {
                int num = horse.num, r = horse.r, c = horse.c, dir = horse.dir;
                int nr = r + delta[dir][0], nc = c + delta[dir][1];

                // 벽 또는 파란 칸으로 이동한 경우
                if ((nr <= 0 || nc <= 0 || nr > N || nc > N) || map[nr][nc] == 2) {
                    dir = dir <= 1 ? 1 - dir : 5 - dir;
                    horse.dir = dir;
                    nr = r + delta[dir][0];
                    nc = c + delta[dir][1];
                }

                if (move(num, r, c, nr, nc)) {
                    System.out.print(cnt);
                    return;
                }
            }
        }

        System.out.print(-1);
    }

    static boolean move(int num, int r, int c, int nr, int nc) {
        if ((nr <= 0 || nc <= 0 || nr > N || nc > N) || map[nr][nc] == 2) {
            return false;
        }

        Deque<Horse> tmp = new LinkedList<>();
        while (true) {
            Horse h = boards[r][c].stack.pop();
            h.r = nr;
            h.c = nc;
            tmp.add(h);
            if (h.num == num) {
                break;
            }
        }

        while (!tmp.isEmpty()) {
            boards[nr][nc].stack.push(map[nr][nc] == 0 ? tmp.pollLast() : tmp.poll());
        }

        return boards[nr][nc].stack.size() >= 4;
    }

    static class Board {
        // 말을 쌓아둘 스택
        Stack<Horse> stack;
        public Board() {
            stack = new Stack<>();
        }
    }

    static class Horse {
        int num, r, c, dir;
        public Horse(int num, int r, int c, int dir) {
            this.num = num;
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }
}
