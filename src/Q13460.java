import java.io.*;
import java.util.*;

public class Q13460 {
    static int N, M;
    static char[][] board = new char[10][10];
    static int[][] delta = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] r = new int[2];
        int[] b = new int[2];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = line.charAt(j);
                if (c == 'R') {
                    r[0] = i;
                    r[1] = j;
                    board[i][j] = '.';
                } else if (c == 'B') {
                    b[0] = i;
                    b[1] = j;
                    board[i][j] = '.';
                } else {
                    board[i][j] = c;
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            int[] tmpR = {r[0], r[1]};
            int[] tmpB = {b[0], b[1]};
            go(tmpR, tmpB, i, 0);
        }

        if (min == Integer.MAX_VALUE) {
            System.out.print(-1);
        } else {
            System.out.print(min);
        }
    }

    static void go(int[] r, int[] b, int d, int index) {

        // 종료 조건
        if (index == 10) {
            return;
        }

        // 어떤 구슬을 먼저 굴릴지 우선순위를 결정
        if (prior(r, b, d)) {
            move(r, b, d);
            move(b, r, d);
        } else {
            move(b, r, d);
            move(r, b, d);
        }

        // 파란 구슬이 구멍에 빠지면 게임 종료
        if (b[0] == -1 && b[1] == -1) {
            return;
        }

        // 게임 성공
        if (r[0] == -1 && r[1] == -1) {
            min = Math.min(min, index + 1);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int[] tmpR = {r[0], r[1]};
            int[] tmpB = {b[0], b[1]};
            go(tmpR, tmpB, i, index + 1);
        }
    }

    // 빨간 구슬과 파란 구슬 중 먼저 굴려야하는 구슬을 결정
    static boolean prior(int[] r, int[] b, int d) {
        switch (d) {
            case 0 : return r[0] < b[0];
            case 1 : return r[1] < b[1];
            case 2 : return r[0] > b[0];
            case 3 : return r[1] > b[1];
            default : return false;
        }
    }

    // target 구슬을 이동
    static void move(int[] target, int[] other, int d) {
        int r = target[0];
        int c = target[1];
        while (true) {
            int nr = r + delta[d][0];
            int nc = c + delta[d][1];
            // 벽 또는 다른 구슬을 만난 경우
            if (board[nr][nc] == '#' || (nr == other[0] && nc == other[1])) {
                break;
            }
            // 구슬이 구멍으로 빠진 경우
            if (board[nr][nc] == 'O') {
                r = -1;
                c = -1;
                break;
            }
            r = nr;
            c = nc;
        }
        target[0] = r;
        target[1] = c;
    }
}
