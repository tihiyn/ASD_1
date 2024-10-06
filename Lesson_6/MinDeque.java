import java.util.ArrayList;
import java.util.List;

public class MinDeque<T extends Number> {
    private List<T> storage;
    private Deque<T> minDeque;

    public MinDeque() {
        storage = new ArrayList<>();
        minDeque = new Deque<>();
    }

    public void addTail(T item)
    {
        storage.add(item);

        if (minDeque.getTail() != null && item.intValue() > minDeque.getTail().intValue()) {
            T tmp = minDeque.removeTail();
            minDeque.addTail(item);
            minDeque.addTail(tmp);
            return;
        }

        minDeque.addTail(item);
    }

    public void addFront(T item) {
        storage.add(0, item);

        if (minDeque.getFront() != null && item.intValue() > minDeque.getFront().intValue()) {
            T tmp = minDeque.removeFront();
            minDeque.addFront(item);
            minDeque.addFront(tmp);
            return;
        }

        minDeque.addFront(item);
    }

    public T removeTail() {
        if (storage.isEmpty()) {
            return null;
        }

        T val = storage.remove(storage.size() - 1);

        if (val.intValue() == minDeque.getFront().intValue()) {
            minDeque.removeFront();
            return val;
        }

        if (val.intValue() > minDeque.getTail().intValue()) {
            T tmp = minDeque.removeTail();
            minDeque.removeTail();
            minDeque.addTail(tmp);
            return val;
        }

        minDeque.removeTail();
        return val;
    }

    public T removeFront() {
        if (storage.isEmpty()) {
            return null;
        }

        T val = storage.remove(0);

        if (val.intValue() == minDeque.getTail().intValue()) {
            minDeque.removeTail();
            return val;
        }

        if (val.intValue() > minDeque.getFront().intValue()) {
            T tmp = minDeque.removeFront();
            minDeque.removeFront();
            minDeque.addFront(tmp);
            return val;
        }

        minDeque.removeFront();
        return val;
    }

    public T getFront() {
        return storage.get(0);
    }

    public T getTail() {
        return storage.get(storage.size() - 1);
    }

    public Integer getMin() {
        if (storage.isEmpty()) {
            return null;
        }

        return Math.min(minDeque.getTail().intValue(), minDeque.getFront().intValue());
    }
}

