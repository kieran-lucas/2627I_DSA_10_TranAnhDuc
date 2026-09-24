import java.util.Stack;

public class w3_tailop_25021739 {
    static int priority(char op) {
        if (op == '+' || op == '-') {
            return 1 ;
        }
        if (op == '*' || op == '/') {
            return 2;
        }
        return 0;
    }
    static String convert(String infix) {
        Stack<Character> stack = new Stack<>();
        StringBuilder postfix = new StringBuilder();

        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);

            if (c == ' ') {
                continue;
            }

            if (Character.isLetterOrDigit(c)) {
                postfix.append(c).append(' ');
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop()).append(' ');
                }

                stack.pop();
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!stack.isEmpty()
                        && stack.peek() != '('
                        && priority(stack.peek()) >= priority(c)) {
                    postfix.append(stack.pop()).append(' ');
                }
                stack.push(c);
            }
        }
        while (!stack.isEmpty()) {
            postfix.append(stack.pop()).append(' ');
        }
        return postfix.toString();
    }
    public static void main(String[] args) {
        String expression = "A + B * C - D / E";
        System.out.println("Infix: " + expression);
        System.out.println("Postfix: " + convert(expression));
    }

}