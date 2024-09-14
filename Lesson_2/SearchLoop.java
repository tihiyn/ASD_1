import java.util.HashSet;
import java.util.Set;

public class SearchLoop {
    public boolean searchLoop(LinkedList2 list) {
        if (list.head == null) {
            return false;
        }

        Set<Node> set = new HashSet<>();
        for (Node node = list.head; node != null; node = node.next) {
            if (set.contains(node)) {
                return true;
            }
            set.add(node);
        }
        set.clear();
        for (Node node = list.tail; node != null; node = node.prev) {
            if (set.contains(node)) {
                return true;
            }
            set.add(node);
        }

        return false;
    }
}

