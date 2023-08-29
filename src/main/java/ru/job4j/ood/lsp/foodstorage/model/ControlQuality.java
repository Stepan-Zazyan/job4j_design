package ru.job4j.ood.lsp.foodstorage.model;

import ru.job4j.ood.lsp.foodstorage.model.food.Food;
import ru.job4j.ood.lsp.foodstorage.model.store.Shop;
import ru.job4j.ood.lsp.foodstorage.model.store.Trash;
import ru.job4j.ood.lsp.foodstorage.model.store.Warehouse;

import java.time.LocalDate;
public class ControlQuality {

    private final Warehouse warehouse = new Warehouse();
    private final Shop shop = new Shop();
    private final Trash trash = new Trash();

    public boolean distribute(Food food, LocalDate localDate) {
        boolean rsl = false;
        double liveDays = food.getExpiryDate().getDayOfMonth() - food.getCreateDate().getDayOfMonth();
        double daysLeft = food.getExpiryDate().getDayOfMonth() - localDate.getDayOfMonth();
        if (daysLeft < 0) {
            trash.add(food);
            shop.delete(food);
            rsl = trash.add(food) == null;
        }
        if ((liveDays - (liveDays * 0.25))  < daysLeft) {
            rsl = warehouse.add(food) == null;
        }
        if ((liveDays - (liveDays * 0.25)) > daysLeft && (liveDays - (liveDays * 0.75)) < daysLeft) {
            shop.add(food);
            warehouse.delete(food);
            rsl = shop.add(food) == null;
        }
        if ((liveDays - (liveDays * 0.75)) > daysLeft && daysLeft > 0) {
            shop.update(food, 25);
            rsl = shop.add(food) == null;
        }

       return rsl;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public Shop getShop() {
        return shop;
    }

    public Trash getTrash() {
        return trash;
    }
}
