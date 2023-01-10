package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Predicate;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfterWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addAfter(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenRemoveIf() {
        input = new ArrayList<>(Arrays.asList(3, 1, 3));
        Predicate<Integer> predicate = s -> s == 1;
        ListUtils.removeIf(input, predicate);
        assertThat(input).containsSequence(3, 3);
    }

    @Test
    void whenReplaceIf() {
        input = new ArrayList<>(Arrays.asList(3, 1, 3));
        Predicate<Integer> predicate = s -> s == 1;
        ListUtils.replaceIf(input, predicate, 2);
        assertThat(input).containsSequence(3, 2, 3);
    }

    @Test
    void whenRemoveAll() {
        input = new ArrayList<>(Arrays.asList(3, 1, 4, 3, 6));
        List<Integer> list = List.of(2, 3, 4, 5);
        ListUtils.removeAll(input, list);
        assertThat(input).containsSequence(1, 6);
    }

}