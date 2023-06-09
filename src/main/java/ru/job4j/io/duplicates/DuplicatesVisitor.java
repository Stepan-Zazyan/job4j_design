package ru.job4j.io.duplicates;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.FileVisitResult.CONTINUE;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private List<FileProperty> path = new ArrayList<>();
    private List<FileProperty> pathAll = new ArrayList<>();
    public DuplicatesVisitor() {
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        FileProperty fp = new FileProperty((Math.round((float) attrs.size() / 100)), file.getFileName().toString());
        if (!pathAll.contains(fp)) {
            pathAll.add(fp);
        } else {
            path.add(fp);
            System.out.println(file.toAbsolutePath() + " " + fp.getSize());
        }
        return CONTINUE;
    }

    public List<FileProperty> getPaths() {
        return path;
    }
}