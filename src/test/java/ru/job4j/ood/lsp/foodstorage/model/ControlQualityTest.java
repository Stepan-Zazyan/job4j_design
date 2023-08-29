package ru.job4j.ood.lsp.foodstorage.model;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.foodstorage.model.food.Cheese;
import ru.job4j.ood.lsp.foodstorage.model.food.Food;
import ru.job4j.ood.lsp.foodstorage.model.food.IceCream;
import ru.job4j.ood.lsp.foodstorage.model.food.Milk;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ControlQualityTest {

    @Test
    void checkTrash() {
        Food cheese = new Cheese(1, "Cheese",
                LocalDate.of(2023, 10, 8),
                LocalDate.of(2023, 10, 1),
                100, 0);
        ControlQuality controlQuality = new ControlQuality();
        boolean rsl = controlQuality.distribute(cheese, LocalDate.of(2023, 10, 10));
        Optional<Food> findResult = controlQuality.getTrash().findById(1);
        assertTrue(rsl);
        assertEquals(findResult, Optional.of(cheese));
    }

    @Test
    void checkWarehouse() {
        Food milk = new Milk(1, "Milk",
                LocalDate.of(2023, 10, 15),
                LocalDate.of(2023, 10, 9),
                100, 0);
        ControlQuality controlQuality = new ControlQuality();
        boolean rsl = controlQuality.distribute(milk, LocalDate.of(2023, 10, 10));
        Optional<Food> findResult = controlQuality.getWarehouse().findById(1);
        assertTrue(rsl);
        assertEquals(findResult, Optional.of(milk));
    }

    @Test
    void checkShop() {
        Food iceCream = new IceCream(1, "IceCream",
                LocalDate.of(2023, 10, 15),
                LocalDate.of(2023, 10, 5),
                100, 0);

        ControlQuality controlQuality = new ControlQuality();
        boolean rsl = controlQuality.distribute(iceCream, LocalDate.of(2023, 10, 10));
        Optional<Food> findResult = controlQuality.getShop().findById(1);
        assertTrue(rsl);
        assertEquals(findResult, Optional.of(iceCream));
    }

    @Test
    void checkShopWithDiscount() {
        Food milk = new Milk(1, "Milk",
                LocalDate.of(2023, 10, 11),
                LocalDate.of(2023, 10, 1),
                100, 0);
        ControlQuality controlQuality = new ControlQuality();
        controlQuality.getShop().add(milk);
        boolean rsl = controlQuality.distribute(milk, LocalDate.of(2023, 10, 10));
        Optional<Food> findResult = controlQuality.getShop().findById(1);
        assertTrue(rsl);
        assertEquals(findResult, Optional.of(milk));
    }
}