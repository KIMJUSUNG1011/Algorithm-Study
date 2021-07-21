import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14503_2 {
    static int N, M;
    static int[][] board = new int[51][51];
    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
           for (int j = 0; j < M; j++) {
               board[i][j] = Integer.parseInt(st.nextToken());
           }
        }

        // 청소한 구역은 2 로 표시
        board[r][c] = 2;
        answer++;
        move(r, c, d);
        System.out.print(answer);
    }

    static void move(int r, int c, int d) {
        for (int i = 0; i < 4; i++) {
            d = (d + 3) % 4;
            int nr = r + delta[d][0];
            int nc = c + delta[d][1];

            // 한 방향이라도 이동할 방향이 있을 경우 후진을 하지 않음
            if (board[nr][nc] == 0) {
                board[nr][nc] = 2;
                answer++;
                move(nr, nc, d);
                return;
            }
        }

        // 후진
        int br = r + delta[(d + 2) % 4][0];
        int bc = c + delta[(d + 2) % 4][1];

        // 종료 조건
        if (board[br][bc] == 1) {
            return;
        }
        move(br, bc, d);
    }
}
