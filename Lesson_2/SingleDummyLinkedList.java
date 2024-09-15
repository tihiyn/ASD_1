import java.util.ArrayList;

public class SingleDummyLinkedList {
    private Node dummyNode;

    public SingleDummyLinkedList() {
        dummyNode = new DummyNode();
        dummyNode.next = dummyNode;
        dummyNode.prev = dummyNode;
    }

    public void addInTail(Node _item)
    {
        insertAfter(dummyNode.prev, _item);
    }

    public Node find(int _value)
    {
        for (Node node = dummyNode.next; !(node instanceof DummyNode); node = node.next) {
            if (node.value == _value) {
                return node;
            }
        }

        return null;
    }

    public ArrayList<Node> findAll(int _value)
    {
        ArrayList<Node> nodes = new ArrayList<>();
        for (Node node = dummyNode.next; !(node instanceof DummyNode); node = node.next) {
            if (node.value == _value) {
                nodes.add(node);
            }
        }
        return nodes;
    }

    public boolean remove(int _value)
    {
        Node current = find(_value);
        if (current == null) {
            return false;
        }

        Node prev = current.prev;
        Node next = current.next;
        prev.next = next;
        next.prev = prev;

        return true;
    }

    public void removeAll(int _value)
    {
        boolean isRemoved = true;
        while (isRemoved) {
            isRemoved = remove(_value);
        }
    }

    public void clear()
    {
        dummyNode = null;
    }

    public int count()
    {
        int count = 0;
        for (Node node = dummyNode.next; !(node instanceof DummyNode); node = node.next) {
            count++;
        }

        return count;
    }

    public void insertAfter(Node _nodeAfter, Node _nodeToInsert)
    {
        if(_nodeAfter == null) {
            _nodeAfter = dummyNode;
        }
        Node next = _nodeAfter.next;
        _nodeAfter.next = _nodeToInsert;
        _nodeToInsert.prev = _nodeAfter;
        _nodeToInsert.next = next;
        next.prev = _nodeToInsert;
    }
}

