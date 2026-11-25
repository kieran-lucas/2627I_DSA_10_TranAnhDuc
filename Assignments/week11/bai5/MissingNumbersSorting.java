import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;

public class MissingNumbersSorting {
    public static void main(String[] args) throws IOException {
        BufferedInputStream input = new BufferedInputStream(System.in);
        int[] a = readArray(input);
        int[] b = readArray(input);
        Arrays.sort(a);
        Arrays.sort(b);

        StringBuilder result = new StringBuilder();
        int i = 0;
        int j = 0;
        while (j < b.length) {
            int value = b[j];
            while (i < a.length && a[i] < value) {
                i++;
            }

            int start = j;
            do {
                j++;
            } while (j < b.length && b[j] == value);

            int difference = j - start;
            while (i < a.length && a[i] == value) {
                i++;
                difference--;
            }
            if (difference > 0) {
                if (result.length() > 0) {
                    result.append(' ');
                }
                result.append(value);
            }
        }
        System.out.println(result);
    }

    private static int[] readArray(BufferedInputStream input) throws IOException {
        int[] values = new int[nextInt(input)];
        for (int i = 0; i < values.length; i++) {
            values[i] = nextInt(input);
        }
        return values;
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
