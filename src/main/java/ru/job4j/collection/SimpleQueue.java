package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        //проверка на удаление из пустого объкта SimpleStack<>()
        // почему компилятор сообщает что он никогда не null?
        if (in == null) {
            throw new NoSuchElementException();
        }
        return in.pop();
    }

    public void push(T value) {
        //переложить тарелки 1 2 3 в другую стопку 3 2 1
        out.push(in.pop());
        //добавить значение 4 в новую стопку в начало 4 3 2 1
        out.push(value);
        //перекинуть в изначальную стопку из другой стопки 1 2 3 4
        in.push(out.pop());
    }
}