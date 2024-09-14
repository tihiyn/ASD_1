public class ReverseLinkedList {
    public void reverseList(LinkedList2 list) {
        if (list.head == null) {
            return;
        }

        Node formTail = list.tail;
        for (Node fromHead = list.head; formTail != fromHead.prev && fromHead != formTail; fromHead = fromHead.next) {
            swap(fromHead, formTail);
            formTail = formTail.prev;
        }
    }

    public void swap(Node n1, Node n2) {
        n1.value = -n1.value - n2.value;
        n2.value = -n1.value - n2.value;
        n1.value = -n1.value - n2.value;
    }
}

