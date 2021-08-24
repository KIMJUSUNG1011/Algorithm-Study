import java.io.*;
import java.util.*;

public class Q21610 {
    static int N, M;
    static int[][] board = new int[50][50];
    static int[][] added = new int[50][50];
    static ArrayList<int[]> clouds = new ArrayList<>();
    static int[][] delta = {
            {0, -1}, {-1, -1}, {-1, 0}, {-1, 1},
            {0, 1}, {1, 1}, {1, 0}, {1, -1}
    };

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

        for (int i = N - 2; i < N; i++) {
            for (int j = 0; j < 2; j++) {
                clouds.add(new int[]{i, j});
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            boolean[][] check = new boolean[N][N];
            for (int j = 0; j < clouds.size(); j++) {
                int r = clouds.get(j)[0];
                int c = clouds.get(j)[1];
                for (int k = 0; k < s; k++) {
                    r += delta[d][0];
                    c += delta[d][1];

                    if (r == -1) {
                        r = N - 1;
                    }
                    if (r == N) {
                        r = 0;
                    }
                    if (c == -1) {
                        c = N - 1;
                    }
                    if (c == N) {
                        c = 0;
                    }
                }
                clouds.set(j, new int[]{r, c});
                board[r][c]++;
            }

            for (int j = 0; j < clouds.size(); j++) {
                int r = clouds.get(j)[0];
                int c = clouds.get(j)[1];

                int cnt = 0;
                for (int k = 1; k < 8; k += 2) {
                    int nr = r + delta[k][0];
                    int nc = c + delta[k][1];
                    if ((nr >= 0 && nc >= 0 && nr < N && nc < N) && board[nr][nc] > 0) {
                        cnt++;
                    }
                }
                check[r][c] = true;
                added[r][c] += cnt;
            }

            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (added[j][k] > 0) {
                        board[j][k] += added[j][k];
                        added[j][k] = 0;
                    }
                }
            }

            clouds.clear();
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (board[j][k] >= 2 && !check[j][k]) {
                        board[j][k] -= 2;
                        clouds.add(new int[]{j, k});
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] > 0) {
                    answer += board[i][j];
                }
            }
        }
        System.out.print(answer);
    }
}