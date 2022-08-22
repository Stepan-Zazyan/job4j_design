package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while ((data[column].length == 0 || column == data[column].length - 1) && row < data.length) {
           row++;
           column = 0;
        }
        return row < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }

/*
    public boolean hasNext() {
        return row < data.length && column < data[column].length;
    }
*/
/*@Override
public Integer next() {
    if (!hasNext()) {
        throw new NoSuchElementException();
    }
    if (data[column].length == 0) {
        column = 0;
        return data[row++][column];
    }
    if (data[column].length == 1) {
        return data[row++][column];
    }
    if (column < data[column].length) {
        return data[row][column++];
    }
    column = 0;
    return data[row++][column];
}*/
}