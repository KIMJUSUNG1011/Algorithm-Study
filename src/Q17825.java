import java.io.*;
import java.util.*;

public class Q17825 {
    static int[][] board = new int[4][30];
    static int[] dice = new int[10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 10; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }
        init();
    }

    static void init() {

        // 0번 경로 세팅
        for (int i = 0; i <= 20; i++) {
            board[0][i] = i * 2;
        }

        // 1번 경로 세팅
        board[1][6] = 13;
        board[1][7] = 16;
        board[1][8] = 19;
        board[1][9] = 25;
        board[1][10] = 30;
        board[1][11] = 35;
        board[1][12] = 40;

        // 2번 경로 세팅
        board[2][11] = 22;
        board[2][12] = 24;
        board[2][13] = 25;
        board[2][14] = 30;
        board[2][15] = 35;
        board[2][16] = 40;

        // 3번 경로 세팅
        board[3][16] = 28;
        board[3][17] = 27;
        board[3][18] = 26;
        board[3][19] = 25;
        board[3][20] = 30;
        board[3][21] = 35;
        board[3][22] = 40;
    }
}
