import java.io.*;
import java.util.*;

public class Test {
    static int N, M;
    static int[][] map = new int[20][20];
    static int[][] group = new int[20][20];
    static boolean[][] visited = new boolean[20][20];
    static int[] gCnt = new int[400];
    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int nGroup = 0, answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 2 && !visited[i][j]) {
                    setGroup(i, j, ++nGroup);
                }
            }
        }

        go(0, 0);
        System.out.print(answer);
    }

    // 상대 돌을 그룹화
    static void setGroup(int i, int j, int gNum) {

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        visited[i][j] = true;

        while (!q.isEmpty()) {
            int[] p = q.poll();
            group[p[0]][p[1]] = gNum;
            gCnt[gNum]++;
            for (int k = 0; k < 4; k++) {
                int nr = p[0] + delta[k][0];
                int nc = p[1] + delta[k][1];
                if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
                    continue;
                }
                if (map[nr][nc] == 0) {
                    map[nr][nc] = -1;
                }
                if (map[nr][nc] == 2 && !visited[nr][nc]) {
                    q.add(new int[]{nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }
    }

    // 모든 경우의 수 체크
    static void go(int index, int cnt) {

        if (cnt == 2) {

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    System.out.print(map[i][j] + " ");
                } System.out.println();
            } System.out.println();

            answer = Math.max(answer, count());
            return;
        }

        if (index == N * M) {
            return;
        }

        int r = index / M;
        int c = index % M;

        if (map[r][c] == -1) {
            map[r][c] = 1;
            go(index + 1, cnt + 1);
            map[r][c] = -1;
        }

        go(index + 1, cnt);
    }

    // 죽은 상대 돌의 개수를 카운트
    static int count() {

        // 살아있는 그룹인지 체크
        boolean[] check = new boolean[nGroup + 1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 2 || !check[group[i][j]]) {
                    for (int k = 0; k < 4; k++) {
                        int nr = i + delta[k][0];
                        int nc = j + delta[k][1];
                        if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
                            continue;
                        }
                        // 살아있는 그룹인 경우
                        if (map[nr][nc] == 0 || map[nr][nc] == -1) {
                            check[group[i][j]] = true;
                            break;
                        }
                    }
                }
            }
        }

        int res = 0;
        for (int i = 1; i <= nGroup; i++) {
            if (!check[i]) {
                res += gCnt[i];
            }
        }

        return res;
    }
}