package as.map;

import java.util.*;

public class MyHashMap<K, V> implements Map {

    private static int size = 0;
    private Node[] bucket;
    private static final int DEFAULT_SIZE = 16;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    public MyHashMap() {
        bucket = new Node[DEFAULT_SIZE];
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
        for (Node node : bucket) {
            if (node != null) {
                if (Objects.equals(node.getValue(), value))
                    return true;
                while (node.getNext() != null) {
                    if (Objects.equals(node.getValue(), value))
                        return true;
                    node = node.getNext();
                }
            }
        }
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

        if (containsKey(key)) {
            if (bucket[index].getKey().equals(key)) {
                bucket[index].setValue(value);
                return bucket[index];
            } else {
                Node entry = bucket[index];
                while (entry.getNext() != null) {
                    if (entry.getKey().equals(key)) {
                        entry.setValue(value);
                        return value;
                    }
                    entry = entry.getNext();
                }
            }
        }

        if (bucket[index] == null) {
            bucket[index] = newNode;
        } else {
            Node entry = bucket[index];
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
        int index = key.hashCode() % bucket.length;
        Node node = bucket[index];
        if (node.getKey().equals(key)) {
            bucket[index] = null;
        } else {
            while (node.getNext() != null) {
                if (node.getNext().getKey().equals(key)) {
                    Node toRemove = node.getNext();
                    node.setNext(node.getNext().getNext());
                    return (V) toRemove.getValue();
                }
                node = node.getNext();
            }
        }
        return (V) node.getValue();
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
        Set<Map.Entry<K, V>> res = new HashSet<>();
        for (Node<K, V> node : bucket) {
            if (node != null) {
                res.add(node);
                while (node.getNext() != null) {
                    node = node.getNext();
                    res.add(node);
                }
            }
        }
        return res;
    }

    static class Node<K, V> implements Map.Entry {

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
