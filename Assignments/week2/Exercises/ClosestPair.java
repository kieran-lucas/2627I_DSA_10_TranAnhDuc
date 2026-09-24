import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

public class ClosestPair {
    public static double[] find(double[] values) {
        if (values.length < 2) {
            throw new IllegalArgumentException("At least two values are required");
        }
        double[] sorted = values.clone();
        Arrays.sort(sorted);
        int best = 1;
        double distance = Math.abs(sorted[1] - sorted[0]);
        for (int index = 2; index < sorted.length; index++) {
            double nextDistance = Math.abs(sorted[index] - sorted[index - 1]);
            if (nextDistance < distance) {
                distance = nextDistance;
                best = index;
            }
        }
        return new double[] {sorted[best - 1], sorted[best], distance};
    }

    public static void main(String[] args) {
        double[] pair = find(StdIn.readAllDoubles());
        StdOut.printf("%.6f %.6f distance=%.6f%n", pair[0], pair[1], pair[2]);
    }
}
