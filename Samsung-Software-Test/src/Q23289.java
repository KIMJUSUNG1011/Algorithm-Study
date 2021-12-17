import java.io.*;
import java.util.*;

public class Q23289 {

    static int R, C, K, W;
    static int[][] board = new int[20][20];
    static List<int[]> targets = new ArrayList<>();
    static List<Machine> machines = new ArrayList<>();
    static int[][][][] walls = new int[2][20][20][20];
    static int[][][] winds = new int[400][20][20];
    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int answer = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());

                // 조사해야 하는 칸
                if (board[i][j] == 5) {
                    targets.add(new int[]{i ,j});
                    board[i][j] = 0;
                }

                // 온풍기의 위치
                if (board[i][j] >= 1 && board[i][j] <= 4) {

                    // 계산 편의를 위해 방향을 재설정
                    int dir = board[i][j];
                    if (dir == 2) {
                        dir = 3;
                    } else if (dir == 3) {
                        dir = 0;
                    } else if (dir == 4) {
                        dir = 2;
                    }

                    machines.add(new Machine(i, j, dir));
                    board[i][j] = 0;
                }
            }
        }

        W = Integer.parseInt(br.readLine());
        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int t = Integer.parseInt(st.nextToken());

            // 세로 방향의 벽
            if (t == 0) {
                walls[0][y][x][x - 1] = 1;
                walls[0][y][x - 1][x] = 1;
            }

            // 가로 방향의 벽
            if (t == 1) {
                walls[1][x][y][y + 1] = 1;
                walls[1][x][y + 1][y] = 1;
            }
        }

        // 온풍기에서 나오는 바람은 매번 동일하기 때문에
        // 처음 한 번만 구하면 됨
        for (int i = 0; i < machines.size(); i++) {
            Machine m = machines.get(i);
            wind(m.r, m.c, m.dir, i);
        }

        while (answer <= 100) {

            // 바람 처리
            for (int i = 0; i < machines.size(); i++) {
                for (int j = 0; j < R; j++) {
                    for (int k = 0; k < C; k++) {
                        board[j][k] += winds[i][j][k];
                    }
                }
            }

            // 온도 조절
            control();

            // 가장 바깥쪽 칸의 온도를 1씩 감소
            decrease();

            // 초콜릿 하나 먹음
            answer++;

            // 모든 칸의 온도가 K 이상이 되었는지 검사
            if (test()) {
                break;
            }
        }

        System.out.print(answer);
    }

    // 바람 처리
    // 큐를 사용해야함
    static void wind(int r, int c, int dir, int order) {

        Queue<int[]> q = new LinkedList<>();
        int[][] check = new int[20][20];

        r += delta[dir][0];
        c += delta[dir][1];
        check[r][c] = 5;
        q.add(new int[]{r, c});

        while (!q.isEmpty()) {
            int[] p = q.poll();
            int r1, c1, r2, c2;

            if (check[p[0]][p[1]] <= 1) {
                continue;
            }

            // 범위 체크, 방문 여부 체크, 장애물 체크
            r = p[0] + delta[dir][0];
            c = p[1] + delta[dir][1];
            if ((r >= 0 && c >= 0 && r < R && c < C) && check[r][c] == 0) {
                if (isWall(p[0], p[1], dir) == 0) {
                    check[r][c] = check[p[0]][p[1]] - 1;
                    q.add(new int[]{r, c});
                }
            }

            r1 = r + delta[(dir + 1) % 4][0];
            c1 = c + delta[(dir + 1) % 4][1];
            if ((r1 >= 0 && c1 >= 0 && r1 < R && c1 < C) && check[r1][c1] == 0) {
                if (isWall(p[0], p[1], (dir + 1) % 4) == 0 && isWall(r1, c1, (dir + 2) % 4) == 0) {
                    check[r1][c1] = check[p[0]][p[1]] - 1;
                    q.add(new int[]{r1, c1});
                }
            }

            r2 = r + delta[(dir + 3) % 4][0];
            c2 = c + delta[(dir + 3) % 4][1];
            if ((r2 >= 0 && c2 >= 0 && r2 < R && c2 < C) && check[r2][c2] == 0) {
                if (isWall(p[0], p[1], (dir + 3) % 4) == 0 && isWall(r2, c2, (dir + 2) % 4) == 0) {
                    check[r2][c2] = check[p[0]][p[1]] - 1;
                    q.add(new int[]{r2, c2});
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                winds[order][i][j] = check[i][j];
            }
        }
    }

    // 벽 확인
    static int isWall(int r, int c, int dir) {

        if (dir == 0 || dir == 2) {
            return walls[0][c][r][r + delta[dir][0]];
        } else {
            return walls[1][r][c][c + delta[dir][1]];
        }
    }

    static void control() {

        int[][] tmp = new int[20][20];
        int[][][][] check = new int[20][20][20][20];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                tmp[i][j] = board[i][j];
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                for (int k = 0; k < 4; k++) {
                    int nr = i + delta[k][0];
                    int nc = j + delta[k][1];
                    if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
                        continue;
                    }
                    if (check[i][j][nr][nc] == 1 || check[nr][nc][i][j] == 1) {
                        continue;
                    }
                    if (isWall(i, j, k) == 1) {
                        continue;
                    }

                    check[i][j][nr][nc] = 1;
                    check[nr][nc][i][j] = 1;

                    int val = Math.abs(board[i][j] - board[nr][nc]) / 4;
                    if (board[i][j] > board[nr][nc]) {
                        tmp[i][j] -= val;
                        tmp[nr][nc] += val;
                    } else {
                        tmp[i][j] += val;
                        tmp[nr][nc] -= val;
                    }
                }
            }
        }

        board = tmp;
    }

    static void decrease() {

        int r = 0, c = 0, dir = 1;

        while (true) {

            if (board[r][c] > 0) {
                board[r][c]--;
            }

            int nr = r + delta[dir][0];
            int nc = c + delta[dir][1];

            if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
                dir = (dir + 1) % 4;
                nr = r + delta[dir][0];
                nc = c + delta[dir][1];
            }

            r = nr;
            c = nc;

            if (r == 0 && c == 0) {
                break;
            }
        }
    }

    static boolean test() {

        for (int i = 0; i < targets.size(); i++) {
            int[] p = targets.get(i);
            if (board[p[0]][p[1]] < K) {
                return false;
            }
        }

        return true;
    }

    static class Machine {

        int r, c, dir;

        public Machine(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }
}


// 1. 온풍기에서 나오는 바람 처리
// 2. 온도 조절
// 3. 가장 바깥쪽 칸의 온도를 1씩 감소
