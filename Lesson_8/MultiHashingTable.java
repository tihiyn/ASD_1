public class MultiHashingTable {
    public int size;
    public int step;
    public String [] slots;

    public MultiHashingTable(int sz, int stp)
    {
        size = sz;
        step = stp;
        slots = new String[size];
        for(int i=0; i<size; i++) slots[i] = null;
    }

    public int hashFun(String value) {
        int hash = 0;

        for (byte b: value.getBytes()) {
            hash = 31 * hash + (b & 0xff);
        }

        return Math.abs(hash) % size;
    }

    public int secondHashFun(String value) {
        int hash = 0;

        for (byte b: value.getBytes()) {
            hash = 19 * hash + (b & 0xff);
        }

        return (hash % (size - 1)) + 1;
    }

    public int seekSlot(String value) {
        int index = hashFun(value);
        int stepSize = secondHashFun(value);

        for (int i = 0; i < size; i++) {
            int newIndex = (index + i * stepSize) % size;
            if (slots[newIndex] == null) {
                return newIndex;
            }
        }
        return -1;
    }

    public int put(String value)
    {
        int slotIndex = seekSlot(value);

        if (slotIndex == -1) {
            return -1;
        }

        slots[slotIndex] = value;
        return slotIndex;
    }

    public int find(String value)
    {
        int index = hashFun(value);
        int stepSize = secondHashFun(value);

        for (int i = 0; i < size; i++) {
            int newIndex = (index + i * stepSize) % size;
            if (slots[newIndex] != null && slots[newIndex].equals(value)) {
                return newIndex;
            }
            if (slots[newIndex] == null) {
                return -1;
            }
        }
        return -1;
    }
}

