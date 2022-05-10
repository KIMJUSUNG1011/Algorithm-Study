import java.io.*;
import java.util.*;

public class Q13460_2 {

    static int N, M, answer = 11;
    static char[][] map = new char[10][10];
    static int[][] ball = new int[2][2];
    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'R') {
                    ball[0][0] = i;
                    ball[0][1] = j;
                    map[i][j] = '.';
                }
                if (map[i][j] == 'B') {
                    ball[1][0] = i;
                    ball[1][1] = j;
                    map[i][j] = '.';
                }
            }
        }

        dfs(1, -1);

        if (answer == 11) {
            System.out.print(-1);
        } else {
            System.out.print(answer);
        }
    }

    static void dfs(int index, int prevDir) {

        if (index == 11) {
            return;
        }

        int[][] prev = new int[2][2];

        for (int i = 0; i < 4; i++) {
            prev[i / 2][i % 2] = ball[i / 2][i % 2];
        }

        for (int i = 0; i < 4; i++) {

            // 같은 방향, 왔던 방향으로 이동 안 함
            if (prevDir >= 0 && (i == prevDir || i == (prevDir + 2) % 4)) {
                continue;
            }

            // 우선순위 결정 후 구슬 굴림
            if (getPrior(i) <= 0) {
                move(0, i);
                move(1, i);
            } else {
                move(1, i);
                move(0, i);
            }

            int[] red = ball[0];
            int[] blue = ball[1];

            if (map[blue[0]][blue[1]] == 'O') {
                restore(prev);
                continue;
            }

            // 탈출 성공
            if (map[red[0]][red[1]] == 'O') {
                answer = Math.min(answer, index);
                restore(prev);
                continue;
            }

            dfs(index + 1, i);
            restore(prev);
        }
    }

    // 어떤 구슬에게 우선순위가 있는지 결정
    // ret <= 0 : 빨간구슬
    // ret > 0 : 파란구슬
    static int getPrior(int dir) {

        int[] red = ball[0];
        int[] blue = ball[1];

        if (dir == 0) {
            return red[0] - blue[0];
        } else if (dir == 1) {
            return blue[1] - red[1];
        } else if (dir == 2) {
            return blue[0] - red[0];
        } else if (dir == 3) {
            return red[1] - blue[1];
        } else {
            return -1;
        }
    }

    static void move(int color, int dir) {

        for (int i = 0; i < 10; i++) {

            int nr = ball[color][0] + delta[dir][0];
            int nc = ball[color][1] + delta[dir][1];

            // 탈출
            if (map[nr][nc] == 'O') {
                ball[color][0] = nr;
                ball[color][1] = nc;
                break;
            }

            if (map[nr][nc] == '#') {
                break;
            }

            if (ball[1 - color][0] == nr && ball[1 - color][1] == nc) {
                break;
            }

            ball[color][0] = nr;
            ball[color][1] = nc;
        }
    }

    static void restore(int[][] prev) {
        for (int i = 0; i < 4; i++) {
            ball[i / 2][i % 2] = prev[i / 2][i % 2];
        }
    }
}
