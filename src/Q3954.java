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
        int cnt = 0;
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;

        while (cPointer < sc) {
            if (code[cPointer] == '-') {
                memory[mPointer] = (memory[mPointer] + 255) % 256;
            } else if (code[cPointer] == '+') {
                memory[mPointer] = (memory[mPointer] + 1) % 256;
            } else if (code[cPointer] == '<') {
                mPointer = (mPointer + sm - 1) % sm;
            } else if (code[cPointer] == '>') {
                mPointer = (mPointer + 1) % sm;
            } else if (code[cPointer] == '[') {
                if (memory[mPointer] == 0) {
                    cPointer = pair[cPointer];
                }
            } else if (code[cPointer] == ']') {
                if (memory[mPointer] != 0) {
                    cPointer = pair[cPointer];
                }
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
