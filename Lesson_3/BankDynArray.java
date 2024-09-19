import java.lang.reflect.Array;

public class BankDynArray<T> {
    public T [] array;
    public int count;
    public int capacity;
    Class clazz;
    int balance;

    public BankDynArray(Class clz)
    {
        clazz = clz;

        count = 0;
        makeArray(1);
        balance = 0;
    }

    public void makeArray(int new_capacity)
    {
        T[] oldArray = array;
        array = (T[]) Array.newInstance(this.clazz, new_capacity);
        capacity = new_capacity;
        if (oldArray == null) {
            return;
        }
        System.arraycopy(oldArray, 0, array, 0, count);
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
        if (balance >= capacity) {
            makeArray(capacity * 2);
            balance -= count;
        }
        array[count] = itm;
        count++;
        balance += 2;
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
        array[count] = null;

        if (count < (capacity + 2 - 1) / 2) {
            makeArray(Math.max(16, (int) (capacity / 1.5)));
        }
    }
}

