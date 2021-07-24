import java.io.*;
import java.util.*;

class Q14890_2 {
    static int N, L;
    static int[][] map = new int[101][101];
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int k = 0;
        while (k++ < 2) {
            int i, j;
            for (i = 0; i < N; i++) {
                boolean flag = true;
                int cnt = 1;
                for (j = 0; j < N - 1; j++) {
                    int h = k == 1 ? map[i][j] - map[i][j + 1] : map[j][i] - map[j + 1][i];
                    if (h < -1 || h > 1) {
                        flag = false;
                        break;
                    } else if (h == 0) {
                        cnt++;
                    } else if (h == -1) {
                        if (cnt >= L) {
                            cnt = 1;
                        } else {
                            flag = false;
                            break;
                        }
                    } else {
                        cnt = 1;
                        while (++j < N - 1) {
                            int tmpH = k == 1 ? map[i][j] - map[i][j + 1] : map[j][i] - map[j + 1][i];
                            if (tmpH == 0) {
                                cnt++;
                            } else {
                                break;
                            }
                        }
                        if (cnt < L) {
                            flag = false;
                            break;
                        } else {
                            cnt -= L;
                            j--;
                        }
                    }
                }
                if (flag) {
                    answer++;
                }
            }
        }

        System.out.print(answer);
    }
}
