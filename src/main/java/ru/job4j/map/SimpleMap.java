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
        if (capacity * LOAD_FACTOR <= count) {
            expand();
        }
        boolean rsl = false;
        int i = indexFor(hash(Objects.hashCode(key)));
        if (Objects.isNull(table[i])) {
            rsl = true;
            table[i] = new MapEntry<>(key, value);
            count++;
            modCount++;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (int i = 0; i < count; i++) {
            int h = indexFor(hash(Objects.hashCode(table[i].value)));
            if (table[i] == null) {
                continue;
            }
            newTable[h] = table[i];
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        V value = null;
        int i = indexFor(hash(Objects.hashCode(key)));
        if (Objects.equals(table[i].key, key)) {
            value = table[i].value;
        }
        return value;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int i = indexFor(hash(Objects.hashCode(key)));
        if (Objects.equals(table[i].key, key)) {
            table[i].value = null;
            table[i].key = null;
            rsl = true;
            count--;
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
                while (count < table.length && table[count] == null) {
                    count++;
                }
                return count < table.length;
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

        public MapEntry() {
        }

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

}