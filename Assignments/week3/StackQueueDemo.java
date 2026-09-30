import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

public class StackQueueDemo {
    private static class Node {
        String item;
        Node next;

        Node(String item) {
            this.item = item;
        }
    }

    private static class LinkedStack {
        private Node first;

        boolean isEmpty() {
            return first == null;
        }

        void push(String item) {
            Node node = new Node(item);
            node.next = first;
            first = node;
        }

        String pop() {
            if (isEmpty()) {
                throw new NoSuchElementException();
            }
            String item = first.item;
            first = first.next;
            return item;
        }
    }

    private static class LinkedQueue {
        private Node first;
        private Node last;

        boolean isEmpty() {
            return first == null;
        }

        void enqueue(String item) {
            Node node = new Node(item);
            if (isEmpty()) {
                first = node;
            } else {
                last.next = node;
            }
            last = node;
        }

        String dequeue() {
            if (isEmpty()) {
                throw new NoSuchElementException();
            }
            String item = first.item;
            first = first.next;
            if (first == null) {
                last = null;
            }
            return item;
        }
    }

    private static void addOutput(StringBuilder output, String item) {
        if (output.length() > 0) {
            output.append(' ');
        }
        output.append(item);
    }

    public static void main(String[] args) {
        LinkedStack linkedStack = new LinkedStack();
        LinkedQueue linkedQueue = new LinkedQueue();
        edu.princeton.cs.algs4.Stack<String> referenceStack = new edu.princeton.cs.algs4.Stack<>();
        edu.princeton.cs.algs4.Queue<String> referenceQueue = new edu.princeton.cs.algs4.Queue<>();
        StringBuilder stackOutput = new StringBuilder();
        StringBuilder queueOutput = new StringBuilder();

        while (!StdIn.isEmpty()) {
            String token = StdIn.readString();
            if (token.equals("-")) {
                if (!linkedStack.isEmpty()) {
                    String item = linkedStack.pop();
                    if (!item.equals(referenceStack.pop())) {
                        throw new IllegalStateException();
                    }
                    addOutput(stackOutput, item);
                }
                if (!linkedQueue.isEmpty()) {
                    String item = linkedQueue.dequeue();
                    if (!item.equals(referenceQueue.dequeue())) {
                        throw new IllegalStateException();
                    }
                    addOutput(queueOutput, item);
                }
            } else {
                linkedStack.push(token);
                linkedQueue.enqueue(token);
                referenceStack.push(token);
                referenceQueue.enqueue(token);
            }
        }

        StdOut.println("Stack: " + stackOutput);
        StdOut.println("Queue: " + queueOutput);
    }
}
