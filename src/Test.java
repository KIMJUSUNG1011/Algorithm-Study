import java.util.*;

public class Test {

    public static void main(String[] args) {
        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(1);
        dq.add(2);
        dq.add(3);
        System.out.println(dq.pollFirst());
    }
}
