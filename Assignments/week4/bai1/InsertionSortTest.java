import java.util.Arrays;
import java.util.Random;

public class InsertionSortTest {
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
        System.out.println("PASS: 208 insertion sort cases");
    }

    private static void check(int[] values) {
        int[] expected = values.clone();
        Arrays.sort(expected);
        InsertionSort.sort(values);
        if (!Arrays.equals(values, expected)) {
            throw new AssertionError("Incorrect insertion sort");
        }
    }
}
