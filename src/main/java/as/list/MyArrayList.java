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

    // TODO: toArray method
    public Object[] toArray(Object[] a) {
        return null;
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
        fastRemove(index);
        return toRemove;
    }

    public boolean containsAll(Collection c) {
        Iterator itr = c.iterator();
        while (itr.hasNext()) {
            if (!contains(itr.next())) {
                return false;
            }
        }
        return true;
    }

    //TODO: Check on the exceptions
    public boolean addAll(Collection c) {
        // when return true
        Iterator itr = c.iterator();
        while (itr.hasNext()) {
            add(itr.next());
        }
        return false;
    }

    //TODO: Check on the exception
    public boolean addAll(int index, Collection c) {
        Iterator itr = c.iterator();
        int counter = index;
        while (itr.hasNext()) {
            add(counter, itr.next());
            counter++;
        }
        return false;
    }

    //TODO: Check on the exception
    public boolean removeAll(Collection c) {
        Iterator itr = c.iterator();
        while (itr.hasNext()) {
            remove(itr.next());
        }
        return false;
    }

    //TODO: Check on the exception
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
        System.arraycopy(data, index, data, index + 1, size);
        data[index + 1] = element;
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

    public ListIterator<E> listIterator() {
        return new ListItr();
    }

    public ListIterator<E> listIterator(int index) {
        return new ListItr(index);
    }

    //TODO: sublist
    public List<E> subList(int fromIndex, int toIndex) {
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
        System.arraycopy(data, index + 1, data, index, size);
        size--;
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

        @Override
        public void remove() {
            MyArrayList.this.remove(cursor);
            cursor--;
        }
    }

    private class ListItr extends Itr implements ListIterator<E> {

        ListItr() {
        }

        ListItr(int index) {
            super();
            this.cursor = index;
        }

        @Override
        public boolean hasPrevious() {
            return cursor != 0;
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
        public void set(E e) {
            MyArrayList.this.set(cursor, e);
        }

        @Override
        public void add(E e) {
            MyArrayList.this.add(cursor, e);
        }
    }
}
