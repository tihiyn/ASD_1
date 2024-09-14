public class Sort {
    public void sort(LinkedList2 list) {
        if (list.head == null) {
            return;
        }

        for (Node i = list.head; i != null; i = i.next) {
            Node min = i;

            for (Node j = i.next; j != null; j = j.next) {
                if (j.value < min.value) {
                    min = j;
                }
            }
            
            if (min != i) {
                swap(i, min);
            }
        }
    }

    public void swap(Node n1, Node n2) {
        n1.value = -n1.value - n2.value;
        n2.value = -n1.value - n2.value;
        n1.value = -n1.value - n2.value;
    }
}

