package ru.job4j.kiss;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MaxMinTest {

    @Test
    void searchMax() {
        List<Integer> list = List.of(1, 4, 3, 2);
        int max = 4;
        MaxMin<Integer> maxMin = new MaxMin<>();
        int result = maxMin.searchMinMax(list, (s, d) -> s > d);
        assertEquals(max, result);
    }

    @Test
    void searchMin() {
        List<Integer> list = List.of(1, 4, 3, 2);
        int min = 1;
        MaxMin<Integer> maxMin = new MaxMin<>();
        int result = maxMin.searchMinMax(list, (s, d) -> s < d);
        assertEquals(min, result);
    }
}