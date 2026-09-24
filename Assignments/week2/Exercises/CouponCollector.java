import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class CouponCollector {
    private static long samplesToCollectAll(int size) {
        boolean[] seen = new boolean[size];
        int remaining = size;
        long samples = 0;
        while (remaining > 0) {
            int value = StdRandom.uniformInt(size);
            samples++;
            if (!seen[value]) {
                seen[value] = true;
                remaining--;
            }
        }
        return samples;
    }

    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        int trials = args.length > 1 ? Integer.parseInt(args[1]) : 1000;
        if (size < 1 || trials < 1) {
            throw new IllegalArgumentException("N and trials must be positive");
        }
        double total = 0;
        for (int trial = 0; trial < trials; trial++) {
            total += samplesToCollectAll(size);
        }
        double harmonic = 0;
        for (int value = 1; value <= size; value++) {
            harmonic += 1.0 / value;
        }
        double average = total / trials;
        double theoretical = size * harmonic;
        StdOut.printf("N=%d trials=%d average=%.3f H_N=%.6f N*H_N=%.3f ratio=%.3f%n",
                size, trials, average, harmonic, theoretical, average / theoretical);
    }
}
