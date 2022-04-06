import java.util.*;
import java.io.*;

public class Q17070_2 {

    static int N;
    static int[][] map = new int[16][16];
    static int[][][][] check = new int[16][16][16][16];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                for (int k = 0; k < N; k++) {
                    for (int m = 0; m < N; m++) {
                        check[i][j][k][m] = -1;
                    }
                }
            }
        }

        System.out.print(go(new Pipe(0, 0, 0, 1, 1)));
    }

    static int go(Pipe p) {

        if (check[p.r1][p.c1][p.r2][p.c2] >= 0) {
            return check[p.r1][p.c1][p.r2][p.c2];
        }

        if (p.r2 == N - 1 && p.c2 == N - 1) {
            return 1;
        }

        int res = 0;

        // 가로
        if (p.type == 1) {
            res += go1(p.r2, p.c2);
        }

        // 세로
        if (p.type == 2) {
            res += go2(p.r2, p.c2);
        }

        // 대각선
        if (p.type == 3) {
            res += go1(p.r2, p.c2);
            res += go2(p.r2, p.c2);
        }

        res += go3(p.r2, p.c2);

        check[p.r1][p.c1][p.r2][p.c2] = res;

        return res;
    }

    // 가로 처리
    static int go1(int r, int c) {
        if (c < N - 1 && map[r][c + 1] == 0) {
            return go(new Pipe(r, c, r, c + 1, 1));
        }
        return 0;
    }

    // 세로 처리
    static int go2(int r, int c) {
        if (r < N - 1 && map[r + 1][c] == 0) {
            return go(new Pipe(r, c, r + 1, c, 2));
        }
        return 0;
    }

    // 대각선 처리
    static int go3(int r, int c) {
        if (r < N - 1 && c < N - 1) {
            if (map[r][c + 1] == 0 && map[r + 1][c] == 0 && map[r + 1][c + 1] == 0) {
                return go(new Pipe(r, c, r + 1, c + 1, 3));
            }
        }
        return 0;
    }

    static class Pipe {

        int r1, c1, r2, c2, type;

        public Pipe(int r1, int c1, int r2, int c2, int type) {
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
            this.type = type;
        }
    }
}
