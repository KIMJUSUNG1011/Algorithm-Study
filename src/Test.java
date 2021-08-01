import java.util.*;

public class Test {
    public static void main(String[] args) {
        Map<Integer, Shark> map = new HashMap<>();
        Map<Integer, Shark> tmp_map = new HashMap<>();
        map.put(4, new Shark(1, 1, 1, 1, 1));
        map.put(1, new Shark(1, 1, 1, 1, 2));
        map.put(3, new Shark(1, 1, 1, 1, 3));

        tmp_map.put(10, map.get(4));
        map.clear();
        System.out.println(tmp_map.get(10));
    }

    static class Shark {
        int r, c, s, d, z;
        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
        public String toString() {
            return r+","+c+","+s+","+d+","+z;
        }
    }
}
