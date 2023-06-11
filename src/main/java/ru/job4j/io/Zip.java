package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path x: sources) {
                zip.putNextEntry(new ZipEntry(String.valueOf(x)));
                try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(String.valueOf(x)))) {
                    zip.write(in.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName jvm = ArgsName.of(args);
        Path start = Paths.get(jvm.get("d"));
        String extension = jvm.get("e");
        String formasionne = jvm.get("o");
        Zip z = new Zip();
        z.validateLength(args);
        z.validateValues(String.valueOf(start), extension, formasionne);
        List<Path> list = Search.search(start, s -> !s.toFile().getName().endsWith(extension));
        z.packFiles(list, new File("myFirst.zip"));
    }

    public void validateLength(String[] strins) {
        if (strins.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage  ROOT_FOLDER.");
        }
        if (strins.length == 1) {
            throw new IllegalArgumentException("Set file extension in configuration");
        }
        if (strins.length == 2) {
            throw new IllegalArgumentException("Set the object");
        }
    }

    public void validateValues(String str0, String str1, String str2) {
        File file = new File(str0);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        if (!str1.endsWith(".java")) {
            throw new IllegalArgumentException("File extension must start with \".\"");
        }
        if (!str2.endsWith(".zip")) {
            throw new IllegalArgumentException("File format must be \".zip\"");
        }
    }
}
