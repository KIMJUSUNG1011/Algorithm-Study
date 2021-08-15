import java.io.*;
import java.util.*;

public class Q20061 {
    static int N;
    static int score = 0;
    static int[][] green = new int[6][4];
    static int[][] blue = new int[4][6];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            green_process(t, y);
            blue_process(t, x);
        }

        int cnt = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (green[i][j] == 1) {
                    cnt++;
                }
                if (blue[j][i] == 1) {
                    cnt++;
                }
            }
        }

        System.out.println(score);
        System.out.print(cnt);
    }

    static void blue_process(int t, int x) {

        if (t == 1 || t == 2) {
            int pos = 0;
            while (pos <= 5 && blue[x][pos] != 1) {
                pos++;
            }
            blue[x][pos - 1] = 1;
            if (t == 2) {
                blue[x][pos - 2] = 1;
            }
        }

        if (t == 3) {
            int pos1 = 0, pos2 = 0;
            while (pos1 <= 5 && blue[x][pos1] != 1) {
                pos1++;
            }
            while (pos2 <= 5 && blue[x + 1][pos2] != 1) {
                pos2++;
            }
            int pos = Math.min(pos1, pos2) - 1;
            blue[x][pos] = 1;
            blue[x + 1][pos] = 1;
        }

        int i = 5;
        while (i >= 0) {
            boolean isRemove = true;
            for (int j = 0; j < 4; j++) {
                if (blue[j][i] == 0) {
                    isRemove = false;
                    break;
                }
            }

            // i 열 삭제
            if (isRemove) {
                score++;
                // blue 배열 재설정
                for (int j = 0; j < 4; j++) {
                    for (int k = i - 1; k >= 0; k--) {
                        blue[j][k + 1] = blue[j][k];
                        blue[j][k] = 0;
                    }
                }
            } else {
                i--;
            }
        }

        // 0, 1 번 열에 블록이 있는 경우
        int cnt = 0;
        for (i = 0; i <= 1; i++) {
            for (int j = 0; j < 4; j++) {
                if (blue[j][i] == 1) {
                    cnt++;
                    break;
                }
            }
        }

        if (cnt > 0) {
            for (i = 5 - cnt; i >= 0; i--) {
                for (int j = 0; j < 4; j++) {
                    blue[j][i + cnt] = blue[j][i];
                    blue[j][i] = 0;
                }
            }
        }
    }

    static void green_process(int t, int y) {

        if (t == 1 || t == 3) {
            int pos = 0;
            while (pos <= 5 && green[pos][y] != 1) {
                pos++;
            }
            green[pos - 1][y] = 1;
            if (t == 3) {
                green[pos - 2][y] = 1;
            }
        }

        if (t == 2) {
            int pos1 = 0, pos2 = 0;
            while (pos1 <= 5 && green[pos1][y] != 1) {
                pos1++;
            }
            while (pos2 <= 5 && green[pos2][y + 1] != 1) {
                pos2++;
            }
            int pos = Math.min(pos1, pos2) - 1;
            green[pos][y] = 1;
            green[pos][y + 1] = 1;
        }

        int i = 5;
        while (i >= 0) {
            boolean isRemove = true;
            for (int j = 0; j < 4; j++) {
               if (green[i][j] == 0) {
                   isRemove = false;
                   break;
               }
            }

            // i 행 삭제
            if (isRemove) {
                score++;
                // green 배열 재설정
                for (int j = 0; j < 4; j++) {
                    for (int k = i - 1; k >= 0; k--) {
                        green[k + 1][j] = green[k][j];
                        green[k][j] = 0;
                    }
                }
            } else {
                i--;
            }
        }

        // 0, 1 번 행에 블록이 있는 경우
        int cnt = 0;
        for (i = 0; i <= 1; i++) {
            for (int j = 0; j < 4; j++) {
                if (green[i][j] == 1) {
                    cnt++;
                    break;
                }
            }
        }

        if (cnt > 0) {
            for (i = 5 - cnt; i >= 0; i--) {
                for (int j = 0; j < 4; j++) {
                    green[i + cnt][j] = green[i][j];
                    green[i][j] = 0;
                }
            }
        }
    }

    static void print(int[][] arr) {
        for (int[] ints : arr) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            } System.out.println();
        } System.out.println();
    }
}

/*
9
2 1 0
2 1 0
2 1 0
2 1 0
2 1 0
3 0 2
3 0 2
3 0 3
3 0 3

답 :
4
8
 */
