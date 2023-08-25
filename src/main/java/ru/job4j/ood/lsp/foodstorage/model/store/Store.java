package ru.job4j.ood.lsp.foodstorage.model.store;

import ru.job4j.ood.lsp.foodstorage.model.food.Food;

import java.util.Collection;
import java.util.Optional;

public interface Store {

    Food add(Food food);

    boolean delete(Food food);

    void update(Food oldFood, double discount);

    Optional<Food> findById(int id);

    Collection<Food> findAll();
}
