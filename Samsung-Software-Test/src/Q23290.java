import java.io.*;
import java.util.*;

public class Q23290 {

    static int M, S;
    static int[][] delta = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
    static int[][] delta2 = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    // 상어, 물고기들을 관리
    static Fish shark = new Fish(-1, -1, -1);
    static Queue<Fish> save = new LinkedList<>();
    static int[][] nFish = new int[4][4];
    static Queue<Fish> fishes = new LinkedList<>();

    // 물고기 냄새를 관리
    static int[][] nSmell = new int[4][4];
    static Queue<Smell> smells = new LinkedList<>();

    // 상어의 이동 경로와 제외된 물고기 수 저장
    static List<Info> infos = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken()) - 1;
            fishes.add(new Fish(r, c, dir));
            nFish[r][c]++;
        }

        st = new StringTokenizer(br.readLine(), " ");
        shark.r = Integer.parseInt(st.nextToken()) - 1;
        shark.c = Integer.parseInt(st.nextToken()) - 1;

        for (int i = 0; i < S; i++) {

            // 모든 물고기 별도 저장
            Iterator<Fish> it = fishes.iterator();
            while (it.hasNext()) {
                Fish f = it.next();
                save.add(new Fish(f.r, f.c, f.dir));
            }

            // 모든 물고기들을 이동
            moveFishes();

            // 상어의 이동
            moveShark();

            // 냄새 삭제
            removeSmell();

            // 물고기 복제
            while (!save.isEmpty()) {
                Fish f = save.poll();
                fishes.add(new Fish(f.r, f.c, f.dir));
                nFish[f.r][f.c]++;
            }
        }

        System.out.println(fishes.size());
    }

    static void moveFishes() {

        Iterator<Fish> it = fishes.iterator();

        while (it.hasNext()) {

            Fish f = it.next();
            int r = f.r, c = f.c, dir = f.dir;

            for (int j = 0; j < 8; j++) {
                int nr = r + delta[dir][0];
                int nc = c + delta[dir][1];
                if (nr < 0 || nc < 0 || nr >= 4 || nc >= 4) {
                    dir = (dir + 7) % 8;
                    continue;
                }
                if ((nr == shark.r && nc == shark.c) || nSmell[nr][nc] > 0) {
                    dir = (dir + 7) % 8;
                    continue;
                }

                nFish[r][c]--;
                nFish[nr][nc]++;

                f.r = nr;
                f.c = nc;
                f.dir = dir;

                break;
            }
        }
    }

    static void moveShark() {

        // 1, 2, 3, 4 로 구성되는 3자리 정수 순열을 찾기
        int[] arr = {1, 2, 3, 4};

        go(0, arr, "");

        Collections.sort(infos);

        Info info = infos.get(0);
        String route = info.route + "";

        for (int i = 0; i < 3; i++) {

            int dir = (route.charAt(i) - '0') - 1;
            shark.r += delta2[dir][0];
            shark.c += delta2[dir][1];

            nFish[shark.r][shark.c] = 0;

            int len = fishes.size();

            for (int j = 0; j < len; j++) {

                Fish f = fishes.poll();

                if (f.r == shark.r && f.c == shark.c) {
                    smells.add(new Smell(f.r, f.c, 2));
                    nSmell[f.r][f.c]++;
                } else {
                    fishes.add(f);
                }
            }
        }

        infos.clear();
    }

    static void go(int index, int[] arr, String route) {

        if (index == 3) {

            int r = shark.r, c = shark.c, cnt = 0;
            int[][] check = new int[4][4];

            for (int i = 0; i < 3; i++) {
                int dir = (route.charAt(i) - '0') - 1;
                r += delta2[dir][0];
                c += delta2[dir][1];
                if (r < 0 || c < 0 || r >= 4 || c >= 4) {
                    return;
                }
                if (check[r][c] == 0) {
                    cnt += nFish[r][c];
                    check[r][c] = 1;
                }
            }

            infos.add(new Info(Integer.parseInt(route), cnt));
            return;
        }

        for (int i = 0; i < 4; i++) {
            go(index + 1, arr, route + arr[i]);
        }
    }

    static void removeSmell() {

        int len = smells.size();

        for (int i = 0; i < len; i++) {

            Smell s = smells.poll();

            if (s.time == 0) {
                nSmell[s.r][s.c]--;
            } else {
                s.time--;
                smells.add(s);
            }
        }
    }

    static class Fish {

        int r, c, dir;

        public Fish(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }

    static class Smell {

        int r, c, time;

        public Smell(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }

    static class Info implements Comparable<Info> {

        int route, cnt;

        public Info(int route, int cnt) {
            this.route = route;
            this.cnt = cnt;
        }

        public int compareTo(Info o) {
            if (this.cnt != o.cnt) {
                return o.cnt - this.cnt;
            } else {
                return this.route - o.route;
            }
        }
    }
}

// 1. 모든 물고기들을 별도로 저장
// 2. 모든 물고기들을 이동
// 3. 상어를 이동시키면서 물고기를 제외시키고, 물고기 냄새를 저장
// 4. 두 턴 전의 물고기 냄새를 삭제
// 5. 1 에서 저장한 물고기들을 복사
