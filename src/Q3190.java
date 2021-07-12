import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q3190 {
    static int n, k, l;
    static int[][] map = new int[101][101];
    static Deque<int[]> dq = new ArrayDeque<>();
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException, NullPointerException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            //  사과의 위치를 1 로 표시
            map[x][y] = 1;
        }

        //  방향 변환 정보를 저장
        char[] changed = new char[10001];
        l = Integer.parseInt(br.readLine());
        for (int i = 0 ; i < l; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            changed[time] = st.nextToken().charAt(0);
        }

        // 뱀의 위치는 2 로 표시
        dq.add(new int[]{1, 1});
        map[1][1] = 2;

        //  최초에는 오른쪽 방향으로 이동하며 시작
        //  0 이 오른쪽 방향
        int cur_dir = 0;

        // 방향이 바뀌기 전까지 특정 방향으로 이동
        for (int i = 1; ; i++) {
            int[] head = !dq.isEmpty() ? dq.peekLast() : new int[2];
            int nx = head[0] + dx[cur_dir];
            int ny = head[1] + dy[cur_dir];

            // 벽을 만나거나 자기자신과 부딪힌 경우 게임 종료
            if ((nx < 1 || ny < 1 || nx > n || ny > n) || map[nx][ny] == 2) {
                System.out.print(i);
                return;
            }

            // 사과를 먹지 않은 경우 꼬리를 잘라냄
            if (map[nx][ny] != 1) {
                int[] tail = !dq.isEmpty() ? dq.poll() : new int[2];
                map[tail[0]][tail[1]] = 0;
            }

            dq.add(new int[]{nx, ny});
            map[nx][ny] = 2;

            // 방향 전환
            if (changed[i] == 'L') {
                cur_dir = (cur_dir == 0 ? 3 : cur_dir - 1);
            } else if (changed[i] == 'D') {
                cur_dir = (cur_dir == 3 ? 0 : cur_dir + 1);
            }
        }
    }
}
