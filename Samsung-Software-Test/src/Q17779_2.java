import java.io.*;
import java.util.*;

public class Q17779_2 {

    static int N, answer = Integer.MAX_VALUE;
    static int[][] map = new int[20][20];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                go(i, j);
            }
        }

        System.out.print(answer);
    }

    static void go(int r, int c) {

        for (int i = 1; i <= c; i++) {
            for (int j = 1; j < N - c; j++) {
                if (r + i + j < N) {
                    move(r, c, i, j);
                }
            }
        }
    }

    static void move(int r, int c, int d1, int d2) {

        int[][] check = new int[N][N];
        int[] cnt = new int[5];

        int nr = r, c1 = c, c2 = c;

        // 5번 선거구
        for (int i = 0; i <= d1 + d2; i++) {

            for (int j = c1; j <= c2; j++) {
                check[nr][j] = 5;
                cnt[4] += map[nr][j];
            }

            if (i >= 0 && i < d1) {
                c1--;
            } else {
                c1++;
            }

            if (i >= 0 && i < d2) {
                c2++;
            } else {
                c2--;
            }

            nr++;
        }

        // 1 번 선거구
        for (int i = 0; i < r + d1; i++) {
            for (int j = 0; j <= c; j++) {
                if (check[i][j] == 5) {
                    break;
                }
                cnt[0] += map[i][j];
            }
        }

        // 2 번 선거구
        for (int i = 0; i <= r + d2; i++) {
            for (int j = N - 1; j > c; j--) {
                if (check[i][j] == 5) {
                    break;
                }
                cnt[1] += map[i][j];
            }
        }

        // 3 번 선거구
        for (int i = r + d1; i < N; i++) {
            for (int j = 0; j < c - d1 + d2; j++) {
                if (check[i][j] == 5) {
                    break;
                }
                cnt[2] += map[i][j];
            }
        }

        // 4 번 선거구
        for (int i = r + d2 + 1; i < N; i++) {
            for (int j = N - 1; j >= c - d1 + d2; j--) {
                if (check[i][j] == 5) {
                    break;
                }
                cnt[3] += map[i][j];
            }
        }

        Arrays.sort(cnt);
        answer = Math.min(answer, cnt[4] - cnt[0]);
    }
}
