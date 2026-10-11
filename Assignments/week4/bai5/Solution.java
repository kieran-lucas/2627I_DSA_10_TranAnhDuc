import java.util.Scanner;

public class Solution {
    public static void insertionSort2(int n, int[] arr) {
        for (int i = 1; i < n; i++) {
            int value = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > value) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = value;
            printArray(arr);
        }
    }

    private static void printArray(int[] arr) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) {
                line.append(' ');
            }
            line.append(arr[i]);
        }
        System.out.println(line);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
        }
        insertionSort2(n, arr);
        input.close();
    }
}
