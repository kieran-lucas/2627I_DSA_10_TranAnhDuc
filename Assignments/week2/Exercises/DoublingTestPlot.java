import edu.princeton.cs.algs4.*;

public class DoublingTestPlot {

    public static double timeTrial(int n) {
        int max = 1000000;
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniformInt(-max, max);
        }

        Stopwatch timer = new Stopwatch();
        ThreeSum.count(a);

        return timer.elapsedTime();
    }

    public static void main(String[] args) {

        StdDraw.setXscale(0, 4000);
        StdDraw.setYscale(0, 2);

        StdDraw.setPenRadius(0.01);

        for (int n = 250; n <= 4000; n *= 2) {
            double time = timeTrial(n);

            StdOut.printf("%7d %7.3f\n", n, time);

            StdDraw.point(n, time);
        }
    }
}
