import java.io.*;

public class Q16637 {
    static int N;
    static String s;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        s = br.readLine();
        go(0, 0, '+');
        System.out.print(max);
    }

    static void go(int index, int sum, char c) {

        if (index > s.length()) {
            max = Math.max(max, sum);
            return;
        }

        // 괄호를 추가하는 경우
        if (index < s.length() - 1) {
            int res = cal(s.charAt(index) - '0', s.charAt(index + 2) - '0', s.charAt(index + 1));
            char nextC = (index == s.length() - 3) ? 0 : s.charAt(index + 3);
            go(index + 4, cal(sum, res, c), nextC);
        }

        // 괄호를 추가하지 않는 경우
        char nextC = (index == s.length() - 1) ? 0 : s.charAt(index + 1);
        go(index + 2, cal(sum, s.charAt(index) - '0', c), nextC);
    }

    static int cal(int a, int b, char c) {
        if (c == '+') {
            return a + b;
        } else if (c == '-') {
            return a - b;
        } else {
            return a * b;
        }
    }
}

/*
1. 숫자 하나를 괄호 안의 왼쪽 피연산자로 선택하는 경우와 선택하지 않는 경우로 나뉨
 */
