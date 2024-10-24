import java.lang.reflect.Array;

public class NativeCache<T> {
    private String [] slots;
    private T [] values;
    private int [] hits;
    public final int size;
    public int count;
    private final int step;

    public NativeCache(int sz, int stp, Class<T> clazz) {
        size = sz;
        count = 0;
        step = stp;
        slots = new String[size];
        values = (T[]) Array.newInstance(clazz, size);
        hits = new int[size];
    }

    private int hashFun(String key) {
        int hash = 0;

        for (byte b: key.getBytes()) {
            hash = 31 * hash + (b & 0xff);
        }

        return Math.abs(hash) % size;
    }

    public boolean isKey(String key) {
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

    public void put(String key, T value) {
        if (count == size) {
            removeNonRelevant();
        }

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
        count++;
    }

    private void removeNonRelevant() {
        int min = Integer.MAX_VALUE;
        int minIndex = 0;

        for (int i = 0; i < size; i++) {
            if (hits[i] < min) {
                min = hits[i];
                minIndex = i;
            }
        }

        slots[minIndex] = null;
        values[minIndex] = null;
        hits[minIndex] = 0;
        count--;
    }

    public T get(String key) {
        int hashIndex = hashFun(key);
        int index = hashIndex;

        for (; slots[index] != null; index = (index + step) % size) {
            if (slots[index].equals(key)) {
                hits[index]++;
                return values[index];
            }

            if ((index + step) % size == hashIndex) {
                break;
            }
        }

        return null;
    }
}

