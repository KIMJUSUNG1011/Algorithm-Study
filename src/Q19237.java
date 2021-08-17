import java.io.*;
import java.util.*;

public class Q19237 {
    static int N, M, K;
    static Board[][] board = new Board[20][20];
    static Shark[] sharks = new Shark[401];
    static int[][][] priority = new int[401][4][4];
    static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                int x = Integer.parseInt(st.nextToken());
                if (x != 0) {
                    sharks[x] = new Shark(i, j, 0, x);
                }
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= M; i++) {
            sharks[i].dir = Integer.parseInt(st.nextToken()) - 1;
        }
        for (int i = 1; i <= M; i++) {
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int k = 0; k < 4; k++) {
                    priority[i][j][k] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
        }

        int t = 0;
        while (t <= 1000 && isEnd()) {

            // 상어가 자신의 냄새를 남김
            for (int i = 1; i <= M; i++) {
                Shark shark = sharks[i];
                if (shark != null) {
                    board[shark.r][shark.c] = new Board(shark.num, K);
                }
            }

            boolean[][] visited = new boolean[N][N];
            for (int i = 1; i <= M; i++) {
                Shark shark = sharks[i];
                if (shark == null) {
                    continue;
                }
                int r = shark.r;
                int c = shark.c;
                int dir = shark.dir;
                int num = shark.num;

                boolean flag = false;
                int mySmellDir = -1;
                for (int j = 0; j < 4; j++) {
                    int p = priority[num][dir][j];
                    int nr = r + delta[p][0];
                    int nc = c + delta[p][1];
                    if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
                        continue;
                    }
                    if (board[nr][nc] == null) {
                        shark.r = nr;
                        shark.c = nc;
                        shark.dir = p;
                        flag = true;
                        break;
                    } else {
                        // 자신의 냄새가 있는 칸을 찾을 때도 우선순위를 적용시켜야함
                        if (board[nr][nc].num == num && mySmellDir == -1) {
                            mySmellDir = p;
                        }
                    }
                }

                // 인접한 빈칸이 없는 경우 자신의 냄새가 있는 칸으로 이동
                if (!flag) {
                    if (mySmellDir == -1) {
                        System.out.println("num : " + num);
                        printSharks();
                        printBoard();
                    }
                    shark.r = r + delta[mySmellDir][0];
                    shark.c = c + delta[mySmellDir][1];
                    shark.dir = mySmellDir;
                }

                // 코드상에서 번호가 작은 순으로 이동을 진행하므로
                // 이동한 칸에 이미 다른 상어가 있는 경우 스스로를 삭제
                if (visited[shark.r][shark.c]) {
                    sharks[i] = null;
                } else {
                    visited[shark.r][shark.c] = true;
                }
            }
            // 시간이 다 된 냄새를 제거함
            removeSmell();
            t++;
        }

        if (t > 1000) {
            t = -1;
        }
        System.out.print(t);
    }

    static boolean isEnd() {
        for (int i = 2; i <= M; i++) {
            if (sharks[i] != null) {
                return true;
            }
        } return false;
    }

    static void removeSmell() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] != null) {
                    board[i][j].time--;
                    if (board[i][j].time == 0) {
                        board[i][j] = null;
                    }
                }
            }
        }
    }

    static class Board {
        int num, time;
        public Board(int num, int time) {
            this.num = num;
            this.time = time;
        }
        public String toString() {
            return num + "," + time;
        }
    }

    static class Shark {
        int r, c, dir, num;
        public Shark(int r, int c, int dir, int num) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.num = num;
        }
        public String toString() {
            return r + ", " + c + ", " + dir + ", " + num;
        }
    }

    static void printBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            } System.out.println();
        } System.out.println();
    }

    static void printSharks() {
        for (int i = 1; i <= M; i++) {
            System.out.println(sharks[i]);
        } System.out.println();
    }

    static void printPriotiry() {
        for (int i = 1; i <= M; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    System.out.print(priority[i][j][k] + " ");
                } System.out.println();
            } System.out.println();
        }
    }
}
