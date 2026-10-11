import java.io.PrintWriter;
import java.util.Locale;

public class InsertionBenchmark {
    public static void main(String[] args) throws Exception {
        String[] types = {"file", "random", "ascending", "descending", "equal"};
        int[] sizes = {1000, 2000, 4000, 8000};
        PrintWriter raw = new PrintWriter("build/insertion-runs.csv");
        PrintWriter summary = new PrintWriter("build/insertion-summary.csv");
        raw.println("type,size,run,nanoseconds");
        summary.println("type,size,runs,average_ns");
        for (String type : types) {
            for (int size : sizes) {
                for (int warmup = 0; warmup < 20; warmup++) {
                    int[] values = BenchmarkData.create(type, size, warmup);
                    InsertionSort.sort(values);
                    BenchmarkData.check(values);
                }
                int repeats = 10;
                if (type.equals("ascending") || type.equals("equal")) {
                    repeats = 100;
                }
                long total = 0;
                for (int run = 0; run < repeats; run++) {
                    int[] values = BenchmarkData.create(type, size, run);
                    long start = System.nanoTime();
                    InsertionSort.sort(values);
                    long elapsed = System.nanoTime() - start;
                    BenchmarkData.check(values);
                    total += elapsed;
                    raw.println(type + "," + size + "," + (run + 1) + "," + elapsed);
                }
                double average = (double) total / repeats;
                summary.printf(Locale.US, "%s,%d,%d,%.2f%n", type, size, repeats, average);
            }
        }
        raw.close();
        summary.close();
    }
}
