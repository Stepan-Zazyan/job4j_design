package ru.job4j.ood.srp;

public class TimeZone implements TimeParse {
    /*Ошибка в продумывании интерфейса, надо поделить на части: парсинг и время*/
    @Override
    public void parse() {
        System.out.println("зачем мне здесь что-то парсить?");
    }

    @Override
    public void timeFormatChange() {
        System.out.println("поменяли формат времени");
    }
}
