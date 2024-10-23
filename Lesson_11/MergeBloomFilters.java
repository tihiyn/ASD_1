import java.util.List;

public class MergeBloomFilters {
    public BloomFilter merge(List<BloomFilter> bfs) {
        if (bfs == null || bfs.isEmpty()) {
            throw new IllegalArgumentException("No filters to merge");
        }

        int size = bfs.get(0).getFilterLen();
        BloomFilter result = new BloomFilter(size);

        for (BloomFilter bf : bfs) {
            if (bf.getFilterLen() != size) {
                throw new IllegalArgumentException("All filters must have same size");
            }
            result.setFilter(result.getFilter() | bf.getFilter());
        }

        return result;
    }
}

