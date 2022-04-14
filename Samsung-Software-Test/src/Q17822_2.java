import java.io.*;
import java.util.*;

public class Q17822_2 {

    static int N, M, T, answer = 0;
    static int[][] board = new int[50][50];
    static int[] dir = {1, -1};
    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static Queue<int[]> q = new LinkedList<>();

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

            rotate(x, d, k);

            int[][] check = new int[N][M];
            int flag = 0, cnt = 0, sum = 0;

            for (int j = 0; j < N; j++) {
                for (int m = 0; m < M; m++) {
                    if (board[j][m] != 0 && check[j][m] == 0) {
                        int[] res = bfs(j, m, check);
                        if (res[0] > 1) {
                            flag = 1;
                        }
                        cnt += res[0];
                        sum += res[1];
                    }
                }
            }

            // 지워진 같은 수가 없는 경우
            if (flag == 0) {
                double avg = sum / (double)cnt;
                for (int j = 0; j < N; j++) {
                    for (int m = 0; m < M; m++) {
                        if (board[j][m] == 0) {
                            continue;
                        }
                        if (board[j][m] > avg) {
                            board[j][m]--;
                        } else if (board[j][m] < avg) {
                            board[j][m]++;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                answer += board[i][j];
            }
        }

        System.out.print(answer);
    }

    static int[] bfs(int r, int c, int[][] check) {

        int cnt = 0, sum = 0;
        int tmp = board[r][c];

        check[r][c] = 1;
        q.add(new int[]{r, c});

        while (!q.isEmpty()) {
            int[] p = q.poll();
            cnt++;
            sum += board[p[0]][p[1]];
            board[p[0]][p[1]] = 0;

            for (int i = 0; i < 4; i++) {
                int nr = p[0] + delta[i][0];
                int nc = (p[1] + delta[i][1]) % M;
                if (nr < 0 || nr >= N) {
                    continue;
                }
                if (nc < 0) {
                    nc += M;
                }
                if (check[nr][nc] == 0 && board[nr][nc] == tmp) {
                    check[nr][nc] = 1;
                    q.add(new int[]{nr, nc});
                }
            }
        }

        if (cnt == 1) {
            board[r][c] = tmp;
        }

        return new int[]{cnt, sum};
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
