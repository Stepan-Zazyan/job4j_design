package ru.job4j.ood.lsp.foodstorage.model.store;

import ru.job4j.ood.lsp.foodstorage.model.food.Food;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Shop extends AbstractStore {
    private static final Shop SHOP = new Shop();
    private Map<Integer, Food> storeShop = new HashMap<>();
    private int id = 0;

    private static Shop getInstance() {
        return SHOP;
    }

    @Override
    public Food add(Food food) {
        id++;
        food.setId(id);
        return storeShop.putIfAbsent(id, food);
    }

    @Override
    public boolean delete(Food food) {
        return storeShop.remove(food.getId()) != null;
    }

    @Override
    public void update(Food food, double discount) {
        storeShop.get(food.getId()).setPrice(food.getPrice() * ((100 - discount) / 100));
        storeShop.get(food.getId()).setDiscount(25);
    }

    @Override
    public Optional<Food> findById(int id) {
        return Optional.ofNullable(storeShop.get(id));
    }

    @Override
    public Collection<Food> findAll() {
        return storeShop.values();
    }

    public Map<Integer, Food> getStoreShop() {
        return storeShop;
    }

    public void setStoreShop(Map<Integer, Food> storeShop) {
        this.storeShop = storeShop;
    }
}
