import java.io.*;
import java.util.*;

public class Q21611 {
    static int N, M;
    static int[][] board = new int[50][50];
    static int[][] front = new int[50][50];
    static int[][] back = new int[50][50];
    static int[][] delta1 = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[][] delta2 = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static int[] score = new int[3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        init();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            delete(d, s);
            while (true) {
                if (explosion()) { break; }
            }
            group();
        }
        System.out.print(score[0] + score[1] * 2 + score[2] * 3);
    }

    static void init() {
        boolean[][] visited = new boolean[N][N];
        visited[N / 2][N / 2] = true;
        int r = N / 2, c = (N / 2) - 1, d = 1;
        while (c != -1) {
            back[r][c] = (d + 2) % 4;
            visited[r][c] = true;
            int nr = r + delta2[(d + 1) % 4][0];
            int nc = c + delta2[(d + 1) % 4][1];
            if (!visited[nr][nc]) {
                d = (d + 1) % 4;
            } else {
                nr = r + delta2[d][0];
                nc = c + delta2[d][1];
            }
            front[r][c] = d;
            r = nr;
            c = nc;
        }
    }

    static void delete(int d, int s) {
        int r = N / 2, c = N / 2;
        for (int i = 0; i < s; i++) {
            r += delta1[d][0];
            c += delta1[d][1];
            board[r][c] = 0;
        }
        move();
    }

    static boolean explosion() {
        boolean endFlag = true;
        int r = N / 2, c = (N / 2) - 1;
        int cnt = 1;
        while (c != -1) {
            int f = front[r][c];
            int nr = r + delta2[f][0];
            int nc = c + delta2[f][1];

            if (board[r][c] == 0) {
                r = nr;
                c = nc;
                continue;
            }

            if (nc != -1 && (board[r][c] == board[nr][nc])) {
                cnt++;
            } else {
                if (cnt >= 4 && board[r][c] != 0) {
                    endFlag = false;
                    int tmpR = r, tmpC = c;
                    while (cnt-- > 0) {
                        int num = board[tmpR][tmpC];
                        score[num - 1]++;
                        board[tmpR][tmpC] = 0;
                        int b = back[tmpR][tmpC];
                        tmpR += delta2[b][0];
                        tmpC += delta2[b][1];
                    }
                }
                cnt = 1;
            }

            r = nr;
            c = nc;
        }
        move();
        return endFlag;
    }

    static void move() {
        int r = (N / 2) + 1, c = (N / 2) - 1;
        while (c != -1) {

            int f = front[r][c];
            if (board[r][c] == 0) {
                r += delta2[f][0];
                c += delta2[f][1];
                continue;
            }

            // 구슬을 빈칸으로 이동
            int tmpR = r, tmpC = c;
            while (true) {
                int b = back[tmpR][tmpC];
                int br = tmpR + delta2[b][0];
                int bc = tmpC + delta2[b][1];
                if (board[br][bc] == 0 && (br != N / 2 || bc != N / 2)) {
                    board[br][bc] = board[tmpR][tmpC];
                    board[tmpR][tmpC] = 0;
                    tmpR = br;
                    tmpC = bc;
                } else {
                    break;
                }
            }

            r += delta2[f][0];
            c += delta2[f][1];
        }
    }

    static void group() {
        int[][] tmp = new int[N][N];
        int r = N / 2, c = (N / 2) - 1;
        int tr = N / 2, tc = (N / 2) - 1;
        int cnt = 1;
        while (c != -1) {
            int f = front[r][c];
            int nr = r + delta2[f][0];
            int nc = c + delta2[f][1];

            if (nc != -1 && (board[r][c] == board[nr][nc])) {
                cnt++;
            } else {
                if (board[r][c] != 0 && tc != -1) {
                    tmp[tr][tc] = cnt;
                    f = front[tr][tc];
                    tr += delta2[f][0];
                    tc += delta2[f][1];

                    tmp[tr][tc] = board[r][c];
                    f = front[tr][tc];
                    tr += delta2[f][0];
                    tc += delta2[f][1];
                }
                cnt = 1;
            }
            r = nr;
            c = nc;
        }
        board = tmp;
    }
}

