import java.io.*;
import java.util.*;

public class Q19238_2 {

    static int N, M, FLAG;
    static long fuel;
    static int[][] map = new int[20][20];
    static int[][][] dest = new int[402][20][20];
    static int[][] check = new int[20][20];
    static int[] car = new int[2];
    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static Queue<int[]> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine(), " ");
        car[0] = Integer.parseInt(st.nextToken()) - 1;
        car[1] = Integer.parseInt(st.nextToken()) - 1;
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int startR = Integer.parseInt(st.nextToken()) - 1;
            int startC = Integer.parseInt(st.nextToken()) - 1;
            int destR = Integer.parseInt(st.nextToken()) - 1;
            int destC = Integer.parseInt(st.nextToken()) - 1;
            map[startR][startC] = i + 1;
            dest[i + 1][destR][destC] = 1;
        }

        for (int i = 0; i < M; i++) {
            if (bfs() == -1) {
                System.out.print(-1);
                return;
            }
        }

        System.out.print(fuel);
    }

    static int bfs() {

        int dist = 0, num = 0;

        FLAG = 0;
        initCheck();
        check[car[0]][car[1]] = 1;
        q.add(new int[]{car[0], car[1]});

        car[0] = car[1] = N;

        while (!q.isEmpty()) {

            int size = q.size();

            // 같은 깊이 탐색
            for (int i = 0; i < size; i++) {

                int[] p = q.poll();

                // 손님을 만난 경우
                if (map[p[0]][p[1]] > 1) {
                    if (car[0] > p[0] || (car[0] == p[0] && car[1] > p[1])) {
                        car[0] = p[0];
                        car[1] = p[1];
                        num = map[p[0]][p[1]];
                        FLAG = 1;
                    }
                }

                addQueue(p[0], p[1]);
            }

            // 최단거리 승객 찾음
            if (FLAG == 1) {
                while (!q.isEmpty()) { q.poll(); }
                break;
            }

            dist++;
        }

        // 손님에게 갈 수 없는 경우
        if (FLAG == 0) {
            return - 1;
        }

        // 연료 고갈로 업무 종료
        if (fuel < dist) {
            return -1;
        }

        fuel -= dist;
        map[car[0]][car[1]] = 0;

        FLAG = 0;
        initCheck();
        check[car[0]][car[1]] = 1;
        q.add(new int[]{car[0], car[1]});

        // 손님을 목적지로 이동
        while (!q.isEmpty()) {

            int[] p = q.poll();

            // 목적지 도착
            if (dest[num][p[0]][p[1]] == 1) {
                car[0] = p[0];
                car[1] = p[1];
                FLAG = 1;
                while (!q.isEmpty()) { q.poll(); }
                break;
            }

            addQueue(p[0], p[1]);
        }

        dist = check[car[0]][car[1]] - 1;

        // 목적지로 갈 수 없는 경우
        if (FLAG == 0) {
            return - 1;
        }

        // 연료 고갈로 업무 종료
        if (fuel < dist) {
            return - 1;
        }

        // 연료 충전
        fuel += dist;

        return 0;
    }

    static void addQueue(int r, int c) {
        for (int i = 0; i < 4; i++) {
            int nr = r + delta[i][0];
            int nc = c + delta[i][1];
            if ((nr < 0 || nc < 0 || nr >= N || nc >= N) || map[nr][nc] == 1) {
                continue;
            }
            if (check[nr][nc] == 0) {
                check[nr][nc] = check[r][c] + 1;
                q.add(new int[]{nr, nc});
            }
        }
    }

    static void initCheck() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (check[i][j] > 0) {
                    check[i][j] = 0;
                }
            }
        }
    }
}
