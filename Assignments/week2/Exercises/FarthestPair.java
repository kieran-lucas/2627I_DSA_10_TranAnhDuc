import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FarthestPair {
    public static double[] find(double[] values) {
        if (values.length < 2) {
            throw new IllegalArgumentException("At least two values are required");
        }
        double minimum = values[0];
        double maximum = values[0];
        for (int index = 1; index < values.length; index++) {
            if (values[index] < minimum) {
                minimum = values[index];
            }
            if (values[index] > maximum) {
                maximum = values[index];
            }
        }
        return new double[] {minimum, maximum, Math.abs(maximum - minimum)};
    }

    public static void main(String[] args) {
        double[] pair = find(StdIn.readAllDoubles());
        StdOut.printf("%.6f %.6f distance=%.6f%n", pair[0], pair[1], pair[2]);
    }
}
