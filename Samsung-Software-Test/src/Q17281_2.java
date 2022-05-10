import java.io.*;
import java.util.*;

public class Q17281_2 {

    static int N, answer = -1;
    static int[][] record = new int[51][10];
    static int[] order = new int[9];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= 9; j++) {
                record[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        order[3] = 1;
        dfs(0, 1 << 1);
        System.out.print(answer);
    }

    static void dfs(int index, int flag) {

        if (index == 9) {
            play();
            return;
        }

        if (index == 3) {
            index++;
        }

        for (int i = 1; i <= 9; i++) {
            if ((flag & (1 << i)) != 0) {
                continue;
            }
            order[index] = i;
            dfs(index + 1, flag | (1 << i));
        }
    }

    static void play() {

        int inning = 0, pos = 0, score = 0;

        while (inning++ < N) {

            int out = 0;
            int[] base = new int[4];

            while (out < 3) {

                int hit = record[inning][order[pos]];
                pos = (pos + 1) % 9;
                base[0] = 1;

                if (hit == 0) {
                    out++;
                    continue;
                }

                for (int i = 3; i >= 0; i--) {
                    if (base[i] == 0) {
                        continue;
                    }
                    int res = i + hit;
                    if (res <= 3) {
                        base[res] = 1;
                    } else {
                        score++;
                    }
                    base[i] = 0;
                }
            }
        }

        answer = Math.max(answer, score);
    }
}
