public class Selection {
    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exch(a, i, min);
        }
        private static boolean less(Comparable u, Comparable v) {
            return u.compareTo(u, v) < 0;
        }
        private static void exch(Comparable[] a, i, j) {
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;

        }
    }
}