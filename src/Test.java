import java.util.*;

public class Test {
    public static void main(String[] args) {
        A[] arr1 = new A[5];
        A[] arr2 = new A[5];

        arr1[0] = new A(10);
        arr2[0] = arr1[0];
        arr1[0] = null;
        System.out.print(arr2[0]);
    }

    static class A {
        int a;
        public A(int a) {
            this.a = a;
        }
    }
}
