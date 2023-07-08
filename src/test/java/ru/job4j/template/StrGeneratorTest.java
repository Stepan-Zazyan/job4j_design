package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
public class StrGeneratorTest {

    @Test
    void whenGenerate() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Stepan Zazyan");
        args.put("subject", "you");
        StrGenerator str = new StrGenerator();
        String actual = str.produce(template, args);
        String expected = "I am a Stepan Zazyan, Who are you?";
        assertEquals(actual, expected);
    }

    @Test
    void whenNoSuchKeyInTemplate() {
        String template = "I am a ${name}, Who are ${subject}?";
        StrGenerator str = new StrGenerator();
        Map<String, String> args = new HashMap<>();
        assertThatThrownBy(() -> str.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenManyKeyInTemplate() {
        String template = "I am a ${name}, Who are ${subject}?";
        StrGenerator str = new StrGenerator();
        Map<String, String> args = new HashMap<>();
        args.put("name", "Stepan Zazyan");
        args.put("subject", "you");
        args.put("subjectssss", "yoooou");
        assertThatThrownBy(() -> str.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Есть лишние ключи");
    }
}