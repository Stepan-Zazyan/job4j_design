package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;

public class Generics {
    public static void main(String[] args) {
        Generics gen = new Generics();
        List<Animal> first = new ArrayList<>();
        List<Predator> second = new ArrayList<>();
        List<Tiger> third = new ArrayList<>();
        first.add(new Animal());
        second.add(new Predator());
        third.add(new Tiger());

        gen.printObject(first);
        gen.printObject(second);
        gen.printObject(third);
        System.out.println();

        System.out.println("gen.printBoundedWildCard((List<? extends Predator>) first); - Верхний предел это Predator, угроза потери данных");
        gen.printBoundedWildCard(second);
        gen.printBoundedWildCard(third);
        System.out.println();

        gen.printLowerBoundedWildCard(first);
        gen.printLowerBoundedWildCard(second);
        System.out.println("gen.printLowerBoundedWildCard(third); - нижний предел это Predator, тип данных несовместим");
    }

    public void printObject(List<?> list) {
        for (Object next : list) {
            System.out.println("Текущий элемент: " + next);
        }
    }

    public void printBoundedWildCard(List<? extends Predator> list) {
        for (Object next : list) {
            System.out.println("Текущий элемент: " + next);
        }
    }

    public void printLowerBoundedWildCard(List<? super Predator> list) {
        for (Object next : list) {
            System.out.println("Текущий элемент: " + next);
        }
    }
}