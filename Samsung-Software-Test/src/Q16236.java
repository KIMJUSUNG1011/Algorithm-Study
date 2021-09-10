import java.io.*;
import java.util.*;

public class Q16236 {
    static int N;
    static int[][] map = new int[20][20];
    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static Fish shark;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    shark = new Fish(i, j, 0);
                    map[i][j] = 0;
                }
            }
        }

        int t = 0;
        while (true) {
            ArrayList<Fish> fishes = new ArrayList<>();
            Queue<int[]> q = new LinkedList<>();
            int[][] check = new int[N][N];
            q.add(new int[]{shark.r, shark.c});
            check[shark.r][shark.c] = 1;
            while (!q.isEmpty()) {
                int[] p = q.poll();
                for (int i = 0; i < 4; i++) {
                    int nr = p[0] + delta[i][0];
                    int nc = p[1] + delta[i][1];
                    if ((nr < 0 || nc < 0 || nr >= N || nc >= N) || map[nr][nc] > shark.size) {
                        continue;
                    }
                    if (check[nr][nc] == 0) {
                        q.add(new int[]{nr, nc});
                        check[nr][nc] = check[p[0]][p[1]] + 1;
                        if (map[nr][nc] > 0 && map[nr][nc] < shark.size) {
                            int d = check[nr][nc] - 1;
                            fishes.add(new Fish(nr, nc, d));
                        }
                    }
                }
            }

            // 종료 조건
            if (fishes.size() == 0) {
                break;
            }

            Collections.sort(fishes);
            Fish food = fishes.get(0);
            shark.r = food.r;
            shark.c = food.c;
            shark.cnt++;
            if (shark.size == shark.cnt) {
                shark.size++;
                shark.cnt = 0;
            }
            t += food.d;
            map[food.r][food.c] = 0;
        }

        System.out.print(t);
    }

    static class Fish implements Comparable<Fish> {
        int r, c, d, size, cnt;
        public Fish(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.size = 2;
            this.cnt = 0;
        }
        @Override
        public int compareTo(Fish o) {
            if (this.d != o.d) {
                return this.d - o.d;
            } else if (this.r != o.r) {
                return this.r - o.r;
            } else {
                return this.c - o.c;
            }
        }
        public String toString() {
            return r + "," + c + "," + d;
        }
    }
}
