package ru.job4j.ood.isp;

public class Driver implements Worker {
    public void drive() {
        System.out.println("Веду машину");
    }
/*Ошибка - надо разделить на интерфейсы*/
    public void cook() {
        System.out.println("не готовлю");
    }
}
