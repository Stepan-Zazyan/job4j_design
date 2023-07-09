package ru.job4j.ood.srp;

public class Tiger implements Animal {

    @Override
    public void eatMeat() {
        System.out.println("Вкусная была зебра");
    }

    /*нарушение принципа (Single responsibility principle),
    надо делать отдельный код для питания, хищники не едят траву*/
    @Override
    public void eatLeaves() {
        System.out.println("я не ем травку");
    }
}
