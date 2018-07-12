package my_list;

import java.util.*;

public class MyLinkedList<E> implements List<E> {

    private int size = 0;
    Node<E> first;
    Node<E> last;

    public MyLinkedList() {
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    public Iterator<E> iterator() {
        return null;
    }

    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<E> x = first; x != null; x = x.next) {
            result[i++] = x.item;
        }
        return result;
    }

    public <T> T[] toArray(T[] a) {
        return null;
    }

    public boolean add(E e) {

        if (first == null) {
            first = new Node<E>(null, e, null);
            last = first;
        } else {
            Node<E> newNode = new Node<E>(last, e, null);
            last.next = newNode;
            last = newNode;
        }
        return true;
    }

    public boolean remove(Object o) {
        return true;
    }

    public boolean containsAll(Collection<?> c) {
        return false;
    }

    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    public boolean removeAll(Collection<?> c) {
        return false;
    }

    public boolean retainAll(Collection<?> c) {
        return false;
    }

    public void clear() {
        for (Node<E> x = first; x != null; ) {
            Node<E> next = x.next;
            x.prev = null;
            x.item = null;
            x.next = null;
            x = next;
        }
        first = last = null;
        size = 0;
    }

    public E get(int index) {
        checkIndex(index);
        for (int i = 0; i <= index; i++) {
            Node<E> it = first;
            if (i == index) {
                return it.item;
            }
            it = it.next;
        }
        return null;
    }

    public E set(int index, E element) {
        checkIndex(index);
        for (int i = 0; i <= index; i++) {
            Node<E> it = first;
            if (index == i) {
                E oldElement = it.item;
                it.item = element;
                return oldElement;
            }
            it = it.next;
        }
        return null;
    }

    public void add(int index, E element) {
    }

    public E remove(int index) {
        return null;
    }

    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<E> x = first; x.next != null; x = x.next) {
                if (x.item == null) {
                    return index;
                }
                index++;
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        return 0;
    }

    public ListIterator<E> listIterator() {
        return null;
    }

    public ListIterator<E> listIterator(int index) {
        return null;
    }

    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    void checkIndex(int index) {
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
