package ru.job4j.collection;


public interface LinkedList<E> extends Iterable<E> {
    void add(E value);
    E get(int index);

    E deleteFirst();

    void addFirst(E value);
}
