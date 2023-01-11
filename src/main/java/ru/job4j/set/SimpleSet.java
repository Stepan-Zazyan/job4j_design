package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        boolean rsl = true;
        for (T x : set) {
            if (x.equals(value)) {
                rsl = false;
                break;
            }
        }
        set.add(value);
        return rsl;
    }

    @Override
    public boolean contains(T value) {
        boolean rsl = false;
        for (T x : set) {
            if (x.equals(value)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
