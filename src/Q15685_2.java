import java.io.*;
import java.util.*;

public class Q15685_2 {
    static int N;
    static int[][] map = new int[101][101];
    static int[][] delta = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            move(y, x, d, g);
        }

        int answer = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] == 1 & map[i + 1][j] == 1 && map[i][j + 1] == 1 && map[i + 1][j + 1] == 1) {
                   answer++;
                }
            }
        }
        System.out.print(answer);
    }

    static void move(int r, int c, int d, int g) {
        Deque<Integer> dq1 = new LinkedList<>();
        Deque<Integer> dq2 = new LinkedList<>();
        dq1.offer(d);
        for (int i = 0; i < g; i++) {
            while (!dq1.isEmpty()) {
                int d1 = dq1.pollLast();
                int d2 = (d1 + 1) % 4;
                dq2.offer(d2);
                dq2.offerFirst(d1);
            }
            dq1.addAll(dq2);
            dq2.clear();
        }

        map[r][c] = 1;
        while (!dq1.isEmpty()) {
            int nd = dq1.poll();
            int nr = r + delta[nd][0];
            int nc = c + delta[nd][1];
            map[nr][nc] = 1;
            r = nr;
            c = nc;
        }
    }
}
