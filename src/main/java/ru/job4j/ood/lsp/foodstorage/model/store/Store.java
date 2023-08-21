package ru.job4j.ood.lsp.foodstorage.model.store;

import ru.job4j.ood.lsp.foodstorage.model.food.Food;

import java.util.Collection;
import java.util.Optional;

public interface Store {

    public Food add(Food food);

    public boolean delete();

    public boolean update();

    public Optional<Store> findById();

    public Collection<Store> findAll();
}
