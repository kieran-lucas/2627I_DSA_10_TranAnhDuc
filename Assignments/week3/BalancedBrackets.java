import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BalancedBrackets {
    private static boolean isBalanced(String text) {
        Stack<Character> openings = new Stack<>();
        for (int i = 0; i < text.length(); i++) {
            char bracket = text.charAt(i);
            if (bracket == '(' || bracket == '[' || bracket == '{') {
                openings.push(bracket);
            } else {
                if (openings.isEmpty()) {
                    return false;
                }
                char opening = openings.pop();
                if (bracket == ')' && opening != '('
                        || bracket == ']' && opening != '['
                        || bracket == '}' && opening != '{') {
                    return false;
                }
            }
        }
        return openings.isEmpty();
    }

    public static void main(String[] args) {
        int count = StdIn.readInt();
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < count; i++) {
            output.append(isBalanced(StdIn.readString()) ? "YES" : "NO").append('\n');
        }
        StdOut.print(output);
    }
}
