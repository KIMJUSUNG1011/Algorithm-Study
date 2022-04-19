import java.io.*;
import java.util.*;

public class Q16637_2 {

    static int N, nNum = 0, nOp = 0, answer = Integer.MIN_VALUE;
    static int[] num = new int[10];
    static char[] op = new char[9];
    static int[] check = new int[10];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String s = br.readLine();
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) {
                num[nNum++] = s.charAt(i) - '0';
            } else {
                op[nOp++] = s.charAt(i);
            }
        }
    }

    static void dfs(int index, int sum) {

        if (index >= nNum) {
            return;
        }

        int res = 0;

        check[index] = 1;
        if (index < N - 1) {
            calc(num[index], num[index + 1], op[index]);
            dfs(index + 2, sum + );
        }

        check[index] = 0;
        dfs(index + 1);
    }

    static int calc(int a, int b, char p) {
        return 0;
    }
}
