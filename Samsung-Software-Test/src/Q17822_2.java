import java.io.*;
import java.util.*;

public class Q17822_2 {

    static int N, M, T, CNT, answer = 0;
    static int[][] board = new int[50][50];
    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int[] dir = {1, -1};

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

        CNT = N * M;
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            rotate(x, d, k);
            process();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                answer += board[i][j];
            }
        }

        System.out.print(answer);
    }

    static void process() {

        int srcCNT = CNT;
        int sum = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] != 0) {
                    sum += board[i][j];
                    dfs(i, j, board[i][j]);
                }
            }
        }

        // 지워진 경우
        if (srcCNT > CNT) {
            return;
        }

        // 지워지지 않은 경우
        double avg = sum / (double)CNT;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 0 || board[i][j] == avg) {
                    continue;
                }
                if (board[i][j] > avg) {
                    board[i][j]--;
                } else {
                    board[i][j]++;
                }
            }
        }
    }

    static void dfs(int r, int c, int src) {

        for (int i = 0; i < 4; i++) {
            int nr = r + delta[i][0];
            int nc = c + delta[i][1];
            if (nr < 0 || nr >= N) {
                continue;
            }
            if (nc < 0) {
                nc = M - 1;
            }
            if (nc >= M) {
                nc = 0;
            }
            if (board[nr][nc] == src) {
                CNT--;
                board[nr][nc] = 0;
                dfs(nr, nc, src);
            }
        }
    }

    static void rotate(int x, int d, int k) {

        for (int i = x - 1; i < N; i+=x) {
            int[] tmp = new int[M];
            for (int j = 0; j < M; j++) {
                int idx = (j + k * dir[d]) % M;
                if (idx < 0) {
                    idx += M;
                }
                tmp[idx] = board[i][j];
            }
            for (int j = 0; j < M; j++) {
                board[i][j] = tmp[j];
            }
        }
    }
}
