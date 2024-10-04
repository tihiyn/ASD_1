import java.lang.reflect.Array;

public class AmortizedDeque<T> {
    private T[] storage;
    private Class clazz;
    private int startPointer;
    private int endPointer;
    private int capacity;
    private int size;

    public AmortizedDeque(Class clazz) {
        capacity = 1;
        storage = (T[]) Array.newInstance(clazz, capacity);
        this.clazz = clazz;
        startPointer = 0;
        endPointer = 0;
        size = 0;
    }

    private void resize() {
        T[] oldDeque = storage;
        capacity *= 2;
        storage = (T[]) Array.newInstance(clazz, capacity);

        for (int i = 0; i < size; i++) {
            storage[i] = oldDeque[(startPointer + i) % size];
        }

        startPointer = 0;
        endPointer = size;
    }

    public void addFront(T item) {
        if (isFull()) {
            resize();
        }

        startPointer = movePointerLeftInCycle(startPointer);
        storage[startPointer] = item;
        size++;
    }

    public void addTail(T item) {
        if (isFull()) {
            resize();
        }

        storage[endPointer] = item;
        endPointer = movePointerRightInCycle(endPointer);
        size++;
    }

    public T removeFront() {
        if (size == 0) {
            return null;
        }

        startPointer = movePointerRightInCycle(startPointer);
        size--;
        return storage[movePointerLeftInCycle(startPointer)];
    }

    public T removeTail() {
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
    
    private int movePointerRightInCycle(int pointer) {
        return (pointer + 1) % capacity;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int size() {
        return size;
    }
}

