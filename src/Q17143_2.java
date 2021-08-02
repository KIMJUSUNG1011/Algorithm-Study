import java.io.*;
import java.util.*;

public class Q17143_2 {
    static int R, C, M;
    static Shark[][] map, tmp_map;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new Shark[R + 1][C + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            map[r][c] = new Shark(r, c, s, d, z);
        }

        long answer = 0;
        for (int i = 1; i <= C; i++) {
            // 지면과 가장 가까운 상어 잡기
            for (int j = 1; j <= R; j++) {
                if (map[j][i] != null) {
                    answer += map[j][i].z;
                    map[j][i] = null;
                    break;
                }
            }

            // 상어 이동
            tmp_map = new Shark[R + 1][C + 1];
            for (int j = 1; j <= R; j++) {
                for (int k = 1; k <= C; k++) {
                    if (map[j][k] != null) {
                        move_shark(map[j][k]);
                        Shark cur_shark = map[j][k];
                        Shark prev_shark = tmp_map[cur_shark.r][cur_shark.c];

                        // 이동한 위치에 이미 상어가 있는 경우 큰 상어가 작은 상어를 잡아먹음
                        if (prev_shark == null || prev_shark.z < cur_shark.z) {
                            tmp_map[cur_shark.r][cur_shark.c] = cur_shark;
                        }
                    }
                }
            }

            map = tmp_map;
        }

        System.out.print(answer);
    }

    static void move_shark(Shark shark) {
        int dir = shark.d;
        int speed = shark.s;
        int start = dir == 1 || dir == 2 ? shark.r : shark.c;
        int len = dir == 1 || dir == 2 ? R : C;

        // 상어가 한 바퀴를 돌아서 다시 자기자리로 돌아오기 위해 이동하는 횟수
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
