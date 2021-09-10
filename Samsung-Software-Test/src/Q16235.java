import java.io.*;
import java.util.*;

public class Q16235 {
    static int N, M, K;
    static int[][] food = new int[11][11];
    static int[][] foodAddedInWinter = new int[11][11];
    static int[][] foodAddedByDeadTree = new int[11][11];
    static Deque<Tree> livingTrees = new ArrayDeque<>();
    static int[][] delta = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {-1, -1}, {-1, 1}, {1, 1}, {1, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                food[i][j] = 5;
                foodAddedInWinter[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            livingTrees.add(new Tree(x, y, age));
        }

        while (K-- > 0) {
            // spring
            int len = livingTrees.size();
            for (int i = 0; i < len; i++) {
                Tree t = livingTrees.pollFirst();
                if (t != null) {
                    if (food[t.r][t.c] >= t.age) {
                        food[t.r][t.c] -= t.age++;
                        livingTrees.addLast(t);
                    } else {
                        foodAddedByDeadTree[t.r][t.c] += t.age / 2;
                    }
                }
            }

            // summer
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    food[i][j] += foodAddedByDeadTree[i][j];
                    foodAddedByDeadTree[i][j] = 0;
                }
            }

            // fall
            Deque<Tree> tmpTrees = new ArrayDeque<>();
            while (!livingTrees.isEmpty()) {
                Tree t = livingTrees.pollFirst();
                tmpTrees.addLast(t);
                if (t.age % 5 == 0) {
                    for (int k = 0; k < 8; k++) {
                        int nr = t.r + delta[k][0];
                        int nc = t.c + delta[k][1];
                        if (nr < 1 || nc < 1 || nr > N || nc > N) {
                            continue;
                        }
                        tmpTrees.addFirst(new Tree(nr, nc, 1));
                    }
                }
            }
            livingTrees = tmpTrees;

            // winter
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    food[i][j] += foodAddedInWinter[i][j];
                }
            }
        }

        System.out.print(livingTrees.size());
    }

    static class Tree {
        int r, c, age;
        public Tree(int r, int c, int age) {
            this.r = r;
            this.c = c;
            this.age = age;
        }
    }
}

/*
10 1 1000
100 100 100 100 100 100 100 100 100 100
100 100 100 100 100 100 100 100 100 100
100 100 100 100 100 100 100 100 100 100
100 100 100 100 100 100 100 100 100 100
100 100 100 100 100 100 100 100 100 100
100 100 100 100 100 100 100 100 100 100
100 100 100 100 100 100 100 100 100 100
100 100 100 100 100 100 100 100 100 100
100 100 100 100 100 100 100 100 100 100
100 100 100 100 100 100 100 100 100 100
1 1 1
answer : 5443
 */
