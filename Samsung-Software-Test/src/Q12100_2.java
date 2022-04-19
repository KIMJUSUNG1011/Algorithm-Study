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
            }
        }

        dfs(0, map);

        System.out.print(answer);
    }

    static void dfs(int index, int[][] map) {

        if (index == 5) {
            return;
        }

        move(map);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer = Math.max(answer, map[i][j]);
            }
        }

        for (int i = 0; i < 4; i++) {
            int[][] tmp = rotate(map);
            dfs(index + 1, tmp);
            map = tmp;
        }
    }

    static int[][] rotate(int[][] src) {

        int[][] dst = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dst[j][N - 1 - i] = src[i][j];
            }
        }

        return dst;
    }

    static void move(int[][] map) {

        for (int i = 0; i < N; i++) {
            tilt(map[i]);
            add(map[i]);
        }
    }

    static void tilt(int[] arr) {

        int blankPos = -1;

        for (int i = 0; i < N; i++) {
            if (arr[i] == 0) {
                blankPos = i;
                break;
            }
        }

        if (blankPos == -1) {
            return;
        }

        for (int i = 0; i < N; i++) {
            if (arr[i] > 0 && i > blankPos) {
                arr[blankPos] = arr[i];
                arr[i] = 0;
                blankPos++;
            }
        }
    }

    static void add(int[] arr) {

        int blankPos = -1, pos = 0;

        while (true) {

            if (pos >= N || arr[pos] == 0) {
                break;
            }

            if (pos == N - 1 || (arr[pos] != arr[pos + 1])) {
                if (blankPos > -1) {
                    arr[blankPos] = arr[pos];
                    arr[pos] = 0;
                    blankPos++;
                }
                pos++;
                continue;
            }

            if (arr[pos] == arr[pos + 1]) {
                if (blankPos == -1) {
                    blankPos = 0;
                }
                int val = arr[pos] * 2;
                arr[pos] = arr[pos + 1] = 0;
                arr[blankPos] = val;
                blankPos++;
                pos += 2;
                continue;
            }
        }
    }

    static void print(int[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            } System.out.println();
        } System.out.println();
    }
}
