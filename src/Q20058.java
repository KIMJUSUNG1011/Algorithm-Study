import java.io.*;
import java.util.*;

public class Q20058 {
    static int N, Q, len;
    static int[][] board = new int[64][64];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        len = (int)Math.pow(2, N);
        for (int i = 0; i < len; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < len; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < Q; i++) {
            int L = Integer.parseInt(st.nextToken());
            solve(L);
        }
    }

    static void solve(int L) {
        System.out.println(L);
        int partLen = (int)Math.pow(2, L);
        int[][] tmpBoard = new int[len][len];
        for (int i = 0; i <= len - 1; i += partLen) {
            for (int j = 0; j <= len - 1; j += partLen) {
                rotate(i, j, partLen, tmpBoard);
            }
        }
        printBoard(tmpBoard);
    }

    static void rotate(int r, int c, int partLen, int[][] tmp) {
        int s1 = r + (c + partLen - 1);
        int s2 = r - c;
        System.out.println(partLen);
        System.out.println(r + "," + c + " / " + s1 + "," + s2);
        for (int i = r; i < r + partLen; i++) {
            for (int j = c; j < c + partLen; j++) {
                tmp[s2 + j][s1 - i] = board[i][j];
            }
        }
    }

    static void printBoard(int[][] board) {
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                System.out.print(board[i][j] + " ");
            } System.out.println();
        } System.out.println();
    }
}
