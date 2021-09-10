import java.io.*;
import java.util.*;

public class Q16235_2 {
    static int N, M, K;
    static int[][] foodToBeAdded = new int[11][11];
    static Board[][] boards = new Board[11][11];
    static int[][] delta = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {-1, -1}, {-1, 1}, {1, 1}, {1, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K  = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= N; j++) {
                foodToBeAdded[i][j] = Integer.parseInt(st.nextToken());
                boards[i][j] = new Board();
            }
        }
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            boards[r][c].alive.add(age);
        }

        while (K-- > 0) {
            spring();
            summer();
            fall();
            winter();
        }

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                answer += boards[i][j].alive.size();
            }
        }

        System.out.print(answer);
    }

    static void spring() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                Board board = boards[i][j];
                int nAlive = board.alive.size();
                for (int k = 1; k <= nAlive; k++) {
                    int age = board.alive.remove();
                    if (board.food >= age) {
                        board.food -= age;
                        board.alive.add(age + 1);
                    } else {
                        board.dead.add(age);
                    }
                }
            }
        }
    }

    static void summer() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                Board board = boards[i][j];
                while (!board.dead.isEmpty()) {
                    int age = board.dead.remove();
                    board.food += age / 2;
                }
            }
        }
    }

    static void fall() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                Board board = boards[i][j];
                for (Integer age : board.alive) {
                    if (age % 5 == 0) {
                        for (int k = 0; k < 8; k++) {
                            int nr = i + delta[k][0];
                            int nc = j + delta[k][1];
                            if (nr >= 1 && nc >= 1 && nr <= N && nc <= N) {
                                boards[nr][nc].alive.addFirst(1);
                            }
                        }
                    }
                }
            }
        }
    }

    static void winter() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                boards[i][j].food += foodToBeAdded[i][j];
            }
        }
    }

    static class Board {
        int food;
        Deque<Integer> alive, dead;
        public Board() {
            food = 5;
            alive = new LinkedList<>();
            dead = new LinkedList<>();
        }
    }
}
