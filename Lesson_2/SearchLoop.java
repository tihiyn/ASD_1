public class SearchLoop {
    public boolean searchLoop(LinkedList2 list) {
        if (list.head == null) {
            return false;
        }

        Node firstPointer = list.head;
        Node secondPointer = list.head;
        while (secondPointer != null && secondPointer.next != null) {
            firstPointer = firstPointer.next;
            secondPointer = secondPointer.next.next;

            if (firstPointer == secondPointer) {
                return true;
            }
        }

        firstPointer = list.tail;
        secondPointer = list.tail;
        while (secondPointer != null && secondPointer.prev != null) {
            firstPointer = firstPointer.prev;
            secondPointer = secondPointer.prev.prev;

            if (firstPointer == secondPointer) {
                return true;
            }
        }

        return false;
    }
}

