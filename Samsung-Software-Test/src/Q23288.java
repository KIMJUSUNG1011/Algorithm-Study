import java.io.*;
import java.util.*;

public class Q23288 {

    static int N, M, K;
    static int[][] map = new int[20][20];
    static int[] dice = {2, 1, 4, 3, 5, 6};
    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    // group[i][j] = k
    // map[i][j] 의 그룹은 k
    static int[][] group = new int[20][20];

    // nGroup[i] = j
    // i 번 그룹의 개수는 j 개
    static int[] nGroup = new int[401];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num = 1, dir = 1, answer = 0;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (group[i][j] == 0) {
                    go(i, j, num++);
                }
            }
        }

        int r = 0, c = 0;
        for (int i = 0; i < K; i++) {
            int nr = r + delta[dir][0];
            int nc = c + delta[dir][1];

            if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
                dir = (dir + 2) % 4;
                nr = r + delta[dir][0];
                nc = c + delta[dir][1];
            }

            // 주사위 전개도 변경
            roll(dir);

            // 이동 방향 변경
            if (dice[5] > map[nr][nc]) {
                dir = (dir + 1) % 4;
            } else if (dice[5] < map[nr][nc]) {
                dir = (dir + 3) % 4;
            }

            num = group[nr][nc];
            answer += map[nr][nc] * nGroup[num];

            r = nr;
            c = nc;
        }

        System.out.print(answer);
    }

    // 그룹 번호 매기기
    public static void go(int r, int c, int num) {

        Queue<int[]> q = new LinkedList<>();
        int cnt = 0;

        q.add(new int[]{r, c});
        group[r][c] = num;

        while (!q.isEmpty()) {
            int[] p = q.poll();
            cnt++;
            for (int i = 0; i < 4; i++) {
                int nr = p[0] + delta[i][0];
                int nc = p[1] + delta[i][1];
                if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
                    continue;
                }
                if (group[nr][nc] != num && map[nr][nc] == map[r][c]) {
                    q.add(new int[]{nr, nc});
                    group[nr][nc] = num;
                }
            }
        }

        // 그룹의 구성원 개수 저장
        nGroup[num] = cnt;
    }

    public static void roll(int dir) {

        int[] tmp = new int[6];

        // 북쪽으로 이동
        if (dir == 0) {
            tmp[0] = dice[1];
            tmp[1] = dice[4];
            tmp[2] = dice[2];
            tmp[3] = dice[3];
            tmp[4] = dice[5];
            tmp[5] = dice[0];
        } else if (dir == 1) {
            tmp[0] = dice[0];
            tmp[1] = dice[2];
            tmp[2] = dice[5];
            tmp[3] = dice[1];
            tmp[4] = dice[4];
            tmp[5] = dice[3];
        } else if (dir == 2) {
            tmp[0] = dice[5];
            tmp[1] = dice[0];
            tmp[2] = dice[2];
            tmp[3] = dice[3];
            tmp[4] = dice[1];
            tmp[5] = dice[4];
        } else {
            tmp[0] = dice[0];
            tmp[1] = dice[3];
            tmp[2] = dice[1];
            tmp[3] = dice[5];
            tmp[4] = dice[4];
            tmp[5] = dice[2];
        }

        dice = tmp;
    }
}

// 1. 주사위가 이동할 때마다 전개도가 어떻게 변하는가?
// 2. bfs 로 미리 모든 칸의 점수를 저장
