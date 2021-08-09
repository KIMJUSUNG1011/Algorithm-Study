import java.io.*;
import java.util.*;

public class Q17822 {
    static int N, M, T;
    static int[][] board = new int[50][50];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            for (int j = x; j <= N; j += x) {
                rotate(j - 1, d, k);
            }
            remove();
        }
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] != -1) {
                    answer += board[i][j];
                }
            }
        }
        System.out.print(answer);
    }

    static void remove() {
        int[][] tmp_board = new int[N][M];
        boolean flag = true;
        int sum = 0, cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tmp_board[i][j] != -1) {
                    tmp_board[i][j] = board[i][j];
                }
                if (board[i][j] != -1) {
                    sum += board[i][j];
                    cnt++;
                    int tmp = board[i][j];
                    int[][] delta = {{i, (j + 1) % M}, {i, (j + M - 1) % M}, {i - 1, j}, {i + 1, j}};
                    for (int[] p : delta) {
                        if (p[0] >= 0 && p[1] >= 0 && p[0] < N && p[1] < M) {
                            if (tmp == board[p[0]][p[1]]) {
                                tmp_board[p[0]][p[1]] = -1;
                                tmp_board[i][j] = -1;
                                flag = false;
                            }
                        }
                    }
                }
            }
        }

        if (flag) {
            Double avg = (double)sum / cnt;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (board[i][j] != -1) {
                        board[i][j] += avg.compareTo((double)board[i][j]);
                    }
                }
            }
        } else {
            board = tmp_board;
        }
    }

    static void rotate(int num, int d, int k) {
        int[] tmp = new int[M];
        d = d == 0 ? k : M - k;
        for (int i = 0; i < M; i++) {
            tmp[(i + d) % M] = board[num][i];
        }
        for (int i = 0; i < M; i++) {
            board[num][i] = tmp[i];
        }
    }
}
