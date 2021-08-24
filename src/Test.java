import java.util.*;

public class Test {
    static int[][] tmp_delta = {{0 ,1}, {1, 0}};
    static int[][] visited = new int[20][20];
    static int N = 10;

    public static void main(String[] args) {
        int[][] tmp_board = new int[10][10];
        Queue<int[]> q = new LinkedList<>();
        visited[2][3] = 1;
        q.add(new int[]{2, 3});
        while (!q.isEmpty()) {
            int[] p = q.poll();
            for (int i = 0; i < 2; i++) {
                int nr = p[0] + tmp_delta[i][0];
                int nc = p[1] + tmp_delta[i][1];
                if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
                    continue;
                }
                if (visited[nr][nc] == 0) {
                    visited[nr][nc] = 1;
                    q.add(new int[]{nr, nc});
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(visited[i][j] + " ");
            } System.out.println();
        }
    }

}
