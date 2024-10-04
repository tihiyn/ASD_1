import java.lang.reflect.Array;

public class AmortizedDeque<T> {
    private DynamicArray<T> storage;
    private int startPointer;
    private int endPointer;
    private int size;

    public AmortizedDeque(Class<T> clazz) {
        storage = new DynamicArray<>(clazz, 1);
        startPointer = 0;
        endPointer = 0;
        size = 0;
    }

    private void resize() {
        DynamicArray<T> newStorage = new DynamicArray<>(storage.getTypeOfElements(), storage.getCapacity() * 2);

        for (int i = 0; i < size; i++) {
            newStorage.setItem(i, storage.getItem((startPointer + i) % storage.getCapacity()));
        }

        storage = newStorage;
        startPointer = 0;
        endPointer = size;
    }

    public void addFront(T item) {
        if (isFull()) {
            resize();
        }

        startPointer = movePointerLeftInCycle(startPointer);
        storage.setItem(startPointer, item);
        size++;
    }

    public void addTail(T item) {
        if (isFull()) {
            resize();
        }

        storage.setItem(endPointer, item);
        endPointer = movePointerRightInCycle(endPointer);
        size++;
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

    public DynamicArray(Class<T> clazz, int initialCapacity) {
        this.clazz = clazz;
        this.capacity = initialCapacity;
        storage = (T[]) Array.newInstance(clazz, initialCapacity);
    }

    public DynamicArray<T> getExtendedArray(int newCapacity) {
        return new DynamicArray<>(clazz, newCapacity);
    }

    public T getItem(int index) {
        return storage[index];
    }

    public void setItem(int index, T value) {
        storage[index] = value;
    }

    public int getCapacity() {
        return capacity;
    }

    public Class<T> getTypeOfElements() {
        return clazz;
    }
}

