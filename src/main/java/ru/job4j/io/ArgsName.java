package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

/*
 * Принимает массив аргументов который передает в карту и разбивает на ключ / значение;
 */
public class ArgsName {
    /*
     * @values - хранит ключ / значение которые были переданы из массива args;
     */
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Key not exist");
        }
        return values.get(key);
    }

    private void checkString(String line) {
        if (!line.contains("=")) {
            throw new IllegalArgumentException(
                    String.format("this name: %s does not contain the symbol \"=\"", line));
        }
        if (!line.startsWith("-")) {
            throw new IllegalArgumentException(
                    String.format("this name: %s does not start with the symbol \"-\"", line));
        }
        if (line.startsWith("-=")) {
            throw new IllegalArgumentException(
                    String.format("this name: %s contain kay ", line)
            );
        }
        if (line.indexOf("=") == line.length() - 1) {
            throw new IllegalArgumentException(
                    String.format("this name: %s does not contain a value", line));
        }
    }

    /*
     * @param args - массив аргументов
     * @values - объект Map в который передаем значения из массива
     */
    private void parse(String[] args) {
        for (String line : args) {
            checkString(line);
            String[] strings = line.split("=", 2);
            strings[0] = strings[0].split("-", 2)[1];
            values.putIfAbsent(strings[0], strings[1]);
        }
    }

    /*
     * Фабричный метод создает объект ArgsName и заполняет Map values на основе принятого массива аргументов
     * @param args переданный массив аргументов
     * @return возвращает объект типа ArgsName
     */
    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Incorrect number of argument");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
