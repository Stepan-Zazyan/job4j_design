package ru.job4j.map;

import java.util.*;


public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (capacity * LOAD_FACTOR < count) {
            expand();
        }
        boolean rsl = true;
        for (MapEntry<K, V> x : table) {
            if (x.key == key) {
                rsl = false;
                break;
            }
        }
        if (rsl) {
            for (MapEntry<K, V> entry : table) {
                if (entry.key == null) {
                    entry.value = value;
                    entry.key = key;
                }
            }
        }
        count++;
        modCount++;
        return rsl;
    }

    private int hash(int hashCode) {
        return Objects.hash(hashCode);
    }

    private int indexFor(int hash) {
        return hash(hash) & (capacity - 1);
    }

    private void expand() {
        if (table.length == 0) {
            table = Arrays.copyOf(table, table.length + 1);
        } else {
            table = Arrays.copyOf(table, table.length * 2);
        }
    }

    @Override
    public V get(K key) {
        V value = null;
        for (MapEntry<K, V> entry : table) {
            if (entry.key == key) {
                value = entry.value;
            }
        }
        return value;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        for (MapEntry<K, V> entry : table) {
            if (entry.key == key) {
                entry.value = null;
                entry.key = null;
                rsl = true;
                count--;
            }
        }
        modCount++;
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        int expectedModCount = modCount;
        return new Iterator<K>() {
            int count = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return count < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[count++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

}