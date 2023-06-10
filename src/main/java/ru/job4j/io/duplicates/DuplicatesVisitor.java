package ru.job4j.io.duplicates;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

import static java.nio.file.FileVisitResult.CONTINUE;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, List<FileProperty>> path = new HashMap<>();

    public DuplicatesVisitor() {
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        FileProperty fpAbsolute = new FileProperty((Math.round((float) attrs.size() / 100)), file.toAbsolutePath().toString());
        FileProperty fpShort = new FileProperty((Math.round((float) attrs.size() / 100)), file.getFileName().toString());
        if (!path.containsKey(fpShort)) {
            List<FileProperty> list = new ArrayList<>();
            list.add(fpAbsolute);
            path.put((fpShort), list);
        } else {
            path.get(fpShort).add(fpAbsolute);
        }
        return CONTINUE;
    }

    public void printRes() {
        for (Map.Entry<FileProperty, List<FileProperty>> s : path.entrySet()) {
            if (s.getValue().size() > 1) {
                for (FileProperty x : s.getValue()) {
                    System.out.println(x.getName() + " " + x.getSize());
                }
            }
        }
    }
}