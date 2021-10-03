import java.util.*;

public class Test {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        two(10);
        System.out.println(sb);
    }

    static void two(int n) {
        if (n == 1) {
            sb.append(n);
            return;
        }
        two(n / 2);
        sb.append(n % 2);
    }
}