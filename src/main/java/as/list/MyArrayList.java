package as.list;

import java.util.*;

public class MyArrayList<E> implements List<E> {

    private Object[] data;

    private int size;

    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList() {
        data = new Object[DEFAULT_CAPACITY];
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

    public Iterator iterator() {
        return new Itr();
    }

    public Object[] toArray() {
        return Arrays.copyOf(data, size);
    }

    // TODO
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    public boolean add(Object o) {
        ensureCapacity();
        data[size++] = o;
        return true;
    }

    public boolean remove(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (data[i] == null) {
                    fastRemove(i);
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (data[i].equals(o)) {
                    fastRemove(i);
                    return true;
                }
            }
        }
        return false;
    }

    public E remove(int index) {
        rangeCheck(index);
        E toRemove = get(index);
        for (int i = index; i < size; i++) {
            fastRemove(i);
        }
        return toRemove;
    }

    //TODO
    public boolean containsAll(Collection c) {
        return true;
    }

    //TODO
    public boolean addAll(Collection c) {
        return false;
    }

    //TODO
    public boolean addAll(int index, Collection c) {
        return false;
    }

    //TODO
    public boolean removeAll(Collection c) {
        return false;
    }

    //TODO
    public boolean retainAll(Collection c) {
        return false;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        rangeCheck(index);
        return (E) data[index];
    }

    @SuppressWarnings("unchecked")
    public E set(int index, E element) {
        rangeCheck(index);
        E oldValue = (E) data[index];
        data[index] = element;
        return oldValue;
    }

    public void add(int index, Object element) {
        ensureCapacity();
        rangeCheck(index);
        size++;
        System.arraycopy(data, index - 1, data, index, size);
        data[index] = element;
    }

    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i != 0; i--) {
                if (data[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i != 0; i--) {
                if (data[i].equals(o)) {
                    return i;
                }
            }
        }

        return -1;
    }

    public ListIterator listIterator() {
        return new ListItr();
    }

    //TODO
    public ListIterator listIterator(int index) {
        return null;
    }

    //TODO
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    private void rangeCheck(int index) {
        if (index > size)
            throw new IndexOutOfBoundsException("There no element with index: " + index);
    }

    private void ensureCapacity() {
        if (size == data.length)
            data = Arrays.copyOf(data, size * 2);
    }

    private void fastRemove(int index) {
        data[index] = data[++index];
        data[--size] = null;
    }

    private class Itr implements Iterator<E> {
        int cursor = -1;

        @Override
        public boolean hasNext() {
            return cursor != size - 1;
        }

        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            return (E) data[++cursor];
        }
    }

    private class ListItr extends Itr implements ListIterator<E> {

        @Override
        public boolean hasPrevious() {
            return cursor != -1;
        }

        @Override
        @SuppressWarnings("unchecked")
        public E previous() {
            return (E) data[--cursor];
        }

        @Override
        public int nextIndex() {
            return cursor + 1;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(E e) {

        }

        @Override
        public void add(E e) {

        }
    }
}
