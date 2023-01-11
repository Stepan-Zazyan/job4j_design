package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        for (T x : set) {
            if (Objects.isNull(value) && null == x) {
                return false;
            }
            if (value.equals(x)) {
                return false;
            }
        }
        set.add(value);
        return true;
    }

    @Override
    public boolean contains(T value) {
        for (T x : set) {
            if (Objects.isNull(value) && null == x) {
                return true;
            }
            if (value.equals(x)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
