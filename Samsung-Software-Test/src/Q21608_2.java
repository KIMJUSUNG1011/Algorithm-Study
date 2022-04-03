import java.io.*;
import java.util.*;

public class Q21608_2 {

    static int N, answer;
    static int[][] check = new int[401][401];
    static int[][] board = new int[20][20];
    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int[] cal = {0, 1, 10, 100, 1000};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N * N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int num = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 4; j++) {
               int x = Integer.parseInt(st.nextToken());
               check[num][x] = 1;
            }
            process(num);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int nLike = 0;
                for (int k = 0; k < 4; k++) {
                    int nr = i + delta[k][0];
                    int nc = j + delta[k][1];
                    if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
                        continue;
                    }
                    if (check[board[i][j]][board[nr][nc]] == 1) {
                        nLike++;
                    }
                }
                answer += cal[nLike];
            }
        }

        System.out.print(answer);
    }

    static void process(int num) {

        // 인접한 칸에 있는 좋아하는 학생의 수
        int nLike = -1;

        // 인접한 칸에 있는 비어있는 칸의 수
        int nBlank = -1;

        // 학생의 자리
        int r = 0, c = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] != 0) {
                    continue;
                }
                int nLikeCurrent = 0, nBlankCurrent = 0;
                for (int k = 0; k < 4; k++) {
                    int nr = i + delta[k][0];
                    int nc = j + delta[k][1];
                    if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
                        continue;
                    }
                    if (check[num][board[nr][nc]] == 1) {
                        nLikeCurrent++;
                    }
                    if (board[nr][nc] == 0) {
                        nBlankCurrent++;
                    }
                }
                if ((nLikeCurrent > nLike) || (nLikeCurrent == nLike && nBlankCurrent > nBlank)) {
                    r = i;
                    c = j;
                    nLike = nLikeCurrent;
                    nBlank = nBlankCurrent;
                }
            }
        }

        board[r][c] = num;
    }
}
