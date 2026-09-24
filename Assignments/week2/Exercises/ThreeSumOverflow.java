import edu.princeton.cs.algs4.*;

public class ThreeSumOverflow {
    public static long count(int[] a) {
        int n = a.length;
        long count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if ((long) a[i] + a[j] + a[k] == 0) {
                        count ++;
                    }
                }
            }
        }
        return count;
    }
    public static void main(String[] args){
        int n = StdIn.readInt();
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = StdIn.readInt();
        }
        StdOut.println(count(a));
    }

}

