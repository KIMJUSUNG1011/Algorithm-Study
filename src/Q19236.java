import java.io.*;
import java.util.*;

public class Q19236 {
    static int answer = Integer.MIN_VALUE;
    static int[][] delta = {
            {-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Fish[] fishes = new Fish[17];
        int[][] nums = new int[4][4];
        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 4; j++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                nums[i][j] = a;
                fishes[a] = new Fish(i, j, b - 1);
            }
        }
        go(0, 0, fishes, nums, nums[0][0]);
        System.out.print(answer);
    }

    static void go(int r, int c, Fish[] fishes, int[][] nums, int sum) {
        // 원본 복사
        Fish[] tmpFishes = new Fish[17];
        int[][] tmpNums = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tmpNums[i][j] = nums[i][j];
                Fish f = fishes[i * 4 + j + 1];
                if (f != null) {
                    tmpFishes[i * 4 + j + 1] = new Fish(f.r, f.c, f.dir);
                }
            }
        }

        int target = tmpNums[r][c];
        int dir = tmpFishes[target].dir;

        // 상어가 해당 물고기를 먹음
        tmpNums[r][c] = -1;
        tmpFishes[target] = null;
        move(tmpFishes, tmpNums);
        tmpNums[r][c] = 0;

        boolean flag = false;
        while (true) {
            int nr = r + delta[dir][0];
            int nc = c + delta[dir][1];
            if (nr < 0 || nc < 0 || nr >= 4 || nc >= 4) {
                break;
            }
            if (tmpNums[nr][nc] != 0) {
                flag = true;
                go(nr, nc, tmpFishes, tmpNums, sum + tmpNums[nr][nc]);
            }
            r = nr;
            c = nc;
        }

        if (!flag) {
            answer = Math.max(answer, sum);
        }
    }

    static void move(Fish[] fishes, int[][] nums) {
        for (int i = 1; i <= 16; i++) {
            if (fishes[i] == null) {
                continue;
            }
            int r = fishes[i].r;
            int c = fishes[i].c;
            int dir = fishes[i].dir;

            if (nums[r][c] == -1) {
                continue;
            }

            int nr, nc, cnt = 0;
            int tmpDir = dir;
            do {
                nr = r + delta[tmpDir][0];
                nc = c + delta[tmpDir][1];

                // 다음 칸으로 이동할 수 있는 경우
                if ((nr >= 0 && nc >= 0 && nr < 4 && nc < 4) && nums[nr][nc] != -1) {
                    fishes[i].dir = tmpDir;
                    break;
                } else {
                    tmpDir = (tmpDir + 1) % 8;
                    cnt++;
                }
            } while (tmpDir != dir);

            if (cnt < 8) {
                int target = nums[nr][nc];
                int tmpNum = nums[r][c];
                nums[r][c] = target;
                nums[nr][nc] = tmpNum;
                fishes[i].r = nr;
                fishes[i].c = nc;

                if (fishes[target] != null) {
                    fishes[target].r = r;
                    fishes[target].c = c;
                }
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
        public String toString() {
            return r+","+c+","+dir;
        }
    }
}
