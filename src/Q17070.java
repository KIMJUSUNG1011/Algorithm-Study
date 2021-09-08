import java.io.*;
import java.util.*;

public class Q17070 {
    static int N;
    static int[][] map = new int[16][16];
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        go(new Pipe(0, 0, 0, 1, 0));
        System.out.print(answer);
    }

    static void go(Pipe pipe) {

        int r1 = pipe.r1, c1 = pipe.c1;
        int r2 = pipe.r2, c2 = pipe.c2;
        int type = pipe.type;

        if (r2 == N - 1 && c2 == N - 1) {
            answer++;
            return;
        }

        // 가로
        if (type == 0) {
           if (check(r2, c2 + 1)) {
               go(new Pipe(r1, c1 + 1, r2, c2 + 1, 0));
               if (check(r2 + 1, c2) && check(r2 + 1, c2 + 1)) {
                   go(new Pipe(r1, c1 + 1, r2 + 1, c2 + 1, 2));
               }
           }
        }

        // 세로
        if (type == 1) {
            if (check(r2 + 1, c2)) {
                go(new Pipe(r1 + 1, c1, r2 + 1, c2, 1));
                if (check(r2, c2 + 1) && check(r2 + 1, c2 + 1)) {
                    go(new Pipe(r1 + 1, c1, r2 + 1, c2 + 1, 2));
                }
            }
        }

        // 대각선
        if (type == 2) {
            if (check(r2, c2 + 1)) {
                go(new Pipe(r1 + 1, c1 + 1, r2, c2 + 1, 0));
            }
            if (check(r2 + 1, c2)) {
                go(new Pipe(r1 + 1, c1 + 1, r2 + 1, c2, 1));
            }
            if (check(r2, c2 + 1) && check(r2 + 1, c2) && check(r2 + 1, c2 + 1)) {
                go(new Pipe(r1 + 1, c1 + 1, r2 + 1, c2 + 1, 2));
            }
        }
    }

    static boolean check(int r, int c) {
        return (r >= 0 && c >= 0 && r < N && c < N) && (map[r][c] == 0);
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
