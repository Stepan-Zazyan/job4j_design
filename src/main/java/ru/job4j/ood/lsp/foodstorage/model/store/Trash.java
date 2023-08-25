package ru.job4j.ood.lsp.foodstorage.model.store;

import ru.job4j.ood.lsp.foodstorage.model.food.Food;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Trash extends AbstractStore {
    private static final Trash TRASH = new Trash();
    private Map<Integer, Food> storeTrash = new HashMap<>();
    private int id = 0;

    private static Trash getInstance() {
        return TRASH;
    }

    @Override
    public Food add(Food food) {
        id++;
        food.setId(id);
        return storeTrash.putIfAbsent(id, food);
    }

    @Override
    public boolean delete(Food food) {
        return storeTrash.remove(food.getId()) != null;
    }

    @Override
    public void update(Food food, double discount) {
        storeTrash.get(food.getId()).setPrice(food.getPrice() * ((100 - discount) / 100));
        storeTrash.get(food.getId()).setDiscount(25);
    }

    @Override
    public Optional<Food> findById(int id) {
        return Optional.ofNullable(storeTrash.get(id));
    }

    @Override
    public Collection<Food> findAll() {
        return storeTrash.values();
    }

    public Map<Integer, Food> getStoreTrash() {
        return storeTrash;
    }
}
