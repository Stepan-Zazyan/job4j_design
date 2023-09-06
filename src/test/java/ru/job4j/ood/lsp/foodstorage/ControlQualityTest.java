package ru.job4j.ood.lsp.foodstorage;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.foodstorage.food.Cheese;
import ru.job4j.ood.lsp.foodstorage.food.Food;
import ru.job4j.ood.lsp.foodstorage.food.IceCream;
import ru.job4j.ood.lsp.foodstorage.food.Milk;

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

    @Test
    void checkResortAll() {
        Food milk = new Milk(1, "Milk",
                LocalDate.of(2023, 10, 30),
                LocalDate.of(2023, 10, 15),
                100, 0);
        Food iceCream = new IceCream(2, "IceCream",
                LocalDate.of(2023, 10, 25),
                LocalDate.of(2023, 10, 15),
                100, 0);
        Food cheese = new Cheese(3, "Cheese",
                LocalDate.of(2023, 10, 10),
                LocalDate.of(2023, 10, 8),
                100, 0);
        ControlQuality controlQuality = new ControlQuality();
        boolean rslMilk = controlQuality.distribute(milk, LocalDate.of(2023, 10, 16));
        boolean rslIceCream = controlQuality.distribute(iceCream, LocalDate.of(2023, 10, 18));
        boolean rslCheese = controlQuality.distribute(cheese, LocalDate.of(2023, 10, 11));
        assertTrue(rslMilk);
        assertTrue(rslIceCream);
        assertTrue(rslCheese);
        Optional<Food> findResultMilk = controlQuality.getWarehouse().findById(1);
        assertEquals(findResultMilk, Optional.of(milk));
        Optional<Food> findResultIceCream = controlQuality.getShop().findById(2);
        assertEquals(findResultIceCream, Optional.of(iceCream));
        Optional<Food> findResultCheese = controlQuality.getTrash().findById(3);
        assertEquals(findResultCheese, Optional.of(cheese));
        controlQuality.resortAll(controlQuality.getShop(), controlQuality.getWarehouse(), controlQuality.getTrash(), LocalDate.of(2023, 10, 19));
        Optional<Food> findResultMilkAfterResort = controlQuality.getShop().findById(1);
        assertEquals(findResultMilkAfterResort, Optional.of(milk));
        Optional<Food> findResultIceCreamAfterResort = controlQuality.getShop().findById(2);
        assertEquals(findResultIceCreamAfterResort, Optional.of(iceCream));
        Optional<Food> findResultCheeseAfterResort = controlQuality.getTrash().findById(3);
        assertEquals(findResultCheeseAfterResort, Optional.of(cheese));
    }
}