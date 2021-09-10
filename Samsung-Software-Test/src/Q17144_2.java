import java.io.*;
import java.util.*;

public class Q17144_2 {
    static int R, C, T;
    static int[][] board = new int[50][50];
    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int[][] cleaner = new int[2][2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        int cnt = 0;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == -1) {
                    if (cnt == 0) {
                        cleaner[0][0] = i;
                        cleaner[0][1] = j;
                        cnt++;
                    } else {
                        cleaner[1][0] = i;
                        cleaner[1][1] = j;
                    }
                }
            }
        }

        while (T-- > 0) {
            diffuse();
            rotate(cleaner[0][0] - 1, 0, 0, 1);
            rotate(cleaner[1][0] + 1, 0, 2, 3);
        }

        int answer = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] != -1) {
                    answer += board[i][j];
                }
            }
        }

        System.out.print(answer);
    }

    static void diffuse() {
        int[][] tmp = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] == -1) {
                    tmp[i][j] = -1;
                } else if (board[i][j] != 0) {
                    int cnt = 0;
                    int amount = board[i][j] / 5;
                    for (int k = 0; k < 4; k++) {
                       int nr = i + delta[k][0];
                       int nc = j + delta[k][1];
                       if ((nr >= 0 && nc >= 0 && nr < R && nc < C) && board[nr][nc] != -1) {
                           tmp[nr][nc] += amount;
                           cnt++;
                       }
                    }
                    tmp[i][j] += board[i][j] - amount * cnt;
                }
            }
        }
        board = tmp;
    }

    static void rotate(int r, int c, int d, int rotateD) {
        int cond1 = d == 0 ? 0 : 1;
        int cond2 = d == 0 ? 1 : 0;
        while (r != cleaner[cond1][0] || c != cleaner[cond1][1]) {
            // 수 이동
            int br = r + delta[(d + 2) % 4][0];
            int bc = c + delta[(d + 2) % 4][1];
            if (board[br][bc] != -1) {
                board[br][bc] = board[r][c];
                board[r][c] = 0;
            }

            // 다음 좌표로 이동
            int nr = r + delta[d][0];
            int nc = c + delta[d][1];

            // 방향이 꺾이는 부분
            if (nr < 0 || nr >= R || nc >= C || nr == cleaner[cond2][0]) {
                d = (d + rotateD) % 4;
                r += delta[d][0];
                c += delta[d][1];
            } else {
                r = nr;
                c = nc;
            }
        }
    }
}
