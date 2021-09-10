// clear 1
import java.io.*;
import java.util.*;
import java.lang.*;

public class Q14891 {
    static int[][] wheels = new int[5][8];
    static int[] check = new int[5];
    static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 1; i <= 4; i++) {
            String s = br.readLine();
            for (int j = 0; j < 8; j++) {
                wheels[i][j] = s.charAt(j) - '0';
            }
        }

        k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            for (int j = 1; j <= 4; j++) {
                check[j] = 0;
            }
            solve(num, dir);
        }

        int answer = 0;
        for (int i = 1; i <= 4; i++) {
            answer += Math.pow(2, i - 1) * wheels[i][0];
        }
        System.out.print(answer);
    }

    static void solve(int num, int dir) {
        // 바깥 재귀의 바퀴들을 건드리지 못하도록 미리 회전 처리 체크
        check[num] = 1;

        // 이전 바퀴 회전
        if (num > 1 && check[num - 1] == 0) {
            if (wheels[num][6] != wheels[num - 1][2]) {
                solve(num - 1, -dir);
            }
        }

        // 다음 바퀴 회전
        if (num < 4 && check[num + 1] == 0) {
            if (wheels[num][2] != wheels[num + 1][6]) {
                solve(num + 1, -dir);
            }
        }

        // 바퀴 회전
        int tmp = dir == 1 ? wheels[num][7] : wheels[num][0];
        if (dir == 1) {
            for (int i = 6; i >= 0; i--) {
               wheels[num][i + 1] = wheels[num][i];
            }
            wheels[num][0] = tmp;
        } else {
            for (int i = 1; i <= 7; i++) {
                wheels[num][i - 1] = wheels[num][i];
            }
            wheels[num][7] = tmp;
        }

        /*
        int arg = dir == 1 ? 1 : 7;
        int[] tmp_arr = new int[8];
        for (int i = 0; i < 8; i++) {
            tmp_arr[(i + arg) % 8] = wheels[num][i];
        }
        for (int i = 0; i < 8; i++) {
            wheels[num][i] = tmp_arr[i];
        }
         */
    }
}
