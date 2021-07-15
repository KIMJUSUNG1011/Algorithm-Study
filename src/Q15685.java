import java.io.*;
import java.util.*;

public class Q15685 {
    static int n;
    static int[][] map = new int[111][111];
    static int[][] delta = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            // 커브가 그려질 방향을 미리 모두 설정
            int[] dirs = new int[1024];
            dirs[0] = d;
            for (int j = 0; j < g; j++) {
                // j 세대에서 j + 1 세대로 갈 때 추가되는 좌표의 수
                int cnt = (int) Math.pow(2, j);
                for (int k = 0; k < cnt; k++) {
                    dirs[(cnt * 2 - 1) - k] = (dirs[k] + 1) % 4;
                }
            }

            // 문제의 예시의 좌표를 배열에 적용하기 위해서는
            // x, y 가 바뀌어야함
            map[y][x] = 1;
            for (int j = 0; j < (int)Math.pow(2, g); j++) {
                y += delta[dirs[j]][0];
                x += delta[dirs[j]][1];
                map[y][x] = 1;
            }
        }

        // 좌표의 범위가 0 ~ 100 이므로 격자는 101 개가 있는 것임
        long answer = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] + map[i + 1][j] + map[i][j + 1] + map[i + 1][j + 1] == 4) {
                    answer++;
                }
            }
        }

        System.out.print(answer);
    }
}
