import java.util.ArrayDeque;
import java.util.Deque;

public class Test {

    public static void main(String[] args) {
        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(0);
        dq.add(1);
        dq.add(2);
        dq.add(3);
        System.out.print(dq.pollLast());
        System.out.print(dq.size());
    }
}
