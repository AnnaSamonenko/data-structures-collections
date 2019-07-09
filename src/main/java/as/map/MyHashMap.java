package as.map;

import java.util.*;

public class MyHashMap<K, V> implements Map {

    private static int size = 0;
    private Node[] bucket;
    private int defaultSize = 16;
    private Set keySet;
    private Set values;

    public MyHashMap() {
        bucket = new Node[defaultSize];
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
        int index = key.hashCode() % bucket.length;
        if (bucket[index] != null) {
            Node node = bucket[index];
            if (node.getKey().equals(key)) {
                return true;
            } else {
                while (node.getNext() != null) {
                    node = node.getNext();
                    if (node.getKey().equals(key)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        int index = key.hashCode() % bucket.length;

        Node head = bucket[index];
        if (head == null)
            return null;
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
        Node newNode = new Node(key, value);
        int index = (int) newNode.getHash() % bucket.length;

        if (bucket[index] == null) {
            bucket[index] = newNode;
        } else {
            Node entry = (Node) bucket[index];
            while (entry.getNext() != null) {
                entry = entry.getNext();
            }
            entry.setNext(newNode);
        }
        size++;
        return newNode;
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
        for (int i = 0; i < bucket.length; i++) {
            bucket[i] = null;
        }
        size = 0;
    }

    @Override
    public Set keySet() {
        Set<K> res = new HashSet();
        for (Node node : bucket) {
            if (node != null) {
                res.add((K) node.getKey());
                while (node.getNext() != null) {
                    node = node.getNext();
                    res.add((K) node.getKey());
                }
            }
        }
        return res;
    }

    @Override
    public Collection values() {
        return null;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return null;
    }

    class Node<K, V> implements Map.Entry {

        private long hash;
        private K key;
        private V value;
        private Node next;

        public Node(K key, V value) {
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

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

}
