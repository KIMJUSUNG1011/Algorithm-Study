import java.io.*;
import java.util.*;

public class Q20055 {
    static int N, K;
    static int[] belt = new int[200];
    static boolean[] visited = new boolean[100];
    static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N * 2; i++) {
            belt[i] = Integer.parseInt(st.nextToken());
        }

        int stage = 1;
        while (true) {
            rotate();
            move();

            // 올리는 위치에 로봇을 올림
            if (!visited[0] && belt[0] > 0) {
                visited[0] = true;
                belt[0]--;
                q.add(0);
            }

            int cnt = 0;
            for (int i = 0; i < N * 2; i++) {
                if (belt[i] == 0) {
                    cnt++;
                }
            }
            if (cnt >= K) {
                break;
            }

            stage++;
        }

        System.out.print(stage);
    }

    static void rotate() {
        // 내구도 회전
        int tmp = belt[N * 2 - 1];
        for (int i = N * 2 - 2; i >= 0; i--) {
            belt[i + 1] = belt[i];
        } belt[0] = tmp;

        // 로봇 회전
        int size = q.size();
        for (int i = 0; i < size; i++) {
            int x = q.remove();
            visited[x] = false;
            visited[x + 1] = true;

            // 로봇이 내리는 위치에 도달한 경우 즉시 내림
            if (x + 1 == N - 1) {
                visited[x + 1] = false;
                continue;
            }

            q.add(x + 1);
        }
    }

    static void move() {
        int size = q.size();
        for (int i = 0; i < size; i++) {
            int x = q.remove();

            // 로봇을 한 칸 이동
            if (!visited[x + 1] && belt[x + 1] >= 1) {
                belt[x + 1]--;
                visited[x + 1] = true;
                visited[x] = false;
                x++;

                // 로봇이 내리는 위치에 도달한 경우 즉시 내림
                if (x == N - 1) {
                    visited[x] = false;
                    continue;
                }
            }
            q.add(x);
        }
    }
}
