import java.util.*;

public class Test {
    public static void main(String[] args) {
        double[] arr = {
                2.6, 1.8, 2.6, 3.7, 1.9, 4.2, 3.8, 2.1, 2.7, 3.0,
                4.0, 2.4, 2.3, 4.3, 3.1, 2.6, 2.6, 2.5, 2.7, 2.7,
                2.9, 3.4, 1.9, 2.3, 3.3, 2.2, 3.5, 3.0, 2.5, 3.4
        };

        double sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        System.out.println("sum : " + sum);
        System.out.println("avg : " + (sum / arr.length));
        Arrays.sort(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
            if ((i + 1) % 10 == 0) {
                System.out.println();
            }
        }

        int idx1 = ((arr.length / 2) - 1) / 2;
        int idx2 = ((arr.length / 2) + (arr.length - 1)) / 2;
        System.out.println("Q1 : " + arr[idx1]);
        System.out.println("Q2 : " + arr[idx2]);

        System.out.println("max : " + arr[arr.length - 1]);
        System.out.println("min : " + arr[0]);
    }
}
