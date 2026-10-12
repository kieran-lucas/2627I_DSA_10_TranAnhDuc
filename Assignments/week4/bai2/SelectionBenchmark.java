import edu.princeton.cs.algs4.In;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

public class SelectionBenchmark {
    private static final String[] TYPES = {"file", "random", "ascending", "descending", "equal"};
    private static final int[] SIZES = {1000, 2000, 4000, 8000};
    private static final int BLOCKS = 5;
    private static final int RUNS_PER_BLOCK = 20;
    private static final int RUNS = BLOCKS * RUNS_PER_BLOCK;

    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
        Path data = Path.of("build", "princeton-data", "algs4-data");
        if (!Files.isRegularFile(data.resolve("1Kints.txt"))) {
            data = Path.of("algs4-data");
        }
        int[][] files = new int[SIZES.length][];
        for (int i = 0; i < SIZES.length; i++) {
            In input = new In(data.resolve(SIZES[i] / 1000 + "Kints.txt").toString());
            files[i] = input.readAllInts();
            input.close();
            if (files[i].length != SIZES[i]) {
                throw new IllegalStateException("Unexpected file size");
            }
        }
        System.out.println("Existing data: " + data.toAbsolutePath());
        System.out.println("Java: " + System.getProperty("java.version"));
        System.out.println("VM: " + System.getProperty("java.vm.name"));
        System.out.println("Input seed: 20261011L + N * 100L + run, run = 0..99");
        testEdgeCases();
        System.out.println("Warming up both algorithms...");
        for (int run = 0; run < 200; run++) {
            for (String type : TYPES) {
                int[] original = type.equals("file")
                        ? files[0] : BenchmarkData.create(type, 1000, 10000 + run);
                runPair(original, run % 2 == 0);
            }
        }
        int cases = TYPES.length * SIZES.length;
        int firstCase = 0;
        int lastCase = cases;
        if (args.length > 0 && args[0].equals("descending")) {
            firstCase = 3 * SIZES.length;
            lastCase = firstCase + SIZES.length;
        }
        for (int c = firstCase; c < lastCase; c++) {
            for (int run = 0; run < 20; run++) {
                int[] original = createInput(c, 20000 + run, files);
                runPair(original, run % 2 == 0);
            }
        }
        long[][] insertionTimes = new long[cases][RUNS];
        long[][] selectionTimes = new long[cases][RUNS];
        int[] order = new int[lastCase - firstCase];
        for (int i = 0; i < order.length; i++) {
            order[i] = firstCase + i;
        }
        Random orderRandom = new Random(20261011L);
        for (int block = 0; block < BLOCKS; block++) {
            for (int i = order.length - 1; i > 0; i--) {
                int j = orderRandom.nextInt(i + 1);
                int temp = order[i];
                order[i] = order[j];
                order[j] = temp;
            }
            for (int c : order) {
                for (int offset = 0; offset < RUNS_PER_BLOCK; offset++) {
                    int run = block * RUNS_PER_BLOCK + offset;
                    int[] original = createInput(c, run, files);
                    long[] elapsed = runPair(original, run % 2 == 0);
                    insertionTimes[c][run] = elapsed[0];
                    selectionTimes[c][run] = elapsed[1];
                }
            }
            System.out.println("Completed block " + (block + 1) + "/" + BLOCKS);
        }
        System.out.println("All " + order.length * RUNS * 2 + " measured sorts matched Arrays.sort.");
        System.out.println("Means are in ns; S/I = Selection mean / Insertion mean.");
        System.out.println("Doubling = mean at N / mean at N/2 for the same algorithm and type.");
        System.out.printf("%-12s %6s %5s %15s %15s %12s %12s %12s%n",
                "Type", "N", "Runs", "Insertion_ns", "Selection_ns", "S/I", "I_doubling", "S_doubling");
        for (int c = firstCase; c < lastCase; c++) {
            double insertionMean = mean(insertionTimes[c]);
            double selectionMean = mean(selectionTimes[c]);
            String insertionDoubling = "-";
            String selectionDoubling = "-";
            if (c % SIZES.length != 0) {
                insertionDoubling = String.format("%.6f", insertionMean / mean(insertionTimes[c - 1]));
                selectionDoubling = String.format("%.6f", selectionMean / mean(selectionTimes[c - 1]));
            }
            System.out.printf("%-12s %6d %5d %15.2f %15.2f %12.6f %12s %12s%n",
                    TYPES[c / SIZES.length], SIZES[c % SIZES.length], RUNS,
                    insertionMean, selectionMean, selectionMean / insertionMean,
                    insertionDoubling, selectionDoubling);
        }
        System.out.println("Descending variation: sample CV = sample standard deviation / mean * 100.");
        System.out.println("Block_min/max are the smallest/largest mean across five blocks of 20 runs.");
        System.out.printf("%6s %-10s %12s %12s %10s %15s %15s%n",
                "N", "Algorithm", "Min_ns", "Max_ns", "CV_percent", "Block_min_ns", "Block_max_ns");
        for (int i = 0; i < SIZES.length; i++) {
            int c = 3 * SIZES.length + i;
            printVariation(SIZES[i], "Insertion", insertionTimes[c]);
            printVariation(SIZES[i], "Selection", selectionTimes[c]);
        }
    }

    private static int[] createInput(int c, int run, int[][] files) {
        int sizeIndex = c % SIZES.length;
        String type = TYPES[c / SIZES.length];
        if (type.equals("file")) {
            return files[sizeIndex];
        }
        return BenchmarkData.create(type, SIZES[sizeIndex], run);
    }

    private static long[] runPair(int[] original, boolean insertionFirst) {
        int[] expected = original.clone();
        Arrays.sort(expected);
        int[] insertion = original.clone();
        int[] selection = original.clone();
        long insertionTime;
        long selectionTime;
        if (insertionFirst) {
            insertionTime = timeInsertion(insertion);
            selectionTime = timeSelection(selection);
        } else {
            selectionTime = timeSelection(selection);
            insertionTime = timeInsertion(insertion);
        }
        check(insertion, expected);
        check(selection, expected);
        return new long[] {insertionTime, selectionTime};
    }

    private static long timeInsertion(int[] values) {
        long start = System.nanoTime();
        InsertionSort.sort(values);
        return System.nanoTime() - start;
    }

    private static long timeSelection(int[] values) {
        long start = System.nanoTime();
        SelectionSort.sort(values);
        return System.nanoTime() - start;
    }

    private static void check(int[] actual, int[] expected) {
        if (!Arrays.equals(actual, expected)) {
            throw new IllegalStateException("Incorrect sorted result");
        }
    }

    private static void testEdgeCases() {
        int[][] inputs = {
                {}, {5}, {2, 1}, {1, 2, 3}, {3, 2, 1}, {7, 7, 7},
                {Integer.MIN_VALUE, 0, Integer.MAX_VALUE, -1, 0},
                {2, -3, 2, 1, -3}
        };
        for (int[] input : inputs) {
            int[] expected = input.clone();
            Arrays.sort(expected);
            int[] insertion = input.clone();
            int[] selection = input.clone();
            InsertionSort.sort(insertion);
            SelectionSort.sort(selection);
            check(insertion, expected);
            check(selection, expected);
        }
        System.out.println("Passed 16 edge-case checks against Arrays.sort.");
    }

    private static double mean(long[] times) {
        long total = 0;
        for (long time : times) {
            total += time;
        }
        return (double) total / times.length;
    }

    private static void printVariation(int size, String algorithm, long[] times) {
        long min = times[0];
        long max = times[0];
        double average = mean(times);
        double squaredDifferences = 0;
        for (long time : times) {
            if (time < min) {
                min = time;
            }
            if (time > max) {
                max = time;
            }
            double difference = time - average;
            squaredDifferences += difference * difference;
        }
        double cv = Math.sqrt(squaredDifferences / (times.length - 1)) / average * 100;
        double blockMin = Double.POSITIVE_INFINITY;
        double blockMax = 0;
        for (int block = 0; block < BLOCKS; block++) {
            long total = 0;
            for (int offset = 0; offset < RUNS_PER_BLOCK; offset++) {
                total += times[block * RUNS_PER_BLOCK + offset];
            }
            double blockMean = (double) total / RUNS_PER_BLOCK;
            if (blockMean < blockMin) {
                blockMin = blockMean;
            }
            if (blockMean > blockMax) {
                blockMax = blockMean;
            }
        }
        System.out.printf("%6d %-10s %12d %12d %10.3f %15.2f %15.2f%n",
                size, algorithm, min, max, cv, blockMin, blockMax);
    }
}

