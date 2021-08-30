import java.io.*;
import java.util.*;

public class Q17471 {
    static int N;
    static int[] map = new int[11];
    static boolean[] check = new boolean[11];
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
            }
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
            process();
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

    static void process() {
        int sA = 0, sB = 0, cA = 0, cB = 0, a = 0, b = 0;
        for (int i = 1; i <= N; i++) {
            if (check[i]) {
                sA += map[i];
                cA++;
                if (a == 0) { a = i; }
            } else {
                sB += map[i];
                cB++;
                if (b == 0) { b = i; }
            }
        }

        if (isConnect(a, cA) && isConnect(b, cB)) {
            min = Math.min(min, Math.abs(sA - sB));
        }
    }

    // 팀 끼리 연결되어 있는지 체크
    static boolean isConnect(int start, int size) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        q.add(start);
        visited[start] = true;
        while (!q.isEmpty()) {
            int p = q.poll();
            size--;
            ArrayList<Integer> list = graph.get(p);
            for (int x : list) {
                if (!visited[x] && (check[x] == check[p])) {
                    q.add(x);
                    visited[x] = true;
                }
            }
        }
        return (size == 0);
    }
}
