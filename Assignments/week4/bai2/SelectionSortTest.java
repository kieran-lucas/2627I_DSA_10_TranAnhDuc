import java.util.Arrays;
import java.util.Random;

public class SelectionSortTest {
    public static void main(String[] args) {
        int[][] cases = {{}, {7}, {2, 1}, {1, 2, 3}, {3, 2, 1},
                {4, 4, 4}, {-1, 5, -3, 5, 0}, {Integer.MAX_VALUE, 0, Integer.MIN_VALUE}};
        for (int[] values : cases) {
            check(values);
        }
        Random random = new Random(42);
        for (int test = 0; test < 200; test++) {
            int[] values = new int[random.nextInt(101)];
            for (int i = 0; i < values.length; i++) {
                values[i] = random.nextInt(41) - 20;
            }
            check(values);
        }
        System.out.println("PASS: 208 cases for both selection sort versions");
    }

    private static void check(int[] values) {
        int[] expected = values.clone();
        Arrays.sort(expected);
        Integer[] boxed = new Integer[values.length];
        for (int i = 0; i < values.length; i++) {
            boxed[i] = values[i];
        }
        SelectionSort.sort(values);
        Selection.sort(boxed);
        if (!Arrays.equals(values, expected)) {
            throw new AssertionError("Incorrect selection sort");
        }
        for (int i = 0; i < values.length; i++) {
            if (boxed[i] != expected[i]) {
                throw new AssertionError("Incorrect Comparable selection sort");
            }
        }
    }
}
