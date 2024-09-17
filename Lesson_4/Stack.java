import java.util.ArrayList;
import java.util.List;

public class Stack<T> {
    private List<T> stack;

    public Stack()
    {
        stack = new ArrayList<>();
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

        return val;
    }

    public void push(T val)
    {
        stack.add(val);
    }

    public T peek()
    {
        if (stack.isEmpty()) {
            return null;
        }

        return stack.get(stack.size() - 1);
    }
}

