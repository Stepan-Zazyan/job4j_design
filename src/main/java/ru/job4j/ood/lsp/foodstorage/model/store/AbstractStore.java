package ru.job4j.ood.lsp.foodstorage.model.store;

import ru.job4j.ood.lsp.foodstorage.model.food.Food;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractStore implements Store {

    private Map<Integer, AbstractStore> store = new HashMap<>();
    private int id = 0;

    public Food add(Food food) {
        return food;
    }

    public boolean delete(String name) {
        return false;
    }

    public boolean update(int oldId, String oldValue, String value) {
        return false;
    }

    public Optional<Store> findById(int id) {
        return Optional.empty();
    }

    public Collection<Store> findAll() {
        return null;
    }

    public int getId() {
        return id;
    }
}
