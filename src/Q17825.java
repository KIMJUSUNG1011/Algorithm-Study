import java.io.*;
import java.util.*;

public class Q17825 {
    static int[] dice = new int[10];
    static int[] score = new int[33];
    static int[] board = new int[33];
    static int[] turn = new int[33];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 10; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }
        init();
    }

    static void init() {
        // 점수판 초기화
        for (int i = 0; i <= 20; i++) {
            score[i] = i * 2;
        }
        score[22] = 13;
        score[23] = 16;
        score[24] = 19;
        score[25] = 25;
        score[26] = 22;
        score[27] = 24;
        score[28] = 28;
        score[29] = 27;
        score[30] = 26;
        score[31] = 30;
        score[32] = 35;

        // board[i] : 위치 i 의 다음 위치를 저장
        for (int i = 0; i <= 20; i++) {
            board[i] = i + 1;
        }
        board[22] = 23;
        board[23] = 24;
        board[24] = 25;
        board[26] = 27;
        board[27] = 25;
        board[28] = 29;
        board[29] = 30;
        board[30] = 25;
        board[25] = 31;
        board[31] = 32;
        board[32] = 20;
        board[20] = 21;

        // 도착점에서는 공회전을 시킴
        board[21] = 21;

        // 방향 전환 정보를 저장
        turn[5] = 22;
        turn[10] = 26;
        turn[15] = 28;
    }
}
