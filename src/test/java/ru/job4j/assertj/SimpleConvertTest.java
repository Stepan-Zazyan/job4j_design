package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1))
                .startsWith("first")
                .containsSequence("second", "three")
                .hasSize(5);
    }

    @Test
    void checkList() {
        SimpleConvert col = new SimpleConvert();
        List<String> list = col.toList("first", "second", "three", "four", "five");
        assertThat(list).isNotNull()
                .allSatisfy(t -> {
                    assertThat(t.length()).isGreaterThan(2);
                    assertThat(t.length()).isLessThan(10);
                })
                .anySatisfy(t -> {
                    assertThat(t.length()).isEqualTo(6);
                    assertThat(t.length()).isGreaterThan(5);
                })
                .allMatch(t -> t.length() > 3)
                .anyMatch(t-> t.contains("e"))
                .noneMatch((t->t.startsWith("q")));
    }

    @Test
    void checkSet() {
        SimpleConvert sc = new SimpleConvert();
        Set<String> set = sc.toSet("first", "second", "three", "four", "five");
    assertThat(set).filteredOn(t->t.length() > 5).first().isEqualTo("second");
    assertThat(set).filteredOnAssertions(t-> assertThat(t.length()).isLessThan(5))
            .hasSize(2)
            .first().isEqualTo("four");
    }

    @Test
    void checkMap() {
        SimpleConvert sc = new SimpleConvert();
        Map<String, Integer> map = sc.toMap("first", "second", "three", "four", "five");
        assertThat(map).hasSize(5)
                .containsKeys("first", "second", "three", "four", "five")
                .containsValues(2, 1, 3, 4, 0)
                .containsEntry("second", 1);
    }
}

