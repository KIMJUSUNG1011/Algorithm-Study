import java.io.*;
import java.util.*;

public class Q21609 {
    static int N, M;
    static int[][] board = new int[20][20];
    static boolean[][] visited = new boolean[20][20];
    static ArrayList<BlockGroup> blockGroups = new ArrayList<>();
    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int score = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            init();
            if (!find()) { break; }
            deleteBlock();
            gravity();
            rotate();
            gravity();
        }

        System.out.print(score);
    }

    static void init() {
        blockGroups.clear();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                visited[i][j] = false;
            }
        }
    }

    static boolean find() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] != -1 && board[i][j] != 0 && board[i][j] != -100 && !visited[i][j]) {
                    // board[i][j] 가 기준 블록인 경우만 탐색
                    findBlock(i, j);
                }
            }
        }
        return (blockGroups.size() > 0);
    }

    static void findBlock(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] innerVisited = new boolean[N][N];
        int normal = board[r][c], size = 0, nRainbow = 0;

        innerVisited[r][c] = true;
        q.add(new int[]{r, c});
        while (!q.isEmpty()) {
            int[] p = q.poll();
            size++;
            if (board[p[0]][p[1]] == 0) {
                nRainbow++;
            }
            if (board[p[0]][p[1]] == normal) {
                visited[p[0]][p[1]] = true;
            }
            for (int i = 0; i < 4; i++) {
                int nr = p[0] + delta[i][0];
                int nc = p[1] + delta[i][1];
                if ((nr < 0 || nc < 0 || nr >= N || nc >= N) || innerVisited[nr][nc]) {
                    continue;
                }
                if (board[nr][nc] == 0 || board[nr][nc] == normal) {
                    innerVisited[nr][nc] = true;
                    q.add(new int[]{nr, nc});
                }
            }
        }
        if (size >= 2) {
            blockGroups.add(new BlockGroup(r, c, size, nRainbow));
        }
    }

    static void deleteBlock() {
        Collections.sort(blockGroups);
        BlockGroup bg = blockGroups.get(0);
        score += Math.pow(bg.size, 2);

        Queue<int[]> q = new LinkedList<>();
        boolean[][] innerVisited = new boolean[N][N];
        int normal = board[bg.r][bg.c];

        innerVisited[bg.r][bg.c] = true;
        q.add(new int[]{bg.r, bg.c});
        while (!q.isEmpty()) {
            int[] p = q.poll();
            board[p[0]][p[1]] = -100;
            for (int i = 0; i < 4; i++) {
                int nr = p[0] + delta[i][0];
                int nc = p[1] + delta[i][1];
                if ((nr < 0 || nc < 0 || nr >= N || nc >= N) || innerVisited[nr][nc]) {
                    continue;
                }
                if (board[nr][nc] == 0 || board[nr][nc] == normal) {
                    innerVisited[nr][nc] = true;
                    q.add(new int[]{nr, nc});
                }
            }
        }
    }

    static void gravity() {
        for (int i = N - 1; i >= 0; i--) {
            for (int j = N - 1; j >= 0; j--) {
                if (board[i][j] == -1 || board[i][j] == -100) {
                    continue;
                }
                int r = i, c = j;
                while (true) {
                    int nr = r + delta[2][0];
                    int nc = c + delta[2][1];
                    if ((nr >= N || nc >= N) || board[nr][nc] != -100) {
                        break;
                    }
                    board[nr][nc] = board[r][c];
                    board[r][c] = -100;
                    r = nr;
                    c = nc;
                }
            }
        }
    }

    static void rotate() {
        int[][] tmp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tmp[(N - 1) - j][i] = board[i][j];
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = tmp[i][j];
            }
        }
    }

    static class BlockGroup implements Comparable<BlockGroup> {
        int r, c, size, nRainbow;
        public BlockGroup(int r, int c, int size, int nRainbow) {
            this.r = r;
            this.c = c;
            this.size = size;
            this.nRainbow = nRainbow;
        }

        @Override
        public int compareTo(BlockGroup o) {
            if (this.size != o.size) {
                return o.size - this.size;
            } else if (this.nRainbow != o.nRainbow) {
                return o.nRainbow - this.nRainbow;
            } else if (this.r != o.r) {
                return o.r - this.r;
            } else {
                return o.c - this.c;
            }
        }
    }
}
