public class SelectionSort {
    public static void sort(int[] values) {
        for (int i = 0; i < values.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < values.length; j++) {
                if (values[j] < values[min]) {
                    min = j;
                }
            }
            int temp = values[i];
            values[i] = values[min];
            values[min] = temp;
        }
    }
}
