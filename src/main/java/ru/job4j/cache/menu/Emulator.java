package ru.job4j.cache.menu;

import ru.job4j.cache.DirFileCache;
import java.io.IOException;
import java.util.Scanner;

public class Emulator {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("укажите кэшируемую директорию");
        String dir = sc.next();
        DirFileCache dirFileCache = new DirFileCache(dir);
        boolean t = true;
        while (t) {
            System.out.println("Введите имя файла");
            String name = sc.next();
            System.out.println("загрузить содержимое файла в кэш - нажмите 1"
                    + System.lineSeparator()
                    + "получить содержимое файла из кэша - нажмите 2"
                    + System.lineSeparator()
                    + "Завершить программу - нажмите любую другую цифру");
            int value = sc.nextInt();
            switch (value) {
                case (1) -> System.out.println(dirFileCache.load(name));
                case (2) -> System.out.println(dirFileCache.getCache().get(name).get());
                default -> {
                    t = false;
                }
            }
        }
    }
}
