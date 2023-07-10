package ru.job4j.ood.srp.currency;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryCurrencyConverterTest {

    @Test
    void convert() {
        InMemoryCurrencyConverter currency = new InMemoryCurrencyConverter();
        double actual = currency.convert(Currency.RUB, 1000, Currency.EUR);
        double expected = 1000 * 0.0166;
        assertEquals(actual, expected);
    }
}