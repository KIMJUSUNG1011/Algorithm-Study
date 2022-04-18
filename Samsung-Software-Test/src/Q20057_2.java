import java.io.*;
import java.util.*;

public class Q20057_2 {

    static int N;
    static long answer;
    static int[][] map = new int[499][499];
    static int[][] check = new int[499][499];
    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int r = N / 2, c = N / 2, dir = 3;

        while (r > 0 || c > 0) {

            check[r][c] = 1;
            spread(r, c, dir);

            r += delta[dir][0];
            c += delta[dir][1];

            int nDir = (dir + 3) % 4;
            if (check[r + delta[nDir][0]][c + delta[nDir][1]] == 0) {
                dir = nDir;
            }
        }

        System.out.print(answer);
    }

    static void spread(int r, int c, int dir) {

        int y = map[r + delta[dir][0]][c + delta[dir][1]];
        int dirRight = (dir + 1) % 4;
        int dirLeft = (dir + 3) % 4;

        int sum = 0;
        sum += wing(r + delta[dirRight][0], c + delta[dirRight][1], y, dir, dirRight);
        sum += wing(r + delta[dirLeft][0], c + delta[dirLeft][1], y, dir, dirLeft);
        sum += calc(r + delta[dir][0] * 3, c + delta[dir][1] * 3, y, 0.05);

        calc(r + delta[dir][0] * 2, c + delta[dir][1] * 2, y - sum, 1);
        map[r + delta[dir][0]][c + delta[dir][1]] = 0;
    }

    static int wing(int r, int c, int y, int front, int side) {
        int res = 0;
        res += calc(r, c, y, 0.01);
        r += delta[front][0];
        c += delta[front][1];
        res += calc(r, c, y, 0.07);
        res += calc(r + delta[side][0], c + delta[side][1], y, 0.02);
        res += calc(r + delta[front][0], c + delta[front][1], y, 0.1);
        return res;
    }

    static int calc(int r, int c, int y, double rate) {
        int res = (int)(y * rate);
        if (r >= 0 && c >= 0 && r < N && c < N) {
            map[r][c] += res;
        } else {
            answer += res;
        }
        return res;
    }

    static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%3d", map[i][j]);
            } System.out.println();
        } System.out.println();
    }
}
