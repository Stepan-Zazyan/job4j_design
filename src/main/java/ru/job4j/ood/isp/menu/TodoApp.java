package ru.job4j.ood.isp.menu;

import java.util.Scanner;

public class TodoApp {

    final ActionDelegate defaultAction = () -> System.out.println("Some action");
    public static void main(String[] args) {
        TodoApp todoApp = new TodoApp();
        todoApp.init();
    }

    public void init() {
        String menu = """
                Выберите пункт меню:
                    1.Добавить элемент в корень меню;
                    2.Добавить элемент к родительскому элементу;
                    3.Вызвать действие, привязанное к пункту меню (действие можно сделать константой,
                    4.Вывести меню в консоль.\s""";
        System.out.println(menu);
        SimpleMenu simpleMenu = new SimpleMenu();
        Scanner scanner = new Scanner(System.in);
        int a = 4;
        String str = "";
        while (a == 1 || a == 2 || a == 3 || a == 4) {
            a = scanner.nextInt();
            if (a == 1) {
                System.out.println("Введите элемент");
                str = scanner.next();
                simpleMenu.add(str, "", () -> System.out.println("Something"));
            }
            if (a == 2) {
                System.out.println("Введите родительский элемент");
                String strParent = scanner.next();
                System.out.println("Введите дочерний элемент");
                String strChild = scanner.next();
                simpleMenu.add(strParent, strChild, () -> System.out.println("Something"));
            }
            if (a == 3) {
                System.out.println("Введите действие");
                defaultAction.delegate();
            }
            if (a == 4) {
                new SimpleMenuPrinter().print(simpleMenu);
            }
        }
    }
}
