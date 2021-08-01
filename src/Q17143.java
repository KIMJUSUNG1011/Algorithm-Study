import java.io.*;
import java.util.*;

public class Q17143 {
    static int R, C, M;
    static Map<Integer, Shark> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            // 좌표를 하나의 정수로 표현하여 맵의 키로 사용
            int rc = C * (r - 1) + c;
            map.put(rc, new Shark(r, c, s, d, z));
        }

        long answer = 0;
        Map<Integer, Shark> tmp_map = new HashMap<>();
        for (int i = 1; i <= C; i++) {
            boolean flag = false;
            for (Integer rc : map.keySet()) {
                Shark shark = map.get(rc);

                // 지면에서 가장 가까운 상어를 잡음
                if (!flag && (rc - i) % C == 0) {
                    answer += shark.z;
                    flag = true;
                } else {
                    // 상어들을 이동시킴
                    move_shark(shark);

                    // 가장 큰 상어만 남김
                    int newRC = C * (shark.r - 1) + shark.c;
                    int z = tmp_map.get(newRC) == null ? 0 : tmp_map.get(newRC).z;
                    if (z < shark.z) {
                        tmp_map.put(newRC, shark);
                    }
                }
            }
            map.clear();
            map.putAll(tmp_map);
            tmp_map.clear();
        }

        System.out.print(answer);
    }

    static void move_shark(Shark shark) {
        int dir = shark.d;
        int speed = shark.s;
        int start = dir == 1 || dir == 2 ? shark.r : shark.c;
        int len = dir == 1 || dir == 2 ? R : C;

        // 상어가 다시 자기자리로 돌아오기 위해 이동하는 횟수
        int oneCycle = (len - 1) * 2;

        // 상어가 실질적으로 이동하는 횟수
        int moveCnt = speed % oneCycle;

        int left = start - 1;
        int right = len - start;
        int all = left + right;
        int cond = dir == 1 || dir == 4 ? left : right;
        int next, x;

        if (moveCnt >= 0 && moveCnt <= cond) {
            next = dir == 1 || dir == 4 ? start - moveCnt : start + moveCnt;
        } else if (moveCnt > cond && moveCnt <= cond + all) {
            x = moveCnt - cond;
            next = dir == 1 || dir == 4 ? 1 + x : len - x;
            dir += dir == 1 || dir == 3 ? 1 : -1;
        } else {
            x = moveCnt - (cond + all);
            next = dir == 1 || dir == 4 ? len - x : 1 + x;
        }

        if (dir == 1 || dir == 2) {
            shark.r = next;
        } else {
            shark.c = next;
        }

        shark.d = dir;
    }

    static class Shark {
        int r, c, s, d, z;
        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
        public String toString() {
            return r+","+c+","+s+","+d+","+z;
        }
    }
}
