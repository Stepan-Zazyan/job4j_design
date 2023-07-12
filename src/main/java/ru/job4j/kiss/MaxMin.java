package ru.job4j.kiss;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;

public class MaxMin<T> implements Comparable<MaxMin<T>> {
    int value;

    public T searchMinMax(List<T> list, BiPredicate<T, T> function) {
        Objects.checkIndex(0, list.size());
        T x = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (function.test(list.get(i), x)) {
                x = list.get(i);
            }
        }
        return x;
    }


    public static void main(String[] args) {
        List<Integer> list = List.of(1, 4, 3, 2);
        MaxMin<Integer> mm = new MaxMin<>();
        System.out.println(mm.searchMinMax(list, (s, d) -> s < d));
    }

    @Override
    public int compareTo(MaxMin o) {
        return Integer.compare(value, o.value);
    }
}
