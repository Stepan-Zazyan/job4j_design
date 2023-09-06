package ru.job4j.ood.lsp.foodstorage.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.foodstorage.food.Cheese;
import ru.job4j.ood.lsp.foodstorage.food.Food;
import ru.job4j.ood.lsp.foodstorage.food.Milk;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class TrashTest {

    @Test
    void checkAdd() {
        Trash trash = new Trash();
        Food cheese = new Cheese(1, "Cheese",
                LocalDate.of(2023, 8, 21),
                LocalDate.of(2023, 8, 1),
                100, 0);
        trash.add(cheese);
        assertEquals(cheese, trash.getStoreTrash().get(1));
    }

    @Test
    void checkDelete() {
        Trash trash = new Trash();
        Food cheese = new Cheese(1, "Cheese",
                LocalDate.of(2023, 8, 21),
                LocalDate.of(2023, 8, 1),
                100, 0);
        trash.add(cheese);
        assertTrue(trash.delete(cheese));
    }

    @Test
    void checkFindById() {
        Trash trash = new Trash();
        Food cheese = new Cheese(1, "Cheese",
                LocalDate.of(2023, 8, 21),
                LocalDate.of(2023, 8, 1),
                100, 0);
        trash.add(cheese);
        assertEquals(cheese, trash.findById(1).get());
    }

    @Test
    void checkFindAll() {
        Trash trash = new Trash();
        Food cheese = new Cheese(1, "Cheese",
                LocalDate.of(2023, 8, 21),
                LocalDate.of(2023, 8, 1),
                100, 0);
        Food milk = new Milk(2, "Milk",
                LocalDate.of(2023, 8, 21),
                LocalDate.of(2023, 8, 1),
                100, 0);
        trash.add(cheese);
        trash.add(milk);
        assertEquals(trash.getStoreTrash().values(), trash.findAll());
    }

    @Test
    void checkUpdate() {
        Trash trash = new Trash();
        Food cheese = new Cheese(1, "Cheese",
                LocalDate.of(2023, 8, 21),
                LocalDate.of(2023, 8, 1),
                100, 0);
        trash.add(cheese);
        trash.update(cheese, 25);
        assertEquals(25, trash.getStoreTrash().get(1).getDiscount());
        assertEquals(75, trash.getStoreTrash().get(1).getPrice());
    }

}