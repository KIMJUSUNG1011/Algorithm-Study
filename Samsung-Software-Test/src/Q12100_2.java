import java.io.*;
import java.util.*;

public class Q12100_2 {

    static int N;
    static long answer = -1;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                answer = Math.max(answer, map[i][j]);
            }
        }

        for (int i = 0; i < 4; i++) {
            dfs(0, rotate(map, i));
        }

        System.out.print(answer);
    }

    static void dfs(int index, int[][] map) {

        if (index == 5) {
            return;
        }

        for (int i = 0; i < N; i++) {
            combine(map[i]);
        }

        for (int i = 0; i < 4; i++) {
            dfs(index + 1, rotate(map, i));
        }
    }

    static int[][] rotate(int[][] src, int num) {

        int[][] dst = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (num == 0) {
                    dst[i][j] = src[i][j];
                } else if (num == 1) {
                    dst[j][N - 1 - i] = src[i][j];
                } else if (num == 2) {
                    dst[N - 1 - i][N - 1 - j] = src[i][j];
                } else if (num == 3) {
                    dst[N - 1 - j][i] = src[i][j];
                }
            }
        }

        return dst;
    }

    static void combine(int[] arr) {

        int lastNum = 0, inputPos = 0;

        for (int i = 0; i < N; i++) {

            if (arr[i] == 0) {
                continue;
            }

            if (lastNum == arr[i]) {
                arr[inputPos - 1] *= 2;
                answer = Math.max(answer, arr[inputPos - 1]);
                arr[i] = 0;
                lastNum = 0;
            } else {
                arr[inputPos] = arr[i];
                if (inputPos < i) {
                    arr[i] = 0;
                }
                lastNum = arr[inputPos];
                inputPos++;
            }
        }
    }
}
