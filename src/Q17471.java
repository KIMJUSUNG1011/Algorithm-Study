import java.io.*;
import java.util.*;

public class Q17471 {
    static int N;
    static int[] map = new int[11];
    static boolean[] check = new boolean[11];
    static boolean[][] pair = new boolean[11][11];
    static int nA = 0;
    static int min = Integer.MAX_VALUE;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int m = Integer.parseInt(st.nextToken());
            for (int j = 0; j < m; j++) {
                int num = Integer.parseInt(st.nextToken());
                graph.get(i).add(num);
                pair[i][num] = true;
                pair[num][i] = true;
            }
            pair[i][i] = true;
        }

        nA = N / 2;
        while (nA > 0) {
            go(1, 0);
            nA--;
        }

        if (min == Integer.MAX_VALUE) {
            System.out.print(-1);
        } else {
            System.out.print(min);
        }
    }

    static void go(int index, int cnt) {

        if (cnt == nA) {
            if (isConnect()) {
                int sA = 0, sB = 0;
                for (int i = 1; i <= N; i++) {
                    if (check[i]) {
                        sA += map[i];
                    } else {
                        sB += map[i];
                    }
                }
                min = Math.min(min, Math.abs(sA - sB));
            }
            return;
        }

        if (index == N + 1) {
            return;
        }

        check[index] = true;
        go(index + 1, cnt + 1);
        check[index] = false;
        go(index + 1, cnt);
    }

    // A, B 팀이 각각 팀 끼리 연결되어 있는지 체크
    static boolean isConnect() {
        return true;
    }
}
