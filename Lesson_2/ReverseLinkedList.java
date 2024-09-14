public class ReverseLinkedList {
    public void reverseList(LinkedList2 list) {
        if (list.head == null) {
            return;
        }

        Node fromTail = list.tail;
        for (Node fromHead = list.head; fromTail != fromHead.prev && fromHead != fromTail; fromHead = fromHead.next) {
            swap(fromHead, fromTail);
            fromTail = fromTail.prev;
        }
    }

    public void swap(Node n1, Node n2) {
        n1.value = -n1.value - n2.value;
        n2.value = -n1.value - n2.value;
        n1.value = -n1.value - n2.value;
    }
}

