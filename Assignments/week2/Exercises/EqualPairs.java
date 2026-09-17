import edu.princeton.cs.algs4.*;
import java.util.Arrays;

public class EqualPairs {

    public static long count(int[] a) {
        Arrays.sort(a);

        long count = 0;
        int i = 0;

        while (i < a.length) {
            int j = i + 1;

            while (j < a.length && a[j] == a[i]) {
                j++;
            }

            long n = j - i;
            count += n * (n - 1) / 2;

            i = j;
        }

        return count;
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = StdIn.readInt();
        }

        StdOut.println(count(a));
    }
}
