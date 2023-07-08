package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class MaxMin<T> {
    public T max(List<T> list, Comparator<T> comparator) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException();
        }
        T max = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (comparator.compare(max, list.get(i)) < 0) {
                max = list.get(i);
            }
        }
        return max;
    }

    public T min(List<T> list, Comparator<T> comparator) {
        Objects.checkIndex(0, list.size());
        T min = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (comparator.compare(min, list.get(i)) > 0) {
                min = list.get(i);
            }
        }
        return min;
    }
}
