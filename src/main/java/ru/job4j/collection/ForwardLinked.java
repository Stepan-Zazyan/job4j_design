package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements LinkedList<T> {
    private Node<T> head;
    private int size = 0;
    private int modCount = 0;

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            size++;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
        size++;
        modCount++;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> tail = head;
        int counter = 0;
        while (counter < index) {
            tail = tail.next;
            counter++;
        }
        return tail.item;
    }

    /**
     * сохраняем главный указатель первого элемента
     * Сохраняем указатель на последующий элемент
     * меняем указатель первого элемента на null
     * передвигаем главный указатель на последующий элемент
     * сохраняем указатель на последующий элемент
     * последующий элемент должен указывать на предыдущий
     * Если последующий элемент не null повторить
     */
    public boolean revert() {
        if (size == 1 || size == 0) {
            return false;
        }
        Node<T> futherNext = head.next;
        int count = 0;
        while (futherNext != null) {
            count++;
            Node<T> current = head;
            Node<T> currentNext = futherNext;
            if (count == 1) {
                head.next = null;
            }
            head = currentNext;
            futherNext = head.next;
            head.next = current;
        }
        return true;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T value = head.item;
        Node<T> afterHead = head.next;
        head.item = null;
        head.next = null;
        head = afterHead;
        modCount++;
        size--;
        return value;
    }

    @Override
    public void addFirst(T value) {
        if (head == null) {
            head = new Node<>(value, null);
        } else {
            head = new Node<>(value, head);
        }
        modCount++;
        size++;
    }

    @Override
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

