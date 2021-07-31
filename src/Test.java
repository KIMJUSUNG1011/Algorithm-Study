import java.util.*;

public class Test {
    public static void main(String[] args) {
        A a = new A(1, 1);
        func(a);
        System.out.print(a.b);
    }

    static void func(A a) {
       a.b = 10;
    }

    static class A {
        int a, b;
        public A(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}
