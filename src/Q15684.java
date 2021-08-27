import java.io.*;
import java.util.*;

public class Q15684 {
    static int N, M, H;
    static int[][] map = new int[31][11];
    static ArrayList<int[]> pairs = new ArrayList<>();
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = 1;
            map[a][b + 1] = 2;
        }

        for (int i = 1; i <= H; i++) {
            for (int j = 1; j <= N; j++) {
                if (map[i][j] == 0) {
                    // 가로선을 넣을 수 있는 점선의 왼쪽 좌표를 넣음
                    if (j < N && map[i][j + 1] == 0) {
                        pairs.add(new int[]{i, j});
                    }
                }
            }
        }

        go(0, 0);
        if (min == Integer.MAX_VALUE) {
            System.out.print(-1);
        } else {
            System.out.print(min);
        }
    }

    static void go(int index, int cnt) {

        if (play()) {
            min = Math.min(min, cnt);
            return;
        }

        if (cnt == 3 || index == pairs.size()) {
            return;
        }

        int r = pairs.get(index)[0];
        int c = pairs.get(index)[1];
        if (map[r][c] == 0) {
            map[r][c] = 1;
            map[r][c + 1] = 2;
            go(index + 1, cnt + 1);
            map[r][c] = 0;
            map[r][c + 1] = 0;
        }

        go(index + 1, cnt);
    }

    static boolean play() {
        for (int i = 1; i <= N; i++) {
            int r = 1, c = i;
            while (true) {
                int n = map[r][c];
                if (n == 1) {
                    c++;
                }
                if (n == 2) {
                    c--;
                }
                r++;
                if (r > H) {
                    if (i != c) {
                        return false;
                    }
                    break;
                }
            }
        }
        return true;
    }
}
