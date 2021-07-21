import java.io.*;
import java.util.*;

public class Q16235 {
    static int N, M, K;
    static int[][] food = new int[11][11];
    static int[][] foodToBeAdded = new int[11][11];
    static TreeInfo[][] trees = new TreeInfo[11][11];

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
                foodToBeAdded[i][j] = Integer.parseInt(st.nextToken());
                trees[i][j] = new TreeInfo();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            trees[x][y].ageList.put(age, 1);
        }

        for (int i = 1; i <= K; i++) {
            spring();
            fall();
            winter();
        }

        long answer = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                TreeInfo info = trees[i][j];
                if (info.ageList.size() != 0) {
                    for (int age : info.ageList.keySet()) {
                        answer += info.ageList.get(age);
                    }
                }
            }
        }
        System.out.print(answer);
    }

    static void winter() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                food[i][j] += foodToBeAdded[i][j];
            }
        }
    }

    static void fall() {
        int[][] delta = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {-1, -1}, {-1, 1}, {1, 1}, {1, -1}};
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                TreeInfo info = trees[i][j];
                if (info.ageList.size() == 0) {
                    continue;
                }
                for (Integer age : info.ageList.keySet()) {
                    if (age % 5 == 0 && age > 0) {
                        int cnt = info.ageList.get(age);
                        for (int k = 0; k < 8; k++) {
                            int nr = i + delta[k][0];
                            int nc = j + delta[k][1];
                            if (nr < 1 || nc < 1 || nr > N || nc > N) {
                                continue;
                            }
                            TreeInfo nInfo = trees[nr][nc];
                            int nOneYear = 0;
                            if (nInfo.ageList.get(1) != null) {
                                nOneYear = nInfo.ageList.get(1);
                            }
                            nInfo.ageList.put(1, nOneYear + cnt);
                        }
                    }
                }
            }
        }
    }

    static void spring() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                TreeInfo info = trees[i][j];
                // 해당 좌표에는 나무가 없음
                if (info.ageList.size() == 0) {
                    continue;
                }

                int deadFood = 0;
                Map<Integer, Integer> newAgeList = new HashMap<>();
                for (int age : info.ageList.keySet()) {
                    int cnt = info.ageList.get(age);
                    int maxCnt = Math.min(food[i][j] / age, cnt);
                    if (maxCnt > 0) {
                        food[i][j] -= (age * maxCnt);
                        newAgeList.put(age + 1, maxCnt);
                    }
                    deadFood += ((age / 2) * (cnt - maxCnt));
                }
                food[i][j] += deadFood;
                info.ageList = newAgeList;
            }
        }
    }

    static class TreeInfo {
        Map<Integer, Integer> ageList;
        public TreeInfo() {
            ageList = new HashMap<>();
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
 */
