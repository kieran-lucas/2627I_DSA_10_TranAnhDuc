import java.util.Scanner;

public class Solution {
    public static void insertIntoSorted(int[] arr) {
        int value = arr[arr.length - 1];
        int j = arr.length - 2;
        while (j >= 0 && arr[j] > value) {
            arr[j + 1] = arr[j];
            printArray(arr);
            j--;
        }
        arr[j + 1] = value;
        printArray(arr);
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
        insertIntoSorted(arr);
        input.close();
    }
}
