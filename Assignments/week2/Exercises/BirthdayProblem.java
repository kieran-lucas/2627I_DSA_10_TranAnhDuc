import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class BirthdayProblem {
    private static int samplesUntilRepeat(int size) {
        boolean[] seen = new boolean[size];
        int samples = 0;
        while (true) {
            int value = StdRandom.uniformInt(size);
            samples++;
            if (seen[value]) {
                return samples;
            }
            seen[value] = true;
        }
    }

    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        int trials = args.length > 1 ? Integer.parseInt(args[1]) : 1000;
        if (size < 1 || trials < 1) {
            throw new IllegalArgumentException("N and trials must be positive");
        }
        double total = 0;
        for (int trial = 0; trial < trials; trial++) {
            total += samplesUntilRepeat(size);
        }
        double average = total / trials;
        double theoreticalScale = Math.sqrt(Math.PI * size / 2);
        StdOut.printf("N=%d trials=%d average=%.3f sqrt(pi*N/2)=%.3f ratio=%.3f%n",
                size, trials, average, theoreticalScale, average / theoreticalScale);
    }
}
