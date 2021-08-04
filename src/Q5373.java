import java.io.*;
import java.util.*;

public class Q5373 {
    static int n;
    static char[][] cube = new char[6][9];
    static int[] conv = new int[256];
    static StringBuilder sb = new StringBuilder();
    static int[][] info = {
            // {위로 맞닿는 3개, 왼쪽으로 맞닿는 3개, 아래로 맞닿는 3개, 오른쪽으로 맞닿는 3개}
            {42, 43, 44, 26, 23, 20, 47, 46, 45, 27, 30, 33},
            {38, 37, 36, 35, 32, 29, 51, 52, 53, 18, 21, 24},
            {36, 39, 42, 17, 14, 11, 45, 48, 51, 0, 3, 6},
            {44, 41, 38, 8, 5, 2, 53, 50, 47, 9, 12, 15},
            {11, 10, 9, 20, 19, 18, 2, 1, 0, 29, 28, 27},
            {6, 7, 8, 24, 25, 26, 15, 16, 17, 33, 34, 35}
    };

    static void rotate(char face, char dir) {
        int nFace = conv[face];
        if (dir == '+') {
            // 주변 회전
            char[] tmp = new char[3];
            for (int i = 0; i <= 2; i++) {
                tmp[i] = cube[info[nFace][i] / 9][info[nFace][i] % 9];
            }
            for (int i = 1; i <= 3; i++) {
                for (int j = 0; j <= 2; j++) {
                    int n1 = info[nFace][3 * (i - 1) + j];
                    int n2 = info[nFace][3 * i + j];
                    cube[n1 / 9][n1 % 9] = cube[n2 / 9][n2 % 9];
                }
            }
            for (int i = 0; i <= 2; i++) {
                int n1 = info[nFace][9 + i];
                cube[n1 / 9][n1 % 9] = tmp[i];
            }

            // 자신을 시계방향으로 회전
            char[][] tmp_face = new char[3][3];
            for (int i = 0; i < 9; i++) {
                int r = i / 3, c = i % 3;
                tmp_face[c][2 - r] = cube[nFace][i];
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    cube[nFace][i * 3 + j] = tmp_face[i][j];
                }
            }
        } else {
            // 주변 회전
            char[] tmp = new char[3];
            for (int i = 0; i <= 2; i++) {
                tmp[i] = cube[info[nFace][i + 9] / 9][info[nFace][i + 9] % 9];
            }
            for (int i = 2; i >= 0; i--) {
                for (int j = 0; j <= 2; j++) {
                    int n1 = info[nFace][3 * (i + 1) + j];
                    int n2 = info[nFace][3 * i + j];
                    cube[n1 / 9][n1 % 9] = cube[n2 / 9][n2 % 9];
                }
            }
            for (int i = 0; i <= 2; i++) {
                int n1 = info[nFace][i];
                cube[n1 / 9][n1 % 9] = tmp[i];
            }

            // 자신을 반시계방향으로 회전
            char[][] tmp_face = new char[3][3];
            for (int i = 0; i < 9; i++) {
                int r = i / 3, c = i % 3;
                tmp_face[2 - c][r] = cube[nFace][i];
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    cube[nFace][i * 3 + j] = tmp_face[i][j];
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            // 큐브 초기화
            for (int i = 0; i < 9; i++) {
                cube[0][i] = 'r';
                cube[1][i] = 'o';
                cube[2][i] = 'g';
                cube[3][i] = 'b';
                cube[4][i] = 'w';
                cube[5][i] = 'y';
            }

            // 매핑 테이블 초기화
            conv['F'] = 0;
            conv['B'] = 1;
            conv['L'] = 2;
            conv['R'] = 3;
            conv['U'] = 4;
            conv['D'] = 5;

            int cnt = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < cnt; i++) {
                String s = st.nextToken();
                char face = s.charAt(0);
                char dir = s.charAt(1);
                rotate(face, dir);
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    sb.append(cube[4][i * 3 + j]);
                } sb.append("\n");
            }
        }

        System.out.print(sb);
    }
}
