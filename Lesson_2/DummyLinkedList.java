import java.util.ArrayList;

public class DummyLinkedList {
    private Node head;
    private Node tail;

    public DummyLinkedList() {
        Node dummyHead = new Node(-1);
        Node dummyTail = new Node(-1);
        head = dummyHead;
        tail = dummyTail;
        head.next = tail;
        tail.prev = head;
    }

    public void addInTail(Node _item)
    {
        Node last = tail.prev;
        last.next = _item;
        _item.prev = last;
        _item.next = tail;
        tail.prev = _item;
    }

    public Node find(int _value)
    {
        for (Node node = head.next; node != tail; node = node.next) {
            if (node.value == _value) {
                return node;
            }
        }

        return null;
    }

    public ArrayList<Node> findAll(int _value)
    {
        ArrayList<Node> nodes = new ArrayList<>();
        for (Node node = head.next; node != tail; node = node.next) {
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
        head = null;
        tail = null;
    }

    public int count()
    {
        int count = 0;
        for (Node node = head.next; node != tail; node = node.next) {
            count++;
        }

        return count;
    }

    public void insertAfter(Node _nodeAfter, Node _nodeToInsert)
    {
        if(_nodeAfter == null) {
            _nodeAfter = head;
        }
        Node next = _nodeAfter.next;
        _nodeAfter.next = _nodeToInsert;
        _nodeToInsert.prev = _nodeAfter;
        _nodeToInsert.next = next;
        next.prev = _nodeToInsert;
    }
}

