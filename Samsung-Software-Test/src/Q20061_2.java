import java.io.*;
import java.util.*;

public class Q20061_2 {

    static int N, score = 0, cnt = 0;
    static int[][][] map = new int[2][6][4];
    static int[][] height = {{5, 5, 5, 5}, {5, 5, 5, 5}};
    static int[] convert = {0, 1, 3, 2};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int t = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            // 초록색 보드
            move(t, c, 0);

            // 파란색 보드
            move(convert[t], 3 - r, 1);
        }

        for (int i = 0; i < 6; i++) {
            for (int j = 0 ; j < 4; j++) {
                if (map[0][i][j] == 1) {
                    cnt++;
                }
                if (map[1][i][j] == 1) {
                    cnt++;
                }
            }
        }

        System.out.print(score + "\n" + cnt);
    }

    static void move(int t, int firstCol, int color) {

        int inputRow;

        if (color == 1 && t == 2) {
            firstCol--;
        }

        if (t == 1) {
            inputRow = height[color][firstCol];
            map[color][inputRow][firstCol] = 1;
            height[color][firstCol]--;
        }

        if (t == 2) {
            inputRow = Math.min(height[color][firstCol], height[color][firstCol + 1]);
            map[color][inputRow][firstCol] = map[color][inputRow][firstCol + 1] = 1;
            height[color][firstCol] = height[color][firstCol + 1] = inputRow - 1;
        }

        if (t == 3) {
            inputRow = height[color][firstCol];
            map[color][inputRow][firstCol] = map[color][inputRow - 1][firstCol] = 1;
            height[color][firstCol] -= 2;
        }

        delete(color);
    }

    static void delete(int color) {

        int newRow = 5, minRow = 5;
        int[][][] tmpMap = new int[2][6][4];
        int[] deleteRow = {1, 1, 1, 1, 1, 1};

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (map[color][i][j] == 0) {
                    deleteRow[i] = 0;
                    break;
                }
            }
        }

        for (int i = 5; i >= 0; i--) {
            if (deleteRow[i] == 0) {
                for (int j = 0; j < 4; j++) {
                    tmpMap[color][newRow][j] = map[color][i][j];
                }
                newRow--;
            } else {
                score++;
            }
        }

        for (int i = 0; i < 4; i++) {
            height[color][i] = 5;
            for (int j = 0; j < 6; j++) {
                if (tmpMap[color][j][i] == 1) {
                    height[color][i] = j - 1;
                    break;
                }
            }
            minRow = Math.min(minRow, height[color][i]);
        }

        // 연한 부분 삭제
        if (minRow <= 0) {

            int nDelete = (minRow == -1 ? 2 : 1);

            for (int i = 5 - nDelete; i >= 0; i--) {
                for (int j = 0; j < 4; j++) {
                    tmpMap[color][i + nDelete][j] = tmpMap[color][i][j];
                    tmpMap[color][i][j] = 0;
                }
            }

            for (int i = 0; i < 4; i++) {
                height[color][i] += nDelete;
                if (height[color][i] > 5) {
                    height[color][i] = 5;
                }
            }
        }

        map[color] = tmpMap[color];
    }

    static void print(int[][] map, int[] row) {
        for (int i = 0; i < 4; i++) {
            System.out.print(row[i] + " ");
        } System.out.println();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(map[i][j] + " ");
            } System.out.println();
        } System.out.println();
    }
}
