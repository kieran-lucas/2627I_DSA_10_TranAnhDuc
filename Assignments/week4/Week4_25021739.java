import edu.princeton.cs.algs4.*;
public class Week4_25021739 {
    public static int findHIndex(int[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (a[i] > a[j]) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
            if (N - i <= a[i]) {
                    return N - i;
            }
        }
        return 0;
    }
    public static void main(String[] args) {
    int N = StdIn.readInt();
    int[] a = new int[N];
    for (int i = 0; i < N; i++) {
        a[i] = StdIn.readInt();
        }
    StdOut.println(findHIndex(a));
    }
}