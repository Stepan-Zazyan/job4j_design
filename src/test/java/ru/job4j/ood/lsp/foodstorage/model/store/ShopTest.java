package ru.job4j.ood.lsp.foodstorage.model.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.foodstorage.model.food.Cheese;
import ru.job4j.ood.lsp.foodstorage.model.food.Food;
import ru.job4j.ood.lsp.foodstorage.model.food.Milk;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ShopTest {
    @Test
    void checkAdd() {
        Shop shop = new Shop();
        Food cheese = new Cheese(1, "Cheese",
                LocalDate.of(2023, 8, 21),
                LocalDate.of(2023, 8, 1),
                100, 0);
        shop.add(cheese);
        assertEquals(cheese, shop.getStoreShop().get(1));
    }

    @Test
    void checkDelete() {
        Shop shop = new Shop();
        Food cheese = new Cheese(1, "Cheese",
                LocalDate.of(2023, 8, 21),
                LocalDate.of(2023, 8, 1),
                100, 0);
        shop.add(cheese);
        assertTrue(shop.delete(cheese));
    }

    @Test
    void checkFindById() {
        Shop shop = new Shop();
        Food cheese = new Cheese(1, "Cheese",
                LocalDate.of(2023, 8, 21),
                LocalDate.of(2023, 8, 1),
                100, 0);
        shop.add(cheese);
        assertEquals(cheese, shop.findById(1).get());
    }

    @Test
    void checkFindAll() {
        Shop shop = new Shop();
        Food cheese = new Cheese(1, "Cheese",
                LocalDate.of(2023, 8, 21),
                LocalDate.of(2023, 8, 1),
                100, 0);
        Food milk = new Milk(2, "Milk",
                LocalDate.of(2023, 8, 21),
                LocalDate.of(2023, 8, 1),
                100, 0);
        shop.add(cheese);
        shop.add(milk);
        assertEquals(shop.getStoreShop().values(), shop.findAll());
    }

    @Test
    void checkUpdate() {
        Shop shop = new Shop();
        Food cheese = new Cheese(1, "Cheese",
                LocalDate.of(2023, 8, 21),
                LocalDate.of(2023, 8, 1),
                100, 0);
        shop.add(cheese);
        shop.update(cheese, 25);
        assertEquals(25, shop.getStoreShop().get(1).getDiscount());
        assertEquals(75, shop.getStoreShop().get(1).getPrice());
    }
}