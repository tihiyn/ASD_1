import java.util.LinkedList;
import java.util.Stack;

public class BackStack<T> {
    private LinkedList<T> storage;
    private int size;

    public BackStack() {
        storage = new LinkedList<>();
        size = 0;
    }

    public void push(T item) {
        storage.addFirst(item);
        size++;
    }

    public T pop() {
        if (size == 0) {
            return null;
        }

        size--;
        return storage.removeFirst();
    }

    public T peek() {
        if (size == 0) {
            return null;
        }

        return storage.getFirst();
    }

    public int size() {
        return size;
    }
}

