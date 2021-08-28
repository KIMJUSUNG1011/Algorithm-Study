import java.io.*;
import java.util.*;

public class Q17135 {
    static int N, M, D;
    static int[][] map = new int[15][15];
    static boolean[][] check = new boolean[16][15];
    static ArrayList<int[]> arrows = new ArrayList<>();
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            arrows.add(new int[]{N, i});
        }

        go(0, 0);
        System.out.print(max);
    }

    static void go(int index, int cnt) {

        if (cnt == 3) {
            play(copy(map));
            return;
        }

        if (index == arrows.size()) {
            return;
        }

        int r = arrows.get(index)[0];
        int c = arrows.get(index)[1];
        check[r][c] = true;
        go(index + 1, cnt + 1);
        check[r][c] = false;
        go(index + 1, cnt);
    }

    static void play(int[][] tmp_map) {
        int nDead = 0;
        while (true) {
            boolean[][] dead = new boolean[N][M];
            for (int i = 0; i < M; i++) {
                if (check[N][i]) {
                    int[] p = shortest(N, i, tmp_map);
                    if (p != null) {
                        dead[p[0]][p[1]] = true;
                    }
                }
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (dead[i][j]) {
                        tmp_map[i][j] = 0;
                        nDead++;
                    }
                }
            }
            // 적이 아래로 한칸씩 이동
            boolean endFlag = true;
            for (int i = N - 1; i >= 0; i--) {
                for (int j = 0; j < M; j++) {
                    if (tmp_map[i][j] != 0) {
                        endFlag = false;
                    } else {
                        continue;
                    }
                    if (i + 1 < N) {
                        tmp_map[i + 1][j] = tmp_map[i][j];
                    }
                    tmp_map[i][j] = 0;
                }
            }

            if (endFlag) {
                max = Math.max(max, nDead);
                break;
            }
        }
    }

    static int[] shortest(int r, int c, int[][] tmp_map) {
        int[] res = {-1, -1};
        int d = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tmp_map[i][j] == 0) {
                    continue;
                }

                int tmp_d = Math.abs(r - i) + Math.abs(c - j);
                if (tmp_d > D) {
                    continue;
                }

                if (tmp_d < d) {
                    res[0] = i;
                    res[1] = j;
                    d = tmp_d;
                } else if (tmp_d == d) {
                    if (j < res[1]) {
                        res[0] = i;
                        res[1] = j;
                    }
                }
            }
        }
        if (res[0] == -1 && res[1] == -1) {
            return null;
        } else {
            return res;
        }
    }

    static int[][] copy(int[][] o) {
        int[][] t = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
               t[i][j] = o[i][j];
            }
        }
        return t;
    }
}
