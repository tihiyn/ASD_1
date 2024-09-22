import java.util.ArrayList;
import java.util.List;

public class AverageStack<T> {
    private List<T> stack;
    private int sum;

    public AverageStack()
    {
        stack = new ArrayList<>();
        sum = 0;
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
        sum -= (int) val;

        return val;
    }

    public void push(T val)
    {
        stack.add(val);
        sum += (int) val;
    }

    public T peek()
    {
        if (stack.isEmpty()) {
            return null;
        }

        return stack.get(stack.size() - 1);
    }

    public double average() {
        if (stack.isEmpty()) {
            return 0;
        }

        return (double) sum / stack.size();
    }
}

