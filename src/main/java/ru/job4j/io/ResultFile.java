package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class ResultFile {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        try (FileOutputStream out = new FileOutputStream("data/dataresult.txt")) {
            for (int k : array) {
                for (int i : array) {
                    out.write((k * i + " ").getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка доступа к файлу");
        }
    }
}