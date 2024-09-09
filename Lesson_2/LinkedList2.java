import java.util.*;

public class LinkedList2
{
    public Node head;
    public Node tail;

    public LinkedList2()
    {
        head = null;
        tail = null;
    }

    public void addInTail(Node _item)
    {
        if (head == null) {
            this.head = _item;
            this.head.next = null;
            this.head.prev = null;
        } else {
            this.tail.next = _item;
            _item.prev = tail;
        }
        this.tail = _item;
    }

    public Node find(int _value)
    {
        if (head == null) {
            return null;
        }

        Node node = head;
        while (node != null) {
            if (node.value == _value) {
                return node;
            }

            node = node.next;
        }

        return null;
    }

    public ArrayList<Node> findAll(int _value)
    {
        ArrayList<Node> nodes = new ArrayList<Node>();
        Node node = head;
        while (node != null) {
            if (node.value == _value) {
                nodes.add(node);
            }
            node = node.next;
        }

        return nodes;
    }

    public boolean remove(int _value)
    {
        if (head == null) {
            return false;
        }

        Node node = find(_value);
        if (node == null) {
            return false;
        }

        if (node.prev == null) {
            head = node.next;
        } else {
            node.prev.next = node.next;
        }
        if (node.next == null) {
            tail = node.prev;
        } else {
            node.next.prev = node.prev;
        }

        return true;
    }

    public void removeAll(int _value)
    {
        boolean flag = true;
        while (flag) {
            flag = remove(_value);
        }
    }

    public void clear()
    {
        head = null;
        tail = null;
    }

    public int count()
    {
        if (head == null) {
            return 0;
        }

        int size = 1;
        Node node = head;
        while (node.next != null) {
            size++;
            node = node.next;
        }
        return size;
    }

    public void insertAfter(Node _nodeAfter, Node _nodeToInsert)
    {
        if (_nodeAfter == null) {
            _nodeToInsert.next = head;
            if (head == null) {
                tail = _nodeToInsert;
            } else {
                head.prev = _nodeToInsert;
            }
            head = _nodeToInsert;
        } else {
            Node next = _nodeAfter.next;
            _nodeAfter.next = _nodeToInsert;
            _nodeToInsert.prev = _nodeAfter;
            _nodeToInsert.next = next;
            if (next == null) {
                tail = _nodeToInsert;
            } else {
                next.prev = _nodeToInsert;
            }
        }
    }
}

class Node
{
    public int value;
    public Node next;
    public Node prev;

    public Node(int _value)
    {
        value = _value;
        next = null;
        prev = null;
    }
}

