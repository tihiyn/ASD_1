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
        int secondSize = second.size();
        for (int i = 0; i < secondSize; i++) {
            T e = second.pop();
            first.push(e);
        }

        first.push(item);
        size++;
    }

    public T dequeue() {
        if (size == 0) {
            return null;
        }

        int firstSize = first.size();
        for (int i = 0; i < firstSize; i++) {
            T e = first.pop();
            second.push(e);
        }

        size--;
        return second.pop();
    }

    public int size() {
        return size;
    }
}

