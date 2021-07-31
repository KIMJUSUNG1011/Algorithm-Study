import java.io.*;
import java.util.*;

public class Q17143 {
    static int R, C, M;
    static int[][] max;
    static PriorityQueue<Shark> q = new PriorityQueue<>(new Comparator<Shark>() {
        @Override
        public int compare(Shark o1, Shark o2) {
            if (o1.c != o2.c) {
                return o1.c - o2.c;
            } else if (o1.r != o2.r) {
                return o1.r - o2.r;
            } else {
                return o1.z - o2.z;
            }
        }
    });

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
            q.add(new Shark(r, c, s, d, z));
        }

        /*
        while (!q.isEmpty()) {
            Shark shark = q.poll();
            System.out.print(shark + " / ");
            move_shark(shark);
            System.out.println(shark);
        }
       */

        int answer = 0;
        for (int i = 1; i <= C; i++) {
            max = new int[101][101];
            boolean flag = false;
            int len = q.size();
            for (int j = 0; j < len; j++) {
                Shark shark = q.poll();
                if (shark == null) {
                    break;
                }
                if (!flag) {
                    if (shark.c == i) {
                        System.out.print(i + ":");
                        System.out.println(shark);
                        answer += shark.z;
                        flag = true;
                        continue;
                    }
                }

                move_shark(shark);

                if (max[shark.r][shark.c] < shark.z) {
                    System.out.println(shark);
                    max[shark.r][shark.c] = shark.z;
                    q.add(shark);
                }
            }
            len = q.size();
            for (int j = 0; j < len; j++) {
                Shark shark = q.poll();
                if (shark == null) {
                    break;
                }
                if (max[shark.r][shark.c] == shark.z) {
                    // System.out.println(shark);
                    q.add(shark);
                }
            }
        }

        System.out.print(answer);
    }

    static void move_shark(Shark shark) {
        boolean aa = false;

        if (shark.r == 1 && shark.c == 5) {
            aa = true;
        }

        Shark tmp = new Shark(shark.r, shark.c, shark.s, shark.d, shark.z);

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

        if (shark.r == 1 && shark.c == 3) {
            if (aa) {
                // System.out.println(tmp);
                // System.out.println(shark);
            }
        }
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
