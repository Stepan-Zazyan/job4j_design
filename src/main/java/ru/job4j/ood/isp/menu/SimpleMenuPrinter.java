package ru.job4j.ood.isp.menu;

public class SimpleMenuPrinter {
    void print(Menu menu) {
        for (Menu.MenuItemInfo item: menu) {
            System.out.println(item);
        }
    }
}
