package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;


public class MaxMin {

    public <T> T searchMin(List<T> list, Comparator<T> comparator) {
        return strangeComparison(list, (s, a) -> comparator.compare(s, a) > 0);
    }

    public <T> T searchMax(List<T> list, Comparator<T> comparator) {
        return strangeComparison(list, (s, a) -> comparator.compare(s, a) < 0);
    }

    private <T> T strangeComparison(List<T> list, BiPredicate<T, T> test) {
        Objects.checkIndex(0, list.size());
        T x = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (test.test(x, list.get(i))) {
                x = list.get(i);
            }
        }
        return x;
    }

}
