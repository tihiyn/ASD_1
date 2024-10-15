import java.util.Random;

public class SaltHashTable {
    private final int size;
    private final int step;
    private Slot[] slots;
    public int collisionCounter;

    private static class Slot {
        public String value;
        public int salt;

        public Slot(String value, int salt) {
            this.value = value;
            this.salt = salt;
        }
    }

    public SaltHashTable(int sz, int stp) {
        size = sz;
        step = stp;
        slots = new Slot[size];
        collisionCounter = 0;
        for (int i = 0; i < size; i++) slots[i] = null;
    }

    private int hashFun(String value, int salt) {
        int hash = salt;

        for (byte b : value.getBytes()) {
            hash = 31 * hash + (b & 0xff);
        }

        return Math.abs(hash) % size;
    }

    private int seekSlot(String value, int salt) {
        int hashIndex = hashFun(value, salt);
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
        int salt = new Random().nextInt();
        int slotIndex = seekSlot(value, salt);

        if (slotIndex == -1) {
            return -1;
        }

        slots[slotIndex] = new Slot(value, salt);
        return slotIndex;
    }

    public int find(String value) {
        for (int i = 0; i < size; i++) {
            if (slots[i] != null && slots[i].value.equals(value)) {
                return i;
            }
        }

        return -1;
    }
}

