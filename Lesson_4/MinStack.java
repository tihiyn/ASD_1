import java.util.ArrayList;
import java.util.List;

public class MinStack {
    private List<Integer> stack;
    private Stack<Integer> minStack;

    public MinStack()
    {
        stack = new ArrayList<>();
        minStack = new Stack<>();
    }

    public int size()
    {
        return stack.size();
    }

    public Integer pop()
    {
        if (stack.isEmpty()) {
            return null;
        }

        Integer val = stack.get(stack.size() - 1);
        stack.remove(stack.size() - 1);

        if (minStack.peek() != null && val > minStack.peek()) {
            Integer tmp = minStack.pop();
            minStack.pop();
            minStack.push(tmp);
        } else {
            minStack.pop();
        }

        return val;
    }

    public void push(Integer val)
    {
        stack.add(val);

        if (minStack.peek() != null && val > minStack.peek()) {
            Integer tmp = minStack.pop();
            minStack.push(val);
            minStack.push(tmp);
        } else {
            minStack.push(val);
        }
    }

    public Integer peek()
    {
        if (stack.isEmpty()) {
            return null;
        }

        return stack.get(stack.size() - 1);
    }

    public Integer minValue() {
        return minStack.peek();
    }
}

