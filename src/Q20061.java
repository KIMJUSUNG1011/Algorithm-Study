import java.io.*;
import java.util.*;

public class Q20061 {
    static int N;
    static int[][] green = new int[6][4];
    static int[] green_h = {0, 0, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            green_process(t, x, y);
            print(green);

            System.out.print("h : ");
            for (int h : green_h) {
                System.out.print(h + " ");
            } System.out.println();
            System.out.println();
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
                    int h = green_h[j];
                    green[i][j] = 0;
                    green_h[j] = 0;

                    // green 배열 재설정
                    for (int k = i - 1; k >= 6 - h; k--) {
                        green[k + 1][j] = green[k][j];
                        green[k][j] = 0;
                    }

                    // green_h 배열 재설정
                    for (int k = 0; k <= 5; k++) {
                        if (green[k][j] == 1) {
                            green_h[j] = 6 - k;
                            break;
                        }
                    }
                }
            }
        }

        // 0, 1 번 행에 블록이 있는 경우
        int cnt = 0;
        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j < 4; j++) {
                if (green[i][j] == 1) {
                    cnt++;
                    break;
                }
            }
        }

        if (cnt > 0) {
            for (int i = 5 - cnt; i >= 0; i--) {
                for (int j = 0; j < 4; j++) {
                    green[i + cnt][j] = green[i][j];
                    green[i][j] = 0;
                }
            }
        }
    }

    static void print(int[][] arr) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(arr[i][j]+ " ");
            } System.out.println();
        }
    }
}
