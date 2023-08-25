package ru.job4j.ood.lsp.foodstorage.model.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.foodstorage.model.food.Cheese;
import ru.job4j.ood.lsp.foodstorage.model.food.Food;
import ru.job4j.ood.lsp.foodstorage.model.food.Milk;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseTest {
    @Test
    void checkAdd() {
        Warehouse warehouse = new Warehouse();
        Food cheese = new Cheese(1, "Cheese",
                LocalDate.of(2023, 8, 21),
                LocalDate.of(2023, 8, 1),
                100, 0);
        warehouse.add(cheese);
        assertEquals(cheese, warehouse.getStoreWarehouse().get(1));
    }

    @Test
    void checkDelete() {
        Store warehouse = new Warehouse();
        Food cheese = new Cheese(1, "Cheese",
                LocalDate.of(2023, 8, 21),
                LocalDate.of(2023, 8, 1),
                100, 0);
        warehouse.add(cheese);
        assertTrue(warehouse.delete(cheese));
    }

    @Test
    void checkFindById() {
        Warehouse warehouse = new Warehouse();
        Food cheese = new Cheese(1, "Cheese",
                LocalDate.of(2023, 8, 21),
                LocalDate.of(2023, 8, 1),
                100, 0);
        warehouse.add(cheese);
        assertEquals(cheese, warehouse.findById(1).get());
    }

    @Test
    void checkFindAll() {
        Warehouse warehouse = new Warehouse();
        Food cheese = new Cheese(1, "Cheese",
                LocalDate.of(2023, 8, 21),
                LocalDate.of(2023, 8, 1),
                100, 0);
        Food milk = new Milk(2, "Milk",
                LocalDate.of(2023, 8, 21),
                LocalDate.of(2023, 8, 1),
                100, 0);
        warehouse.add(cheese);
        warehouse.add(milk);
        assertEquals(warehouse.getStoreWarehouse().values(), warehouse.findAll());
    }

    @Test
    void checkUpdate() {
        Warehouse warehouse = new Warehouse();
        Food cheese = new Cheese(1, "Cheese",
                LocalDate.of(2023, 8, 21),
                LocalDate.of(2023, 8, 1),
                100, 0);
        warehouse.add(cheese);
        warehouse.update(cheese, 25);
        assertEquals(25, warehouse.getStoreWarehouse().get(1).getDiscount());
        assertEquals(75, warehouse.getStoreWarehouse().get(1).getPrice());
    }
}