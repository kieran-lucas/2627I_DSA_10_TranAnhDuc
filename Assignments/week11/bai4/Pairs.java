import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.HashSet;

public class Pairs {
    public static void main(String[] args) throws IOException {
        BufferedInputStream input = new BufferedInputStream(System.in);
        int n = nextInt(input);
        int k = nextInt(input);
        HashSet<Integer> values = new HashSet<>(n);

        for (int i = 0; i < n; i++) {
            values.add(nextInt(input));
        }

        int pairs = 0;
        for (int value : values) {
            long target = (long) value + k;
            if (target >= Integer.MIN_VALUE && target <= Integer.MAX_VALUE
                    && values.contains((int) target)) {
                pairs++;
            }
        }
        System.out.println(pairs);
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
