import java.util.*;

public class Test {

    public static void main(String[] args) {

        Queue<A> q1 = new LinkedList<>();
        Queue<A> q2 = new LinkedList<>();

        for (int i = 1; i <= 5; i++) {
            q2.add(new A(i));
        }

        q1.addAll(q2);

        for (int i = 0; i < q2.size(); i++) {
            A a = q2.poll();
            a.a = 10;
            q2.add(a);
        }

        while (!q1.isEmpty()) {
            System.out.println(q1.poll().a);
        }

    }

    static class A {
        int a;
        public A(int a) {
            this.a = a;
        }
    }
}