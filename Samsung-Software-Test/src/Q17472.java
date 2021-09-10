import java.io.*;
import java.util.*;

// union_find 알고리즘 활용
public class Q17472 {
    static int N, M, nIsland;
    static int[][] map = new int[10][10];
    static boolean[][][] check = new boolean[100][100][10];
    static ArrayList<Bridge> bridges = new ArrayList<>();
    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int[] parent = new int[100];

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
        getMinDistance();
        Collections.sort(bridges);

        for (int i = 1; i <= nIsland; i++) {
            parent[i] = i;
        }

        int answer = 0;
        for (int i = 0; i < bridges.size(); i++) {
            Bridge b = bridges.get(i);
            if (find(b.i1) != find(b.i2)) {
                union(b.i1, b.i2);
                answer += b.len;
            }
        }

        int p = find(1);
        for (int i = 2; i <= nIsland; i++) {
            if (p != find(i)) {
                System.out.print(-1);
                return;
            }
        }

        System.out.print(answer);
    }

    // a 노드의 루트를 리턴
    static int find(int a) {
        if (parent[a] == a) {
            return a;
        } else {
            return find(parent[a]);
        }
    }

    // a 노드와 b 노드를 연결
    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
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

    // 섬들 사이의 최단거리 구하기
    static void getMinDistance() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    continue;
                }
                int now = map[i][j];
                for (int k = 0; k < 4; k++) {
                    int r = i, c = j, len = 0;
                    while (true) {
                        int nr = r + delta[k][0];
                        int nc = c + delta[k][1];
                        if ((nr < 0 || nc < 0 || nr >= N || nc >= M) || map[nr][nc] == now) {
                            break;
                        }
                        if (map[nr][nc] != 0) {
                            int end = map[nr][nc];
                            if (len > 1 && !check[now][end][len] && !check[end][now][len]) {
                                bridges.add(new Bridge(now, end, len));
                                check[now][end][len] = true;
                            }
                            break;
                        }
                        r = nr;
                        c = nc;
                        len++;
                    }
                }
            }
        }
    }

    static class Bridge implements Comparable<Bridge> {
        int i1, i2, len;
        public Bridge(int i1, int i2, int len) {
            this.i1 = i1;
            this.i2 = i2;
            this.len = len;
        }
        @Override
        public int compareTo(Bridge o) {
            return this.len - o.len;
        }
        public String toString() {
            return i1 + "," + i2 + "," + len;
        }
    }
}
