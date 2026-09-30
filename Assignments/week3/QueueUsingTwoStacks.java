import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QueueUsingTwoStacks {
    private static class TwoStackQueue {
        private final Stack<Integer> incoming = new Stack<>();
        private final Stack<Integer> outgoing = new Stack<>();

        void enqueue(int value) {
            incoming.push(value);
        }

        private void prepareFront() {
            if (outgoing.isEmpty()) {
                while (!incoming.isEmpty()) {
                    outgoing.push(incoming.pop());
                }
            }
        }

        int dequeue() {
            prepareFront();
            return outgoing.pop();
        }

        int peek() {
            prepareFront();
            return outgoing.peek();
        }
    }

    public static void main(String[] args) {
        int count = StdIn.readInt();
        TwoStackQueue queue = new TwoStackQueue();
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < count; i++) {
            int operation = StdIn.readInt();
            if (operation == 1) {
                queue.enqueue(StdIn.readInt());
            } else if (operation == 2) {
                queue.dequeue();
            } else if (operation == 3) {
                output.append(queue.peek()).append('\n');
            }
        }
        StdOut.print(output);
    }
}
