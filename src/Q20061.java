import java.io.*;
import java.util.*;

public class Q20061 {
    static int N;
    static int[][] green = new int[6][4];
    static int[] green_h = {5, 5, 5, 5};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            green_process(t, x, y);
        }
    }

    static void green_process(int t, int x, int y) {

        if (t == 1) {
            int h = green_h[y];
            green[5 - h][y] = 1;
            green_h[y] = h + 1;
        }

        if (t == 2) {
            int h1 = green_h[y], h2 = green_h[y + 1];
            int h = Math.max(h1, h2);
            green[5 - h][y] = 1;
            green[5 - h][y + 1] = 1;
            green_h[y] = h + 1;
            green_h[y + 1] = h + 1;
        }

        if (t == 3) {
            int h = green_h[y];
            green[5 - h][y] = 1;
            green[(5 - h) - 1][y] = 1;
            green_h[y] = h + 2;
        }

        for (int i = 5; i >= 0; i--) {
            boolean isRemove = true;
            for (int j = 0; j < 4; j++) {
               if (green[i][j] == 0) {
                   isRemove = false;
                   break;
               }
            }

            // i 행 삭제
            if (isRemove) {
                for (int j = 0; j < 4; j++) {
                    green[i][j] = 0;
                    int h = green_h[j];

                    // 가장 위의 행이 삭제되는 경우
                    int k = 6 - h + 1;
                    while (k <= 5 && green[k][j] != 1) {
                        k++;
                    }
                    green_h[j] = 6 - k;

                    for (k = i - 1; k >= 6 - h; k--) {
                        green[k + 1][j] = green[k][j];
                    }
                }
            }
        }
    }
}
