import java.util.*;

public class Test {
    static int N = 4;
    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int[][] visited = new int[4][4];
    static int[][] map = {
            {1, 1, 1, 1},
            {1, 2, 1, 1},
            {1, 2, 2, 2},
            {2, 1, 1, 2}
    };

    public static void main(String[] args) {
        int sum = dfs(0, 0, map[0][0]);
        System.out.println(sum);
        printMap();
    }

    static int dfs(int r, int c, int val) {
        visited[r][c] = 1;
        int sum = 0;

        for (int i = 0; i < 4; i++) {
            int nr = r + delta[i][0];
            int nc = c + delta[i][1];
            if ((nr >= 0 && nc >= 0 && nr < N && nc < N) && visited[nr][nc] == 0) {
                if (map[nr][nc] == val) {
                    sum += dfs(nr, nc, val);
                }
            }
        }

        return val + sum;
    }

    static void printMap() {
        for (int i = 0 ; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(visited[i][j] + " ");
            } System.out.println();
        }
    }
}
