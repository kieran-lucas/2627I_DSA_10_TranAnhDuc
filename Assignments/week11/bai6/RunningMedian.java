import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.TreeSet;

public class RunningMedian {
    private static final class Entry {
        final int value;
        final int index;

        Entry(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedInputStream input = new BufferedInputStream(System.in);
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        Comparator<Entry> order = (first, second) -> {
            int comparison = Integer.compare(first.value, second.value);
            return comparison != 0 ? comparison : Integer.compare(first.index, second.index);
        };
        TreeSet<Entry> lower = new TreeSet<>(order);
        TreeSet<Entry> upper = new TreeSet<>(order);

        int n = nextInt(input);
        for (int i = 0; i < n; i++) {
            Entry entry = new Entry(nextInt(input), i);
            if (lower.isEmpty() || order.compare(entry, lower.last()) <= 0) {
                lower.add(entry);
            } else {
                upper.add(entry);
            }

            if (lower.size() > upper.size() + 1) {
                upper.add(lower.pollLast());
            } else if (upper.size() > lower.size()) {
                lower.add(upper.pollFirst());
            }

            long middle = lower.last().value;
            long twiceMedian = lower.size() == upper.size()
                    ? middle + upper.first().value
                    : 2 * middle;
            writeMedian(output, twiceMedian);
        }
        output.flush();
    }

    private static void writeMedian(BufferedWriter output, long twiceMedian) throws IOException {
        if (twiceMedian < 0) {
            output.write('-');
            twiceMedian = -twiceMedian;
        }
        output.write(Long.toString(twiceMedian / 2));
        output.write((twiceMedian % 2 == 0) ? ".0\n" : ".5\n");
    }

    private static int nextInt(BufferedInputStream input) throws IOException {
        int ch;
        do {
            ch = input.read();
        } while (ch != -1 && ch <= ' ');
        if (ch == -1) {
            throw new EOFException();
        }

        boolean negative = ch == '-';
        if (negative) {
            ch = input.read();
        }
        long value = 0;
        while (ch >= '0' && ch <= '9') {
            value = value * 10 + ch - '0';
            ch = input.read();
        }
        return (int) (negative ? -value : value);
    }
}
