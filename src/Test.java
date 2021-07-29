import java.util.*;

public class Test {
    public static void main(String[] args) {
        PriorityQueue<Pair> q = new PriorityQueue<>(new Comparator<Pair>(){
            @Override
            public int compare(Pair p1, Pair p2) {
                if (p1.r != p2.r) {
                    return p1.r - p2.r;
                } else {
                    return p1.c - p2.c;
                }
            }
        });

        Pair p1 = new Pair(2,4);
        Pair p2 = new Pair(1, 5);
        Pair p3 = new Pair(2, 2);
        Pair p4 = new Pair(1, 3);
        Pair p5 = new Pair(0, 9);

        q.add(p5);
        q.add(p4);
        q.add(p1);
        q.add(p2);
        q.add(p3);

        while (!q.isEmpty()) {
            System.out.println(q.poll());
        }
    }

    static class Pair {
        int r, c;
        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
        public String toString() {
            return this.r+","+this.c;
        }
    }
}
