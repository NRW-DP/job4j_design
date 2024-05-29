package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(this.path))) {
            bufferedReader.lines().forEach(s -> {
                        if (!s.trim().startsWith("#") && !s.isBlank()) {
                            String[] line = s.split("=", 2);
                            if (line.length != 2 || line[0].isEmpty() || line[1].isEmpty()) {
                                throw new IllegalArgumentException("Missing key or value : " + s);
                            }
                            values.put(line[0], line[1]);
                        }
                    }
            );

        } catch (IOException e) {
            System.out.println("Error when outputting data from a file!");
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
        System.out.println(new Config("src/main/resources/app.properties"));

    }
}
