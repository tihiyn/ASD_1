public class SortAndMerge {
    public LinkedList2 sortAndMerge(LinkedList2 first, LinkedList2 second) {
        sort(first);
        sort(second);

        LinkedList2 result = new LinkedList2();
        Node firstPointer = first.head;
        Node secondPointer = second.head;

        while (firstPointer != null && secondPointer != null) {
            if (firstPointer.value >= secondPointer.value) {
                result.addInTail(new Node(secondPointer.value));
                secondPointer = secondPointer.next;
            } else {
                result.addInTail(new Node(firstPointer.value));
                firstPointer = firstPointer.next;
            }
        }

        for (; firstPointer != null; firstPointer = firstPointer.next) {
            result.addInTail(new Node(firstPointer.value));
        }

        for (; secondPointer != null; secondPointer = secondPointer.next) {
            result.addInTail(new Node(secondPointer.value));
        }

        return result;
    }
}

