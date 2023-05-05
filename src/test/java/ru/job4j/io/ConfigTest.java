package ru.job4j.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("login"))
                .isEqualTo("postgres");
        assertThat(config.value("password"))
                .isEqualTo("1234");
    }

    @Test
    void whenPairWithCommentAndEmptyLine() {
        String path = "./data/appWithCommentAndEmptyLines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"))
                .isEqualTo("Petr Arsentev");
    }

    @Test
    void whenWithNoEqualSign() {
        String path = "./data/appWithNoEqualSign.properties";
        Config config = new Config(path);
        IllegalArgumentException thrown =
                Assertions.assertThrows(IllegalArgumentException.class, config::load);
        Assertions.assertEquals(("cannot find '=' sign at line "
                + config.getLineCounter()), thrown.getMessage());
    }

    @Test
    void whenValueKeyMistake() {
        String path = "./data/appValueKeyMistake.properties";
        Config config = new Config(path);
        IllegalArgumentException thrown =
                Assertions.assertThrows(IllegalArgumentException.class, config::load);
        Assertions.assertEquals(("wrong sequence in line "
                + config.getLineCounter()), thrown.getMessage());
    }
}