import java.io.*;
import java.util.*;

public class Q3954 {
    static int t, sm, sc, si, mPointer, cPointer, iPointer, min, max;
    static int[] memory, pair;
    static char[] code, input;
    static Stack<Paren> stack;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            sm = Integer.parseInt(st.nextToken());
            sc = Integer.parseInt(st.nextToken());
            si = Integer.parseInt(st.nextToken());
            memory = new int[sm];
            code = new char[sc];
            input = new char[si];
            pair = new int[sc];

            stack = new Stack<>();
            String line = br.readLine();
            for (int i = 0; i < sc; i++) {
                code[i] = line.charAt(i);
                if (code[i] == '[' || code[i] == ']') {
                    stack.push(new Paren(code[i], i));
                }
            }

            line = br.readLine();
            for (int i = 0; i < si; i++) {
                input[i] = line.charAt(i);
            }

            pair();
            if (execute()) {
                answer.append("Terminates").append("\n");
            } else {
                answer.append("Loops").append(" ");
                answer.append(min - 1).append(" ").append(max).append("\n");
            }
        }

        System.out.print(answer);
    }

    // 괄호끼리 짝을 지어줌
    static void pair() {
        Stack<Paren> tmp = new Stack<>();
        while (!stack.isEmpty()) {
            Paren p = stack.pop();
            if (p.ch == ']') {
                tmp.push(p);
            } else {
                Paren t = tmp.pop();
                pair[t.idx] = p.idx;
                pair[p.idx] = t.idx;
            }
        }
    }

    static boolean execute() {
        mPointer = 0;
        cPointer = 0;
        iPointer = 0;
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;

        int cnt = 0;
        while (cPointer < sc) {
            if (code[cPointer] == '-') {
                memory[mPointer] = (memory[mPointer] + 255) % 256;
            } else if (code[cPointer] == '+') {
                memory[mPointer] = (memory[mPointer] + 1) % 256;
            } else if (code[cPointer] == '<') {
                mPointer = (mPointer + sm - 1) % sm;
            } else if (code[cPointer] == '>') {
                mPointer = (mPointer + 1) % sm;
            } else if (code[cPointer] == '[' && memory[mPointer] == 0) {
                cPointer = pair[cPointer];
            } else if (code[cPointer] == ']' && memory[mPointer] != 0) {
                cPointer = pair[cPointer];
            } else if (code[cPointer] == ',') {
                if (iPointer == si) {
                    memory[mPointer] = 255;
                } else {
                    memory[mPointer] = input[iPointer];
                    iPointer++;
                }
            }
            cPointer++;
            cnt++;

            // 최악의 경우 5천만번의 명령을 수행해야 한 번의 루프가 나오는 경우도 있음
            // 따라서 5천만번의 수행으로는 어떤 루프가 무한루프인지 확정지을 수 없으므로
            // 5천만번 ~ 1억번을 돌려서 가장 큰 루프를 구해야함
            if (cnt > 50000000) {
                min = Math.min(min, cPointer);
                max = Math.max(max, cPointer);
            }
            if (cnt > 100000000) {
                break;
            }
        }

        return (cnt <= 50000000);
    }

    static class Paren {
        char ch;
        int idx;
        public Paren(char ch, int idx) {
            this.ch = ch;
            this.idx = idx;
        }
    }
}
