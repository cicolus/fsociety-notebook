import javax.swing.text.html.HTMLDocument;
import java.util.*;

public class Problem_G {
    public static class Gem{
        long x;
        long y;

        public Gem(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class compareY implements Comparator<Gem>{
        @Override
        public int compare(Gem e1, Gem e2) {
            if (e1.y > e2.y) {
                return 1;
            } else if (e1.y == e2.y) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long r = scanner.nextLong();
        long w = scanner.nextLong();
        long h = scanner.nextLong();
        List<Gem> gems = new LinkedList<Gem>();
        List<Long> maxValues = new LinkedList<Long>();
        Map<Integer, Set<Integer>> visited = new HashMap<>();
        for (int t = 0; t < n; t++) {
            gems.add(new Gem(scanner.nextLong(), scanner.nextLong()));
            maxValues.add((long)0);
            visited.put(t, new HashSet<Integer>());
        }
        gems.sort(new compareY());

        for (int i = 0; i < n; i++) {
            long max = 1;
            HashSet<Integer> done = new HashSet<>();
            for (int j = i - 1; j >= 0; j--) {
                if (!done.contains(j)) {
                    if ((Math.abs(gems.get(i).x - gems.get(j).x) * r) <= Math.abs(gems.get(i).y - gems.get(j).y)) {
                        if (maxValues.get(j) + 1 > max) {
                            max = maxValues.get(j) + 1;
                        }
                        done.addAll(visited.get(j));
                        visited.get(i).addAll(done);
                        visited.get(i).add(j);
                    }
                }
            }
            maxValues.set(i, max);
        }
        long max = maxValues.get(1);
        for (int i = 0; i < n; i++) {
            if (maxValues.get(i) > max) {
                max = maxValues.get(i);
            }
        }
        System.out.print(max);
    }
}
