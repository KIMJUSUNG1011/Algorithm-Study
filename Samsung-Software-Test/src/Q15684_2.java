import java.io.*;
import java.util.*;

public class Q15684_2 {

    static int N, M, H, answer = 4;
    static int[][][] check = new int[32][12][12];
    static List<int[]> pairs = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            check[a][b][b + 1] = 1;
        }

        for (int i = 1; i <= H; i++) {
            for (int j = 1; j < N; j++) {
                if (check[i][j][j + 1] == 0) {
                    if (check[i][j - 1][j] == 0 && check[i][j + 1][j + 2] == 0) {
                        pairs.add(new int[]{i, j});
                    }
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            if (dfs(0, 0, i) == 0) {
                break;
            }
        }

        if (answer > 3) {
            System.out.print(-1);
        } else {
            System.out.print(answer);
        }
    }

    static int dfs(int index, int cnt, int size) {

        if (cnt == size) {
            if (go() == 0) {
                answer = Math.min(answer, cnt);
                return 0;
            }
            return 1;
        }

        if (index == pairs.size()) {
            return 1;
        }

        int r = pairs.get(index)[0];
        int c = pairs.get(index)[1];

        if (dfs(index + 1, cnt, size) == 0) {
            return 0;
        }

        if (check[r][c - 1][c] == 0) {
            check[r][c][c + 1] = 1;
            if (dfs(index + 1, cnt + 1, size) == 0) {
                return 0;
            }
            check[r][c][c + 1] = 0;
        }

        return 1;
    }

    // i 번 세로선의 결과가 i 번이 나오면 0 을 리턴
    static int go() {
        for (int i = 1; i <= N; i++) {
            int c = i;
            for (int r = 1; r <= H; r++) {
                if (check[r][c][c + 1] == 1) {
                    c++;
                } else if (check[r][c - 1][c] == 1) {
                    c--;
                }
            }
            if (c != i) {
                return 1;
            }
        }
        return 0;
    }
}
