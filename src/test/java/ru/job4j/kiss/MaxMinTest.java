package ru.job4j.kiss;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MaxMinTest {

    @Test
    void searchMax() {
        List<Integer> list = List.of(1, 4, 3, 2);
        int max = 4;
        MaxMin maxMin = new MaxMin();
        Comparator<Integer> comp = Integer::compareTo;
        int result = maxMin.searchMax(list, comp);
        assertEquals(max, result);
    }

    @Test
    void searchMin() {
        List<Integer> list = List.of(1, 4, 3, 2);
        int min = 1;
        MaxMin maxMin = new MaxMin();
        Comparator<Integer> comp = Integer::compareTo;
        int result = maxMin.searchMin(list, comp);
        assertEquals(min, result);
    }

}