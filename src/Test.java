public class Test {
    public static void main(String[] args) {
        int[] a = {1, 2, 3};
        int[] b = a;
        a = new int[5];
        System.out.print(b[0]);
    }
}
