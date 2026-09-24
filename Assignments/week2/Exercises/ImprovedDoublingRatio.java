import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.ThreeSum;

public class ImprovedDoublingRatio {
    private static volatile long result;

    private static int timeTrial(int[] values) {
        return ThreeSum.count(values);
    }

    public static void main(String[] args) {
        int maximum = args.length > 0 ? Integer.parseInt(args[0]) : 1000;
        int trials = args.length > 1 ? Integer.parseInt(args[1]) : 10;
        if (maximum < 125 || trials < 1) {
            throw new IllegalArgumentException("Maximum N must be at least 125 and trials positive");
        }
        int[] warmup = new int[125];
        for (int index = 0; index < warmup.length; index++) {
            warmup[index] = StdRandom.uniformInt(-1000000, 1000000);
        }
        for (int call = 0; call < 20; call++) {
            result = timeTrial(warmup);
        }
        StdOut.println("N trials average(s) doubling-ratio");
        double previous = Double.NaN;
        for (int size = 125; size <= maximum; size *= 2) {
            int[][] inputs = new int[trials][size];
            for (int trial = 0; trial < trials; trial++) {
                for (int index = 0; index < size; index++) {
                    inputs[trial][index] = StdRandom.uniformInt(-1000000, 1000000);
                }
            }
            long total = 0;
            Stopwatch timer = new Stopwatch();
            for (int trial = 0; trial < trials; trial++) {
                total += timeTrial(inputs[trial]);
            }
            double average = timer.elapsedTime() / trials;
            result = total;
            StdOut.printf("%d %d %.6f %.2f%n", size, trials, average, average / previous);
            previous = average;
            if (size > maximum / 2) {
                break;
            }
        }
    }
}
