package ru.job4j.filesearcher;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FileSearcher {
    public static void main(String[] args) throws IOException {
        ArgsName jvm = ArgsName.of(args);
        Path start = Paths.get(jvm.get("d"));
        System.out.println(start);
        String name = jvm.get("n");
        System.out.println(name);
        String type = jvm.get("t");
        System.out.println(type);
        String nameLogFile = jvm.get("o");
        System.out.println(nameLogFile);
        FileSearcher fileSearcher = new FileSearcher();
        fileSearcher.validateLength(args);
        fileSearcher.validateValues(String.valueOf(start), name, type, nameLogFile);
        List<Path> list = new ArrayList<>();

        switch (type) {
            case ("name") -> {
                list = Search.search(start, s -> s.toFile().getName().contains(name));
            }
            case ("regex") -> {
                Pattern pattern = Pattern.compile(name);
                list = Search.search(start, s -> pattern.matcher(s.toString()).find());
            }
            case ("mask") -> {
                String str = name.replace("*", "[a-zA-Z0-9]");
                Pattern pattern = Pattern.compile(str);
                list = Search.search(start, s -> pattern.matcher(s.toString()).find());
            }
            default -> System.out.println("Введите корректный вид поиска");
        }

        try (BufferedWriter out = new BufferedWriter(new FileWriter(nameLogFile))) {
            out.write(list.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void validateLength(String[] strings) {
        if (strings.length == 3) {
            throw new IllegalArgumentException("Set all values in program properties");
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
