import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q23291 {

    static int N, K;
    static int[] arr = new int[100];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }
}

// 1. 물고기 수가 최소인 어항에 한 마리씩 추가
// 2. 어항 쌓기 1
// 3. 물고기 수 조절
// 4. 어항 일렬
// 5. 어항 쌓기 2
// 6. 물고기 수 조절
// 7. 어항 일렬
