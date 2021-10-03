public class Test {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        convert(11, 3, sb);
        System.out.println(sb);

        sb = new StringBuilder();
        convert(11, 2, sb);
        System.out.println(sb);
    }
    static void convert(int n, int x, StringBuilder sb) {
        if (n < x) {
            sb.append(n);
            return;
        }
        convert(n / x, x, sb);
        sb.append(n % x);
    }
}
