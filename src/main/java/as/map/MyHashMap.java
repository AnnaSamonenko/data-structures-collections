package as.map;

import java.util.*;

public class MyHashMap<K, V> implements Map {

    private static int size = 0;
    private Entry[] bucket;
    private int defaultSize = 16;

    public MyHashMap() {
        bucket = new Entry[defaultSize];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        int index = key.hashCode() % bucket.length;

        MyEntry head = (MyEntry) bucket[index];
        if (head.getKey().equals(key)) {
            return (V) head.getValue();
        } else {
            while (head.getNext() != null) {
                head = head.next;
                if (head.getKey().equals(key)) {
                    return (V) head.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public Object put(Object key, Object value) {
        MyEntry newEntry = new MyEntry(key, value);
        int index = (int) newEntry.getHash() % bucket.length;

        if (bucket[index] == null) {
            bucket[index] = newEntry;
        } else {
            MyEntry entry = (MyEntry) bucket[index];
            while (entry.getNext() != null) {
                entry = entry.getNext();
            }
            entry.setNext(newEntry);
        }
        size++;
        return newEntry;
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map m) {
    }

    @Override
    public void clear() {
    }

    @Override
    public Set keySet() {
        return null;
    }

    @Override
    public Collection values() {
        return null;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return null;
    }

    private class MyEntry<K, V> implements Map.Entry {

        private long hash;
        private K key;
        private V value;
        private MyEntry next;

        public MyEntry(K key, V value) {
            this.key = key;
            this.value = value;
            hash = key.hashCode();
        }

        @Override
        public Object getKey() {
            return key;
        }

        @Override
        public Object getValue() {
            return value;
        }

        @Override
        public Object setValue(Object value) {
            this.value = (V) value;
            return value;
        }

        public long getHash() {
            return hash;
        }

        public MyEntry getNext() {
            return next;
        }

        public void setNext(MyEntry next) {
            this.next = next;
        }
    }

}
