public class DopZadacha {
    public LinkedList sumList(LinkedList first, LinkedList second) {
        LinkedList resultList = new LinkedList();
        if (first.count() == second.count()) {
            Node firstListNode = first.head;
            Node secondListNode = second.head;

            while (firstListNode != null) {
                int sum = firstListNode.value + secondListNode.value;
                resultList.addInTail(new Node(sum));

                firstListNode = firstListNode.next;
                secondListNode = secondListNode.next;
            }
        }

        return resultList;
    }
}

