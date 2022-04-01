import java.io.*;
import java.util.*;

public class Q13458_2 {

    static long answer;
    static int N, B, C;
    static int[] A = new int[1000000];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        answer = N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine(), " ");
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            A[i] -= B;
            if (A[i] > 0) {
                answer += A[i] / C;
                if (A[i] % C > 0) {
                    answer++;
                }
            }
        }

        System.out.print(answer);
    }
}

/*
1. 모든 시험장에 총 감독관은 하나씩 들어가야 하므로 정답 = 시험장의 개수
2. 각 시험장에서 총 감독관이 감시할 수 있는 응시자를 뺌
    1. 뺀 값 > 0 : 부감독관이 필요함
    2. 뺀 값을 부감독관이 감시할 수 있는 응시자의 수로 나눔
        1. 정답 + 몫
        2. 나머지가 있는 경우 추가로 + 1
 */
