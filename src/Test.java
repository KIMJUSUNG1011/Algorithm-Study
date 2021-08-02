import java.util.*;

public class Test {
    public static void main(String[] args) {
        Map<Integer, String> map = new TreeMap<>();
        map.put(41, "a");
        map.put(1, "b");
        map.put(14, "c");

        for (Integer i : map.keySet()) {
            System.out.print(i+" ");
        }
    }
}
