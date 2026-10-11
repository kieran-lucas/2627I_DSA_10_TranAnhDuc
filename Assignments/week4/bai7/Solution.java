import java.util.Scanner;

public class Solution {
    public static int[] countingSort(int[] arr) {
        int[] count = new int[100];
        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
        }
        int[] count = countingSort(arr);
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < count.length; i++) {
            if (i > 0) {
                line.append(' ');
            }
            line.append(count[i]);
        }
        System.out.println(line);
        input.close();
    }
}
