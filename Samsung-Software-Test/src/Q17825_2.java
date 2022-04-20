import java.io.*;
import java.util.*;

public class Q17825_2 {

    static int answer = 0;
    static int[] dice = new int[10];
    static int[][] map = new int[5][43];
    static int[][] check = new int[5][43];
    static int[][] horse = new int[4][2];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 10; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }

        init();

        dfs(0, 0);

        System.out.print(answer);
    }

    static void dfs(int index, int sum) {

        if (index == 10) {
            answer = Math.max(answer, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {

            int[] h = horse[i];

            if (h[1] > 40) {
                continue;
            }

            int val = 0;
            int[] prev = {h[0], h[1]};
            int[] next = roll(h, dice[index]);

            if (next == null) {
                continue;
            }

            if (next[1] <= 40) {
                val = next[1];
            }

            horse[i][0] = next[0];
            horse[i][1] = next[1];
            check[prev[0]][prev[1]] = 0;
            check[next[0]][next[1]] = 1;

            dfs(index + 1, sum + val);

            check[next[0]][next[1]] = 0;
            check[prev[0]][prev[1]] = 1;
            horse[i][1] = prev[1];
            horse[i][0] = prev[0];
        }
    }

    static int[] roll(int[] k, int val) {

        int route = k[0], pos = k[1];

        if (route == 0 && pos % 10 == 0) {
            route = pos / 10;
        }

        for (int i = 0; i < val; i++) {

            pos = map[route][pos];

            // 도착칸 도달
            if (pos > 40) {
                break;
            }

            if (pos == 25 || pos == 40) {
                route = 4;
            }
        }

        // 이동을 마치는 칸에 다른 말이 있는 경우
        if (pos <= 40 && check[route][pos] == 1) {
            return null;
        }

        return new int[]{route, pos};
    }

    static void init() {

        // 외곽
        for (int i = 0; i <= 40; i+=2) {
            map[0][i] = i + 2;
        }

        // 좌측
        map[1][10] = 13;
        map[1][13] = 16;
        map[1][16] = 19;
        map[1][19] = 25;

        // 하단
        map[2][20] = 22;
        map[2][22] = 24;
        map[2][24] = 25;

        // 우측
        map[3][30] = 28;
        map[3][28] = 27;
        map[3][27] = 26;
        map[3][26] = 25;

        // 상단
        map[4][25] = 30;
        map[4][30] = 35;
        map[4][35] = 40;
        map[4][40] = 42;
    }
}
