import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HashTableTest {
    static String[] collisionDataProvider(int size, int step, String core, HashTable table) {
        String[] keys = new String[size];
        int baseHash = table.hashFun(core);

        int generated = 0;
        for (int i = 0; i < Integer.MAX_VALUE && generated < size; i += step) {
            String newKey = core + i;
            if (table.hashFun(newKey) == baseHash) {
                keys[generated] = newKey;
                generated++;
            }
        }

        return keys;
    }

    static Stream<Arguments> hashFunTestDataProvider() {
        return Stream.of(
                Arguments.of(10, "Nikita", 1, 45),
                Arguments.of(100, "q1w2e3r4t5y5", 3, 4950),
                Arguments.of(5001, "-_-", 11, 12_502_500),
                Arguments.of(20_001, "DJDNK_DNJB", 53, 200_010_000)
        );
    }

    @ParameterizedTest
    @MethodSource("hashFunTestDataProvider")
    void hashFunTest(int size, String core, int step, int expectedCollisionsNum) {
        HashTable table = new HashTable(size, step);

        String[] collisions = collisionDataProvider(size, step, core, table);

        for (String c : collisions) {
            table.put(c);
        }

        // numOfCollision = (1 + size - 1) / 2 * (size - 1)
        assertEquals(expectedCollisionsNum, table.collisionCounter);
    }

    @ParameterizedTest
    @MethodSource("hashFunTestDataProvider")
    void saltHashFunTest(int size, String core, int step, int expectedCollisionsNum) {
        SaltHashTable table = new SaltHashTable(size, 1);

        String[] collisions = collisionDataProvider(size, step, core, new HashTable(size, step));

        for (String c : collisions) {
            table.put(c);
        }

        assertTrue(table.collisionCounter < expectedCollisionsNum);
    }
}

