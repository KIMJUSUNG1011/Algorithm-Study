import java.io.*;
import java.util.*;

public class Q20061_2 {

    static int N;
    static int[][] greenMap = new int[6][4];
    static int[] greenRow = {5, 5, 5, 5};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int t = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            move(t, c);

            print(greenMap, greenRow);

            System.out.println();
        }
    }

    static void move(int t, int firstCol) {

        int inputRow;

        if (t == 1) {
            inputRow = greenRow[firstCol];
            greenMap[inputRow][firstCol] = 1;
            greenRow[firstCol]--;
        }

        if (t == 2) {
            inputRow = Math.min(greenRow[firstCol], greenRow[firstCol + 1]);
            greenMap[inputRow][firstCol] = greenMap[inputRow][firstCol + 1] = 1;
            greenRow[firstCol] = greenRow[firstCol + 1] = inputRow - 1;
        }

        if (t == 3) {
            inputRow = greenRow[firstCol];
            greenMap[inputRow][firstCol] = greenMap[inputRow - 1][firstCol] = 1;
            greenRow[firstCol] -= 2;
        }

        delete();
    }

    static void delete() {

        int newRow = 5, minRow = 5;
        int[][] tmpMap = new int[6][4];
        int[] deleteRow = {1, 1, 1, 1, 1, 1};

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (greenMap[i][j] == 0) {
                    deleteRow[i] = 0;
                    break;
                }
            }
        }

        for (int i = 5; i >= 0; i--) {
            if (deleteRow[i] == 0) {
                for (int j = 0; j < 4; j++) {
                    tmpMap[newRow][j] = greenMap[i][j];
                }
                newRow--;
            }
        }

        for (int i = 0; i < 4; i++) {
            greenRow[i] = 5;
            for (int j = 0; j < 6; j++) {
                if (tmpMap[j][i] == 1) {
                    greenRow[i] = j - 1;
                    break;
                }
            }
            minRow = Math.min(minRow, greenRow[i]);
        }

        // 연한 부분 삭제
        if (minRow <= 0) {

            int nDelete = (minRow == -1 ? 2 : 1);

            for (int i = 5 - nDelete; i >= 0; i--) {
                for (int j = 0; j < 4; j++) {
                    tmpMap[i + nDelete][j] = tmpMap[i][j];
                    tmpMap[i][j] = 0;
                }
            }

            for (int i = 0; i < 4; i++) {
                greenRow[i] += nDelete;
            }
        }

        greenMap = tmpMap;
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
