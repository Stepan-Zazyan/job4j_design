package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    private int countIn = 0;
    private int countOut = 0;

    public T poll() {
        if (countIn == 0 && countOut == 0) {
            throw new NoSuchElementException();
        }
        countIn--;
        return in.pop();
    }

    public void push(T value) {
        while (countIn > 0) {
            out.push(in.pop());
            countOut++;
            countIn--;
        }
        out.push(value);
        countOut++;
        while (countOut > 0) {
            in.push(out.pop());
            countOut--;
            countIn++;
        }
    }
}