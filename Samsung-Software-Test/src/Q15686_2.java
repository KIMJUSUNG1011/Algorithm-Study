import java.io.*;
import java.util.*;

public class Q15686_2 {

    static int N, M, answer = Integer.MAX_VALUE;
    static int[][] map = new int[50][50];
    static List<int[]> chickens = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    chickens.add(new int[]{i, j});
                }
            }
        }

        go(0, 0);

        System.out.print(answer);
    }

    static void go(int index, int cnt) {

        if (cnt == M) {
            int sum = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 1) {
                        int min = Integer.MAX_VALUE;
                        for (int[] p : chickens) {
                            if (map[p[0]][p[1]] == 3) {
                                min = Math.min(min, Math.abs(i - p[0]) + Math.abs(j - p[1]));
                            }
                        }
                        sum += min;
                    }
                }
            }
            answer = Math.min(answer, sum);
            return;
        }

        if (index == chickens.size()) {
            return;
        }

        int r = chickens.get(index)[0];
        int c = chickens.get(index)[1];

        map[r][c] = 3;
        go(index + 1, cnt + 1);
        map[r][c] = 2;
        go(index + 1, cnt);
    }
}
