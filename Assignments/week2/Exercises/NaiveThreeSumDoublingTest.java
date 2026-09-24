import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.ThreeSum;

public class NaiveThreeSumDoublingTest {
    private static volatile long result;

    public static int countNaive(int[] values) {
        int count = 0;
        for (int first = 0; first < values.length; first++) {
            for (int second = 0; second < values.length; second++) {
                for (int third = 0; third < values.length; third++) {
                    if (first < second && second < third
                            && values[first] + values[second] + values[third] == 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private static double averageTime(int[] values, boolean naive) {
        int calls = 1;
        double elapsed;
        do {
            long total = 0;
            Stopwatch timer = new Stopwatch();
            for (int call = 0; call < calls; call++) {
                total += naive ? countNaive(values) : ThreeSum.count(values);
            }
            elapsed = timer.elapsedTime();
            result = total;
            if (elapsed >= 0.05 || calls >= 1024) {
                break;
            }
            calls *= 2;
        } while (true);
        return elapsed / calls;
    }

    public static void main(String[] args) {
        int maximum = args.length > 0 ? Integer.parseInt(args[0]) : 512;
        if (maximum < 64) {
            throw new IllegalArgumentException("Maximum N must be at least 64");
        }
        StdOut.println("N naive(s) ThreeSum(s) naive/ThreeSum count");
        for (int size = 64; size <= maximum; size *= 2) {
            int[] values = new int[size];
            for (int index = 0; index < size; index++) {
                values[index] = StdRandom.uniformInt(-1000000, 1000000);
            }
            int naiveCount = countNaive(values);
            int standardCount = ThreeSum.count(values);
            if (naiveCount != standardCount) {
                throw new IllegalStateException("Counts differ");
            }
            double naiveTime = averageTime(values, true);
            double standardTime = averageTime(values, false);
            StdOut.printf("%d %.6f %.6f %.2f %d%n", size, naiveTime,
                    standardTime, naiveTime / standardTime, naiveCount);
            if (size > maximum / 2) {
                break;
            }
        }
    }
}
