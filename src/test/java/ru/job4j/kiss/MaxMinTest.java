package ru.job4j.kiss;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MaxMinTest {

    @Test
    void max() {
        List<Integer> list = List.of(1,4,3,2);
        int max = 4;
        MaxMin<Integer> maxMin = new MaxMin<>();
        int result = maxMin.max(list, Integer::compareTo);
        assertEquals(max, result);
    }

    @Test
    void min() {
        List<Integer> list = List.of(1,4,3,2);
        int min = 1;
        MaxMin<Integer> maxMin = new MaxMin<>();
        int result = maxMin.min(list, Integer::compareTo);
        assertEquals(min, result);
    }
}