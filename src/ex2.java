import java.util.EmptyStackException;
import java.util.Stack;

public class ex2 {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(ownTop(stack));
        System.out.println(ownIsEmpty(stack));
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(ownIsEmpty(stack));
    }

    public static int ownTop(Stack<Integer> stack) {
        int top = stack.pop();
        stack.push(top);
        return top;
    }

    public static boolean ownIsEmpty(Stack<Integer> stack) {
        int top;
        try {
            top = stack.pop();
        } catch (EmptyStackException e) {
            return true;
        }
        stack.push(top);
        return false;
    }
}