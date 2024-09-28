import java.util.List;

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

    public void reverse() {
        for (int i = 0; i < storage.size() / 2; i++) {
            T temp = storage.get(i);
            storage.set(i, storage.get(storage.size() - 1 - i));
            storage.set(storage.size() - 1 - i, temp);
        }
    }
}

