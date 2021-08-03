import java.io.*;
import java.util.*;

public class Q17140 {
    static int R, C, K;
    static int r = 3, c = 3;
    static int[][] arr = new int[3][3];

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

            // 정답 발견
            if (R - 1 < r && C - 1 < c) {
                if (arr[R - 1][C - 1] == K) {
                    System.out.print(t);
                    return;
                }
            }

            ArrayList<Queue<Pair>> q_list = new ArrayList<>();
            int[][] tmp_arr;
            int[] cnt_arr;
            if (r >= c) {   // R 연산
                int max_c = 0;
                for (int i = 0; i < r; i++) {
                    cnt_arr = new int[101];
                    PriorityQueue<Pair> q = new PriorityQueue<>(
                            (p1, p2) -> p1.cnt != p2.cnt ? p1.cnt - p2.cnt : p1.num - p2.num
                    );
                    int cur_c = 0;
                    for (int j = 0; j < c; j++) {
                        if (arr[i][j] > 0) {
                            cnt_arr[arr[i][j]]++;
                        }
                    }
                    for (int j = 1; j <= 100; j++) {
                        if (cnt_arr[j] > 0) {
                            q.add(new Pair(j, cnt_arr[j]));
                            cur_c += 2;
                        }
                    }
                    max_c = Math.max(max_c, cur_c);
                    q_list.add(q);
                }
                c = Math.min(max_c, 100);
                tmp_arr = new int[r][c];
                for (int i = 0; i < r; i++) {
                    int idx = 0;
                    Queue<Pair> tmp_q = q_list.get(i);
                    while (!tmp_q.isEmpty()) {
                        Pair p = tmp_q.poll();
                        tmp_arr[i][idx] = p.num;
                        tmp_arr[i][idx + 1] = p.cnt;
                        idx += 2;
                        if (idx > 100) {
                            break;
                        }
                    }
                }
            } else {        // C 연산
                int max_r = 0;
                for (int i = 0; i < c; i++) {
                    cnt_arr = new int[101];
                    PriorityQueue<Pair> q = new PriorityQueue<>(
                            (p1, p2) -> p1.cnt != p2.cnt ? p1.cnt - p2.cnt : p1.num - p2.num
                    );
                    int cur_r = 0;
                    for (int j = 0; j < r; j++) {
                        if (arr[j][i] > 0) {
                            cnt_arr[arr[j][i]]++;
                        }
                    }
                    for (int j = 1; j <= 100; j++) {
                        if (cnt_arr[j] > 0) {
                            q.add(new Pair(j, cnt_arr[j]));
                            cur_r += 2;
                        }
                    }
                    max_r = Math.max(max_r, cur_r);
                    q_list.add(q);
                }
                r = Math.min(max_r, 100);
                tmp_arr = new int[r][c];
                for (int i = 0; i < c; i++) {
                    int idx = 0;
                    Queue<Pair> tmp_q = q_list.get(i);
                    while (!tmp_q.isEmpty()) {
                        Pair p = tmp_q.poll();
                        tmp_arr[idx][i] = p.num;
                        tmp_arr[idx + 1][i] = p.cnt;
                        idx += 2;
                        if (idx > 100) {
                            break;
                        }
                    }
                }
            }
            arr = tmp_arr;
        }

        System.out.print(-1);
    }

    static void print_arr(int[][] arr) {
        for (int[] ints : arr) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            } System.out.println();
        } System.out.println();
    }

    static class Pair {
        int num, cnt;
        public Pair(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
}
