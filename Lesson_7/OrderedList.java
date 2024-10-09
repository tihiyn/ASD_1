import java.math.BigDecimal;
import java.util.*;


class Node<T>
{
    public T value;
    public Node<T> next, prev;

    public Node(T _value)
    {
        value = _value;
        next = null;
        prev = null;
    }
}

public class OrderedList<T>
{
    public Node<T> head, tail;
    private boolean _ascending;
    private int size;

    public OrderedList(boolean asc)
    {
        head = null;
        tail = null;
        _ascending = asc;
        size = 0;
    }

    public int compare(T v1, T v2)
    {
        if (v1 instanceof String && v2 instanceof String) {
            return (((String) v1).trim()).compareTo(((String) v2).trim());
        }

        if (v1 instanceof Number && v2 instanceof Number) {
            return BigDecimal.valueOf(((Number) v1).doubleValue()).compareTo(BigDecimal.valueOf(((Number) v2).doubleValue()));
        }

        throw new IllegalArgumentException("Not comparable types");
    }

    public void add(T value)
    {
        Node<T> newNode = new Node<>(value);
        size++;

        if (head == null) {
            head = newNode;
            tail = newNode;
            return;
        }

        if (compare(value, tail.value) >= 0 == _ascending) {
            addInTail(value);
            return;
        }

        if (compare(value, head.value) <= 0 == _ascending) {
            addInHead(value);
            return;
        }

        int comparing;
        for (Node<T> node = head; node != null; node = node.next) {
            comparing = compare(value, node.value);
            if ((_ascending && comparing < 0) || (!_ascending && comparing > 0)) {
                if (node.prev == null) {
                    newNode.next = node;
                    node.prev = newNode;
                    head = newNode;
                    return;
                }

                newNode.prev = node.prev;
                newNode.next = node;
                node.prev.next = newNode;
                node.prev = newNode;
                return;
            }
        }

        newNode.prev = tail;
        tail.next = newNode;
        tail = newNode;
    }

    private void addInTail(T val) {
        Node<T> node = new Node<>(val);
        tail.next = node;
        node.prev = tail;
        tail = node;
    }

    private void addInHead(T val) {
        Node<T> node = new Node<>(val);
        head.prev = node;
        node.next = head;
        head = node;
    }

    public Node<T> find(T val)
    {
        for (Node<T> node = head; node != null; node = node.next) {
            int res = compare(node.value, val);
            if (res == 0) {
                return node;
            }

            if (_ascending && res > 0) {
                return null;
            }

            if (!_ascending && res < 0) {
                return null;
            }
        }

        return null;
    }

    public void delete(T val)
    {
        if (head == null) {
            return;
        }

        Node<T> node = find(val);
        if (node == null) {
            return;
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
        size--;
    }

    public void clear(boolean asc)
    {
        _ascending = asc;
        head = null;
        tail = null;
        size = 0;
    }

    public int count()
    {
        return size;
    }

    ArrayList<Node<T>> getAll()
    {
        ArrayList<Node<T>> r = new ArrayList<Node<T>>();
        Node<T> node = head;
        while(node != null)
        {
            r.add(node);
            node = node.next;
        }
        return r;
    }

    public boolean is_ascending() {
        return _ascending;
    }

    public void removeDuplicates() {
        for (Node<T> node = head; node.next != null; node = node.next) {
            if (node.value.equals(node.next.value)) {
                delete(node.value);
            }
        }
    }

    public boolean contains(OrderedList<T> subList) {
        if (subList == null) {
            return true;
        }

        if (subList.size > 1 && this._ascending != subList._ascending) {
            return false;
        }

        Node<T> secondPointer = subList.head;
        int counter = 0;

        for (Node<T> node = this.head; node != null; node = node.next) {
            if (counter == subList.size) {
                return true;
            }

            if (secondPointer == null) {
                return false;
            }

            if (compare(node.value, secondPointer.value) == 0) {
                counter++;
                secondPointer = secondPointer.next;
                continue;
            }

            counter = 0;
        }

        return false;
    }
}

