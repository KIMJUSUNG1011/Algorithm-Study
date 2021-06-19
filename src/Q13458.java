import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q13458 {
    static int n, b, c;
    static int[] a = new int[1000007];
    static long answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine(), " ");
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            int spare = a[i] - b;
            if (spare > 0) {
                int x = spare / c;
                int y = spare % c;
                if (y == 0) {
                    answer += x;
                } else {
                    answer += x + 1;
                }
            }
        }

        answer += n;
        System.out.print(answer);
    }
}
