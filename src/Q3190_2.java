import java.util.*;
import java.io.*;

public class Q3190_2 {
    static int N, K, L;
    static int[][] board = new int[101][101];
    static char[] directions = new char[10001];
    static int[][] delta = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            // 사과는 1 로 표시
            board[r][c] = 1;
        }

        L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            directions[x] = st.nextToken().charAt(0);
        }

        // 뱀은 2 로 표시
        board[1][1] = 2;
        Deque<int[]> snake = new ArrayDeque<>();
        snake.add(new int[]{1, 1});
        int time = 0, dir = 0;
        while (true) {
            if (snake.isEmpty()) {
                break;
            }
            time++;
            int[] head = snake.peekLast();
            int nr = head[0] + delta[dir][0];
            int nc = head[1] + delta[dir][1];

            // 벽을 만난 경우 종료
            if (nr < 1 || nc < 1 || nr > N || nc > N) {
                break;
            }

            // 자신의 몸과 부딪히면 종료
            if (board[nr][nc] == 2) {
                break;
            }

            // 사과를 먹지 못한 경우 꼬리를 잘라냄
            if (board[nr][nc] != 1) {
                int[] tail = snake.poll();
                board[tail[0]][tail[1]] = 0;
            }

            // 다음에 이동하는 칸을 뱀의 머리에 추가
            snake.add(new int[]{nr, nc});
            board[nr][nc] = 2;

            if (directions[time] == 'L') {
                dir = (dir + 3) % 4;
            } else if (directions[time] == 'D') {
                dir = (dir + 1) % 4;
            }
        }

        System.out.print(time);
    }
}
