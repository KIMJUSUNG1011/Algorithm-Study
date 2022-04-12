import java.io.*;
import java.util.*;

public class Q17135_2 {

    static int N, M, D, answer = -1;
    static int[][] map = new int[15][15];
    static int[] pos = new int[3];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);

        System.out.print(answer);
    }

    static void dfs(int index, int cnt) {

        if (cnt == 3) {
            play();
            return;
        }

        if (index == M) {
            return;
        }

        pos[cnt] = index;
        dfs(index + 1, cnt + 1);
        pos[cnt] = 0;
        dfs(index + 1, cnt);
    }

    static void play() {

        int[][] tmpMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tmpMap[i][j] = map[i][j];
            }
        }

        int nKill = 0, stage = N + 1;

        while (stage-- > 1) {

            for (int i = 0; i < 3; i++) {

                int col = pos[i], flag = 0;

                for (int j = 0; j < D; j++) {
                    int r = stage - 1;
                    for (int k = col - j; k <= col + j; k++) {
                        if (k >= 0 && r >= 0 && k < M && r < N) {
                            if (tmpMap[r][k] == 1) {
                                tmpMap[r][k] = -stage;
                                flag = 1;
                                nKill++;
                                break;
                            }
                            if (tmpMap[r][k] == -stage) {
                                flag = 1;
                                break;
                            }
                        }
                        if (k < col) {
                            r--;
                        } else {
                            r++;
                        }
                    }
                    if (flag == 1) {
                        break;
                    }
                }
            }
        }

        answer = Math.max(answer, nKill);
    }
}
