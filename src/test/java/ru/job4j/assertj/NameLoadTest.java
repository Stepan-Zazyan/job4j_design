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

   /*не узнает метод parse, просит сделать статичным в итоге рушатся поля,
    ипользуемые в методе, да и не в этом решение.. думаю*/
    @Test
    void checkParseEmptyName() {
        String[] ar = {};
        assertThatThrownBy(NameLoad.parse(ar))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /*Не видит мой тестовый метод bool,выделяет красным,
    пришлось вот так изворачивиться. но как это использовать для исключений?*/
    @Test
    void boolTest() {
        int num = 6;
        boolean res = new NameLoad().hello(num);
        assertThat(res).isTrue();
    }

   /*При обычной подстановке  assertThatThrownBy(validate(name))
    получаем cannot resolve method validate..почему?
    вот такое тоже не получается, required & provided не сходятся.*/
    @Test
    void validateContains() {
        String name = "w=p";
        Exception ex = new IllegalStateException();
        assertThatThrownBy(ex)
                .isInstanceOf(IllegalStateException.class);
    }

}