import java.util.ArrayList;
import java.util.List;

public class MinStack<T> {
    private List<T> stack;
    private Stack<T> minStack;

    public MinStack()
    {
        stack = new ArrayList<>();
        minStack = new Stack<>();
    }

    public int size()
    {
        return stack.size();
    }

    public T pop()
    {
        if (stack.isEmpty()) {
            return null;
        }

        T val = stack.get(stack.size() - 1);
        stack.remove(stack.size() - 1);

        if (minStack.peek() != null && (int) val > (int) minStack.peek()) {
            T tmp = minStack.pop();
            minStack.pop();
            minStack.push(tmp);
        } else {
            minStack.pop();
        }

        return val;
    }

    public void push(T val)
    {
        stack.add(val);

        if (minStack.peek() != null && (int) val > (int) minStack.peek()) {
            T tmp = minStack.pop();
            minStack.push(val);
            minStack.push(tmp);
        } else {
            minStack.push(val);
        }
    }

    public T peek()
    {
        if (stack.isEmpty()) {
            return null;
        }

        return stack.get(stack.size() - 1);
    }

    public T minValue() {
        return minStack.peek();
    }
}

