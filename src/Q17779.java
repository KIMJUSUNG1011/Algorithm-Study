import java.io.*;
import java.util.*;

public class Q17779 {
    static int N;
    static int[][] map = new int[21][21];
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N - 2; i++) {
            for (int j = 1; j < N - 1; j++) {
                go(i, j, j, N - j - 1);
            }
        }

        System.out.print(answer);
    }

    static void go(int r, int c, int maxD1, int maxD2) {
        for (int i = 1; i <= maxD1; i++) {
            for (int j = 1; j <= maxD2; j++) {
                if (possible(r, c, i, j)) {
                    answer = Math.min(answer, divide(r, c, i, j));
                }
            }
        }
    }

    static int divide(int r, int c, int d1, int d2) {
        int[][] tmp_map = new int[N][N];
        int[] cnt = new int[5];

        // 좌측 위 경계선
        for (int i = r, j = c; i <= r + d1 && j >= c - d1; i++, j--) {
            if (tmp_map[i][j] != 5) {
                tmp_map[i][j] = 5;
                cnt[4] += map[i][j];
            }
        }
        // 좌측 아래 경계선
        for (int i = r, j = c; i <= r + d2 && j <= c + d2; i++, j++) {
            if (tmp_map[i][j] != 5) {
                tmp_map[i][j] = 5;
                cnt[4] += map[i][j];
            }
        }
        // 우측 위 경계선
        for (int i = r + d1, j = c - d1; i <= r + d1 + d2 && j <= c - d1 + d2; i++, j++) {
            if (tmp_map[i][j] != 5) {
                tmp_map[i][j] = 5;
                cnt[4] += map[i][j];
            }
        }
        // 우측 아래 경계선
        for (int i = r + d2, j = c + d2; i <= r + d2 + d1 && j >= c + d2 - d1; i++, j--) {
            if (tmp_map[i][j] != 5) {
                tmp_map[i][j] = 5;
                cnt[4] += map[i][j];
            }
        }

        // 경계선 내부 채우기
        for (int i = r + 1, j = c - 1; i <= r + d1 && j >= c - d1; i++, j--) {
            for (int k = j + 1; tmp_map[i][k] != 5; k++) {
                tmp_map[i][k] = 5;
                cnt[4] += map[i][k];
            }
        }

        // 경계선 내부 채우기
        for (int i = r + d1, j = c - d1; i <= r + d1 + d2 - 1 && j <= c - d1 + d2 - 1; i++, j++) {
            for (int k = j + 1; tmp_map[i][k] != 5; k++) {
                tmp_map[i][k] = 5;
                cnt[4] += map[i][k];
            }
        }

        // 1번 선거구
        for (int i = 0; i < r + d1; i++) {
            for (int j = 0; j <= c; j++) {
                if (tmp_map[i][j] != 5) {
                    cnt[0] += map[i][j];
                }
            }
        }

        // 2번 선거구
        for (int i = 0; i <= r + d2; i++) {
            for (int j = c + 1; j < N; j++) {
                if (tmp_map[i][j] != 5) {
                    cnt[1] += map[i][j];
                }
            }
        }

        // 3번 선거구
        for (int i = r + d1; i < N; i++) {
            for (int j = 0; j < c - d1 + d2; j++) {
                if (tmp_map[i][j] != 5) {
                    cnt[2] += map[i][j];
                }
            }
        }

        // 4번 선거구
        for (int i = r + d2 + 1; i < N; i++) {
            for (int j = c - d1 + d2; j < N; j++) {
                if (tmp_map[i][j] != 5) {
                    cnt[3] += map[i][j];
                }
            }
        }

        Arrays.sort(cnt);
        return cnt[4] - cnt[0];
    }

    static boolean possible(int r, int c, int d1, int d2) {
        if (r + d1 < 0 || c - d1 < 0) {
            return false;
        }
        if (r + d1 + d2 >= N || c - d1 + d2 >= N) {
            return false;
        }
        return (r + d2 < N && c + d2 < N);
    }
}
