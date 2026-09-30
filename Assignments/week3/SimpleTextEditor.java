import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SimpleTextEditor {
    private static class Edit {
        final int appendedLength;
        final String deletedText;

        Edit(int appendedLength, String deletedText) {
            this.appendedLength = appendedLength;
            this.deletedText = deletedText;
        }
    }

    public static void main(String[] args) {
        int count = StdIn.readInt();
        StringBuilder text = new StringBuilder();
        StringBuilder output = new StringBuilder();
        Stack<Edit> history = new Stack<>();

        for (int i = 0; i < count; i++) {
            int operation = StdIn.readInt();
            if (operation == 1) {
                String addition = StdIn.readString();
                text.append(addition);
                history.push(new Edit(addition.length(), null));
            } else if (operation == 2) {
                int length = StdIn.readInt();
                int start = text.length() - length;
                history.push(new Edit(0, text.substring(start)));
                text.setLength(start);
            } else if (operation == 3) {
                output.append(text.charAt(StdIn.readInt() - 1)).append('\n');
            } else if (operation == 4) {
                Edit edit = history.pop();
                if (edit.deletedText == null) {
                    text.setLength(text.length() - edit.appendedLength);
                } else {
                    text.append(edit.deletedText);
                }
            }
        }

        StdOut.print(output);
    }
}
