import java.util.ArrayList;

public class DummyLinkedList {
    private Node head;
    private Node tail;

    public DummyLinkedList() {
        head = new DummyNode();
        tail = new DummyNode();
        head.next = tail;
        tail.prev = head;
    }

    public void addInTail(Node _item)
    {
        insertAfter(tail.prev, _item);
    }

    public Node find(int _value)
    {
        for (Node node = head.next; !(node instanceof DummyNode); node = node.next) {
            if (node.value == _value) {
                return node;
            }
        }

        return null;
    }

    public ArrayList<Node> findAll(int _value)
    {
        ArrayList<Node> nodes = new ArrayList<>();
        for (Node node = head.next; !(node instanceof DummyNode); node = node.next) {
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
        for (Node node = head.next; !(node instanceof DummyNode); node = node.next) {
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

class DummyNode extends Node {
    public DummyNode() {
        super(-1);
    }
}

