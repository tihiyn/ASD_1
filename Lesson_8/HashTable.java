public class HashTable
{
    public int size;
    public int step;
    public String [] slots;

    public HashTable(int sz, int stp)
    {
        size = sz;
        step = stp;
        slots = new String[size];
        for(int i=0; i<size; i++) slots[i] = null;
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

        while (slots[index] != null) {
            index = (index + step) % size;

            if (index == hashIndex) {
                return -1;
            }
        }

        return index;
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
        int initialIndex = index;

        while (slots[index] != null) {
            if (slots[index].equals(value)) {
                return index;
            }

            index = (index + step) % size;

            if (index == initialIndex) {
                break;
            }
        }

        return -1;
    }
}

