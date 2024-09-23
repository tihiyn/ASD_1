import java.util.ArrayList;
import java.util.List;

public class AverageStack {
    private List<Double> stack;
    private Double sum;

    public AverageStack()
    {
        stack = new ArrayList<>();
        sum = 0.0;
    }

    public int size()
    {
        return stack.size();
    }

    public Double pop()
    {
        if (stack.isEmpty()) {
            return null;
        }

        Double val = stack.get(stack.size() - 1);
        stack.remove(stack.size() - 1);
        sum -= val;

        return val;
    }

    public void push(Double val)
    {
        stack.add(val);
        sum += val;
    }

    public Double peek()
    {
        if (stack.isEmpty()) {
            return null;
        }

        return stack.get(stack.size() - 1);
    }

    public Double average() {
        if (stack.isEmpty()) {
            return 0.0;
        }

        return sum / stack.size();
    }
}

