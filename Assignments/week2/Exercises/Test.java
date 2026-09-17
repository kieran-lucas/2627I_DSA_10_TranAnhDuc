import edu.princeton.cs.algs4.*;

public class Test {
    public static int rank(int key, int[] a) {
        int low = 0;
        int high = a.length - 1;
        int result = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] < key) {
                low = mid + 1;
            }
            else if (a[mid] > key) {
                high = mid - 1;
            }
            else {
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
        int key =  StdIn.readInt();
        StdOut.print(rank(key, a));
    }
}
