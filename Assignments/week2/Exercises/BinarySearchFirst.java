import edu.princeton.cs.algs4.*;

public class BinarySearchFirst {

    public static int rank(int key, int[] a) {
        int low = 0;
        int high = a.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (key < a[mid]) {
                high = mid - 1;
            } else if (key > a[mid]) {
                low = mid + 1;
            } else {
                result = mid;
                high = mid - 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = StdIn.readInt();
        }

        int key = StdIn.readInt();

        StdOut.print(rank(key, a));
    }
}
