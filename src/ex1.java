import java.util.Stack;

public class ex1 {
    public static String str = "()()()";

    public static void main(String[] args) {
        Stack<Character> stack = new Stack<>();
        Stack<Character> saveStack = new Stack<>();

        pushStringOntoStack(stack, str);
        System.out.println("Iterative: str = \"" + str + "\"; checkParensIter = " + checkParensIter(stack));

        pushStringOntoStack(stack, str);
        System.out.println("Recursive: str = \"" + str + "\"; checkParensRec  = " + checkParensRec(stack, 0));

        pushStringOntoStack(stack, str);
        System.out.println("stack before = " + stack);
        System.out.println("RecurRest: str = \"" + str + "\"; checkParensRecRestore  = " + checkParensRecRestore(stack, saveStack, 0, false));
        System.out.println("stack after = " + stack);
    }

    public static boolean checkParensIter(Stack<Character> stack) {
        int counter = 0;

        while (!stack.empty()) {
            counter += switch (stack.pop()) {
                case '(' -> 1;
                case ')' -> -1;
                default -> 0;
            };

            if (counter < 0) return false;
        }

        return counter == 0;
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

    public static boolean checkParensRecRestore(Stack<Character> stack, Stack<Character> saveStack, int currCounter, boolean resultKnown) {
        if (!resultKnown) {
            char top = stack.pop();
            currCounter += switch (top) {
                case '(' -> 1;
                case ')' -> -1;
                default -> 0;
            };

            saveStack.push(top);
            resultKnown = stack.empty() || currCounter < 0;
            return checkParensRecRestore(stack, saveStack, currCounter, resultKnown);
        } else {
            if (!saveStack.empty()) {
                stack.push(saveStack.pop());
                return checkParensRecRestore(stack, saveStack, currCounter, resultKnown);
            }
            return currCounter == 0;
        }
    }

    public static void pushStringOntoStack(Stack<Character> stack, String str) {
        stack.clear();
        for (int i = str.length() - 1; i >= 0; i--) {
            stack.push(str.charAt(i));
        }
    }
}