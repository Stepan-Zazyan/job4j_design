package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

public class Config {

    private int lineCounter = 1;
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public int getLineCounter() {
        return lineCounter;
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                if (line.isBlank() || line.startsWith("#")) {
                    lineCounter++;
                    continue;
                }
                if (!line.contains("=")) {
                    throw new IllegalArgumentException("cannot find '=' sign at line " + lineCounter);
                }
                String[] res = line.split("=", 2);
                if (Objects.equals(res[0], "")
                || Objects.equals(res[1], "")) {
                    throw new IllegalArgumentException("wrong sequence in line "  + lineCounter);
                }
                values.put(res[0], res[1]);
                lineCounter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }


}
