// 블로거 '안산학생' 님의 코드의 도움을 받았습니다.
import java.io.*;
import java.util.*;

public class Q17825 {
    static int[] dice = new int[10];
    static int[] horse = new int[4];
    static int[] score = new int[33];
    static int[] board = new int[33];
    static int[] visited = new int[33];
    static int[] turn = new int[33];
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 10; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }
        init();
        go(0, 0);
        System.out.print(max);
    }

    static void go(int idx, int sum) {

        if (idx == 10) {
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int dice_val = dice[idx];
            int prev = horse[i];
            int now = prev;

            // 현재 위치가 파란색 칸인 경우
            if (turn[now] > 0) {
                now = turn[now];
                dice_val--;
            }

            // 주사위 값 만큼 위치를 이동
            while (dice_val-- > 0) {
                now = board[now];
            }

            // 이동한 위치에 이미 다른 주사위가 있는 경우 말을 고르지 않음
            // 단, 이동을 마치는 칸이 도착점인 경우 고를 수 있도록 함
            if (visited[now] != 0 && now != 21) {
                continue;
            }

            horse[i] = now;
            visited[prev] = 0;
            visited[now] = 1;
            go(idx + 1, sum + score[now]);
            visited[now] = 0;
            visited[prev] = 1;
            horse[i] = prev;
        }
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
