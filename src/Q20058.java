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
            rotate(L);
        }
    }

    static void rotate(int L) {

    }
}
