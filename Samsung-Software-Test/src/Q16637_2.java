import java.io.*;

public class Q16637_2 {

    static int N, answer = Integer.MIN_VALUE;
    static int[] arr = new int[19];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String s = br.readLine();
        for (int i = 0; i < N; i++) {
            arr[i] = s.charAt(i) - '0';
        }

        dfs(0, 0);

        System.out.print(answer);
    }

    static void dfs(int index, int sum) {

        if (index >= N) {
            answer = Math.max(answer, sum);
            return;
        }

        int op = 0;

        if (arr[index] < 0 || arr[index] > 9) {
            op = arr[index];
            index++;
        }

        int res = arr[index];

        // 괄호를 치는 경우
        if (index < N - 1) {
            dfs(index + 3, calc(sum, calc(res, arr[index + 2], arr[index + 1]), op));
        }

        // 괄호를 치지 않는 경우
        dfs(index + 1, calc(sum, res, op));
    }

    static int calc(int a, int b, int p) {

        char c = (char)(p + '0');

        if (c == '-') {
            return a - b;
        }

        if (c == '*') {
            return a * b;
        }

        return a + b;
    }
}
