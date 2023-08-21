package ru.job4j.ood.lsp.foodstorage.model.store;

import ru.job4j.ood.lsp.foodstorage.model.food.Food;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Warehouse extends AbstractStore {
    private static final Warehouse INSTANCE = new Warehouse();
    private Map<Integer, Food> store = new HashMap<>();

    public static Warehouse getInstance() {
        return INSTANCE;
    }

    @Override
    public Food add(Food food) {
        return null;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public Optional<Store> findById() {
        return Optional.empty();
    }

    @Override
    public Collection<Store> findAll() {
        return null;
    }

}
