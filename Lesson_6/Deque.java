import java.util.*;

public class Deque<T>
{
    private List<T> storage;

    public Deque()
    {
        storage = new ArrayList<>();
    }

    public void addFront(T item)
    {
        storage.add(0, item);
    }

    public void addTail(T item)
    {
        storage.add(item);
    }

    public T removeFront()
    {
        if (storage.isEmpty()) {
            return null;
        }

        return storage.remove(0);
    }

    public T removeTail()
    {
        if (storage.isEmpty()) {
            return null;
        }

        return storage.remove(storage.size() - 1);
    }

    public int size()
    {
        return storage.size();
    }
}

