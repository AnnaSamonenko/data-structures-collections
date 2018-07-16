package as.list;

import java.util.*;
import java.util.function.Consumer;

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

    public Iterator<E> iterator() {
        return new Itr();
    }

    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<E> x = first; x != null; x = x.next) {
            result[i++] = x.item;
        }
        return result;
    }

    //TODO: toArray
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @SuppressWarnings("unchecked")
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

    public void add(int index, E element) {
        Node<E> x = first;
        for (int i = 0; i <= index; i++) {
            if (i == index) {
                Node<E> nodeToAdd = new Node<>(x.prev, element, x.next);
                x.next = nodeToAdd;
                nodeToAdd.next.prev = nodeToAdd;
                size++;
            }
            x = x.next;
        }
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
                    x.prev.next = toRemove.next;
                    x.next.prev = toRemove.prev;
                    size--;
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

    @SuppressWarnings("unchecked")
    private E removeFirst() {
        Node temp = first;
        first = first.next;
        first.prev = null;
        size--;
        return (E) temp.item;
    }

    @SuppressWarnings("unchecked")
    private E removeLast() {
        Node temp = last;
        last = last.prev;
        last.next = null;
        size--;
        return (E) temp.item;
    }

    //TODO: Check on the exception
    public boolean containsAll(Collection<?> c) {
        Iterator itr = c.iterator();
        while (itr.hasNext()) {
            if (!contains(itr.next())) {
                return false;
            }
        }
        return true;
    }

    //TODO: Check on the exception
    public boolean addAll(Collection<? extends E> c) {
        Iterator itr = c.iterator();
        while (itr.hasNext()) {
            add((E) itr.next());
        }
        return false;
    }

    //TODO: Check on the exception
    public boolean addAll(int index, Collection<? extends E> c) {
        Iterator itr = c.iterator();
        int counter = index;
        while (itr.hasNext()) {
            add(counter, (E) itr.next());
            counter++;
        }
        return false;
    }

    //TODO: Check on the exception
    public boolean removeAll(Collection<?> c) {
        Iterator itr = c.iterator();
        while (itr.hasNext()) {
            remove(itr.next());
        }
        return false;
    }

    public boolean retainAll(Collection<?> c) {
        Iterator itr = iterator();
        while (itr.hasNext()) {
            if (!c.contains(itr.next())) {
                itr.remove();
            }
        }
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

    public ListIterator<E> listIterator() {
        return new ListItr();
    }

    //TODO: listIterator
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    //TODO: sublist
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    private void checkIndex(int index) {
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

    //TODO: Itr class
    private class Itr implements Iterator {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Object next() {
            return null;
        }

        @Override
        public void remove() {

        }

        @Override
        public void forEachRemaining(Consumer action) {

        }
    }

    //TODO: ListItr class
    private class ListItr implements ListIterator {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Object next() {
            return null;
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public Object previous() {
            return null;
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(Object o) {

        }

        @Override
        public void add(Object o) {

        }
    }
}
