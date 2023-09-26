package ru.job4j.ood.isp.menu;

public class SimpleMenuPrinter {
    void print(Menu menu) {
        for (Menu.MenuItemInfo item : menu) {
            System.out.println(item.getName());
            System.out.println(item.getChildren());
            if (!item.getChildren().isEmpty()) {
                for (String i : item.getChildren()) {
                    System.out.println(i);
                }
            }
        }
    }
}
