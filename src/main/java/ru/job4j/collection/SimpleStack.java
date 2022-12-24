package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleStack<T> {
    private Node<T> head;
    private int size = 0;
    private int modCount = 0;

    public T pop() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T value = head.item;
        head = head.next;
        modCount++;
        return value;
    }

    public void push(T value) {
        if (head == null) {
            head = new Node<T>(value, null);
        }
        Node<T> afterHead = head;
        head.item = value;
        head.next = afterHead;
        modCount++;
    }

    public Iterator<T> iterator() {
        int expectedModCount = modCount;
        return new Iterator<T>() {
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
