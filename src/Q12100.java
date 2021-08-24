import java.io.*;
import java.util.*;

public class Q12100 {
    static int N;
    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 4; i++) {
            int[][] tmp = new int[N][N];
            rotate(tmp, board, i);
            go(tmp, 0);
        }

        System.out.print(answer);
    }

    static void go(int[][] board, int index) {

        // 종료 조건
        if (index == 5) {
            return;
        }

        combine(board);
        move(board);

        int max = getMax(board);
        answer = Math.max(answer, max);

        // 같은 방향으로 여러번 이동시키는 경우도 가능함
        for (int i = 0; i < 4; i++) {
            int[][] tmp = new int[N][N];
            rotate(tmp, board, i);
            go(tmp, index + 1);
        }
    }

    static int getMax(int[][] arr) {
        int max = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] != 0) {
                    max = Math.max(max, arr[i][j]);
                }
            }
        }
        return max;
    }

    // 시계방향으로 d 만큼 회전한 값을 복사
    static void rotate(int[][] t, int[][] o, int d) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (d == 0) {
                    t[i][j] = o[i][j];
                } else if (d == 1) {
                    t[j][(N - 1) - i] = o[i][j];
                } else if (d == 2) {
                    t[(N - 1) - i][(N - 1) - j] = o[i][j];
                } else {
                    t[(N - 1) - j][i] = o[i][j];
                }
            }
        }
    }

    // 충돌 예정인 블록을 합침
    static void combine(int[][] arr) {
        for (int c = 0; c < N; c++) {
            int or = 0, tr = 1;
            while (tr < N && or < N) {
                if (arr[or][c] == 0) {
                    or++;
                    tr++;
                    continue;
                }
                if (arr[tr][c] == 0) {
                    tr++;
                    continue;
                }
                if (arr[or][c] == arr[tr][c]) {
                    arr[or][c] *= 2;
                    arr[tr][c] = 0;
                    or = tr + 1;
                    tr = or + 1;
                } else {
                    or = tr;
                    tr++;
                }
            }
        }
    }

    // 블록을 이동
    static void move(int[][] arr) {
        for (int c = 0; c < N; c++) {
            int or = 1, tr = 0;
            while (tr < N && or < N) {
                if (arr[tr][c] != 0) {
                    tr++;
                    or++;
                    continue;
                }
                if (arr[or][c] != 0) {
                    arr[tr][c] = arr[or][c];
                    arr[or][c] = 0;
                    tr++;
                }
                or++;
            }
        }
    }
}
