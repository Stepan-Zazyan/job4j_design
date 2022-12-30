package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleStack<T> {
    private Node<T> head;
    private int size = 0;
    private int modCount = 0;

    private ForwardLinked<T> linked = new ForwardLinked<T>();

    public T pop() {
       return linked.deleteFirst();
    }

    public void push(T value) {
        if (head == null) {
            head = new Node<T>(value, null);
        } else {
            //сохранил текущий ук
            Node<T> currentHead = head;
            //иниц значение
            head.item = value;
            //иниц ссылку на след ноду
            head.next = head;
            //переместил указатель
            head = new Node<T>(value, currentHead);
        }
        modCount++;
        size++;
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
