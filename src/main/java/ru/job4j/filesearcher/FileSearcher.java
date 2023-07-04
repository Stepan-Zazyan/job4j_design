package ru.job4j.filesearcher;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
        List<Path> list = Search.search(start, s -> s.toFile().getName().contains(name));
        System.out.println(list);
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
