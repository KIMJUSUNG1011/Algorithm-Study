// clear 1
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14890 {
    static int n, l;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        int i, j;
        for (i = 0; i < n; i++) {
            int cur_cnt = 1;
            for (j = 0; j < n - 1; j++) {
                if (map[i][j] == map[i][j + 1]) {
                    cur_cnt++;
                } else if (map[i][j] + 1 == map[i][j + 1]) {
                    if (cur_cnt < l) {
                        break;
                    }
                    cur_cnt = 1;
                } else if (map[i][j] - 1 == map[i][j + 1]) {
                    int next = map[i][j + 1];
                    int k = j + 1;
                    while (k < n && map[i][k] == next) {
                        k++;
                    }
                    if (k - (j + 1) < l) {
                        break;
                    } else {
                        j += l - 1;
                        cur_cnt = 0;
                    }
                } else {
                    break;
                }
            }
            if (j == n - 1) {
                answer++;
            }
        }

        for (i = 0; i < n; i++) {
            int cur_cnt = 1;
            for (j = 0; j < n - 1; j++) {
                if (map[j][i] == map[j + 1][i]) {
                    cur_cnt++;
                } else if (map[j][i] + 1 == map[j + 1][i]) {
                    if (cur_cnt < l) {
                        break;
                    }
                    cur_cnt = 1;
                } else if (map[j][i] - 1 == map[j + 1][i]) {
                    int next = map[j + 1][i];
                    int k = j + 1;
                    while (k < n && map[k][i] == next) {
                        k++;
                    }
                    if (k - (j + 1) < l) {
                        break;
                    } else {
                        j += l - 1;
                        cur_cnt = 0;
                    }
                } else {
                    break;
                }
            }
            if (j == n - 1) {
                answer++;
            }
        }

        System.out.print(answer);
    }
}
