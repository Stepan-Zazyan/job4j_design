package ru.job4j.ood.lsp.foodstorage;

import ru.job4j.ood.lsp.foodstorage.food.Food;
import ru.job4j.ood.lsp.foodstorage.store.Shop;
import ru.job4j.ood.lsp.foodstorage.store.Trash;
import ru.job4j.ood.lsp.foodstorage.store.Warehouse;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ControlQuality {

    private final Warehouse warehouse = new Warehouse();
    private final Shop shop = new Shop();
    private final Trash trash = new Trash();

    public boolean distribute(Food food, LocalDate localDate) {
        boolean rsl = false;
        double liveDays = food.getExpiryDate().getDayOfMonth() - food.getCreateDate().getDayOfMonth();
        double daysLeft = food.getExpiryDate().getDayOfMonth() - localDate.getDayOfMonth();
        if (daysLeft < 0) {
            shop.delete(food);
            rsl = trash.add(food) == null;
        }
        if ((liveDays - (liveDays * 0.25))  < daysLeft) {
            rsl = warehouse.add(food) == null;
        }
        if ((liveDays - (liveDays * 0.25)) > daysLeft && (liveDays - (liveDays * 0.75)) < daysLeft) {
            warehouse.delete(food);
            rsl = shop.add(food) == null;
        }
        if ((liveDays - (liveDays * 0.75)) > daysLeft && daysLeft > 0) {
            shop.update(food, 25);
            rsl = shop.add(food) != null;
        }
       return rsl;
    }

    public void resortAll(Shop shop, Warehouse warehouse, Trash trash, LocalDate localDate) {
        Map<Integer, Food> map = new HashMap<>();
        map.putAll(shop.getStoreShop());
        map.putAll(warehouse.getStoreWarehouse());
        map.putAll(trash.getStoreTrash());
        for (Map.Entry<Integer, Food> unit: map.entrySet()) {
            distribute(unit.getValue(), localDate);
        }
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
