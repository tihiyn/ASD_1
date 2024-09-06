import java.util.*;

public class LinkedList
{
    public Node head;
    public Node tail;

    public LinkedList()
    {
        head = null;
        tail = null;
    }

    public void addInTail(Node item) {
        if (this.head == null)
            this.head = item;
        else
            this.tail.next = item;
        this.tail = item;
    }

    public Node find(int value) {
        Node node = this.head;
        while (node != null) {
            if (node.value == value)
                return node;
            node = node.next;
        }
        return null;
    }

    public ArrayList<Node> findAll(int _value) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        Node node = this.head;
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
        if (this.head == null) {
            return false;
        }

        Node current = this.head;
        Node previous = this.head;

        if (current.value == _value) {
            this.head = current.next;
            if (this.head == null) {
                this.tail = null;
            }
            return true;
        }

        while (current.value != _value) {
            if (current.next == null) {
                return false;
            }

            previous = current;
            current = current.next;
        }

        if (current.next == null) {
            this.tail = previous;
        }
        previous.next = current.next;
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
        this.head = null;
        this.tail = null;
    }

    public int count()
    {
        if (this.head == null) {
            return 0;
        }

        int count = 1;

        Node node = this.head;
        while (node.next != null) {
            count++;
            node = node.next;
        }

        return count;
    }

    public void insertAfter(Node _nodeAfter, Node _nodeToInsert)
    {
        if (_nodeAfter == null) {
            _nodeToInsert.next = this.head;
            this.head = _nodeToInsert;
        }
        else {
            _nodeToInsert.next = _nodeAfter.next;
            _nodeAfter.next = _nodeToInsert;
            if (_nodeToInsert.next == null) {
                this.tail = _nodeToInsert;
            }
        }
    }
}

class Node
{
    public int value;
    public Node next;
    public Node(int _value)
    {
        value = _value;
        next = null;
    }
}

