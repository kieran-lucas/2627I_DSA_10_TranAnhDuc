import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class EqualStacks {
    private static long readStack(Queue<Integer> stack, int count) {
        long height = 0;
        for (int i = 0; i < count; i++) {
            int cylinder = StdIn.readInt();
            stack.enqueue(cylinder);
            height += cylinder;
        }
        return height;
    }

    public static void main(String[] args) {
        int firstCount = StdIn.readInt();
        int secondCount = StdIn.readInt();
        int thirdCount = StdIn.readInt();
        Queue<Integer> first = new Queue<>();
        Queue<Integer> second = new Queue<>();
        Queue<Integer> third = new Queue<>();
        long firstHeight = readStack(first, firstCount);
        long secondHeight = readStack(second, secondCount);
        long thirdHeight = readStack(third, thirdCount);

        while (firstHeight != secondHeight || secondHeight != thirdHeight) {
            if (firstHeight >= secondHeight && firstHeight >= thirdHeight) {
                firstHeight -= first.dequeue();
            } else if (secondHeight >= firstHeight && secondHeight >= thirdHeight) {
                secondHeight -= second.dequeue();
            } else {
                thirdHeight -= third.dequeue();
            }
        }

        StdOut.println(firstHeight);
    }
}
