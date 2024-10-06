import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinDequeTest {
    @Test
     void getMin() {
        MinDeque<Integer> deque = new MinDeque<>();

        deque.addTail(3);
        deque.addTail(5);
        deque.addTail(4);
        assertEquals(3, deque.getMin());

        deque.addTail(2);
        assertEquals(2, deque.getMin());

        assertEquals(2, deque.minDeque.removeTail());
        assertEquals(3, deque.minDeque.removeTail());
        assertEquals(4, deque.minDeque.removeTail());
        assertEquals(5, deque.minDeque.removeTail());
        assertNull(deque.minDeque.removeTail());
    }
}

