import java.io.*;
import java.util.*;

public class Q17471_2 {

    static int N, stage = 0, answer = 1001;
    static int[] nums = new int[10];
    static int[] check = new int[10];
    static List<ArrayList<Integer>> graph = new ArrayList();
    static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int k = Integer.parseInt(st.nextToken());
            for (int j = 0; j < k; j++) {
                graph.get(i).add(Integer.parseInt(st.nextToken()) - 1);
            }
        }

        dfs(0, 0);

        if (answer == 1001) {
            System.out.print(-1);
        } else {
            System.out.print(answer);
        }
    }

    static void dfs(int index, int cnt) {

        if (index == N) {

            // 1 선거구의 하나의 구역
            int n1 = 0;

            // 2 선거구의 하나의 구역
            int n2 = N - 1 - log2(stage);

            // 선거구가 하나인 경우
            if (stage++ == 0) {
                return;
            }

            int sumN1 = bfs(n1, cnt, 1);
            if (sumN1 == -1) {
                return;
            }

            int sumN2 = bfs(n2, N - cnt, 0);
            if (sumN2 == -1) {
                return;
            }

            answer = Math.min(answer, Math.abs(sumN1 - sumN2));

            return;
        }

        check[index] = 1;
        dfs(index + 1, cnt + 1);

        // 탐색의 절반은 같은 경우이므로 탐색 x
        if (index == 0) {
            return;
        }

        check[index] = 0;
        dfs(index + 1, cnt);
    }

    // mark == 1 : 1번 선거구 표시
    // mark == 0 : 2번 선거구 표시
    static int bfs(int num, int cnt, int mark) {

        int[] visited = new int[N];
        int sum = 0;
        visited[num] = 1;
        q.add(num);

        while (!q.isEmpty()) {
            int p = q.poll();
            sum += nums[p];
            cnt--;
            List<Integer> g = graph.get(p);
            for (int i = 0; i < g.size(); i++) {
                int a = g.get(i);

                // 연결되어 있으면서 같은 선거구인 경우
                if (visited[a] == 0 && check[a] == mark) {
                    visited[a] = 1;
                    q.add(a);
                }
            }
        }

        if (cnt == 0) {
            return sum;
        } else {
            return -1;
        }
    }

    static int log2(int num) {
        return (int)(Math.log(num) / Math.log(2));
    }
}
