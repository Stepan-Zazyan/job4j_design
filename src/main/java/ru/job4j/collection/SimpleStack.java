package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleStack<T> {
    private Node<T> head;
    private int modCount = 0;

    private final ForwardLinked<T> linked = new ForwardLinked<>();

    public T pop() {
        return linked.deleteFirst();
    }

    public void push(T value) {
        linked.addFirst(value);
    }

    public Iterator<T> iterator() {
        int expectedModCount = modCount;
        return new Iterator<>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.item;
                node = node.next;
                return value;
            }
        };
    }
}
