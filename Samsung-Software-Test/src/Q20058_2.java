import java.io.*;
import java.util.*;

public class Q20058_2 {

    static int N, Q, lenLarge, lenSmall, sum = 0, max = 0;
    static int[][] A = new int[64][64];
    static int[][] check = new int[64][64];
    static Queue<int[]> q = new LinkedList<>();
    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        lenLarge = (int)Math.pow(2, N);
        for (int i = 0; i < lenLarge; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < lenLarge; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < Q; i++) {
            int L = Integer.parseInt(st.nextToken());
            lenSmall = (int)Math.pow(2, L);
            go();
        }

        for (int i = 0; i < lenLarge; i++) {
            for (int j = 0; j < lenLarge; j++) {
                sum += A[i][j];
            }
        }

        for (int i = 0; i < lenLarge; i++) {
            for (int j = 0; j < lenLarge; j++) {
                if (A[i][j] > 0 && check[i][j] == 0) {
                    bfs(i, j);
                }
            }
        }

        System.out.print(sum + "\n" + max);
    }

    static void go() {

        int[][] tmp = new int[lenLarge][lenLarge];

        for (int i = 0; i < lenLarge / lenSmall; i++) {
            for (int j = 0; j < lenLarge / lenSmall; j++) {
                rotate(i * lenSmall, j * lenSmall, tmp);
            }
        }

        for (int i = 0; i < lenLarge; i++) {
            for (int j = 0; j < lenLarge; j++) {
                A[i][j] = tmp[i][j];
                if (tmp[i][j] == 0) {
                    continue;
                }
                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    int nr = i + delta[k][0];
                    int nc = j + delta[k][1];
                    if (nr < 0 || nc < 0 || nr >= lenLarge || nc >= lenLarge) {
                        continue;
                    }
                    if (tmp[nr][nc] > 0) {
                        cnt++;
                    }
                }
                if (cnt < 3) {
                    A[i][j]--;
                }
            }
        }
    }

    static void rotate(int r, int c, int[][] tmp) {

        for (int i = 0; i < lenSmall; i++) {
            for (int j = 0; j < lenSmall; j++) {
                tmp[j + r][lenSmall - 1 - i + c] = A[i + r][j + c];
            }
        }
    }

    static void bfs(int r, int c) {

        int cnt = 0;
        check[r][c] = 1;
        q.add(new int[]{r, c});

        while (!q.isEmpty()) {
            int[] p = q.poll();
            cnt++;
            for (int k = 0; k < 4; k++) {
                int nr = p[0] + delta[k][0];
                int nc = p[1] + delta[k][1];
                if (nr < 0 || nc < 0 || nr >= lenLarge || nc >= lenLarge) {
                    continue;
                }
                if (A[nr][nc] > 0 && check[nr][nc] == 0) {
                    check[nr][nc] = 1;
                    q.add(new int[]{nr, nc});
                }
            }
        }

        if (cnt > 1) {
            max = Math.max(max, cnt);
        }
    }
}
