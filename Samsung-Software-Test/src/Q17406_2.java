import java.io.*;
import java.util.*;

public class Q17406_2 {

    static int N, M, K, answer = 5001;
    static int[][] info = new int[6][3];
    static int[] check = new int[6];
    static int[][] delta = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            info[i][0] = Integer.parseInt(st.nextToken()) - 1;
            info[i][1] = Integer.parseInt(st.nextToken()) - 1;
            info[i][2] = Integer.parseInt(st.nextToken());
        }

        dfs(0, map);

        System.out.print(answer);
    }

    static void dfs(int index, int[][] map) {

        if (index == K) {
            int val = 5001;
            for (int i = 0; i < N; i++) {
                int sum = 0;
                for (int j = 0; j < M; j++) {
                    sum += map[i][j];
                }
                val = Math.min(val, sum);
            }
            answer = Math.min(answer, val);
            return;
        }

        for (int i = 0; i < K; i++) {
            if (check[i] == 0) {
                check[i] = 1;
                int[][] tmpMap = arrayCopy(map);
                rotate(info[i][0], info[i][1], info[i][2], tmpMap);
                dfs(index + 1, tmpMap);
                check[i] = 0;
            }
        }
    }

    static void rotate(int r, int c, int s, int[][] tmpMap) {

        for (int i = s; i > 0; i--) {

            int currR = r - i;
            int currC = c - i;
            int tmp = tmpMap[currR][currC];

            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < i * 2; k++) {
                    tmpMap[currR][currC] = tmpMap[currR + delta[j][0]][currC + delta[j][1]];
                    currR += delta[j][0];
                    currC += delta[j][1];
                }
            }

            tmpMap[currR][currC + 1] = tmp;
        }
    }

    static int[][] arrayCopy(int[][] src) {
        int[][] dst = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dst[i][j] = src[i][j];
            }
        }
        return dst;
    }
}
