import java.io.*;
import java.util.*;

public class Q21608 {
    static int N;
    static int[][] board = new int[20][20];
    static int[][] prior = new int[401][4];
    static boolean[][] visited = new boolean[401][401];
    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static ArrayList<Integer> order = new ArrayList<>();
    static ArrayList<Desk> desks = new ArrayList<>();
    static int[] score = {0, 1, 10, 100, 1000};
    static long answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N * N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int num = Integer.parseInt(st.nextToken());
            order.add(num);
            for (int j = 0; j < 4; j++) {
                int p = Integer.parseInt(st.nextToken());
                prior[num][j] = p;
                visited[num][p] = true;
            }
        }

        for (int num : order) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] != 0) {
                        continue;
                    }
                    int pCnt = 0, bCnt = 0;
                    for (int k = 0; k < 4; k++) {
                        int nr = i + delta[k][0];
                        int nc = j + delta[k][1];
                        if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
                            continue;
                        }
                        if (visited[num][board[nr][nc]]) {
                            pCnt++;
                        }
                        if (board[nr][nc] == 0) {
                            bCnt++;
                        }
                    }
                    desks.add(new Desk(i, j, pCnt, bCnt));
                }
            }
            Collections.sort(desks);
            Desk d = desks.get(0);
            board[d.r][d.c] = num;
            desks.clear();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int num = board[i][j];
                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    int nr = i + delta[k][0];
                    int nc = j + delta[k][1];
                    if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
                        continue;
                    }
                    if (visited[num][board[nr][nc]]) {
                        cnt++;
                    }
                }
                answer += score[cnt];
            }
        }

        System.out.print(answer);
    }

    static class Desk implements Comparable<Desk> {
        int r, c, pCnt, bCnt;
        public Desk(int r, int c, int pCnt, int bCnt) {
           this.r = r;
           this.c = c;
           this.pCnt = pCnt;
           this.bCnt = bCnt;
        }

        @Override
        public int compareTo(Desk o) {
            if (this.pCnt != o.pCnt) {
                return o.pCnt - this.pCnt;
            } else if (this.bCnt != o.bCnt) {
                return o.bCnt - this.bCnt;
            } else if (this.r != o.r) {
                return this.r - o.r;
            } else {
                return this.c - o.c;
            }
        }
    }
}
