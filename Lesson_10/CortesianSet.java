import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CortesianSet<T>
{
    private List<T> storage;
    private final int capacity;
    private final int step;
    private int size;

    public CortesianSet()
    {
        capacity = 20_000;
        storage = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            storage.add(null);
        }
        step = 3;
        size = 0;
    }

    public int size()
    {
        return size;
    }

    public int hashFun(T value)
    {
        return value.hashCode() % capacity;
    }

    public int seekSlot(T value)
    {
        int hashIndex = hashFun(value);
        int index = hashIndex;

        for (; storage.get(index) != null; index = (index + step) % capacity) {
            if (storage.get(index).equals(value)) {
                return index;
            }

            if ((index + step) % capacity == hashIndex) {
                break;
            }
        }

        return index;
    }

    public void put(T value)
    {
        if (value == null) {
            return;
        }

        int slotIndex = seekSlot(value);
        if (!value.equals(storage.get(slotIndex))) {
            storage.set(slotIndex, value);
            size++;
        }

    }

    public boolean get(T value)
    {
        if (value == null) {
            return false;
        }

        int hashIndex = hashFun(value);
        int index = hashIndex;

        for (; storage.get(index) != null; index = (index + step) % capacity) {
            if (storage.get(index).equals(value)) {
                return true;
            }

            if ((index + step) % capacity == hashIndex) {
                return false;
            }
        }

        return false;
    }

    public boolean remove(T value)
    {
        if (value == null) {
            return false;
        }

        int hashIndex = hashFun(value);
        int index = hashIndex;

        for (; ; index = (index + step) % capacity) {
            if (value.equals(storage.get(index))) {
                storage.set(index, null);
                size--;
                return true;
            }

            if ((index + step) % capacity == hashIndex) {
                return false;
            }
        }
    }

    public CortesianSet<T> intersection(CortesianSet<T> set2)
    {
        if (set2 == null) {
            return null;
        }

        CortesianSet<T> result = new CortesianSet<>();
        for (T value : storage) {
            if (value != null && set2.get(value)) {
                result.put(value);
            }
        }
        return result;
    }

    public CortesianSet<T> union(CortesianSet<T> set2)
    {
        if (set2 == null) {
            return null;
        }

        CortesianSet<T> result = new CortesianSet<>();
        for (T value : storage) {
            if (value != null) {
                result.put(value);
            }
        }

        for (T value : set2.storage) {
            if (value != null) {
                result.put(value);
            }
        }
        return result;
    }

    public CortesianSet<T> difference(CortesianSet<T> set2)
    {
        if (set2 == null) {
            return null;
        }

        CortesianSet<T> result = new CortesianSet<>();
        for (T value : storage) {
            if (value != null && !set2.get(value)) {
                result.put(value);
            }
        }
        return result;
    }

    public boolean isSubset(CortesianSet<T> set2)
    {
        if (set2 == null) {
            return false;
        }

        for (T value : set2.storage) {
            if (value != null && !get(value)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(CortesianSet<T> set2)
    {
        if (set2 == null || size() != set2.size()) {
            return false;
        }

        for (T value : storage) {
            if (value != null && !set2.get(value)) {
                return false;
            }
        }
        return true;
    }

    public CortesianSet<Pair<T>> cartesianProduct(CortesianSet<T> another) {
        CortesianSet<Pair<T>> result = new CortesianSet<>();

        if (another == null) {
            return result;
        }

        for (T firstElement: storage) {
            if (firstElement == null) {
                continue;
            }

            for (T secondElement: another.storage) {
                if (secondElement == null) {
                    continue;
                }

                result.put(new Pair<>(firstElement, secondElement));
            }
        }

        return result;
    }

    public static class Pair<T> {
        private T first;
        private T second;

        public Pair(T f, T s) {
            first = f;
            second = s;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair<?> pair = (Pair<?>) o;
            return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "first=" + first +
                    ", second=" + second +
                    '}';
        }
    }
}

