public class DeleteBloomFilter {
    private byte[] bitCounterArray;
    private final int filter_len;

    public DeleteBloomFilter(int f_len) {
        filter_len = f_len;
        bitCounterArray = new byte[filter_len];
        
        for (int i = 0; i < filter_len; i++) {
            bitCounterArray[i] = 0;
        }
    }
    
    public int hash1(String str1) {
        int hash = 0;
        for (int i = 0; i < str1.length(); i++) {
            hash = hash * 17 + (int) str1.charAt(i);
        }
        return Math.abs(hash) % filter_len;
    }
    
    public int hash2(String str1) {
        int hash = 0;
        for (int i = 0; i < str1.length(); i++) {
            hash = hash * 223 + (int) str1.charAt(i);
        }
        return Math.abs(hash) % filter_len;
    }
    
    public void add(String str1) {
        if (str1 == null) {
            return;
        }

        bitCounterArray[hash1(str1)]++;
        bitCounterArray[hash2(str1)]++;
    }
    
    public void remove(String str1) {
        if (str1 == null) {
            return;
        }

        if (bitCounterArray[hash1(str1)] > 0) {
            bitCounterArray[hash1(str1)]--;
        }
        
        if (bitCounterArray[hash2(str1)] > 0) {
            bitCounterArray[hash2(str1)]--;
        }
    }
    
    public boolean isValue(String str1) {
        if (str1 == null) {
            return false;
        }

        return bitCounterArray[hash1(str1)] > 0 && bitCounterArray[hash2(str1)] > 0;
    }
}

