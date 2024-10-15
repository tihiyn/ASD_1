import java.lang.reflect.Array;

public class DynamicHashTable {
    private final float loadFactor = 0.8f;
    private final int step;
    public int size;
    private DynamicArray<String> slots;

    public DynamicHashTable(int sz, int stp)
    {
        size = sz;
        step = stp;
        slots = new DynamicArray<>(String.class, size);
    }

    public int hashFun(String value)
    {
        int hash = 0;

        for (byte b: value.getBytes()) {
            hash = 31 * hash + (b & 0xff);
        }

        return Math.abs(hash) % size;
    }

    public int seekSlot(String value)
    {
        int hashIndex = hashFun(value);
        int index = hashIndex;

        for (; slots.getItem(index) != null; index = (index + step) % size) {
            if ((index + step) % size == hashIndex) {
                return -1;
            }
        }

        return index;
    }

    public int put(String value)
    {
        if (slots.count == (int) (size * loadFactor)) {
            String[] oldSlots = new String[size];
            copy(oldSlots);
            size *= 2;
            slots.makeArray(size);
            rehash(oldSlots);
        }

        int slotIndex = seekSlot(value);

        if (slotIndex == -1) {
            return -1;
        }

        slots.set(value, slotIndex);
        return slotIndex;
    }

    public int find(String value)
    {
        int hashIndex = hashFun(value);
        int index = hashIndex;

        for (; slots.getItem(index) != null; index = (index + step) % size) {
            if (slots.getItem(index).equals(value)) {
                return index;
            }

            if ((index + step) % size == hashIndex) {
                break;
            }
        }

        return -1;
    }

    private void copy(String[] oldSlots) {
        for (int i = 0; i < slots.capacity; i++) {
            oldSlots[i] = slots.getItem(i);
        }
    }

    private void rehash(String[] oldSlots) {
        for (int i = 0; i < oldSlots.length; i++) {
            String oldSlot = oldSlots[i];
            if (oldSlot != null) {
                slots.remove(i);
                slots.set(oldSlot, seekSlot(oldSlot));
            }
        }
    }
}

class DynamicArray<T>
{
    public T[] array;
    public int count;
    public int capacity;
    public Class<T> clazz;

    public DynamicArray(Class<T> clz, int initCapacity)
    {
        clazz = clz;

        count = 0;
        makeArray(initCapacity);
    }

    public void makeArray(int new_capacity)
    {
        T[] oldArray = array;
        capacity = new_capacity;
        array = (T[]) Array.newInstance(this.clazz, capacity);
        for(int i = 0; i < new_capacity; i++) {
            array[i] = null;
        }

        if (oldArray == null) {
            return;
        }
        System.arraycopy(oldArray, 0, array, 0, count);
    }

    public T getItem(int index)
    {
        if (index < 0 || index >= capacity) {
            throw new IndexOutOfBoundsException();
        }

        return array[index];
    }

    public void set(T itm, int index) {
        if (index < 0 || index >= capacity) {
            throw new IndexOutOfBoundsException();
        }

        if (array[index] == null) {
            count++;
        }

        array[index] = itm;
    }

    public void remove(int index)
    {
        if (index < 0 || index >= capacity) {
            throw new IndexOutOfBoundsException();
        }

        count--;
        array[index] = null;
    }
}

