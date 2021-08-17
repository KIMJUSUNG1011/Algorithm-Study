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
                    priority[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        int t = 0;
        while (true) {
            t++;
        }
    }

    static class Board {
        int num, time;
        public Board(int num, int time) {
            this.num = num;
            this.time = time;
        }
        public String toString() {
            return num + ", " + time;
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
