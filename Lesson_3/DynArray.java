import java.lang.reflect.Array;

public class DynArray<T>
{
    public T [] array;
    public int count;
    public int capacity;
    Class clazz;

    public DynArray(Class clz)
    {
        clazz = clz;

        count = 0;
        makeArray(16);
    }

    public void makeArray(int new_capacity)
    {
        T[] oldArray = array;
        array = (T[]) Array.newInstance(this.clazz, new_capacity);
        if (oldArray != null) {
            for (int i = 0; i < count; i++) {
                array[i] = oldArray[i];
            }
        }
        capacity = new_capacity;
    }

    public T getItem(int index)
    {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException();
        }

        return array[index];
    }

    public void append(T itm)
    {
        if (count == capacity) {
            makeArray(capacity * 2);
        }
        array[count] = itm;
        count++;
    }

    public void insert(T itm, int index)
    {
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException();
        }

        if (count == capacity) {
            makeArray(capacity * 2);
        }

        for (int i = count - 1; i >= index; i--) {
            array[i + 1] = array[i];
        }
        array[index] = itm;
        count++;
    }

    public void remove(int index)
    {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException();
        }

        for (int i = index; i < count - 1; i++) {
            array[i] = array[i + 1];
        }
        count--;

        int mayBeSize = (int) (capacity / 1.5);
        if (count < (capacity + 2 - 1) / 2 && mayBeSize > 16) {
            makeArray(mayBeSize);
        }
    }

}

