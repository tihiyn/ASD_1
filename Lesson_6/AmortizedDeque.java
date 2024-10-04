import java.lang.reflect.Array;

public class AmortizedDeque<T> {
    private DynamicArray<T> storage;
    private int startPointer;
    private int endPointer;
    private int size;
    private int capacity;

    public AmortizedDeque(Class<T> clazz) {
        capacity = 1;
        storage = new DynamicArray<>(clazz, capacity);
        startPointer = 0;
        endPointer = 0;
        size = 0;

    }

    public void addFront(T item) {
        startPointer = movePointerLeftInCycle(startPointer);
        storage.setItem(startPointer, item);
        size++;

        if (capacity != storage.getCapacity()) {
            rebootPointers();
        }
    }

    public void addTail(T item) {
        storage.setItem(endPointer, item);
        endPointer = movePointerRightInCycle(endPointer);
        size++;
        if (capacity != storage.getCapacity()) {
            rebootPointers();
        }
    }

    public T removeFront() {
        if (size == 0) {
            return null;
        }

        startPointer = movePointerRightInCycle(startPointer);
        size--;
        return storage.getItem(movePointerLeftInCycle(startPointer));
    }

    public T removeTail() {
        if (size == 0) {
            return null;
        }

        endPointer = movePointerLeftInCycle(endPointer);
        size--;
        return storage.getItem(endPointer);
    }

    private int movePointerLeftInCycle(int pointer) {
        return (pointer - 1 + storage.getCapacity()) % storage.getCapacity();
    }

    private int movePointerRightInCycle(int pointer) {
        return (pointer + 1) % storage.getCapacity();
    }

    private void rebootPointers() {
	capacity = storage.getCapacity();
        startPointer = capacity - 1;
        endPointer = size - 1;
    }

    public boolean isFull() {
        return size == storage.getCapacity();
    }

    public int size() {
        return size;
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

    public DynamicArray<T> getExtendedArray(int newCapacity) {
        return new DynamicArray<>(clazz, newCapacity);
    }

    public T getItem(int index) {
        size--;
        return storage[index];
    }

    public void setItem(int index, T value) {
        if (size == capacity) {
            resize(index);
            storage[capacity - 1] = value;
            size++;
            return;
        }

        size++;
        storage[index] = value;
    }

    public int getCapacity() {
        return capacity;
    }

    public void resize(int startPointer) {
        T[] newStorage = (T[]) Array.newInstance(clazz, capacity * 2);

        for (int i = 0; i < capacity; i++) {
            newStorage[i] = storage[(startPointer + 1 + i) % capacity];
        }

        storage = newStorage;
        capacity *= 2;
    }
}

