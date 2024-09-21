import java.util.*;

public class Queue<T>
{
    List<T> storage;

    public Queue()
    {
        storage = new ArrayList<>();
    }

    public void enqueue(T item)
    {
        storage.add(0, item);
    }

    public T dequeue()
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

