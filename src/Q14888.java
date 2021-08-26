import java.io.*;
import java.util.*;

public class Q14888 {
    static int N;
    static int[] arr = new int[11];
    static int[] ops = new int[11];
    static boolean[] visited = new boolean[11];
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        ops[0] = 1;
        int idx = 1;
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 4; i++) {
            int num = Integer.parseInt(st.nextToken());
            while (num-- > 0) {
                ops[idx++] = i + 1;
            }
        }

        visited[0] = true;
        go(0, 0, 0);
        System.out.print(max + "\n" + min);
    }

    static void go(int index, int res, int pos) {

        // 연산 수행
        res = operate(ops[pos], arr[index], res);

        // 종료 조건
        if (index == N - 1) {
            max = Math.max(max, res);
            min = Math.min(min, res);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                go(index + 1, res, i);
                visited[i] = false;
            }
        }
    }

    static int operate(int op, int num1, int num2) {
        if (op == 1) {
           return num2 + num1;
        } else if (op == 2) {
            return num2 - num1;
        } else if (op == 3) {
           return num2 * num1;
        } else {
            return num2 / num1;
        }
    }
}
