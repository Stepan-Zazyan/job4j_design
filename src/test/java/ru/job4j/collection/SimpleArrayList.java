package ru.job4j.collection;

import java.util.*;

import static java.util.Objects.checkIndex;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size = 0;

    private int modCount = 0;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    public T[] extendSize(T[] container) {
        return Arrays.copyOf(container, container.length * 2);
    }

    @Override
    public void add(T value) {
        if (container.length == size) {
            extendSize(container);
        }
        container[size] = value;
        size++;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, container.length);
        return container[index] = newValue;
    }

    @Override
    public T remove(int index) {
        for (int i = index; i < container.length - 1; i++) {
            container[i] = container[i + 1];
        }
        size--;
        modCount++;
        return container[index];
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        int expectedModCount = modCount;
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                return size < container.length;
            }

            @Override
            public T next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (size == container.length) {
                    return container[size - 1];
                }
                return container[size];
            }

        };
    }
}