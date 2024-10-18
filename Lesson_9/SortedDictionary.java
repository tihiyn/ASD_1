import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SortedDictionary<T> {
    private OrderedArray<String> slots;
    private T[] values;
    private final int capacity;
    private final boolean isAscending;

    public SortedDictionary(int sz, boolean isAsc, Class<T> clazz) {
        isAscending = isAsc;
        capacity = sz;
        slots = new OrderedArray<>(isAscending);
        values = (T[]) Array.newInstance(clazz, this.capacity);
    }

    public boolean isKey(String key) {
        return slots.getIndex(key) != -1;
    }

    public void put(String key, T value) {
        if (slots.count() == capacity) {
            throw new RuntimeException("Dictionary is full");
        }

        int index = slots.getIndex(key);

        if (index == -1) {
            index = slots.add(key);
        }

        values[index] = value;
    }

    public T get(String key) {
        int index = slots.getIndex(key);

        if (index == -1) {
            return null;
        }

        return values[index];
    }

    public void remove(String key) {
        int index = slots.getIndex(key);

        if (index != -1) {
            slots.delete(key);
            values[index] = null;
        }
    }
}

class OrderedArray<T> {
    private List<T> storage;
    private boolean _ascending;

    public OrderedArray(boolean asc) {
        _ascending = asc;
        storage = new ArrayList<>();
    }

    public int compare(T v1, T v2)
    {
        if (v1 instanceof String && v2 instanceof String) {
            return (((String) v1).trim()).compareTo(((String) v2).trim());
        }

        if (v1 instanceof Number && v2 instanceof Number) {
            return BigDecimal.valueOf(((Number) v1).doubleValue()).compareTo(BigDecimal.valueOf(((Number) v2).doubleValue()));
        }

        throw new IllegalArgumentException("Not comparable types");
    }

    public int add(T value) {
        if (storage.isEmpty()) {
            storage.add(value);
            return 0;
        }

        if (compare(value, storage.get(storage.size() - 1)) >= 0 == _ascending) {
            storage.add(value);
            return storage.size() - 1;
        }

        if (compare(value, storage.get(0)) <= 0 == _ascending) {
            storage.add(0, value);
            return 0;
        }

        int comparing;
        for (int i = 0; i < storage.size(); i++) {
            comparing = compare(value, storage.get(i));
            if ((_ascending && comparing < 0) || (!_ascending && comparing > 0)) {
                storage.add(i, value);
                return i;
            }
        }

        return -1;
    }

    public T find(T val) {
        for (T e : storage) {
            int res = compare(e, val);
            if (res == 0) {
                return e;
            }

            if (_ascending && res > 0) {
                return null;
            }

            if (!_ascending && res < 0) {
                return null;
            }
        }

        return null;
    }

    public void delete(T val) {
        T findVal = find(val);
        storage.remove(findVal);
    }

    public void clear(boolean asc) {
        storage.clear();
        _ascending = asc;
    }

    public int count() {
        return storage.size();
    }

    public int getIndex(T val) {
        if (storage.isEmpty() || val == null) {
            return -1;
        }

        int comparing;
        int begin = 0;
        int end = storage.size() - 1;

        for (int step = (end - begin + 1) / 2; begin <= end; step = (end - begin + 1) / 2) {
            comparing = compare(storage.get(begin + step), val);

            if (comparing == 0) {
                return begin + step;
            }

            if (comparing < 0 == _ascending) {
                begin = begin + step + 1;
            }

            if (comparing > 0 == _ascending) {
                end = begin + step - 1;
            }
        }

        return -1;
    }
}

