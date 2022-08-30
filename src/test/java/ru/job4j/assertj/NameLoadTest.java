package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkParseEmptyName() {
        String[] ar = {};
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(ar))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void validateNotContainsEqualSign() {
        String name = "wp";
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.validate(name))
                .isInstanceOf(IllegalArgumentException.class)
                .message();
    }

    @Test
    void validateStartsWithEqualSign() {
        String name = "=w";
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.validate(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain a key");
    }

    @Test
    void validateIndexEqualSignNameEqualsLength() {
        String name = "12=";
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.validate(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain a value");
    }

}