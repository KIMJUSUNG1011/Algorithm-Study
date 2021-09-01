import java.io.*;
import java.util.*;

public class Q17472 {
    static int N, M;
    static int[][] map = new int[10][10];
    static ArrayList<Bridge> bridges = new ArrayList<>();
    static int nIsland = 0, sr = -1, sc = -1;
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
                if (map[i][j] == 1) {
                    nIsland++;
                    if (sr == -1 && sc == -1) {
                        sr = i;
                        sc = j;
                    }
                }
            }
        }
        findBridge();
        if (bridges.size() == 0) {
            System.out.print(-1);
            return;
        }

        go(0, 0);
        System.out.print(min);
    }

    static void go(int index, int len) {

        if (index == bridges.size()) {
            if (isConnect(len)) {
                min = Math.min(min, len);
            }
            return;
        }

        Bridge b = bridges.get(index);
        process(b, 1);
        go(index + 1, len + b.len);
        process(b, 0);
        go(index + 1, len);
    }

    static boolean isConnect(int len) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] check = new boolean[N][M];
        q.add(new int[]{sr, sc});
        check[sr][sc] = true;

        int cnt = 0;
        while (!q.isEmpty()) {
            int[] p = q.poll();
            cnt++;
            for (int i = 0; i < 4; i++) {
                int nr = p[0] + delta[i][0];
                int nc = p[1] + delta[i][1];
                if (nr >= 0 && nc >= 0 && nr < N && nc < M) {
                    if (!check[nr][nc] && map[nr][nc] == 1) {
                        q.add(new int[]{nr, nc});
                        check[nr][nc] = true;
                    }
                }
            }
        }
        return (cnt - nIsland == len);
    }

    static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            } System.out.println();
        } System.out.println();
    }

    static void process(Bridge b, int t) {
        for (int i = 0; i < b.len; i++) {
            if (b.type == 0) {
                map[b.r][b.c + i] = t;
            } else {
                map[b.r + i][b.c] = t;
            }
        }
    }

    static void findBridge() {
        // 가능한 모든 가로 다리 생성
        for (int i = 0; i < N; i++) {
            int j = 0, start = -1;
            while (j < M) {
                if (start == -1) {
                    if (j < M - 1 && map[i][j] == 1 && map[i][j + 1] == 0) {
                        start = j + 1;
                    }
                } else {
                    if (map[i][j] == 1) {
                        int len = j - start;
                        if (len > 1) {
                            bridges.add(new Bridge(i, start, len, 0));
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
            int j = 0, start = -1;
            while (j < N) {
                if (start == -1) {
                    if (j < N - 1 && map[j][i] == 1 && map[j + 1][i] == 0) {
                        start = j + 1;
                    }
                } else {
                    if (map[j][i] == 1) {
                        int len = j - start;
                        if (len > 1) {
                            bridges.add(new Bridge(start, i, len, 1));
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
        int r, c, len, type, i1, i2;
        public Bridge(int r, int c, int len, int type) {
            this.r = r;
            this.c = c;
            this.len = len;
            this.type = type;
        }
        public String toString() {
            return r + "," + c + "," + len + "," + type;
        }
    }
}
