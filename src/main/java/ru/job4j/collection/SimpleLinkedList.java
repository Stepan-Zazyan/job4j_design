package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private Node<E> head;

    @Override
    public void add(E value) {
        Node<E> node = new Node<E>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<E> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    @Override
    public E get(int index) {
        Node<E> tail = head;
        int counter = 0;
        while (counter < index) {
            tail = tail.next;
            counter++;
            if (tail == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return tail.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E value = node.item;
                node = node.next;
                return value;
            }
        };
    }
}

class Node<E> {
    E item;
    Node<E> next;

    Node(E element, ru.job4j.collection.Node<E> next) {
        this.item = element;
        this.next = next;
    }
}
