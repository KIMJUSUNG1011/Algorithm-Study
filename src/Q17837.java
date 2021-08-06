import java.io.*;
import java.util.*;

public class Q17837 {
    static int N, K;
    static int[][] map = new int[13][13];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Horse[] horses = new Horse[K];
        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            horses[i] = new Horse(r, c, dir);
        }
    }

    static class Horse {
        int r, c, dir;
        public Horse(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }
}
