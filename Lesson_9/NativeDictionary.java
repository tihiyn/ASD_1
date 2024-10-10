import java.lang.reflect.Array;

class NativeDictionary<T>
{
    public int size;
    public String [] slots;
    public T [] values;
    public int step;

    public NativeDictionary(int sz, Class clazz)
    {
        size = sz;
        slots = new String[size];
        values = (T[]) Array.newInstance(clazz, this.size);
        step = 3;
    }

    public int hashFun(String key)
    {
        int hash = 0;

        for (byte b: key.getBytes()) {
            hash = 31 * hash + (b & 0xff);
        }

        return Math.abs(hash) % size;
    }

    public boolean isKey(String key)
    {
        int hashIndex = hashFun(key);
        int index = hashIndex;

        for (; slots[index] != null; index = (index + step) % size) {
            if (slots[index].equals(key)) {
                return true;
            }

            if ((index + step) % size == hashIndex) {
                return false;
            }
        }

        return false;
    }

    public void put(String key, T value)
    {
        int hashIndex = hashFun(key);
        int index = hashIndex;

        for (; slots[index] != null; index = (index + step) % size) {
            if (slots[index].equals(key)) {
                break;
            }

            if ((index + step) % size == hashIndex) {
                index = hashIndex;
                break;
            }
        }

        slots[index] = key;
        values[index] = value;
    }

    public T get(String key)
    {
        int hashIndex = hashFun(key);
        int index = hashIndex;

        for (; slots[index] != null; index = (index + step) % size) {
            if (slots[index].equals(key)) {
                return values[index];
            }

            if ((index + step) % size == hashIndex) {
                break;
            }
        }

        return null;
    }
}

