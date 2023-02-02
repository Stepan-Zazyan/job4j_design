package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("data/even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            StringBuilder value = new StringBuilder();
            while ((read = in.read()) != -1) {
                if (!(String.valueOf((char) read).equals(System.lineSeparator()))) {
                    value.append((char) read);
                    continue;
                }
                if (Integer.parseInt(value.toString()) % 2 == 0) {
                    text.append(value).append(System.lineSeparator());
                }
                value = new StringBuilder();
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
