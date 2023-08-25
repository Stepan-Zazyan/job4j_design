package ru.job4j.ood.lsp.foodstorage.model.store;

import ru.job4j.ood.lsp.foodstorage.model.food.Food;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Warehouse extends AbstractStore {
    private static final Warehouse INSTANCE = new Warehouse();
    private final Map<Integer, Food> storeWarehouse = new HashMap<>();
    private int id = 0;

    private static Warehouse getInstance() {
        return INSTANCE;
    }

    @Override
    public Food add(Food food) {
        id++;
        food.setId(id);
        return storeWarehouse.putIfAbsent(id, food);
    }

    @Override
    public boolean delete(Food food) {
        return storeWarehouse.remove(food.getId()) != null;
    }

    @Override
    public void update(Food food, double discount) {
        storeWarehouse.get(food.getId()).setPrice(food.getPrice() * ((100 - discount) / 100));
        storeWarehouse.get(food.getId()).setDiscount(25);
    }

    @Override
    public Optional<Food> findById(int id) {
        return Optional.ofNullable(storeWarehouse.get(id));
    }

    @Override
    public Collection<Food> findAll() {
        return storeWarehouse.values();
    }

}
