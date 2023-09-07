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
        int a = scanner.nextInt();
        String str = "";
        if (a == 1) {
            System.out.println("Введите элемент");
            str = scanner.nextLine();
            simpleMenu.add(str, str, () -> System.out.println("Something"));
        }
        if (a == 2) {
            System.out.println("Введите элемент");
            str = scanner.nextLine();
            simpleMenu.add(str, str, () -> System.out.println("Something"));
        }
        if (a == 3) {
            System.out.println("Введите действие");
           defaultAction.delegate();
        }
        if (a != 1 && a != 2 && a != 3) {
            System.out.println(menu);
        }
    }
}
