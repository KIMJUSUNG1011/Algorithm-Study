import java.io.*;
import java.util.*;

public class Q5373_2 {
    static int t, n;
    static String faces = "UDFBLR";
    static String colors = "wyrogb";
    static char[][][] cube = new char[6][3][3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            // 케이스마다 큐브 초기화
            for (int i = 0; i < 6; i++) {
                char color = colors.charAt(i);
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        cube[i][j][k] = color;
                    }
                }
            }
            n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            while (n-- > 0) {
                String s = st.nextToken();
                char face = s.charAt(0);
                char dir = s.charAt(1);
                rotate(face, dir);
            }
        }
    }

    static void rotate(char face, char dir) {
        int faceNum = faces.indexOf(face);
        char[][] tmp = new char[3][3];
        if (dir == '+') {
            // 해당 면을 시계방향으로 회전하여 임시 배열에 저장
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    tmp[j][2 - i] = cube[faceNum][i][j];
                }
            }
            // 하드코딩
            switch (face) {
                case 'U' :
                case 'D' :
                case 'F' :
                case 'B' :
                case 'L' :
                case 'R' :
            }
        } else {
            // 해당 면을 반시계방향으로 회전하여 임시 배열에 저장
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    tmp[2 - j][i] = cube[faceNum][i][j];
                }
            }
            // 하드코딩
            switch (face) {
                case 'U' :
                case 'D' :
                case 'F' :
                case 'B' :
                case 'L' :
                case 'R' :
            }
        }

        // 임시 배열을 원본에 복사
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                cube[faceNum][i][j] = tmp[i][j];
            }
        }
    }
}
