public class MergeOrderedLists<T> {
    public OrderedList<T> merge(OrderedList<T> first, OrderedList<T> second) {
        if (first == null || second == null || first.is_ascending() != second.is_ascending()) {
            return null;
        }

        OrderedList<T> mergeList = new OrderedList<>(first.is_ascending());

        Node<T> firstPointer = first.head;
        Node<T> secondPointer = second.head;
        int comparing;

        while (firstPointer != null && secondPointer != null) {
            comparing = first.compare(firstPointer.value, secondPointer.value);

            if (comparing >= 0 == mergeList.is_ascending()) {
                mergeList.add(secondPointer.value);
                secondPointer = secondPointer.next;
            }

            if (comparing < 0 == mergeList.is_ascending()) {
                mergeList.add(firstPointer.value);
                firstPointer = firstPointer.next;
            }
        }

        for (Node<T> node = firstPointer; node != null; node = node.next) {
            mergeList.add(node.value);
        }

        for (Node<T> node = secondPointer; node != null; node = node.next) {
            mergeList.add(node.value);
        }

        return mergeList;
    }
}

