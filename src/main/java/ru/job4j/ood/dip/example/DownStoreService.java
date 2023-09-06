package ru.job4j.ood.dip.example;

public class DownStoreService implements Store {
    /*. Во-первых, этот класс нарушает SRP,
    потому что представляет как саму модель заказа, так и АПИ для работы с ней.
    Во-вторых, он нарушает DIP, потому что опять же сохранение идет в память,
    нам нужно здесь аналогично абстрагироваться от самого хранилища, создав для него отдельный интерфейс.
    Но зависимость от хранилища уже будет в сервис заказа, а сервис магазина будет зависеть от сервиса заказов*/
    private Store store;

    public DownStoreService(Store store) {
        this.store = store;
    }

    @Override
    public void method() {
        System.out.println("Я наследую метод для приличия");
    }
}
