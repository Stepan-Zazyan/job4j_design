package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String outStr = argsName.get("out");
        String filter = argsName.get("filter");
        var scanner = new Scanner(new BufferedReader(
                new FileReader(path))).useDelimiter(System.lineSeparator());
        String[] array = filter.split(",");
        List<String> headers;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            headers = List.of(br.readLine().split(delimiter));
        }
        List<Integer> filters = new ArrayList<>();
        for (String s : array) {
            if (headers.contains(s)) {
                filters.add(headers.indexOf(s));
            }
        }
        StringBuilder resultBuilder = new StringBuilder();
        for (int i = 0 ; scanner.hasNext(); i++) {
            String[] str = scanner.next().split(delimiter);
            StringBuilder stringBuilder = new StringBuilder();
            for (Integer integer : filters) {
                stringBuilder.append(str[integer]).append(delimiter);
            }
            resultBuilder.append(stringBuilder.substring(
                    0, stringBuilder.length() - 1)).append(System.lineSeparator());
        }
        if ("stdout".equals(outStr)) {
            System.out.print(resultBuilder);
        } else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outStr))) {
                writer.write(resultBuilder.toString());
            }
        }
    }
    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String outStr = argsName.get("out");
        String filter = argsName.get("filter");
        CSVReader csvReader = new CSVReader();
        csvReader.validateLength(args);
        csvReader.validateValues(path, delimiter, outStr, filter);
        handle(argsName);
    }

    public void validateLength(String[] strins) {
        if (strins.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage  ROOT_FOLDER.");
        }
        if (strins.length == 1) {
            throw new IllegalArgumentException("Set delimiter, output thread and filter, please");
        }
        if (strins.length == 2) {
            throw new IllegalArgumentException("Set output thread and filter, please");
        }
        if (strins.length == 3) {
            throw new IllegalArgumentException("Set filter, please");
        }
    }

    public void validateValues(String str0, String str1, String str2, String str3) {
        File file = new File(str0);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!str1.contains(" ") && !str1.contains(";")) {
            throw new IllegalArgumentException("delimiter must be \" \" or ;");
        }
        if (!"stdout".equals(str2)) {
            throw new IllegalArgumentException("set output as \"stdout\"");
        }
        if (!str3.contains("age") && !str3.contains("name")) {
            throw new IllegalArgumentException("Filter must contains csv file values");
        }
    }
}
