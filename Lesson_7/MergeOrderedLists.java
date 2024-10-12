import java.util.ArrayList;
import java.util.List;

public class MergeOrderedLists<T> {
    public OrderedList<T> merge(List<OrderedList<T>> lists) {
        if (lists.isEmpty()) {
            return null;
        }

        lists.removeIf(list -> list == null || list.size == 0);
        if (lists.isEmpty()) {
            return null;
        }
        
        List<Node<T>> listOfCurrent = new ArrayList<>(lists.size());
        Node<T> nodeToCompare = lists.get(0).head;
        int generalSize = 0;
        boolean is_asc = lists.get(0).is_ascending();

        for (OrderedList<T> list: lists) {
            listOfCurrent.add(list.head);
            generalSize += list.size;

            if (list.is_ascending() != is_asc) {
                return null;
            }

            if (is_asc == list.compare(list.tail.value, nodeToCompare.value) >= 0) {
                nodeToCompare = list.tail;
            }
        }

        OrderedList<T> mergeList = new OrderedList<>(is_asc);
        Node<T> nodeToPush = nodeToCompare;
        int index = 0;

        for (int i = 0; i < generalSize; i++) {
            for (int j = 0; j < listOfCurrent.size(); j++) {
                if (listOfCurrent.get(j) == null) {
                    continue;
                }

                if (is_asc == mergeList.compare(nodeToPush.value, listOfCurrent.get(j).value) >= 0) {
                    index = j;
                    nodeToPush = listOfCurrent.get(j);
                }
            }

            mergeList.add(nodeToPush.value);
            listOfCurrent.set(index, nodeToPush.next);
            nodeToPush = nodeToCompare;
        }

        return mergeList;
    }
}

