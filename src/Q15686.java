import java.io.*;
import java.util.*;

public class Q15686 {
    static int N, M;
    static int[][] map = new int[50][50];
    static boolean[][] check = new boolean[50][50];
    static ArrayList<int[]> chickens = new ArrayList<>();
    static ArrayList<int[]> homes = new ArrayList<>();
    static int min = Integer.MAX_VALUE;

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
                if (map[i][j] == 1) {
                    homes.add(new int[]{i, j});
                }
            }
        }

        go(0, 0);
        System.out.print(min);
    }

    static void go(int index, int cnt) {

        if (cnt == M || index == chickens.size()) {
            return;
        }

        int r = chickens.get(index)[0];
        int c = chickens.get(index)[1];

        check[r][c] = true;
        min = Math.min(min, getDistance());
        go(index + 1, cnt + 1);

        check[r][c] = false;
        go(index + 1, cnt);
    }

    static int getDistance() {
        int res = 0;
        for (int[] p1 : homes) {
            int mDist = Integer.MAX_VALUE;
            for (int[] p2 : chickens) {
                if (check[p2[0]][p2[1]]) {
                    int dist = Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
                    mDist = Math.min(mDist, dist);
                }
            }
            res += mDist;
        }
        return res;
    }
}
