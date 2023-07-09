package ru.job4j.ood.srp;

public class FoodMenu implements Flight {

    @Override
    public void serveFood() {
        System.out.println("Курица и чипсы");
    }

    /*Ошибка. Переделаем еду в одном месте, технические характеристики в другом*/
    @Override
    public void flightTime() {
        System.out.println("Длина полета в меню лишняя");
    }
}
