import java.util.Random;

public class SaltHashTable {
    public int size;
    public int step;
    public String[] slots;
    private final int salt;
    public collisionCounter;

    public SaltHashTable(int sz, int stp) {
        size = sz;
        step = stp;
        slots = new String[size];
        Random random = new Random();
        salt = random.nextInt();
        collisionCounter = 0;
        for (int i = 0; i < size; i++) slots[i] = null;
    }

    public int hashFun(String value) {
        int hash = salt;

        for (byte b : value.getBytes()) {
            hash = 31 * hash + (b & 0xff);
        }

        return Math.abs(hash) % size;
    }

    public int seekSlot(String value) {
        int hashIndex = hashFun(value);
        int index = hashIndex;

        for (; slots[index] != null; index = (index + step) % size) {
            collisionCounter++;
	    if ((index + step) % size == hashIndex) {
                return -1;
            }
        }

        return index;
    }

    public int put(String value) {
        int slotIndex = seekSlot(value);

        if (slotIndex == -1) {
            return -1;
        }

        slots[slotIndex] = value;
        return slotIndex;
    }

    public int find(String value) {
        int hashIndex = hashFun(value);
        int index = hashIndex;

        for (; slots[index] != null; index = (index + step) % size) {
            if (slots[index].equals(value)) {
                return index;
            }

            if ((index + step) % size == hashIndex) {
                break;
            }
        }

        return -1;
    }
}

