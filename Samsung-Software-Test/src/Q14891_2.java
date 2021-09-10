import java.io.*;
import java.util.*;

public class Q14891_2 {
    static int[][] wheels = new int[4][8];
    static boolean[] visited = new boolean[4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 4; i++) {
            String s = br.readLine();
            for (int j = 0; j < 8; j++) {
               wheels[i][j] = s.charAt(j) - '0';
            }
        }

        int K = Integer.parseInt(br.readLine());
        while (K-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());
            go(num, dir);
            for (int i = 0; i < 4; i++) {
                visited[i] = false;
            }
        }

        int answer = 0;
        for (int i = 0; i < 4; i++) {
            answer += wheels[i][0] * Math.pow(2, i);
        }

        System.out.print(answer);
    }

    static void go(int num, int dir) {
        if (visited[num]) {
            return;
        }
        visited[num] = true;

        if (num > 0 && (wheels[num][6] != wheels[num - 1][2])) {
            go(num - 1, -dir);
        }

        if (num < 3 && (wheels[num][2] != wheels[num + 1][6])) {
            go(num + 1, -dir);
        }

        if (dir == 1) {
            int tmp = wheels[num][7];
            for (int i = 6; i >= 0; i--) {
                wheels[num][i + 1] = wheels[num][i];
            }
            wheels[num][0] = tmp;
        } else {
            int tmp = wheels[num][0];
            for (int i = 1; i <= 7; i++) {
                wheels[num][i - 1] = wheels[num][i];
            }
            wheels[num][7] = tmp;
        }
    }
}
