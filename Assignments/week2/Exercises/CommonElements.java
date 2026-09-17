import edu.princeton.cs.algs4.*;
public class CommonElements {
    public static void printCommon(int[] a, int[] ) {
        int i = 0;
        int j = 0;
    }
    while (i < a.length && j < b.length) {
        if (a[i] < b[j]) {
            i++;
        }
        else if (a[i] > b[j]) {
            j++;
        }
        else {
            StdOut.print(a[i] + " ");
            i++;
            j++;
        }
    }
    public static void main(String[] args) {
        int n = StdIn.readIn();
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdIn.readIn();
        }
        for (int i = 0; i < n; i++ ) {
            b[i] = StdIn.readIn();
        }
    }
    printCommon(a, b);

}
