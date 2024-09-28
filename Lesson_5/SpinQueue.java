public class SpinQueue<T> {
    public void spin(Queue<T> queue, int N) {
        for (int i = 0; i < N; i++) {
            queue.enqueue(queue.dequeue());
        }
    }
}

