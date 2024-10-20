import java.util.List;

public class BloomFilter
{
    public int filter;
    public int filter_len;

    public BloomFilter(int f_len)
    {
        filter_len = f_len;
        filter = 0;
    }

    public int hash1(String str1)
    {
        int hash = 0;
        for(int i=0; i<str1.length(); i++)
        {
            hash = hash * 17 + (int) str1.charAt(i);
        }

        hash = hash % filter_len;

        return 1 << hash;
    }

    public int hash2(String str1)
    {
        int hash = 0;
        for(int i=0; i<str1.length(); i++)
        {
            hash = hash * 223 + (int) str1.charAt(i);
        }

        return 1 << hash;
    }

    public void add(String str1)
    {
        if (str1 == null) {
            return;
        }

        filter |= hash1(str1);
        filter |= hash2(str1);
    }

    public boolean isValue(String str1)
    {
        if (str1 == null) {
            return false;
        }

        return (filter & hash1(str1)) != 0 && (filter & hash2(str1)) != 0;
    }
}

