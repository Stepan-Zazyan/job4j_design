package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    private final Map<String, SoftReference<String>> cache = new HashMap<>();

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    public String load(String key) {
        StringBuilder stringBuilder = new StringBuilder();
        Path path = FileSystems.getDefault().getPath(cachingDir, key);
        if (!cache.containsKey(key)) {
            try (BufferedReader br = new BufferedReader(new FileReader(String.valueOf(path)))) {
                stringBuilder.append(br.readLine());
            } catch (IOException e) {
                throw new RuntimeException("Введено неправильное имя файла либо файл не существует");
            }
        }
        SoftReference<String> softReference = new SoftReference<>(stringBuilder.toString());
        cache.put(key, softReference);
        return stringBuilder.toString();
    }

    public Map<String, SoftReference<String>> getCache() {
        return cache;
    }
}
