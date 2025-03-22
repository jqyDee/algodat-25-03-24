import java.util.Stack;

public class ex1 {
    public static String str = "()()()";

    public static void main(String[] args) {
        Stack<Character> stack = new Stack<>();

        pushStringOntoStack(stack, str);
        System.out.println("Iterative: str = \"" + str + "\"; checkParensIter = " + checkParensIter(stack));

        pushStringOntoStack(stack, str);
        System.out.println("Recursive: str = \"" + str + "\"; checkParensRec  = " + checkParensRec(stack, 0));

        pushStringOntoStack(stack, str);
        System.out.println("stack before = " + stack);
        System.out.println("RecurRest: str = \"" + str + "\"; checkParensRecRestore  = " + checkParensRecRestore(stack));
        System.out.println("stack after = " + stack);
    }

    public static boolean checkParensIter(Stack<Character> stack) {
        int currCounter = 0;

        while (!stack.empty()) {
            currCounter += switch (stack.pop()) {
                case '(' -> 1;
                case ')' -> -1;
                default -> 0;
            };

            if (currCounter < 0) return false;
        }

        return currCounter == 0;
    }

    public static boolean checkParensRec(Stack<Character> stack, int currCounter) {
        if (!stack.empty()) {
            currCounter += switch (stack.pop()) {
                case '(' -> 1;
                case ')' -> -1;
                default -> 0;
            };
            if (currCounter < 0) return false;

            return checkParensRec(stack, currCounter);
        }
        return currCounter == 0;
    }

    public static boolean checkParensRecRestore(Stack<Character> stack) {
        return checkParensRecRestore(stack, 0) == 0;
    }

    private static int checkParensRecRestore(Stack<Character> stack, int counter) {
        if (stack.empty()) {
            return counter;
        }

        char top = stack.pop();

        counter += switch (top) {
            case '(' -> 1;
            case ')' -> -1;
            default -> 0;
        };

        if (counter < 0) {
            stack.push(top);
            return -1;
        }

        int result = checkParensRecRestore(stack, counter);

        stack.push(top);

        return result;
    }

    public static void pushStringOntoStack(Stack<Character> stack, String str) {
        stack.clear();
        for (int i = str.length() - 1; i >= 0; i--) {
            stack.push(str.charAt(i));
        }
    }
}