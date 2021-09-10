import java.io.*;
import java.util.*;

public class Q20055 {
    static int N, K;
    static Board[] belt = new Board[200];
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N * 2; i++) {
            int a = Integer.parseInt(st.nextToken());
            belt[i] = new Board(a, 0);
        }

        int stage = 0;
        while (cnt < K) {
            rotate();
            move();
            addRobot();
            stage++;
        }

        System.out.print(stage);
    }

    static void rotate() {
        // 벨트, 로봇 회전
        Board tmp = belt[N * 2 - 1];
        for (int i = N * 2 - 2; i >= 0; i--) {
            belt[i + 1] = belt[i];

            // 로봇이 내리는 위치에 도달한 경우 즉시 내림
            if (i + 1 == N - 1) {
                belt[i + 1].robot = 0;
            }
        } belt[0] = tmp;
    }

    static void move() {
        for (int i = N - 1; i >= 0; i--) {
            if (belt[i].robot == 1) {
                // 이동하려는 칸에 로봇이 있거나 내구도가 부족한 경우
                if (belt[i + 1].robot == 1 || belt[i + 1].dur < 1) {
                    continue;
                }
                belt[i].robot = 0;
                belt[i + 1].dur--;
                if (belt[i + 1].dur == 0) {
                    cnt++;
                }

                // 로봇이 내리는 위치에 도달한 것이 아닌 경우
                if (i + 1 != N - 1) {
                    belt[i + 1].robot = 1;
                }
            }
        }
    }

    static void addRobot() {
        // 올리는 위치에 로봇을 올림
        if (belt[0].robot == 0 && belt[0].dur > 0) {
            belt[0].robot = 1;
            belt[0].dur--;
            if (belt[0].dur == 0) {
                cnt++;
            }
        }
    }

    static class Board {
        int dur, robot;
        public Board(int dur, int robot) {
            this.dur = dur;
            this.robot = robot;
        }
    }
}
