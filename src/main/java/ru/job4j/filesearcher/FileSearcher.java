package ru.job4j.filesearcher;

import ru.job4j.io.Search;
import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class FileSearcher {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите директорию");
        args[0] = sc.next();
        System.out.println("Введите имя файла, маска, либо регулярное выражение");
        args[1] = sc.next();
        System.out.println("Выберите тип поиска: mask искать по маске, name по полному совпадение имени, regex по регулярному выражению");
        args[2] = sc.next();
        System.out.println("Введите имя лог-файла");
        args[3] = sc.next();
        FileSearcher fileSearcher = new FileSearcher();
        fileSearcher.validateLength(args);
        fileSearcher.validateValues(args[0], args[1], args[2], args[3]);
        List<Path> list = Search.search(Path.of(args[0]), s -> s.toFile().getName().contains(args[1]));
        try (BufferedWriter out = new BufferedWriter(new FileWriter("resultFileSearcher.txt"))) {
            out.write(list.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void validateLength(String[] strins) {
        if (strins.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage  ROOT_FOLDER.");
        }
        if (strins.length == 1) {
            throw new IllegalArgumentException("Set file name, mask or regular expression");
        }
        if (strins.length == 2) {
            throw new IllegalArgumentException("Set type search");
        }
        if (strins.length == 3) {
            throw new IllegalArgumentException("Set log file name");
        }
    }

    public void validateValues(String str0, String str1, String str2, String str3) {
        File file = new File(str0);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        if (!str1.contains(".")) {
            throw new IllegalArgumentException("File with extension must have \".\"");
        }
        if (!str2.equals("mask") && !str2.equals("name") && !str2.equals("regex")) {
            throw new IllegalArgumentException("Choose proper search format");
        }
        if (!str3.endsWith(".txt")) {
            throw new IllegalArgumentException("LogFile must end with \".\"txt");
        }
    }
}
