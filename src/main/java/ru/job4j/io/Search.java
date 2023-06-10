package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        Search s = new Search();
        s.validateLength(args);
        s.validateValues(args[0], args[1]);
        Path start = Paths.get(args[0]);
        String ends = args[1];
        search(start, p -> p.toFile().getName().endsWith(ends)).
                forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public void validateLength(String[] strins) {
        if (strins.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage  ROOT_FOLDER.");
        }
        if (strins.length == 1) {
            throw new IllegalArgumentException("Set file extension in configuration");
        }
    }

    public void validateValues(String str0, String str1) {
        File file = new File(str0);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        if (!str1.startsWith(".")) {
            throw new IllegalArgumentException("File extension must start with \".\"");
        }
    }
}
