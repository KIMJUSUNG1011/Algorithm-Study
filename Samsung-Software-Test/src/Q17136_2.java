import java.io.*;
import java.util.*;

public class Q17136_2 {

    static int[][] map = new int[10][10];
    static int[][] maxPaper = new int[10][10];
    static int[] paper = {0, 5, 5, 5, 5, 5};
    static int nOne = 0, answer = 25;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    nOne++;
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 1; k <= 5; k++) {
                    if (check(i, j, k) == -1) {
                        break;
                    }
                    maxPaper[i][j] = k;
                }
            }
        }

        dfs(0, 0, 0);

        if (answer == 25) {
            System.out.print(-1);
        } else {
            System.out.print(answer);
        }
    }

    static void dfs(int index, int sum, int cnt) {

        while (index < 100 && map[index / 10][index % 10] != 1) {
            index++;
        }

        if (cnt >= answer) {
            return;
        }

        int flag = 0;
        int r = index / 10;
        int c = index % 10;

        if (index == 100) {
            if (sum == nOne) {
                answer = Math.min(answer, cnt);
            }
            return;
        }

        for (int i = maxPaper[r][c]; i >= 1; i--) {
            if (flag == 0 && check(r, c, i) == -1) {
                continue;
            } else {
                flag = 1;
            }
            if (paper[i] > 0) {
                paper[i]--;
                go(r, c, i, 0);
                dfs(index + 1, sum + i * i, cnt + 1);
                go(r, c, i, 1);
                paper[i]++;
            }
        }
    }

    static int check(int r, int c, int len) {
        for (int i = r; i < r + len; i++) {
            for (int j = c; j < c + len; j++) {
                if (i < 0 || j < 0 || i >= 10 || j >= 10) {
                    return -1;
                }
                if (map[i][j] == 0 || map[i][j] == 2) {
                    return -1;
                }
            }
        }
        return 0;
    }

    static void go(int r, int c, int len, int type) {
        for (int i = r; i < r + len; i++) {
            for (int j = c; j < c + len; j++) {
                if (type == 0) {
                    map[i][j] = 2;
                } else {
                    map[i][j] = 1;
                }
            }
        }
    }
}
