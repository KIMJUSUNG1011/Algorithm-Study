import java.io.*;
import java.util.*;

public class Q21608 {
    static int N;
    static int[][] board = new int[20][20];
    static int[][] prior = new int[401][4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N * N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int num = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 4; j++) {
                prior[num][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void printPrior() {
        for (int i = 1; i <= N * N; i++) {
            System.out.print(i + " : ");
            for (int j = 0; j < 4; j++) {
                System.out.print(prior[i][j] + " ");
            } System.out.println();
        }
    }
}
