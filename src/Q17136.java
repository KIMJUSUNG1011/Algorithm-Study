import java.io.*;
import java.util.*;

public class Q17136 {
    static int[][] map = new int[10][10];
    static int[] paperCnt = {5, 5, 5, 5, 5};
    static ArrayList<int[]> pairs = new ArrayList<>();
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    pairs.add(new int[]{i, j});
                }
            }
        }

        go(0, new boolean[10][10], 0);

        if (min == Integer.MAX_VALUE) {
            System.out.print(-1);
        } else {
            System.out.print(min);
        }
    }

    static void go(int index, boolean[][] check, int cnt) {

        if (index == pairs.size()) {
            min = Math.min(min, cnt);
            return;
        }

        if (cnt >= min) {
            return;
        }

        int r = pairs.get(index)[0];
        int c = pairs.get(index)[1];

        if (check[r][c]) {
            go(index + 1, check, cnt);
            return;
        }

        for (int i = 0; i < 5; i++) {
            // 남은 색종이 개수 체크
            if (paperCnt[i] == 0) {
                continue;
            }
            // 종이 범위 체크
            if (r + i >= 10 || c + i >= 10) {
                continue;
            }

            boolean[][] tmp = copy(check);
            boolean flag = true;
            for (int j = 0; j < i + 1; j++) {
                for (int k = 0; k < i + 1; k++) {
                    int nr = r + j, nc = c + k;
                    if (map[nr][nc] != 1 || check[nr][nc]) {
                        flag = false;
                        break;
                    }
                    tmp[nr][nc] = true;
                }
            }
            // 색종이가 덮는 부분이 모두 1 인지
            // 다른 색종이에 의해 덮여있는지 체크
            if (!flag) {
                break;
            }

            paperCnt[i]--;
            go(index + 1, tmp, cnt + 1);
            paperCnt[i]++;
        }
    }

    static boolean[][] copy(boolean[][] o) {
        boolean[][] t = new boolean[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                t[i][j] = o[i][j];
            }
        }
        return t;
    }
}

/*
- 색종이를 놓기 전에 체크할 것
1. 색종이의 개수가 남아있는지
2. 색종이가 종이의 범위를 넘어가지 않는지
3. 색종이를 덮는 부분이 모두 1인지
4. 다른 색종이에 의해 덮여져있는지
 */
