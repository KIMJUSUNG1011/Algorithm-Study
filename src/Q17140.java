import java.io.*;
import java.util.*;

public class Q17140 {
    static int R, C, K;
    static int r = 3, c = 3;
    static int[][] arr = new int[111][111];
    static PriorityQueue<Pair> q = new PriorityQueue<>(
            (p1, p2) -> p1.cnt != p2.cnt ? p1.cnt - p2.cnt : p1.num - p2.num
    );

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
        }

        for (int t = 0; t <= 100; t++) {
            if ((R - 1 < r && C - 1 < c) && arr[R - 1][C - 1] == K) {
                System.out.print(t);
                return;
            }

            int[] cnt_arr;
            if (r >= c) {
                int max_c = 0;
                for (int i = 0; i < r; i++) {
                    cnt_arr = new int[101];
                    for (int j = 0; j < c; j++) {
                        if (arr[i][j] > 0) {
                            cnt_arr[arr[i][j]]++;
                        }
                    }
                    for (int j = 1; j <= 100; j++) {
                        if (cnt_arr[j] > 0) {
                            q.add(new Pair(j, cnt_arr[j]));
                        }
                    }
                    int idx = 0;
                    while (!q.isEmpty()) {
                        Pair p = q.poll();
                        arr[i][idx++] = p.num;
                        arr[i][idx++] = p.cnt;
                    }
                    for (int j = idx; j <= 100; j++) {
                        arr[i][j] = 0;
                    }
                    // 가장 길이가 긴 행의 길이를 찾음
                    max_c = Math.max(max_c, idx);
                }
                c = Math.min(max_c, 100);
            } else {
                int max_r = 0;
                for (int i = 0; i < c; i++) {
                    cnt_arr = new int[101];
                    for (int j = 0; j < r; j++) {
                        if (arr[j][i] > 0) {
                            cnt_arr[arr[j][i]]++;
                        }
                    }
                    for (int j = 1; j <= 100; j++) {
                        if (cnt_arr[j] > 0) {
                            q.add(new Pair(j, cnt_arr[j]));
                        }
                    }
                    int idx = 0;
                    while (!q.isEmpty()) {
                        Pair p = q.poll();
                        arr[idx++][i] = p.num;
                        arr[idx++][i] = p.cnt;
                    }
                    for (int j = idx; j <= 100; j++) {
                        arr[j][i] = 0;
                    }
                    // 가장 길이가 긴 열의 길이를 찾음
                    max_r = Math.max(max_r, idx);
                }
                r = Math.min(max_r, 100);
            }
        }

        System.out.print(-1);
    }

    static class Pair {
        int num, cnt;
        public Pair(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
}
