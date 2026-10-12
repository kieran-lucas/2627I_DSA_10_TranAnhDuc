import edu.princeton.cs.algs4.In;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Locale;

public class InsertionBenchmark {
    public static void main(String[] args) throws Exception {
        Path data = DownloadAlgs4Data.download();
        String[] types = {"file", "random", "ascending", "descending", "equal"};
        int[] sizes = {1000, 2000, 4000, 8000};
        System.out.println("Java: " + System.getProperty("java.version"));
        System.out.println("VM: " + System.getProperty("java.vm.name"));
        System.out.println("Warming up the JVM...");
        for (int run = 0; run < 500; run++) {
            for (String type : types) {
                if (!type.equals("file")) {
                    int[] values = BenchmarkData.create(type, 1000, run);
                    InsertionSort.sort(values);
                    BenchmarkData.check(values);
                }
            }
        }
        System.out.printf("%-12s %8s %6s %16s %14s %10s%n",
                "Type", "N", "Runs", "Total (ns)", "Mean (ns)", "Mean (ms)");
        for (String type : types) {
            for (int size : sizes) {
                int[] fileValues = null;
                if (type.equals("file")) {
                    In input = new In(data.resolve(size / 1000 + "Kints.txt").toString());
                    fileValues = input.readAllInts();
                    input.close();
                    if (fileValues.length != size) {
                        throw new IllegalStateException("Unexpected file size");
                    }
                }
                for (int warmup = 0; warmup < 20; warmup++) {
                    int[] values = fileValues == null
                            ? BenchmarkData.create(type, size, warmup + 10000) : fileValues.clone();
                    InsertionSort.sort(values);
                    BenchmarkData.check(values);
                }
                int repeats = 10;
                if (type.equals("ascending") || type.equals("equal")) {
                    repeats = 100;
                }
                long total = 0;
                for (int run = 0; run < repeats; run++) {
                    int[] values = fileValues == null
                            ? BenchmarkData.create(type, size, run) : fileValues.clone();
                    int[] expected = values.clone();
                    Arrays.sort(expected);
                    long start = System.nanoTime();
                    InsertionSort.sort(values);
                    long elapsed = System.nanoTime() - start;
                    BenchmarkData.check(values);
                    if (!Arrays.equals(values, expected)) {
                        throw new IllegalStateException("Incorrect sorted result");
                    }
                    total += elapsed;
                }
                double average = (double) total / repeats;
                System.out.printf(Locale.US, "%-12s %8d %6d %16d %14.2f %10.6f%n",
                        type, size, repeats, total, average, average / 1000000.0);
            }
        }
        System.out.println("All 920 measured sorts matched Arrays.sort.");
    }
}
