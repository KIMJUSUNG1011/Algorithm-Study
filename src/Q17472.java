import java.io.*;
import java.util.*;

public class Q17472 {
    static int N, M, nIsland;
    static int[][] map = new int[10][10];
    static ArrayList<Bridge> bridges = new ArrayList<>();
    static ArrayList<Bridge> select = new ArrayList<>();
    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        divideBridge();
        findBridge();

        if (bridges.size() == 0) {
            System.out.print(-1);
            return;
        }

        go(0, 0);
        if (min == Integer.MAX_VALUE) {
            System.out.print(-1);
        } else {
            System.out.print(min);
        }
    }

    static void go(int index, int len) {

        if (index == bridges.size()) {
            boolean[][] con = new boolean[101][101];
            boolean[] check = new boolean[nIsland + 1];
            for (Bridge b : select) {
                con[b.i1][b.i2] = true;
                con[b.i2][b.i1] = true;
            }
            Queue<Integer> q = new LinkedList<>();
            q.add(1);
            check[1] = true;
            int cnt = 0;
            while (!q.isEmpty()) {
                int p = q.poll();
                cnt++;
                for (int i = 1; i <= nIsland; i++) {
                    if (con[p][i] && !check[i]) {
                        q.add(i);
                        check[i] = true;
                    }
                }
            }
            if (cnt == nIsland) {
                min = Math.min(min, len);
            }
            return;
        }

        Bridge b = bridges.get(index);
        select.add(b);
        go(index + 1, len + b.len);
        select.remove(select.size() - 1);
        go(index + 1, len);
    }

    // 섬에 번호 붙이기
    static void divideBridge() {
        int cnt = 0;
        boolean[][] check = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0 && !check[i][j]) {
                    ++cnt;
                    Queue<int[]> q = new LinkedList<>();
                    q.add(new int[]{i, j});
                    check[i][j] = true;
                    while (!q.isEmpty()) {
                        int[] p = q.poll();
                        map[p[0]][p[1]] = cnt;
                        for (int k = 0; k < 4; k++) {
                            int nr = p[0] + delta[k][0];
                            int nc = p[1] + delta[k][1];
                            if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
                                continue;
                            }
                            if (map[nr][nc] != 0 && !check[nr][nc]) {
                                q.add(new int[]{nr, nc});
                                check[nr][nc] = true;
                            }
                        }
                    }
                }
            }
        }
        nIsland = cnt;
    }

    // 모든 가능한 다리를 찾기
    static void findBridge() {
        // 가능한 모든 가로 다리 생성
        for (int i = 0; i < N; i++) {
            int j = 0, start = -1, i1 = -1, i2 = -1;
            while (j < M) {
                if (start == -1) {
                    if (j < M - 1 && map[i][j] != 0 && map[i][j + 1] == 0) {
                        i1 = map[i][j];
                        start = j + 1;
                    }
                } else {
                    if (map[i][j] != 0) {
                        i2 = map[i][j];
                        int len = j - start;
                        if (len > 1) {
                            bridges.add(new Bridge(i1, i2, len, 0));
                        }
                        start = -1;
                        continue;
                    }
                }
                j++;
            }
        }
        // 가능한 모든 세로 다리 생성
        for (int i = 0; i < M; i++) {
            int j = 0, start = -1, i1 = -1, i2 = -1;
            while (j < N) {
                if (start == -1) {
                    if (j < N - 1 && map[j][i] != 0 && map[j + 1][i] == 0) {
                        i1 = map[j][i];
                        start = j + 1;
                    }
                } else {
                    if (map[j][i] != 0) {
                        i2 = map[j][i];
                        int len = j - start;
                        if (len > 1) {
                            bridges.add(new Bridge(i1, i2, len, 1));
                        }
                        start = -1;
                        continue;
                    }
                }
                j++;
            }
        }
    }

    static class Bridge {
        int i1, i2, len, type;
        public Bridge(int i1, int i2, int len, int type) {
            this.i1 = i1;
            this.i2 = i2;
            this.len = len;
            this.type = type;
        }
    }
}
