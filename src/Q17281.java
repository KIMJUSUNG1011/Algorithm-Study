import java.io.*;
import java.util.*;

public class Q17281 {
    static int N;
    static int[][] result = new int[50][9];
    static int[] player = {1, 2, 3, 4, 5, 6, 7, 8};
    static int[] order = new int[9];
    static boolean[] check = new boolean[8];
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 9; j++) {
                result[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        go(0);
        System.out.print(max);
    }

    static void go(int index) {

        if (index == 9) {
            play();
            return;
        }

        // 1번 선수를 4번 타자에 고정
        if (index == 3) {
            go(index + 1);
            return;
        }

        for (int i = 0; i < 8; i++) {
            if (!check[i]) {
                check[i] = true;
                order[index] = player[i];
                go(index + 1);
                order[index] = 0;
                check[i] = false;
            }
        }
    }

    static void play() {
        int score = 0, inning = 0, idx = 0;
        while (inning < N) {
            int out = 0;
            int[] base = new int[4];
            while (out != 3) {
                int hit = result[inning][order[idx]];
                if (hit == 0) {
                    out++;
                    idx = (idx + 1) % 9;
                    continue;
                }
                base[0] = 1;
                for (int i = 3; i >= 0; i--) {
                    if (base[i] == 0) {
                        continue;
                    }
                    int ni = i + hit;
                    if (ni > 3) {
                        score++;
                    } else {
                        base[ni] = 1;
                    }
                    base[i] = 0;
                }
                idx = (idx + 1) % 9;
            }
            inning++;
        }
        max = Math.max(max, score);
    }
}

/*
1. 모든 경우의 타순 정하기
2. 게임 플레이
 */
