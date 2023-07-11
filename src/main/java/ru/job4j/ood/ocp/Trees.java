package ru.job4j.ood.ocp;

public class Trees {
    void fruitsGrow() {
        System.out.println("Я даю фрукты");
    }
    /*Нарушение OCP.
    Приходится изменять класс для деревьев без фруктов, например для дуба*/
    void fruitsNoGrow() {
        System.out.println("Я не даю фрукты");
    }
}
