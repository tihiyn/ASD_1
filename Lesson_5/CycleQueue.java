import java.lang.reflect.Array;

public class CycleQueue<T> {
    private T[] storage;
    private int startPointer;
    private int endPointer;
    private final int capacity;
    private int size;

    public CycleQueue(Class clazz, int initCapacity) {
        storage = (T[]) Array.newInstance(clazz, initCapacity);
        startPointer = 0;
        endPointer = 0;
        capacity = initCapacity;
        size = 0;
    }

    public void enqueue(T item) {
        if (isFull()) {
            throw new ArrayStoreException("Queue overflow");
        }

        startPointer = movePointerLeftInCycle(startPointer);
        storage[startPointer] = item;
        size++;
    }

    public T dequeue() {
        if (size == 0) {
            return null;
        }

        endPointer = movePointerLeftInCycle(endPointer);
        size--;
        return storage[endPointer];
    }

    private int movePointerLeftInCycle(int pointer) {
        return (pointer - 1 + capacity) % capacity;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int size() {
        return size;
    }
}

