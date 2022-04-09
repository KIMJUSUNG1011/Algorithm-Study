import java.io.*;
import java.util.*;

public class Q17140_2 {

    static int r, c, k, R = 3, C = 3, cnt = -1;
    static int[][] A = new int[3][3];
    static int[][] count = new int[101][101];
    static Queue<Pair> pairs = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        r = Integer.parseInt(st.nextToken()) - 1;
        c = Integer.parseInt(st.nextToken()) - 1;
        k = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 3; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (cnt++ < 100) {

            if ((r < R && c < C) && A[r][c] == k) {
                break;
            }

            if (R >= C) {
                goRow();
            } else {
                goCol();
            }
        }

        if (cnt == 101) {
            System.out.print(-1);
        } else {
            System.out.print(cnt);
        }
    }

    static void goRow() {

        int max = 0;

        for (int i = 0; i < R; i++) {
            int nType = 0;
            for (int j = 0; j < C; j++) {
                if (A[i][j] != 0) {
                    count[i][A[i][j]]++;
                    if (count[i][A[i][j]] == 1) {
                        nType++;
                    }
                }
            }
            max = Math.max(max, nType);
        }

        C = Math.min(max * 2, 100);
        A = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 1; j < 101; j++) {
                if (count[i][j] > 0) {
                    pairs.add(new Pair(j, count[i][j]));
                    count[i][j] = 0;
                }
            }
            int idx = 0;
            while (!pairs.isEmpty()) {
                Pair p = pairs.poll();
                A[i][idx++] = p.num;
                A[i][idx++] = p.cnt;
            }
        }
    }

    static void goCol() {

        int max = 0;

        for (int i = 0; i < C; i++) {
            int nType = 0;
            for (int j = 0; j < R; j++) {
                if (A[j][i] != 0) {
                    count[i][A[j][i]]++;
                    if (count[i][A[j][i]] == 1) {
                        nType++;
                    }
                }
            }
            max = Math.max(max, nType);
        }

        R = Math.min(max * 2, 100);
        A = new int[R][C];

        for (int i = 0; i < C; i++) {
            for (int j = 1; j < 101; j++) {
                if (count[i][j] > 0) {
                    pairs.add(new Pair(j, count[i][j]));
                    count[i][j] = 0;
                }
            }
            int idx = 0;
            while (!pairs.isEmpty()) {
                Pair p = pairs.poll();
                A[idx++][i] = p.num;
                A[idx++][i] = p.cnt;
            }
        }
    }

    static class Pair implements Comparable<Pair> {

        int num, cnt;

        public Pair(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.cnt != o.cnt) {
                return this.cnt - o.cnt;
            } else {
                return this.num - o.num;
            }
        }
    }
}
