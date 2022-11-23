package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private Node<E> head;

    @Override
    public void add(E value) {
        //создаем новую ноду
        Node<E> node = new Node<E>(value, null);
        //Если список пуст, то ноду делаем заглавной
        if (head == null) {
            head = node;
            return;
        }
        //Если в списке есть ноды, то объявляем ноду и к ней
        //можем добавлять другие ноды
        Node<E> tail = head;
        //пока указатель ноды не равен null
        //начинаем двигаться по списку и идем до конца
        while (tail.next != null) {
            //инициализируем ноду указателем ноды
            tail = tail.next;
        }
        //дойдя до конца
        // записываем в последний указатель ноду
        tail.next = node;
    }

    @Override
    public E get(int index) {
        Node<E> tail = head;
        int counter = 0;
        //пока указатель ноды не равен null
        //начинаем двигаться по списку и идем до конца
        while (counter < index) {
            //инициализируем ноду указателем ноды
            tail = tail.next;
            // прибывляем счетчик
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
                //записываем значение ноды
                E value = node.item;
                //записываем в ноду ее указатель(сл. ноду)
                node = node.next;
                //возваращаем значение следующей ноды
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
