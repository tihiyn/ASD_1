import java.util.Arrays;

public class MultiDimDynArray {
    public Object multiArray;
    public int[] dims;

    public MultiDimDynArray(int dimCount, int... dims) {
        this.dims = dims;
        this.multiArray = create(dims, 0);
    }

    private Object create(int[] dims, int level) {
        int size = dims[level];
        Object array = new Object[size];

        if (level == dims.length - 1) {
            return array;
        }

        for (int i = 0; i < size; i++) {
            ((Object[]) array)[i] = create(dims, level + 1);
        }

        return array;
    }

    public Object get(int... pos) {
        Object array = multiArray;
        for (int i = 0; i < pos.length - 1; i++) {
            array = ((Object[]) array)[pos[i]];
        }

        return ((Object[]) array)[pos[pos.length - 1]];
    }

    private Object extend(Object array, int newSize, int level) {
        Object[] newArray = new Object[newSize];
        int oldSize = ((Object[]) array).length;

        System.arraycopy(array, 0, newArray, 0, oldSize);

        if (level == dims.length) {
            return newArray;
        }

        for (int i = oldSize; i < newSize; i++) {
            newArray[i] = create(dims, level + 1);
        }

        return newArray;
    }

    public void set(Object value, int... pos) {
        if (pos.length != dims.length) {
            throw new IllegalArgumentException("Incorrect number of indexes");
        }
        Object array = multiArray;
        Object parentArray = null;
        int parentIndex = -1;

        for (int i = 0; i < pos.length - 1; i++) {
            int index = pos[i];
            int currentSize = ((Object[]) array).length;

            if (index >= currentSize) {
                array = extend(array, index + 1, i);
                if (parentArray != null) {
                    ((Object[]) parentArray)[parentIndex] = array;
                } else {
                    multiArray = array;
                }

                dims[i] = index + 1;
            }

            parentArray = array;
            parentIndex = index;
            array = ((Object[]) array)[index];

            if (array == null) {
                array = create(dims, i + 1);
                ((Object[]) parentArray)[parentIndex] = array;
            }
        }

        int lastIndex = pos[pos.length - 1];
        int currentSize = ((Object[]) array).length;
        if (lastIndex >= currentSize) {
            array = extend(array, lastIndex + 1, pos.length - 1);
            ((Object[]) parentArray)[parentIndex] = array;

            dims[pos.length - 1] = lastIndex + 1;
        }
        ((Object[]) array)[lastIndex] = value;
    }

    public void remove(int... pos) {
        if (pos.length != dims.length) {
            throw new IllegalArgumentException("Incorrect number of indexes");
        }

        for (int i = 0; i < pos.length; i++) {
            if (pos[i] >= dims[i]) {
                throw new IndexOutOfBoundsException("Incorrect position");
            }
        }

        set(null, pos);
    }

    @Override
    public String toString() {
        return Arrays.deepToString((Object[]) multiArray);
    }
}

