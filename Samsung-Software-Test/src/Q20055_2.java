import java.io.*;
import java.util.*;

public class Q20055_2 {

    static int N, K, answer;
    static int[] belt = new int[200];
    static int[] robot = new int[200];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 2 * N; i++) {
            belt[i] = Integer.parseInt(st.nextToken());
        }

        while (true) {

            answer++;

            // 벨트 회전
            int tmp = belt[2 * N - 1];
            for (int i = 2 * N - 1; i > 0; i--) {
                belt[i] = belt[i - 1];
            }
            belt[0] = tmp;

            // 로봇 회전
            for (int i = N - 1; i > 0; i--) {
                robot[i] = robot[i - 1];
            }
            robot[0] = robot[N - 1] = 0;

            // 로봇 이동
            for (int i = N - 2; i >= 0; i--) {
                if (robot[i] == 0) {
                    continue;
                }
                if (robot[i + 1] == 0 && belt[i + 1] >= 1) {
                    if (i + 1 < N - 1) {
                        robot[i + 1] = 1;
                    }
                    robot[i] = 0;
                    belt[i + 1]--;
                }
            }

            if (belt[0] > 0) {
                robot[0] = 1;
                belt[0]--;
            }

            int cnt = 0;
            for (int i = 0; i < 2 * N; i++) {
                if (belt[i] == 0) {
                    cnt++;
                }
            }

            if (cnt >= K) {
                break;
            }
        }

        System.out.print(answer);
    }
}
