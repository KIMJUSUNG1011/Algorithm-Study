import java.io.*;
import java.util.*;

public class Q17471 {
    static int N;
    static int[] map = new int[11];
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
    }

    static void print() {
        for (int i = 1; i <= N; i++) {
            ArrayList<Integer> list = graph.get(i);
            System.out.print(i + " : ");
            for (Integer num : list) {
                System.out.print(num + " ");
            } System.out.println();
        }
    }
}
