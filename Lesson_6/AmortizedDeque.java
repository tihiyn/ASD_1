import java.lang.reflect.Array;

public class AmortizedDeque<T> {
    private DynamicArray<T> storage;

    public AmortizedDeque(Class<T> clazz) {
        storage = new DynamicArray<>(clazz, 1);
    }

    public void addFront(T item) {
        storage.addItem(0, item);
    }

    public void addTail(T item) {
        storage.addItem(storage.getSize(), item);
    }

    public T removeFront() {
        if (storage.getSize() == 0) {
            return null;
        }

        return storage.removeItem(0);
    }

    public T removeTail() {
        if (storage.getSize() == 0) {
            return null;
        }

        return storage.removeItem(storage.getSize() - 1);
    }
}

class DynamicArray<T> {
    private T[] storage;
    private Class<T> clazz;
    private int capacity;
    private int size;

    public DynamicArray(Class<T> clazz, int initialCapacity) {
        this.clazz = clazz;
        this.capacity = initialCapacity;
        storage = (T[]) Array.newInstance(clazz, initialCapacity);
        size = 0;
    }

    public T removeItem(int index) {
        size--;
        return storage[index];
    }

    public void addItem(int index, T value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (size == capacity) {
            resize();
        }

        for (int i = size - 1; i >= index; i--) {
            storage[i + 1] = storage[i];
        }

        storage[index] = value;
        size++;
    }

    private void resize() {
        T[] oldArray = storage;
        capacity *= 2;
        storage = (T[]) Array.newInstance(this.clazz, capacity);
        System.arraycopy(oldArray, 0, storage, 0, size);
    }

    public int getSize() {
        return size;
    }
}

