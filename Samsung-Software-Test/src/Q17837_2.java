import java.io.*;
import java.util.*;

public class Q17837_2 {

    static int N, K;
    static Board[][] board = new Board[12][12];
    static int[][] map = new int[12][12];
    static int[][] knight = new int[11][4];
    static int[][] delta = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                board[i][j] = new Board();
            }
        }
        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            knight[i][0] = Integer.parseInt(st.nextToken()) - 1;
            knight[i][1] = Integer.parseInt(st.nextToken()) - 1;
            knight[i][2] = Integer.parseInt(st.nextToken()) - 1;
            board[knight[i][0]][knight[i][1]].order[0] = i;
            board[knight[i][0]][knight[i][1]].top++;
        }

        for (int i = 1; i <= 1000; i++) {
            for (int j = 1; j <= K; j++) {
                if (move(j) == 0) {
                    System.out.print(i);
                    return;
                }
            }
        }

        System.out.print(-1);
    }

    static int move(int num) {

        int[] k = knight[num];

        int nr = k[0] + delta[k[2]][0];
        int nc = k[1] + delta[k[2]][1];

        if (!check(nr, nc)) {
            k[2] += (k[2] == 0 || k[2] == 2 ? 1 : -1);
            nr = k[0] + delta[k[2]][0];
            nc = k[1] + delta[k[2]][1];
            if (!check(nr, nc)) {
                return -1;
            }
        }

        Board cur = board[k[0]][k[1]];
        Board next = board[nr][nc];

        int pos = k[3];
        int size = cur.top - pos;

        for (int i = 0; i < size; i++) {
            int n = 0;
            if (map[nr][nc] == 0) {
                n = cur.order[i + pos];
            }
            if (map[nr][nc] == 1) {
                n = cur.order[(cur.top - 1) - i];
            }
            knight[n][0] = nr;
            knight[n][1] = nc;
            knight[n][3] = next.top + i;
            next.order[next.top + i] = n;
        }
        cur.top -= size;
        next.top += size;

        if (next.top >= 4) {
            return 0;
        }

        return -1;
    }

    static boolean check(int r, int c) {
        if ((r >= 0 && c >= 0 && r < N && c < N) && map[r][c] != 2) {
            return true;
        } else {
            return false;
        }
    }

    static class Board {
        int[] order;
        int top;
        public Board() {
            order = new int[10];
            top = 0;
        }
    }
}
