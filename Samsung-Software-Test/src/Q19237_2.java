import java.io.*;
import java.util.*;

public class Q19237_2 {

    static int N, M, K, time;
    static int[][][] map = new int[20][20][2];
    static int[][][] priority = new int[401][4][4];
    static Shark[] sharks = new Shark[401];
    static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num > 0) {
                    sharks[num] = new Shark(i, j, num, -1);
                }
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= M; i++) {
            sharks[i].dir = Integer.parseInt(st.nextToken()) - 1;
        }

        for (int i = 1; i <= M; i++) {
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int k = 0; k < 4; k++) {
                    priority[i][j][k] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
        }

        for (time = 1; time <= 1000; time++) {

            int cnt = 0;

            // 모든 상어가 냄새를 뿌림
            for (Shark s : sharks) {
                if (s != null) {
                    map[s.r][s.c][0] = s.num;
                    map[s.r][s.c][1] = K;
                }
            }

            // 상어 이동
            for (Shark s : sharks) {
                if (s != null) {
                    move(s);
                }
            }

            // 가장 작은 번호 남기고 제외
            for (Shark s : sharks) {
                if (s != null) {
                    if (map[s.r][s.c][0] == 0 || map[s.r][s.c][0] == s.num) {
                        map[s.r][s.c][0] = -1;
                        cnt++;
                    } else {
                        sharks[s.num] = null;
                    }
                }
            }

            // 냄새 카운트
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j][0] > 0) {
                        map[i][j][1]--;
                        if (map[i][j][1] == 0) {
                            map[i][j][0] = 0;
                        }
                    }
                }
            }

            if (cnt == 1) {
                break;
            }
        }

        if (time == 1001) {
            System.out.print(-1);
        } else {
            System.out.print(time);
        }
    }

    static void move(Shark s) {

        int[] noSmell = {-1, -1, -1};
        int[] mySmell = {-1, -1, -1};

        // 우선순위를 뒤쪽부터 체크하며 업데이트
        for (int i = 3; i >= 0; i--) {
            int dir = priority[s.num][s.dir][i];
            int nr = s.r + delta[dir][0];
            int nc = s.c + delta[dir][1];
            if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
                continue;
            }
            // 냄새가 없는 칸
            if (map[nr][nc][0] == 0) {
                noSmell[0] = nr;
                noSmell[1] = nc;
                noSmell[2] = dir;
            }
            // 자신의 냄새가 있는 칸
            if (map[nr][nc][0] == s.num) {
                mySmell[0] = nr;
                mySmell[1] = nc;
                mySmell[2] = dir;
            }
        }

        // 냄새가 없는 칸으로 이동
        if (noSmell[0] > -1) {
            s.r = noSmell[0];
            s.c = noSmell[1];
            s.dir = noSmell[2];
            return;
        }

        // 자신의 냄새가 있는 칸으로 이동
        s.r = mySmell[0];
        s.c = mySmell[1];
        s.dir = mySmell[2];
    }

    static class Shark {
        int r, c, num, dir;
        public Shark(int r, int c, int num, int dir) {
            this.r = r;
            this.c = c;
            this.num = num;
            this.dir = dir;
        }
        public String toString() {
            return r+","+c+":"+num+","+dir;
        }
    }
}
