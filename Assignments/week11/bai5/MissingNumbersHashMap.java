import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MissingNumbersHashMap {
    public static void main(String[] args) throws IOException {
        BufferedInputStream input = new BufferedInputStream(System.in);
        HashMap<Integer, Integer> difference = new HashMap<>();

        int n = nextInt(input);
        for (int i = 0; i < n; i++) {
            difference.merge(nextInt(input), -1, Integer::sum);
        }
        int m = nextInt(input);
        for (int i = 0; i < m; i++) {
            difference.merge(nextInt(input), 1, Integer::sum);
        }

        ArrayList<Integer> missing = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : difference.entrySet()) {
            if (entry.getValue() > 0) {
                missing.add(entry.getKey());
            }
        }
        Collections.sort(missing);

        StringBuilder result = new StringBuilder();
        for (int value : missing) {
            if (result.length() > 0) {
                result.append(' ');
            }
            result.append(value);
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
