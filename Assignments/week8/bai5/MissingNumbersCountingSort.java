import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;

public class MissingNumbersCountingSort {
    public static void main(String[] args) throws IOException {
        BufferedInputStream input = new BufferedInputStream(System.in);
        int[] difference = new int[101];

        int n = nextInt(input);
        for (int i = 0; i < n; i++) {
            int value = nextInt(input);
            difference[Math.floorMod(value, difference.length)]--;
        }

        int m = nextInt(input);
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            int value = nextInt(input);
            difference[Math.floorMod(value, difference.length)]++;
            min = Math.min(min, value);
            max = Math.max(max, value);
        }

        StringBuilder result = new StringBuilder();
        int offset = Math.floorMod(min, difference.length);
        long range = (long) max - min;
        for (int i = 0; i <= range; i++) {
            if (difference[(offset + i) % difference.length] > 0) {
                if (result.length() > 0) {
                    result.append(' ');
                }
                result.append(min + i);
            }
        }
        System.out.println(result);
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
