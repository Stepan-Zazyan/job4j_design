package ru.job4j.ood.lsp.foodstorage.model.store;

import ru.job4j.ood.lsp.foodstorage.model.food.Food;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractStore implements Store {

    private Map<Integer, AbstractStore> store = new HashMap<>();

    private final int id = 0;

    public abstract Food add(Food food);

    public abstract boolean delete(Food food);

    public abstract void update(Food food, double discount);

    public abstract Optional<Food> findById(int id);

    public abstract Collection<Food> findAll();

    public int getId() {
        return id;
    }
}
