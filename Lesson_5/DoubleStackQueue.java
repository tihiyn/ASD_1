import java.util.Stack;

public class DoubleStackQueue<T> {
    private Stack<T> first;
    private Stack<T> second;
    int size;

    public DoubleStackQueue() {
        first = new Stack<>();
        second = new Stack<>();
        size = 0;
    }

    public void enqueue(T item) {
        first.push(item);
        size++;
    }

    public T dequeue() {
        if (size == 0) {
            return null;
        }
        
        size--;
        if (!second.isEmpty()) {
            return second.pop();
        }

        while (!first.isEmpty()) {
            second.push(first.pop());
        }

        return second.pop();
    }

    public int size() {
        return size;
    }
}

