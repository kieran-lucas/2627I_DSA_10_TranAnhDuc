import edu.princeton.cs.algs4.In;
import java.util.Random;

public class BenchmarkData {
    public static int[] create(String type, int size, int run) {
        if (type.equals("file")) {
            In input = new In("algs4-data/" + size / 1000 + "Kints.txt");
            int[] values = input.readAllInts();
            input.close();
            if (values.length != size) {
                throw new IllegalStateException("Unexpected file size");
            }
            return values;
        }
        int[] values = new int[size];
        Random random = new Random(20261011L + size * 100L + run);
        for (int i = 0; i < size; i++) {
            if (type.equals("random")) {
                values[i] = random.nextInt(2000001) - 1000000;
            } else if (type.equals("ascending")) {
                values[i] = i;
            } else if (type.equals("descending")) {
                values[i] = size - i;
            } else {
                values[i] = 7;
            }
        }
        return values;
    }

    public static void check(int[] values) {
        for (int i = 1; i < values.length; i++) {
            if (values[i - 1] > values[i]) {
                throw new IllegalStateException("Array is not sorted");
            }
        }
    }
}
