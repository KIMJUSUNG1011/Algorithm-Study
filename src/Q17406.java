import java.io.*;
import java.util.*;

public class Q17406 {
    static int N, M, K;
    static int[][] rInfo = new int[6][3];
    static boolean[] check = new boolean[6];
    static int[][] delta = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            rInfo[i][0] = Integer.parseInt(st.nextToken()) - 1;
            rInfo[i][1] = Integer.parseInt(st.nextToken()) - 1;
            rInfo[i][2] = Integer.parseInt(st.nextToken());
        }

        go(0, arr);
        System.out.print(min);
    }

    static void go(int index, int[][] arr) {

        if (index == K) {
            min = Math.min(min, getVal(arr));
            return;
        }

        for (int i = 0; i < K; i++) {
            if (!check[i]) {
                int[] info = rInfo[i];
                check[i] = true;
                go(index + 1, rotate(info[0], info[1], info[2], arr));
                check[i] = false;
            }
        }
    }

    static int[][] rotate(int r, int c, int s, int[][] arr) {
        int[][] tmp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tmp[i][j] = arr[i][j];
            }
        }
        int sr = r - s, sc = c - s, len = s * 2;
        for (int i = 0; i < s; i++) {
            int tr = sr, tc = sc, dir = 0;
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < len; k++) {
                    int nr = tr + delta[dir + j][0];
                    int nc = tc + delta[dir + j][1];
                    tmp[nr][nc] = arr[tr][tc];
                    tr = nr;
                    tc = nc;
                }
            }
            len -= 2;
            sr += 1;
            sc += 1;
        }
        return tmp;
    }

    static int getVal(int[][] arr) {
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = 0; j < M; j++) {
                sum += arr[i][j];
            }
            res = Math.min(res, sum);
        }
        return res;
    }
}
