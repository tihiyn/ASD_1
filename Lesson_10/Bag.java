import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Bag<T> {
    private List<Pair<T>> storage;
    private final int capacity;
    private final int step;

    public static class Pair<T> {
        private T value;
        private Integer count;

        public Pair(T f, Integer s) {
            value = f;
            count = s;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair<?> pair = (Pair<?>) o;
            return Objects.equals(value, pair.value) && Objects.equals(count, pair.count);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, count);
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "value=" + value +
                    ", count=" + count +
                    '}';
        }
    }

    public Bag() {
        capacity = 20_000;
        storage = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            storage.add(null);
        }
        step = 3;
    }

    public int hashFun(T value)
    {
        return Math.abs(value.hashCode()) % capacity;
    }

    public int seekSlot(T value)
    {
        int hashIndex = hashFun(value);
        int index = hashIndex;

        for (; storage.get(index) != null; index = (index + step) % capacity) {
            if (value.equals(storage.get(index).value)) {
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
        if (storage.get(slotIndex) != null && value.equals(storage.get(slotIndex).value)) {
            storage.get(slotIndex).count++;
            return;
        }

        storage.set(slotIndex, new Pair<>(value, 1));

    }

    public boolean get(T value)
    {
        if (value == null) {
            return false;
        }

        int hashIndex = hashFun(value);
        int index = hashIndex;

        for (; ; index = (index + step) % capacity) {
            if (value.equals(storage.get(index).value)) {
                return true;
            }

            if ((index + step) % capacity == hashIndex) {
                return false;
            }
        }
    }

    public boolean remove(T value)
    {
        if (value == null) {
            return false;
        }

        int hashIndex = hashFun(value);
        int index = hashIndex;

        for (; ; index = (index + step) % capacity) {
            if (storage.get(index) != null && value.equals(storage.get(index).value)) { //
                decrementCount(index);
                return true;
            }

            if ((index + step) % capacity == hashIndex) {
                return false;
            }
        }
    }

    private void decrementCount(int index) {
        storage.get(index).count--;

        if (storage.get(index).count == 0) {
            storage.set(index, null);
        }
    }

    public List<Pair<T>> getAll() {
        return storage.stream().filter(Objects::nonNull).toList();
    }
}

