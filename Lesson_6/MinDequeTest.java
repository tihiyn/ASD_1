import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinDequeTest {
    @Test
     void getMin() {
        MinDeque<Integer> deque = new MinDeque<>();

        deque.addTail(3);
        deque.addTail(4);
        deque.addTail(5);
        deque.addTail(6);
        deque.addTail(7);

        assertEquals(3, deque.getMin());

        deque.removeFront();
        deque.removeFront();

        assertEquals(5, deque.getMin());

        deque.removeTail();
        deque.removeTail();

        assertEquals(5, deque.getMin());
    }
}

