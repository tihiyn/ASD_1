import java.lang.reflect.Array;
import java.util.Arrays;

public class BitDictionary<T> {
    public int capacity;
    private final int keySize;
    private BitString[] slots;
    private T[] values;
    private final int step = 3;

    public BitDictionary(int sz, int ks, Class<T> clazz) {
        capacity = sz;
        keySize = ks;
        slots = new BitString[capacity];
        values = (T[]) Array.newInstance(clazz, this.capacity);
        for (int i = 0; i < capacity; i++) slots[i] = null;
    }

    public int hashFun(BitString key)
    {
        return key.hashCode() % capacity;
    }

    public void put(byte[] keyBytes, T value) {
        BitString key = new BitString(keyBytes, keySize);
        int hashIndex = hashFun(key);
        int index = hashIndex;

        for (; slots[index] != null; index = (index + step) % capacity) {
            if (slots[index].equals(key)) {
                break;
            }

            if ((index + step) % capacity == hashIndex) {
                index = hashIndex;
                break;
            }
        }

        slots[index] = key;
        values[index] = value;
    }

    public T get(byte[] keyBytes) {
        BitString key = new BitString(keyBytes, keySize);
        int hashIndex = hashFun(key);
        int index = hashIndex;

        for (; slots[index] != null; index = (index + step) % capacity) {
            if (slots[index].equals(key)) {
                return values[index];
            }

            if ((index + step) % capacity == hashIndex) {
                break;
            }
        }

        return null;
    }

    public void remove(byte[] keyBytes) {
        BitString key = new BitString(keyBytes, keySize);
        int hashIndex = hashFun(key);
        int index = hashIndex;

        for (; slots[index] != null; index = (index + step) % capacity) {
            if (slots[index].equals(key)) {
                slots[index] = null;
                values[index] = null;
                return;
            }

            if ((index + step) % capacity == hashIndex) {
                return;
            }
        }
    }
}

class BitString {
    private byte[] storage;

    public BitString(byte[] bits, int size) {
        if (!isValidContainingOfBitString(bits)) {
            throw new IllegalArgumentException("Bit string should contains only 0 and 1");
        }

        if (bits.length > size) {
            throw new IllegalArgumentException("Bit string size is greater than expected size");
        }

        if (bits.length < size) {
            storage = padWithZeros(bits, size);
            return;
        }

        storage = Arrays.copyOf(bits, bits.length);
    }

    private boolean isValidContainingOfBitString(byte[] bits) {
        for (byte bit : bits) {
            if (bit != 0 && bit != 1) {
                return false;
            }
        }
        return true;
    }

    private byte[] padWithZeros(byte[] bits, int size) {
        byte[] paddedBits = new byte[size];
        int startIndex = size - bits.length;
        System.arraycopy(bits, 0, paddedBits, startIndex, bits.length);
        return paddedBits;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BitString bitString = (BitString) obj;
        return Arrays.equals(storage, bitString.storage);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(storage);
    }
}

