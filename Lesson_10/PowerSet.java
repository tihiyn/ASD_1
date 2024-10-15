import java.util.ArrayList;
import java.util.List;

public class PowerSet
{
    private List<String> storage;
    private final int capacity;
    private final int step;
    private int size;

    public PowerSet()
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

    public int hashFun(String value)
    {
        int hash = 0;

        for (byte b: value.getBytes()) {
            hash = 31 * hash + (b & 0xff);
        }

        return Math.abs(hash) % capacity;
    }

    public int seekSlot(String value)
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

    public void put(String value)
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

    public boolean get(String value)
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

    public boolean remove(String value)
    {
        if (value == null) {
            return false;
        }

        int hashIndex = hashFun(value);
        int index = hashIndex;

        for (; storage.get(index) != null; index = (index + step) % capacity) {
            if (storage.get(index).equals(value)) {
                storage.set(index, null);
                size--;
                return true;
            }

            if ((index + step) % capacity == hashIndex) {
                return false;
            }
        }

        return false;
    }

    public PowerSet intersection(PowerSet set2)
    {
        if (set2 == null) {
            return null;
        }
        
        PowerSet result = new PowerSet();
        for (String value : storage) {
            if (value != null && set2.get(value)) {
                result.put(value);
            }
        }
        return result;
    }

    public PowerSet union(PowerSet set2)
    {
        if (set2 == null) {
            return null;
        }
        
        PowerSet result = new PowerSet();
        for (String value : storage) {
            if (value != null) {
                result.put(value);
            }
        }

        for (String value : set2.storage) {
            if (value != null) {
                result.put(value);
            }
        }
        return result;
    }

    public PowerSet difference(PowerSet set2)
    {
        if (set2 == null) {
            return null;
        }
        
        PowerSet result = new PowerSet();
        for (String value : storage) {
            if (value != null && !set2.get(value)) {
                result.put(value);
            }
        }
        return result;
    }

    public boolean isSubset(PowerSet set2)
    {
        if (set2 == null) {
            return false;
        }
        
        for (String value : set2.storage) {
            if (value != null && !this.get(value)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(PowerSet set2)
    {
        if (set2 == null || size() != set2.size()) {
            return false;
        }
        
        for (String value : storage) {
            if (value != null && !set2.get(value)) {
                return false;
            }
        }
        return true;
    }
}

