package as.list;

import java.util.*;

public class MyLinkedList<E> implements List<E> {

    private int size = 0;
    private Node<E> first;
    private Node<E> last;

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

    //TODO
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

    //TODO
    public <T> T[] toArray(T[] a) {
        return null;
    }

    public boolean add(E e) {
        if (first == null) {
            first = new Node(null, e, null);
            last = first;
        } else {
            Node<E> newNode = new Node(last, e, null);
            last.next = newNode;
            last = newNode;
        }
        size++;
        return true;
    }

    //TODO
    public void add(int index, E element) {

    }

    public E remove(int index) {
        checkIndex(index);
        if (index == 0) {
            removeFirst();
        } else if (index == size - 1) {
            removeLast();
        } else {
            int counter = 0;
            for (Node x = first; x != null; x = x.next) {
                if (counter == index) {
                    //check
                    Node<E> toRemove = x;
                    x.prev = toRemove.next;
                    x.next = toRemove.prev;
                    return toRemove.item;
                }
                counter++;
            }
        }
        return null;
    }

    public boolean remove(Object o) {
        int index = 0;
        if (o == null) {
            for (Node x = first; x != null; x = x.next) {
                if (x.item == null) {
                    remove(index);
                    return true;
                }
                index++;
            }
        } else {
            for (Node x = first; x != null; x = x.next) {
                if (x.item.equals(o)) {
                    remove(index);
                    return true;
                }
                index++;
            }
        }
        return false;
    }

    public E removeFirst() {
        Node temp = first;
        first = first.next;
        first.prev = null;
        return (E) temp.item;
    }

    public E removeLast() {
        Node temp = last;
        last = last.prev;
        last.next = null;
        return (E) temp.item;
    }

    //TODO
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    //TODO
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    //TODO
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    //TODO
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    //TODO
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
        Node<E> it = first;
        for (int i = 0; i <= index; i++) {
            if (i == index) {
                return it.item;
            }
            it = it.next;
        }
        return null;
    }

    public E set(int index, E element) {
        checkIndex(index);
        Node<E> it = first;
        for (int i = 0; i <= index; i++) {
            if (index == i) {
                E oldElement = it.item;
                it.item = element;
                return oldElement;
            }
            it = it.next;
        }
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
        int index = size;
        if (o == null) {
            for (Node x = last; x != null; x = x.prev) {
                index--;
                if (x.item == null) {
                    return index;
                }
            }
        } else {
            for (Node x = last; x != null; x = x.prev) {
                index--;
                if (x.item.equals(o)) {
                    return index;
                }
            }
        }
        return -1;
    }

    //TODO
    public ListIterator<E> listIterator() {
        return null;
    }

    //TODO
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    //TODO
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    void checkIndex(int index) {
        if (index > size)
            throw new IndexOutOfBoundsException();
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
